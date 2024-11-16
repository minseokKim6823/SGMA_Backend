package com.springcooler.sgma.user.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.user.command.domain.aggregate.AcceptStatus;
import com.springcooler.sgma.user.command.domain.aggregate.ActiveStatus;
import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("email")
    private String email; // 신규 추가

    @JsonProperty("user_auth_id")
    private String userAuthId;

    @JsonProperty("user_status")
    private ActiveStatus userStatus = ActiveStatus.ACTIVE;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("withdrawn_at")
    private LocalDateTime withdrawnAt;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("accept_status")
    private AcceptStatus acceptStatus = AcceptStatus.N;

    @JsonProperty("signup_path")
    private SignupPath signupPath;

    @JsonProperty("user_identifier")
    private String userIdentifier; // 변경, 가입 경로 + 사용자id 조합
}

