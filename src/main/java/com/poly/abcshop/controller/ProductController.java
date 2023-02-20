package com.poly.abcshop.controller;

import com.poly.abcshop.config.AppConstants;
import com.poly.abcshop.domain.Category;
import com.poly.abcshop.domain.Product;
import com.poly.abcshop.dto.CategoryDto;
import com.poly.abcshop.dto.ProductDto;
import com.poly.abcshop.service.CategoryService;
import com.poly.abcshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String listProc(Model model
            , @ModelAttribute(name = "pageNumber", binding = false) String pageNumber
            , @ModelAttribute(name = "choose", binding = false) String choose){

        getAllCate(model);

        switch (choose){
            case "lowPrice" :
                getAll(model, pageNumber, "price", "asc");
                break;
            case "highPrice" :
                getAll(model, pageNumber, "price", "desc");
                break;

            default:
                getAll(model, pageNumber, "name", "asc");
        }

        return "/site/products";
    }

    @GetMapping("/category/{categoryId}")
    public String getAllByCategory(Model model, @PathVariable int categoryId){

        List<ProductDto> list = this.productService.findAllByCategoryId(categoryId);
        getAllCate(model);
        model.addAttribute("products", list);
        return "/site/products-cate";
    }

    @GetMapping("/detail/{productId}")
    public String getSingleProc(Model model, @PathVariable int productId){
        ProductDto dto = this.productService.findById(productId);
        model.addAttribute("item", dto);

        List<ProductDto> dtos = this.productService.findAllByCategoryId(dto.getCategory().getCategoryId());
        model.addAttribute("items", dtos);

        return "/site/detail";
    }


    private void getAll(Model model, String pageNumber, String sortBy, String sortDir){
        List<ProductDto> countProc = productService.findAll();
        int maxPage = (int) Math.ceil(countProc.size() / (double) AppConstants.PAGE_SIZE);
        model.addAttribute("maxPage", maxPage);
        List<ProductDto> list;
        if (pageNumber.isEmpty() || Integer.valueOf(pageNumber) > maxPage){
            list = productService
                    .getAllProcSort(0
                            , AppConstants.PAGE_SIZE, sortBy, sortDir);
            model.addAttribute("currentPage", 0);
            model.addAttribute("pageNumber", 0);
        }else{
            list = productService
                    .getAllProcSort(Integer.valueOf(pageNumber)
                            , AppConstants.PAGE_SIZE, sortBy, sortDir);
            model.addAttribute("currentPage", Integer.valueOf(pageNumber));
        }
        System.out.println("PageNumber: " + pageNumber + ", maxPage:" + maxPage);
        model.addAttribute("products", list);
    }

    private void getAllCate(Model model){
        List<CategoryDto> list = this.categoryService.findAll();
        model.addAttribute("categories", list);
    }
}
