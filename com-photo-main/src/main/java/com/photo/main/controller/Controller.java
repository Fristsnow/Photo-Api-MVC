package com.photo.main.controller;

import com.photo.common.utils.Result;
import com.photo.model.User;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@Slf4j
public class Controller {

    /**
     * 删除公共方法
     * @param request
     * @param id
     * @param userService
     * @return
     */
    public static Result<String> getStringResult(HttpServletRequest request, @PathVariable Integer id, UserService userService) {
        User user1 = userService.userById(id);
        String email = DigestUtils.md5DigestAsHex(user1.getEmail().getBytes());
        Object auth = request.getSession().getAttribute("user");
        if (email.equals(auth)){
            return Result.error(422,"你不能删除你自己");
        }
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 重置密码
     * @param request
     * @param id
     * @param userService
     * @return
     */
    public static Result<HashMap<String, String>> getHashMapResult(HttpServletRequest request, @PathVariable Integer id, UserService userService) {
        if (request.getSession().getAttribute("admin") != null) {
            userService.resetPassword(id);
            HashMap<String, String> map = new HashMap<>();
            map.put("password", "123456");
            return Result.success(map);
        }
        return Result.error(401, "unauthenticated");
    }

}
