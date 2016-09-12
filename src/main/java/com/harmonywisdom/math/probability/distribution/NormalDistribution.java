package com.harmonywisdom.math.probability.distribution;

public class NormalDistribution {
	
	public static double probability(double avg,double std,double x){
		double result=0;
		double contsant1=Math.pow(2*Math.PI, -0.5);
		double power=-1*(x-avg)*(x-avg)*0.5/std/std;
		double right=Math.pow(Math.E, power);
		result=right*contsant1/std;
		return result;
	}
	

	
	public static void main(String args[]){
		System.out.println(probability(0,1,2));
	}

}
