package com.poly.abcshop.service.impl;

import com.poly.abcshop.domain.Category;
import com.poly.abcshop.domain.Product;
import com.poly.abcshop.dto.CategoryDto;
import com.poly.abcshop.dto.ProductDto;
import com.poly.abcshop.exceptions.ResourceNotFoundException;
import com.poly.abcshop.repository.CategoryRepo;
import com.poly.abcshop.repository.ProductPageRepo;
import com.poly.abcshop.repository.ProductRepo;
import com.poly.abcshop.service.CategoryService;
import com.poly.abcshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductPageRepo pageRepo;

    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = this.productRepo.save(this.modelMapper.map(productDto, Product.class));
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, int productId) {
        Product product = this.productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", productId));
        this.modelMapper.map(productDto, product);
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto findById(int productId) {
        Product product = this.productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", productId));
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void delete(int productId) {
        Product product = this.productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", productId));
        this.productRepo.delete(product);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> list = this.productRepo.findAll();

        List<ProductDto> dtos = list.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<ProductDto> findTop8Discount() {
        List<Product> list = this.productRepo.findTop8ByDiscount();

        List<ProductDto> dtos = list.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<ProductDto> getAllProcSort(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> productPage = this.productRepo.findAll(p);

        List<Product> products = productPage.getContent();

        List<ProductDto> dtos = products.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public List<ProductDto> findAllByCategoryId(int categoryId) {
        CategoryDto dto = this.categoryService.findById(categoryId);
        List<Product> products =
                this.productRepo.findAllByCategory(this.modelMapper.map(dto, Category.class));

        List<ProductDto> dtos = products.stream()
                .map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return dtos;
    }


}
