package com.lugano.revobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lugano.revobank.utils.AccountValues;

@SpringBootApplication
public class RevobankApplication {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AccountValues accountValues = AccountValues.getInstance();
		SpringApplication.run(RevobankApplication.class, args);
	}

}
