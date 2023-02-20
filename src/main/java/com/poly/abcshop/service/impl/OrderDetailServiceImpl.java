package com.poly.abcshop.service.impl;

import com.poly.abcshop.domain.Order;
import com.poly.abcshop.domain.OrderDetail;
import com.poly.abcshop.dto.OrderDetailDto;
import com.poly.abcshop.dto.OrderDto;
import com.poly.abcshop.repository.OrderDetailRepo;
import com.poly.abcshop.service.OrderDetailService;
import com.poly.abcshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    OrderService orderService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrderDetailDto create(OrderDetailDto dto) {
        OrderDetail item = this.orderDetailRepo.save(this.modelMapper.map(dto, OrderDetail.class));
        return this.modelMapper.map(item, OrderDetailDto.class);
    }

    @Override
    public List<OrderDetailDto> findAllByOrder(Long orderId) {
        OrderDto orderDto = this.orderService.findById(orderId);
        List<OrderDetail> orderDetails =
                this.orderDetailRepo.findAllByOrder(this.modelMapper.map(orderDto, Order.class));

        List<OrderDetailDto> list = orderDetails.stream()
                .map((orderDetail) ->
                        this.modelMapper.map(orderDetail, OrderDetailDto.class)).collect(Collectors.toList());
        return list;
    }
}
