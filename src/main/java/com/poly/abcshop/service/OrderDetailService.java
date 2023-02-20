package com.poly.abcshop.service;

import com.poly.abcshop.domain.OrderDetail;
import com.poly.abcshop.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto create(OrderDetailDto orderDetail);

    List<OrderDetailDto> findAllByOrder(Long orderId);
}
