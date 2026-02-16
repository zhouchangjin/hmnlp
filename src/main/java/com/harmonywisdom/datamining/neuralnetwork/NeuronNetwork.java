package com.harmonywisdom.datamining.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuronNetwork {
	
	int miniBatchSize=64; //进行参数调整需要计算多少个样本
	
	int epochNum=100; // 反复迭代训练样本次数，次数越多，越接近过拟合。

	double learningRate=0.5f; //学习速率
	
	
	
	List<NeuronNetworkLayer> hiddenLayers;

	
	public NeuronNetwork(){
		hiddenLayers=new ArrayList<>();
	}
	
	public NeuronNetworkLayer getLastLayer() {
		return hiddenLayers.get(hiddenLayers.size()-1);
	}
	
	public void addHiddenLayer(NeuronNetworkLayer networkLayer) {
		networkLayer.setLayerSeq(hiddenLayers.size());
		hiddenLayers.add(networkLayer);
	}
	
	public void addHiddenLayer(int neuronCount) {
		
		HiddenLayer hiddenLayer=new HiddenLayer();
		hiddenLayer.initSize(neuronCount);
		hiddenLayers.add(hiddenLayer);
	}
	
	public void train(List<double[]> datalist,List<double[]> actualVals) {
		for(int i=0;i<epochNum;i++) {
			List<double[]> miniBatch=new ArrayList<>();
			List<double[]> miniBatchVal=new ArrayList<>();
			for(int k=0;k<datalist.size();k++) {
				miniBatch.add(datalist.get(k));
				miniBatchVal.add(actualVals.get(k));
				if(miniBatch.size()==miniBatchSize) {
					trainBatch(miniBatch,miniBatchVal);
				}
			}
		}
	}
	
	private void trainBatch(List<double[]> miniBatch, List<double[]> miniBatchVal) {
		for(int i=0;i<miniBatch.size();i++) {
			double input[]=miniBatch.get(i);
			double y[]=miniBatchVal.get(i);
			double outputvalues[]=triggerInput(input);
			double error[]=new double[outputvalues.length];
			for(int ei=0;ei<error.length;ei++) {
				error[ei]=outputvalues[ei]-y[ei];
			}
			//double error[]=new double[] {outputvalues[0]-0.01,outputvalues[1]-0.99};
			triggerBackward(error);
		}
		
		for(NeuronNetworkLayer layer:hiddenLayers) {
			int c=layer.getNeuronCnt();
			for(int i=0;i<c;i++) {
				INeuron n=layer.getNeuron(i);
				n.update(miniBatchSize, learningRate);
			}
		}
		
	}

	public double[] triggerInput(double input[]) {
		
		double currentInput[]=input;
		for(NeuronNetworkLayer hiddenLayer:hiddenLayers) {
			currentInput=hiddenLayer.forward(currentInput);
			for(int k=0;k<currentInput.length;k++) {
				System.out.print(currentInput[k]+" , ");
			}
			System.out.println();
		}
		
		return currentInput;
	}
	
	

	private void triggerBackward(double[] outputvalues) {
		NeuronNetworkLayer layer=getLastLayer();
		int size=layer.getNeuronCnt();
		int len=outputvalues.length;
		if(size!=len) {
			System.out.println("结果数辆和最后一层神经元数量不一致");
		}
		for(int i=0;i<size;i++) {
			INeuron n=layer.getNeuron(i);
			n.backward(outputvalues[i]);
		}
		NeuronNetworkLayer previousLayer=layer;
		int previousSeq=previousLayer.getLayerSeq();
		while(previousSeq>0) {
			int curSeq=previousSeq-1;
			NeuronNetworkLayer currentLayer=getLayer(curSeq);
			int sizeCur=currentLayer.getNeuronCnt();

			//System.out.println(previousSeq+"[--]"+curSeq);
			
			for(int i=0;i<sizeCur;i++) {
				INeuron n=currentLayer.getNeuron(i);
				double sumInput=0;
				for(int j=0;j<previousLayer.getNeuronCnt();j++) {
					INeuron pn=previousLayer.getNeuron(j);
					double backwardinputArr[]=pn.getNeuronBackwardInput();
					double backwardinput=backwardinputArr[i];
					sumInput+=backwardinput;
				}
				n.backward(sumInput);	
			}
			previousSeq=curSeq;
			previousLayer=currentLayer;
		}
	}
	
	private NeuronNetworkLayer getLayer(int previousLayer) {
		return this.hiddenLayers.get(previousLayer);
	}

	public static void main(String args[]) {
		NeuronNetwork ann=new NeuronNetwork();
		HiddenLayer hiddenLayer=new HiddenLayer();
		//hiddenLayer.initLayerWithSameWeights(2, new double[] {0,1}, 0);
		
	    hiddenLayer.initLayerWithWeights(2, new double[][] {
	    	{0.15,0.2},
	    	{0.25,0.3}
	    	
	    }, new double[] {
	    		0.35,
	    		0.35
	    });
		
		HiddenLayer output=new HiddenLayer();
	    //output.initLayerWithSameWeights(1,  new double[] {0,1}, 0);
		output.initLayerWithWeights(2, new double[][] {
			{0.4,0.45},
			{0.5,0.55}
			
		}, new double[] {
			0.6,
			0.6
		});
	    
		ann.addHiddenLayer(hiddenLayer);
		ann.addHiddenLayer(output);
		
		double outputvalues[]=ann.triggerInput(new double[] {0.05,0.1});
		double actualValue[]=new double[]{0.01,0.99};
		double error[]=new double[] {outputvalues[0]-0.01,outputvalues[1]-0.99};
		ann.triggerBackward(error);
		//ann.triggerBackward(outputvalues);
		
		
	}


}
