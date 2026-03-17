package com.harmonywisdom.datamining.neuralnetwork;

public interface INeuron {
	
	void setLayerNo(int num);
	
	void setNeuronNo(int num);
	
	int getInputSize();
	
	double[] backward(double backwardinput);
	
	void setWeights(double weights[]);
	
	double forward(double x[]);
	
	void update(int batchSize,double learnRate);
	
	double[] getNeuronBackwardInput();
	
	void setBias(double bias);
	

}
