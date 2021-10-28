package com.lugano.revobank.services.results;

import java.util.ArrayList;
import java.util.List;

import com.lugano.revobank.dto.BalanceDTO;
import com.lugano.revobank.entities.Balance;

public class ResultServiceBalance extends AbstractResultService {

	private List<BalanceDTO> resultBalanceList = new ArrayList<BalanceDTO>();

	public ResultServiceBalance() {
	}

	public ResultServiceBalance(Balance balance) {
		this.addBalanceDTO(balance);
	}

	public ResultServiceBalance(List<Balance> balances) {
		balances.forEach(balance -> this.addBalanceDTO(new BalanceDTO(balance)));
	}

	public ResultServiceBalance(Balance balance, List<String> messages) {
		this.addBalanceDTO(balance);
		this.AddMessages(messages);
	}

	public List<BalanceDTO> getResultBalanceList() {
		return resultBalanceList;
	}

	public void addBalanceDTO(BalanceDTO BalanceDTO) {
		this.resultBalanceList.add(BalanceDTO);
	}

	public void addBalanceDTO(Balance balance) {
		this.addBalanceDTO(new BalanceDTO(balance));
	}

}
