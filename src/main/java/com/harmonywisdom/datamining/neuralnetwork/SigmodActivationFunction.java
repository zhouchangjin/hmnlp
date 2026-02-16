package com.harmonywisdom.datamining.neuralnetwork;

import com.harmonywisdom.math.function.CommonFunction;

public class SigmodActivationFunction implements ActivationFunction{
		
	double output;


	@Override
	public double activate(double input) {
		output= CommonFunction.sigmoid(input);
		return output;
	}

	@Override
	public double derivation() {
		
		return output*(1-output);
	}

}
