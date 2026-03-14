package com.harmonywisdom.datamining.neuralnetwork;

import java.util.ArrayList;
import java.util.List;


public class SimpleLayer extends HiddenLayer implements NeuronNetworkLayer{
	

	
	public SimpleLayer() {
		hiddenNeurons=new ArrayList<>();
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
			INeuron n=new SimpleNeuron();
			n.setWeights(weightsi);
			n.setBias(biasi);
			hiddenNeurons.add(n);
		}
		
	}

	

}
