package com.lugano.revobank.dto;

import com.lugano.revobank.entities.Balance;

public class BalanceDTO {

	private Long id;
	private String amount;
	private String createdAt;
	private String updatedAt;

	public BalanceDTO() {
	}

	public BalanceDTO(Long id, String amount, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.amount = amount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public BalanceDTO(Balance entity) {
		this.id = entity.getId();
		if(entity.getAmount() != null) {
			this.amount = entity.getAmount().toString();
		}
		if (entity.getCreatedAt() != null) {
			this.createdAt = entity.getCreatedAt().toString();
		}
		if (entity.getUpdatedAt() != null) {
			this.updatedAt = entity.getUpdatedAt().toString();
		}
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

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
