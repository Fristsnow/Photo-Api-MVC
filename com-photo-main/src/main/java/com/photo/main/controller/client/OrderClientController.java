package com.photo.main.controller.client;

import com.photo.common.utils.Result;
import com.photo.model.Order;
import com.photo.model.Photo;
import com.photo.model.vo.OrderPhoto;
import com.photo.service.OrderService;
import com.photo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
//            List<Photo> photoList = photoService.photoByUserIdL(clientId);
            for (Order o : order) {
                Integer oId = o.getId();
                List<Photo> photoList = photoService.photoUserIdOrderId(clientId, oId);
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
    @PostMapping("/{photo_id_list}")
    public Result<String> createOrder(HttpServletRequest request,
                                      @RequestBody OrderPhoto order, @PathVariable List<Integer> photo_id_list) {
        if (request.getSession().getAttribute("client") != null) {
            Integer clientId = (Integer) request.getSession().getAttribute("client_id");
            order.setUserId(clientId);
            Integer ids = orderService.createOrder(order);
            log.info("Order id:{}", ids);
//            log.info("order 订单的输出{}", order);
            double total = 0;
            for (Integer id : photo_id_list) {
                Photo photo = photoService.photoById(id);
                if (photo == null)
                    return Result.error(404, "该系列图片不能添加到购物车，有可能是因为有部分图片找不到");
                if (!Objects.equals(photo.getStatus(), "cart"))
                    return Result.error(422, "该系列图片不能添加到购物车，有可能是因为有图片没有加进购物车");
                if (photo.getPrintPrice() != null && photo.getFramePrice() != null) {
                    total += photo.getPrintPrice() + photo.getFramePrice();
                }
                photoService.photoByOrderId(id, ids);
            }
            log.info("total:{}", total);
            orderService.createTotal(total, ids);

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
