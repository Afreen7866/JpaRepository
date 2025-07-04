package com.example.demo.bank.service;

import java.util.List;

import com.example.demo.bank.dto.AccountDto;


public interface AccountService {

	AccountDto create(AccountDto account);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);
}
