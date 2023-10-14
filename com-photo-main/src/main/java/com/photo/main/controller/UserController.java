package com.photo.main.controller;

import com.photo.main.common.Result;
import com.photo.model.User;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Result list() {
        List<User> userList = userService.list();
        log.info("所有用户:{}", userList);
        return Result.success(userList);
    }
}
