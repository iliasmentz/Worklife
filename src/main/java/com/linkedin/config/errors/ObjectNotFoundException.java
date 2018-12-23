package com.linkedin.config.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectNotFoundException extends Exception {
  private final Class targetClass;
  private final Object id;

  public ObjectNotFoundException(Class targetClass, Object id) {
    this.targetClass = targetClass;
    this.id = id;
  }

  public ObjectNotFoundException(Class targetClass) {
    this.targetClass = targetClass;
    this.id = -1; //when id is not provided;
  }
}
