package com.springcooler.sgma.user.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RequestNicknameDTO {
    @JsonProperty("nickname")
    private String nickname; // 변경, 가입 경로 + 사용자id 조합
}
