package com.lugano.revobank.entities;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.lugano.revobank.dto.BalanceDTO;

@Entity
public class Balance {

	@Id
	@Column(name = "BAL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BAL")
	@SequenceGenerator(name = "SQ_BAL", sequenceName = "SQ_BAL", allocationSize = 1)
	private Long id;

	@Column(name = "BAL_AMOUNT")
	private BigDecimal amount;

	@Column(name = "BAL_CREATED_AT")
	private Instant createdAt;

	@Column(name = "BAL_UPDATED_AT")
	private Instant updatedAt;

	public Balance() {
	}
	
	public Balance(Long id) {
		super();
		this.id = id;
	}

	public Balance(Long id, BigDecimal amount, Instant createdAt, Instant updatedAt) {
		super();
		this.id = id;
		this.amount = amount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Balance(BalanceDTO dto) {
		this.id = dto.getId();
		if (dto.getAmount() != null) {
			this.amount = new BigDecimal(dto.getAmount());
		}
		if (dto.getCreatedAt() != null) {
			this.createdAt = Instant.parse(dto.getCreatedAt());
		}
		if (dto.getCreatedAt() != null) {
			this.updatedAt = Instant.parse(dto.getUpdatedAt());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

}
