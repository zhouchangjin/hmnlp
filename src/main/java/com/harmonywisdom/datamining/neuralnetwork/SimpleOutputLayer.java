package com.harmonywisdom.datamining.neuralnetwork;

public class SimpleOutputLayer implements IOutputLayer{
	
	double[] output;
	
	double[] actualValue;

	@Override
	public double[] forward(double[] predict) {
		output=predict;
		return output;
	}

	@Override
	public void setActualValue(double[] actualValue) {
		this.actualValue=actualValue;
		
	}

	@Override
	public double[] errorDerivation() {
		double[] errorVals=new double[actualValue.length];
		for(int i=0;i<errorVals.length;i++) {
			errorVals[i]=output[i]-actualValue[i];
		}
		
		return errorVals;
	}

	@Override
	public double[] errorRespective() {
		double[] errorVals=new double[actualValue.length];
		for(int i=0;i<errorVals.length;i++) {
			errorVals[i]=output[i]-actualValue[i];
		}
		return errorVals;
	}

	@Override
	public double error() {
		double sumE=0;
		for(int i=0;i<actualValue.length;i++) {
			double e=output[i]-actualValue[i];
			sumE+=e*e;
		}
		sumE=sumE/actualValue.length;
		return sumE;
	}

	@Override
	public void setActualClass(int classIndex) {
		// TODO Auto-generated method stub
		//用不到
	}

	@Override
	public void resetTotalError() {
		// TODO Auto-generated method stub
		
	}

}
