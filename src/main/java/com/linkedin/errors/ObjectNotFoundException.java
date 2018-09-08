package com.linkedin.errors;


public class ObjectNotFoundException extends Exception {
	private Class targetClass;
	private Object id;
	
	public ObjectNotFoundException(Class targetClass, Object id) {
		this.targetClass = targetClass;
		this.id = id;
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
