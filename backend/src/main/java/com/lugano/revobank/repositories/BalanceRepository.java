package com.lugano.revobank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lugano.revobank.entities.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

}
