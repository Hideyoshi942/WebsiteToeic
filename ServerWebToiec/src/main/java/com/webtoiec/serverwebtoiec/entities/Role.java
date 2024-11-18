package com.webtoiec.serverwebtoiec.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
  ROLE_ADMIN(1), ROLE_MEMBER(2);

  private int value;

  Role(int value) {
    this.value = value;
  }

  @JsonValue
  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @JsonCreator
  public static Role findByAbbr(int item)
  {
    for (Role object : values())
    {
      if (object.value == item)
      {
        return object;
      }
    }
    return null;
  }
}
