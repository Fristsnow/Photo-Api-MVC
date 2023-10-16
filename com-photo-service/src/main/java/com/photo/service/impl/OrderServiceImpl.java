package com.photo.service.impl;

import com.photo.mapper.OrderMapper;
import com.photo.model.Order;
import com.photo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> orderList() {
        return orderMapper.orderList();
    }
}
