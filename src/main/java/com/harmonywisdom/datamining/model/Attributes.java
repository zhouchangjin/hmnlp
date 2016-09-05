package com.harmonywisdom.datamining.model;

import java.util.HashMap;
import java.util.HashSet;

public class Attributes {
	
	HashSet<String> nameSet;
	HashMap<Integer, Attribute> indexMap;
	HashMap<String,Integer> invertedMap;
	
	public Attributes(){
		indexMap=new HashMap<Integer, Attribute>();
		nameSet=new HashSet<String>();
		invertedMap=new HashMap<String, Integer>();
	}
	
	public int size(){
		return nameSet.size();
	}

	
	boolean hasAttribute(String name){
		return nameSet.contains(name);
	}
	int generateId(){
		return nameSet.size();
	}
	public void addAttribute(String name,AttributeType type,String... values){
		if(!hasAttribute(name)){
			int id=generateId();
			Attribute att=new Attribute.AttributeBuilder(name).setId(id).setType(type).build();
			indexMap.put(id, att);
			invertedMap.put(name, id);
			nameSet.add(name);
		}else{
			return;
		}
	}
	
	
	public Attribute getAttribute(int index){
		return indexMap.get(index);
	}
	
	
	
	
	

}
