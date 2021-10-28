package com.lugano.revobank.businessRules.results;

import java.util.List;

import com.lugano.revobank.entities.Account;

public class ResultFacadeAccount extends AbstractResultFacadeBusinessRules {

	private Account account;

	public ResultFacadeAccount() {
	}

	public ResultFacadeAccount(List<String> messages, Account account) {
		super(messages);
		this.account = account;
	}
	
	public ResultFacadeAccount(Account account) {
		super();
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
