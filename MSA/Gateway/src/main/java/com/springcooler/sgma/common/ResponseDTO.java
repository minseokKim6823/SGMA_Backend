package com.springcooler.sgma.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springcooler.sgma.common.exception.CommonException;
import com.springcooler.sgma.common.exception.ErrorCode;
import com.springcooler.sgma.common.exception.ExceptionDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

//필기. 응답 DTO 통일
@Data
public class ResponseDTO<T> {

    @JsonIgnore // 필기. HttpStatus는 응답 바디에 포함되지 않도록 설정
    private HttpStatus httpStatus;

    @NotNull // 성공 여부는 필수
    private boolean success;

    @Nullable // 데이터는 있을 수도, 없을 수도 있음
    private T data;

    @Nullable // 에러는 있을 수도, 없을 수도 있음
    private ExceptionDTO error;

    // 기본 생성자
    public ResponseDTO() {}

    // 모든 필드를 받는 생성자
    public ResponseDTO(HttpStatus httpStatus, boolean success, @Nullable T data, @Nullable ExceptionDTO error) {
        this.httpStatus = httpStatus;
        this.success = success;
        this.data = data;
        this.error = error;
    }

    // 성공 시 응답을 생성하는 static 메서드
    public static <T> ResponseDTO<T> ok(T data) {
        return new ResponseDTO<>(
                HttpStatus.OK, // 200 OK 상태
                true, // 성공이므로 true
                data, // 데이터를 담음
                null // 에러는 없음
        );
    }

    // 실패 시 CommonException을 기반으로 응답 생성
    public static ResponseDTO<Object> fail(@NotNull CommonException e) {
        return new ResponseDTO<>(
                e.getErrorCode().getHttpStatus(), // 에러의 HttpStatus 가져옴
                false, // 실패이므로 false
                null, // 데이터는 없음
                ExceptionDTO.of(e.getErrorCode()) // 에러 정보를 담음
        );
    }

    // 특정 예외 처리: 필수 요청 파라미터가 없을 때
    public static ResponseDTO<Object> fail(final MissingServletRequestParameterException e) {
        return new ResponseDTO<>(
                HttpStatus.BAD_REQUEST, // 400 BAD_REQUEST
                false, // 실패이므로 false
                null, // 데이터 없음
                ExceptionDTO.of(ErrorCode.MISSING_REQUEST_PARAMETER) // 에러 정보
        );
    }

    // 특정 예외 처리: 메소드 인자 타입이 일치하지 않을 때
    public static ResponseDTO<Object> fail(final MethodArgumentTypeMismatchException e) {
        return new ResponseDTO<>(
                HttpStatus.BAD_REQUEST, // 400 BAD_REQUEST (여기서 INTERNAL_SERVER_ERROR 대신 BAD_REQUEST가 적절)
                false, // 실패
                null, // 데이터 없음
                ExceptionDTO.of(ErrorCode.INVALID_PARAMETER_FORMAT) // 에러 정보
        );
    }
}
