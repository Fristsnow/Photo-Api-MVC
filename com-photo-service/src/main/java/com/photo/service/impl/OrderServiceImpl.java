package com.photo.service.impl;

import com.photo.mapper.OrderMapper;
import com.photo.mapper.PhotoMapper;
import com.photo.model.Order;
import com.photo.model.vo.OrderPhoto;
import com.photo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
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
    public Order orderByUserId(Integer clientId) {
        return orderMapper.orderByUserId(clientId);
    }

    @Override
    public Integer createOrder(OrderPhoto order) {
        order.setStatus("Valid");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.setOrderPlace(LocalDateTime.now());
        log.info("order：{}", order);
        orderMapper.createOrder(order);
        return order.getId();
    }

    @Override
    public List<Order> orderByUserIdL(Integer clientId) {
        return orderMapper.orderListL(clientId);
    }

    @Override
    public void createTotal(double total, Integer id) {
        orderMapper.createTotal(total, id);
    }


    @Override
    public void updateOrderCancel(Integer id) {
        orderMapper.updateOrderCancel(id);
    }
}
