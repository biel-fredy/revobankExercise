package com.lugano.revobank.businessRules.specs;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.entities.Account;

public interface BusinessRuleAccount {
	
	public ResultFacadeAccount execute(Account account);

}
