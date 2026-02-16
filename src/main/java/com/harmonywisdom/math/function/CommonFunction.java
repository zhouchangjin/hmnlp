package com.harmonywisdom.math.function;


public class CommonFunction {
	
	public static double sigmoid(double x) {
		return 1/(1+Math.pow(Math.E, -x));
	}
	
	public static double dot(double[] x,double[] y) {
		double sum=0.0;
		for(int i=0;i<x.length;i++) {
			sum+=x[i]*y[i];
		}
		return sum;
	}

	public static double[] softmax(double[] inputValues) {
		double sum=0.0;
		double[] component=new double[inputValues.length];
		double[] output=new double[inputValues.length];
		for(int i=0;i<inputValues.length;i++) {
			double num=inputValues[i];
			double compo=Math.pow(Math.E,-num);
			sum+=compo;
			component[i]=compo;
		}
		for(int i=0;i<component.length;i++) {
			output[i]=component[i]/sum;
		}
		return output;
	}

}
