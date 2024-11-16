package com.springcooler.sgma.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcooler.sgma.user.command.application.service.UserService;
import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import com.springcooler.sgma.user.command.domain.aggregate.vo.RequestLoginVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 필기. JWT(Json Web Token)의 구조
 *
 * 필기.
 *  1. 헤더(Header)
 *    - typ: 토큰의 타입 지정(JWT)
 *    - alg: 해싱 알고리즘으로 Verify Signature에서 사용 됨
 * 필기.
 *  2. 내용 또는 정보(Payload)
 *    - 토큰에 담을 정보가 들어 있음
 *    - 담는 정보의 한 조각을 클레임(claim - name과 value의 쌍으로 구성)이라 부름
 *       a. 등록된 클레임(registered claim)
 *          : 토큰에 대한 정보가 담김
 *            (iss: 토큰 발급자(issuer)
 *             sub: 토큰 제목(subject)
 *             aud: 토큰 대상자(audience)
 *             exp: 토큰의 만료 시간(expiration)
 *             nbf: 토큰 활성화(발급) 날짜(not before)
 *             iat: 토큰 활성화(발급) 시간(issued at))
 * 필기.
 *       b. 공개 클레임(public claim)
 *       	: 사용자 정의 클레임으로 공개용 정보를 위해 사용(충돌 방지를 위해 URI로 구성)
 * 필기.
 *       c. 비공개 클레임(private claim)
 *      	: 사용자 정의 클레임으로 서버(JWT 발급자)와 클라이언트 사이에 임의로 지정한 정보를 저장
 *            (충돌 발생 우려가 있어 조심해서 사용할 것)
 * 필기.
 *  3. 서명(Verify Signature)
 *    - Header 인코딩 값과 Payload 인코딩 값을 합쳐서 비밀 키로 해쉬(헤더의 해싱 알고리즘으로)하여 생성
 */
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userSerivce;
    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userSerivce, Environment env) {
        super(authenticationManager);
        this.userSerivce = userSerivce;
        this.env = env;
    }

    /* 설명. 로그인 시도 시 동작하는 기능(POST /login 요청 시) */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            // request body에서 RequestLoginVO로 변환
            RequestLoginVO creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginVO.class);

            // userIdentifier 생성 (signupPath와 email 조합)
            String userIdentifier = creds.getSignupPath() + "_" + creds.getEmail();

            // userIdentifier와 password를 사용해 UsernamePasswordAuthenticationToken 생성
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userIdentifier, creds.getPassword(), new ArrayList<>());

            // RequestLoginVO 객체를 details로 설정
            authToken.setDetails(creds);

            return getAuthenticationManager().authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /* 설명. 로그인 성공 시 JWT 토큰 생성하는 기능 */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info("로그인 성공하고 security가 관리하는 principal객체(authResult): {}", authResult);

        String email = ((User)authResult.getPrincipal()).getUsername();
        SignupPath signupPath = ((RequestLoginVO) authResult.getDetails()).getSignupPath(); // 로그인 시 signup_path를 받아옴

        // userIdentifier 생성 (signupPath와 email 조합)
        String userIdentifier = signupPath + "_" + email;

        // 토큰의 claims에 userIdentifier를 sub로 설정
        Claims claims = Jwts.claims().setSubject(userIdentifier);
        List<String> roles = authResult.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        claims.put("auth", roles);

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);
    }

}
