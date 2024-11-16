package com.springcooler.sgma.user.command.domain.aggregate.vo.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokens {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long accessTokenExpiry;
    private long refreshTokenExpiry;
    private String userIdentifier;
    private boolean isProfileIncomplete;
}
