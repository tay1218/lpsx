package com.example.lpsx.common.exception;

import lombok.Getter;

/**
 * 自定义业务异常
 * <p>
 * GlobalExceptionHandler 统一捕获并转换为 Result 返回给前端
 */
@Getter
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = 400;
    }
}
