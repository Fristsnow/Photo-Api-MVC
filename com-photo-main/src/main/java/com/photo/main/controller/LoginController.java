package com.photo.main.controller;

import com.photo.common.Result;
import com.photo.model.User;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
@Slf4j
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * admin login
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/admin/login")
    public Result<HashMap<String,Object>> AdminLogin(HttpServletRequest request, @RequestBody User user) {

        User user1 = userService.login(user.getEmail());
        if (user1 == null) {
            return Result.error(422, "用户不存在");
        }
        if (user1.getIsAdmin() != 1) return Result.error(409,"你没有权限访问，请联系管理员");

        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes()); // 21232F297A57A5A743894A0E4A801FC3

        if (!user1.getPassword().equals(password)) {
            return Result.error(422, "密码不对");
        }

        request.getSession().setAttribute("user", DigestUtils.md5DigestAsHex(user1.getEmail().getBytes()));

        return getHashMapResult(request, user1);
    }

    /**
     * admin logout
     * @param request
     * @return
     */
    @PostMapping("/admin/logout")
    public Result<String> AdminLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("id");
        return Result.success();
    }

    /**
     * Client login
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/client/login")
    public Result<HashMap<String,Object>> ClientLogin(HttpServletRequest request, @RequestBody User user) {

        User user1 = userService.login(user.getEmail());
        if (user1 == null) {
            return Result.error(422, "用户不存在");
        }

        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes()); // 21232F297A57A5A743894A0E4A801FC3

        if (!user1.getPassword().equals(password)) {
            return Result.error(422, "密码不对");
        }

        request.getSession().setAttribute("user", DigestUtils.md5DigestAsHex(user1.getEmail().getBytes()));
        request.getSession().setAttribute("id", user1.getId());

        return getHashMapResult(request, user1);
    }

    /**
     * Client logout
     * @param request
     * @return
     */
    @PostMapping("/client/logout")
    public Result<String> ClientLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("id");
        return Result.success();
    }

    /**
     * 登录公共方法
     * @param request
     * @param user1
     * @return
     */
    private Result<HashMap<String, Object>> getHashMapResult(HttpServletRequest request, User user1) {
        log.info("Cookie {},{}", request.getSession().getAttribute("user"), user1.getId());

        String cookie = (String) request.getSession().getAttribute("user");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",user1.getId());
        map.put("email",user1.getEmail());

        if (user1.getIsAdmin() == 1){
            map.put("full_name",user1.getFullName());
        }else {
            map.put("username",user1.getUsername());
        }

        map.put("cookie",cookie);
        map.put("create_time",user1.getCreateTime());

        return Result.success(map);
    }
}
