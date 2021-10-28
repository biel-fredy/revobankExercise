package com.lugano.revobank.dto.enums;

public class EnumDTO {

	private String numericValue;
	private String description;

	public EnumDTO() {
	}

	public EnumDTO(String numericValue, String description) {
		super();
		this.numericValue = numericValue;
		this.description = description;
	}

	public EnumDTO(String numericValue) {
		this.numericValue = numericValue;
	}

	public String getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(String numericValue) {
		this.numericValue = numericValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
