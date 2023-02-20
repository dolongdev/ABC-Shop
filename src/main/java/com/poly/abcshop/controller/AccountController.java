package com.poly.abcshop.controller;

import com.poly.abcshop.dto.AccountDto;
import com.poly.abcshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal){
        if (principal == null){
            model.addAttribute("message"
                    , "Vui lòng đăng nhập để sử dụng trang này!");
            return "redirect:/security/login";
        }

        AccountDto account = this.accountService.findByUsername(principal.getName());
        model.addAttribute("currentUser", account);

        return "/site/profile";
    }

    @PostMapping("/updateAcc")
    public String updateAcc(Principal principal, @ModelAttribute(name = "currentUser") AccountDto account
            , BindingResult result){
        if (result.hasErrors()){
            return "/error";
        }
        this.accountService.update(account, principal.getName());
        return "site/profile";
    }
}
