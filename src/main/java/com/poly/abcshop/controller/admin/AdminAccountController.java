package com.poly.abcshop.controller.admin;

import com.poly.abcshop.dto.AccountDto;
import com.poly.abcshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administration/accounts")
public class AdminAccountController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public String listAcc(Model model){
        List<AccountDto> list = this.accountService.list();
        model.addAttribute("accounts", list);
        return "/admin/accounts";
    }

    @GetMapping("/addOrEdit")
    public String addOrEdit(@ModelAttribute(name = "collection") String collection
            , @ModelAttribute(name = "username") String username,
                            Model model){
        if (collection.equalsIgnoreCase("add") || username.isEmpty()){
            model.addAttribute("item", new AccountDto());
        }else {
            AccountDto item = this.accountService.findByUsername(username);
            model.addAttribute("item", item);
        }
        return "/admin/add-or-edit-acc";
    }

    @PostMapping("/addOrEdit")
    public String addOrEditPost(Model model, @ModelAttribute(name = "item") AccountDto item
            , @ModelAttribute(name = "collection") String collection){
        Boolean existAccount = this.accountService.existAccount(item.getUsername());

        if (collection.equalsIgnoreCase("add")){
            if (existAccount == true){
                model.addAttribute("message", "Tên đăng nhập đã tồn tại!!");
            }else {
                AccountDto dto = this.accountService.create(item);
                model.addAttribute("item", dto);
                model.addAttribute("message", "Thêm mới thành công!!");
                System.out.println("Thêm mới thành công!!");
            }
//            return "redirect:/administration/accounts/addOrEdit?collection=add";
            return "/admin/add-or-edit-acc";
        } else if (collection.equalsIgnoreCase("update")) {
            this.accountService.update(item, item.getUsername());
            model.addAttribute("message", "Cập nhật thành công!!");
            return "/admin/add-or-edit-acc";
        }
        return "redirect:/administration/accounts";
    }

    @PostMapping("/search")
    public String searchAccount(Model model, @RequestParam(name = "keyword") String keyword){
        List<AccountDto> list = this.accountService.searchAccount(keyword);
        model.addAttribute("accounts", list);
        return "/admin/accounts";
    }
}
