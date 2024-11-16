package com.springcooler.sgma.user.command.domain.aggregate.vo.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import lombok.Data;

@Data
public class RequestLoginVO {

    @JsonProperty("user_auth_id")
    private String userAuthId;

    @JsonProperty("password")
    private String password;

    @JsonProperty("signup_path")
    private SignupPath signupPath; // 신규 추가
}
