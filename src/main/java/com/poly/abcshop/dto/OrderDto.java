package com.poly.abcshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String address;
    private Date create_date = new Date();
    private float amount;
    private boolean status;
    private AccountDto accountDto;
}
