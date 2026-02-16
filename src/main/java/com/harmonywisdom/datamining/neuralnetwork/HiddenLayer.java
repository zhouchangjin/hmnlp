package com.harmonywisdom.datamining.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer implements NeuronNetworkLayer{
	
	List<INeuron> hiddenNeurons;
	
	double activateResult[];
	
	int layerSeq;
	
	
	public HiddenLayer() {
		hiddenNeurons=new ArrayList<>();
	}

	@Override
	public double[] forward(double[] inputValues) {
		// TODO Auto-generated method stub
		double[] out=new double[hiddenNeurons.size()];
		for(int i=0;i<hiddenNeurons.size();i++) {
			INeuron n=hiddenNeurons.get(i);
			double vi=n.forward(inputValues);
			out[i]=vi;
		}
		activateResult=out;
		return activateResult;
	}

	@Override
	public void initSize(int neuroCount) {
		for(int i=0;i<neuroCount;i++) {
			Neuron n=new Neuron();
			hiddenNeurons.add(n);
		}
	}

	@Override
	public void setWeights(int neuronIndex, double[] weights) {
		INeuron n=hiddenNeurons.get(neuronIndex);
		n.setWeights(weights);	
	}

	@Override
	public void setBias(int neuronIndex, double bias) {
		// TODO Auto-generated method stub
		INeuron n=hiddenNeurons.get(neuronIndex);
		n.setBias(bias);
	}

	@Override
	public void initLayerWithSameWeights(int neuroCount, double[] weights, double bias) {
		
		for(int i=0;i<neuroCount;i++) {
			Neuron n=new Neuron();
			n.setWeights(weights);
			n.setBias(bias);
			hiddenNeurons.add(n);
		}
	}

	@Override
	public INeuron getNeuron(int position) {
		// TODO Auto-generated method stub
		return hiddenNeurons.get(position);
	}

	@Override
	public int getNeuronCnt() {
		// TODO Auto-generated method stub
		return hiddenNeurons.size();
	}

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
	public void initLayerWithWeights(int nCnt, double[][] weights, double[] bias) {
		
		int size=weights.length;
		if(size!=nCnt) {
			System.out.println("入参数量错误");
		}
		for(int i=0;i<nCnt;i++) {
			double weightsi[]=weights[i];
			double biasi=bias[i];
			INeuron n=new Neuron();
			n.setWeights(weightsi);
			n.setBias(biasi);
			hiddenNeurons.add(n);
		}
		
		
	}

	@Override
	public void initSimpleLayerWithWeights(int nCnt, double[][] weights, double[] bias) {
		int size=weights.length;
		if(size!=nCnt) {
			System.out.println("入参数量错误");
		}
		for(int i=0;i<nCnt;i++) {
			double weightsi[]=weights[i];
			double biasi=bias[i];
			INeuron n=new SimpleNeuron();
			n.setWeights(weightsi);
			n.setBias(biasi);
			hiddenNeurons.add(n);
		}
		
	}
	


}
