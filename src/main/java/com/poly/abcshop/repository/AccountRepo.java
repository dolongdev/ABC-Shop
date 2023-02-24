package com.poly.abcshop.repository;

import com.poly.abcshop.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, String> {
    Boolean existsAccountByUsername(String username);

    List<Account> findByUsernameContaining(String username);
}
