package com.harmonywisdom.datamining.neuralnetwork;

public interface IOutputLayer {
	
	void setActualClass(int classIndex);
	
	void setActualValue(double[] actualValue);
	
	double[] forward(double[] predict);
	
	double[] errorDerivation();
	
	double[] errorRespective();
	
	double error();
	
	void resetTotalError();

}
