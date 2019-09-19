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

public class ProtectionFromElements {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	
	private Vector modeReliabilityDecisionVariables;
	private double protectionFromElementsPhysical;
	private double protectionFromElementsCognitive;
	private double protectionFromElementsAffective;
	private double protectionFromElements;
	private double privateProtectionFromElements;
	private double publicProtectionFromElements;
	private double publicModeProtectionFromElementsCognitive;
	private double privateModeProtectionFromElementsCognitive;
	private double publicModeProtectionFromElementsAffective;
	private double privateModeProtectionFromElementsAfffective;
	private Vector protectionFromElementsDecisionVariables;
	private ArrayList<Double> elementsProtectionCognitiveList;
	private ArrayList<Double> elementsProtectionUnpleasentCognitiveList;
	private ArrayList<Double> elementsProtectionNeitherNorPleasantCognitiveList;
	private ArrayList<Double> elementsProtectionPleasantCognitiveList;
	private ArrayList<Double> elementsProtectionUnpleasentPhysicalList;
	private ArrayList<Double> elementsProtectionNeitherNorPleasantPhysicalList;
	private ArrayList<Double> elementsProtectionPleasantPhysicalList;
	private ArrayList<Double> elementsProtectionUnpleasentAffectiveList;
	private ArrayList<Double> elementsProtectionNeitherNorPleasantAffectiveList;
	private ArrayList<Double> elementsProtectionPleasantAffectiveList;
	private ArrayList<Double> protectionFromElementsAffectiveList;
	private double cycleProtectionFromElements;
	private double walkingProtectionFromElements;
	
	public ProtectionFromElements(Mode prefferedMode){
		this.prefferedMode = prefferedMode;
		updateProtectionFromElements();
		pca= new FuzzyDecisionVariables();	
	}
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	
	private double updateProtectionFromElementsPhysical() {			
		return protectionFromElementsPhysical =prefferedMode.setValueToAttribute(Constants.protectionFromElementsPhysical, 0);	
	}
	
	public double getProtectionFromElementsPhysical() {
		updateProtectionFromElementsPhysical();
		return protectionFromElementsPhysical;
	}
	private double updateProtectionFromElementsCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			protectionFromElementsCognitive = prefferedMode.getValueOfAttribute(Constants.protectionFromElementsPerception);					
		}else if (prefferedMode instanceof PublicTransport){
			protectionFromElementsCognitive = prefferedMode.getValueOfAttribute(Constants.protectionFromElementsPerception);											
		}else if (prefferedMode instanceof Cycle){
			protectionFromElementsCognitive = prefferedMode.getValueOfAttribute(Constants.protectionFromElementsPerception);								
		}else if (prefferedMode instanceof Walking){
			protectionFromElementsCognitive = prefferedMode.getValueOfAttribute(Constants.protectionFromElementsPerception);													
		}	
		return protectionFromElementsCognitive;			
	}		
	public double getProtectionFromElementsCognitive() {
		 updateProtectionFromElementsCognitive();
		return protectionFromElementsCognitive;
	}
	private double updateProtectionFromElementsAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			protectionFromElementsAffective = prefferedMode.getValueOfAttribute(Constants.protectionFromElementsAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			protectionFromElementsAffective = prefferedMode.getValueOfAttribute(Constants.protectionFromElementsAffective);											
		}else if (prefferedMode instanceof Cycle){
			protectionFromElementsAffective = prefferedMode.getValueOfAttribute(Constants.protectionFromElementsAffective);								
		}else if (prefferedMode instanceof Walking){
			protectionFromElementsAffective = prefferedMode.getValueOfAttribute(Constants.protectionFromElementsAffective);													
		}			
		return protectionFromElementsAffective;			
	}		
	public double getProtectionFromElementsAffective() {
		updateProtectionFromElementsAffective();
		return protectionFromElementsAffective;
	}
		////////The Fuzzy System Version
		public Vector modeProtecctionFromElementsValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();
			if(prefferedMode instanceof PersonalVehicle){
				getProtectionFromElementsAffective();
				getProtectionFromElementsCognitive();
				getProtectionFromElementsPhysical();
			}else if (prefferedMode instanceof PublicTransport){
				getProtectionFromElementsAffective();
				getProtectionFromElementsCognitive();
				getProtectionFromElementsPhysical();
			}else if (prefferedMode instanceof Cycle){
				getProtectionFromElementsAffective();
				getProtectionFromElementsCognitive();
				getProtectionFromElementsPhysical();
			}else if (prefferedMode instanceof Walking){
				getProtectionFromElementsAffective();
				getProtectionFromElementsCognitive();
				getProtectionFromElementsPhysical();
			}			
			protectionFromElementsDecisionVariables = evaluateThreeVariablesSatisfaction.getTip(getProtectionFromElementsPhysical(),getProtectionFromElementsCognitive(),getProtectionFromElementsAffective());			
			return protectionFromElementsDecisionVariables; 
		}
		public Vector getProtectionFromElementsDecisionVariables() {
			return protectionFromElementsDecisionVariables;
		}

		///This method returns either fuzzy method or the ordinary method
		public double updateProtectionFromElements(){				
			modeProtecctionFromElementsValues();	
			protectionFromElements=(double) getProtectionFromElementsDecisionVariables().get(0);	
			return protectionFromElements ;
		}
		public double getProtectionFromElements() {
			return protectionFromElements;
		}
		
		public double getPrivateProtectionFromElements(){
			if(prefferedMode instanceof PersonalVehicle){
				privateProtectionFromElements = this.getProtectionFromElements();
			}
			return (double) privateProtectionFromElements;
		}			
		public double getPublicProtectionFromElements(){
			if(prefferedMode instanceof PublicTransport){
				publicProtectionFromElements = this.getProtectionFromElements();
			}
			return (double) publicProtectionFromElements;
		}
		public double getCycleProtectionFromElements(){
			if(prefferedMode instanceof Cycle){
				cycleProtectionFromElements = this.getProtectionFromElements();
			}
			return (double) cycleProtectionFromElements;
		}			
		public double getWalkingProtectionFromElements(){
			if(prefferedMode instanceof Walking){
				walkingProtectionFromElements = this.getProtectionFromElements();
			}
			return (double) walkingProtectionFromElements;
		}
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedProtectionFromElementsCognitive() {
			elementsProtectionCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getProtectionFromElementsDecisionVariables().get(1);
			elementsProtectionCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);						 
			return elementsProtectionCognitiveList	;			
		}
		public double getFiredPrivateProtectionFromElementsCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedProtectionFromElementsCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicProtectionFromElementsCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedProtectionFromElementsCognitive());
			return publicCognitive;
		}
		public double getFiredCycleProtectionFromElementsCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedProtectionFromElementsCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingProtectionFromElementsCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Cycle)
				walkingCognitive = pca.firedTravelDemands(firedProtectionFromElementsCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivateProtectionFromElementsCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedProtectionFromElementsCognitive());			
			return counts;
		}
		public int getFiredPublicProtectionFromElementsCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts =pca.firedCounts(firedProtectionFromElementsCognitive());			
			return counts;
		}
		public int getFiredCycleProtectionFromElementsCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedProtectionFromElementsCognitive());			
			return counts;
		}
		public int getFiredWalkingProtectionFromElementsCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts =pca.firedCounts(firedProtectionFromElementsCognitive());			
			return counts;
		}
			
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedProtectionFromElementsUnpleasantCognitive() {				
			elementsProtectionUnpleasentCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionUnpleasentCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);					 
			return elementsProtectionUnpleasentCognitiveList	;			
		}
		public double getFiredPrivateProtectionFromElementsUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedProtectionFromElementsUnpleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicProtectionFromElementsUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedProtectionFromElementsUnpleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleProtectionFromElementsUnpleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedProtectionFromElementsUnpleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingProtectionFromElementsUnpleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Cycle)
				walkingCognitive = pca.firedTravelDemands(firedProtectionFromElementsUnpleasantCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivateProtectionFromElementsUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedProtectionFromElementsUnpleasantCognitive());			
			return counts;
		}
		public int getFiredPublicProtectionFromElementsUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts =pca.firedCounts(firedProtectionFromElementsUnpleasantCognitive());			
			return counts;
		}
		public int getFiredCycleProtectionFromElementsUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedProtectionFromElementsUnpleasantCognitive());			
			return counts;
		}
		public int getFiredWalkingProtectionFromElementsUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts =pca.firedCounts(firedProtectionFromElementsUnpleasantCognitive());			
			return counts;
		}	
		public ArrayList<Double> firedProtectionFromElementsNeitherNorPleasantCognitive() {				
			elementsProtectionNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);		 				 
			return elementsProtectionNeitherNorPleasantCognitiveList	;			
		}
		public double getFiredPrivateProtectionFromElementsNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedProtectionFromElementsNeitherNorPleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicProtectionFromElementsNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedProtectionFromElementsNeitherNorPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleProtectionFromElementsNeitherNorPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedProtectionFromElementsNeitherNorPleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingProtectionFromElementsNeitherNorPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Cycle)
				walkingCognitive = pca.firedTravelDemands(firedProtectionFromElementsNeitherNorPleasantCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivateProtectionFromElementsNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedProtectionFromElementsNeitherNorPleasantCognitive());			
			return counts;
		}
		public int getFiredPublicProtectionFromElementsNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts =pca.firedCounts(firedProtectionFromElementsNeitherNorPleasantCognitive());			
			return counts;
		}
		public int getFiredCycleProtectionFromElementsNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedProtectionFromElementsNeitherNorPleasantCognitive());			
			return counts;
		}
		public int getFiredWalkingProtectionFromElementsNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts =pca.firedCounts(firedProtectionFromElementsNeitherNorPleasantCognitive());			
			return counts;
		}
		public ArrayList<Double> firedProtectionFromElementsPleasantCognitive() {				
			elementsProtectionPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);		 				 
			return elementsProtectionPleasantCognitiveList	;			
		}
		public double getFiredPrivateProtectionFromElementsPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedProtectionFromElementsPleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicProtectionFromElementsPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedProtectionFromElementsPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleProtectionFromElementsPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedProtectionFromElementsPleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingProtectionFromElementsPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Cycle)
				walkingCognitive = pca.firedTravelDemands(firedProtectionFromElementsPleasantCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivateProtectionFromElementsPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedProtectionFromElementsPleasantCognitive());			
			return counts;
		}
		public int getFiredPublicProtectionFromElementsPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts =pca.firedCounts(firedProtectionFromElementsPleasantCognitive());			
			return counts;
		}
		public int getFiredCycleProtectionFromElementsPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedProtectionFromElementsPleasantCognitive());			
			return counts;
		}
		public int getFiredWalkingProtectionFromElementsPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts =pca.firedCounts(firedProtectionFromElementsPleasantCognitive());			
			return counts;
		}	
///////The following are the list of individual level of PCA-PHYSICAL
		public ArrayList<Double> firedProtectionFromElementsUnpleasantPhysical() {				
			elementsProtectionUnpleasentPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionUnpleasentPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);
		//	System.out.println("The Unpleasant elementsProtection Physical Values:");
			for (Double antecedents :elementsProtectionUnpleasentPhysicalList){
		//	System.out.println(antecedents);
			} 			 
			return elementsProtectionUnpleasentPhysicalList;			
		}
			
		public ArrayList<Double> firedProtectionFromElementsNeitherNorPleasantPhysical() {				
			elementsProtectionNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);
		//	System.out.println("The Neither pleasant nor unpleasant elementsProtection Physical Values:");
			for (Double antecedents :elementsProtectionNeitherNorPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return elementsProtectionNeitherNorPleasantPhysicalList;			
		}
				
		public ArrayList<Double> firedProtectionFromElementsPleasantPhysical() {				
			elementsProtectionPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);
		//	System.out.println("The Pleasant elementsProtection Physical Values:");
			for (Double antecedents :elementsProtectionPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return elementsProtectionPleasantPhysicalList	;			
		}
				
///////The following are the list of individual level of PCA-AFFECTIVE
		public ArrayList<Double> firedProtectionFromElementsAffective() {
			protectionFromElementsAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);
			protectionFromElementsAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);						 
			return protectionFromElementsAffectiveList	;			
		}

		public double getFiredPrivateProtectionFromElementsAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedProtectionFromElementsAffective());
			return privateAffective;
		}
		
		public double getFiredPublicProtectionFromElementsAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedProtectionFromElementsAffective());
			return publicAffective;
		}
		public double getFiredCycleProtectionFromElementsAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedProtectionFromElementsAffective());
			return cycleAffective;
		}
		public double getFiredWalkingProtectionFromElementsAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedProtectionFromElementsAffective());
			return walkingAffective;
		}
////Counts
		public int getFiredPrivateProtectionFromElementsAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedProtectionFromElementsAffective());			
			return counts;
		}
		public int getFiredPublicProtectionFromElementsAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts =pca.firedCounts(firedProtectionFromElementsAffective());			
			return counts;
		}
		public int getFiredCycleProtectionFromElementsAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedProtectionFromElementsAffective());			
			return counts;
		}
		public int getFiredWalkingProtectionFromElementsAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts =pca.firedCounts(firedProtectionFromElementsAffective());			
			return counts;
		}
		public ArrayList<Double> firedProtectionFromElementsUnpleasantAffective() {				
			elementsProtectionUnpleasentAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);		 			 
			return elementsProtectionUnpleasentAffectiveList;			
		}
		public double getFiredPrivateProtectionFromElementsUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedProtectionFromElementsUnpleasantAffective());
			return privateAffective;
		}
		
		public double getFiredPublicProtectionFromElementsUnpleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedProtectionFromElementsUnpleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleProtectionFromElementsUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedProtectionFromElementsUnpleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingProtectionFromElementsUnpleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedProtectionFromElementsUnpleasantAffective());
			return walkingAffective;
		}
////Counts
		public int getFiredPrivateProtectionFromElementsUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedProtectionFromElementsUnpleasantAffective());			
			return counts;
		}
		public int getFiredPublicProtectionFromElementsUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts =pca.firedCounts(firedProtectionFromElementsUnpleasantAffective());			
			return counts;
		}
		public int getFiredCycleProtectionFromElementsUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedProtectionFromElementsUnpleasantAffective());			
			return counts;
		}
		public int getFiredWalkingProtectionFromElementsUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts =pca.firedCounts(firedProtectionFromElementsUnpleasantAffective());			
			return counts;
		}							
		public ArrayList<Double> firedProtectionFromElementsNeitherNorPleasantAffective() {				
			elementsProtectionNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
			return elementsProtectionNeitherNorPleasantAffectiveList ;			
		}
		public double getFiredPrivateProtectionFromElementsNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedProtectionFromElementsNeitherNorPleasantAffective());
			return privateAffective;
		}
		
		public double getFiredPublicProtectionFromElementsNeitherNorPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedProtectionFromElementsNeitherNorPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleProtectionFromElementsNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedProtectionFromElementsNeitherNorPleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingProtectionFromElementsNeitherNorPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedProtectionFromElementsNeitherNorPleasantAffective());
			return walkingAffective;
		}
////Counts
		public int getFiredPrivateProtectionFromElementsNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedProtectionFromElementsNeitherNorPleasantAffective());			
			return counts;
		}
		public int getFiredPublicProtectionFromElementsNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts =pca.firedCounts(firedProtectionFromElementsNeitherNorPleasantAffective());			
			return counts;
		}
		public int getFiredCycleProtectionFromElementsNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedProtectionFromElementsNeitherNorPleasantAffective());			
			return counts;
		}
		public int getFiredWalkingProtectionFromElementsNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts =pca.firedCounts(firedProtectionFromElementsNeitherNorPleasantAffective());			
			return counts;
		}						
		public ArrayList<Double> firedProtectionFromElementsPleasantAffective() {				
			elementsProtectionPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getProtectionFromElementsDecisionVariables().get(1);				
			elementsProtectionPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);						 
			return elementsProtectionPleasantAffectiveList	;			
		}
		public double getFiredPrivateProtectionFromElementsPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedProtectionFromElementsPleasantAffective());
			return privateAffective;
		}
		
		public double getFiredPublicProtectionFromElementsPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedProtectionFromElementsPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleProtectionFromElementsPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedProtectionFromElementsPleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingProtectionFromElementsPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedProtectionFromElementsPleasantAffective());
			return walkingAffective;
		}
////Counts
		public int getFiredPrivateProtectionFromElementsPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedProtectionFromElementsPleasantAffective());			
			return counts;
		}
		public int getFiredPublicProtectionFromElementsPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts =pca.firedCounts(firedProtectionFromElementsPleasantAffective());			
			return counts;
		}
		public int getFiredCycleProtectionFromElementsPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedProtectionFromElementsPleasantAffective());			
			return counts;
		}
		public int getFiredWalkingProtectionFromElementsPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts =pca.firedCounts(firedProtectionFromElementsPleasantAffective());			
			return counts;
		}
	
}
