package com.lugano.revobank.businessRules.rules.account;

import java.util.Random;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.utils.AccountValues;

public class GenerateAccountNumber implements BusinessRuleAccount {
	
	private AccountValues accountValues = AccountValues.getInstance();

	@Override
	public ResultFacadeAccount execute(Account account) {
		account = generateAccountKey(account);
		
		return new ResultFacadeAccount(null, account);
	}
	
	public Account generateAccountKey(Account account) {
		StringBuilder accountNumber = new StringBuilder();
		Character accountDigit;
		Random generator = new Random();
        for (int i = 0; i < 7; i++) {
            accountNumber.append(generator.nextInt(10));
        }
		
		accountDigit = Integer.toString(generator.nextInt(10)).charAt(0);
		account.setAccount(accountNumber.toString());
		account.setAccountDigit(accountDigit);
		
		String accountKey = account.getAccount() + account.getAccountDigit();
		
		if (!accountValues.accountsCreated.contains(accountKey)) {
			accountValues.accountsCreated.add(accountKey);
		} else {
			account = generateAccountKey(account);
		}
		
		return account;
	}

}
