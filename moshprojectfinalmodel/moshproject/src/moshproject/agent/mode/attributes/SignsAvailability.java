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

public class SignsAvailability {
	
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	
	private Vector modeReliabilityDecisionVariables;
	private double signsAvailabilityPhysical;
	private double signsAvailabilityCognitive;
	private double signsAvailabilityAffective;
	private double signsAvailability;
	private double privateModeSignsAvail;
	private double publicModeSignsAvail;
	private double publicSignsAvailabilityCognitive;
	private double privateSignsAvailabilityCognitive;
	private double publicSignsAvailabilityAffective;
	private double privateSignsAvailabilityAffective;
	private Vector signsAvailabilityDecisionVariables;
	private ArrayList<Double> signsAvailabilityCognitiveList;
	private ArrayList<Double> signsAvailabilityUnpleasantCognitiveList;
	private ArrayList<Double> signsAvailabilityNeitherNorPleasantCognitiveList;
	private ArrayList<Double> signsAvailabilityPleasantCognitiveList;
	private ArrayList<Double> signsAvailabilityUnpleasentPhysicalList;
	private ArrayList<Double> signsAvailabilityNeitherNorPleasantPhysicalList;
	private ArrayList<Double> signsAvailabilityPleasantPhysicalList;
	private ArrayList<Double> signsAvailabilityUnpleasantAffectiveList;
	private ArrayList<Double> signsAvailabilityNeitherNorPleasantAffectiveList;
	private ArrayList<Double> signsAvailabilityPleasantAffectiveList;
	private ArrayList<Double> signsAvailabilityAffectiveList;
	private ArrayList<Double> signsAvailabilityPhysicalList;
	private double cycleSignsAvail;
	private double walkingSignsAvail;
	
	public SignsAvailability(Mode prefferedMode){
		this.prefferedMode=prefferedMode;
		updateSignsAvailability();
		pca= new FuzzyDecisionVariables();	
	}
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}

	private double updateSignsAvailabilityPhysical() {								
		return signsAvailabilityPhysical=prefferedMode.setValueToAttribute(Constants.signsAvailabilityPhysical, 0);			
	}		
	public double getSignsAvailabilityPhysical() {
		updateSignsAvailabilityPhysical();
		return signsAvailabilityPhysical;
	}
	private double updateSignsAvailabilityCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			signsAvailabilityCognitive = prefferedMode.getValueOfAttribute(Constants.signsAvailabilityPerception);					
		}else if (prefferedMode instanceof PublicTransport){
			signsAvailabilityCognitive = prefferedMode.getValueOfAttribute(Constants.signsAvailabilityPerception);											
		}else if (prefferedMode instanceof Cycle){
			signsAvailabilityCognitive = prefferedMode.getValueOfAttribute(Constants.signsAvailabilityPerception);								
		}else if (prefferedMode instanceof Walking){
			signsAvailabilityCognitive = prefferedMode.getValueOfAttribute(Constants.signsAvailabilityPerception);													
		}				
		return signsAvailabilityCognitive;			
	}	
	public double getSignsAvailabilityCognitive() {
		updateSignsAvailabilityCognitive();
		return signsAvailabilityCognitive;
	}
	private double updateSignsAvailabilityAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			signsAvailabilityAffective = prefferedMode.getValueOfAttribute(Constants.signsAvailabilityAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			signsAvailabilityAffective = prefferedMode.getValueOfAttribute(Constants.signsAvailabilityAffective);											
		}else if (prefferedMode instanceof Cycle){
			signsAvailabilityAffective = prefferedMode.getValueOfAttribute(Constants.signsAvailabilityAffective);								
		}else if (prefferedMode instanceof Walking){
			signsAvailabilityAffective = prefferedMode.getValueOfAttribute(Constants.signsAvailabilityAffective);													
		}			
		return signsAvailabilityAffective;			
	}		
	public double getSignsAvailabilityAffective() {
		updateSignsAvailabilityAffective();
		return signsAvailabilityAffective;
	}
		////////The Fuzzy System Version
		public Vector signsAvailabilityValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();
			if(prefferedMode instanceof PersonalVehicle){
				getSignsAvailabilityAffective();
				getSignsAvailabilityCognitive();				
				getSignsAvailabilityPhysical();
			}else if (prefferedMode instanceof PublicTransport){
				getSignsAvailabilityAffective();
				getSignsAvailabilityCognitive();				
				getSignsAvailabilityPhysical();
			}	else if (prefferedMode instanceof Cycle){
				getSignsAvailabilityAffective();
				getSignsAvailabilityCognitive();				
				getSignsAvailabilityPhysical();
			}else if (prefferedMode instanceof Walking){
				getSignsAvailabilityAffective();
				getSignsAvailabilityCognitive();				
				getSignsAvailabilityPhysical();
			}	
			signsAvailabilityDecisionVariables =	evaluateThreeVariablesSatisfaction.getTip(getSignsAvailabilityPhysical(),getSignsAvailabilityCognitive(),getSignsAvailabilityAffective());				
			return signsAvailabilityDecisionVariables;
		}
		public Vector getSignsAvailabilityDecisionVariables() {
			return signsAvailabilityDecisionVariables;
		}

		///This method returns either fuzzy method or the ordinary method
		public double updateSignsAvailability(){				
			signsAvailabilityValues();	
			signsAvailability =(double) getSignsAvailabilityDecisionVariables().get(0);	
			return signsAvailability ;
		}	
		public double getSignsAvailability() {
			return signsAvailability;
		}	
		public double getPrivateModeSignsAvailability(){
			if(prefferedMode instanceof PersonalVehicle){
				privateModeSignsAvail = this.getSignsAvailability();
			}
			return (double) privateModeSignsAvail;
		}			
		public double getPublicModeSignsAvailability(){
			if(prefferedMode instanceof PublicTransport){
				publicModeSignsAvail = this.getSignsAvailability();
			}
			return (double) publicModeSignsAvail;
		}
		public double getCycleModeSignsAvailability(){
			if(prefferedMode instanceof Cycle){
				cycleSignsAvail = this.getSignsAvailability();
			}
			return (double) cycleSignsAvail;
		}			
		public double getWalkingModeSignsAvailability(){
			if(prefferedMode instanceof Walking){
				walkingSignsAvail = this.getSignsAvailability();
			}
			return (double) walkingSignsAvail;
		}
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedSignsAvailabilityCognitive() {
			signsAvailabilityCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);
			signsAvailabilityCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);						 
			return signsAvailabilityCognitiveList	;			
		}
		public double getFiredPrivateSignsAvailabilityCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedSignsAvailabilityCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicSignsAvailabilityCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedSignsAvailabilityCognitive());
			return publicCognitive;
		}
		public double getFiredCycleSignsAvailabilityCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				cycleCognitive =pca.firedTravelDemands(firedSignsAvailabilityCognitive());
			return cycleCognitive;
		}
		
		public double getFiredWalkingSignsAvailabilityCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				walkingCognitive = pca.firedTravelDemands(firedSignsAvailabilityCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivateSignsAvailabilityCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityCognitive());			
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityCognitive());			
			return counts ;
		}
		public int getFiredCycleSignsAvailabilityCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityCognitive());			
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityCognitive());			
			return counts ;
		}
			
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedSignsAvailabilityUnpleasantCognitive() {				
			signsAvailabilityUnpleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityUnpleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);					 
			return signsAvailabilityUnpleasantCognitiveList	;			
		}
		public double getFiredPrivateSignsAvailabilityUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedSignsAvailabilityUnpleasantCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicSignsAvailabilityUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedSignsAvailabilityUnpleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleSignsAvailabilityUnpleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				cycleCognitive =pca.firedTravelDemands(firedSignsAvailabilityUnpleasantCognitive());
			return cycleCognitive;
		}
		
		public double getFiredWalkingSignsAvailabilityUnpleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				walkingCognitive = pca.firedTravelDemands(firedSignsAvailabilityUnpleasantCognitive());
			return walkingCognitive;
		}
		public int getFiredPrivateSignsAvailabilityUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityUnpleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityUnpleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleSignsAvailabilityUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityUnpleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityUnpleasantCognitive());			
			return counts ;
		}
		public ArrayList<Double> firedSignsAvailabilityNeitherNorPleasantCognitive() {				
			signsAvailabilityNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);		 				 
			return signsAvailabilityNeitherNorPleasantCognitiveList	;			
		}
		public double getFiredPrivateSignsAvailabilityNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedSignsAvailabilityNeitherNorPleasantCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicSignsAvailabilityNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedSignsAvailabilityNeitherNorPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleSignsAvailabilityNeitherNorPleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				cycleCognitive =pca.firedTravelDemands(firedSignsAvailabilityNeitherNorPleasantCognitive());
			return cycleCognitive;
		}
		
		public double getFiredWalkingSignsAvailabilityNeitherNorPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				walkingCognitive = pca.firedTravelDemands(firedSignsAvailabilityNeitherNorPleasantCognitive());
			return walkingCognitive;
		}
		public int getFiredPrivateSignsAvailabilityNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityNeitherNorPleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityNeitherNorPleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleSignsAvailabilityNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityNeitherNorPleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityNeitherNorPleasantCognitive());			
			return counts ;
		}
		public ArrayList<Double> firedSignsAvailabilityPleasantCognitive() {				
			signsAvailabilityPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);		 				 
			return signsAvailabilityPleasantCognitiveList	;			
		}
		public double getFiredPrivateSignsAvailabilityPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedSignsAvailabilityPleasantCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicSignsAvailabilityPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedSignsAvailabilityPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleSignsAvailabilityPleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				cycleCognitive =pca.firedTravelDemands(firedSignsAvailabilityPleasantCognitive());
			return cycleCognitive;
		}
		
		public double getFiredWalkingSignsAvailabilityPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				walkingCognitive = pca.firedTravelDemands(firedSignsAvailabilityPleasantCognitive());
			return walkingCognitive;
		}
		public int getFiredPrivateSignsAvailabilityPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityPleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityPleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleSignsAvailabilityPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityPleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityPleasantCognitive());			
			return counts ;
		}	
///////The following are the list of individual level of PCA-PHYSICAL		
		public ArrayList<Double> firedSignsAvailabilityPhysical() {
			signsAvailabilityPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);
			signsAvailabilityPhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);						 
			return signsAvailabilityPhysicalList	;			
		}
		public double getFiredPrivateSignsAvailabilityPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedSignsAvailabilityPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicSignsAvailabilityPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedSignsAvailabilityPhysical());
			return publicPhysical;
		}
		public double getFiredCycleSignsAvailabilityPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				cyclePhysical =pca.firedTravelDemands(firedSignsAvailabilityPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingSignsAvailabilityPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				walkingPhysical = pca.firedTravelDemands(firedSignsAvailabilityPhysical());
			return walkingPhysical;
		}
		public int getFiredPrivateSignsAvailabilityPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityPhysical());			
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityPhysical());			
			return counts ;
		}
		public int getFiredCycleSignsAvailabilityPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityPhysical());			
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityPhysical());			
			return counts ;
		}		
		public ArrayList<Double> firedSignsAvailabilityUnpleasantPhysical() {				
			signsAvailabilityUnpleasentPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityUnpleasentPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);					 
			return signsAvailabilityUnpleasentPhysicalList;			
		}
		public double getFiredPrivateSignsAvailabilityUnpleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedSignsAvailabilityUnpleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicSignsAvailabilityUnpleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedSignsAvailabilityUnpleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleSignsAvailabilityUnpleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				cyclePhysical =pca.firedTravelDemands(firedSignsAvailabilityUnpleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingSignsAvailabilityUnpleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				walkingPhysical = pca.firedTravelDemands(firedSignsAvailabilityUnpleasantPhysical());
			return walkingPhysical;
		}
		public int getFiredPrivateSignsAvailabilityUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityUnpleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityUnpleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleSignsAvailabilityUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityUnpleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityUnpleasantPhysical());			
			return counts ;
		}			
		public ArrayList<Double> firedSignsAvailabilityNeitherNorPleasantPhysical() {				
			signsAvailabilityNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);						 
			return signsAvailabilityNeitherNorPleasantPhysicalList;			
		}
		public double getFiredPrivateSignsAvailabilityNeitherNorPleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedSignsAvailabilityNeitherNorPleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicSignsAvailabilityNeitherNorPleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedSignsAvailabilityNeitherNorPleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleSignsAvailabilityNeitherNorPleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				cyclePhysical =pca.firedTravelDemands(firedSignsAvailabilityNeitherNorPleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingSignsAvailabilityNeitherNorPleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				walkingPhysical = pca.firedTravelDemands(firedSignsAvailabilityNeitherNorPleasantPhysical());
			return walkingPhysical;
		}
		public int getFiredPrivateSignsAvailabilityNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityNeitherNorPleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityNeitherNorPleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleSignsAvailabilityNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityNeitherNorPleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityNeitherNorPleasantPhysical());			
			return counts ;
		}	
		public ArrayList<Double> firedSignsAvailabilityPleasantPhysical() {				
			signsAvailabilityPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);						 
			return signsAvailabilityPleasantPhysicalList	;			
		}
		public double getFiredPrivateSignsAvailabilityPleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedSignsAvailabilityPleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicSignsAvailabilityPleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedSignsAvailabilityPleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleSignsAvailabilityPleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				cyclePhysical =pca.firedTravelDemands(firedSignsAvailabilityPleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingSignsAvailabilityPleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				walkingPhysical = pca.firedTravelDemands(firedSignsAvailabilityPleasantPhysical());
			return walkingPhysical;
		}
		public int getFiredPrivateSignsAvailabilityPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityPleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityPleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleSignsAvailabilityPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityPleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityPleasantPhysical());			
			return counts ;
		}		
///////The following are the list of individual level of PCA-AFFECTIVE		
		public ArrayList<Double> firedSignsAvailabilityAffective() {
			signsAvailabilityAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);
			signsAvailabilityAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);						 
			return signsAvailabilityAffectiveList	;			
		}
		public double getFiredPrivateSignsAvailabilityAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedSignsAvailabilityAffective());
			return privateAffective;
		}			
		public double getFiredPublicSignsAvailabilityAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedSignsAvailabilityAffective());
			return publicAffective;
		}
		public double getFiredCycleSignsAvailabilityAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedSignsAvailabilityAffective());
			return cycleAffective;
		}			
		public double getFiredWalkingSignsAvailabilityAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				walkingAffective = pca.firedTravelDemands(firedSignsAvailabilityAffective());
			return walkingAffective;
		}
///The Counts of all Affective that fired	
		public int getFiredPrivateSignsAvailabilityAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityAffective());
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityAffective());
			return counts ;
		}	
		public int getFiredCycleSignsAvailabilityAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityAffective());
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityAffective());
			return counts ;
		}		
		public ArrayList<Double> firedModeSignsAvailabilityUnpleasantAffective() {				
			signsAvailabilityUnpleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityUnpleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);		 			 
			return signsAvailabilityUnpleasantAffectiveList;			
		}
		public double getFiredPrivateSignsAvailabilityUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedModeSignsAvailabilityUnpleasantAffective());
			return privateAffective;
		}			
		public double getFiredPublicSignsAvailabilityUnpleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedModeSignsAvailabilityUnpleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleSignsAvailabilityUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedModeSignsAvailabilityUnpleasantAffective());
			return cycleAffective;
		}			
		public double getFiredWalkingSignsAvailabilityUnpleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				walkingAffective = pca.firedTravelDemands(firedModeSignsAvailabilityUnpleasantAffective());
			return walkingAffective;
		}
///The Counts of all Affective that fired	
		public int getFiredPrivateSignsAvailabilityUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedModeSignsAvailabilityUnpleasantAffective());
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedModeSignsAvailabilityUnpleasantAffective());
			return counts ;
		}	
		public int getFiredCycleSignsAvailabilityUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedModeSignsAvailabilityUnpleasantAffective());
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedModeSignsAvailabilityUnpleasantAffective());
			return counts ;
		}								
		public ArrayList<Double> firedModeSignsAvailabilityNeitherNorPleasantAffective() {				
			signsAvailabilityNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
			return signsAvailabilityNeitherNorPleasantAffectiveList ;			
		}
		public double getFiredPrivateSignsAvailabilityNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedModeSignsAvailabilityNeitherNorPleasantAffective());
			return privateAffective;
		}			
		public double getFiredPublicSignsAvailabilityNeitherNorPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedModeSignsAvailabilityNeitherNorPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleSignsAvailabilityNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedModeSignsAvailabilityNeitherNorPleasantAffective());
			return cycleAffective;
		}			
		public double getFiredWalkingSignsAvailabilityNeitherNorPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				walkingAffective = pca.firedTravelDemands(firedModeSignsAvailabilityNeitherNorPleasantAffective());
			return walkingAffective;
		}
///The Counts of all Affective that fired	
		public int getFiredPrivateSignsAvailabilityNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedModeSignsAvailabilityNeitherNorPleasantAffective());
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedModeSignsAvailabilityNeitherNorPleasantAffective());
			return counts ;
		}	
		public int getFiredCycleSignsAvailabilityNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedModeSignsAvailabilityNeitherNorPleasantAffective());
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedModeSignsAvailabilityNeitherNorPleasantAffective());
			return counts ;
		}							
		public ArrayList<Double> firedSignsAvailabilityPleasantAffective() {				
			signsAvailabilityPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getSignsAvailabilityDecisionVariables().get(1);				
			signsAvailabilityPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);					 
			return signsAvailabilityPleasantAffectiveList	;			
		}
		public double getFiredPrivateSignsAvailabilityPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedSignsAvailabilityPleasantAffective());
			return privateAffective;
		}			
		public double getFiredPublicSignsAvailabilityPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedSignsAvailabilityPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleSignsAvailabilityPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedSignsAvailabilityPleasantAffective());
			return cycleAffective;
		}			
		public double getFiredWalkingSignsAvailabilityPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				walkingAffective = pca.firedTravelDemands(firedSignsAvailabilityPleasantAffective());
			return walkingAffective;
		}
///The Counts of all Affective that fired	
		public int getFiredPrivateSignsAvailabilityPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedSignsAvailabilityPleasantAffective());
			return counts;
		}				
		public int getFiredPublicSignsAvailabilityPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedSignsAvailabilityPleasantAffective());
			return counts ;
		}	
		public int getFiredCycleSignsAvailabilityPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedSignsAvailabilityPleasantAffective());
			return counts;
		}				
		public int getFiredWalkingSignsAvailabilityPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedSignsAvailabilityPleasantAffective());
			return counts ;
		}					
}
