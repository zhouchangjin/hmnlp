package com.harmonywisdom.datamining.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer implements NeuronNetworkLayer{
	
	List<INeuron> hiddenNeurons;
	
	double activateResult[];
	
	int layerSeq;
	
	int pLayerNodes=-1;
	
	NeuronNetworkLayer previousLayer;
	
	
	public HiddenLayer() {
		hiddenNeurons=new ArrayList<>();
	}
	
	public HiddenLayer(int seq) {
		hiddenNeurons=new ArrayList<>();
		this.layerSeq=seq;
	}

	@Override
	public double[] forward(double[] inputValues) {
		// TODO Auto-generated method stub
		double[] out=new double[hiddenNeurons.size()];
		for(int i=0;i<hiddenNeurons.size();i++) {
			INeuron n=hiddenNeurons.get(i);
			double vi=n.forward(inputValues);
			out[i]=vi;
		}
		activateResult=out;
		return activateResult;
	}

	@Override
	public void initSize(int neuroCount) {
		for(int i=0;i<neuroCount;i++) {
			Neuron n=new Neuron();
			//hiddenNeurons.add(n);
			addNeuron(n);
		}
	}

	@Override
	public void setWeights(int neuronIndex, double[] weights) {
		INeuron n=hiddenNeurons.get(neuronIndex);
		n.setWeights(weights);	
	}

	@Override
	public void setBias(int neuronIndex, double bias) {
		// TODO Auto-generated method stub
		INeuron n=hiddenNeurons.get(neuronIndex);
		n.setBias(bias);
	}

	@Override
	public void initLayerWithSameWeights(int neuroCount, double[] weights, double bias) {
		
		for(int i=0;i<neuroCount;i++) {
			Neuron n=new Neuron();
			n.setWeights(weights);
			n.setBias(bias);
			addNeuron(n);
			//hiddenNeurons.add(n);
		}
	}

	@Override
	public INeuron getNeuron(int position) {
		// TODO Auto-generated method stub
		return hiddenNeurons.get(position);
	}

	@Override
	public int getNeuronCnt() {
		// TODO Auto-generated method stub
		return hiddenNeurons.size();
	}

	@Override
	public void setLayerSeq(int layerSeq) {
		// TODO Auto-generated method stub
		this.layerSeq=layerSeq;
	}

	@Override
	public int getLayerSeq() {
		// TODO Auto-generated method stub
		return layerSeq;
	}

	@Override
	public void initLayerWithWeights(int nCnt, double[][] weights, double[] bias) {
		
		int size=weights.length;
		if(size!=nCnt) {
			System.out.println("入参数量错误");
		}
		for(int i=0;i<nCnt;i++) {
			double weightsi[]=weights[i];
			double biasi=bias[i];
			INeuron n=new Neuron();
			n.setWeights(weightsi);
			n.setBias(biasi);
			addNeuron(n);
			//hiddenNeurons.add(n);
		}
		
		
	}

	@Override
	public double[] getSumPartialDerivation() {
		int pCnt=getPLayerNodesCnt();
		double[] sumDerivation=new double[pCnt];
		for(int j=0;j<getNeuronCnt();j++) {
			INeuron pn=getNeuron(j);
			double backwardinputArr[]=pn.getNeuronBackwardInput();
			for(int i=0;i<backwardinputArr.length;i++) {
				double backwardinput=backwardinputArr[i];
				double sumInput=sumDerivation[i];
				sumInput+=backwardinput;
				sumDerivation[i]=sumInput;
			}
		}	
		return sumDerivation;
	}

	@Override
	public int getPLayerNodesCnt() {
		if(pLayerNodes==-1) {
			for(int i=0;i<getNeuronCnt();i++) {
				INeuron pn=getNeuron(i);
				if(pLayerNodes<pn.getInputSize()) {
					pLayerNodes=pn.getInputSize();
				}
			}
		}
		return pLayerNodes;
	}

	@Override
	public void backward(NeuronNetworkLayer backlayer) {
		double sumChainedDerivation[]=backlayer
				.getSumPartialDerivation();
		int sizeCur=getNeuronCnt();
		for(int i=0;i<sizeCur;i++) {
			INeuron n=getNeuron(i);
			double sumInput=sumChainedDerivation[i];
			n.backward(sumInput);
		}
	}



	@Override
	public void addNeuron(INeuron neuron) {
		int layerNo=layerSeq;
		int neuronNo=hiddenNeurons.size();
		neuron.setLayerNo(layerNo);
		neuron.setNeuronNo(neuronNo);
		hiddenNeurons.add(neuron);
	}

	@Override
	public void setPreviousLayer(NeuronNetworkLayer layer) {
		
		previousLayer=layer;
	}

	@Override
	public NeuronNetworkLayer getPreviousLayer() {
		// TODO Auto-generated method stub
		return previousLayer;
	}

	
	


}
