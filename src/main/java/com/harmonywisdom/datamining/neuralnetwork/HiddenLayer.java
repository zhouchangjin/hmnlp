package com.harmonywisdom.datamining.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer implements NeuronNetworkLayer{
	
	List<Neuron> hiddenNeurons;
	
	public HiddenLayer() {
		hiddenNeurons=new ArrayList<>();
	}

	@Override
	public double[] forward(double[] inputValues) {
		// TODO Auto-generated method stub
		double[] out=new double[hiddenNeurons.size()];
		for(int i=0;i<hiddenNeurons.size();i++) {
			Neuron n=hiddenNeurons.get(i);
			double vi=n.forward(inputValues);
			out[i]=vi;
		}
		return out;
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
		Neuron n=hiddenNeurons.get(neuronIndex);
		n.setWeights(weights);	
	}

	@Override
	public void setBias(int neuronIndex, double bias) {
		// TODO Auto-generated method stub
		Neuron n=hiddenNeurons.get(neuronIndex);
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
	public Neuron getNeuron(int position) {
		// TODO Auto-generated method stub
		return hiddenNeurons.get(position);
	}
	


}
