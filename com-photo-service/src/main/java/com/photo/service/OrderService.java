package com.photo.service;

import com.photo.model.Order;
import com.photo.model.vo.OrderPhoto;

import java.util.List;

public interface OrderService {
    List<Order> orderList();

    Order orderById(Integer id);

    void updateOrderCancel(Integer id);

    void updateOrderComplete(Integer id);

    Order orderByUserId(Integer clientId);

    Integer createOrder(OrderPhoto order);

    List<Order> orderByUserIdL(Integer clientId);

    void createTotal(double total,Integer id);
}
