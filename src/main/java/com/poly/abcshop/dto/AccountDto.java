package com.poly.abcshop.dto;

import com.poly.abcshop.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String about;
    private String phone;
    private Date registerDate;
    List<AuthorityDto> authorities;
}
