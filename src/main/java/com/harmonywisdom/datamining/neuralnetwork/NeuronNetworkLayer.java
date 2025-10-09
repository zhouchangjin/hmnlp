package com.harmonywisdom.datamining.neuralnetwork;

public interface NeuronNetworkLayer {

	double[] forward(double inputValues[]);
	
	void initSize(int neuroCount);
	
	void initLayerWithSameWeights(int neuroCount,double weight[],double bias);
	
	void setWeights(int neuronIndex,double weight[]);
	
	void setBias(int neuronIndex,double bias);
	
	Neuron getNeuron(int position);
	
}
