package com.poly.abcshop.dto;

import com.poly.abcshop.domain.Account;
import com.poly.abcshop.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int productId;
    private String name;
    private int quantity;
    private double price;
    private String image;
    private String description;
    private float discount;
    private Date enteredDate;
    private short status;
    private Category category;
}
