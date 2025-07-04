package com.example.demo.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
