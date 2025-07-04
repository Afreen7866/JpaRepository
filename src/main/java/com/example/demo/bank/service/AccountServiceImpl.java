package com.example.demo.bank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.bank.dto.AccountDto;
import com.example.demo.bank.mapper.AccountMapper;
import com.example.demo.bank.model.Account;
import com.example.demo.bank.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

	private AccountRepository accountRepository;
	
	@Override
	public AccountDto create(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exixts"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exixts"));
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exixts"));
	    if(account.getBalance() < amount) {
	    	throw new RuntimeException("Insufficient amount");
	    }
	    double total = account.getBalance()-amount;
	    account.setBalance(total);
	    Account savedAcscount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAcscount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exixts"));
		accountRepository.deleteById(id);
		
	}

}
