package com.harmonywisdom.datamining.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.harmonywisdom.datamining.classifer.ClassifyResult;
import com.harmonywisdom.datamining.classifer.NaiveBayesClassifier;
import com.harmonywisdom.datamining.datasource.ArffReader;
import com.harmonywisdom.datamining.model.Instance;
import com.harmonywisdom.datamining.model.Instances;

public class ArffReaderExample {

	public static void main(String[] args) {
		
		 ArrayList<String> title=new ArrayList<String>();
		  ArrayList<String> flag=new ArrayList<String>();
		  File f=new File("d:/nlphome/test/dataset_test_modified_0913.txt");
		  try {
			BufferedReader br=new BufferedReader(new FileReader(f));
			
			String line=null;
	    	while((line=br.readLine())!=null){
	    		String pair[]=line.split(" && ");
	    		title.add(pair[1]);
	    		flag.add(pair[0]);
	    	}
	    	br.close();
		  }catch(Exception e){
			  
		  }
		
		// TODO Auto-generated method stub
		ArffReader reader=new ArffReader();
		reader.setPath("d:/nlphome/trainning/training_0914.arff");
		ArffReader reader2=new ArffReader();
		reader2.setPath("d:/nlphome/test/test_0914.arff");
		Instances instances=reader.loadInstance();
		instances.setClassIndex(1);
		
		Instances testinstances=reader2.loadInstance();
		
		//System.out.println(instances.showAttributesStatistics());
		NaiveBayesClassifier classifier=new NaiveBayesClassifier();
		classifier.build(instances);
		//System.out.println(classifier.outputModel());
		int pos=0;
		int negative=0;
		int known=0;
		int tp=0;
		int fp=0;
		int tn=0;
		int fn=0;
		for(int i=0;i<testinstances.size();i++){
			Instance instance=testinstances.get(i);
			ClassifyResult cr=classifier.classifySingleInstance(instance);
			if(flag.get(i).equals("1")){
				pos++;
				if(cr.toString().startsWith("T")){
					
					tp++;
					
				}else if(cr.toString().startsWith("F")){
					//System.out.println(i+"_"+cr.toString()+"_"+title.get(i)+"_"+flag.get(i));
					fp++;
				}else{
					fp++;
					//System.out.println(title.get(i));
				}
			}else{
				negative++;
				if(cr.toString().startsWith("T")){
					fn++;
					System.out.println(cr.toString()+"_"+title.get(i)+"_"+flag.get(i));
				}else if(cr.toString().startsWith("F")){
					tn++;
				}else{
					//System.out.println(title.get(i));
					fn++;
				}
			}
			
		}
		System.out.println("总相关新闻数"+pos);
		System.out.println("相关召回率"+tp*1.0/pos);
		System.out.println("不相关召回率"+tn*1.0/negative);
	}

}
