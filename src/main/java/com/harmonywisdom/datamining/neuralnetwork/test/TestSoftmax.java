package com.harmonywisdom.datamining.neuralnetwork.test;

import com.harmonywisdom.datamining.neuralnetwork.HiddenLayer;
import com.harmonywisdom.datamining.neuralnetwork.NeuronNetwork;
import com.harmonywisdom.datamining.neuralnetwork.SimpleLayer;
import com.harmonywisdom.datamining.neuralnetwork.SoftmaxCrossEntropyOutputLayer;

public class TestSoftmax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NeuronNetwork ann=new NeuronNetwork();
		SimpleLayer simpleLayer=new SimpleLayer();
		simpleLayer.initLayerWithWeights(2, new double[][] {
			{-2.5,0.6},
			{-1.5,0.4}
		}, new double[] {
			1.6,0.7
		});

		SimpleLayer simpleLayer2=new SimpleLayer();
		simpleLayer2.initLayerWithWeights(3, new double[][] {
			{-0.1,1.5},
			{2.4,-5.2},
			{-2.2,3.7}
		}, new double[] {
				0,0,1
		});
		ann.addHiddenLayer(simpleLayer);
		ann.addHiddenLayer(simpleLayer2);
		double output[]=ann.triggerInput(new double[] {0.04,0.42});
		SoftmaxCrossEntropyOutputLayer outputL=new SoftmaxCrossEntropyOutputLayer();
		double p[]=outputL.forward(output);
		for(int i=0;i<p.length;i++) {
			System.out.println(p[i]+" "+(-1*Math.log(p[i])));
		}
	}

}
