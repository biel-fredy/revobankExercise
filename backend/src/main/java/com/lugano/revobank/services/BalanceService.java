package com.lugano.revobank.services;

import com.lugano.revobank.entities.Debit;
import com.lugano.revobank.services.results.ResultServiceBalance;

public interface BalanceService {
	
	public ResultServiceBalance getBalanceAmount(Long id);

	public ResultServiceBalance debitAmount(Debit debit);
}
