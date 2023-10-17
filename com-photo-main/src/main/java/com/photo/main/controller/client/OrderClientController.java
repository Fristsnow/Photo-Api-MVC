package com.photo.main.controller.client;

import com.photo.common.utils.Result;
import com.photo.model.Order;
import com.photo.model.Photo;
import com.photo.service.OrderService;
import com.photo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/client/order")
public class OrderClientController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PhotoService photoService;

    /**
     * List Order
     *
     * @param request
     * @return
     */
    @GetMapping
    public Result<List<Order>> list(HttpServletRequest request) {
        if (request.getSession().getAttribute("client") != null) {
            Integer clientId = (Integer) request.getSession().getAttribute("client_id");
            List<Order> order = orderService.orderByUserIdL(clientId);
            List<Photo> photoList = photoService.photoByUserIdL(clientId);

            for (Order o : order) {
                o.setPhotos(photoList);
            }
            return Result.success(order);
        }
        return Result.error401();
    }

    /**
     * Create Order
     *
     * @param request
     * @param order
     * @return
     */
    @PostMapping
    public Result<String> createOrder(HttpServletRequest request, @RequestBody Order order) {
        if (request.getSession().getAttribute("client") != null) {
            Integer clientId = (Integer) request.getSession().getAttribute("client_id");


            for (Integer id : order.getPhoto_id_list()) {
                photoService.photoByOrderId(id);
            }
//            for (Integer ids : order.getPhoto_id_list()) {
//                Photo photo = photoService.photoById(ids);
//                Double total = (photo.getPrintPrice() + photo.getFramePrice());
//                order.setTotal(total);
//            }
            orderService.createOrder(order, clientId);
            log.info("order 订单的输出{}", order);
            HashMap<Object, Object> map = new HashMap<>();
            return Result.success();
        }
        return Result.error401();
    }

    /**
     * 取消 Order
     *
     * @param request
     * @param id
     * @return
     */
    @PostMapping("/cancel/{id}")
    public Result<String> cancelOrder(HttpServletRequest request, @PathVariable Integer id) {
        if (request.getSession().getAttribute("client") != null) {
            Order order = orderService.orderById(id);
            if (order == null && !Objects.equals(order.getStatus(), "Valid"))
                return Result.error(404, "not found");
            orderService.updateOrderCancel(id);
            return Result.success();
        }
        return Result.error401();
    }
}
