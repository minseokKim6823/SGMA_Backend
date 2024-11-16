package com.springcooler.sgma.user.command.domain.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserIdEmailVerificationVO {

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("")
    private String email;

    @JsonProperty("")
    private String code;
}
