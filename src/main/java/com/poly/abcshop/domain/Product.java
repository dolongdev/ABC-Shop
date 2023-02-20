package com.poly.abcshop.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int productId;
    private String name;
    private int quantity;
    private double price;
    private String image;
    private String description;
    private float discount;
    private Date enteredDate;
    private short status;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    Category category;
}
