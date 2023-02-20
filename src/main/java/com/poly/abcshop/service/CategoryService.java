package com.poly.abcshop.service;

import com.poly.abcshop.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto dto);

    CategoryDto update(CategoryDto dto, int categoryId);

    void delete(int categoryId);

    CategoryDto findById(int categoryId);

    List<CategoryDto> findAll();
}
