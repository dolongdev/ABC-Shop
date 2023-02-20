package com.poly.abcshop.repository;

import com.poly.abcshop.domain.Order;
import com.poly.abcshop.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrder(Order order);
}
