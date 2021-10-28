package com.lugano.revobank.businessRules.rules.account;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccountWithDatabaseQuery;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.repositories.AccountRepository;

public class RestrictFieldsUpdate extends BusinessRuleAccountWithDatabaseQuery implements BusinessRuleAccount {

	public RestrictFieldsUpdate(AccountRepository accountRepository) {
		super(accountRepository);
	}

	@Override
	public ResultFacadeAccount execute(Account account) {
		Account oldAccount = getAccountRepository().getById(account.getId());
		oldAccount.setBirthDate(account.getBirthDate());
		oldAccount.setJobTitle(account.getJobTitle());
		oldAccount.setName(account.getName());
		oldAccount.setStatus(account.getStatus());

		return new ResultFacadeAccount(null, oldAccount);
	}

}
