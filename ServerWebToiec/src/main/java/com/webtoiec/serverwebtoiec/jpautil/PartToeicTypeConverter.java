package com.webtoiec.serverwebtoiec.jpautil;

import com.webtoiec.serverwebtoiec.entities.PartToeic;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PartToeicTypeConverter implements AttributeConverter<PartToeic, Integer> {

  @Override
  public Integer convertToDatabaseColumn(PartToeic attribute) {
    return attribute.getValue();
  }

  @Override
  public PartToeic convertToEntityAttribute(Integer dbData) {
    for (PartToeic type : PartToeic.values()) {
      if (type.getValue() == dbData) {
        return type;
      }
    }
    throw new IllegalArgumentException("Invalid PartToeic value: " + dbData);
  }
}
