package com.poly.abcshop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto {
    private String id;
    private AccountDto account;
    private RoleDto role;
}
