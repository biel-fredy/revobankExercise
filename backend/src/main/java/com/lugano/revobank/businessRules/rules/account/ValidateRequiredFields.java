package com.lugano.revobank.businessRules.rules.account;

import java.util.ArrayList;
import java.util.List;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.entities.Account;

public class ValidateRequiredFields implements BusinessRuleAccount {

	@Override
	public ResultFacadeAccount execute(Account account) {
		List<String> messages = new ArrayList<String>();
		
		if(account.getName() == null || account.getName().trim() == "") {
			messages.add("Name is required.");
		}
		
		if(account.getBirthDate() == null) {
			messages.add("Birth Date is required.");
		}
		
		if(account.getJobTitle() == null) {
			messages.add("Job Title is required.");
		}
		
		return new ResultFacadeAccount(messages, account);
	}

}
