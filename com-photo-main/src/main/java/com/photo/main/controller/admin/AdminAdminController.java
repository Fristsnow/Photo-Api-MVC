package com.photo.main.controller.admin;

import com.photo.common.Result;
import com.photo.model.User;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminAdminController {

    @Autowired
    private UserService userService;

    /**
     * 所有用户
     * @return
     */
    @GetMapping("/user")
    public Result<List<User>> list() {
        List<User> userList = userService.list();
        log.info("所有用户:{}", userList);
        return Result.success(userList);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/admin")
    @ResponseBody
    public Result<User> createAdmin(@RequestBody User user) {
        log.info("Create user : {}", user.toString());
        if (!Objects.equals(user.getPassword(), user.getRepeatPassword())) {
            return Result.error(422, "密码不一致");
        }
        userService.createAdmin(user);
        return Result.success();
//        return null;
    }
}
