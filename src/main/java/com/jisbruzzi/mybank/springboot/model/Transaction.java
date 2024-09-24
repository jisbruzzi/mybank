package com.jisbruzzi.mybank.springboot.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {
	private String slogan;
	private String id;
	private Number amount;
	private ZonedDateTime timestamp;
	private String reference;

	public Transaction(Number amount, String reference, String slogan) {
		this.amount = amount;
		this.reference = reference;
		this.slogan = slogan;
		id = UUID.randomUUID().toString();
		timestamp = ZonedDateTime.now();
	}

	public Transaction() {

	}

	public Transaction(String uuid, Number amount, String reference, String slogan, Instant instant) {
		this.id = uuid;
		this.amount = amount;
		this.reference = reference;
		this.slogan = slogan;
		this.timestamp = instant.atZone(ZoneId.systemDefault());
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

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
}
