package com.poly.abcshop.service.impl;

import com.poly.abcshop.domain.Account;
import com.poly.abcshop.domain.Order;
import com.poly.abcshop.dto.AccountDto;
import com.poly.abcshop.dto.OrderDto;
import com.poly.abcshop.exceptions.ResourceNotFoundException;
import com.poly.abcshop.repository.OrderRepo;
import com.poly.abcshop.service.AccountService;
import com.poly.abcshop.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AccountService accountService;

    @Override
    public OrderDto create(OrderDto order) {
        Order item = this.orderRepo.save(this.modelMapper.map(order, Order.class));
        return this.modelMapper.map(item, OrderDto.class);
    }

    @Override
    public OrderDto findById(Long orderId) {
        Order order = this.orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Order ID", orderId));
        return this.modelMapper.map(order, OrderDto.class);
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = this.orderRepo.findAll();
        return list;
    }

    @Override
    public List<Order> findAllUsername(String username) {
        AccountDto account = this.accountService.findByUsername(username);
        List<Order> list = this.orderRepo.findAllByAccount(this.modelMapper.map(account, Account.class));
        return list;
    }
}
