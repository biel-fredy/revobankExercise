package com.lugano.revobank.businessRules.results;

import java.util.List;

import com.lugano.revobank.entities.Balance;

public class ResultFacadeBalance extends AbstractResultFacadeBusinessRules {

	private Balance balance;

	public ResultFacadeBalance() {
	}

	public ResultFacadeBalance(List<String> messages, Balance balance) {
		super(messages);
		this.balance = balance;
	}

	public ResultFacadeBalance(Balance balance) {
		super();
		this.balance = balance;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}
}
