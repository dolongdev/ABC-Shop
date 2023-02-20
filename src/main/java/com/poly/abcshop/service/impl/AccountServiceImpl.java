package com.poly.abcshop.service.impl;

import com.poly.abcshop.domain.Account;
import com.poly.abcshop.dto.AccountDto;
import com.poly.abcshop.exceptions.ResourceNotFoundException;
import com.poly.abcshop.repository.AccountRepo;
import com.poly.abcshop.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public AccountDto create(AccountDto entity) {
        Account account = this.accountRepo.save(this.modelMapper.map(entity, Account.class));
        return this.modelMapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto findByUsername(String username) {
        Account account = accountRepo.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Username", username));

        return this.modelMapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto update(AccountDto dto, String username) {
        Account account = accountRepo.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Username", username));
        account.setAbout(dto.getAbout());
        account.setPhone(dto.getPhone());
        account.setEmail(dto.getEmail());
        account.setFullname(dto.getFullname());
        this.accountRepo.save(account);
        return this.modelMapper.map(account, AccountDto.class);
    }

    @Override
    public List<AccountDto> list() {
        List<Account> list = this.accountRepo.findAll();

        List<AccountDto> dtos = list.stream()
                .map((account) -> this.modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
