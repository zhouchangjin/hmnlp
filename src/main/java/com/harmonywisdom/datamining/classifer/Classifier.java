package com.harmonywisdom.datamining.classifer;

import com.harmonywisdom.datamining.model.Instance;
import com.harmonywisdom.datamining.model.Instances;
/**
 * 
 * @author 01
 *
 */
public interface Classifier {
	
	
	public void build(Instances instances);
	public ClassifyResult classifySingleInstance(Instance instance);

}
