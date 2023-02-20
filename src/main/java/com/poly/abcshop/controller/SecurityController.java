package com.poly.abcshop.controller;

import com.poly.abcshop.config.AppConstants;
import com.poly.abcshop.dto.AccountDto;
import com.poly.abcshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    AccountService accountService;

    @GetMapping("/login")
    public String login(){
        return "/site/login";
    }

    @GetMapping("/register")
    public String register(){
        return "/site/register";
    }

    @GetMapping("/changePass")
    public String changePass(Principal principal, Model model){
        if (principal == null){
            model.addAttribute("message"
                    , "Vui lòng đăng nhập để sử dụng chức năng này!");
            return "redirect:/security/login";
        }
        return "/site/change_pass";
    }

    @PostMapping("/changePass")
    public String changePassFunc(@RequestParam(name = "oldPass") String oldPass
            , @RequestParam(name = "newPass") String newPass
            , @RequestParam(name = "reNewPass") String reNewPass, Principal principal, Model model){
        AccountDto accountDto = this.accountService.findByUsername(principal.getName());
        if (oldPass.equals(accountDto.getPassword())){
            if (newPass.equals(oldPass)){
                model.addAttribute("message"
                        , "Mật khẩu mới không được trùng mật khẩu cũ!!");
                return "redirect:/security/changePass";
            }else {
                if (newPass.equals(reNewPass)){
                    accountDto.setPassword(reNewPass);
                    this.accountService.update(accountDto, principal.getName());
                }else {
                    model.addAttribute("message"
                            , "Nhập lại mật khẩu không đúng!!");
                    return "redirect:/security/changePass";
                }
            }
        }else {
            model.addAttribute("message", "Sai mật khẩu!!");
            return "redirect:/security/changePass";
        }

        return "redirect:/home";
    }


}
