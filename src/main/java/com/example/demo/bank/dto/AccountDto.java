package com.example.demo.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {

	private Long id;
	
	private String accountHolderName;
	
	private double balance;
}
