package com.photo.common.exception;

import com.photo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
public class BaseException {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandle(SQLIntegrityConstraintViolationException exception) {
        log.error(exception.getMessage());
        return Result.error(422, "用户已经存在，请重新输入");
    }
}
