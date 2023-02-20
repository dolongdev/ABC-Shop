package com.poly.abcshop.dto;

import com.poly.abcshop.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long id;
    private int quantity;
    private OrderDto order;
    private ProductDto product;
}
