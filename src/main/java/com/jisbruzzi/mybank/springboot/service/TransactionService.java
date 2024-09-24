package com.jisbruzzi.mybank.springboot.service;

import com.jisbruzzi.mybank.springboot.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Service
public class TransactionService {
	private final String slogan;
	private JdbcTemplate jdbcTemplate;

	public TransactionService(@Value("${bank.slogan}") String slogan, JdbcTemplate jdbcTemplate) {
		this.slogan = slogan;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public Transaction create(Number amount, String reference) {
		System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO transactions ( slogan, reference,created_at, amount ) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, slogan);
			ps.setString(2, reference);
			ps.setTimestamp(3, Timestamp.from(Instant.now()));
			ps.setInt(4, amount.intValue());
			return ps;
		}, keyHolder);

		String uuid = !keyHolder.getKeys().isEmpty() ? keyHolder.getKeys().values().iterator().next().toString() : null;

		return new Transaction(uuid, amount, reference, slogan, Instant.now());
	}

	@Transactional
	public List<Transaction> findAll() {
		System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
		return jdbcTemplate.query("SELECT id, slogan, reference,created_at, amount FROM transactions", (resultSet, rowNumber) -> {
			Transaction invoice = new Transaction();
			invoice.setId(resultSet.getObject("id").toString());
			invoice.setSlogan(resultSet.getString("slogan"));
			invoice.setReference(resultSet.getString("reference"));
			Timestamp timestamp = resultSet.getTimestamp("created_at");
			Instant instant = timestamp.toInstant();
			invoice.setTimestamp(instant.atZone(ZoneId.systemDefault()));
			invoice.setAmount(resultSet.getInt("amount"));
			return invoice;
		});
	}
}
