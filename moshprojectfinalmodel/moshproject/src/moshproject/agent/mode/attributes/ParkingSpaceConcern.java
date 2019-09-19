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

public class ParkingSpaceConcern {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 
	
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;
	private double parkingSpaceCognitive;
	private double privateParkingSpaceConcernCognitive;
	private double parkingSpaceAffective;
	private double privateParkingSpaceConcernAffective;
	private double parkingSpacePhysical;
	private double privateParkingSpaceConcernPhysical;
	private double modeParkingSpaceConcern;
	private double privateParkingSpaceConcern;
	private Vector modeParkingSpaceConcernDecisionVariables;
	private ArrayList<Double> parkingSpaceCognitiveList;
	private ArrayList<Double> parkingSpaceUnpleasentCognitiveList;
	private ArrayList<Double> parkingSpaceNeitherNorPleasantCognitiveList;
	private ArrayList<Double> parkingSpacePleasantCognitiveList;
	private ArrayList<Double> parkingSpaceUnpleasentPhysicalList;
	private ArrayList<Double> parkingSpaceNeitherNorPleasantPhysicalList;
	private ArrayList<Double> parkingSpacePleasantPhysicalList;
	private ArrayList<Double> parkingSpaceUnpleasentAffectiveList;
	private ArrayList<Double> parkingSpaceNeitherNorPleasantAffectiveList;
	private ArrayList<Double> parkingSpacePleasantAffectiveList;
	private ArrayList<Double> parkingSpaceAffectiveList;
	private ArrayList<Double> parkingSpacePhysicalList;
	private double cycleParkingSpaceConcern;	
	
	public ParkingSpaceConcern(Mode prefferedMode){
		this.prefferedMode = prefferedMode;
		updateParkingSpaceConcern();
		pca= new FuzzyDecisionVariables();	
	}
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	
	private double updateParkingSpaceCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			parkingSpaceCognitive = prefferedMode.getValueOfAttribute(Constants.parkingSpaceConcernPerception);				
		}else if (prefferedMode instanceof Cycle){
			parkingSpaceCognitive = prefferedMode.getValueOfAttribute(Constants.parkingSpaceConcernPerception);								
		}			
		return parkingSpaceCognitive;			
	}	
	public double getParkingSpaceCognitive() {
		updateParkingSpaceCognitive();
		return parkingSpaceCognitive;
	}
	private double updateParkingSpaceAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			parkingSpaceAffective = prefferedMode.getValueOfAttribute(Constants.parkingSpaceConcernAffective);		
		}else if (prefferedMode instanceof Cycle){
			parkingSpaceAffective = prefferedMode.getValueOfAttribute(Constants.parkingSpaceConcernAffective);								
		}				
		return parkingSpaceAffective;			
	}	
	public double getParkingSpaceAffective() {
		updateParkingSpaceAffective();
		return parkingSpaceAffective;
	}
	private double updateParkingSpacePhysical() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			parkingSpacePhysical = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);		
		}else if (prefferedMode instanceof Cycle){
			parkingSpacePhysical = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);								
		}				
		return parkingSpacePhysical;			
	}

	public double getParkingSpacePhysical() {
		updateParkingSpacePhysical();
		return parkingSpacePhysical;
	}
		////////The Fuzzy System Version
		public Vector modeParkingSpaceConcernValues() {	
			evaluateThreeVariablesSatisfaction = new FuzzyDecisionGenerator();
			if (prefferedMode instanceof PersonalVehicle){
				getParkingSpaceCognitive();
				getParkingSpacePhysical();
				getParkingSpaceAffective();
			}else if (prefferedMode instanceof Cycle){
				getParkingSpaceCognitive();
				getParkingSpacePhysical();
				getParkingSpaceAffective();
			}
			modeParkingSpaceConcernDecisionVariables =	evaluateThreeVariablesSatisfaction.getTip(getParkingSpacePhysical(),getParkingSpaceCognitive(),getParkingSpaceAffective());			
			return modeParkingSpaceConcernDecisionVariables; 
		}
		public Vector getModeParkingSpaceConcernDecisionVariables() {
			return modeParkingSpaceConcernDecisionVariables;
		}
///This method returns either fuzzy method or the ordinary method
		public double updateParkingSpaceConcern(){				
			 modeParkingSpaceConcernValues();	
			 modeParkingSpaceConcern =(double) getModeParkingSpaceConcernDecisionVariables().get(0);		
			return modeParkingSpaceConcern ;
		}		
		public double getModeParkingSpaceConcern() {
			return modeParkingSpaceConcern;
		}
		public double getPrivateParkingSpaceConcern(){
			if(prefferedMode instanceof PersonalVehicle){
				privateParkingSpaceConcern = this.getModeParkingSpaceConcern();
			}
			return privateParkingSpaceConcern;
		}	
		public double getCycleParkingSpaceConcern(){
			if(prefferedMode instanceof Cycle){
				cycleParkingSpaceConcern = this.getModeParkingSpaceConcern();
			}
			return cycleParkingSpaceConcern;
		}	
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedParkingSpaceConcernCognitive() {
			parkingSpaceCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);
			parkingSpaceCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);						 
			return parkingSpaceCognitiveList	;			
		}
		
		public double getFiredPrivateParkingSpaceConcernCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedParkingSpaceConcernCognitive());
			return privateCognitive;
		}
		public double getFiredCycleParkingSpaceConcernCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedParkingSpaceConcernCognitive());
			return cycleCognitive;
		}
		public int getFiredPrivateParkingSpaceConcernCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedParkingSpaceConcernCognitive());	
			return counts;
		}		
		public int getFiredCycleParkingSpaceConcernCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedParkingSpaceConcernCognitive());	
			return counts;
		}
			
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedParkingSpaceConcernUnpleasantCognitive() {				
			parkingSpaceUnpleasentCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpaceUnpleasentCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);				 
			return parkingSpaceUnpleasentCognitiveList	;			
		}
////Unpleasant
		public double getFiredPrivateParkingSpaceConcernUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedParkingSpaceConcernUnpleasantCognitive());
			return privateCognitive;
		}
		public double getFiredCycleParkingSpaceConcernUnpleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedParkingSpaceConcernUnpleasantCognitive());
			return cycleCognitive;
		}
////Unpleasant Count
		public int getFiredPrivateParkingSpaceConcernUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedParkingSpaceConcernUnpleasantCognitive());	
			return counts;
		}		
		public int getFiredCycleParkingSpaceConcernUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedParkingSpaceConcernUnpleasantCognitive());	
			return counts;
		}
///////////
		public ArrayList<Double> firedParkingSpaceConcernNeitherNorPleasantCognitive() {				
			parkingSpaceNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpaceNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);						 
			return parkingSpaceNeitherNorPleasantCognitiveList	;			
		}
/////Neither Nor Pleasant Cognitive
		public double getFiredPrivateParkingSpaceConcernNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedParkingSpaceConcernNeitherNorPleasantCognitive());
			return privateCognitive;
		}
		public double getFiredCycleParkingSpaceConcernNeitherNorPleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedParkingSpaceConcernNeitherNorPleasantCognitive());
			return cycleCognitive;
		}
////Neither Nor pleasant Count
		public int getFiredPrivateParkingSpaceConcernNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedParkingSpaceConcernNeitherNorPleasantCognitive());	
			return counts;
		}		
		public int getFiredCycleParkingSpaceConcernNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedParkingSpaceConcernNeitherNorPleasantCognitive());	
			return counts;
		}
		
		public ArrayList<Double> firedParkingSpaceConcernPleasantCognitive() {				
			parkingSpacePleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpacePleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);						 
			return parkingSpacePleasantCognitiveList	;			
		}
/////Pleasant
		public double getFiredPrivateParkingSpaceConcernPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedParkingSpaceConcernPleasantCognitive());
			return privateCognitive;
		}
		public double getFiredCycleParkingSpaceConcernPleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedParkingSpaceConcernPleasantCognitive());
			return cycleCognitive;
		}
////Pleasant Count
		public int getFiredPrivateParkingSpaceConcernPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedParkingSpaceConcernPleasantCognitive());	
			return counts;
		}		
		public int getFiredCycleParkingSpaceConcernPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedParkingSpaceConcernPleasantCognitive());	
			return counts;
		}
			
///////The following are the list of individual level of PCA-PHYSICAL
		public ArrayList<Double> firedParkingSpacePhysical() {
			parkingSpacePhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);
			parkingSpacePhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);
		//	System.out.println("The Reliability Physical Values:");
			for (Double antecedents :parkingSpacePhysicalList){
		//		System.out.println(antecedents);
			} 				 
			return parkingSpacePhysicalList	;			
		}

		public ArrayList<Double> firedParkingSpaceConcernUnpleasantPhysical() {				
			parkingSpaceUnpleasentPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpaceUnpleasentPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);
		//	System.out.println("The Unpleasant parkingSpace Physical Values:");
			for (Double antecedents :parkingSpaceUnpleasentPhysicalList){
		//	System.out.println(antecedents);
			} 			 
			return parkingSpaceUnpleasentPhysicalList;			
		}
			
		public ArrayList<Double> firedParkingSpaceConcernyNeitherNorPleasantPhysical() {				
			parkingSpaceNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpaceNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);
		//	System.out.println("The Neither pleasant nor unpleasant Reliability Physical Values:");
			for (Double antecedents :parkingSpaceNeitherNorPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return parkingSpaceNeitherNorPleasantPhysicalList;			
		}
				
		public ArrayList<Double> firedParkingSpaceConcernPleasantPhysical() {				
			parkingSpacePleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpacePleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);
		//	System.out.println("The Pleasant parkingSpace Physical Values:");
			for (Double antecedents :parkingSpacePleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return parkingSpacePleasantPhysicalList	;			
		}
				
///////The following are the list of individual level of PCA-AFFECTIVE
		public ArrayList<Double> firedParkingSpaceConcernAffective() {
			parkingSpaceAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);
			parkingSpaceAffectiveList =(ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);
			return parkingSpaceAffectiveList	;			
		}
	
		public double getFiredPrivateParkingSpaceAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedParkingSpaceConcernAffective());
			return privateAffective;
		}
		public double getFiredCycleParkingSpaceAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedParkingSpaceConcernAffective());
			return cycleAffective;
		}
		public int getFiredPrivateParkingSpaceConcernAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedParkingSpaceConcernAffective());	
			return counts;
		}		
		public int getFiredCycleParkingSpaceConcernAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedParkingSpaceConcernAffective());	
			return counts;
		}	

		public ArrayList<Double> firedParkingSpaceConcernUnpleasantAffective() {				
			parkingSpaceUnpleasentAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpaceUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);					 
			return parkingSpaceUnpleasentAffectiveList;			
		}
//////Unpleasant
		public double getFiredPrivateParkingSpaceUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedParkingSpaceConcernUnpleasantAffective());
			return privateAffective;
		}
		public double getFiredCycleParkingSpaceUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedParkingSpaceConcernUnpleasantAffective());
			return cycleAffective;
		}
////////Unpleasant counts
		public int getFiredPrivateParkingSpaceConcernUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedParkingSpaceConcernUnpleasantAffective());	
			return counts;
		}		
		public int getFiredCycleParkingSpaceConcernUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedParkingSpaceConcernUnpleasantAffective());	
			return counts;
		}	
///////
		public ArrayList<Double> firedParkingSpaceConcernNeitherNorPleasantAffective() {				
			parkingSpaceNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpaceNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
			return parkingSpaceNeitherNorPleasantAffectiveList ;			
		}
/////Neither Nor
		public double getFiredPrivateParkingSpaceNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedParkingSpaceConcernNeitherNorPleasantAffective());
			return privateAffective;
		}
		public double getFiredCycleParkingSpaceNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedParkingSpaceConcernNeitherNorPleasantAffective());
			return cycleAffective;
		}
////////Neither Nor pleasant counts
		public int getFiredPrivateParkingSpaceConcernNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedParkingSpaceConcernNeitherNorPleasantAffective());	
			return counts;
		}		
		public int getFiredCycleParkingSpaceConcernNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedParkingSpaceConcernNeitherNorPleasantAffective());	
			return counts;
		}
								
		public ArrayList<Double> firedParkingSpaceConcernPleasantAffective() {				
			parkingSpacePleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeParkingSpaceConcernDecisionVariables().get(1);				
			parkingSpacePleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);		 				 
			return parkingSpacePleasantAffectiveList	;			
		}
///////Pleasant
		public double getFiredPrivateParkingSpacePleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedParkingSpaceConcernPleasantAffective());
			return privateAffective;
		}
		public double getFiredCycleParkingSpacePleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedParkingSpaceConcernPleasantAffective());
			return cycleAffective;
		}
////////pleasant counts
		public int getFiredPrivateParkingSpaceConcernPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedParkingSpaceConcernPleasantAffective());	
			return counts;
		}		
		public int getFiredCycleParkingSpaceConcernPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedParkingSpaceConcernPleasantAffective());	
			return counts;
		}

}
