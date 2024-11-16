package com.springcooler.sgma.common.exception;

import com.springcooler.sgma.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
// 게이트웨이에서 사용할 Global Exception Handler
@RestControllerAdvice(basePackages = "com.springcooler.sgma")
public class GlobalExceptionHandler {

    //필기. JWT 인증 실패 또는 유효하지 않은 토큰
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseDTO<?> handleUnauthorizedException(ResponseStatusException e) {
        log.error("Unauthorized access: {}", e.getMessage());
        return ResponseDTO.fail(new CommonException(ErrorCode.UNAUTHORIZED_ACCESS));
    }

    //필기. 서버 내부 오류 발생 시
    @ExceptionHandler(Exception.class)
    public ResponseDTO<?> handleServerException(Exception e) {
        log.error("Server error: {}", e.getMessage());
        return ResponseDTO.fail(new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
