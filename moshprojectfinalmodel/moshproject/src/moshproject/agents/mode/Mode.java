package moshproject.agents.mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import moshproject.common.Constants;
import moshproject.common.Constants.DistanceRange;
import moshproject.common.Constants.Occupation;
import moshproject.fuzzycollections.AffectiveGenerator;

public abstract class Mode {
	List<ModeAttribute> attributeList = new ArrayList<ModeAttribute>();
	List<ModeAttribute> subAttributeList = new ArrayList<ModeAttribute>();
	Random rand = new Random();
	private double attrCognitiveLevel;
//	Occupation occupation;
	
	public Mode(){
		AffectiveGenerator evaluatePurposeAffective;
	}
	
	public ModeAttribute getAttribute(String name) {
		ModeAttribute a = null;
		
		for (ModeAttribute attr : attributeList){
			if (attr.name.equals(name)){
				a = attr;
				break;
			}
		}

		return a;
	}
	
	public void addAttribute(String name){
		ModeAttribute modeAttr = new ModeAttribute(name);
		attributeList.add(modeAttr);
	}
	
	public ModeAttribute addAllAttributes(String name, double value){
		ModeAttribute modeAttr = new ModeAttribute(name, value);
		attributeList.add(modeAttr);
		return modeAttr;
	}
	
	public double setValueToAttribute(String name, double value) {
		for (ModeAttribute attr : attributeList){
			if (attr.name.equals(name)){
				attr.value = value;
			}
		}
		return value;
		
	}
	
	public int setValueToAttribute(String name, int value) {
		for (ModeAttribute attr : attributeList){
			if (attr.name.equals(name)){
				attr.value = value;
			}
		}
		return value;
		
	}
	
	public void setValueToAttributeOccupation(String name, Constants.Occupation otherAttr) {
		for (ModeAttribute attr : attributeList){
			if (attr.name.equals(name)){
				attr.otherValue = otherAttr;
			}
		}		
	}
	
	public Constants.Occupation getValueOfAttributeOccupation(String name) {
		Constants.Occupation value = null;
		for (ModeAttribute valueAttr:attributeList){
			if (valueAttr.name.equals(name)){
				value =   (Occupation)valueAttr.otherValue;
			}	
		}
		return value;
	}
///
	public void setValueToAttributeDistanceRange(String name, Constants.DistanceRange otherAttr) {
		for (ModeAttribute attr : attributeList){
			if (attr.name.equals(name)){
				attr.otherValue = otherAttr;
			}
		}		
	}
	
	public Constants.DistanceRange getValueOfAttributeDistanceRange(String name) {
		Constants.DistanceRange value = null;
		for (ModeAttribute valueAttr:attributeList){
			if (valueAttr.name.equals(name)){
				value =   (DistanceRange)valueAttr.otherValue;
			}	
		}
		return value;
	}
///
	public double getValueOfAttribute(String name) {
		double value=0.0;
		for (ModeAttribute valueAttr:attributeList){
			if (valueAttr.name.equals(name)){
				value = (double) valueAttr.value;
			}
			
		}
		return value;
	}
	
	
		
	public double getValueOfSubAttribute(List<ModeAttribute> name) {
		double value=0.0;
		for (ModeAttribute valueAttr:attributeList){
			if (valueAttr.name.equals(name)){
				value = (double) valueAttr.value;
			}
			
		}
		return value;
	}
	
	
//	public double evaluateMajorAttributeValue(List<ModeAttribute> attributeList){
//		double value=0.0;
//		for(ModeAttribute attribute: attributeList){
//			getValueOfSubAttribute(attributeList);
//		value += attribute.value;
//			
//		}
//		return value/attributeList.size();		
//	}
	
	public double evaluateHigherAbstraction(List<Double> attributeList){
		double value=0.0; int count =0;
		for(Double attribute: attributeList){	
			if (attribute>0){
				value += attribute;	
				count =count+1;
			}					
		}
		if (count==0){
			value = value/1;
		}else{
			value =value/count;
		}
		return value;
		
	}
	
	
//	public double evaluateFiringStrenght(List<Double> factorList){
//		double value=0.0;
//		for(Double factorValue: factorList){			
//		value += factorValue;
//			
//		}
//		return value/factorList.size();		
//	}
		
	public double valuesAndPriorityAverage(List<Double> newFiredList){	
		 int count =0; double demands =0.0;	
			for (Double antecedents :newFiredList){
				if(antecedents>0){
					demands +=antecedents;
					count =count+1;
				}				
			} 
			if (count==0){
				demands=demands/1;
			}else{
			 demands = demands/count;
			}
	return demands;	
	}

//////
	
	//dummy method - implemented in children classes
	public double addValueToAllAttribute() {
		return 0;
	}

public double setAttributeCognitveValue(double attrCognitive) {
		
		return this.attrCognitiveLevel = attrCognitive;	
	}
	//public abstract void updatePeriodOfTheDay(int period);


}
