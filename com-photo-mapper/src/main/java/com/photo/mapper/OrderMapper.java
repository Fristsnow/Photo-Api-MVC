package com.photo.mapper;

import com.photo.model.Order;
import com.photo.model.vo.OrderPhoto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> orderList();

    Order orderById(Integer id);

    void updateOrderCancel(Integer id);

    void updateOrderComplete(Integer id);

    Order orderByUserId(Integer clientId);

    Integer createOrder(OrderPhoto order);

    List<Order> orderListL(Integer clientId);

    void createTotal(double total,Integer id);
}
