package com.lugano.revobank.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

import com.lugano.revobank.dto.AccountDTO;
import com.lugano.revobank.entities.enums.AccountStatus;
import com.lugano.revobank.entities.enums.Jobs;
import com.lugano.revobank.entities.enums.converters.JobsEnumConverter;
import com.lugano.revobank.entities.enums.converters.StatusEnumConverter;

@Entity
public class Account {

	@Id
	@Column(name = "ACC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACC")
	@SequenceGenerator(name = "SQ_ACC", sequenceName = "SQ_ACC", allocationSize = 1)
	private Long id;

	@Column(name = "ACC_NAME")
	private String name;

	@Column(name = "ACC_DOCUMENT")
	private String document;

	@Column(name = "ACC_BIRTH_DATE")
	private LocalDate birthDate;

	@Column(name = "ACC_ACCOUNT", unique = true)
	private String account;

	@Column(name = "ACC_ACCOUNT_DIGIT")
	private Character accountDigit;

	@Column(name = "ACC_JOB_TITLE")
	@Convert(converter = JobsEnumConverter.class)
	private Jobs jobTitle;

	@Column(name = "ACC_STATUS")
	@Convert(converter = StatusEnumConverter.class)
	private AccountStatus status;

	@Column(name = "ACC_CREATED_AT")
	private Instant createdAt;

	@Column(name = "ACC_UPDATED_AT")
	private Instant updatedAt;

	@PrimaryKeyJoinColumn(name = "teste")
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private Balance balance;

	public Account() {
	}

	public Account(Long id, String name, String document, LocalDate birthDate, String account, Character accountDigit,
			Jobs jobTitle, AccountStatus status, Instant createdAt, Instant updatedAt, Balance balance) {
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

	public Account(AccountDTO dto) {
		super();
		this.id = dto.getId();
		this.name = dto.getName();
		this.document = dto.getDocument();
		if (dto.getBirthDate() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			this.birthDate = LocalDate.parse(dto.getBirthDate(), formatter);
		}
		this.account = dto.getAccount();
		this.accountDigit = dto.getAccountDigit().trim().charAt(0);
		this.jobTitle = Jobs.valueToEnum(Integer.parseInt(dto.getJobTitle().getNumericValue()));
		this.status = AccountStatus.valueToEnum(Integer.parseInt(dto.getStatus().getNumericValue()));
		if (dto.getCreatedAt() != null) {
			this.createdAt = Instant.parse(dto.getCreatedAt());
		}
		if (dto.getCreatedAt() != null) {
			this.updatedAt = Instant.parse(dto.getUpdatedAt());
		}
		if (dto.getBalance() != null) {
			this.balance = new Balance(dto.getBalance());
		}
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", document=" + document + ", birthDate=" + birthDate
				+ ", account=" + account + ", accountDigit=" + accountDigit + ", jobTitle=" + jobTitle + ", status="
				+ status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Character getAccountDigit() {
		return accountDigit;
	}

	public void setAccountDigit(Character accountDigit) {
		this.accountDigit = accountDigit;
	}

	public Jobs getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(Jobs jobTitle) {
		this.jobTitle = jobTitle;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
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

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

}
