package com.jisbruzzi.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {
	private String id;
	private Number amount;
	private ZonedDateTime timestamp;
	private String reference;

	public Transaction(Number amount, String reference) {
		this.amount = amount;
		this.reference = reference;
		id = UUID.randomUUID().toString();
		timestamp = ZonedDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Number getAmount() {
		return amount;
	}

	public void setAmount(Number amount) {
		this.amount = amount;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}
