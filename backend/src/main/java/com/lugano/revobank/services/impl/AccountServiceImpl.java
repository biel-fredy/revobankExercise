package com.lugano.revobank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lugano.revobank.businessRules.FacadeBusinessRulesAccount;
import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.repositories.AccountRepository;
import com.lugano.revobank.services.AccountService;
import com.lugano.revobank.services.results.ResultServiceAccount;

@Service
@Transactional(readOnly = false)
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	private FacadeBusinessRulesAccount facadeBusinessRulesAccount;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository,
			FacadeBusinessRulesAccount facadeBusinessRulesAccount) {
		super();
		this.accountRepository = accountRepository;
		this.facadeBusinessRulesAccount = facadeBusinessRulesAccount;
	}

	@Override
	public ResultServiceAccount save(Account account) {
		ResultFacadeAccount resultFacade = facadeBusinessRulesAccount.executeRules(account, "INSERT");
		ResultServiceAccount resultService = new ResultServiceAccount();
		if(resultFacade.getMessages().size() <= 0) {
			accountRepository.save(resultFacade.getAccount());
			resultService.addAccountDTO(resultFacade.getAccount());
			resultService.setSuccessOrFailed(true);
		} else {
			resultService.AddMessages(resultFacade.getMessages());
			resultService.addAccountDTO(account);
			resultService.setSuccessOrFailed(false);
		}
		return resultService;
	}

	@Override
	public ResultServiceAccount update(Account account) {		
		ResultFacadeAccount resultFacade = facadeBusinessRulesAccount.executeRules(account, "UPDATE");		
		ResultServiceAccount resultService = new ResultServiceAccount();		
		if(resultFacade.getMessages().size() <= 0) {
			accountRepository.save(resultFacade.getAccount());
			resultService.addAccountDTO(resultFacade.getAccount());
			resultService.setSuccessOrFailed(true);
		} else {
			resultService.addAccountDTO(account);
			resultService.AddMessages(resultFacade.getMessages());
			resultService.setSuccessOrFailed(false);
		}
		return resultService;
	}

	@Override
	public ResultServiceAccount block(Long id) {
		Account account = accountRepository.getById(id);
		ResultFacadeAccount resultFacade = facadeBusinessRulesAccount.executeRules(account, "BLOCK");		
		ResultServiceAccount resultService = new ResultServiceAccount();
		if(resultFacade.getMessages().size() <= 0) {
			accountRepository.save(resultFacade.getAccount());
			resultService.addAccountDTO(resultFacade.getAccount());
			resultService.setSuccessOrFailed(true);
		} else {
			resultService.addAccountDTO(account);
			resultService.AddMessages(resultFacade.getMessages());
			resultService.setSuccessOrFailed(false);
		}
		return resultService;
	}

}
