package moshproject.agent.mode.attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import moshproject.agents.mode.Cycle;
import moshproject.agents.mode.Mode;
import moshproject.agents.mode.PersonalVehicle;
import moshproject.agents.mode.PublicTransport;
import moshproject.agents.mode.Walking;
import moshproject.agents.passenger.Passenger;
import moshproject.common.Constants;
import moshproject.common.FuzzyDecisionVariables;
import moshproject.fuzzycollections.FuzzyDecisionGenerator;

public class ModeDelays {
	
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 
	
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;
	private double delaysCognitive;
	private double delaysPhysical;
	private double privateDelaysCognitive;
	private double publicDelaysCognitive;
	private double delaysAffective;
	private double privateDelaysAffective;
	private double publicDelaysAffective;
	private double delays;
	private double privateDelays;
	private double publicDelays;
	private Vector delaysDecisionVariables;
	private ArrayList<Double> delaysCognitiveList;
	private ArrayList<Double> delaysUnpleasantCognitiveList;
	private ArrayList<Double> delaysNeitherNorPleasantCognitiveList;
	private ArrayList<Double> delaysPleasantCognitiveList;
	private ArrayList<Double> delaysUnpleasantPhysicalList;
	private ArrayList<Double> delaysNeitherNorPleasantPhysicalList;
	private ArrayList<Double> delaysPleasantPhysicalList;
	private ArrayList<Double> delaysUnpleasantAffectiveList;
	private ArrayList<Double> delaysNeitherNorPleasantAffectiveList;
	private ArrayList<Double> delaysPleasantAffectiveList;
	private ArrayList<Double> delaysAffectiveList;
	private double cycleDelays;
	private double walkingDelays;	
		
	public ModeDelays(Mode preferredMode){
		this.prefferedMode =preferredMode;
		updateDelays();
		pca= new FuzzyDecisionVariables();
	}
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	
	private double updateDelaysPhysical() {						
		return delaysPhysical= prefferedMode.setValueToAttribute(Constants.delaysPhysical,0);			
	}
	
	public double getDelaysPhysical() {
		updateDelaysPhysical();
		return delaysPhysical;
	}

	private double updateDelaysCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			delaysCognitive = prefferedMode.getValueOfAttribute(Constants.delaysPerception);					
		}else if (prefferedMode instanceof PublicTransport){
			delaysCognitive = prefferedMode.getValueOfAttribute(Constants.delaysPerception);											
		}else if (prefferedMode instanceof Cycle){
			delaysCognitive = prefferedMode.getValueOfAttribute(Constants.delaysPerception);								
		}else if (prefferedMode instanceof Walking){
			delaysCognitive = prefferedMode.getValueOfAttribute(Constants.delaysPerception);													
		}		
		return delaysCognitive;			
	}	
	public double getDelaysCognitive() {
		updateDelaysCognitive();
		return delaysCognitive;
	}

	private double updateDelaysAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			delaysAffective = prefferedMode.getValueOfAttribute(Constants.delaysAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			delaysAffective = prefferedMode.getValueOfAttribute(Constants.delaysAffective);											
		}else if (prefferedMode instanceof Cycle){
			delaysAffective = prefferedMode.getValueOfAttribute(Constants.delaysAffective);								
		}else if (prefferedMode instanceof Walking){
			delaysAffective = prefferedMode.getValueOfAttribute(Constants.delaysAffective);													
		}			
		return delaysAffective;			
	}	
	
	public double getDelaysAffective() {
		updateDelaysAffective();
		return delaysAffective;
	}

		////////The Fuzzy System Version
		public Vector delaysDecisionVariables() {	
			evaluateThreeVariablesSatisfaction = new FuzzyDecisionGenerator();
		//	getPrefferedMode();
			if (prefferedMode instanceof PersonalVehicle){
				getDelaysPhysical();
				getDelaysCognitive();
				getDelaysAffective();
			}else if (prefferedMode instanceof PublicTransport){
				getDelaysPhysical();
				getDelaysCognitive();
				getDelaysAffective();
			}else if (prefferedMode instanceof Cycle){
				getDelaysPhysical();
				getDelaysCognitive();
				getDelaysAffective();
			}else if (prefferedMode instanceof Walking){
				getDelaysPhysical();
				getDelaysCognitive();
				getDelaysAffective();
			}
			delaysDecisionVariables =	evaluateThreeVariablesSatisfaction.getTip(getDelaysPhysical(),getDelaysCognitive(),getDelaysAffective());	
			return delaysDecisionVariables;
		}
		public Vector getDelaysDecisionVariables() {
			return delaysDecisionVariables;
		}
///This method returns either fuzzy method or the ordinary method
		public double updateDelays(){				
			delaysDecisionVariables();	
			delays =(double) getDelaysDecisionVariables().get(0);		
			return delays ;
		}	
		public double getDelays() {
			return delays;
		}
		public double getPrivateDelays(){
			if(prefferedMode instanceof PersonalVehicle){
				privateDelays = this.getDelays();
			}	
			return privateDelays;
		}
		public double getPublicDelays(){
			if(prefferedMode instanceof PublicTransport){
				publicDelays = this.getDelays();
			}		
			return publicDelays;
		}
		public double getCycleDelays(){
			if(prefferedMode instanceof Cycle){
				cycleDelays = this.getDelays();
			}	
			return cycleDelays;
		}
		public double getWalkingDelays(){
			if(prefferedMode instanceof Walking){
				walkingDelays = this.getDelays();
			}		
			return walkingDelays;
		}
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedModeDelaysCognitive() {
			delaysCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getDelaysDecisionVariables().get(1);
			delaysCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);			 				 
			return delaysCognitiveList	;			
		}
		public double getFiredPrivateDelaysCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedModeDelaysCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicDelaysCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedModeDelaysCognitive());
			return publicCognitive;
		}
		public double getFiredCycleDelaysCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedModeDelaysCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingDelaysCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedModeDelaysCognitive());
			return walkingCognitive;
		}
///cognitive counts by mode
		public int getFiredPrivateDelaysCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedModeDelaysCognitive());	
			return counts;
		}			
		
		public int getFiredPublicDelaysCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedModeDelaysCognitive());		
			return counts ;
		}
		public int getFiredCycleDelaysCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedModeDelaysCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingDelaysCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedModeDelaysCognitive());		
			return counts ;
		}
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedModeDelaysUnpleasantCognitive() {				
			delaysUnpleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getDelaysDecisionVariables().get(1);				
			delaysUnpleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);	 			 
			return delaysUnpleasantCognitiveList	;			
		}
		public double getFiredPrivateDelaysUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedModeDelaysUnpleasantCognitive());
				return privateCognitive;
		}				
		public double getFiredPublicDelaysUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedModeDelaysUnpleasantCognitive());
				return publicCognitive;
		}
		public double getFiredCycleDelaysUnpleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedModeDelaysUnpleasantCognitive());
				return cycleCognitive;
		}
		public double getFiredWalkingDelaysUnpleasantCognitive(){
			double walkingCognitive =0;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedModeDelaysUnpleasantCognitive());
				return walkingCognitive;
		}
///Unpleasant Timeliness cognitive counts by mode
		public int getFiredPrivateDelaysUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedModeDelaysUnpleasantCognitive());	
				return counts;
		}							
		public int getFiredPublicDelaysUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedModeDelaysUnpleasantCognitive());		
				return counts ;
		}
		public int getFiredCycleDelaysUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedModeDelaysUnpleasantCognitive());	
			return counts;
		}			
		public int getFiredWalkingDelaysUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedModeDelaysUnpleasantCognitive());		
			return counts ;
		}
/////////				
		public ArrayList<Double> firedModeDelaysNeitherNorPleasantCognitive() {				
			delaysNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getDelaysDecisionVariables().get(1);				
			delaysNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);				 
			return delaysNeitherNorPleasantCognitiveList	;			
		}
		public double getFiredPrivateDelaysNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedModeDelaysNeitherNorPleasantCognitive());
				return privateCognitive;
		}				
		public double getFiredPublicDelaysNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedModeDelaysNeitherNorPleasantCognitive());
				return publicCognitive;
		}
		public double getFiredCycleDelaysNeitherNorPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedModeDelaysNeitherNorPleasantCognitive());
				return cycleCognitive;
		}
		public double getFiredWalkingDelaysNeitherNorPleasantCognitive(){
			double walkingCognitive =0;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedModeDelaysNeitherNorPleasantCognitive());
				return walkingCognitive;
		}
///Unpleasant Timeliness cognitive counts by mode
		public int getFiredPrivateDelaysNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedModeDelaysNeitherNorPleasantCognitive());	
				return counts;
		}							
		public int getFiredPublicDelaysNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedModeDelaysNeitherNorPleasantCognitive());		
				return counts ;
		}
		public int getFiredCycleDelaysNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedModeDelaysNeitherNorPleasantCognitive());	
			return counts;
		}			
		public int getFiredWalkingDelaysNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedModeDelaysNeitherNorPleasantCognitive());		
			return counts ;
		}
////////
		public ArrayList<Double> firedModeDelaysPleasantCognitive() {				
			delaysPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getDelaysDecisionVariables().get(1);				
			delaysPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);					 
			return delaysPleasantCognitiveList	;			
		}
		public double getFiredPrivateDelaysPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedModeDelaysPleasantCognitive());
				return privateCognitive;
		}				
		public double getFiredPublicDelaysPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedModeDelaysPleasantCognitive());
				return publicCognitive;
		}
		public double getFiredCycleDelaysPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedModeDelaysPleasantCognitive());
				return cycleCognitive;
		}
		public double getFiredWalkingDelaysPleasantCognitive(){
			double walkingCognitive =0;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedModeDelaysPleasantCognitive());
				return walkingCognitive;
		}
///Unpleasant Timeliness cognitive counts by mode
		public int getFiredPrivateDelaysPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedModeDelaysPleasantCognitive());	
				return counts;
		}							
		public int getFiredPublicDelaysPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedModeDelaysUnpleasantCognitive());		
				return counts ;
		}
		public int getFiredCycleDelaysPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedModeDelaysUnpleasantCognitive());	
			return counts;
		}			
		public int getFiredWalkingDelaysPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedModeDelaysPleasantCognitive());		
			return counts ;
		}
			
///////The following are the list of individual level of PCA-PHYSICAL
		public ArrayList<Double> firedModeDelaysUnpleasantPhysical() {				
			delaysUnpleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getDelaysDecisionVariables().get(1);				
			delaysUnpleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);
	//		System.out.println("The Unpleasant delays Physical Values:");
			for (Double antecedents :delaysUnpleasantPhysicalList){
		//	System.out.println(antecedents);
			} 			 
			return delaysUnpleasantPhysicalList;			
		}
			
		public ArrayList<Double> firedModeDelaysNeitherNorPleasantPhysical() {				
			delaysNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getDelaysDecisionVariables().get(1);				
			delaysNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);
		//	System.out.println("The Neither pleasant nor unpleasant delays Physical Values:");
			for (Double antecedents :delaysNeitherNorPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return delaysNeitherNorPleasantPhysicalList;			
		}
				
		public ArrayList<Double> firedModeDelaysPleasantPhysical() {				
			delaysPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getDelaysDecisionVariables().get(1);				
			delaysPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);
		//	System.out.println("The Pleasant delays Physical Values:");
			for (Double antecedents :delaysPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return delaysPleasantPhysicalList	;			
		}
				
///////The following are the list of individual level of PCA-AFFECTIVE
		public ArrayList<Double> firedModeDelaysAffective() {
			delaysAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getDelaysDecisionVariables().get(1);
			delaysAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);				 
			return delaysAffectiveList	;			
		}

		public double getFiredPrivateDelaysAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedModeDelaysAffective());
			return privateAffective;
		}
		
		public double getFiredPublicDelaysAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedModeDelaysAffective());
			return publicAffective;
		}
		public double getFiredCycleDelaysAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedModeDelaysAffective());
			return cycleAffective;
		}
///cognitive counts by mode
		public int getFiredPrivateDelaysAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof PersonalVehicle)
						counts =pca.firedCounts(firedModeDelaysAffective());	
					return counts;
		}			
				
		public int getFiredPublicDelaysAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof PublicTransport)
						counts  = pca.firedCounts(firedModeDelaysAffective());		
					return counts ;
		}
		public int getFiredCycleDelaysAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof Cycle)
						counts =pca.firedCounts(firedModeDelaysAffective());	
					return counts;
		}			
	/////	
		public ArrayList<Double> firedModeDelaysUnpleasantAffective() {				
			delaysUnpleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getDelaysDecisionVariables().get(1);				
			delaysUnpleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);					 
			return delaysUnpleasantAffectiveList;			
		}
		public double getFiredPrivateDelaysUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedModeDelaysUnpleasantAffective());
			return privateAffective;
		}
		
		public double getFiredPublicDelaysUnpleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedModeDelaysUnpleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleDelaysUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedModeDelaysUnpleasantAffective());
			return cycleAffective;
		}
///cognitive counts by mode
		public int getFiredPrivateDelaysUnpleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof PersonalVehicle)
						counts =pca.firedCounts(firedModeDelaysUnpleasantAffective());	
					return counts;
		}			
				
		public int getFiredPublicDelaysUnpleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof PublicTransport)
						counts  = pca.firedCounts(firedModeDelaysUnpleasantAffective());		
					return counts ;
		}
		public int getFiredCycleDelaysUnpleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof Cycle)
						counts =pca.firedCounts(firedModeDelaysUnpleasantAffective());	
					return counts;
		}
							
		public ArrayList<Double> firedModeDelaysNeitherNorPleasantAffective() {				
			delaysNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getDelaysDecisionVariables().get(1);				
			delaysNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
			return delaysNeitherNorPleasantAffectiveList ;			
		}
		public double getFiredPrivateDelaysNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedModeDelaysNeitherNorPleasantAffective());
			return privateAffective;
		}
		
		public double getFiredPublicDelaysNeitherNorPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedModeDelaysNeitherNorPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleDelaysNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedModeDelaysNeitherNorPleasantAffective());
			return cycleAffective;
		}
///cognitive counts by mode
		public int getFiredPrivateDelaysNeitherNorPleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof PersonalVehicle)
						counts =pca.firedCounts(firedModeDelaysNeitherNorPleasantAffective());	
					return counts;
		}			
				
		public int getFiredPublicDelaysNeitherNorPleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof PublicTransport)
						counts  = pca.firedCounts(firedModeDelaysNeitherNorPleasantAffective());		
					return counts ;
		}
		public int getFiredCycleDelaysNeitherNorPleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof Cycle)
						counts =pca.firedCounts(firedModeDelaysNeitherNorPleasantAffective());	
					return counts;
		}
								
		public ArrayList<Double> firedModeDelaysPleasantAffective() {				
			delaysPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getDelaysDecisionVariables().get(1);				
			delaysPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);		 				 
			return delaysPleasantAffectiveList	;			
		}
		public double getFiredPrivateDelaysPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedModeDelaysPleasantAffective());
			return privateAffective;
		}
		
		public double getFiredPublicDelaysPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedModeDelaysPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleDelaysPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedModeDelaysPleasantAffective());
			return cycleAffective;
		}
///cognitive counts by mode
		public int getFiredPrivateDelaysPleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof PersonalVehicle)
						counts =pca.firedCounts(firedModeDelaysPleasantAffective());	
					return counts;
		}			
				
		public int getFiredPublicDelaysPleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof PublicTransport)
						counts  = pca.firedCounts(firedModeDelaysPleasantAffective());		
					return counts ;
		}
		public int getFiredCycleDelaysPleasantAffectiveCounts(){
					int counts =0;
					if(prefferedMode instanceof Cycle)
						counts =pca.firedCounts(firedModeDelaysPleasantAffective());	
					return counts;
		}
}
