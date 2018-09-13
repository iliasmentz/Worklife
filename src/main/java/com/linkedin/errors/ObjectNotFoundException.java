package com.linkedin.errors;


public class ObjectNotFoundException extends Exception {
	private Class targetClass;
	private Object id;
	
	public ObjectNotFoundException(Class targetClass, Object id) {
		this.targetClass = targetClass;
		this.id = id;
	}

	public ObjectNotFoundException(Class targetClass) {
		this.targetClass = targetClass;
		this.id = -1; //when id is not provided;
	}

	public Class getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class targetClass) {
		this.targetClass = targetClass;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}
}
