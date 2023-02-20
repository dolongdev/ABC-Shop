package com.poly.abcshop.controller.api;

import com.poly.abcshop.domain.Order;
import com.poly.abcshop.dto.OrderDto;
import com.poly.abcshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class ApiOrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrder(){
        List<Order> list = this.orderService.findAll();
        return list;
    }
}
