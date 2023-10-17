package com.photo.service.impl;

import com.photo.mapper.OrderMapper;
import com.photo.mapper.PhotoMapper;
import com.photo.model.Order;
import com.photo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<Order> orderList() {

        Order order = new Order();
        order.setPhotos(photoMapper.photoList());

        return orderMapper.orderList();
    }

    @Override
    public Order orderById(Integer id) {
        return orderMapper.orderById(id);
    }

    @Override
    public void updateOrderComplete(Integer id) {
        orderMapper.updateOrderComplete(id);
    }

    @Override
    public void updateOrderCancel(Integer id) {
        orderMapper.updateOrderCancel(id);
    }
}
