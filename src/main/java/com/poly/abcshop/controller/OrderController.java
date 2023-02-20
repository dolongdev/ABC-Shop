package com.poly.abcshop.controller;

import com.poly.abcshop.domain.Order;
import com.poly.abcshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String getAllOrder(Model model, Principal principal){
        if (principal == null){
            model.addAttribute("message"
                    , "Vui lòng đăng nhập để xử dụng giỏ hàng!");
            return "redirect:/security/login";
        }

        List<Order> list = this.orderService.findAllUsername(principal.getName());
        model.addAttribute("orders", list);

        return "/site/orders";
    }
}
