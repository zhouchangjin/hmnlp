package com.harmonywisdom.datamining.neuralnetwork;

public interface NeuronNetworkLayer {
	
	void setPreviousLayer(NeuronNetworkLayer layer);
	
	NeuronNetworkLayer getPreviousLayer();
	
	void setLayerSeq(int layerSeq);
	
	int getLayerSeq();

	double[] forward(double inputValues[]);
	
    void backward(NeuronNetworkLayer backlayer);
	
	void initSize(int neuroCount);
	
	void initLayerWithSameWeights(int neuroCount,double weight[],double bias);
	
	void initLayerWithWeights(int nCnt,double[][] weights,double[] bias);
		
	void setWeights(int neuronIndex,double weight[]);
	
	void setBias(int neuronIndex,double bias);
	
	INeuron getNeuron(int position);
	
	double[] getSumPartialDerivation();
	
	int getNeuronCnt();
	
	int getPLayerNodesCnt();
	
	//void logPrintSumBackwardInput();
	
	void addNeuron(INeuron neuron);
	
}
