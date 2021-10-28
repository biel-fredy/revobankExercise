package com.lugano.revobank.businessRules.results;

import java.util.List;

import com.lugano.revobank.entities.Debit;

public class ResultFacadeDebit extends AbstractResultFacadeBusinessRules {

	private Debit debit;
	private Boolean setAccountBlock = false;

	public ResultFacadeDebit() {
	}

	public ResultFacadeDebit(List<String> messages, Debit debit) {
		super(messages);
		this.debit = debit;
	}

	public ResultFacadeDebit(List<String> messages, Debit debit, Boolean setAccountBlock) {
		super(messages);
		this.debit = debit;
		this.setAccountBlock = setAccountBlock;
	}

	public ResultFacadeDebit(Debit debit) {
		super();
		this.debit = debit;
	}

	public Debit getDebit() {
		return debit;
	}

	public void setDebit(Debit debit) {
		this.debit = debit;
	}

	public Boolean getSetAccountBlock() {
		return setAccountBlock;
	}

	public void setSetAccountBlock(Boolean setAccountBlock) {
		this.setAccountBlock = setAccountBlock;
	}

}
