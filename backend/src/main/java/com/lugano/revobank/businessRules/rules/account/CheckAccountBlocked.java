package com.lugano.revobank.businessRules.rules.account;

import java.util.ArrayList;
import java.util.List;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.entities.enums.AccountStatus;

public class CheckAccountBlocked implements BusinessRuleAccount {

	@Override
	public ResultFacadeAccount execute(Account account) {
		List<String> errorMessages = new ArrayList<String>();

		if (account.getStatus() == AccountStatus.BLOCKED) {
			errorMessages.add("Account Blocked.");
		}

		return new ResultFacadeAccount(errorMessages, account);
	}

}
