package com.javarush.shosafoev.convertes;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;

import static java.util.Objects.nonNull;

@Converter
public class YearAttributeConvertor implements AttributeConverter<Year, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Year attribute) {
        if (nonNull(attribute))
            return attribute.getValue();
        return null;
    }
    
    @Override
    public Year convertToEntityAttribute(Integer dbData) {
        if (nonNull(dbData))
            return Year.of(dbData);
        return null;
    }
}