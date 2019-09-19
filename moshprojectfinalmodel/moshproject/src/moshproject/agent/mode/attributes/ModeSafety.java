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

public class ModeSafety {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	
	private Vector modeReliabilityDecisionVariables;
	private double personalSafetyPhysical;
	private double personalSafetyCognitive;
	private double personalSafetyAffective;
	private double personalSafety;
	private double privatePersonalSafety;
	private double publicPersonalSafety;
	private double publicModeSafetyCognitive;
	private double privateModeSafetyCognitive;
	private double publicModeSafetyAffective;
	private double privateModeSafetyAffective;
	private Vector personalSafetyDecisionVariables;
	private ArrayList<Double> personalSafetyCognitiveList;
	private ArrayList<Double> personalSafetyUnpleasentCognitiveList;
	private ArrayList<Double> personalSafetyNeitherNorPleasantCognitiveList;
	private ArrayList<Double> personalSafetyPleasantCognitiveList;
	private ArrayList<Double> personalSafetyUnpleasantPhysicalList;
	private ArrayList<Double> personalSafetyNeitherNorPleasantPhysicalList;
	private ArrayList<Double> personalSafetyPleasantPhysicalList;
	private ArrayList<Double> personalSafetyUnpleasentAffectiveList;
	private ArrayList<Double> personalSafetyNeitherNorPleasantAffectiveList;
	private ArrayList<Double> personalSafetyPleasantAffectiveList;
	private ArrayList<Double> personalSafetyAffectiveList;
	private double cyclePersonalSafety;
	private double walkingPersonalSafety;
	
	public ModeSafety(Mode prefferedMode){
		this.prefferedMode =prefferedMode;
		pca= new FuzzyDecisionVariables();	
		updatePersonalSafety();		
	}	
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	private double updatePersonalSafetyPhysical() {					
		return personalSafetyPhysical=prefferedMode.setValueToAttribute(Constants.personalSafetyPhysical,0);			
	}	
	public double getPersonalSafetyPhysical() {
		updatePersonalSafetyPhysical();
		return personalSafetyPhysical;
	}

	private double updatePersonalSafetyCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			personalSafetyCognitive = prefferedMode.getValueOfAttribute(Constants.personalSafetyPerception);					
		}else if (prefferedMode instanceof PublicTransport){
			personalSafetyCognitive = prefferedMode.getValueOfAttribute(Constants.personalSafetyPerception);											
		}else if (prefferedMode instanceof Cycle){
			personalSafetyCognitive = prefferedMode.getValueOfAttribute(Constants.personalSafetyPerception);								
		}else if (prefferedMode instanceof Walking){
			personalSafetyCognitive = prefferedMode.getValueOfAttribute(Constants.personalSafetyPerception);													
		}		
		return personalSafetyCognitive;			
	}
	
	public double getPersonalSafetyCognitive() {
		updatePersonalSafetyCognitive();
		return personalSafetyCognitive;
	}

	private double updatePersonalSafetyAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			personalSafetyAffective = prefferedMode.getValueOfAttribute(Constants.personalSafetyAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			personalSafetyAffective = prefferedMode.getValueOfAttribute(Constants.personalSafetyAffective);											
		}else if (prefferedMode instanceof Cycle){
			personalSafetyAffective = prefferedMode.getValueOfAttribute(Constants.personalSafetyAffective);								
		}else if (prefferedMode instanceof Walking){
			personalSafetyAffective = prefferedMode.getValueOfAttribute(Constants.personalSafetyAffective);													
		}			
		return personalSafetyAffective;			
	}	
	
	public double getPersonalSafetyAffective() {
		updatePersonalSafetyAffective() ;
		return personalSafetyAffective;
	}

		////////The Fuzzy System Version
		public Vector personalSafetyValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();
			if(prefferedMode instanceof PersonalVehicle){
				getPersonalSafetyAffective();
				getPersonalSafetyCognitive();
				getPersonalSafetyPhysical();
			}else if (prefferedMode instanceof PublicTransport){
				getPersonalSafetyAffective();
				getPersonalSafetyCognitive();
				getPersonalSafetyPhysical();
			}else if (prefferedMode instanceof Cycle){
				getPersonalSafetyAffective();
				getPersonalSafetyCognitive();
				getPersonalSafetyPhysical();
			}else if (prefferedMode instanceof Walking){
				getPersonalSafetyAffective();
				getPersonalSafetyCognitive();
				getPersonalSafetyPhysical();
			}
			personalSafetyDecisionVariables =	evaluateThreeVariablesSatisfaction.getTip(getPersonalSafetyPhysical(),getPersonalSafetyCognitive(),getPersonalSafetyAffective());					
			return personalSafetyDecisionVariables;
		}
		public Vector getPersonalSafetyDecisionVariables() {
			return personalSafetyDecisionVariables;
		}
		///This method returns either fuzzy method or the ordinary method
		public double updatePersonalSafety(){				
			personalSafetyValues() ;	
			personalSafety =(double)getPersonalSafetyDecisionVariables().get(0);	
			return personalSafety ;
		}	
		public double getPersonalSafety() {
			return personalSafety;
		}
		
		public double getPrivatePersonalSafety(){
			if(prefferedMode instanceof PersonalVehicle){
				privatePersonalSafety = this.getPersonalSafety();
			}	
			return (double) privatePersonalSafety;
		}			
		public double getPublicPersonalSafety(){
			if(prefferedMode instanceof PublicTransport){
				publicPersonalSafety = this.getPersonalSafety();
			}		
			return (double) publicPersonalSafety;
		}
		public double getCyclePersonalSafety(){
			if(prefferedMode instanceof Cycle){
				cyclePersonalSafety = this.getPersonalSafety();
			}	
			return (double) cyclePersonalSafety;
		}			
		public double getWalkingPersonalSafety(){
			if(prefferedMode instanceof Walking){
				walkingPersonalSafety = this.getPersonalSafety();
			}		
			return (double) walkingPersonalSafety;
		}
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedPersonalSafetyCognitive() {
			personalSafetyCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getPersonalSafetyDecisionVariables().get(1);
			personalSafetyCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);						 
			return personalSafetyCognitiveList	;			
		}
		public double getFiredPrivatePersonalSafetyCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedPersonalSafetyCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicPersonalSafetyCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedPersonalSafetyCognitive());
			return publicCognitive;
		}
		public double getFiredCyclePersonalSafetyCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedPersonalSafetyCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingPersonalSafetyCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedPersonalSafetyCognitive());
			return walkingCognitive;
		}
////Counts
		public int getFiredPrivatePersonalSafetyCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSafetyCognitive());	
			return counts;
		}			
		public int getFiredPublicPersonalSafetyCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSafetyCognitive());		
			return counts ;
		}
		public int getFiredCyclePersonalSafetyCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSafetyCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSafetyCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSafetyCognitive());		
			return counts ;
		}
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedPersonalSafetyUnpleasantCognitive() {				
			personalSafetyUnpleasentCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyUnpleasentCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);					 
			return personalSafetyUnpleasentCognitiveList	;			
		}
		public double getFiredPrivatePersonalSafetyUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedPersonalSafetyUnpleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicPersonalSafetyUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedPersonalSafetyUnpleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCyclePersonalSafetyUnpleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedPersonalSafetyUnpleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingPersonalSafetyUnpleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedPersonalSafetyUnpleasantCognitive());
			return walkingCognitive;
		}
////Counts
		public int getFiredPrivatePersonalSafetyUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSafetyUnpleasantCognitive());	
			return counts;
		}			
		public int getFiredPublicPersonalSafetyUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSafetyUnpleasantCognitive());		
			return counts ;
		}
		public int getFiredCyclePersonalSafetyUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSafetyUnpleasantCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSafetyUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSafetyUnpleasantCognitive());		
			return counts ;
		}
		public ArrayList<Double> firedPersonalSafetyNeitherNorPleasantCognitive() {				
			personalSafetyNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);		 				 
			return personalSafetyNeitherNorPleasantCognitiveList	;			
		}
		public double getFiredPrivatePersonalSafetyNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedPersonalSafetyNeitherNorPleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicPersonalSafetyNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedPersonalSafetyNeitherNorPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCyclePersonalSafetyNeitherNorPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedPersonalSafetyNeitherNorPleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingPersonalSafetyNeitherNorPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedPersonalSafetyNeitherNorPleasantCognitive());
			return walkingCognitive;
		}
////Counts
		public int getFiredPrivatePersonalSafetyNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSafetyNeitherNorPleasantCognitive());	
			return counts;
		}			
		public int getFiredPublicPersonalSafetyNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSafetyNeitherNorPleasantCognitive());		
			return counts ;
		}
		public int getFiredCyclePersonalSafetyNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSafetyNeitherNorPleasantCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSafetyNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSafetyNeitherNorPleasantCognitive());		
			return counts ;
		}		
		public ArrayList<Double> firedPersonalSafetyPleasantCognitive() {				
			personalSafetyPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);		 				 
			return personalSafetyPleasantCognitiveList	;			
		}
		public double getFiredPrivatePersonalSafetyPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedPersonalSafetyPleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicPersonalSafetyPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedPersonalSafetyPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCyclePersonalSafetyPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedPersonalSafetyPleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingPersonalSafetyPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedPersonalSafetyPleasantCognitive());
			return walkingCognitive;
		}
////Counts
		public int getFiredPrivatePersonalSafetyPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSafetyPleasantCognitive());	
			return counts;
		}			
		public int getFiredPublicPersonalSafetyPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSafetyPleasantCognitive());		
			return counts ;
		}
		public int getFiredCyclePersonalSafetyPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSafetyPleasantCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSafetyPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSafetyPleasantCognitive());		
			return counts ;
		}
			
///////The following are the list of individual level of PCA-PHYSICAL
		public ArrayList<Double> firedPersonalSafetyUnpleasantPhysical() {				
			personalSafetyUnpleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyUnpleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);
		//	System.out.println("The Unpleasant personalSafety Physical Values:");
			for (Double antecedents :personalSafetyUnpleasantPhysicalList){
		//	System.out.println(antecedents);
			} 			 
			return personalSafetyUnpleasantPhysicalList;			
		}
			
		public ArrayList<Double> firedPersonalSafetyNeitherNorPleasantPhysical() {				
			personalSafetyNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);
		//	System.out.println("The Neither pleasant nor unpleasant personalSafety Physical Values:");
			for (Double antecedents :personalSafetyNeitherNorPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return personalSafetyNeitherNorPleasantPhysicalList;			
		}
				
		public ArrayList<Double> firedPersonalSafetyPleasantPhysical() {				
			personalSafetyPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);
		//	System.out.println("The Pleasant personalSafety Physical Values:");
			for (Double antecedents :personalSafetyPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return personalSafetyPleasantPhysicalList	;			
		}
				
///////The following are the list of individual level of PCA-AFFECTIVE		
		public ArrayList<Double> firedPersonalSafetyAffective() {
			personalSafetyAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSafetyDecisionVariables().get(1);		
			personalSafetyAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);		 				 
			return personalSafetyAffectiveList	;			
		}
		public double getFiredPrivatePersonalSafetyAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedPersonalSafetyAffective());
			return privateAffective;
		}		
		public double getFiredPublicPersonalSafetyAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedPersonalSafetyAffective());
			return publicAffective;
		}
		public double getFiredCyclePersonalSafetyAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof  Cycle)
				cycleAffective = pca.firedTravelDemands(firedPersonalSafetyAffective());
			return cycleAffective;
		}
		public double getFiredWalkingPersonalSafetyAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedPersonalSafetyAffective());
			return walkingAffective;
		}
///Counts
		public int getFiredPrivatePersonalSafetyAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSafetyAffective());	
			return counts;
		}			
		public int getFiredPublicPersonalSafetyAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSafetyAffective());		
			return counts ;
		}
		public int getFiredCyclePersonalSafetyAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSafetyAffective());	
			return counts;
		}			
		public int getFiredWalkingPersonalSafetyAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSafetyAffective());		
			return counts ;
		}		
		public ArrayList<Double> firedPersonalSafetyUnpleasantAffective() {				
			personalSafetyUnpleasentAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);					 
			return personalSafetyUnpleasentAffectiveList;			
		}
		public double getFiredPrivatePersonalSafetyUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedPersonalSafetyUnpleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicPersonalSafetyUnpleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedPersonalSafetyUnpleasantAffective());
			return publicAffective;
		}
		public double getFiredCyclePersonalSafetyUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof  Cycle)
				cycleAffective = pca.firedTravelDemands(firedPersonalSafetyUnpleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingPersonalSafetyUnpleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedPersonalSafetyUnpleasantAffective());
			return walkingAffective;
		}
///Counts
		public int getFiredPrivatePersonalSafetyUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSafetyUnpleasantAffective());	
			return counts;
		}			
		public int getFiredPublicPersonalSafetyUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSafetyUnpleasantAffective());		
			return counts ;
		}
		public int getFiredCyclePersonalSafetyUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSafetyUnpleasantAffective());	
			return counts;
		}			
		public int getFiredWalkingPersonalSafetyUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSafetyUnpleasantAffective());		
			return counts ;
		}							
		public ArrayList<Double> firedPersonalSafetyNeitherNorPleasantAffective() {				
			personalSafetyNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
			return personalSafetyNeitherNorPleasantAffectiveList ;			
		}
		public double getFiredPrivatePersonalSafetyNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedPersonalSafetyNeitherNorPleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicPersonalSafetyNeitherNorPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedPersonalSafetyNeitherNorPleasantAffective());
			return publicAffective;
		}
		public double getFiredCyclePersonalSafetyNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof  Cycle)
				cycleAffective = pca.firedTravelDemands(firedPersonalSafetyNeitherNorPleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingPersonalSafetyNeitherNorPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedPersonalSafetyNeitherNorPleasantAffective());
			return walkingAffective;
		}
///Counts
		public int getFiredPrivatePersonalSafetyNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSafetyNeitherNorPleasantAffective());	
			return counts;
		}			
		public int getFiredPublicPersonalSafetyNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSafetyNeitherNorPleasantAffective());		
			return counts ;
		}
		public int getFiredCyclePersonalSafetyNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSafetyNeitherNorPleasantAffective());	
			return counts;
		}			
		public int getFiredWalkingPersonalSafetyNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSafetyNeitherNorPleasantAffective());		
			return counts ;
		}							
		public ArrayList<Double> firedPersonalSafetyPleasantAffective() {				
			personalSafetyPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getPersonalSafetyDecisionVariables().get(1);				
			personalSafetyPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);						 
			return personalSafetyPleasantAffectiveList	;			
		}
		public double getFiredPrivatePersonalSafetyPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedPersonalSafetyPleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicPersonalSafetyPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedPersonalSafetyPleasantAffective());
			return publicAffective;
		}
		public double getFiredCyclePersonalSafetyPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof  Cycle)
				cycleAffective = pca.firedTravelDemands(firedPersonalSafetyPleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingPersonalSafetyPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedPersonalSafetyPleasantAffective());
			return walkingAffective;
		}
///Counts
		public int getFiredPrivatePersonalSafetyPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSafetyPleasantAffective());	
			return counts;
		}			
		public int getFiredPublicPersonalSafetyPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSafetyPleasantAffective());		
			return counts ;
		}
		public int getFiredCyclePersonalSafetyPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSafetyPleasantAffective());	
			return counts;
		}			
		public int getFiredWalkingPersonalSafetyPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSafetyPleasantAffective());		
			return counts ;
		}
}
