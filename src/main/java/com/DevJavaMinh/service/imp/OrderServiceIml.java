package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.CustomerDto;
import com.DevJavaMinh.dto.OrderDto;
import com.DevJavaMinh.mapper.OrderMapping;
import com.DevJavaMinh.model.Customer;
import com.DevJavaMinh.model.Order;
import com.DevJavaMinh.repository.CustomerRepository;
import com.DevJavaMinh.repository.OrderRepository;
import com.DevJavaMinh.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceIml implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    @Override
    public OrderDto findOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order not found"));
        return OrderMapping.mapToOrderDto(order);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapping::mapToOrderDto).toList();
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMapping.mapToOrder(orderDto);
        order = orderRepository.save(order);

        return OrderMapping.mapToOrderDto(order);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order not found"));
        Optional<Customer> customer = customerRepository.findById(orderDto.getCustomerId());
        order.setCustomer(customer.orElseThrow(()-> new RuntimeException("Customer not found")));
        order.setDate(orderDto.getDate());
        order.setPrice(orderDto.getPrice());
        return OrderMapping.mapToOrderDto(order);
    }
}
