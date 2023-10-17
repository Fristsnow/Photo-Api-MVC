package com.photo.main.controller.admin;

import com.photo.common.utils.Result;
import com.photo.model.User;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.photo.main.controller.Controller.getHashMapResult;
import static com.photo.main.controller.Controller.getStringResult;

/**
 * Admin
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminAdminController {

    @Autowired
    private UserService userService;

    /**
     * 所有用户
     *
     * @return
     */
    @GetMapping("/admin")
    public Result<List<User>> list(HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") != null) {
            List<User> userList = userService.list();
            log.info("所有用户:{}", userList);
            return Result.success(userList);
        }
        return Result.error(401, "unauthenticated");
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("/admin")
    @ResponseBody
    public Result<User> createAdmin(HttpServletRequest request, @RequestBody User user) {
        if (request.getSession().getAttribute("admin") != null) {
            log.info("Create user : {}", user.toString());
            if (!Objects.equals(user.getPassword(), user.getRepeatPassword())) {
                return Result.error(422, "密码不一致");
            }
            userService.createAdmin(user);
            return Result.success();
        }
        return Result.error(401, "unauthenticated");
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @PostMapping("/reset/{id}")
    public Result<HashMap<String, String>> resetPassword(HttpServletRequest request, @PathVariable Integer id) {
        return getHashMapResult(request, id, userService);
    }

    /**
     * 删除admin
     *
     * @param id
     * @return
     */
    @DeleteMapping("/admin/{id}")
    public Result<String> deleteAdmin(HttpServletRequest request, @PathVariable Integer id) {
        if (request.getSession().getAttribute("admin") != null) {
            return getStringResult(request, id, userService);
        }
        return Result.error(401, "unauthenticated");
    }
}
