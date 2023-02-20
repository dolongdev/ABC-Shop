package com.poly.abcshop.controller;

import com.poly.abcshop.dto.OrderDetailDto;
import com.poly.abcshop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/orderDetail/{orderId}")
    public String findAllByOrder(@PathVariable Long orderId, Model model){
        List<OrderDetailDto> list = this.orderDetailService.findAllByOrder(orderId);
        model.addAttribute("items", list);

        return "/site/order_detail";
    }

}
