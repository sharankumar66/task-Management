package com.project.taskManagement.exception;

public class SourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	Integer fieldValue;

	public SourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s not found with %s:%s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
