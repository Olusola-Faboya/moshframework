package moshproject.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import moshproject.agents.mode.PersonalVehicle;
import moshproject.fuzzycollections.FuzzyDecisionGenerator;

public class FuzzyDecisionVariables {
	private Map<String, List<Double>> theVariables;
	private ArrayList<Double> physical;
	private ArrayList<Double> cognitive;
	private ArrayList<Double> affective;
	private ArrayList<Double> unPleasantCognitiveList;
	private ArrayList<Double> NeitherNorPleasantCognitiveList;
	private ArrayList<Double> pleasantCognitiveList;
	private ArrayList<Double> unPleasantPhysicalList;
	private ArrayList<Double> NeitherNorPleasantPhysicalList;
	private ArrayList<Double> pleasantPhysicalList;
	private ArrayList<Double> unPleasantAffectiveList;
	private ArrayList<Double> NeitherNorPleasantAffectiveList;
	private ArrayList<Double> pleasantAffectiveList;
	private ArrayList<Double> countList;
	private ArrayList<Integer> countList1;
	private int counts;
	private ArrayList<Double> disaggregateFiredValueList;
	private double averageFiredValues;
	private ArrayList<Double>subPCADemandValue;	
	public FuzzyDecisionVariables(){
	
	}
	
	public int firedCounts(List<Double> newFiredList){	
		counts =0;	
			for (Double antecedents :newFiredList){
				if(antecedents>0){
					counts=counts +1;
				}
			} 				
			return counts;	
	}
	
	public int vPfiredCounts(List<Integer> newFiredList){				
			for (Integer antecedents :newFiredList){
				counts =0;
				if(antecedents>0){
					counts=counts +1;
				}
			} 				
			return counts;	
	}
	
	//Finding the average of all fired disagggregate values
	public double firedTravelDemands(List newFiredList){
		disaggregateFiredValueList = new ArrayList<Double>();
		 int count =0; averageFiredValues =0;
		disaggregateFiredValueList = (ArrayList<Double>) newFiredList;
			for (Double antecedentsValue :disaggregateFiredValueList){
				if(antecedentsValue>0){
					averageFiredValues +=antecedentsValue;
					count =count+1;
				}				
			} 
			if (count==0){
				averageFiredValues=averageFiredValues/1;
			}else{
			 averageFiredValues = averageFiredValues/count;
			}
		//	System.out.print("count is"+count+",");
		//	System.out.print("demands is"+demands+";");
			
	return averageFiredValues;	
	}
	
	///This method unbundled and get values for all cognitive input levels for the purpose of debugging
	public List<Double> uNboundledMapPCACognitive(Map<String, List<Double>> fuzzyDecisionVariables) {
		theVariables = new HashMap<>();
		cognitive = new ArrayList<>();									   
		theVariables = (Map<String, List<Double>>) fuzzyDecisionVariables;
		if(theVariables!=null){
		for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){			
			String name =newCollections.getKey();		
				if (name=="UnpleasantCognitive"){
					List<Double> value = newCollections.getValue();	
					cognitive.addAll(value);	
				}else if(name=="NeitherPleasantNorUnpleasantCognitive"){
					List<Double> value = newCollections.getValue();	
					cognitive.addAll(value);
				}else if(name=="PleasantCognitive"){
					List<Double> value = newCollections.getValue();	
					cognitive.addAll(value);
				}
			}
		}
		return cognitive;					
	}
////This method unboundled and get values for fired Unpleasant cognitive 
	public List<Double> uNboundledMapUnpleasantCognitive(Map<String, List<Double>> fuzzyDecisionVariables) {	
		theVariables = new HashMap<>();
	unPleasantCognitiveList = new ArrayList<>();
	theVariables  = (Map<String, List<Double>>) fuzzyDecisionVariables;				
	for(Entry<String, List<Double>> newCollections: theVariables .entrySet()){
		String name =newCollections.getKey();				
		if (name=="UnpleasantCognitive"){
			List<Double> value = newCollections.getValue();	
			unPleasantCognitiveList.addAll(value);
		}					
	}						 
	return unPleasantCognitiveList	;			
}

////This method unboundled and get values for fired Neither Pleasant Nor Unpleasant cognitive 
public List<Double> uNboundledMapNeitherNorPleasantCognitive(Map<String, List<Double>> fuzzyDecisionVariables) {	
	theVariables = new HashMap<>();
	NeitherNorPleasantCognitiveList = new ArrayList<>();
	theVariables = (Map<String, List<Double>>)fuzzyDecisionVariables;				
	for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){
		String name =newCollections.getKey();				
		 if (name=="NeitherPleasantNorUnpleasantCognitive"){
			List<Double> value = newCollections.getValue();
			NeitherNorPleasantCognitiveList.addAll(value);
		}					
	}					 
	return NeitherNorPleasantCognitiveList;			
}
////This method unboundled and get values for fired Pleasant cognitive 
	public ArrayList<Double> uNboundledMapPleasantCognitive(Map<String, List<Double>> fuzzyDecisionVariables) {	
		theVariables = new HashMap<>();
		pleasantCognitiveList = new ArrayList<>();
		theVariables = (Map<String, List<Double>>)fuzzyDecisionVariables;				
		for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){
			String name =newCollections.getKey();				
			 if (name=="PleasantCognitive"){
				List<Double> value = newCollections.getValue();
				pleasantCognitiveList.addAll(value);
			}					
		}					 
		return pleasantCognitiveList	;			
	}
	///////PHYSICAL SECTION
	///This method unboundled and get values for all cognitive input levels for the purpose of debugging
		public List<Double> uNboundledMapPCAPhysical(Map<String, List<Double>> fuzzyDecisionVariables) {
			theVariables = new HashMap<>();
			physical = new ArrayList<>();									   
			theVariables = (Map<String, List<Double>>) fuzzyDecisionVariables;				
			for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){
				String name =newCollections.getKey();				
				if (name=="UnpleasantPhysical"){
					List<Double> value = newCollections.getValue();	
					physical.addAll(value);	
				}else if(name=="NeitherPleasantNorUnpleasantPhysical"){
					List<Double> value = newCollections.getValue();	
					physical.addAll(value);
				}else if(name=="PleasantPhysical"){
					List<Double> value = newCollections.getValue();	
					physical.addAll(value);
				}									
			}
			return physical;					
		}
////This method unboundled and get values for fired Unpleasant Physical 
	public List<Double> uNboundledMapUnpleasantPhysical(Map<String, List<Double>> fuzzyDecisionVariables) {	
		theVariables = new HashMap<>();
	unPleasantPhysicalList = new ArrayList<>();
	theVariables  = (Map<String, List<Double>>) fuzzyDecisionVariables;				
	for(Entry<String, List<Double>> newCollections: theVariables .entrySet()){
		String name =newCollections.getKey();				
		if (name=="UnpleasantPhysical"){
			List<Double> value = newCollections.getValue();	
			unPleasantPhysicalList.addAll(value);
		}					
	}						 
	return unPleasantPhysicalList	;			
}
////This method unboundled and get values for fired Pleasant Physical 
	public ArrayList<Double> uNboundledMapPleasantPhysical(Map<String, List<Double>> fuzzyDecisionVariables) {	
		theVariables = new HashMap<>();
		pleasantPhysicalList = new ArrayList<>();
		theVariables = (Map<String, List<Double>>)fuzzyDecisionVariables;				
		for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){
			String name =newCollections.getKey();				
			 if (name=="PleasantPhysical"){
				List<Double> value = newCollections.getValue();
				pleasantPhysicalList.addAll(value);
			}					
		}					 
		return pleasantPhysicalList;			
	}
	
////This method unboundled and get values for fired Neither Pleasant Nor Unpleasant Physical 
public List<Double> uNboundledMapNeitherNorPleasantPhysical(Map<String, List<Double>> fuzzyDecisionVariables) {	
	theVariables = new HashMap<>();
	NeitherNorPleasantPhysicalList = new ArrayList<>();
	theVariables = (Map<String, List<Double>>)fuzzyDecisionVariables;				
	for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){
		String name =newCollections.getKey();				
		 if (name=="NeitherPleasantNorUnpleasantPhysical"){
			List<Double> value = newCollections.getValue();
			NeitherNorPleasantPhysicalList.addAll(value);
		}					
	}					 
	return NeitherNorPleasantPhysicalList;			
}	
///////AFFECTIVE SECTION
	///This method unboundled and get values for all cognitive input levels for the purpose of debugging
		public List<Double> uNboundledMapPCAAffective(Map<String, List<Double>> fuzzyDecisionVariables) {
			theVariables = new HashMap<>();
			affective = new ArrayList<>();									   
			theVariables = (Map<String, List<Double>>) fuzzyDecisionVariables;				
			for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){
				String name =newCollections.getKey();				
				if (name=="UnpleasantAffective"){
					List<Double> value = newCollections.getValue();	
					affective.addAll(value);	
				}else if(name=="NeitherPleasantNorUnpleasantAffective"){
					List<Double> value = newCollections.getValue();	
					affective.addAll(value);
				}else if(name=="PleasantAffective"){
					List<Double> value = newCollections.getValue();	
					affective.addAll(value);
				}									
			}
			return affective;					
		}
////This method unboundled and get values for fired Unpleasant Affective 
	public List<Double> uNboundledMapUnpleasantAffective(Map<String, List<Double>> fuzzyDecisionVariables) {	
		theVariables = new HashMap<>();
	unPleasantAffectiveList = new ArrayList<>();
	theVariables  = (Map<String, List<Double>>) fuzzyDecisionVariables;				
	for(Entry<String, List<Double>> newCollections: theVariables .entrySet()){
		String name =newCollections.getKey();				
		if (name=="UnpleasantAffective"){
			List<Double> value = newCollections.getValue();	
			unPleasantAffectiveList.addAll(value);
		}					
	}						 
	return unPleasantAffectiveList;			
}

////This method unboundled and get values for fired Neither Pleasant Nor Unpleasant Physical 
public List<Double> uNboundledMapNeitherNorPleasantAffective(Map<String, List<Double>> fuzzyDecisionVariables) {	
	theVariables = new HashMap<>();
	NeitherNorPleasantAffectiveList = new ArrayList<>();
	theVariables = (Map<String, List<Double>>)fuzzyDecisionVariables;				
	for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){
		String name =newCollections.getKey();				
		 if (name=="NeitherPleasantNorUnpleasantAffective"){
			List<Double> value = newCollections.getValue();
			NeitherNorPleasantAffectiveList.addAll(value);
		}					
	}					 
	return NeitherNorPleasantAffectiveList;			
}
////This method unboundled and get values for fired Pleasant Physical 
	
	public ArrayList<Double> uNboundledMapPleasantAffective(Map<String, List<Double>> fuzzyDecisionVariables) {	
		theVariables = new HashMap<>();
		pleasantAffectiveList = new ArrayList<>();
		theVariables = (Map<String, List<Double>>)fuzzyDecisionVariables;				
		for(Entry<String, List<Double>> newCollections: theVariables.entrySet()){
			String name =newCollections.getKey();				
			 if (name=="PleasantAffective"){
				List<Double> value = newCollections.getValue();
				pleasantAffectiveList.addAll(value);
			}					
		}					 
		return pleasantAffectiveList;			
	}
/////This section evaluates the demnads
	public double evaluatePCADemand(double getPCA){		
		double pca =0;		
			 if ( getPCA==0){
				 pca= 0;
			 }else{
				 pca= (1-getPCA);
			 }
		return pca ;
	}
	
	
}
