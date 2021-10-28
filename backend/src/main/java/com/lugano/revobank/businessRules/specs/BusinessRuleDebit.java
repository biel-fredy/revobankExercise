package com.lugano.revobank.businessRules.specs;

import com.lugano.revobank.businessRules.results.ResultFacadeDebit;
import com.lugano.revobank.entities.Debit;

public interface BusinessRuleDebit {

	public ResultFacadeDebit execute(Debit debit);

}
