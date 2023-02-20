package com.poly.abcshop.service;

import com.poly.abcshop.domain.Order;
import com.poly.abcshop.dto.OrderDto;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    OrderDto create(OrderDto order);

    OrderDto findById(Long orderId);

    List<Order> findAll();

    List<Order> findAllUsername(String username);
}
