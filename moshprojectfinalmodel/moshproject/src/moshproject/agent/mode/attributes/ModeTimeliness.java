package moshproject.agent.mode.attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import moshproject.agents.intervener.Intervener;
import moshproject.agents.mode.Cycle;
import moshproject.agents.mode.Mode;
import moshproject.agents.mode.PersonalVehicle;
import moshproject.agents.mode.PublicTransport;
import moshproject.agents.mode.Walking;
import moshproject.agents.passenger.Passenger;
import moshproject.common.Constants;
import moshproject.common.FuzzyDecisionVariables;
import moshproject.fuzzycollections.FuzzyDecisionGenerator;

public class ModeTimeliness {
	
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca;
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;		
	
	private double modeTimeliness;
	private double modeTimelinessPhysical;
	private double timelinessCognitive;
	private double publicModeTimelinessCognitive;
	private double privateModeTimelinessCognitive;
	private double modeTimelinessAffective;
	private double publicModeTimelinessAffective; 
	private double privateModeTimelinessAffective; 	
		
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;
	private Vector modeTimelinessDecisionVariables;
	private ArrayList<Double> timelinessCognitiveList;
	private ArrayList<Double> timelinessUnpleasantCognitiveList;
	private ArrayList<Double> timelinessNeitherNorPleasantCognitiveList;
	private ArrayList<Double> timelinessPleasantCognitiveList;
	private ArrayList<Double> timelinessUnpleasentPhysicalList;
	private ArrayList<Double> timelinessNeitherNorPleasantPhysicalList;
	private ArrayList<Double> timelinessPleasantPhysicalList;
	private ArrayList<Double> timelinessUnpleasantAffectiveList;
	private ArrayList<Double> timelinessNeitherNorPleasantAffectiveList;
	private ArrayList<Double> timelinessPleasantAffectiveList;
	private ArrayList<Double> timelinessPhysicalList;
	private ArrayList<Double> timelinessAffectiveList;
	private double walkingTimeliness;
	private double cycleTimeliness;	
	private double privateTimeliness;
	private double publicTimeliness;
	
	public ModeTimeliness(Mode preferredMode ){
		this.prefferedMode = preferredMode;	
		updateTimeliness();
		pca= new FuzzyDecisionVariables();
	}
		
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	
	private double updateTimelinessPhysical() {			
		return modeTimelinessPhysical = prefferedMode.setValueToAttribute(Constants.gettingToDestOnTimePhysical, 0);			
	}	
	public double getTimelinessPhysical() {
		updateTimelinessPhysical();
		return modeTimelinessPhysical;
	}

	private double updateTimelinessCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			timelinessCognitive = prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimePerception);				
		}else if (prefferedMode instanceof PublicTransport){
			timelinessCognitive = prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimePerception);											
		}else if (prefferedMode instanceof Cycle){
			timelinessCognitive = prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimePerception);								
		}else if (prefferedMode instanceof Walking){
			timelinessCognitive = prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimePerception);													
		}		
		return timelinessCognitive;			
	}	
	public double getTimelinessCognitive() {
		updateTimelinessCognitive();
		return timelinessCognitive;
	}	
	
	private double updateTimelinessAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			modeTimelinessAffective = prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimeAffective);				
		}else if (prefferedMode instanceof PublicTransport){
			modeTimelinessAffective = prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimeAffective);											
		}else if (prefferedMode instanceof Cycle){
			modeTimelinessAffective = prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimeAffective);								
		}else if (prefferedMode instanceof Walking){
			modeTimelinessAffective = prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimeAffective);													
		}				
		return modeTimelinessAffective;			
	}			
	public double getTimelinessAffective() {
		updateTimelinessAffective();
		return modeTimelinessAffective;
	}

		public Vector modeTimelinessValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();
			if (prefferedMode instanceof PersonalVehicle){
				getTimelinessPhysical();
				getTimelinessCognitive();
				getTimelinessAffective();
			}else if(prefferedMode instanceof PublicTransport){
				getTimelinessPhysical();
				getTimelinessCognitive();
				getTimelinessAffective();
			}else if(prefferedMode instanceof Cycle){
				getTimelinessPhysical();
				getTimelinessCognitive();
				getTimelinessAffective();
			}	else if(prefferedMode instanceof Walking){
				getTimelinessPhysical();
				getTimelinessCognitive();
				getTimelinessAffective();
			}			
			modeTimelinessDecisionVariables = evaluateThreeVariablesSatisfaction.getTip(getTimelinessPhysical(),getTimelinessCognitive(),getTimelinessAffective());
			return modeTimelinessDecisionVariables;
		}
		public Vector getModeTimelinessDecisionVariables() {
			return modeTimelinessDecisionVariables;
		}
///This method returns either fuzzy method or the ordinary method
		public double updateTimeliness(){				
			modeTimelinessValues();	
			modeTimeliness =(double) getModeTimelinessDecisionVariables().get(0);			
			return modeTimeliness ;
		}	
		public double getModeTimeliness() {
			return modeTimeliness;
		}
		public double getPrivateTimeliness(){
			if(prefferedMode instanceof PersonalVehicle){
				privateTimeliness = this.getModeTimeliness();
			}	
			return privateTimeliness;
		}
		public double getPublicTimeliness(){
			if(prefferedMode instanceof PublicTransport){
				publicTimeliness = this.getModeTimeliness();
			}		
			return publicTimeliness;
		}
		public double getCycleTimeliness(){
			if(prefferedMode instanceof Cycle){
				cycleTimeliness = this.getModeTimeliness();
			}	
			return cycleTimeliness;
		}
		public double getWalkingTimeliness(){
			if(prefferedMode instanceof Walking){
				walkingTimeliness = this.getModeTimeliness();
			}		
			return walkingTimeliness;
		}
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedTimelinessCognitive() {
			timelinessCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getModeTimelinessDecisionVariables().get(1);
			timelinessCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);				 				 
			return timelinessCognitiveList	;			
		}	
///All modes fired timeliness Cognitive
		public double getFiredPrivateTimelinessCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedTimelinessCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicTimelinessCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedTimelinessCognitive());
			return publicCognitive;
		}
		public double getFiredCycleTimelinessCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedTimelinessCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingTimelinessCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedTimelinessCognitive());
			return walkingCognitive;
		}
///Timeliness cognitive counts by mode
		public int getFiredPrivateTimelinessCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedTimelinessCognitive());	
				return counts;
		}					
		public int getFiredPublicTimelinessCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedTimelinessCognitive());		
				return counts ;
		}
		public int getFiredCycleTimelinessCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedTimelinessCognitive());	
			return counts;
		}			
		public int getFiredWalkingTimelinessCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedTimelinessCognitive());		
			return counts ;
		}
			
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedTimelinessUnpleasantCognitive() {				
			timelinessUnpleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeTimelinessDecisionVariables().get(1);				
			timelinessUnpleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);					 
			return timelinessUnpleasantCognitiveList	;			
		}
///Unpleasant timeliness Cognitive
		public double getFiredPrivateTimelinessUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedTimelinessUnpleasantCognitive());
				return privateCognitive;
		}
				
		public double getFiredPublicTimelinessUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedTimelinessUnpleasantCognitive());
				return publicCognitive;
		}
		public double getFiredCycleTimelinessUnpleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedTimelinessUnpleasantCognitive());
				return cycleCognitive;
		}
		public double getFiredWalkingTimelinessUnpleasantCognitive(){
			double walkingCognitive =0;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedTimelinessUnpleasantCognitive());
				return walkingCognitive;
		}
///Unpleasant Timeliness cognitive counts by mode
		public int getFiredPrivateTimelinessUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedTimelinessUnpleasantCognitive());	
				return counts;
		}						
		public int getFiredPublicTimelinessUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedTimelinessUnpleasantCognitive());		
				return counts ;
		}
		public int getFiredCycleTimelinessUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedTimelinessUnpleasantCognitive());	
			return counts;
		}			
		public int getFiredWalkingTimelinessUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedTimelinessUnpleasantCognitive());		
			return counts ;
		}
				
		public ArrayList<Double> firedTimelinessNeitherNorPleasantCognitive() {				
			timelinessNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getModeTimelinessDecisionVariables().get(1);				
			timelinessNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);		 				 
			return timelinessNeitherNorPleasantCognitiveList	;			
		}
///NeitherPleasantNorUnpleasant
		public double getFiredPrivateTimelinessNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedTimelinessNeitherNorPleasantCognitive());
				return privateCognitive;
		}
				
		public double getFiredPublicTimelinessNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedTimelinessNeitherNorPleasantCognitive());
				return publicCognitive;
		}
		public double getFiredCycleTimelinessNeitherNorPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedTimelinessNeitherNorPleasantCognitive());
				return cycleCognitive;
		}
		public double getFiredWalkingTimelinessNeitherNorPleasantCognitive(){
			double walkingCognitive =0;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedTimelinessNeitherNorPleasantCognitive());
				return walkingCognitive;
		}
///Neither Nor pleasant Timeliness cognitive counts by mode
		public int getFiredPrivateTimelinessNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedTimelinessNeitherNorPleasantCognitive());	
				return counts;
		}			
						
		public int getFiredPublicTimelinessNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedTimelinessNeitherNorPleasantCognitive());		
				return counts ;
		}
		public int getFiredCycleTimelinessNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedTimelinessNeitherNorPleasantCognitive());	
			return counts;
		}			
		public int getFiredWalkingTimelinessNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedTimelinessNeitherNorPleasantCognitive());		
			return counts ;
		}
//////////////		
		public ArrayList<Double> firedTimelinessPleasantCognitive() {				
			timelinessPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getModeTimelinessDecisionVariables().get(1);				
			timelinessPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);					 
			return timelinessPleasantCognitiveList	;			
		}
//////Pleasant
		public double getFiredPrivateTimelinessPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedTimelinessPleasantCognitive());
				return privateCognitive;
		}
				
		public double getFiredPublicTimelinessPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedTimelinessPleasantCognitive());
				return publicCognitive;
		}
		public double getFiredCycleTimelinessPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedTimelinessPleasantCognitive());
				return cycleCognitive;
		}
		public double getFiredWalkingTimelinessPleasantCognitive(){
			double walkingCognitive =0;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedTimelinessPleasantCognitive());
				return walkingCognitive;
		}
///Pleasant Timeliness cognitive counts by mode
		public int getFiredPrivateTimelinessPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedTimelinessPleasantCognitive());	
				return counts;
		}			
						
		public int getFiredPublicTimelinessPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedTimelinessPleasantCognitive());		
				return counts ;
		}
		public int getFiredCycleTimelinessPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedTimelinessPleasantCognitive());	
			return counts;
		}			
		public int getFiredWalkingTimelinessPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedTimelinessPleasantCognitive());		
			return counts ;
		}
			
///////The following are the list of individual level of PCA-PHYSICAL
		
		public ArrayList<Double> firedModeTimelinessPhysical() {
			timelinessPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeTimelinessDecisionVariables().get(1);
			timelinessPhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);
		//	System.out.println("The timeliness Physical Values:");
			for (Double antecedents :timelinessPhysicalList){
		//		System.out.println(antecedents);
			} 				 
			return timelinessPhysicalList	;			
		}

		public ArrayList<Double> firedTimelinessUnpleasantPhysical() {				
			timelinessUnpleasentPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeTimelinessDecisionVariables().get(1);				
			timelinessUnpleasentPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);
		//	System.out.println("The Unpleasant timeliness Physical Values:");
			for (Double antecedents :timelinessUnpleasentPhysicalList){
		//	System.out.println(antecedents);
			} 			 
			return timelinessUnpleasentPhysicalList;			
		}
			
		public ArrayList<Double> firedTimelinessNeitherNorPleasantPhysical() {				
			timelinessNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeTimelinessDecisionVariables().get(1);				
			timelinessNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);
		//	System.out.println("The Neither pleasant nor unpleasant timeliness Physical Values:");
			for (Double antecedents :timelinessNeitherNorPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return timelinessNeitherNorPleasantPhysicalList;			
		}
				
		public ArrayList<Double> firedTimelinessPleasantPhysical() {				
			timelinessPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeTimelinessDecisionVariables().get(1);				
			timelinessPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);
		//	System.out.println("The Pleasant timeliness Physical Values:");
			for (Double antecedents :timelinessPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return timelinessPleasantPhysicalList	;			
		}
				
///////The following are the list of individual level of PCA-AFFECTIVE		
		public ArrayList<Double> firedModeTimelinessAffective() {
			timelinessAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeTimelinessDecisionVariables().get(1);
			timelinessAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);						 
			return timelinessAffectiveList	;			
		}
		public double getFiredPrivateTimelinessAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedModeTimelinessAffective());
			return privateAffective;
		}		
		public double getFiredPublicTimelinessAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedModeTimelinessAffective());
			return publicAffective;
		}
		public double getFiredCycleTimelinessAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedModeTimelinessAffective());
			return cycleAffective;
		}
		public double getFiredWalkingTimelinessAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedModeTimelinessAffective());
			return walkingAffective;
		}
///Affective counts by mode
		public int getFiredPrivateTimelinessAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedModeTimelinessAffective());	
			return counts;
		}			
		
		public int getFiredPublicTimelinessAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedModeTimelinessAffective());		
			return counts ;
		}
		public int getFiredCycleTimelinessAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedModeTimelinessAffective());	
			return counts;
		}			
		
		public int getFiredWalkingTimelinessAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedModeTimelinessAffective());		
			return counts ;
		}
///Unpleasant Affective		
		public ArrayList<Double> firedTimelinessUnpleasantAffective() {				
			timelinessUnpleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeTimelinessDecisionVariables().get(1);				
			timelinessUnpleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);						 
			return timelinessUnpleasantAffectiveList;			
		}
		public double getFiredPrivateTimelinessUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedTimelinessUnpleasantAffective());
				return privateAffective;
		}
				
		public double getFiredPublicTimelinessUnpleasantAffective(){
			double publicAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedTimelinessUnpleasantAffective());
				return publicAffective;
		}
		public double getFiredCycleTimelinessUnpleasantAffective(){
			double cycleAffective =0;;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedTimelinessUnpleasantAffective());
				return cycleAffective;
		}
		public double getFiredWalkingTimelinessUnpleasantAffective(){
			double walkingAffective =0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedTimelinessUnpleasantAffective());
				return walkingAffective;
		}
///Unpleasant Timeliness cognitive counts by mode
		public int getFiredPrivateTimelinessUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedTimelinessUnpleasantAffective());	
				return counts;
		}			
						
		public int getFiredPublicTimelinessUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedTimelinessUnpleasantAffective());		
				return counts ;
		}
		public int getFiredCycleTimelinessUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedTimelinessUnpleasantAffective());	
			return counts;
		}			
		public int getFiredWalkingTimelinessUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedTimelinessUnpleasantAffective());		
			return counts ;
		}
///Neither Pleasant Nor Unpleasant							
		public ArrayList<Double> firedTimelinessNeitherNorPleasantAffective() {				
			timelinessNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getModeTimelinessDecisionVariables().get(1);				
			timelinessNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);		 				 
			return timelinessNeitherNorPleasantAffectiveList ;			
		}
		public double getFiredPrivateTimelinessNeitherNorpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedTimelinessNeitherNorPleasantAffective());
				return privateAffective;
		}
				
		public double getFiredPublicTimelinessNeitherNorPleasantAffective(){
			double publicAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedTimelinessNeitherNorPleasantAffective());
				return publicAffective;
		}
		public double getFiredCycleTimelinessNeitherNorPleasantAffective(){
			double cycleAffective =0;;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedTimelinessNeitherNorPleasantAffective());
				return cycleAffective;
		}
		public double getFiredWalkingTimelinessNeitherNorPleasantAffective(){
			double walkingAffective =0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedTimelinessNeitherNorPleasantAffective());
				return walkingAffective;
		}
///Unpleasant Timeliness cognitive counts by mode
		public int getFiredPrivateTimelinessNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedTimelinessNeitherNorPleasantAffective());	
				return counts;
		}			
						
		public int getFiredPublicTimelinessNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedTimelinessNeitherNorPleasantAffective());		
				return counts ;
		}
		public int getFiredCycleTimelinessNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedTimelinessNeitherNorPleasantAffective());	
			return counts;
		}			
		public int getFiredWalkingTimelinessNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedTimelinessNeitherNorPleasantAffective());		
			return counts ;
		}
		
////Pleasant								
		public ArrayList<Double> firedTimelinessPleasantAffective() {				
			timelinessPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getModeTimelinessDecisionVariables().get(1);				
			timelinessPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);						 
			return timelinessPleasantAffectiveList	;			
		}
		public double getFiredPrivateTimelinessPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedTimelinessPleasantAffective());
				return privateAffective;
		}
				
		public double getFiredPublicTimelinessPleasantAffective(){
			double publicAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedTimelinessPleasantAffective());
				return publicAffective;
		}
		public double getFiredCycleTimelinessPleasantAffective(){
			double cycleAffective =0;;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedTimelinessPleasantAffective());
				return cycleAffective;
		}
		public double getFiredWalkingTimelinessPleasantAffective(){
			double walkingAffective =0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedTimelinessPleasantAffective());
				return walkingAffective;
		}
///Unpleasant Timeliness cognitive counts by mode
		public int getFiredPrivateTimelinessPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedTimelinessPleasantAffective());	
				return counts;
		}			
						
		public int getFiredPublicTimelinessPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedTimelinessPleasantAffective());		
				return counts ;
		}
		public int getFiredCycleTimelinessPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
			counts =pca.firedCounts(firedTimelinessPleasantAffective());	
			return counts;
		}			
		public int getFiredWalkingTimelinessPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
			counts  = pca.firedCounts(firedTimelinessPleasantAffective());		
			return counts ;
		}
}
