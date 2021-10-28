package com.lugano.revobank.dto;

import com.lugano.revobank.dto.enums.EnumDTO;
import com.lugano.revobank.entities.Account;

public class AccountDTO {

	private Long id;
	private String name;
	private String document;
	private String birthDate;
	private String account;
	private String accountDigit;
	private EnumDTO jobTitle;
	private EnumDTO status;
	private String createdAt;
	private String updatedAt;
	private BalanceDTO balance;

	public AccountDTO() {
	}

	public AccountDTO(Long id, String name, String document, String birthDate, String account, String accountDigit,
			EnumDTO jobTitle, EnumDTO status, String createdAt, String updatedAt, BalanceDTO balance) {
		super();
		this.id = id;
		this.name = name;
		this.document = document;
		this.birthDate = birthDate;
		this.account = account;
		this.accountDigit = accountDigit;
		this.jobTitle = jobTitle;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.balance = balance;
	}

	public AccountDTO(Account entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.document = entity.getDocument();
		this.birthDate = entity.getBirthDate().toString();
		this.account = entity.getAccount();
		this.accountDigit = Character.toString(entity.getAccountDigit());
		this.jobTitle = new EnumDTO(entity.getJobTitle().getNumericValue().toString(),
				entity.getJobTitle().getDescription());
		this.status = new EnumDTO(entity.getStatus().getNumericValue().toString(), entity.getStatus().getDescription());
		if (entity.getCreatedAt() != null) {
			this.createdAt = entity.getCreatedAt().toString();
		}
		if (entity.getUpdatedAt() != null) {
			this.updatedAt = entity.getUpdatedAt().toString();
		}
		this.balance = new BalanceDTO(entity.getBalance());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountDigit() {
		return accountDigit;
	}

	public void setAccountDigit(String accountDigit) {
		this.accountDigit = accountDigit;
	}

	public EnumDTO getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(EnumDTO jobTitle) {
		this.jobTitle = jobTitle;
	}

	public EnumDTO getStatus() {
		return status;
	}

	public void setStatus(EnumDTO status) {
		this.status = status;
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

	public BalanceDTO getBalance() {
		return balance;
	}

	public void setBalance(BalanceDTO balance) {
		this.balance = balance;
	}

}