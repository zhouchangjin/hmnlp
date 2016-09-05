package com.harmonywisdom.datamining.classifer;

import java.util.HashMap;
import java.util.Iterator;

import com.harmonywisdom.datamining.model.Attribute;
import com.harmonywisdom.datamining.model.AttributeStats;
import com.harmonywisdom.datamining.model.AttributeType;
import com.harmonywisdom.datamining.model.Attributes;
import com.harmonywisdom.datamining.model.DoubleAttributeStats;
import com.harmonywisdom.datamining.model.Instance;
import com.harmonywisdom.datamining.model.Instances;

public class NaiveBayesClassifier implements Classifier{
	
	int trainClzIndex;
	HashMap<String,AttributeStats> statsMap;
	Instances trainInstance;

	@Override
	public void build(Instances instances) {
		// TODO Auto-generated method stub
		this.trainInstance=instances;
		statsMap=new HashMap<String, AttributeStats>();
		trainClzIndex=instances.getClassIndex();
		int size=instances.size();
		for(int i=0;i<size;i++){
			Instance instance=instances.get(i);
			String value=instance.strValue(trainClzIndex);
			Attributes attributes=instances.getAttributes();
			int attsize=attributes.size();
			for(int j=0;j<attsize;j++){
				
				Attribute att=instance.attribute(j);
				String key=value+"_"+att.getAttributeId();
				if(att.getType().equals(AttributeType.Nominal)){
					
				}else if(att.getType().equals(AttributeType.Numeric)){
					double entryValue=instance.value(j);
					if(statsMap.containsKey(key)){
						AttributeStats attstats=statsMap.get(key);
						DoubleAttributeStats stats=attstats.getDoubleAttStats();
						Instances.CalculateDoubleStatus(stats, entryValue);
					}else{
						AttributeStats attstats=new AttributeStats();
						DoubleAttributeStats stats=new DoubleAttributeStats();
						stats.setAttIndex(att.getAttributeId());
						attstats.setDoubleAttStats(stats);
						Instances.CalculateDoubleStatus(stats, entryValue);
						statsMap.put(key, attstats);
					}
					
				}
			}
		}
	}
	
	public String outputModel(){
		String res="=========================================\n";
		Iterator<String> it=statsMap.keySet().iterator();
		while(it.hasNext()){
			String index=it.next();
			String pair[]=index.split("_");
			String className=pair[0];
			int id=Integer.parseInt(pair[1]);
			String attName=this.trainInstance.getAttributes().getAttribute(id).getAttributeName();
			res+=attName+"_"+className+"\n";
			res+=statsMap.get(index).toString();
		}
		return res;
		
	}

}
