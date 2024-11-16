package com.springcooler.sgma.user.command.domain.aggregate.vo.login;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOAuthLoginVO {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("access_token_expiry")
    private Date accessTokenExpiry;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("refresh_token_expiry")
    private Date refreshTokenExpiry;

    @JsonProperty("user_identifier")
    private String userIdentifier;

    @JsonProperty("is_profile_incomplete") // 프로필 정보가 불완전한지 여부
    private boolean isProfileIncomplete;
}
