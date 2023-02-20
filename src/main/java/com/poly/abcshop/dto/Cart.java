package com.poly.abcshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private double total;

    public double getTotal() {
        return price * quantity;
    }
}
