package moshproject.agents.mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import moshproject.common.Constants;
import moshproject.fuzzycollections.AffectiveGenerator;

public abstract class Mode {
	List<ModeAttribute> attributeList = new ArrayList<ModeAttribute>();
	List<ModeAttribute> subAttributeList = new ArrayList<ModeAttribute>();
	Random rand = new Random();
	
//	private double othersAttitude;
//	private double luggageCarrier;
//	private double crosssingAndSigns;
//	private double facilitiesAtDestination;
//	private double journeyTime;
//	private double pathsAndLanes;
//	private double routeAvailability;
	
	
	public Mode(){
		addAttribute(Constants.attitudeOfOthers);
		addAttribute(Constants.capabilityForLuggageCarrier);
		addAttribute(Constants.crossingAndRoadsigns);
		addAttribute(Constants.facilitiesAtDestination);
		addAttribute(Constants.journeyTimeConsideration);
		addAttribute(Constants.paths);
		addAttribute(Constants.routeAvailabilityAndObstruction);
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
	
	public double getValueOfAttribute(String name) {
		double value=0.0;
		for (ModeAttribute valueAttr:attributeList){
			if (valueAttr.name.equals(name)){
				value = valueAttr.value;
			}
			
		}
		return value;
	}
	
	public double getValueOfSubAttribute(List<ModeAttribute> name) {
		double value=0.0;
		for (ModeAttribute valueAttr:attributeList){
			if (valueAttr.name.equals(name)){
				value = valueAttr.value;
			}
			
		}
		return value;
	}
	

	
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



}
