package com.jisbruzzi.web;

import com.jisbruzzi.dto.TransactionDto;
import com.jisbruzzi.model.Transaction;
import com.jisbruzzi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
	private TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping("/invoices")
	public List<Transaction> getTransactions(){
		return transactionService.findAll();
	}
	@PostMapping("/invoices")
	public Transaction createTransaction( @RequestBody @Valid TransactionDto transactionDto){
		return transactionService.create(transactionDto.getAmount(), transactionDto.getReference());
	}
}
