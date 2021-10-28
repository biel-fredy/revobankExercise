package com.lugano.revobank.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.net.HttpHeaders;
import com.lugano.revobank.dto.AccountDTO;
import com.lugano.revobank.entities.Account;
import com.lugano.revobank.services.AccountService;
import com.lugano.revobank.services.results.ResultServiceAccount;

@RestController
@RequestMapping(value = "/accounts")
public class AccountResource {

	private AccountService service;

	@Autowired
	public AccountResource(AccountService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ResultServiceAccount> insertAccount(@RequestBody AccountDTO accountDto) {
		Account account = new Account(accountDto);

		ResultServiceAccount result = service.save(account);

		if (result.getSuccessOrFailed()) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(result.getResultAccountList().get(0).getId()).toUri();
			return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, uri.toString()).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
		}
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<ResultServiceAccount> updateAccount(@PathVariable Long id,
			@RequestBody AccountDTO accountDto) {
		Account account = new Account(accountDto);
		account.setId(id);

		ResultServiceAccount result = service.update(account);

		if (result.getSuccessOrFailed()) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}

	@PostMapping(value = "/{id}/block")
	public ResponseEntity<ResultServiceAccount> blockAccount(@PathVariable Long id) {
		ResultServiceAccount result = service.block(id);

		if (result.getSuccessOrFailed()) {
			return ResponseEntity.ok().body(result);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}
}
