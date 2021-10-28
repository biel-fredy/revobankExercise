package com.lugano.revobank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lugano.revobank.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByDocument(String document);

}
