package com.lugano.revobank.businessRules.rules.account;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.entities.enums.AccountStatus;

public class BlockAccount implements BusinessRuleAccount {

	@Override
	public ResultFacadeAccount execute(Account account) {
		account.setStatus(AccountStatus.BLOCKED);
		return new ResultFacadeAccount(null, account);
	}

}
