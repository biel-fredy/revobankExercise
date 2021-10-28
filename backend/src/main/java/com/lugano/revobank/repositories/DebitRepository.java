package com.lugano.revobank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lugano.revobank.entities.Debit;

public interface DebitRepository extends JpaRepository<Debit, Long> {

}
