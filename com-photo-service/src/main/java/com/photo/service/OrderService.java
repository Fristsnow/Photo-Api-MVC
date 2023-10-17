package com.photo.service;

import com.photo.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> orderList();

    Order orderById(Integer id);

    void updateOrderCancel(Integer id);

    void updateOrderComplete(Integer id);
}
