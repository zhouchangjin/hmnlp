package com.harmonywisdom.datamining.datasource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.harmonywisdom.datamining.model.Attribute;
import com.harmonywisdom.datamining.model.AttributeType;
import com.harmonywisdom.datamining.model.Attributes;
import com.harmonywisdom.datamining.model.Instances;
import com.harmonywisdom.datamining.model.SparseInstance;

public class ArffReader {
	String path;
	String name;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Instances loadInstance(){
		Attributes attributes=new Attributes();
		Instances instances=new Instances();
		instances.setAttributes(attributes);
		File f=new File(path);
		try {
			BufferedReader br=new BufferedReader(new FileReader(f));
			String line=null;
			while((line=br.readLine())!=null){
				if(line.startsWith("%")){
					continue;
				}else if(line.startsWith("@relation")){
					String pair[]=line.split("@relation");
					if(pair.length>1){
						setName(pair[1]);
					}
				}else if(line.startsWith("@attribute")){
					String pair[]=line.split(" ");
					if(pair.length==3){
						String attributeName=pair[1];
						String attributeTypeStr=pair[2];
						if(attributeTypeStr.equals("numeric")){
							attributes.addAttribute(attributeName, AttributeType.Numeric);
						}else if(attributeTypeStr.equals("Date")){
							
						}else if(attributeTypeStr.startsWith("{") && attributeTypeStr.endsWith("}")){
							attributeTypeStr=attributeTypeStr.replace("{", "");
							attributeTypeStr=attributeTypeStr.replace("}", "");
							attributes.addAttribute(attributeName, AttributeType.Nominal,attributeTypeStr.split(","));
						}
					}
				}else if(line.startsWith("@data")){
					break;
				}else{
					continue;
				}
			}
			
			while((line=br.readLine())!=null){
				
				
				if(!line.startsWith("{")){
					String values[]=line.split(",");
					for(int i=0;i<values.length;i++){
						double value=Double.parseDouble(values[i]);
						
					}
				}else{
					SparseInstance instance=new SparseInstance();
					instance.bindAttributes(attributes);
					String values[]=line.replace("{", "").replace("}", "").split(",");
					for(int i=0;i<values.length;i++){
						String pair[]=values[i].split(" ");
						if(pair.length==2){
							int index=Integer.parseInt(pair[0]);
							Attribute att=attributes.getAttribute(index);
							if(att.getType().equals(AttributeType.Numeric)){
								double value=Double.parseDouble(pair[1]);
								instance.setValue(att, value);
							}else if(att.getType().equals(AttributeType.Nominal)){
								String value=pair[1];
								instance.setValue(att, value);
							}
							
						}
						
					}
					instances.add(instance);
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instances;
		
	}

}
