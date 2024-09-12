package com.jisbruzzi.service;

import com.jisbruzzi.model.Transaction;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {
	private List<Transaction> transactions = new CopyOnWriteArrayList();

	public Transaction create(Number amount, String reference) {
		Transaction transaction = new Transaction(amount, reference);
		transactions.add(transaction);
		return transaction;
	}

	public List<Transaction> findAll(){
		return transactions;
	}
}
