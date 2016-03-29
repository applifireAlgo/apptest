package com.app.server.service;
public class EntityTestCriteria {

	public static final int UNIQUE = 0;
	public static final int NOT_NULL = 1;
	public static final int MIN_MAX = 2;
	public static final int REGEX = 3;

	private Integer ruleType;

	private Object negativeValue;
	private Integer testId;
	private String fieldName;

	
	public EntityTestCriteria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityTestCriteria(Integer ruleType, Integer testId, String fiedlName, Object negativeValue) {
		super();
		this.ruleType = ruleType;
		;
		this.fieldName = fiedlName;
		this.testId = testId;
		this.negativeValue = negativeValue;
	}

	public Integer getRuleType() {
		return ruleType;
	}

	public Object getNegativeValue() {
		return negativeValue;
	}

	public void setNegativeValue(Object negativeValue) {
		this.negativeValue = negativeValue;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}



