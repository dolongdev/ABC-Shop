package com.poly.abcshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardItem {
    private int productId;
    private String name;
    private int quantity;
    private double unitPrice;
}
