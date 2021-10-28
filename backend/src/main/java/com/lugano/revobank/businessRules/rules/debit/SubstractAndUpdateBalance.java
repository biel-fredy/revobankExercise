package com.lugano.revobank.businessRules.rules.debit;

import java.time.Instant;

import com.lugano.revobank.businessRules.results.ResultFacadeDebit;
import com.lugano.revobank.businessRules.specs.BusinessRuleDebit;
import com.lugano.revobank.entities.Balance;
import com.lugano.revobank.entities.Debit;

public class SubstractAndUpdateBalance implements BusinessRuleDebit {

	@Override
	public ResultFacadeDebit execute(Debit debit) {
		Balance balance = debit.getBalance();
		balance.setAmount(balance.getAmount().subtract(debit.getAmount()));
		balance.setUpdatedAt(Instant.now());
		debit.setBalance(balance);
		return new ResultFacadeDebit(null, debit);
	}

}
