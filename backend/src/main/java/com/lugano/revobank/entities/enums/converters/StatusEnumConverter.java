package com.lugano.revobank.entities.enums.converters;

import javax.persistence.AttributeConverter;

import com.lugano.revobank.entities.enums.AccountStatus;

public class StatusEnumConverter implements AttributeConverter<AccountStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(AccountStatus attribute) {
		return attribute.getNumericValue();
	}

	@Override
	public AccountStatus convertToEntityAttribute(Integer dbData) {
		return AccountStatus.valueToEnum(dbData);
	}

}