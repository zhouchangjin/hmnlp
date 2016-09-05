package com.harmonywisdom.datamining.model;

import java.util.HashMap;
import java.util.Iterator;

public class NominalAttributeStats {
	
	int attIndex=-1;
	int totalCnt;
	HashMap<String,Integer> nominalCnt;
	public int getAttIndex() {
		return attIndex;
	}

	public void setAttIndex(int attIndex) {
		this.attIndex = attIndex;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	
	
	public NominalAttributeStats() {
		// TODO Auto-generated constructor stub
		nominalCnt=new HashMap<String, Integer>();
	}
	
	public void increaseValue(String name){
		if(nominalCnt.containsKey(name)){
			int cnt=nominalCnt.get(name)+1;
			nominalCnt.put(name, cnt);
		}else{
			nominalCnt.put(name, 1);
		}
		totalCnt++;
	}
	
	public int getValueCnt(String valueName){
		if(nominalCnt.containsKey(valueName)){
			return nominalCnt.get(valueName);
		}else{
			return 0;
		}
	}
	
	public double getValuePercentage(String valueName){
		return getValueCnt(valueName)*1.0/totalCnt;
	}
	
	public String toString(){
		String result="";
		if(attIndex==-1){
			return "";
		}
		Iterator<String> keysetIt=nominalCnt.keySet().iterator();
		while(keysetIt.hasNext()){
			String str=keysetIt.next();
			int cnt= nominalCnt.get(str);
			result+=str+" count "+cnt+" percentage "+cnt*1.0/totalCnt;
			result+="\n";
		}
		return result;
	}

}
