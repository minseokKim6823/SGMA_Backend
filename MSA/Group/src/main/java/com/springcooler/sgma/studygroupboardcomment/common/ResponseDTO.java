package com.springcooler.sgma.studygroupboardcomment.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springcooler.sgma.studygroupboardcomment.common.exception.CommonException;
import com.springcooler.sgma.studygroupboardcomment.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupboardcomment.common.exception.ExceptionDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

//필기. 응답 DTO통일
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {

    @JsonIgnore
    private HttpStatus httpStatus;

    @NotNull
    private boolean success;

    @Nullable
    private T data;

    @Nullable
    private ExceptionDTO error;

    // static 팩토리 메소드
    public static <T> ResponseDTO<T> ok(T data) {
        return new ResponseDTO<>(
                HttpStatus.OK,
                true,
                data,
                null
        );
    }

    //필기. 에러 발생시의 메세지(Ad)
    public static ResponseDTO<Object> fail(@NotNull CommonException e) {
        return new ResponseDTO<>(
                e.getErrorCode().getHttpStatus(),
                false,
                null,
                ExceptionDTO.of(e.getErrorCode())
        );
    }
    
    //필기. 400번 에러 처리(프론트엔드)
    public static ResponseDTO<Object> fail(final MissingServletRequestParameterException e) {
        return new ResponseDTO<>(
                HttpStatus.BAD_REQUEST,
                false,
                null,
                ExceptionDTO.of(ErrorCode.MISSING_REQUEST_PARAMETER)
        );
    }

    public static ResponseDTO<Object> fail(final MethodArgumentTypeMismatchException e) {
        return new ResponseDTO<>(
                HttpStatus.INTERNAL_SERVER_ERROR,
                false,
                null,
                ExceptionDTO.of(ErrorCode.INVALID_PARAMETER_FORMAT)
        );
    }
}
