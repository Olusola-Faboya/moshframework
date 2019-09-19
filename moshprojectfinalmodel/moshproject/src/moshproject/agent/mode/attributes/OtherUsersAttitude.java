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

public class OtherUsersAttitude {
	
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	
	private Vector modeReliabilityDecisionVariables;
	private double othersAttitudePhysical;
	private double othersAttitudeCognitive;
	private double othersAttitudeAffective;
	private double othersUserAttitude;
	private double privateOtherUsersAttitude;
	private double publicOtherUsersAttitude;
	private double publicModeOthersAttitudeCognitive;
	private double privateModeOthersAttitudeCognitive;
	private double publicModeOthersAttitudeAffective;
	private double privateModeOthersAttitudeAffective;
	private Vector othersAttitudeDecisionVariables;
	private ArrayList<Double> othersUserAttitudeCognitiveList;
	private ArrayList<Double> othersUserAttitudeUnpleasantCognitiveList;
	private ArrayList<Double> othersUserAttitudeNeitherNorPleasantCognitiveList;
	private ArrayList<Double> othersUserAttitudePleasantCognitiveList;
	private ArrayList<Double> othersUserAttitudeUnpleasantPhysicalList;
	private ArrayList<Double> othersUserAttitudeNeitherNorPleasantPhysicalList;
	private ArrayList<Double> othersUserAttitudePleasantPhysicalList;
	private ArrayList<Double> othersUserAttitudeUnpleasantAffectiveList;
	private ArrayList<Double> othersUserAttitudeNeitherNorPleasantAffectiveList;
	private ArrayList<Double> othersUserAttitudePleasantAffectiveList;
	private ArrayList<Double> otherUsersAttitudeAffectiveList;
	private ArrayList<Double> othersUserAttitudePhysicalList;
	private double cycleOtherUsersAttitude;
	private double walkingOtherUsersAttitude;
	
	public OtherUsersAttitude(Mode prefferedMode){
		this.prefferedMode=prefferedMode;
		updateOtherUsersAttitude();
		pca= new FuzzyDecisionVariables();			
	}
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	private double updateOthersAttitudePhysical() {						
		return othersAttitudePhysical=prefferedMode.setValueToAttribute(Constants.othersAttitudePhysical,0);			
	}	
	
	public double getOthersAttitudePhysical() {
		updateOthersAttitudePhysical();
		return othersAttitudePhysical;
	}

	private double updateOthersAttitudeCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			othersAttitudeCognitive = prefferedMode.getValueOfAttribute(Constants.othersAttitudePerception);					
		}else if (prefferedMode instanceof PublicTransport){
			othersAttitudeCognitive = prefferedMode.getValueOfAttribute(Constants.othersAttitudePerception);											
		}else if (prefferedMode instanceof Cycle){
			othersAttitudeCognitive = prefferedMode.getValueOfAttribute(Constants.othersAttitudePerception);								
		}else if (prefferedMode instanceof Walking){
			othersAttitudeCognitive = prefferedMode.getValueOfAttribute(Constants.othersAttitudePerception);													
		}		
		return othersAttitudeCognitive;			
	}

	public double getOthersAttitudeCognitive() {
		updateOthersAttitudeCognitive();
		return othersAttitudeCognitive;
	}

	private double updateOthersAttitudeAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			othersAttitudeAffective = prefferedMode.getValueOfAttribute(Constants.othersAttitudeAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			othersAttitudeAffective = prefferedMode.getValueOfAttribute(Constants.othersAttitudeAffective);											
		}else if (prefferedMode instanceof Cycle){
			othersAttitudeAffective = prefferedMode.getValueOfAttribute(Constants.othersAttitudeAffective);								
		}else if (prefferedMode instanceof Walking){
			othersAttitudeAffective = prefferedMode.getValueOfAttribute(Constants.othersAttitudeAffective);													
		}			
		return othersAttitudeAffective;			
	}	
	
	public double getOthersAttitudeAffective() {
	updateOthersAttitudeAffective();
		return othersAttitudeAffective;
	}

		////////The Fuzzy System Version
		public Vector othersAttitudeValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();
			if(prefferedMode instanceof PersonalVehicle){
				getOthersAttitudeAffective();
				getOthersAttitudeCognitive();
				getOthersAttitudePhysical();
			}else if (prefferedMode instanceof PublicTransport){
				getOthersAttitudeAffective();
				getOthersAttitudeCognitive();
				getOthersAttitudePhysical();
			}	else if (prefferedMode instanceof Cycle){
				getOthersAttitudeAffective();
				getOthersAttitudeCognitive();
				getOthersAttitudePhysical();
			}else if (prefferedMode instanceof Walking){
				getOthersAttitudeAffective();
				getOthersAttitudeCognitive();
				getOthersAttitudePhysical();
			}		
			othersAttitudeDecisionVariables=	evaluateThreeVariablesSatisfaction.getTip(getOthersAttitudePhysical(),getOthersAttitudeCognitive(),getOthersAttitudeAffective());						
			return othersAttitudeDecisionVariables;
		}	
		public Vector getOthersAttitudeDecisionVariables() {
			return othersAttitudeDecisionVariables;
		}

		///This method returns either fuzzy method or the ordinary method
		public double updateOtherUsersAttitude(){				
			othersAttitudeValues() ;	
			othersUserAttitude =(double) getOthersAttitudeDecisionVariables().get(0);	
			return  othersUserAttitude ;
		}	
		public double getOthersAttitude() {
			return othersUserAttitude;
		}		
		public double getPrivateOtherUsersAttitude(){
			if(prefferedMode instanceof PersonalVehicle){
				privateOtherUsersAttitude = this.getOthersAttitude();
			}
			return (double) privateOtherUsersAttitude;
		}			
		public double getPublicOtherUsersAttitude(){
			if(prefferedMode instanceof PublicTransport){
				publicOtherUsersAttitude = this.getOthersAttitude();
			}
			return (double) publicOtherUsersAttitude;
		}
		public double getCycleOtherUsersAttitude(){
			if(prefferedMode instanceof Cycle){
				cycleOtherUsersAttitude = this.getOthersAttitude();
			}
			return (double) cycleOtherUsersAttitude;
		}			
		public double getWalkingOtherUsersAttitude(){
			if(prefferedMode instanceof Walking){
				walkingOtherUsersAttitude = this.getOthersAttitude();
			}
			return (double) walkingOtherUsersAttitude;
		}
		
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedOtherUsersAttitudeCognitive() {
			othersUserAttitudeCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getOthersAttitudeDecisionVariables().get(1);
			othersUserAttitudeCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);		 				 
			return othersUserAttitudeCognitiveList	;			
		}	
		public double getFiredPrivateOtherUsersAttitudeCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicOtherUsersAttitudeCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeCognitive());
			return publicCognitive;
		}
		public double getFiredCycleOtherUsersAttitudeCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeCognitive());
			return cycleCognitive;
		}		
		public double getFiredWalkingOtherUsersAttitudeCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeCognitive());
			return walkingCognitive;
		}	
/////Counts
		public int getFiredPrivateOtherUsersAttitudeCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedOtherUsersAttitudeCognitive());			
			return counts;
		}				
		public int getFiredPublicOtherUsersAttitudeCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedOtherUsersAttitudeCognitive());			
			return counts ;
		}
		public int getFiredCycleOtherUsersAttitudeCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedOtherUsersAttitudeCognitive());			
			return counts;
		}				
		public int getFiredWalkingOtherUsersAttitudeCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedOtherUsersAttitudeCognitive());			
			return counts ;
		}
		
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedOtherUsersAttitudeUnpleasantCognitive() {				
			othersUserAttitudeUnpleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudeUnpleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);					 
			return othersUserAttitudeUnpleasantCognitiveList	;			
		}
		public double getFiredPrivateOtherUsersAttitudeUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicOtherUsersAttitudeUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleOtherUsersAttitudeUnpleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantCognitive());
			return cycleCognitive;
		}		
		public double getFiredWalkingOtherUsersAttitudeUnpleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantCognitive());
			return walkingCognitive;
		}	
/////Counts
		public int getFiredPrivateOtherUsersAttitudeUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedOtherUsersAttitudeUnpleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicOtherUsersAttitudeUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedOtherUsersAttitudeUnpleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleOtherUsersAttitudeUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedOtherUsersAttitudeUnpleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingOtherUsersAttitudeUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedOtherUsersAttitudeUnpleasantCognitive());			
			return counts ;
		}
		public ArrayList<Double> firedOtherUsersAttitudeNeitherNorPleasantCognitive() {				
			othersUserAttitudeNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudeNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);						 
			return othersUserAttitudeNeitherNorPleasantCognitiveList	;			
		}		
		public double getFiredPrivateOtherUsersAttitudeNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicOtherUsersAttitudeNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleOtherUsersAttitudeNeitherNorPleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantCognitive());
			return cycleCognitive;
		}		
		public double getFiredWalkingOtherUsersAttitudeNeitherNorPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantCognitive());
			return walkingCognitive;
		}	
/////Counts
		public int getFiredPrivateOtherUsersAttitudeNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicOtherUsersAttitudeNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleOtherUsersAttitudeNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingOtherUsersAttitudeNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantCognitive());			
			return counts ;
		}
////Pleasant		
		public ArrayList<Double> firedOtherUsersAttitudePleasantCognitive() {				
			othersUserAttitudePleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudePleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);					 
			return othersUserAttitudePleasantCognitiveList	;			
		}
		public double getFiredPrivateOtherUsersAttitudePleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedOtherUsersAttitudePleasantCognitive());
			return privateCognitive;
		}		
		public double getFiredPublicOtherUsersAttitudePleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedOtherUsersAttitudePleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCycleOtherUsersAttitudePleasantCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedOtherUsersAttitudePleasantCognitive());
			return cycleCognitive;
		}		
		public double getFiredWalkingOtherUsersAttitudePleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedOtherUsersAttitudePleasantCognitive());
			return walkingCognitive;
		}	
/////Counts
		public int getFiredPrivateOtherUsersAttitudePleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedOtherUsersAttitudePleasantCognitive());			
			return counts;
		}				
		public int getFiredPublicOtherUsersAttitudePleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedOtherUsersAttitudePleasantCognitive());			
			return counts ;
		}
		public int getFiredCycleOtherUsersAttitudePleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedOtherUsersAttitudePleasantCognitive());			
			return counts;
		}				
		public int getFiredWalkingOtherUsersAttitudePleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedOtherUsersAttitudePleasantCognitive());			
			return counts ;
		}
///////The following are the list of individual level of PCA-PHYSICAL
		public ArrayList<Double> firedOtherUsersAttitudePhysical() {
			othersUserAttitudePhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getOthersAttitudeDecisionVariables().get(1);
			othersUserAttitudePhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);		 				 
			return othersUserAttitudePhysicalList	;			
		}	
		public double getFiredPrivateOtherUsersAttitudePhysical(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedOtherUsersAttitudePhysical());
			return privateCognitive;
		}		
		public double getFiredPublicOtherUsersAttitudePhysical(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedOtherUsersAttitudePhysical());
			return publicCognitive;
		}
		public double getFiredCycleOtherUsersAttitudePhysical(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedOtherUsersAttitudePhysical());
			return cycleCognitive;
		}		
		public double getFiredWalkingOtherUsersAttitudePhysical(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedOtherUsersAttitudePhysical());
			return walkingCognitive;
		}	
/////Counts
		public int getFiredPrivateOtherUsersAttitudePhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedOtherUsersAttitudePhysical());			
			return counts;
		}				
		public int getFiredPublicOtherUsersAttitudePhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedOtherUsersAttitudePhysical());			
			return counts ;
		}
		public int getFiredCycleOtherUsersAttitudePhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedOtherUsersAttitudePhysical());			
			return counts;
		}				
		public int getFiredWalkingOtherUsersAttitudePhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedOtherUsersAttitudePhysical());			
			return counts ;
		}
////Unpleasant
		public ArrayList<Double> firedOtherUsersAttitudeUnpleasantPhysical() {				
			othersUserAttitudeUnpleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudeUnpleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);					 
			return othersUserAttitudeUnpleasantPhysicalList;			
		}
		public double getFiredPrivateOtherUsersAttitudeUnpleasantPhysical(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantPhysical());
			return privateCognitive;
		}		
		public double getFiredPublicOtherUsersAttitudeUnpleasantPhysical(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantPhysical());
			return publicCognitive;
		}
		public double getFiredCycleOtherUsersAttitudeUnpleasantPhysical(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantPhysical());
			return cycleCognitive;
		}		
		public double getFiredWalkingOtherUsersAttitudeUnpleasantPhysical(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantPhysical());
			return walkingCognitive;
		}	
/////Counts
		public int getFiredPrivateOtherUsersAttitudeUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedOtherUsersAttitudeUnpleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicOtherUsersAttitudeUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedOtherUsersAttitudeUnpleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleOtherUsersAttitudeUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedOtherUsersAttitudeUnpleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingOtherUsersAttitudeUnpleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedOtherUsersAttitudeUnpleasantPhysical());			
			return counts ;
		}
/////Neither Nor
		public ArrayList<Double> firedOtherUsersAttitudeNeitherNorPleasantPhysical() {				
			othersUserAttitudeNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudeNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);					 
			return othersUserAttitudeNeitherNorPleasantPhysicalList;			
		}
		public double getFiredPrivateOtherUsersAttitudeNeitherNorPleasantPhysical(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantPhysical());
			return privateCognitive;
		}		
		public double getFiredPublicOtherUsersAttitudeNeitherNorPleasantPhysical(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantPhysical());
			return publicCognitive;
		}
		public double getFiredCycleOtherUsersAttitudeNeitherNorPleasantPhysical(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantPhysical());
			return cycleCognitive;
		}		
		public double getFiredWalkingOtherUsersAttitudeNeitherNorPleasantPhysical(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantPhysical());
			return walkingCognitive;
		}	
/////Counts
		public int getFiredPrivateOtherUsersAttitudeNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicOtherUsersAttitudeNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleOtherUsersAttitudeNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingOtherUsersAttitudeNeitherNorPleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantPhysical());			
			return counts ;
		}
				
		public ArrayList<Double> firedOtherUsersAttitudePleasantPhysical() {				
			othersUserAttitudePleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudePleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);						 
			return othersUserAttitudePleasantPhysicalList	;			
		}		
		public double getFiredPrivateOtherUsersAttitudePleasantPhysical(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedOtherUsersAttitudePleasantPhysical());
			return privateCognitive;
		}		
		public double getFiredPublicOtherUsersAttitudePleasantPhysical(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedOtherUsersAttitudePleasantPhysical());
			return publicCognitive;
		}
		public double getFiredCycleOtherUsersAttitudePleasantPhysical(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedOtherUsersAttitudePleasantPhysical());
			return cycleCognitive;
		}		
		public double getFiredWalkingOtherUsersAttitudePleasantPhysical(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedOtherUsersAttitudePleasantPhysical());
			return walkingCognitive;
		}	
/////Counts
		public int getFiredPrivateOtherUsersAttitudePleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedOtherUsersAttitudePleasantPhysical());			
			return counts;
		}				
		public int getFiredPublicOtherUsersAttitudePleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedOtherUsersAttitudePleasantPhysical());			
			return counts ;
		}
		public int getFiredCycleOtherUsersAttitudePleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedOtherUsersAttitudePleasantPhysical());			
			return counts;
		}				
		public int getFiredWalkingOtherUsersAttitudePleasantPhysicalCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedOtherUsersAttitudePleasantPhysical());			
			return counts ;
		}
				
///////The following are the list of individual level of PCA-AFFECTIVE
		public ArrayList<Double> firedOtherUsersAttitudeAffective() {
			otherUsersAttitudeAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getOthersAttitudeDecisionVariables().get(1);
			otherUsersAttitudeAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);						 
			return otherUsersAttitudeAffectiveList	;			
		}
		public double getFiredPrivateOtherUsersAttitudeAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedOtherUsersAttitudeAffective());
			return privateAffective;
		}		
		public double getFiredPublicOtherUsersAttitudeAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedOtherUsersAttitudeAffective());
			return publicAffective;
		}
		public double getFiredCycleOtherUsersAttitudeAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedOtherUsersAttitudeAffective());
			return cycleAffective;
		}		
		public double getFiredWalkingOtherUsersAttitudeAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedOtherUsersAttitudeAffective());
			return walkingAffective;
		}
	/////Counts
			public int getFiredPrivateOtherUsersAttitudeAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedOtherUsersAttitudeAffective());			
				return counts;
			}				
			public int getFiredPublicOtherUsersAttitudeAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedOtherUsersAttitudeAffective());			
				return counts ;
			}
			public int getFiredCycleOtherUsersAttitudeAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedOtherUsersAttitudeAffective());			
				return counts;
			}				
			public int getFiredWalkingOtherUsersAttitudeAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedOtherUsersAttitudeAffective());			
				return counts ;
			}
		public ArrayList<Double> firedOtherUsersAttitudeUnpleasantAffective() {				
			othersUserAttitudeUnpleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudeUnpleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);					 
			return othersUserAttitudeUnpleasantAffectiveList;			
		}
		public double getFiredPrivateOtherUsersAttitudeUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicOtherUsersAttitudeUnpleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleOtherUsersAttitudeUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantAffective());
			return cycleAffective;
		}		
		public double getFiredWalkingOtherUsersAttitudeUnpleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedOtherUsersAttitudeUnpleasantAffective());
			return walkingAffective;
		}
	///// Unpleasant Counts
			public int getFiredPrivateOtherUsersAttitudeUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedOtherUsersAttitudeUnpleasantAffective());			
				return counts;
			}				
			public int getFiredPublicOtherUsersAttitudeUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedOtherUsersAttitudeUnpleasantAffective());			
				return counts ;
			}
			public int getFiredCycleOtherUsersAttitudeUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedOtherUsersAttitudeUnpleasantAffective());			
				return counts;
			}				
			public int getFiredWalkingOtherUsersAttitudeUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedOtherUsersAttitudeUnpleasantAffective());			
				return counts ;
			}					
		public ArrayList<Double> firedOtherUsersAttitudeNeitherNorPleasantAffective() {				
			othersUserAttitudeNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudeNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
			return othersUserAttitudeNeitherNorPleasantAffectiveList ;			
		}
		public double getFiredPrivateOtherUsersAttitudeNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicOtherUsersAttitudeNeitherNorPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleOtherUsersAttitudeNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantAffective());
			return cycleAffective;
		}		
		public double getFiredWalkingOtherUsersAttitudeNeitherNorPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedOtherUsersAttitudeNeitherNorPleasantAffective());
			return walkingAffective;
		}
	/////Counts
			public int getFiredPrivateOtherUsersAttitudeNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantAffective());			
				return counts;
			}				
			public int getFiredPublicOtherUsersAttitudeNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantAffective());			
				return counts ;
			}
			public int getFiredCycleOtherUsersAttitudeNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantAffective());			
				return counts;
			}				
			public int getFiredWalkingOtherUsersAttitudeNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedOtherUsersAttitudeNeitherNorPleasantAffective());			
				return counts ;
			}					
		public ArrayList<Double> firedOtherUsersAttitudePleasantAffective() {				
			othersUserAttitudePleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getOthersAttitudeDecisionVariables().get(1);				
			othersUserAttitudePleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);		 				 
			return othersUserAttitudePleasantAffectiveList	;			
		}
		public double getFiredPrivateOtherUsersAttitudePleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedOtherUsersAttitudePleasantAffective());
			return privateAffective;
		}		
		public double getFiredPublicOtherUsersAttitudePleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedOtherUsersAttitudePleasantAffective());
			return publicAffective;
		}
		public double getFiredCycleOtherUsersAttitudePleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective =pca.firedTravelDemands(firedOtherUsersAttitudePleasantAffective());
			return cycleAffective;
		}		
		public double getFiredWalkingOtherUsersAttitudePleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedOtherUsersAttitudePleasantAffective());
			return walkingAffective;
		}
	/////Counts
			public int getFiredPrivateOtherUsersAttitudePleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedOtherUsersAttitudePleasantAffective());			
				return counts;
			}				
			public int getFiredPublicOtherUsersAttitudePleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedOtherUsersAttitudePleasantAffective());			
				return counts ;
			}
			public int getFiredCycleOtherUsersAttitudePleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedOtherUsersAttitudePleasantAffective());			
				return counts;
			}				
			public int getFiredWalkingOtherUsersAttitudePleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedOtherUsersAttitudePleasantAffective());			
				return counts ;
			}			
}
