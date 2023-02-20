package com.poly.abcshop.controller;

import com.poly.abcshop.dto.Cart;
import com.poly.abcshop.dto.CategoryDto;
import com.poly.abcshop.dto.ProductDto;
import com.poly.abcshop.service.CartService;
import com.poly.abcshop.service.CategoryService;
import com.poly.abcshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    CartService cartService;

    @ModelAttribute
    public void commonAttrs(Model model , HttpSession session){
        model.addAttribute("cartCounter",
                cartService.countCart((Map<Integer, Cart>) session.getAttribute("carts")));
    }

    @GetMapping("/home")
    public String index(Model model){
        List<ProductDto> productDtos = this.productService.findTop8Discount();
        List<CategoryDto> categoryDtos = this.categoryService.findAll();

        model.addAttribute("products", productDtos);
        model.addAttribute("categories", categoryDtos);
        return "/site/index";
    }
}
