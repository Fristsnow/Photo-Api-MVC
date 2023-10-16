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
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    /**
     * Client User
     * @return
     */
    @GetMapping
    public Result<List<User>> listClient(){
        log.info("Client->用户");
        List<User> userList = userService.listClient();
        return Result.success(userList);
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
     * 删除client
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteAdmin(HttpServletRequest  request, @PathVariable Integer id){
        return getStringResult(request, id, userService);
    }


}
