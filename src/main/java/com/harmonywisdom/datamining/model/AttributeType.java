package com.harmonywisdom.datamining.model;

public enum AttributeType {

	Nominal("������",1),Binary("�����",2),Ordinal("������",3),Numeric("��ֵ��",4),Ratio("������",5),String("�ַ�",6),Date("����",7);
	
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
