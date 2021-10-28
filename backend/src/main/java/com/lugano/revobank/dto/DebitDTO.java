package com.lugano.revobank.dto;

import com.lugano.revobank.entities.Debit;

public class DebitDTO {

	private Long id;
	private String amount;
	private String createdAt;
	private BalanceDTO balance;

	public DebitDTO() {
	}

	public DebitDTO(Long id, String amount, String createdAt, BalanceDTO balance) {
		super();
		this.id = id;
		this.amount = amount;
		this.createdAt = createdAt;
		this.balance = balance;
	}

	public DebitDTO(Debit entity) {
		this.id = entity.getId();
		this.amount = entity.getAmount().toString();
		this.createdAt = entity.getCreatedAt().toString();
		this.balance = new BalanceDTO(entity.getBalance());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public BalanceDTO getBalance() {
		return balance;
	}

	public void setBalance(BalanceDTO balance) {
		this.balance = balance;
	}

}
