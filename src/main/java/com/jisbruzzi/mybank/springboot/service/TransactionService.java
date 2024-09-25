package com.jisbruzzi.mybank.springboot.service;

import com.jisbruzzi.mybank.springboot.model.Transaction;
import com.jisbruzzi.mybank.springboot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class TransactionService {
	private final String slogan;
	private TransactionRepository transactionRepository;
	public TransactionService(@Value("${bank.slogan}") String slogan, TransactionRepository transactionRepository) {
		this.slogan = slogan;
		this.transactionRepository = transactionRepository;
	}

	@Transactional
	public Transaction create(Number amount, String reference) {
		Transaction t = new Transaction(amount, reference, slogan, Instant.now());
		return transactionRepository.save(t);
	}

	@Transactional
	public Iterable<Transaction> findAll() {
		return transactionRepository.findAll();
	}
}
