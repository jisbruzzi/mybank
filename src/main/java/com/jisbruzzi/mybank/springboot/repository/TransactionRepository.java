package com.jisbruzzi.mybank.springboot.repository;

import com.jisbruzzi.mybank.springboot.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,String> {
}
