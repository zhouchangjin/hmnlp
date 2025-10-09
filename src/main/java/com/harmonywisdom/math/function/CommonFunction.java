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

}
