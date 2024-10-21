package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.OrderDto;
import com.DevJavaMinh.model.Order;

public class OrderMapping {
    public static Order mapToOrder(OrderDto orderDto) {
        Order orderObj = new Order();
        orderObj.setOrderId(orderDto.getOrderId());
        orderObj.setDate(orderDto.getDate());
        orderObj.setPrice(orderDto.getPrice());
        orderObj.setStatus(orderDto.isStatus());
        return orderObj;
    }
    public static OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getCustomer().getCustomerId(),
                order.getDate(),
                order.getPrice(),
                order.getStatus()
        );
    }
}
