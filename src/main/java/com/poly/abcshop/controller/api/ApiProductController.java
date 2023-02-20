package com.poly.abcshop.controller.api;

import com.poly.abcshop.domain.Product;
import com.poly.abcshop.dto.ApiResponsive;
import com.poly.abcshop.dto.ProductDto;
import com.poly.abcshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public List<ProductDto> findAllProc(){
        List<ProductDto> list = this.productService.findAll();
        return list;
    }

    @GetMapping("/{productId}")
    public ProductDto findById(@PathVariable int productId){
        ProductDto product = this.productService.findById(productId);
        return product;
    }

    @GetMapping("/top8")
    public List<ProductDto> findTop8(){
        List<ProductDto> list = this.productService.findTop8Discount();
        return list;
    }

    @PostMapping("/save")
    public ProductDto createProc(@RequestBody ProductDto productDto){
        ProductDto dto = this.productService.create(productDto);
        return dto;
    }

    @PutMapping("/update/{productId}")
    public ProductDto updateProc(@RequestBody ProductDto productDto, @PathVariable int productId){
        ProductDto dto = this.productService.update(productDto, productId);
        return dto;
    }

    @DeleteMapping("/delete{productId}")
    public ApiResponsive delete(@PathVariable int productId){
        this.productService.delete(productId);
        return new ApiResponsive("Product is successfully deleted!!", true);
    }
}
