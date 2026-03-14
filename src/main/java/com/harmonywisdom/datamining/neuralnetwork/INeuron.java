package com.harmonywisdom.datamining.neuralnetwork;

public interface INeuron {
	
	int getInputSize();
	
	double[] backward(double backwardinput);
	
	void setWeights(double weights[]);
	
	double forward(double x[]);
	
	void update(int batchSize,double learnRate);
	
	double[] getNeuronBackwardInput();
	
	void setBias(double bias);

}
