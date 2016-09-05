package com.harmonywisdom.datamining.model;

public class DoubleAttributeStats {
	int attIndex=-1;
	long count=0;
	double max=Double.MIN_VALUE;
	double min=Double.MAX_VALUE;
	double avg=0;
	double stdDev;
	double sum=0.0;
	double squareSum=0.0;
	double variance=0.0;
	
	
	public double getVariance() {
		return variance;
	}
	public void setVariance(double variance) {
		this.variance = variance;
	}
	public double getSquareSum() {
		return squareSum;
	}
	public void setSquareSum(double squareSum) {
		this.squareSum = squareSum;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public int getAttIndex() {
		return attIndex;
	}
	public void setAttIndex(int attIndex) {
		this.attIndex = attIndex;
	}
	public double getStdDev() {
		return stdDev;
	}
	public void setStdDev(double stdDev) {
		this.stdDev = stdDev;
	}

	
	public long getCount() {
		return count;
	}
	public void increase() {
		this.count++;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	
	public String toString(){
		String res="";
		if(attIndex==-1){
			return "";
		}
		res+="["+min+","+max+"]\n";
		res+="avg="+avg+"\n";
		res+="std="+stdDev+"\n";
		return res;
	}


}
