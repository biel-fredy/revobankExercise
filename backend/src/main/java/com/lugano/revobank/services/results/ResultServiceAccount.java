package com.lugano.revobank.services.results;

import java.util.ArrayList;
import java.util.List;

import com.lugano.revobank.dto.AccountDTO;
import com.lugano.revobank.entities.Account;

public class ResultServiceAccount extends AbstractResultService {

	private List<AccountDTO> resultAccountList = new ArrayList<AccountDTO>();

	public ResultServiceAccount() {
	}

	public ResultServiceAccount(Account account) {
		this.addAccountDTO(account);
	}

	public ResultServiceAccount(List<Account> accounts) {
		accounts.forEach(account -> this.addAccountDTO(new AccountDTO(account)));
	}
	
	public ResultServiceAccount(Account account, List<String> messages) {
		this.addAccountDTO(account);
		this.AddMessages(messages);
	}

	public List<AccountDTO> getResultAccountList() {
		return resultAccountList;
	}

	public void addAccountDTO(AccountDTO accountDTO) {
		this.resultAccountList.add(accountDTO);
	}

	public void addAccountDTO(Account account) {
		this.addAccountDTO(new AccountDTO(account));
	}

}
