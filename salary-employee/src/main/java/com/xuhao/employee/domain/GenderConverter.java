package com.xuhao.salary.domain.model.employee;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Character> {
    
    @Override
    public Character convertToDatabaseColumn(Gender attribute) {
        if (attribute == null) return null;
        return attribute.name().charAt(0);
    }

    @Override
    public Gender convertToEntityAttribute(Character dbData) {
        if (dbData == null) return null;
        return Gender.fromChar(dbData);
    }
}
