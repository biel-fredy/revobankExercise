package com.lugano.revobank.entities;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.lugano.revobank.dto.DebitDTO;

@Entity
public class Debit {

	@Id
	@Column(name = "DEB_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEB")
	@SequenceGenerator(name = "SQ_DEB", sequenceName = "SQ_DEB", allocationSize = 1)
	private Long id;

	@Column(name = "DEB_AMOUNT")
	private BigDecimal amount;

	@Column(name = "DEB_createdAt")
	private Instant createdAt;

	@ManyToOne
	@JoinColumn(name = "DEB_BAL_ID")
	private Balance balance;

	public Debit() {

	}

	public Debit(Long id, BigDecimal amount, Instant createdAt, Balance balance) {
		super();
		this.id = id;
		this.amount = amount;
		this.createdAt = createdAt;
		this.balance = balance;
	}

	public Debit(DebitDTO dto) {
		this.id = dto.getId();
		if (dto.getAmount() != null) {
			this.amount = new BigDecimal(dto.getAmount());
		}
		if (dto.getCreatedAt() != null) {
			this.createdAt = Instant.parse(dto.getCreatedAt());
		}
		if (dto.getBalance() != null) {
			this.balance = new Balance(dto.getBalance());
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

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

}
