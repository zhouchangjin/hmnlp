package com.harmonywisdom.datamining.example;

import com.harmonywisdom.datamining.classifer.NaiveBayesClassifier;
import com.harmonywisdom.datamining.datasource.ArffReader;
import com.harmonywisdom.datamining.model.Instances;

public class ArffReaderExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArffReader reader=new ArffReader();
		reader.setPath("d:/nlphome/trainning/training_0902.arff");
		Instances instances=reader.loadInstance();
		instances.setClassIndex(1);
		//System.out.println(instances.showAttributesStatistics());
		NaiveBayesClassifier classifier=new NaiveBayesClassifier();
		classifier.build(instances);
		System.out.println(classifier.outputModel());
	}

}
