package com.springcooler.sgma.user.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestUpdatePasswordUserVO {
    @JsonProperty("user_auth_id")
    private String userAuthId;

    @JsonProperty("password")
    private String password;
}
