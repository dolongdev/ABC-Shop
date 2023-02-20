package com.poly.abcshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int customerId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Date registeredDate;
    private short status;
}
