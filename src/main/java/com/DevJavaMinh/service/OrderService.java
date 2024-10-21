package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.OrderDto;
import com.DevJavaMinh.model.Order;

import java.util.List;

public interface OrderService {
    OrderDto findOrderById(Long id);
    List<OrderDto> findAllOrders();
    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrder(Long id, OrderDto orderDto);
}
