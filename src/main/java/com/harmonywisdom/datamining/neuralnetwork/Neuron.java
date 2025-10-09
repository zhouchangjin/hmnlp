package com.harmonywisdom.datamining.neuralnetwork;


import com.harmonywisdom.math.function.CommonFunction;

public class Neuron {
	

	
	double[] weights;
	
	double bias;
	
	double value;
	
	double loss;
	
	double dydPred;
	
	double[] inputMem;
	
	
	public Neuron() {
	}
	
	public void setWeights(double weights[]) {
		this.weights=weights;
	}
	
	public void setBias(double bias) {
		this.bias=bias;
	}
	
	
	public double forward(double x[]) {
		inputMem=x;
		double t= CommonFunction.dot(x, weights)+bias;
		double f=CommonFunction.sigmoid(t);
		value=f;
		return value;
	}
	
	public void calcLoss() {
		loss= (1-value)*(1-value);
	}
	
	public void dydPred() {
		dydPred=-2*(1-value);
	}
	
	public void calcf1x() {
	}
	
}
