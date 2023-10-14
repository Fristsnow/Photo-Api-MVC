package com.photo.main.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result returnData(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success() {
        return returnData(200, "success", null);
    }

    public static Result success(Object data) {
        return returnData(200, "success", data);
    }
}
