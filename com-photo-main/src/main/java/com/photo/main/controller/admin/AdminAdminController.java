package com.photo.main.controller.admin;

import com.photo.common.Result;
import com.photo.model.User;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.photo.main.controller.Controller.getStringResult;


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
    @GetMapping("/admin")
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
    /**
     * 改密码
     * @param id
     * @return
     */
    @PostMapping("/reset/{id}")
    public Result<HashMap<String,String>> resetPassword(@PathVariable Integer id){
        userService.resetPassword(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("password","123456");
        return Result.success(map);
    }

    /**
     * 删除admin
     * @param id
     * @return
     */
    @DeleteMapping("/admin/{id}")
    public Result<String> deleteAdmin(HttpServletRequest request, @PathVariable Integer id){
        return getStringResult(request, id, userService);
    }
}
