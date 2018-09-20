package com.linkedin.errors;


import lombok.Data;

//we throw this Exception Whenever one User tries to make changes to another Users stuff (for example to update  a Job that he is not the Author)s
@Data
public class NotAuthorizedException extends Exception {

  private Class targetClass;
  private Object id;

  public NotAuthorizedException(Class targetClass) {
	this.targetClass = targetClass;
	this.id = id;
  }


}
