package com.harmonywisdom.datamining.model;

public class AttributeStats {
	
	DoubleAttributeStats doubleAttStats;
	NominalAttributeStats nominalAttributeStats;
	
	public AttributeStats(){
		doubleAttStats=new DoubleAttributeStats();
		nominalAttributeStats=new NominalAttributeStats();
	}
	public DoubleAttributeStats getDoubleAttStats() {
		return doubleAttStats;
	}
	public void setDoubleAttStats(DoubleAttributeStats doubleAttStats) {
		this.doubleAttStats = doubleAttStats;
	}
	public NominalAttributeStats getNominalAttributeStats() {
		return nominalAttributeStats;
	}
	public void setNominalAttributeStats(NominalAttributeStats nominalAttributeStats) {
		this.nominalAttributeStats = nominalAttributeStats;
	}
	
	public String toString(){
		return doubleAttStats.toString()+"\n"+nominalAttributeStats.toString();
	}

}
