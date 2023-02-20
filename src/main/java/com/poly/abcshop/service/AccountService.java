package com.poly.abcshop.service;

import com.poly.abcshop.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto create(AccountDto entity);

    AccountDto findByUsername(String username);

    AccountDto update(AccountDto dto, String username);

    List<AccountDto> list();
}
