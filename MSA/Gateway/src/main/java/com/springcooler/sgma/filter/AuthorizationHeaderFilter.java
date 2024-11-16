package com.springcooler.sgma.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.springcooler.sgma.common.ResponseDTO;
import com.springcooler.sgma.common.exception.CommonException;
import com.springcooler.sgma.common.exception.ErrorCode;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Environment env;  // 환경 변수를 사용하기 위한 의존성 주입

    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    public static class Config {}

    // 필터를 적용하는 메소드. 인증 헤더가 없거나 토큰이 유효하지 않으면 에러를 반환
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest(); // 클라이언트의 요청을 가져옴

            // Authorization 헤더가 없으면 에러 반환
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, ErrorCode.INVALID_HEADER_VALUE);
            }

            // Bearer 토큰 추출
            String BearerToken = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = BearerToken.replace("Bearer ", "");

            // JWT가 유효하지 않으면 에러 반환
            if (!isJwtValid(jwt)) {
                return onError(exchange, ErrorCode.INVALID_TOKEN_ERROR); //필기.이 부분도 Mono로 처리되며 구독 시점에 실행됨
            }

            // JWT가 유효하면 다음 필터로 요청 전달
            return chain.filter(exchange); //필기. Mono가 여기서 반환되고, 구독은 응답 전송 시 이루어짐
        };
    }

    // JWT 토큰의 유효성을 검사하는 메소드
    private boolean isJwtValid(String jwt) {
        try {
            // JWT에서 subject를 추출하고, subject가 존재하는지 확인
            String subject = Jwts.parser()
                    .setSigningKey(env.getProperty("token.secret")) // 비밀키로 토큰 서명 검증
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

            // subject가 없거나 빈 값이면 false 반환
            if (subject == null || subject.isEmpty()) {
                return false;
            }
        } catch (ExpiredJwtException e) {
            log.error("JWT token expired: {}", e.getMessage()); // 토큰 만료 시 로그 출력
            return false;
        } catch (Exception e) {
            log.error("JWT token invalid: {}", e.getMessage()); // 그 외 에러 시 로그 출력
            return false;
        }

        return true; // 토큰이 유효한 경우 true 반환
    }

    // 에러 발생 시 JSON 형식으로 응답을 반환하는 메소드
    private Mono<Void> onError(ServerWebExchange exchange, ErrorCode errorCode) {
        ServerHttpResponse response = exchange.getResponse();  // 서버 응답 객체 가져오기
        response.setStatusCode(errorCode.getHttpStatus());     // 응답 상태 코드 설정
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON); // 응답 형식을 JSON으로 설정

        // 에러 응답 객체 생성
        ResponseDTO<Object> responseDTO = ResponseDTO.fail(new CommonException(errorCode));

        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper로 객체를 JSON으로 변환
        byte[] bytes;
        try {
            // responseDTO를 JSON 바이트 배열로 변환
            bytes = objectMapper.writeValueAsBytes(responseDTO);
        } catch (Exception e) {
            log.error("Error serializing the error response", e); // 직렬화 실패 시 로그 출력
            return Mono.error(e); // 에러 Mono 반환
        }

        // 응답 바이트 배열을 데이터 버퍼로 변환 후 반환
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer)); // Mono<Void> 반환, 비동기적으로 응답 처리 완료
        /*설명.
        *   스프링 WebFlux와 스프링 Cloud Gateway는 모두 리액티브 스택에서 동작한다.
        *   즉, 요청이 들어오면 비동기적으로 처리하고, 결과가 준비될 때까지 블로킹하지 않으면서(논 블로킹 방식) 처리하는 방식이이고,
        *   스프링 게이트웨이에서 에러를 반환할 때도, 그 error 응답을 비동기적으로 처리할 수 있어야 한다.
        *   +a) Flux<T>는 여러 개의 값을 처리할 수 있지만, Mono는 1개 또는 0개의 값만을 처리
        * 필기.
        *   Publisher: Mono는 Reactive Streams 사양에 따라 동작하는 Publisher로,
        *             구독(subscribe)되기 전까지는 아무런 작업을 수행하지 않는다.
        *
        * */

        /*설명. 요약 - Mono<Void>:
         *  Mono는 Reactor의 비동기 단일 값 처리를 위한 Publisher
         *  여기서는 error 응답을 비동기적으로 처리하고 결과를 반환
         *  Mono<Void>는 응답 후 아무 값도 반환하지 않는다는 것을 의미함
         *  예를 들어, JWT 토큰이 유효하지 않은 경우 요청을 더 이상 처리할 필요가 없기 때문에
         *  즉시, error 응답을 보내야 한다.이때 Mono<Void>를 반환하여 에러 응답을 보내고 작업을 종료
         */
    }
}
