package com.photo.main.controller.admin;

import com.photo.common.utils.Result;
import com.photo.model.Photo;
import com.photo.model.User;
import com.photo.service.PhotoService;
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
 * User
 */
@RestController
@Slf4j
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;
    /**
     * Client User
     *
     * @return
     */
    @GetMapping
    public Result<List<User>> listClient(HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") != null) {
            log.info("Client->用户");
            List<User> userList = userService.listClient();
            return Result.success(userList);
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
     * 删除client
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteAdmin(HttpServletRequest request, @PathVariable Integer id) {
        if (request.getSession().getAttribute("admin") != null) {
            return getStringResult(request, id, userService);
        }
        return Result.error(401, "unauthenticated");
    }

    /**
     * 重置用户购物车
     * @param request
     * @param id
     * @return
     */
    public Result<String> resetCart(HttpServletRequest request, @PathVariable Integer id) {
        if (request.getSession().getAttribute("client") != null){

            Photo photo = photoService.photoById(id);
            if (photo != null && Objects.equals(photo.getStatus(), "cart")){
                photoService.updateValid(id);
                return Result.success();
            }else {
                return Result.error(404,"not found");
            }
        }
        return Result.error401();
    }

}
