package com.lugano.revobank.businessRules.specs;

import com.lugano.revobank.businessRules.results.ResultFacadeBalance;
import com.lugano.revobank.entities.Balance;

public interface BusinessRuleBalance {

	public ResultFacadeBalance execute(Balance balance);

}
