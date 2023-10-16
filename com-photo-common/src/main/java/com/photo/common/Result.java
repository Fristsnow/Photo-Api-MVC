package com.photo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    private String cookies;


    public static <T> Result<T> returnData(int code, String msg, T data, String cookies) {

        Result<T> result = new Result<>();

        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        result.setCookies(cookies);

        return result;
    }

    public static <T> Result<T> cookie(String cookies) {
        return returnData(200, "success", null, cookies);
    }

    public static <T> Result<T> success() {
        return returnData(200, "success", null, null);
    }

    public static <T> Result<T> success(T data) {
        return returnData(200, "success", data, null);
    }

    public static <T> Result<T> error401() {
        return returnData(401, "认证失败", null, null);
    }

    public static <T> Result<T> error(int code, String msg) {
        return returnData(code, msg, null, null);
    }
}
