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

public class EaseOfGettingOnOffMode {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 	
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	

	private double easeOfGettingOnOffCognitive;
	private double easeOfGettingOnOffAffective;
	private double easeOfGettingOnOffPhysical;
	private double easeOfGettingOnOff;
	private double privateEaseOfGettingOnOff;
	private double publicEaseOfGettingOnOff;
	private double privateEaseOfGettingOnOffPhysical;
	private double publicEaseOfGettingOnOffPhysical;
	private double privateEaseOfGettingOnOffAffective;
	private double publicEaseOfGettingOnOffAffective;
	private double privateEaseOfGettingOnOffCognitive;
	private double publicEaseOfGettingOnOffCognitive;
	private Vector easeOfGettingOnOffDecisionVariables;
	private ArrayList<Double> easeOfGettingOnOffCognitiveList;
	private ArrayList<Double> easeOfGettingOnOffUnpleasentCognitiveList;
	private ArrayList<Double> easeOfGettingOnOffNeitherNorPleasantCognitiveList;
	private ArrayList<Double> easeOfGettingOnOffPleasantCognitiveList;
	private ArrayList<Double> easeOfGettingOnOffUnpleasantPhysicalList;
	private ArrayList<Double> easeOfGettingOnOffNeitherNorPleasantPhysicalList;
	private ArrayList<Double> easeOfGettingOnOffPleasantPhysicalList;
	private ArrayList<Double> easeOfGettingOnOffUnpleasentAffectiveList;
	private ArrayList<Double> easeOfGettingOnOffNeitherNorPleasantAffectiveList;
	private ArrayList<Double> easeOfGettingOnOffPleasantAffectiveList;
	private ArrayList<Double> easeOfGetOnOffPhysicalList;
	private ArrayList<Double> easeOfGetOnOffAffectiveList;
	private double cycleEaseOfGettingOnOff;
	private double walkingEaseOfGettingOnOff;
	
	public EaseOfGettingOnOffMode(Mode preferredMode){
		this.prefferedMode = preferredMode;	
		updateEaseOfGettingOnOffMode();
		pca= new FuzzyDecisionVariables();	
	}
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	
	private double updateEaseOfGettingOnOffCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			easeOfGettingOnOffCognitive = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModePerception);					
		}else if (prefferedMode instanceof PublicTransport){
			easeOfGettingOnOffCognitive = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModePerception);											
		}else if (prefferedMode instanceof Cycle){
			easeOfGettingOnOffCognitive = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModePerception);								
		}else if (prefferedMode instanceof Walking){
			easeOfGettingOnOffCognitive = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModePerception);													
		}				
		return easeOfGettingOnOffCognitive;			
	}	

	public double getEaseOfGettingOnOffCognitive() {
		updateEaseOfGettingOnOffCognitive() ;
		return easeOfGettingOnOffCognitive;
	}

	private double updateEaseOfGettingOnOffAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			easeOfGettingOnOffAffective = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModeAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			easeOfGettingOnOffAffective = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModeAffective);											
		}else if (prefferedMode instanceof Cycle){
			easeOfGettingOnOffAffective = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModeAffective);								
		}else if (prefferedMode instanceof Walking){
			easeOfGettingOnOffAffective = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModeAffective);													
		}			
		return easeOfGettingOnOffAffective;			
	}	
	
	public double getEaseOfGettingOnOffAffective() {
		updateEaseOfGettingOnOffAffective();
		return easeOfGettingOnOffAffective;
	}

	private double updateEaseOfGettingOnOffPhysical() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			easeOfGettingOnOffPhysical = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModePerception);					
		}else if (prefferedMode instanceof PublicTransport){
			easeOfGettingOnOffPhysical = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModePerception);											
		}else if (prefferedMode instanceof Cycle){
			easeOfGettingOnOffPhysical = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModePerception);								
		}else if (prefferedMode instanceof Walking){
			easeOfGettingOnOffPhysical = prefferedMode.getValueOfAttribute(Constants.gettingOnOffModePerception);													
		}			
		return easeOfGettingOnOffPhysical;			
	}	
	
	public double getEaseOfGettingOnOffPhysical() {
		updateEaseOfGettingOnOffPhysical();
		return easeOfGettingOnOffPhysical;
	}

		////////The Fuzzy System Version
		public Vector easeOfGettingOnOffValues() {	
			evaluateThreeVariablesSatisfaction = new FuzzyDecisionGenerator();
			if (prefferedMode instanceof PersonalVehicle){
				getEaseOfGettingOnOffCognitive();
				getEaseOfGettingOnOffPhysical();
				getEaseOfGettingOnOffAffective();
			}else if (prefferedMode instanceof PublicTransport){
				getEaseOfGettingOnOffCognitive();
				getEaseOfGettingOnOffPhysical();
				getEaseOfGettingOnOffAffective();
			}else if (prefferedMode instanceof Cycle){
				getEaseOfGettingOnOffCognitive();
				getEaseOfGettingOnOffPhysical();
				getEaseOfGettingOnOffAffective();
			}else if (prefferedMode instanceof Walking){
				getEaseOfGettingOnOffCognitive();
				getEaseOfGettingOnOffPhysical();
				getEaseOfGettingOnOffAffective();
			}				
			 easeOfGettingOnOffDecisionVariables = evaluateThreeVariablesSatisfaction.getTip(getEaseOfGettingOnOffPhysical(),getEaseOfGettingOnOffCognitive(),getEaseOfGettingOnOffAffective());			
			return easeOfGettingOnOffDecisionVariables;
		}
		public Vector getEaseOfGettingOnOffDecisionVariables() {
			return easeOfGettingOnOffDecisionVariables;
		}

		///This method returns either fuzzy method or the ordinary method
		public double updateEaseOfGettingOnOffMode(){				
			easeOfGettingOnOffValues();	
			easeOfGettingOnOff =(double) getEaseOfGettingOnOffDecisionVariables().get(0);	
			return easeOfGettingOnOff ;
		}			
		public double getEaseOfGettingOnOff() {
			return easeOfGettingOnOff;
		}
		public double getPrivateEaseOfGettingOnOff(){
			if(prefferedMode instanceof PersonalVehicle){
				privateEaseOfGettingOnOff = this.getEaseOfGettingOnOff();
			}
			return privateEaseOfGettingOnOff;
		}
		public double getPublicEaseOfGettingOnOff(){
			if(prefferedMode instanceof PublicTransport){
				publicEaseOfGettingOnOff = this.getEaseOfGettingOnOff();
			}	
			return publicEaseOfGettingOnOff;
		}
		public double getCycleEaseOfGettingOnOff(){
			if(prefferedMode instanceof Cycle){
				cycleEaseOfGettingOnOff = this.getEaseOfGettingOnOff();
			}
			return cycleEaseOfGettingOnOff;
		}
		public double getWalkingEaseOfGettingOnOff(){
			if(prefferedMode instanceof Walking){
				walkingEaseOfGettingOnOff = this.getEaseOfGettingOnOff();
			}	
			return walkingEaseOfGettingOnOff;
		}
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedEaseOfGettingOnOffCognitive() {
			easeOfGettingOnOffCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingOnOffDecisionVariables().get(1);
			easeOfGettingOnOffCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);					 
			return easeOfGettingOnOffCognitiveList	;			
		}	
		
		//What is this fo?????
		public double getFiredPrivateEaseOfGetOnOffCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedEaseOfGettingOnOffCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicEaseOfGetOnOffCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedEaseOfGettingOnOffCognitive());
			return publicCognitive;
		}
		public double getFiredCycleEaseOfGetOnOffCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedEaseOfGettingOnOffCognitive());
			return cycleCognitive;
		}		
		public double getFiredWalkingEaseOfGetOnOffCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedEaseOfGettingOnOffCognitive());
			return walkingCognitive;
		}
////Counts		
		public int getFiredPrivateEaseOfGetOnOffCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffCognitive());			
			return counts;
		}				
		public int getFiredPublicEaseOfGetOnOffCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffCognitive());			
			return counts ;
		}
		public int getFiredCycleEaseOfGetOnOffCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffCognitive());			
			return counts;
		}				
		public int getFiredWalkingEaseOfGetOnOffCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffCognitive());			
			return counts ;
		}
///////The following are the list of individual level of PCA-COGNITIVE
///Unpleasant
		public ArrayList<Double> firedEaseOfGettingOnOffUnpleasantCognitive() {				
			easeOfGettingOnOffUnpleasentCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingOnOffDecisionVariables().get(1);				
			easeOfGettingOnOffUnpleasentCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);				 
			return easeOfGettingOnOffUnpleasentCognitiveList	;			
		}	
		public double getFiredPrivateEaseOfGetOnOffUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands( firedEaseOfGettingOnOffUnpleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicEaseOfGetOnOffUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands( firedEaseOfGettingOnOffUnpleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleEaseOfGetOnOffUnpleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands( firedEaseOfGettingOnOffUnpleasantCognitive());
			return cycleCognitive;
		}		
		public double getFiredWalkingEaseOfGetOnOffUnpleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands( firedEaseOfGettingOnOffUnpleasantCognitive());
			return walkingCognitive;
		}
////Counts		
		public int getFiredPrivateEaseOfGetOnOffUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts( firedEaseOfGettingOnOffUnpleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicEaseOfGetOnOffUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts( firedEaseOfGettingOnOffUnpleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleEaseOfGetOnOffUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts( firedEaseOfGettingOnOffUnpleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingEaseOfGetOnOffUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts( firedEaseOfGettingOnOffUnpleasantCognitive());			
			return counts ;
		}
		
//////////Neither Nor Pleasant
		public ArrayList<Double> firedEaseOfGettingOnOffNeitherNorPleasantCognitive() {				
			easeOfGettingOnOffNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingOnOffDecisionVariables().get(1);				
			easeOfGettingOnOffNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);						 
			return easeOfGettingOnOffNeitherNorPleasantCognitiveList	;			
		}
///Neither Nor Pleasant
		public double getFiredPrivateEaseOfGetOnOffNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicEaseOfGetOnOffNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleEaseOfGetOnOffNeitherNorPleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantCognitive());
			return cycleCognitive;
		}		
		public double getFiredWalkingEaseOfGetOnOffNeitherNorPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantCognitive());
			return walkingCognitive;
		}
////Counts		
		public int getFiredPrivateEaseOfGetOnOffNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicEaseOfGetOnOffNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleEaseOfGetOnOffNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingEaseOfGetOnOffNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantCognitive());			
			return counts ;
		}
/////// Pleasant
		public ArrayList<Double> firedEaseOfGettingOnOffPleasantCognitive() {				
			easeOfGettingOnOffPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingOnOffDecisionVariables().get(1);				
			easeOfGettingOnOffPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);						 
			return easeOfGettingOnOffPleasantCognitiveList	;			
		}
		public double getFiredPrivateEaseOfGetOnOffPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicEaseOfGetOnOffPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleEaseOfGetOnOffPleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantCognitive());
			return cycleCognitive;
		}		
		public double getFiredWalkingEaseOfGetOnOffPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantCognitive());
			return walkingCognitive;
		}
////Counts		
		public int getFiredPrivateEaseOfGetOnOffPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffPleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicEaseOfGetOnOffPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffPleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleEaseOfGetOnOffPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffPleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingEaseOfGetOnOffPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffPleasantCognitive());			
			return counts ;
		}
///////The following are the list of individual level of PCA-PHYSICAL
		
		public ArrayList<Double> firedEaseOfGettingOnOffPhysical() {
			easeOfGetOnOffPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingOnOffDecisionVariables().get(1);
			easeOfGetOnOffPhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);					 
			return easeOfGetOnOffPhysicalList	;			
		}
		public double getFiredPrivateEaseOfGetOnOffPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedEaseOfGettingOnOffPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicEaseOfGetOnOffPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedEaseOfGettingOnOffPhysical());
			return publicPhysical;
		}
		public double getFiredCycleEaseOfGetOnOffPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof Cycle)
				cyclePhysical =pca.firedTravelDemands(firedEaseOfGettingOnOffPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingEaseOfGetOnOffPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof Walking)
				walkingPhysical = pca.firedTravelDemands(firedEaseOfGettingOnOffPhysical());
			return walkingPhysical;
		}
////Counts		
		public int getFiredPrivateEaseOfGetOnOffPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffPhysical());			
			return counts;
		}				
		public int getFiredPublicEaseOfGetOnOffPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffPhysical());			
			return counts ;
		}
		public int getFiredCycleEaseOfGetOnOffPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffPhysical());			
			return counts;
		}				
		public int getFiredWalkingEaseOfGetOnOffPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffPhysical());			
			return counts ;
		}
/////////Unpleasant Physical	
		public ArrayList<Double> firedEaseOfGettingOnOffUnpleasantPhysical() {				
			easeOfGettingOnOffUnpleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingOnOffDecisionVariables().get(1);	
			easeOfGettingOnOffUnpleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);	
			return easeOfGettingOnOffUnpleasantPhysicalList;			
		}
		public double getFiredPrivateEaseOfGetOnOffUnpleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedEaseOfGettingOnOffUnpleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicEaseOfGetOnOffUnpleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedEaseOfGettingOnOffUnpleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleEaseOfGetOnOffUnpleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof Cycle)
				cyclePhysical =pca.firedTravelDemands(firedEaseOfGettingOnOffUnpleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingEaseOfGetOnOffUnpleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof Walking)
				walkingPhysical = pca.firedTravelDemands(firedEaseOfGettingOnOffUnpleasantPhysical());
			return walkingPhysical;
		}
////Counts		
		public int getFiredPrivateEaseOfGetOnOffUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffUnpleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicEaseOfGetOnOffUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffUnpleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleEaseOfGetOnOffUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffUnpleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingEaseOfGetOnOffUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffUnpleasantPhysical());			
			return counts ;
		}
/////////NEither Nor Pleasant
		public ArrayList<Double> firedEaseOfGettingOnOffNeitherNorPleasantPhysical() {				
			easeOfGettingOnOffNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingOnOffDecisionVariables().get(1);				
			easeOfGettingOnOffNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);						 
			return easeOfGettingOnOffNeitherNorPleasantPhysicalList;			
		}
		
		public double getFiredPrivateEaseOfGetOnOffNeitherNorPleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicEaseOfGetOnOffNeitherNorPleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleEaseOfGetOnOffNeitherNorPleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof Cycle)
				cyclePhysical =pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingEaseOfGetOnOffNeitherNorPleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof Walking)
				walkingPhysical = pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantPhysical());
			return walkingPhysical;
		}
////Counts		
		public int getFiredPrivateEaseOfGetOnOffNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicEaseOfGetOnOffNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleEaseOfGetOnOffNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingEaseOfGetOnOffNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantPhysical());			
			return counts ;
		}
///Pleasant				
		public ArrayList<Double> firedEaseOfGettingOnOffPleasantPhysical() {				
			easeOfGettingOnOffPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingOnOffDecisionVariables().get(1);				
			easeOfGettingOnOffPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);						 
			return easeOfGettingOnOffPleasantPhysicalList	;			
		}
		public double getFiredPrivateEaseOfGetOnOffPleasantPhysical(){
			double privatePhysical =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privatePhysical =pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantPhysical());
			return privatePhysical;
		}		
		public double getFiredPublicEaseOfGetOnOffPleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantPhysical());
			return publicPhysical;
		}
		public double getFiredCycleEaseOfGetOnOffPleasantPhysical(){
			double cyclePhysical =0.0;
			if(prefferedMode instanceof Cycle)
				cyclePhysical =pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantPhysical());
			return cyclePhysical;
		}		
		public double getFiredWalkingEaseOfGetOnOffPleasantPhysical(){
			double walkingPhysical =0;;
			if(prefferedMode instanceof Walking)
				walkingPhysical = pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantPhysical());
			return walkingPhysical;
		}
////Counts		
		public int getFiredPrivateEaseOfGetOnOffPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffPleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicEaseOfGetOnOffPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffPleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleEaseOfGetOnOffPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfGettingOnOffPleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingEaseOfGetOnOffPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfGettingOnOffPleasantPhysical());			
			return counts ;
		}
		
				
///////The following are the list of individual level of PCA-AFFECTIVE
		
		public ArrayList<Double> firedEaseOfGettingOnOffAffective() {
			easeOfGetOnOffAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingOnOffDecisionVariables().get(1);
			easeOfGetOnOffAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);					 
			return easeOfGetOnOffAffectiveList	;			
		}
		public double getFiredPrivateEaseOfGetOnOffAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedEaseOfGettingOnOffAffective());
			return privateAffective;
		}		
		public double getFiredPublicEaseOfGetOnOffAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedEaseOfGettingOnOffAffective());
			return publicAffective;
		}
		public double getFiredCycleEaseOfGetOnOffAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedEaseOfGettingOnOffAffective());
			return cycleAffective;
		}		
		public double getFiredWalkingEaseOfGetOnOffAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedEaseOfGettingOnOffAffective());
			return walkingAffective;
		}
	////Counts		
			public int getFiredPrivateEaseOfGetOnOffAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedEaseOfGettingOnOffAffective());			
				return counts;
			}				
			public int getFiredPublicEaseOfGetOnOffAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedEaseOfGettingOnOffAffective());			
				return counts ;
			}
			public int getFiredCycleEaseOfGetOnOffAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedEaseOfGettingOnOffAffective());			
				return counts;
			}				
			public int getFiredWalkingEaseOfGetOnOffAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedEaseOfGettingOnOffAffective());			
				return counts ;
			}
////////
		public ArrayList<Double> firedEaseOfGettingOnOffUnpleasantAffective() {				
			easeOfGettingOnOffUnpleasentAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingOnOffDecisionVariables().get(1);				
			easeOfGettingOnOffUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);				 
			return easeOfGettingOnOffUnpleasentAffectiveList;			
		}
		public double getPrivateEaseOfGetOnOffUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedEaseOfGettingOnOffUnpleasantAffective());
			return privateAffective;
		}		
		public double getPublicEaseOfGetOnOffUnpleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedEaseOfGettingOnOffUnpleasantAffective());
			return publicAffective;
		}
		public double getCycleEaseOfGetOnOffUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedEaseOfGettingOnOffUnpleasantAffective());
			return cycleAffective;
		}		
		public double getWalkingEaseOfGetOnOffUnpleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedEaseOfGettingOnOffUnpleasantAffective());
			return walkingAffective;
		}
	////Counts		
			public int getFiredPrivateEaseOfGetOnOffUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedEaseOfGettingOnOffUnpleasantAffective());			
				return counts;
			}				
			public int getFiredPublicEaseOfGetOnOffUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedEaseOfGettingOnOffUnpleasantAffective());			
				return counts ;
			}
			public int getFiredCycleEaseOfGetOnOffUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedEaseOfGettingOnOffUnpleasantAffective());			
				return counts;
			}				
			public int getFiredWalkingEaseOfGetOnOffUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedEaseOfGettingOnOffUnpleasantAffective());			
				return counts ;
			}				
		public ArrayList<Double> firedEaseOfGettingOnOffNeitherNorPleasantAffective() {				
			easeOfGettingOnOffNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingOnOffDecisionVariables().get(1);				
			easeOfGettingOnOffNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);					 
			return easeOfGettingOnOffNeitherNorPleasantAffectiveList ;			
		}
		public double getPrivateEaseOfGetOnOffNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantAffective());
			return privateAffective;
		}		
		public double getPublicEaseOfGetOnOffNeitherNorPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantAffective());
			return publicAffective;
		}
		public double getCycleEaseOfGetOnOffNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantAffective());
			return cycleAffective;
		}		
		public double getWalkingEaseOfGetOnOffNeitherNorPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedEaseOfGettingOnOffNeitherNorPleasantAffective());
			return walkingAffective;
		}
	////Counts		
			public int getFiredPrivateEaseOfGetOnOffNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantAffective());			
				return counts;
			}				
			public int getFiredPublicEaseOfGetOnOffNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantAffective());			
				return counts ;
			}
			public int getFiredCycleEaseOfGetOnOffNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantAffective());			
				return counts;
			}				
			public int getFiredWalkingEaseOfGetOnOffNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedEaseOfGettingOnOffNeitherNorPleasantAffective());			
				return counts ;
			}				
								
		public ArrayList<Double> firedEaseOfGettingOnOffPleasantAffective() {				
			easeOfGettingOnOffPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingOnOffDecisionVariables().get(1);				
			easeOfGettingOnOffPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);				 
			return easeOfGettingOnOffPleasantAffectiveList	;			
		}
		public double getPrivateEaseOfGetOnOffPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantAffective());
			return privateAffective;
		}		
		public double getPublicEaseOfGetOnOffPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantAffective());
			return publicAffective;
		}
		public double getCycleEaseOfGetOnOffPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantAffective());
			return cycleAffective;
		}		
		public double getWalkingEaseOfGetOnOffPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedEaseOfGettingOnOffPleasantAffective());
			return walkingAffective;
		}
	////Counts		
			public int getFiredPrivateEaseOfGetOnOffPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedEaseOfGettingOnOffPleasantAffective());			
				return counts;
			}				
			public int getFiredPublicEaseOfGetOnOffPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedEaseOfGettingOnOffPleasantAffective());			
				return counts ;
			}
			public int getFiredCycleEaseOfGetOnOffPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedEaseOfGettingOnOffPleasantAffective());			
				return counts;
			}				
			public int getFiredWalkingEaseOfGetOnOffPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedEaseOfGettingOnOffPleasantAffective());			
				return counts ;
			}			
}
