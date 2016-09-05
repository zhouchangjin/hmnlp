package com.harmonywisdom.datamining.classifer;

import java.util.HashMap;

public class ClassifyResult {
	HashMap<String,Double> classifyResult;
	String className;
	double max=0;
	
	public ClassifyResult(){
		classifyResult=new HashMap<String, Double>();
	}
	
	public void setResult(String className,double value){
		if(value>max){
			max=value;
			this.className=className;
		}
		classifyResult.put(className, value);
	}
	
	public double getResult(String className){
		return classifyResult.get(className);
	}
	
	public String getClassName(){
		return className;
	}
	
	public String toString(){
		String res=className+"_"+classifyResult.get(className);
		return res;
	}

}
