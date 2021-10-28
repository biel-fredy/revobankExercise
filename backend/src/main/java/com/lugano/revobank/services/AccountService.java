package com.lugano.revobank.services;

import com.lugano.revobank.entities.Account;
import com.lugano.revobank.services.results.ResultServiceAccount;

public interface AccountService {

	public ResultServiceAccount save(Account account);

	public ResultServiceAccount update(Account account);

	public ResultServiceAccount block(Long id);

}
