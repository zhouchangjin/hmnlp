package com.harmonywisdom.datamining.model;

import java.util.List;

public class Attribute {
	
	int attributeId;
	String attributeName;
	AttributeType type;
	String[] values;
	
	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public AttributeType getType() {
		return type;
	}

	public void setType(AttributeType type) {
		this.type = type;
	}

	public Attribute(int attributeId,String attributeName,AttributeType type){
		this.attributeId=attributeId;
		this.attributeName=attributeName;
		this.type=type;
	}
	
	public Attribute(){
		
	}
	
	
	public static class AttributeBuilder{
		int attributeId;
		String attributeName;
		AttributeType type;
		public AttributeBuilder(String attributeName) {
			this.attributeName=attributeName;
		}
		
		public AttributeBuilder setType(AttributeType type){
			this.type=type;
			return this;
		}
		
		public AttributeBuilder setId(int attributeId){
			this.attributeId=attributeId;
			return this;
		}
		
		public Attribute build(){
		  Attribute attribute= new Attribute();
		  attribute.setAttributeId(attributeId);
		  attribute.setAttributeName(attributeName);
		  attribute.setType(type);
		  return attribute;
		}
	}

}
