package com.poly.abcshop.repository;

import com.poly.abcshop.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductPageRepo extends PagingAndSortingRepository<Product, Integer> {
    List<Product> findAllByCategory(int categoryId, Pageable pageable);
}
