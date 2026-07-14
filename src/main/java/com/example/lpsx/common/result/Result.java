package com.example.lpsx.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回封装类
 *
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /** 状态码：200-成功，401-未授权，400-业务异常，500-系统异常 */
    private int code;

    /** 提示信息 */
    private String msg;

    /** 返回数据 */
    private T data;

    // ========== 成功 ==========

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "成功", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(200, "成功", null);
    }

    // ========== 失败 ==========

    public static <T> Result<T> error(String msg) {
        return new Result<>(400, msg, null);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    /** 未登录 */
    public static <T> Result<T> unauthorized() {
        return new Result<>(401, "未授权，请先登录", null);
    }
}
