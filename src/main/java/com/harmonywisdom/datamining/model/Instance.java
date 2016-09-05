package com.harmonywisdom.datamining.model;

import java.util.Iterator;

public interface Instance {
	
	public Attribute attribute(int index);
 	public double value(int attIndex);
 	public String strValue(int attIndex);
 	public void setValue(Attribute att, double value);
 	public void setValue(Attribute att, String strVal);
 	public void bindAttributes(Attributes attributes);
 	public int size();
 	public double sparseValue(int indexOfSparse);
 	public String sparseStrValue(int indexOfSparse);
 	public Attribute sparseAttributeIndex(int indexOfSparse);


}
