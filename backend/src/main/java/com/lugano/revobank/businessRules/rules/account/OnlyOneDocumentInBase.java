package com.lugano.revobank.businessRules.rules.account;

import java.util.ArrayList;
import java.util.List;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccountWithDatabaseQuery;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.repositories.AccountRepository;

public class OnlyOneDocumentInBase extends BusinessRuleAccountWithDatabaseQuery implements BusinessRuleAccount {

	public OnlyOneDocumentInBase(AccountRepository accountRepository) {
		super(accountRepository);
	}

	@Override
	public ResultFacadeAccount execute(Account account) {
		Account accountWithDocument = getAccountRepository().findByDocument(account.getDocument());
		List<String> errorMessage = new ArrayList<String>(1);
		if (accountWithDocument != null) {
			errorMessage.add("Documento já cadastrado");
		}
		return new ResultFacadeAccount(errorMessage, account);
	}

}
