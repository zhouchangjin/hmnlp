package com.harmonywisdom.datamining.model;

public enum AttributeType {

	Nominal("Nominal",1),Binary("Binary",2),Ordinal("Ordinal",3),Numeric("Numeric",4),Ratio("Ratio",5),String("String",6),Date("Date",7);
	
	String name;
	int value;
	
	AttributeType(String name,int value){
		this.name=name;
		this.value=value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
