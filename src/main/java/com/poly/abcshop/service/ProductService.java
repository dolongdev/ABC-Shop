package com.poly.abcshop.service;

import com.poly.abcshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto productDto);

    ProductDto update(ProductDto productDto, int productId);

    ProductDto findById(int productId);

    void delete(int productId);

    List<ProductDto> findAll();

    List<ProductDto> findTop8Discount();

    List<ProductDto> getAllProcSort(int pageNumber, int pageSize, String sortBy, String sortDir);

    List<ProductDto> findAllByCategoryId(int categoryId);

//    List<ProductDto> findAllByCate(int categoryId, int pageNumber, int pageSize, String sortBy, String sortDir);
}
