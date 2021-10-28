package com.lugano.revobank.businessRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.rules.account.BlockAccount;
import com.lugano.revobank.businessRules.rules.account.CheckAccountBlocked;
import com.lugano.revobank.businessRules.rules.account.FillInCreatedAt;
import com.lugano.revobank.businessRules.rules.account.FillInUpdatedAt;
import com.lugano.revobank.businessRules.rules.account.GenerateAccountNumber;
import com.lugano.revobank.businessRules.rules.account.GenerateBalance;
import com.lugano.revobank.businessRules.rules.account.OnlyOneDocumentInBase;
import com.lugano.revobank.businessRules.rules.account.RestrictFieldsUpdate;
import com.lugano.revobank.businessRules.rules.account.ValidateRequiredFields;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.repositories.AccountRepository;

@Service
@Transactional(readOnly = true)
public class FacadeBusinessRulesAccount {

	private Map<String, Map<String, List<BusinessRuleAccount>>> rules = new HashMap<String, Map<String, List<BusinessRuleAccount>>>();;

	@Autowired
	public FacadeBusinessRulesAccount(AccountRepository accountRepository) {

		OnlyOneDocumentInBase onlyOneDocumentInBase = new OnlyOneDocumentInBase(accountRepository);
		FillInCreatedAt fillInCreatedAt = new FillInCreatedAt();
		FillInUpdatedAt fillInUpdatedAt = new FillInUpdatedAt();
		GenerateAccountNumber generateAccountNumber = new GenerateAccountNumber();
		RestrictFieldsUpdate restrictFieldsUpdate = new RestrictFieldsUpdate(accountRepository);
		ValidateRequiredFields validateRequiredFields = new ValidateRequiredFields();
		BlockAccount blockAccount = new BlockAccount();
		GenerateBalance generateBalance = new GenerateBalance();
		CheckAccountBlocked checkAccountBlocked = new CheckAccountBlocked();

		List<BusinessRuleAccount> rulesAccountInsert = new ArrayList<BusinessRuleAccount>(4);
		rulesAccountInsert.add(validateRequiredFields);
		rulesAccountInsert.add(onlyOneDocumentInBase);
		rulesAccountInsert.add(generateAccountNumber);
		rulesAccountInsert.add(generateBalance);
		rulesAccountInsert.add(fillInCreatedAt);

		List<BusinessRuleAccount> rulesAccountUpdate = new ArrayList<BusinessRuleAccount>(3);
		rulesAccountUpdate.add(validateRequiredFields);
		rulesAccountUpdate.add(restrictFieldsUpdate);
		rulesAccountUpdate.add(fillInUpdatedAt);

		List<BusinessRuleAccount> rulesAccountBlock = new ArrayList<BusinessRuleAccount>(1);
		rulesAccountBlock.add(blockAccount);
		rulesAccountBlock.add(fillInUpdatedAt);

		List<BusinessRuleAccount> rulesAccountCheckBlock = new ArrayList<BusinessRuleAccount>(1);
		rulesAccountCheckBlock.add(checkAccountBlocked);

		Map<String, List<BusinessRuleAccount>> businessRulesAccountByOperation = new HashMap<String, List<BusinessRuleAccount>>();
		businessRulesAccountByOperation.put("INSERT", rulesAccountInsert);
		businessRulesAccountByOperation.put("UPDATE", rulesAccountUpdate);
		businessRulesAccountByOperation.put("BLOCK", rulesAccountBlock);
		businessRulesAccountByOperation.put("CHECK_BLOCK", rulesAccountCheckBlock);

		rules.put("ACCOUNT", businessRulesAccountByOperation);
	}

	public ResultFacadeAccount executeRules(Account account, String operation) {
		ResultFacadeAccount result = new ResultFacadeAccount(account);

		Map<String, List<BusinessRuleAccount>> classRules = rules.get("ACCOUNT");

		if (classRules != null) {
			List<BusinessRuleAccount> operationRules = classRules.get(operation);

			if (operationRules != null) {
				for (BusinessRuleAccount rulesAccount : operationRules) {
					ResultFacadeAccount resultRulesAccount = rulesAccount.execute(result.getAccount());
					if (resultRulesAccount.getMessages() != null && resultRulesAccount.getMessages().size() != 0) {
						result.getMessages().addAll(resultRulesAccount.getMessages());
					}
					result.setAccount(resultRulesAccount.getAccount());
				}
			}
		}
		return result;
	}

}
