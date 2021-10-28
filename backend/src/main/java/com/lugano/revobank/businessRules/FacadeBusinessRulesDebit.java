package com.lugano.revobank.businessRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lugano.revobank.businessRules.results.ResultFacadeDebit;
import com.lugano.revobank.businessRules.rules.debit.DebitFillInCreatedAt;
import com.lugano.revobank.businessRules.rules.debit.RestrictDebit;
import com.lugano.revobank.businessRules.rules.debit.SubstractAndUpdateBalance;
import com.lugano.revobank.businessRules.specs.BusinessRuleDebit;
import com.lugano.revobank.entities.Debit;
import com.lugano.revobank.repositories.AccountRepository;

@Service
public class FacadeBusinessRulesDebit {

	private Map<String, Map<String, List<BusinessRuleDebit>>> rules = new HashMap<String, Map<String, List<BusinessRuleDebit>>>();;

	@Autowired
	public FacadeBusinessRulesDebit(AccountRepository accountRepository) {

		RestrictDebit restrictDebit = new RestrictDebit(accountRepository);
		SubstractAndUpdateBalance substractAndUpdateBalance = new SubstractAndUpdateBalance();
		DebitFillInCreatedAt debitFillInCreatedAt = new DebitFillInCreatedAt();

		List<BusinessRuleDebit> rulesDebitInsert = new ArrayList<BusinessRuleDebit>(3);
		rulesDebitInsert.add(restrictDebit);
		rulesDebitInsert.add(substractAndUpdateBalance);
		rulesDebitInsert.add(debitFillInCreatedAt);

		Map<String, List<BusinessRuleDebit>> businessRulesBalanceByOperationDebit = new HashMap<String, List<BusinessRuleDebit>>();
		businessRulesBalanceByOperationDebit.put("INSERT", rulesDebitInsert);
		rules.put("DEBIT_AMOUNT", businessRulesBalanceByOperationDebit);

	}

	public ResultFacadeDebit executeRules(Debit debit, String operation) {
		ResultFacadeDebit result = new ResultFacadeDebit(debit);

		Map<String, List<BusinessRuleDebit>> classRules = rules.get("DEBIT_AMOUNT");

		if (classRules != null) {
			List<BusinessRuleDebit> operationRules = classRules.get(operation);

			if (operationRules != null) {
				for (BusinessRuleDebit rulesDebit : operationRules) {
					ResultFacadeDebit resultRulesDebit = rulesDebit.execute(result.getDebit());
					if (resultRulesDebit.getMessages() != null && resultRulesDebit.getMessages().size() != 0) {
						result.getMessages().addAll(resultRulesDebit.getMessages());
					}
					result.setDebit(resultRulesDebit.getDebit());
				}
			}
		}
		return result;
	}

}
