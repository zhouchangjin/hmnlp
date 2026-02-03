package com.harmonywisdom.datamining.neuralnetwork;


import com.harmonywisdom.math.function.CommonFunction;

public class Neuron {
	
	double[] weights;
	
	double[] dweights;
	
	double[] sumDWeights;
	
	double bias;
	
	double dbias;
	
	double sumDBias;
	

	//同一层的sigmod偏导的一部分
	double dz;
	
	//sigmod后的值
	double value;
	
	double[] inputMem;
	
	double[] backwardValue;

	
	public Neuron() {
	}
	

	
	public double getWeight(int index) {
		return weights[index];
	}
	
	public double[] getNeuronBackwardInput() {
		return backwardValue;
	}
	
	public double[] backward(double backwardinput) {
		
		if(backwardValue==null) {
			backwardValue=new double[weights.length];
		}
		
		for(int i=0;i<weights.length;i++) {
			double weight=getWeight(i);
			backwardValue[i]=weight*dz*backwardinput;
		}
		
		
		if(dweights==null) {
			dweights=new double[weights.length];
		}
		for(int i=0;i<weights.length;i++) {
			dweights[i]=inputMem[i]*dz*backwardinput;
			sumDWeights[i]+=dweights[i];
		}
		dbias=backwardinput*dz;
		sumDBias+=dbias;
//		System.out.print("==================偏差");
//		for(int i=0;i<weights.length;i++) {
//			
//			double out=(weights[i]-0.5*dweights[i]);
//			System.out.print(dweights[i]+"("+out+") ,");
//			
//		}
//		System.out.println();
//		
//		for(int i=0;i<backwardValue.length;i++) {
//			System.out.print(backwardValue[i]+" ");
//		}
//		System.out.println();
		
		return backwardValue; 
	}
	
	public void reset() {
		
		this.sumDWeights=new double[weights.length];
		this.sumDBias=0;
	}
	
	public void update(int batchSize,double learnRate) {
		for(int i=0;i<weights.length;i++) {
			double w=weights[i];
			double dw=sumDWeights[i]/batchSize;
			double newWeight=w-learnRate*dw;
			weights[i]=newWeight;
		}
		
		double avgbias=sumDBias/batchSize;
		double newBias=bias-learnRate*avgbias;
		bias=newBias;
		reset();
	}

	
	public void setWeights(double weights[]) {
		this.weights=weights;
		this.dweights=new double[weights.length];
		reset();
	}
	
	public void setBias(double bias) {
		this.bias=bias;
	}
	
	
	public double forward(double x[]) {
		inputMem=x;
		double t= CommonFunction.dot(x, weights)+bias;
		double f=CommonFunction.sigmoid(t);
		value=f;
		dz=f*(1-f);
		return value;
	}
	

	
	
	

	
}
