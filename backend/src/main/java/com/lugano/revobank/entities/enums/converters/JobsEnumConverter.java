package com.lugano.revobank.entities.enums.converters;

import javax.persistence.AttributeConverter;

import com.lugano.revobank.entities.enums.Jobs;

public class JobsEnumConverter implements AttributeConverter<Jobs, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Jobs attribute) {
		return attribute.getNumericValue();
	}

	@Override
	public Jobs convertToEntityAttribute(Integer dbData) {
		return Jobs.valueToEnum(dbData);
	}

}