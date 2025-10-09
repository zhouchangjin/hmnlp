package com.harmonywisdom.datamining.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuronNetwork {
	
	
	List<NeuronNetworkLayer> hiddenLayers;
	
	double[] currentOutput;
	
	public NeuronNetwork(){
		hiddenLayers=new ArrayList<>();
	}
	
	public void addHiddenLayer(NeuronNetworkLayer networkLayer) {
		hiddenLayers.add(networkLayer);
	}
	
	public void addHiddenLayer(int neuronCount) {
		
		HiddenLayer hiddenLayer=new HiddenLayer();
		hiddenLayer.initSize(neuronCount);
		hiddenLayers.add(hiddenLayer);
	}
	
	public void train(List<double[]> datalist,List<Integer> trueValues) {
		
		for(double dataRow[]:datalist) {
			triggerInput(dataRow);
		}
		
	}
	
	public void triggerInput(double input[]) {
		
		double currentInput[]=input;
		for(NeuronNetworkLayer hiddenLayer:hiddenLayers) {
			currentInput=hiddenLayer.forward(currentInput);
			
		}
		currentOutput=currentInput;
		/**
		NeuronNetworkLayer n=hiddenLayers.get(hiddenLayers.size()-1);
		Neuron ne=n.getNeuron(0);
		double los=ne.calcLoss();
		double dev=ne.dydPred();
		System.out.println("本轮误差"+los+" "+dev);**/
	}
	
	public static void main(String args[]) {
		NeuronNetwork ann=new NeuronNetwork();
		HiddenLayer hiddenLayer=new HiddenLayer();
		hiddenLayer.initLayerWithSameWeights(2, new double[] {0,1}, 0);
		
		HiddenLayer output=new HiddenLayer();
	    output.initLayerWithSameWeights(1,  new double[] {0,1}, 0);
		
	    
		ann.addHiddenLayer(hiddenLayer);
		ann.addHiddenLayer(output);
		
		ann.triggerInput(new double[] {2,3});
		
	}

}
