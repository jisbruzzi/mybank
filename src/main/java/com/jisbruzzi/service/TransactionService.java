package com.jisbruzzi.service;

import com.jisbruzzi.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TransactionService {
	private final String slogan;

	public TransactionService(@Value("${bank.slogan}") String slogan) {
		this.slogan = slogan;
	}

	private List<Transaction> transactions = new CopyOnWriteArrayList();

	public Transaction create(Number amount, String reference) {
		Transaction transaction = new Transaction(amount, reference, slogan);
		transactions.add(transaction);
		return transaction;
	}

	public List<Transaction> findAll() {
		return transactions;
	}
}
