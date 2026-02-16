package com.harmonywisdom.datamining.neuralnetwork;


public class DoNothingActivationFunction implements ActivationFunction{
	
	double output;

	@Override
	public double activate(double input) {
		// TODO Auto-generated method stub
		output= input;
		return output;
	}

	@Override
	public double derivation() {
		// TODO Auto-generated method stub
		return 1;
	}

}
