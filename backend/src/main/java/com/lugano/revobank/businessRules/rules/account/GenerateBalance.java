package com.lugano.revobank.businessRules.rules.account;

import java.math.BigDecimal;
import java.time.Instant;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.entities.Balance;
import com.lugano.revobank.entities.enums.Jobs;

public class GenerateBalance implements BusinessRuleAccount {

	@Override
	public ResultFacadeAccount execute(Account account) {
		BigDecimal balanceAmount = new BigDecimal(0);
		if (account.getJobTitle() == Jobs.COUNTER) {
			balanceAmount = new BigDecimal(5000);
		} else if (account.getJobTitle() == Jobs.PROGRAMMER) {
			balanceAmount = new BigDecimal(8000);
		} else if (account.getJobTitle() == Jobs.DIRECTOR) {
			balanceAmount = new BigDecimal(20000);
		}

		Balance balance = new Balance(null, balanceAmount, Instant.now(), null);
		account.setBalance(balance);
		return new ResultFacadeAccount(null, account);
	}

}
