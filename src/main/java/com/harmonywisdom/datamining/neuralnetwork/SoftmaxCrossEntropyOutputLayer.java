package com.harmonywisdom.datamining.neuralnetwork;

import com.harmonywisdom.math.function.CommonFunction;

public class SoftmaxCrossEntropyOutputLayer implements IOutputLayer{
	
	double[] output;
	
	int actualClass;
	
	double totalEntropy;

	@Override
	public void setActualValue(double[] actualValue) {
		
	}

	@Override
	public double[] forward(double[] predict) {
		output=CommonFunction.softmax(predict);
		return output;
	}

	@Override
	public double[] errorDerivation() {
		double errorDev[]=new double[output.length];
		for(int i=0;i<errorDev.length;i++) {
			if(i==actualClass) {
				//预测分类预测对了，softmax为1，误差为0
				errorDev[i]=output[i]-1;
			}else {
				//预测的分类为B实际分类为A，误差就是实际分类的softmax的CrossEntropy
				//即实际分类的likelihood越低，CE的值越接近无穷大
				//likelihood越接近1,CE为0
				errorDev[i]=output[i];
			}
		}
		totalEntropy+=error();
		return errorDev;
	}

	@Override
	public double[] errorRespective() {
		// TODO Auto-generated method stub
		//没来得及写
		return null;
	}

	@Override
	public double error() {
		// TODO Auto-generated method stub
		//没来得及写
		double p=output[actualClass];
		double entropy=-1*Math.log(p);
		return entropy;
	}

	@Override
	public void setActualClass(int classIndex) {
		// TODO Auto-generated method stub
		this.actualClass=classIndex;
	}

	@Override
	public void resetTotalError() {
		// TODO Auto-generated method stub
		totalEntropy=0;
	}

}
