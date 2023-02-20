package com.poly.abcshop.repository;

import com.poly.abcshop.domain.Account;
import com.poly.abcshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByAccount(Account account);
}
