package com.springcooler.sgma.user.security;

import com.springcooler.sgma.user.command.application.service.UserService;
import com.springcooler.sgma.user.common.exception.CommonException;
import com.springcooler.sgma.user.common.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {
    private final Key key;
    private UserService userService;

    public JwtUtil(
            @Value("${token.secret}") String secretKey
            ,UserService userService
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.userService=userService;
    }

    /* 설명. Token 검증(Bearer 토큰이 넘어왔고, 우리 사이트의 secret key로 만들어 졌는가, 만료되었는지와 내용이 비어있진 않은지) */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token {}", e);
            throw new CommonException(ErrorCode.INVALID_TOKEN_ERROR);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token {}", e);
            throw new CommonException(ErrorCode.EXPIRED_TOKEN_ERROR);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token {}", e);
            throw new CommonException(ErrorCode.TOKEN_UNSUPPORTED_ERROR);
        } catch (IllegalArgumentException e) {
            log.info("JWT Token claims empty {}", e);
            throw new CommonException(ErrorCode.TOKEN_MALFORMED_ERROR);
        }
    }


    /* 설명. 넘어온 AccessToken으로 인증 객체 추출 */
    public Authentication getAuthentication(String token) {
        String userIdentifier = this.getUserId(token);
        UserDetails userDetails = userService.loadUserByUsername(userIdentifier); // userIdentifier로 사용자 로드

        Claims claims = parseClaims(token);
        log.info("넘어온 AccessToken claims 확인: {}", claims);

        String authClaim = claims.get("auth") != null ? claims.get("auth").toString() : null;
        if (authClaim == null || authClaim.trim().isEmpty()) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities
                = Arrays.stream(authClaim.replace("[", "").replace("]", "").split(", "))
                .map(String::trim)
                .filter(role -> !role.isEmpty()) // 빈 문자열을 제거
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    /* 설명. Token에서 Claims 추출 */
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /* 설명. Token에서 사용자의 id(subject 클레임) 추출 */
    public String getUserId(String token) {
        return parseClaims(token).getSubject();
    }

}
