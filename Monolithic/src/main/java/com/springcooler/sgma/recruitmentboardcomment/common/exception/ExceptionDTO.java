package com.springcooler.sgma.recruitmentboardcomment.common.exception;

import com.springcooler.sgma.recruitmentboardreply.common.exception.ErrorCode;
import lombok.Getter;
//필기. 에러 응답 형식(코드,메시지)
@Getter
public class ExceptionDTO {
    private final Integer code;
    private final String message;
    public ExceptionDTO(com.springcooler.sgma.recruitmentboardreply.common.exception.ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ExceptionDTO of(ErrorCode errorCode){
        return new ExceptionDTO(errorCode);
    }
}
