package com.xuhao.salary.common.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    private boolean success;
    private String message;
    private T data;
    private Integer code;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.success = true;
        result.code = 200;
        result.data = data;
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.success = true;
        result.code = 200;
        return result;
    }
}
