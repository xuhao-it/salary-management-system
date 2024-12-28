package com.xuhao.salary.application.dto.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>()
                .setCode(200)
                .setMessage("success")
                .setData(data);
    }

    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<T>()
                .setCode(code)
                .setMessage(message);
    }
}
