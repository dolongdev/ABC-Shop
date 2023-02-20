package com.poly.abcshop.repository;

import com.poly.abcshop.domain.Category;
import com.poly.abcshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(nativeQuery = true
            , value = "SELECT TOP 4 * from products p WHERE p.discount > 0")
    List<Product> findTop8ByDiscount();

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByCategory(int categoryId, Pageable pageable);

}
