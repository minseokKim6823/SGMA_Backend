package com.springcooler.sgma.user.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestUpdatePasswordUserVO {
    @JsonProperty("password")
    private String password;
}
