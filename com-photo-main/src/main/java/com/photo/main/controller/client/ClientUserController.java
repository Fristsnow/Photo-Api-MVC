package com.photo.main.controller.client;

import com.photo.common.Result;
import com.photo.model.User;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/client")
public class ClientUserController {

    @Autowired
    private UserService userService;
    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result<User> createAdmin(@RequestBody User user) {
        log.info("Create Client user : {}", user.toString());
        if (!Objects.equals(user.getPassword(), user.getRepeatPassword())) {
            return Result.error(422, "密码不一致");
        }
        userService.createClient(user);
        return Result.success();
//        return null;
    }
}
