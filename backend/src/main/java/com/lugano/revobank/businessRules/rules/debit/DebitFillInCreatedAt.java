package com.lugano.revobank.businessRules.rules.debit;

import java.time.Instant;

import com.lugano.revobank.businessRules.results.ResultFacadeDebit;
import com.lugano.revobank.businessRules.specs.BusinessRuleDebit;
import com.lugano.revobank.entities.Debit;

public class DebitFillInCreatedAt implements BusinessRuleDebit {

	@Override
	public ResultFacadeDebit execute(Debit debit) {
		debit.setCreatedAt(Instant.now());
		return new ResultFacadeDebit(null, debit);
	}

}
