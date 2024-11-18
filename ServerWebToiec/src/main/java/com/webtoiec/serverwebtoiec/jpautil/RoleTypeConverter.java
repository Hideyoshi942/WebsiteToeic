package com.webtoiec.serverwebtoiec.jpautil;

import com.webtoiec.serverwebtoiec.entities.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleTypeConverter implements AttributeConverter<Role, Integer> {

  @Override
  public Integer convertToDatabaseColumn(Role attribute) {
    return attribute.getValue();
  }

  @Override
  public Role convertToEntityAttribute(Integer dbData) {
    for (Role type : Role.values()) {
      if (type.getValue() == dbData) {
        return type;
      }
    }
    throw new IllegalArgumentException("Invalid Role value: " + dbData);
  }
}
