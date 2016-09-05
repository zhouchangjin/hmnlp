package com.harmonywisdom.datamining.model;

public enum AttributeType {

	Nominal("名义型",1),Binary("标称型",2),Ordinal("序数型",3),Numeric("数值型",4),Ratio("比例型",5),String("字符",6),Date("日期",7);
	
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
