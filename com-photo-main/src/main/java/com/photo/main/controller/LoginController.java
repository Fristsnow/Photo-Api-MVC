package com.photo.main.controller;

import cn.hutool.crypto.digest.BCrypt;
import com.photo.common.Result;
import com.photo.model.User;
import com.photo.model.vo.LoginVo;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    //    @PostMapping("/login")
//    public Result<Map<String,Object>> login(@RequestBody LoginVo loginVo) {
//        String password = user.getPassword();
//        password = DigestUtils.md5DigestAsHex(password.getBytes());
//        userService.lo
//        return Result.success();
//        return userService.login(loginVo);
//    }
    @PostMapping("/admin/login")
    public Result<HashMap<String,Object>> login(HttpServletRequest request, @RequestBody User user) {
//        String password = user.getPassword();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes()); // 21232F297A57A5A743894A0E4A801FC3
//        password = SecureUtil.md5(password); // 21232F297A57A5A743894A0E4A801FC3
//        password = SecureUtil.sha1(password); // d033e22ae348aeb5660fc2140aec35850c4da997
//        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        User user1 = userService.login(user.getEmail());
        if (user1 == null) {
            return Result.error(422, "用户不存在");
        }
        if (!user1.getPassword().equals(password)) {
            return Result.error(422, "密码不对");
        }
        // DigestUtils.md5DigestAsHex("dfashdfbasjdhg".getBytes())
        request.getSession().setAttribute("user", DigestUtils.md5DigestAsHex(user1.getEmail().getBytes()));

        log.info("Cookie {},{}", request.getSession().getAttribute("user"), user1.getId());

        String cookie = (String) request.getSession().getAttribute("user");

        HashMap<String, Object> map = new HashMap<>();

        map.put("id",user1.getId());
        map.put("email",user1.getEmail());
        map.put("full_name",user1.getFullName());
        map.put("cookie",cookie);
        map.put("create_time",user1.getCreateTime());

        return Result.success(map);
    }

    @PostMapping("/admin/logout")
    public Result<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return Result.success();
    }
}
