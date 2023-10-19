package com.photo.main.controller.client;

import com.photo.common.utils.Result;
import com.photo.model.Photo;
import com.photo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/client/cart")
public class CartClientController {

    @Autowired
    private PhotoService photoService;

    /**
     * Cart Select
     *
     * @param request
     * @return photo
     */
    @GetMapping
    public Result<List<Photo>> cart(HttpServletRequest request) {
        if (request.getSession().getAttribute("client") != null) {
            Integer user_id = (Integer) request.getSession().getAttribute("client_id");
            List<Photo> photo = photoService.photoByUserId(user_id);
            return Result.success(photo);
        }
        return Result.error401();
    }

    /**
     * Add Cart
     *
     * @param request
     * @param photo_id_list
     * @return
     */
    @PostMapping("/{photo_id_list}")
    public Result<String> addCart(HttpServletRequest request,
                                  @PathVariable List<Integer> photo_id_list) {
        if (request.getSession().getAttribute("client") != null) {
            for (Integer id : photo_id_list) {
                Photo photo = photoService.photoById(id);
                if (photo == null)
                    return Result.error(404, "该系列图片不能添加到购物车，有可能是因为有部分图片找不到");
                if (!Objects.equals(photo.getStatus(), "uploaded"))
                    return Result.error(422, "该系列图片不能添加到购物车，有可能是因为有图片进入了购物车");
                photoService.updateCart(id);
            }
            return Result.success();
        }
        return Result.error401();
    }
}
