package com.lugano.revobank.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lugano.revobank.dto.DebitDTO;
import com.lugano.revobank.entities.Debit;
import com.lugano.revobank.services.BalanceService;
import com.lugano.revobank.services.results.ResultServiceBalance;

@RestController
@RequestMapping(value = "/balances")
public class BalanceResource {

	private BalanceService service;

	@Autowired
	public BalanceResource(BalanceService service) {
		super();
		this.service = service;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResultServiceBalance> getBalanceAmount(@PathVariable Long id) {
		ResultServiceBalance result = service.getBalanceAmount(id);
		if (result.getSuccessOrFailed()) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}
	}

	@PostMapping(value = "/debit")
	public ResponseEntity<ResultServiceBalance> debitAmount(@RequestBody DebitDTO debitDTO) {
		Debit debit = new Debit(debitDTO);
		ResultServiceBalance result = service.debitAmount(debit);
		if (result.getSuccessOrFailed()) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}

}
