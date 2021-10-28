package com.lugano.revobank.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lugano.revobank.businessRules.FacadeBusinessRulesAccount;
import com.lugano.revobank.businessRules.FacadeBusinessRulesDebit;
import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.results.ResultFacadeDebit;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.entities.Balance;
import com.lugano.revobank.entities.Debit;
import com.lugano.revobank.repositories.AccountRepository;
import com.lugano.revobank.repositories.BalanceRepository;
import com.lugano.revobank.repositories.DebitRepository;
import com.lugano.revobank.services.BalanceService;
import com.lugano.revobank.services.results.ResultServiceBalance;

@Service
@Transactional(readOnly = false)
public class BalanceServiceImpl implements BalanceService {

	private BalanceRepository balanceRepository;

	private AccountRepository accountRepository;

	private DebitRepository debitRepository;

	private FacadeBusinessRulesAccount facadeBusinessRulesAccount;

	private FacadeBusinessRulesDebit facadeBusinessRulesDebit;

	@Autowired
	public BalanceServiceImpl(BalanceRepository balanceRepository, AccountRepository accountRepository,
			DebitRepository debitRepository, FacadeBusinessRulesAccount facadeBusinessRulesAccount,
			FacadeBusinessRulesDebit facadeBusinessRulesDebit) {
		super();
		this.balanceRepository = balanceRepository;
		this.accountRepository = accountRepository;
		this.debitRepository = debitRepository;
		this.facadeBusinessRulesAccount = facadeBusinessRulesAccount;
		this.facadeBusinessRulesDebit = facadeBusinessRulesDebit;
	}

	@Override
	@Transactional(readOnly = true)
	public ResultServiceBalance getBalanceAmount(Long id) {
		Account account = accountRepository.getById(id);
		ResultFacadeAccount resultFacadeAccount = facadeBusinessRulesAccount.executeRules(account, "CHECK_BLOCK");
		Balance balance = balanceRepository.getById(id);
		ResultServiceBalance resultService = new ResultServiceBalance();
		if (balance != null && resultFacadeAccount.getMessages().size() <= 0) {
			resultService.addBalanceDTO(balance);
			resultService.setSuccessOrFailed(true);
		} else {
			resultService.AddMessages(resultFacadeAccount.getMessages());
			resultService.setSuccessOrFailed(false);
		}
		return resultService;
	}

	@Override
	public ResultServiceBalance debitAmount(Debit debit) {
		Balance balanceQuery = debit.getBalance();
		Balance oldBalance = balanceRepository.getById(balanceQuery.getId());
		Balance newBalance = new Balance(oldBalance.getId(), oldBalance.getAmount(), oldBalance.getCreatedAt(),
				oldBalance.getUpdatedAt());
		debit.setBalance(newBalance);
		Account account = accountRepository.getById(newBalance.getId());
		ResultFacadeDebit resultFacadeDebit = facadeBusinessRulesDebit.executeRules(debit, "INSERT");
		ResultFacadeAccount resultFacadeAccount = facadeBusinessRulesAccount.executeRules(account, "CHECK_BLOCK");
		List<String> errorsMessages = new ArrayList<String>();
		errorsMessages.addAll(resultFacadeDebit.getMessages());
		errorsMessages.addAll(resultFacadeAccount.getMessages());
		ResultServiceBalance resultService = new ResultServiceBalance();
		if (errorsMessages.size() <= 0) {
			debitRepository.save(resultFacadeDebit.getDebit());
			balanceRepository.save(resultFacadeDebit.getDebit().getBalance());
			resultService.addBalanceDTO(resultFacadeDebit.getDebit().getBalance());
			resultService.setSuccessOrFailed(true);
		} else {
			resultService.AddMessages(errorsMessages);
			resultService.addBalanceDTO(balanceQuery);
			resultService.setSuccessOrFailed(false);
		}
		return resultService;
	}

}
