package com.harmonywisdom.datamining.neuralnetwork;

public interface ActivationFunction {
	
	double activate(double input);
	
	double derivation();

}
