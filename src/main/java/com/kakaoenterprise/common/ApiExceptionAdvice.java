package com.kakaoenterprise.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kakaoenterprise.web.controll.UserController;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class ApiExceptionAdvice {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final ApiException e) {
        //e.printStackTrace();
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getError().getCode())
                        .message(e.getError().getMessage())
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.RUNTIME_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiExceptionEntity.builder()
                        .errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                        .message(e.getMessage())
                        .build());
    }
}