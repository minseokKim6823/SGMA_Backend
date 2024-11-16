package com.springcooler.sgma.user.command.domain.aggregate.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.user.command.domain.aggregate.SignupPath;
import lombok.Data;

/*설명. 회원가입 시 사용자의 입력값을 받아낼 커멘드 객체용 클래스*/
@Data
public class RequestResistUserVO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("user_name")
    private String name;

    @JsonProperty("password")
    private String password;         //암호화 되기 전 사용자가 입력한 평문(비밀번호)(plain text)

    @JsonProperty("signup_path")
    private SignupPath signupPath;

}