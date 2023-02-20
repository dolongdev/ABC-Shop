package com.poly.abcshop.repository;

import com.poly.abcshop.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, String> {
}
