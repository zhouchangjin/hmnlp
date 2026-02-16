package com.harmonywisdom.datamining.neuralnetwork;

import com.harmonywisdom.math.function.CommonFunction;

public class SoftmaxLayer implements NeuronNetworkLayer{
	
	double output[];
	
	int layerSeq;

	@Override
	public void setLayerSeq(int layerSeq) {
		// TODO Auto-generated method stub
		this.layerSeq=layerSeq;
	}

	@Override
	public int getLayerSeq() {
		// TODO Auto-generated method stub
		return layerSeq;
	}

	@Override
	public double[] forward(double[] inputValues) {
		 output=CommonFunction.softmax(inputValues);
		 return output;
	}

	@Override
	public void initSize(int neuroCount) {
		
		
	}

	@Override
	public void initLayerWithSameWeights(int neuroCount, double[] weight, double bias) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initLayerWithWeights(int nCnt, double[][] weights, double[] bias) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initSimpleLayerWithWeights(int nCnt, double[][] weights, double[] bias) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWeights(int neuronIndex, double[] weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBias(int neuronIndex, double bias) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public INeuron getNeuron(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNeuronCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

}
