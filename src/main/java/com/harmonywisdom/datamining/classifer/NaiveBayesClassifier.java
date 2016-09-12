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
import com.harmonywisdom.datamining.model.NominalAttributeStats;
import com.harmonywisdom.math.probability.distribution.NormalDistribution;

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

	@Override
	public ClassifyResult classifySingleInstance(Instance instance) {
		// TODO Auto-generated method stub
		ClassifyResult cr=new ClassifyResult();
		Attribute clzatt=trainInstance.getAttributes().getAttribute(trainClzIndex);
		String[] classvalues=clzatt.getValues();
		AttributeStats tAattstats=trainInstance.getAttributesStats(trainClzIndex);
		NominalAttributeStats noAttstats=tAattstats.getNominalAttributeStats();
		double probabilities[]=new double[classvalues.length];
		for(int i=0;i<probabilities.length;i++){
			probabilities[i]=1.0;
		}
		
		int count=instance.size();
		for(int i=0;i<count;i++){
			Attribute att=instance.sparseAttributeIndex(i);
			if(att.getAttributeId()==this.trainClzIndex){
				//System.out.println("ignore classIndex");
			}else{
				if(att.getType().equals(AttributeType.Nominal)){
					
				}else if(att.getType().equals(AttributeType.Numeric)){
					double max=0;
					double value=instance.sparseValue(i);
					for(int ci=0;ci<classvalues.length;ci++){
						String key=classvalues[ci]+"_"+att.getAttributeId();
						AttributeStats attstats=this.statsMap.get(key);
						double avg=attstats.getDoubleAttStats().getAvg();
						double std=attstats.getDoubleAttStats().getStdDev();
						double probability=NormalDistribution.probability(avg, std, value);
						double temp=Math.max(probability, 10e-75);
						probabilities[ci]*=temp;
						
					}
					for(int ci=0;ci<classvalues.length;ci++){
						if(probabilities[ci]>max){
							max=probabilities[ci];
						}
					}
					if(max>0 && max<10e-75){
						for(int ci=0;ci<classvalues.length;ci++){
							probabilities[ci]*=1/max;
						}
					}
				}
			}
		}
		for(int i=0;i<classvalues.length;i++){
			double percentage=noAttstats.getValuePercentage(classvalues[i]);
			cr.setResult(classvalues[i], probabilities[i]*percentage);
		}
		return cr;
	}

}
