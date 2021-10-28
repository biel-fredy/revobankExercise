package com.lugano.revobank.entities.enums;

public enum AccountStatus {
	
	ACTIVE(1, "Active"),
	BLOCKED(2, "Blocked");	
	
	private Integer numericValue;
	private String description;
	
	private AccountStatus(Integer numericValue, String description) {
		this.numericValue = numericValue;
		this.description = description;
	}

	public Integer getNumericValue() {
		return numericValue;
	}

	public String getDescription() {
		return description;
	}
	
	public static AccountStatus valueToEnum(Integer numericValue) {
		if (numericValue == null) {
			return null;
		}
		for (AccountStatus status : AccountStatus.values()) {
			if (numericValue == status.getNumericValue()) {
				return status;
			}
		}		
		throw new IllegalArgumentException("Valor inválido: " + numericValue);
	}

}
