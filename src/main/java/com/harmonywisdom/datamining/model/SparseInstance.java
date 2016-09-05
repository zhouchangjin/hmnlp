package com.harmonywisdom.datamining.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SparseInstance implements Instance{
	
	Attributes attributes;
	List<Integer> sparseIndex;
	HashMap<Integer,Double> doubleValues;
	HashMap<Integer,String> stringValues;

	
	public Attributes getAttributes() {
		return attributes;
	}



	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public void bindAttributes(Attributes attributes){
		this.attributes=attributes;
	}



	public SparseInstance() {
		doubleValues=new HashMap<Integer, Double>();
		stringValues=new HashMap<Integer, String>();
		sparseIndex=new ArrayList<Integer>();
		attributes=new Attributes();
	}
	
	

	@Override
	public Attribute attribute(int index) {
		
		return attributes.getAttribute(index);
	}

	@Override
	public double value(int attIndex) {
		// TODO Auto-generated method stub
		if(doubleValues.containsKey(attIndex)){
			return doubleValues.get(attIndex);
		}else{
			return 0;
		}
		
	}

	@Override
	public void setValue(Attribute att, double value) {
		// TODO Auto-generated method stub
		sparseIndex.add(att.getAttributeId());
		doubleValues.put(att.getAttributeId(), value);
	}



	@Override
	public void setValue(Attribute att, String strVal) {
		// TODO Auto-generated method stub
		sparseIndex.add(att.getAttributeId());
		stringValues.put(att.getAttributeId(), strVal);
	}
	
	



	@Override
	public String strValue(int attIndex) {
		// TODO Auto-generated method stub
		if(stringValues.containsKey(attIndex)){
			return stringValues.get(attIndex);
		}else{
			return attributes.getAttribute(attIndex).getValues()[0];
		}
		
	}



	@Override
	public int size() {
		// TODO Auto-generated method stub
		return sparseIndex.size();
	}



	@Override
	public double sparseValue(int indexOfSparse) {
		// TODO Auto-generated method stub
		
		return value(sparseIndex.get(indexOfSparse));
	}



	@Override
	public String sparseStrValue(int indexOfSparse) {
		// TODO Auto-generated method stub
		return strValue(sparseIndex.get(indexOfSparse));
	}



	@Override
	public Attribute sparseAttributeIndex(int indexOfSparse) {
		// TODO Auto-generated method stub
		return attribute(sparseIndex.get(indexOfSparse));
	}




}
