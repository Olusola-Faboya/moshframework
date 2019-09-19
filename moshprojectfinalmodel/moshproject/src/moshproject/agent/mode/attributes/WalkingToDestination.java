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

public class WalkingToDestination {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 	
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	
	private Vector modeReliabilityDecisionVariables;
	private double walkingToDestinationPhysical;
	private double walkingToDestinationCognitive;
	private double walkingToDestinationAffective;
	private double walkingToDestination;
	private double privateWalkingToDestination;
	private double publicWalkingToDestination;
	private double publicWalkingToDestinationCognitive;
	private double privateWalkingToDestinationCognitive;
	private double publicWalkingToDestinationAffective;
	private double privateWalkingToDestinationAffective;
	private double publicWalkingToDestinationPhysical;
	private double privateWalkingToDestinationPhysical;
	private Vector walkingToDestinationDecisionVariables;
	private ArrayList<Double> walkingToDestinationCognitiveList;
	private ArrayList<Double> walkingToDestinationUnpleasantCognitiveList;
	private ArrayList<Double> walkingToDestinationNeitherNorPleasantCognitiveList;
	private ArrayList<Double> walkingToDestinationPleasantCognitiveList;
	private ArrayList<Double> walkingToDestinationUnpleasantPhysicalList;
	private ArrayList<Double> walkingToDestinationNeitherNorPleasantPhysicalList;
	private ArrayList<Double> walkingToDestinationPleasantPhysicalList;
	private ArrayList<Double> walkingToDestinationUnpleasentAffectiveList;
	private ArrayList<Double> walkingToDestinationNeitherNorPleasantAffectiveList;
	private ArrayList<Double> walkingToDestinationPleasantAffectiveList;
	private ArrayList<Double> walkingToDestinationPhysicalList;
	private ArrayList<Double> walkingToDestinationAffectiveList;
	private double cycleWalkingToDestination;
		
	public WalkingToDestination(Mode prefferedMode){
		this.prefferedMode =prefferedMode;
		updateWalkingToDestination();
		pca= new FuzzyDecisionVariables();	
	}	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	private double updateWalkingToDestinationPhysical() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			walkingToDestinationPhysical = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);					
		}else if (prefferedMode instanceof PublicTransport){
			walkingToDestinationPhysical = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);											
		}else if (prefferedMode instanceof Cycle){
			walkingToDestinationPhysical = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);								
		}else if (prefferedMode instanceof Walking){
			walkingToDestinationPhysical = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);													
		}		
		return walkingToDestinationPhysical;			
	}		
	public double getWalkingToDestinationPhysical() {
		updateWalkingToDestinationPhysical();
		return walkingToDestinationPhysical;
	}
	private double updateWalkingToDestinationCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			walkingToDestinationCognitive = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);					
		}else if (prefferedMode instanceof PublicTransport){
			walkingToDestinationCognitive = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);											
		}else if (prefferedMode instanceof Cycle){
			walkingToDestinationCognitive = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);								
		}else if (prefferedMode instanceof Walking){
			walkingToDestinationCognitive = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationPerception);													
		}		
		return walkingToDestinationCognitive;			
	}
	
	public double getWalkingToDestinationCognitive() {
		updateWalkingToDestinationCognitive();
		return walkingToDestinationCognitive;
	}
	private double updateWalkingToDestinationAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			walkingToDestinationAffective = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			walkingToDestinationAffective = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationAffective);											
		}else if (prefferedMode instanceof Cycle){
			walkingToDestinationAffective = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationAffective);								
		}else if (prefferedMode instanceof Walking){
			walkingToDestinationAffective = prefferedMode.getValueOfAttribute(Constants.walkingToDestinationAffective);													
		}			
		return walkingToDestinationAffective;			
	}	
	public double getWalkingToDestinationAffective() {
		updateWalkingToDestinationAffective();
		return walkingToDestinationAffective;
	}
		////////The Fuzzy System Version
		public Vector walkingToDestinationValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();
			if(prefferedMode instanceof PersonalVehicle){
				getWalkingToDestinationAffective();
				getWalkingToDestinationCognitive();
				getWalkingToDestinationPhysical();
			}else if (prefferedMode instanceof PublicTransport){
				getWalkingToDestinationAffective();
				getWalkingToDestinationCognitive();
				getWalkingToDestinationPhysical();
			}else if (prefferedMode instanceof Cycle){
				getWalkingToDestinationAffective();
				getWalkingToDestinationCognitive();
				getWalkingToDestinationPhysical();
			}
			walkingToDestinationDecisionVariables =	evaluateThreeVariablesSatisfaction.getTip(getWalkingToDestinationPhysical(),getWalkingToDestinationCognitive(),getWalkingToDestinationAffective());	
			return walkingToDestinationDecisionVariables;
		}
		public Vector getWalkingToDestinationDecisionVariables() {
			return walkingToDestinationDecisionVariables;
		}
///This method returns either fuzzy method or the ordinary method
		public double updateWalkingToDestination(){				
			walkingToDestinationValues();	
			walkingToDestination =(double) getWalkingToDestinationDecisionVariables().get(0);	
			return walkingToDestination ;
		}	
		
		public double getWalkingToDestination() {
			return walkingToDestination;
		}		
		public double getPrivateWalkingToDestination(){
			if(prefferedMode instanceof PersonalVehicle){
				privateWalkingToDestination = this.getWalkingToDestination();
			}		
			return (double) privateWalkingToDestination;
		}			
		public double getPublicWalkingToDestination(){
			if(prefferedMode instanceof PublicTransport){
				publicWalkingToDestination = this.getWalkingToDestination();
			}
			return (double) publicWalkingToDestination;
		}
		public double getCycleWalkingToDestination(){
			if(prefferedMode instanceof Cycle){
				cycleWalkingToDestination = this.getWalkingToDestination();
			}
			return (double) cycleWalkingToDestination;
		}
		
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedWalkingToDestinationCognitive() {
			walkingToDestinationCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);
			walkingToDestinationCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);						 
			return walkingToDestinationCognitiveList	;			
		}
		public double getFiredPrivateWalkingToDestinationCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedWalkingToDestinationCognitive());
			return privateCognitive;
		}
		public double getFiredPublicWalkingToDestinationCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedWalkingToDestinationCognitive());
			return publicCognitive;
		}
		public double getFiredCycleWalkingToDestinationCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedWalkingToDestinationCognitive());
			return cycleCognitive;
		}
///Counts
		public int getFiredPrivateWalkingToDestinationCognitiveCounts(){
			int cognitiveCount =0;;
			if(prefferedMode instanceof PersonalVehicle)
				cognitiveCount =pca.firedCounts(firedWalkingToDestinationCognitive());// this.firedRelaibilityPhysicalcounts();		
			return cognitiveCount;
		}
		public int getFiredPublicWalkingToDestinationCognitiveCounts(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedWalkingToDestinationCognitive());// this.firedRelaibilityPhysicalcounts();		
			return publicPhyCount;
		}	
		public int getFiredCycleWalkingToDestinationCognitiveCounts(){
			int cycleCount =0;;
			if(prefferedMode instanceof Cycle)
				cycleCount =pca.firedCounts(firedWalkingToDestinationCognitive());// this.firedRelaibilityPhysicalcounts();		
			return cycleCount;
		}	
		
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedWalkingToDestinationUnpleasantCognitive() {				
			walkingToDestinationUnpleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);				
			walkingToDestinationUnpleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);		 			 
			return walkingToDestinationUnpleasantCognitiveList	;			
		}
		public double getFiredPrivateWalkingToDestinationUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedWalkingToDestinationUnpleasantCognitive());
			return privateCognitive;
		}
		public double getFiredPublicWalkingToDestinationUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedWalkingToDestinationUnpleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleWalkingToDestinationUnpleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedWalkingToDestinationUnpleasantCognitive());
			return cycleCognitive;
		}
///Counts
		public int getFiredPrivateWalkingToDestinationUnpleasantCognitiveCounts(){
			int cognitiveCount =0;;
			if(prefferedMode instanceof PersonalVehicle)
				cognitiveCount =pca.firedCounts(firedWalkingToDestinationUnpleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return cognitiveCount;
		}
		public int getFiredPublicWalkingToDestinationUnpleasantCognitiveCounts(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedWalkingToDestinationUnpleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return publicPhyCount;
		}	
		public int getFiredCycleWalkingToDestinationUnpleasantCognitiveCounts(){
			int cycleCount =0;;
			if(prefferedMode instanceof Cycle)
				cycleCount =pca.firedCounts(firedWalkingToDestinationUnpleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return cycleCount;
		}	
		
		public ArrayList<Double> firedWalkingToDestinationNeitherNorPleasantCognitive() {				
			walkingToDestinationNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);				
			walkingToDestinationNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);						 
			return walkingToDestinationNeitherNorPleasantCognitiveList	;			
		}
		public double getFiredPrivateWalkingToDestinationNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantCognitive());
			return privateCognitive;
		}
		public double getFiredPublicWalkingToDestinationNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleWalkingToDestinationNeitherNorPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantCognitive());
			return cycleCognitive;
		}
		public int getFiredPrivateWalkingToDestinationNeitherNorPleasantCognitiveCounts(){
			int cognitiveCount =0;;
			if(prefferedMode instanceof PersonalVehicle)
				cognitiveCount =pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return cognitiveCount;
		}
		public int getFiredPublicWalkingToDestinationNeitherNorPleasantCognitiveCounts(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return publicPhyCount;
		}	
		public int getFiredCycleWalkingToDestinationNeitherNorPleasantCognitiveCounts(){
			int cycleCount =0;;
			if(prefferedMode instanceof Cycle)
				cycleCount =pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return cycleCount;
		}	
		
		public ArrayList<Double> firedWalkingToDestinationPleasantCognitive() {				
			walkingToDestinationPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);				
			walkingToDestinationPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);
						 
			return walkingToDestinationPleasantCognitiveList	;			
		}
		public double getFiredPrivateWalkingToDestinationPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedWalkingToDestinationPleasantCognitive());
			return privateCognitive;
		}
		public double getFiredPublicWalkingToDestinationPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedWalkingToDestinationPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleWalkingToDestinationPleasantCognitive(){
			double cycleCognitive =0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedWalkingToDestinationPleasantCognitive());
			return cycleCognitive;
		}
///Counts
		public int getFiredPrivateWalkingToDestinationPleasantCognitiveCounts(){
			int cognitiveCount =0;;
			if(prefferedMode instanceof PersonalVehicle)
				cognitiveCount =pca.firedCounts(firedWalkingToDestinationPleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return cognitiveCount;
		}
		public int getFiredPublicWalkingToDestinationPleasantCognitiveCounts(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedWalkingToDestinationPleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return publicPhyCount;
		}	
		public int getFiredCycleWalkingToDestinationPleasantCognitiveCounts(){
			int cycleCount =0;;
			if(prefferedMode instanceof Cycle)
				cycleCount =pca.firedCounts(firedWalkingToDestinationPleasantCognitive());// this.firedRelaibilityPhysicalcounts();		
			return cycleCount;
		}
			
///////The following are the list of individual level of PCA-PHYSICAL
		public ArrayList<Double> firedWalkingToDestinationPhysical() {
			walkingToDestinationPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);
			walkingToDestinationPhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);						 
			return walkingToDestinationPhysicalList	;			
		}
		public double getFiredPrivateWalkingToDestinationPhysical(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedWalkingToDestinationPhysical());
			return privateCognitive;
		}		
		public double getFiredPublicWalkingToDestinationPhysical(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedWalkingToDestinationPhysical());
			return publicCognitive;
		}
		public double getFiredCycleWalkingToDestinationPhysical(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedWalkingToDestinationPhysical());
			return cycleCognitive;
		}
		public double getFiredWalkingUserWalkingToDestinationPhysical(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedWalkingToDestinationPhysical());
			return walkingCognitive;
		}
//////// AllCounts
		public int getFiredPrivateWalkingToDestinationPhysicalCounts(){
			int privatePhyCount =0;;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhyCount =pca.firedCounts(firedWalkingToDestinationPhysical());// this.firedRelaibilityPhysicalcounts();		
			return privatePhyCount;
		}
		public int getFiredPublicWalkingToDestinationPhysicalCounts(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedWalkingToDestinationPhysical());// this.firedRelaibilityPhysicalcounts();		
			return publicPhyCount;
		}	
		public int getFiredCycleWalkingToDestinationPhysicalCounts(){
			int cycleCount =0;;
			if(prefferedMode instanceof Cycle)
				cycleCount =pca.firedCounts(firedWalkingToDestinationPhysical());// this.firedRelaibilityPhysicalcounts();		
			return cycleCount;
		}	
		public int getFiredWalkingUserWalkingToDestinationPhysicalCounts(){
			int walkingCount =0;;
			if(prefferedMode instanceof Walking)
				walkingCount =pca.firedCounts(firedWalkingToDestinationPhysical());// this.firedRelaibilityPhysicalcounts();		
			return walkingCount;
		}
////PCA Level Counts
/////////Unpleasant Physical		
		public ArrayList<Double> firedWalkingToDestinationUnpleasantPhysical() {				
			walkingToDestinationUnpleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getWalkingToDestinationDecisionVariables().get(1);	
			walkingToDestinationUnpleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);	
			return walkingToDestinationUnpleasantPhysicalList;			
		}		
		public double getFiredPrivateWalkingToDestinationUnpleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedWalkingToDestinationUnpleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicWalkingToDestinationUnpleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedWalkingToDestinationUnpleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleWalkingToDestinationUnpleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof Cycle)
				cyclePhysical =pca.firedTravelDemands(firedWalkingToDestinationUnpleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingUSerWalkingToDestinationUnpleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof Walking)
				walkingPhysical = pca.firedTravelDemands(firedWalkingToDestinationUnpleasantPhysical());
			return walkingPhysical;
		}
////Unpleasant Counts		
		public int getFiredPrivateWalkingToDestinationUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedWalkingToDestinationUnpleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicWalkingToDestinationUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedWalkingToDestinationUnpleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleWalkingToDestinationUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedWalkingToDestinationUnpleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingUserWalkingToDestinationUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedWalkingToDestinationUnpleasantPhysical());			
			return counts ;
		}
/////////NEither Nor Pleasant
		public ArrayList<Double> firedWalkingToDestinationNeitherNorPleasantPhysical() {				
			walkingToDestinationNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);				
			walkingToDestinationNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);						 
			return walkingToDestinationNeitherNorPleasantPhysicalList;			
		}		
		public double getFiredPrivateWalkingToDestinationNeitherNorPleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicWalkingToDestinationNeitherNorPleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleWalkingToDestinationNeitherNorPleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof Cycle)
				cyclePhysical =pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingUserWalkingToDestinationNeitherNorPleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof Walking)
				walkingPhysical = pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantPhysical());
			return walkingPhysical;
		}
////Neither Nor Plesant Counts		
		public int getFiredPrivateWalkingToDestinationNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicWalkingToDestinationNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleWalkingToDestinationNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingUserWalkingToDestinationNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantPhysical());			
			return counts ;
		}
///Pleasant				
		public ArrayList<Double> firedWalkingToDestinationPleasantPhysical() {				
			walkingToDestinationPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);				
			walkingToDestinationPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);						 
			return walkingToDestinationPleasantPhysicalList	;			
		}
		public double getFiredPrivateWalkingToDestinationPleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedWalkingToDestinationPleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicWalkingToDestinationPleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedWalkingToDestinationPleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleWalkingToDestinationPleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof Cycle)
				cyclePhysical =pca.firedTravelDemands(firedWalkingToDestinationPleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingUSerWalkingToDestinationPleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof Walking)
				walkingPhysical = pca.firedTravelDemands(firedWalkingToDestinationPleasantPhysical());
			return walkingPhysical;
		}
////Pleasant Counts		
		public int getFiredPrivateWalkingToDestinationPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedWalkingToDestinationPleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicWalkingToDestinationPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedWalkingToDestinationPleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleWalkingToDestinationPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedWalkingToDestinationPleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingUserWalkingToDestinationPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedWalkingToDestinationPleasantPhysical());			
			return counts ;
		}
///////////////////////////////////////////////////////////				
///////The following are the list of individual level of PCA-AFFECTIVE
		public ArrayList<Double> firedWalkingToDestinationAffective() {
			walkingToDestinationAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);
			walkingToDestinationAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);					 
			return walkingToDestinationAffectiveList	;			
		}
		public double getFiredPrivateWalkingToDestinationAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedWalkingToDestinationAffective());
			return privateAffective;
		}
		public double getFiredPublicWalkingToDestinationAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedWalkingToDestinationAffective());
			return publicAffective;
		}
		public double getFiredCycleWalkingToDestinationAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedWalkingToDestinationAffective());
			return cycleAffective;
		}
		public double getFiredWalkingUserWalkingToDestinationAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedWalkingToDestinationAffective());
			return walkingAffective;
		}
///AllCounts
		public int getFiredPrivateWalkingToDestinationAffectiveCounts(){
			int privateCount =0;;
			if(prefferedMode instanceof PersonalVehicle)
				privateCount =pca.firedCounts(firedWalkingToDestinationAffective());// this.firedRelaibilityPhysicalcounts();		
			return privateCount;
		}
		public int getFiredPublicWalkingToDestinationAffectiveCounts(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedWalkingToDestinationAffective());// this.firedRelaibilityPhysicalcounts();		
			return publicPhyCount;
		}	
		public int getFiredCycleWalkingToDestinationAffectiveCounts(){
			int cycleCount =0;;
			if(prefferedMode instanceof Cycle)
				cycleCount =pca.firedCounts(firedWalkingToDestinationAffective());// this.firedRelaibilityPhysicalcounts();		
			return cycleCount;
		}	
		public int getFiredWalkingUserWalkingToDestinationAffectiveCounts(){
			int walkingCount =0;;
			if(prefferedMode instanceof Walking)
				walkingCount =pca.firedCounts(firedWalkingToDestinationAffective());// this.firedRelaibilityPhysicalcounts();		
			return walkingCount;
		}	
//// Unpleasant Affective
		public ArrayList<Double> firedWalkingToDestinationUnpleasantAffective() {				
			walkingToDestinationUnpleasentAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);				
			walkingToDestinationUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);					 
			return walkingToDestinationUnpleasentAffectiveList;			
		}
		public double getFiredPrivateWalkingToDestinationUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedWalkingToDestinationUnpleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicWalkingToDestinationUnpleasantAffective(){
			double publicAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedWalkingToDestinationUnpleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleWalkingToDestinationUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedWalkingToDestinationUnpleasantAffective());
			return cycleAffective;
		}		
		public double getFiredWalkingUserWalkingToDestinationUnpleasantAffective(){
			double walkingAffective =0;;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedWalkingToDestinationUnpleasantAffective());
			return walkingAffective;
		}
////Unpleasant Counts		
		public int getFiredPrivateWalkingToDestinationUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedWalkingToDestinationUnpleasantAffective());			
			return counts;
		}				
		public int getFiredPublicWalkingToDestinationUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedWalkingToDestinationUnpleasantAffective());			
			return counts ;
		}
		public int getFiredCycleWalkingToDestinationUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedWalkingToDestinationUnpleasantAffective());			
			return counts;
		}				
		public int getFiredWalkingUserWalkingToDestinationUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedWalkingToDestinationUnpleasantAffective());			
			return counts ;
		}
///////Neither Pleasant nor Unpleasant
		public ArrayList<Double> firedWalkingToDestinationNeitherNorPleasantAffective() {				
			walkingToDestinationNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);				
			walkingToDestinationNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);				 
			return walkingToDestinationNeitherNorPleasantAffectiveList ;			
		}		
		public double getFiredPrivateWalkingToDestinationNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicWalkingToDestinationNeitherNorPleasantAffective(){
			double publicAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleWalkingToDestinationNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantAffective());
			return cycleAffective;
		}		
		public double getFiredWalkingUserWalkingToDestinationNeitherNorPleasantAffective(){
			double walkingAffective =0;;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedWalkingToDestinationNeitherNorPleasantAffective());
			return walkingAffective;
		}
////Neither Pleasant Nor Unpleasant Counts		
		public int getFiredPrivateWalkingToDestinationNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantAffective());			
			return counts;
		}				
		public int getFiredPublicWalkingToDestinationNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantAffective());			
			return counts ;
		}
		public int getFiredCycleWalkingToDestinationNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantAffective());			
			return counts;
		}				
		public int getFiredWalkingUserWalkingToDestinationNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedWalkingToDestinationNeitherNorPleasantAffective());			
			return counts ;
		}
/////////Pleasant
		public ArrayList<Double> firedWalkingToDestinationPleasantAffective() {				
			walkingToDestinationPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getWalkingToDestinationDecisionVariables().get(1);				
			walkingToDestinationPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);						 
			return walkingToDestinationPleasantAffectiveList	;			
		}
		public double getFiredPrivateWalkingToDestinationPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedWalkingToDestinationPleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicWalkingToDestinationPleasantAffective(){
			double publicAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedWalkingToDestinationPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleWalkingToDestinationPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedWalkingToDestinationPleasantAffective());
			return cycleAffective;
		}		
		public double getFiredWalkingUserWalkingToDestinationPleasantAffective(){
			double walkingAffective =0;;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedWalkingToDestinationPleasantAffective());
			return walkingAffective;
		}
////Pleasant Counts		
		public int getFiredPrivateWalkingToDestinationPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedWalkingToDestinationPleasantAffective());			
			return counts;
		}				
		public int getFiredPublicWalkingToDestinationPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedWalkingToDestinationPleasantAffective());			
			return counts ;
		}
		public int getFiredCycleWalkingToDestinationPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedWalkingToDestinationPleasantAffective());			
			return counts;
		}				
		public int getFiredWalkingUserWalkingToDestinationPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedWalkingToDestinationPleasantAffective());			
			return counts ;
		}

}
