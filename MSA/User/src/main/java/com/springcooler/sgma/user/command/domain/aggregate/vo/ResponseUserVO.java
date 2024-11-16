package com.springcooler.sgma.user.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.user.command.domain.aggregate.AcceptStatus;
import com.springcooler.sgma.user.command.domain.aggregate.ActiveStatus;
import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseUserVO {

    @JsonProperty("user_id")
    private Long userId; // 사용자 고유 ID (DB 기본 키)

    @JsonProperty("user_auth_id")
    private String userAuthId; // 사용자 인증 ID, 일반 로그인 시 사용자가 입력한 ID 또는 소셜 로그인 시 고유 ID

    @JsonProperty("user_name")
    private String userName; // 사용자 이름

    @JsonProperty("nickname")
    private String nickname; // 사용자 닉네임

    @JsonProperty("email")
    private String email; // 이메일 (선택 사항)

    @JsonProperty("user_status")
    private ActiveStatus userStatus; // 사용자 상태 (ACTIVE, INACTIVE 등)

    @JsonProperty("created_at")
    private LocalDateTime createdAt; // 생성 날짜

    @JsonProperty("withdrawn_at")
    private LocalDateTime withdrawnAt; // 탈퇴 날짜

    @JsonProperty("profile_image")
    private String profileImage; // 프로필 이미지

    @JsonProperty("accept_status")
    private AcceptStatus acceptStatus; // 약관 동의 여부

    @JsonProperty("signup_path")
    private SignupPath signupPath; // 가입 경로 (NORMAL, KAKAO, GOOGLE 등)

    @JsonProperty("user_identifier")
    private String userIdentifier; // 가입 경로 + user_auth_id 결합된 고유 식별자
}
