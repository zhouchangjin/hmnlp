package com.harmonywisdom.datamining.neuralnetwork.test;

import com.harmonywisdom.datamining.neuralnetwork.NeuronNetwork;
import com.harmonywisdom.datamining.neuralnetwork.SimpleLayer;
import com.harmonywisdom.datamining.neuralnetwork.SoftmaxCrossEntropyOutputLayer;

public class TestSoftmax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NeuronNetwork ann=new NeuronNetwork();
		SimpleLayer simpleLayer=new SimpleLayer(0);
		simpleLayer.initLayerWithWeights(2, new double[][] {
			{-2.5,0.6},
			{-1.5,0.4}
		}, new double[] {
			1.6,0.7
		});

		SimpleLayer simpleLayer2=new SimpleLayer(1);
		simpleLayer2.initLayerWithWeights(3, new double[][] {
			{-0.1,1.5},
			{2.4,-5.2},
			{-2.2,3.7}
		}, new double[] {
				-2,0,1
		});
		ann.addHiddenLayer(simpleLayer);
		ann.addHiddenLayer(simpleLayer2);
		double output[]=ann.triggerInput(new double[] {0.04,0.42});
		SoftmaxCrossEntropyOutputLayer outputL=new SoftmaxCrossEntropyOutputLayer();
		ann.setOutputLayer(outputL);
		
		double p[]=outputL.forward(output);
//		for(int i=0;i<p.length;i++) {
//			System.out.println(p[i]+" "+(-1*Math.log(p[i])));
//		}
//		
//		System.out.println("==========");
		ann.triggerBackward(0);
		double output2[]=ann.triggerInput(new double[] {1,0.54});
		double p2[]=outputL.forward(output2);
//		for(int i=0;i<p2.length;i++) {
//			System.out.println(p2[i]);
//		}
//		System.out.println("============");
		ann.triggerBackward(2);
		double output3[]=ann.triggerInput(new double[] {0.5,0.37});
		double p3[]=outputL.forward(output3);
//		for(int i=0;i<p3.length;i++) {
//			System.out.println(p3[i]);
//		}
//		System.out.println("============");
		ann.triggerBackward(1);
		
		//System.out.println("输出日志");
		//ann.getLastLayer().logPrintSumBackwardInput();
	}

}
