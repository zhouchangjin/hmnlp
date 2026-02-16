package com.harmonywisdom.datamining.neuralnetwork;

public class SimpleNeuron extends Neuron{
	
	public SimpleNeuron() {
		activationFunction=new DoNothingActivationFunction();
	}

}
