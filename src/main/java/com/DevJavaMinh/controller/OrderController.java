package com.DevJavaMinh.controller;

import com.DevJavaMinh.dto.OrderDto;
import com.DevJavaMinh.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(id, orderDto);
    }

}
