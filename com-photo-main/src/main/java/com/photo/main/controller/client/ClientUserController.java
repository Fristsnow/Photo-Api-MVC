package com.photo.main.controller.client;

import com.photo.common.utils.Result;
import com.photo.model.User;
import com.photo.model.vo.Reset;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/client")
public class ClientUserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result<User> createAdmin(HttpServletRequest request, @RequestBody User user) {
        if (request.getSession().getAttribute("client") != null) {
            log.info("Create Client user : {}", user.toString());
            if (!Objects.equals(user.getPassword(), user.getRepeatPassword())) {
                return Result.error(422, "密码不一致");
            }
            userService.createClient(user);
            return Result.success();
        }
        return Result.error401();
    }

    /**
     * 修改密码
     * @param request
     * @param reset
     * @return
     */
    @PostMapping("/user/reset")
    public Result<String> reset(HttpServletRequest request, @RequestBody Reset reset) {
        if (request.getSession().getAttribute("client") != null) {
            Integer clientId = (Integer) request.getSession().getAttribute("client_id");
            User user = userService.userById(clientId);
            String old = DigestUtils.md5DigestAsHex(reset.getOriginal_password().getBytes());
            String newPassword = DigestUtils.md5DigestAsHex(reset.getNew_password().getBytes());
            if (!user.getPassword().equals(old)) return Result.error(422, "新旧密码不一致");
            if (!Objects.equals(reset.getNew_password(), reset.getRepeat_password()))
                return Result.error(422, "两次密码输入不一致");
            userService.userReset(newPassword, clientId);
            return Result.success();
        }
        return Result.error401();
    }
}
