package com.lugano.revobank.entities.enums;

public enum Jobs {
	
	COUNTER(1, "Counter"),
	PROGRAMMER(2, "Programmer"),
	DIRECTOR(3, "Director");	
	
	private Integer numericValue;
	private String description;
	
	private Jobs(Integer numericValue, String description) {
		this.numericValue = numericValue;
		this.description = description;
	}

	public Integer getNumericValue() {
		return numericValue;
	}

	public String getDescription() {
		return description;
	}
	
	public static Jobs valueToEnum(Integer numericValue) {
		if (numericValue == null) {
			return null;
		}
		for (Jobs job : Jobs.values()) {
			if (numericValue == job.getNumericValue()) {
				return job;
			}
		}		
		throw new IllegalArgumentException("Valor inválido: " + numericValue);
	}

}
