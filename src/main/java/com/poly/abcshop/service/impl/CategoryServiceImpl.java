package com.poly.abcshop.service.impl;

import com.poly.abcshop.domain.Category;
import com.poly.abcshop.dto.CategoryDto;
import com.poly.abcshop.exceptions.ResourceNotFoundException;
import com.poly.abcshop.repository.CategoryRepo;
import com.poly.abcshop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category category = this.categoryRepo.save(modelMapper.map(dto, Category.class));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto dto, int categoryId) {
        Category existCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() ->  new ResourceNotFoundException("Category", "Category ID", categoryId));
        this.modelMapper.map(dto, existCategory);
        return this.modelMapper.map(existCategory, CategoryDto.class);
    }

    @Override
    public void delete(int categoryId) {
        Category existCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() ->  new ResourceNotFoundException("Category", "Category ID", categoryId));
        this.categoryRepo.delete(existCategory);
    }

    @Override
    public CategoryDto findById(int categoryId) {
        Category existCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() ->  new ResourceNotFoundException("Category", "Category ID", categoryId));
        return this.modelMapper.map(existCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> list = this.categoryRepo.findAll();

        List<CategoryDto> dtos = list.stream()
                .map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
