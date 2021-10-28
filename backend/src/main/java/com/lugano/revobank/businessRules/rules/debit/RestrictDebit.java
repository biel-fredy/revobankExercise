package com.lugano.revobank.businessRules.rules.debit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.lugano.revobank.businessRules.results.ResultFacadeDebit;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccountWithDatabaseQuery;
import com.lugano.revobank.businessRules.specs.BusinessRuleDebit;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.entities.Debit;
import com.lugano.revobank.entities.enums.AccountStatus;
import com.lugano.revobank.entities.enums.Jobs;
import com.lugano.revobank.repositories.AccountRepository;

public class RestrictDebit extends BusinessRuleAccountWithDatabaseQuery implements BusinessRuleDebit {

	public RestrictDebit(AccountRepository accountRepository) {
		super(accountRepository);
	}

	@Override
	public ResultFacadeDebit execute(Debit debit) {
		List<String> errorMessages = new ArrayList<String>();

		if (debit.getAmount().compareTo(debit.getBalance().getAmount()) > 0) {	
			Account account = getAccountRepository().getById(debit.getBalance().getId());
			if (account.getJobTitle() == Jobs.COUNTER || account.getJobTitle() == Jobs.PROGRAMMER) {
				account.setStatus(AccountStatus.BLOCKED);
				account.setUpdatedAt(Instant.now());
				getAccountRepository().save(account);
				errorMessages.add("Debit Amount greater than Balance.");
			}
		}
		return new ResultFacadeDebit(errorMessages, debit);
	}

}
