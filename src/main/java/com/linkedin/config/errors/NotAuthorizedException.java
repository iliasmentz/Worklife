package com.linkedin.config.errors;


import com.linkedin.config.security.AuthenticationFacade;
import lombok.Data;

//we throw this Exception Whenever one User tries to make changes to another Users stuff (for example to update  a Job that he is not the Author)s
@Data
public class NotAuthorizedException extends Exception {

  private final Class targetClass;
  private final Object id;

  public NotAuthorizedException(Class targetClass) {
    this.targetClass = targetClass;
    this.id = AuthenticationFacade.getUserId();
  }

}
