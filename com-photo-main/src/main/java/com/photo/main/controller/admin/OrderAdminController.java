package com.photo.main.controller.admin;

import com.photo.common.utils.Result;
import com.photo.model.Order;
import com.photo.model.Photo;
import com.photo.service.OrderService;
import com.photo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Order
 */
@RestController
@Slf4j
@RequestMapping("/admin/order")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PhotoService photoService;

    /**
     * 获取所有订单
     *
     * @return
     */
    @GetMapping
    public Result<List<Order>> orderList(HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") != null) {
            List<Order> orderList = orderService.orderList();
            List<Photo> photoList = photoService.photoList();
            for (Order order : orderList) {
                order.setPhotos(photoList);
            }
            return Result.success(orderList);
        }
        return Result.error(401, "unauthenticated");
    }

    /**
     * 返回 Order
     *
     * @param id
     * @return
     */
    @PostMapping("/cancel/{id}")
    public Result<Order> cancelOrder(HttpServletRequest request, @PathVariable Integer id) {
        if (request.getSession().getAttribute("admin") != null) {
            Order order = orderService.orderById(id);
            if (order == null && !Objects.equals(order.getStatus(), "Valid"))
                return Result.error(404, "not found");
            orderService.updateOrderCancel(id);
            return Result.success();
        }
        return Result.error(401, "unauthenticated");
    }

    /**
     * 完成 Order
     *
     * @param id
     * @return
     */
    @PostMapping("/complete/{order_id}")
    public Result<Order> CompleteOrder(HttpServletRequest request, @PathVariable Integer id) {
        if (request.getSession().getAttribute("admin") != null) {
            Order order = orderService.orderById(id);
            if (order == null && Objects.equals(order.getStatus(), "Complete"))
                return Result.error(404, "not found");
            orderService.updateOrderComplete(id);
            return Result.success();
        }
        return Result.error(401, "unauthenticated");
    }
}
