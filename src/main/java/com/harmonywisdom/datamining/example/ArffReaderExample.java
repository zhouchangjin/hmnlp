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
		  File f=new File("d:/nlphome/trainning/dataset_test_0831_2.txt");
		  try {
			BufferedReader br=new BufferedReader(new FileReader(f));
			
			String line=null;
	    	while((line=br.readLine())!=null){
	    		String pair[]=line.split(" && ");
	    		title.add(pair[1]);
	    		flag.add(pair[0]);
	    	}
		  }catch(Exception e){
			  
		  }
		
		// TODO Auto-generated method stub
		ArffReader reader=new ArffReader();
		reader.setPath("d:/nlphome/trainning/training_0902.arff");
		ArffReader reader2=new ArffReader();
		reader2.setPath("d:/nlphome/trainning/test_0903_test3.arff");
		Instances instances=reader.loadInstance();
		instances.setClassIndex(1);
		
		Instances testinstances=reader2.loadInstance();
		
		//System.out.println(instances.showAttributesStatistics());
		NaiveBayesClassifier classifier=new NaiveBayesClassifier();
		classifier.build(instances);
		System.out.println(classifier.outputModel());
		int cnt=0;
		int cnt2=0;
		for(int i=0;i<testinstances.size();i++){
			Instance instance=testinstances.get(i);
			ClassifyResult cr=classifier.classifySingleInstance(instance);
			if(cr.toString().startsWith("T")){
				System.out.println(cr.toString()+"_"+title.get(i)+"_"+flag.get(i));
				cnt2++;
			}else	if(cr.toString().startsWith("F")){
				cnt++;
			}else{
				System.out.println(cr.toString());
			}
		}
		System.out.println(cnt+"_"+cnt2);
		System.out.println(testinstances.size());
		double result=cnt*1.0/testinstances.size();
		System.out.println(result);
	}

}
