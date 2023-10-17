package com.photo.main.controller.client;

import com.photo.common.utils.Result;
import com.photo.model.Photo;
import com.photo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Result<Photo> cart(HttpServletRequest request) {
        if (request.getSession().getAttribute("client") != null) {
            Integer user_id = (Integer) request.getSession().getAttribute("client_id");
            Photo photo = photoService.photoByUserId(user_id);
            return Result.success(photo);
        }
        return Result.error401();
    }

    /**
     * Add Cart
     * @param request
     * @param photo_id_list
     * @return
     */
    @PostMapping
    public Result<String> addCart(HttpServletRequest request, @RequestBody List<Integer> photo_id_list) {
        if (request.getSession().getAttribute("client") != null) {
            for (Integer id : photo_id_list){
                photoService.updateCart(id);
            }
            return Result.success();
        }
        return Result.error401();
    }
}
