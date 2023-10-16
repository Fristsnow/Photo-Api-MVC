package com.photo.main.controller.admin;

import com.photo.common.Result;
import com.photo.model.Frame;
import com.photo.model.Order;
import com.photo.model.Photo;
import com.photo.service.OrderService;
import com.photo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/order")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public Result<List<Order>> orderList(){
        List<Order> orderList = orderService.orderList();
        List<Photo> photoList = photoService.photoList();
        Order order = new Order();
        order.setPhoto(photoList.toString());
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put()
        return Result.success(orderList);
    }

    @PostMapping("/cancel/{id}")
    public Result<Order> cancelOrder(Integer id){
        return Result.success();
    }
}
