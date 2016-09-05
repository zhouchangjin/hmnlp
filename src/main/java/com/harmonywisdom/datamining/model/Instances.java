package com.harmonywisdom.datamining.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Instances {
	
	Attributes attributes;
	HashMap<Integer,AttributeStats> statsMap;
	int classIndex;

	
	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	List<Instance> list;
	
	public Instances(){
		list=new ArrayList<Instance>();
		statsMap=new HashMap<Integer, AttributeStats>();
	}
	
	public int size(){
		return list.size();
	}
	
	
	/**
	 * Calculate statistic indicator
	 * @param stats
	 * @param value
	 */
	public static void CalculateDoubleStatus(DoubleAttributeStats stats,double value){
		
		stats.setSum(stats.getSum()+value);
		stats.setSquareSum(stats.getSquareSum()+value*value);
		stats.increase();
		double avg=stats.getSum()/(1.0*stats.getCount());
		stats.setAvg(avg);
		if(value>stats.getMax()){
			stats.setMax(value);
		}else if(value<stats.getMin()){
			stats.setMin(value);
		}
		long n=stats.getCount();
		if(n>1){
			double var=(stats.getSquareSum()-n*avg*avg)/(n-1);
			stats.setVariance(var);
			double std=Math.pow(var, 0.5);
			stats.setStdDev(std);
		}
		
	}
	
	public void add(Instance instance){
		instance.bindAttributes(attributes);
		
		if(instance instanceof SparseInstance){
			//int size=instance.size();
			int size=attributes.size();
			for(int i=0;i<size;i++){
				Attribute att=instance.attribute(i);
				
				if(att.getType().equals(AttributeType.Nominal)){
					String strValue=instance.strValue(i);
					if(statsMap.containsKey(att.getAttributeId())){
						AttributeStats attstats=statsMap.get(att.getAttributeId());
						NominalAttributeStats stats=attstats.getNominalAttributeStats();
						stats.increaseValue(strValue);
					}else{
						AttributeStats attstats=new AttributeStats();
						NominalAttributeStats stats=new NominalAttributeStats();
						attstats.setNominalAttributeStats(stats);
						stats.increaseValue(strValue);
						stats.setAttIndex(att.getAttributeId());
						statsMap.put(att.getAttributeId(), attstats);
					}
				}else if(att.getType().equals(AttributeType.Numeric)){
					double value=instance.value(i);
					if(statsMap.containsKey(att.getAttributeId())){
						AttributeStats attstats=statsMap.get(att.getAttributeId());
						DoubleAttributeStats stats=attstats.getDoubleAttStats();
						CalculateDoubleStatus(stats, value);
						
					}else{
						AttributeStats attstats=new AttributeStats();
						DoubleAttributeStats stats=new DoubleAttributeStats();
						stats.setAttIndex(att.getAttributeId());
						attstats.setDoubleAttStats(stats);
						CalculateDoubleStatus(stats, value);
						statsMap.put(att.getAttributeId(), attstats);
					}
				}
			}
		}else{
			
		}
		
		list.add(instance);
	}
	
	public Instance get(int index){
		return list.get(index);
	}
	
	public AttributeStats getAttributesStats(int index){
		return statsMap.get(index);
	}
	
	public String showAttributesStatistics(){
		String res="=========================================\n";
		Iterator<Integer> it=statsMap.keySet().iterator();
		while(it.hasNext()){
			int index=it.next();
			String attName=attributes.getAttribute(index).getAttributeName();
			res+=attName+"\n";
			res+=statsMap.get(index).toString();
		}
		return res;
	}

}
