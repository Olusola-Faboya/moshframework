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

public class ModeSecurity {
	
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 
	
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	
	private Vector modeReliabilityDecisionVariables;
	private double personalSecurityPhysical;
	private double personalSecurityCognitive;
	private double personalSecurityAffective;
	private double personalSecurity;
	private double privatePersonalSecurity;
	private double publicPersonalSecurity;
	private double publicModePersonalSecurityCognitive;
	private double privateModePersonalSecurityCognitive;
	private double privateModePersonalSecurityAffective;
	private double publicModePersonalSecurityAffective;
	private Vector personalSecurityDecisionVariables;
	private ArrayList<Double> personalSecurityCognitiveList;
	private ArrayList<Double> personalSecurityUnpleasentCognitiveList;
	private ArrayList<Double> personalSecurityNeitherNorPleasantCognitiveList;
	private ArrayList<Double> personalSecurityPleasantCognitiveList;
	private ArrayList<Double> personalSecurityUnpleasantPhysicalList;
	private ArrayList<Double> personalSecurityNeitherNorPleasantPhysicalList;
	private ArrayList<Double> personalSecurityPleasantPhysicalList;
	private ArrayList<Double> personalSecurityUnpleasentAffectiveList;
	private ArrayList<Double> personalSecurityNeitherNorPleasantAffectiveList;
	private ArrayList<Double> personalSecurityPleasantAffectiveList;
	private ArrayList<Double> personalSecurityAffectiveList;
	private double cyclePersonalSecurity;
	private double walkingPersonalSecurity;
		
	public ModeSecurity(Mode prefferedMode){
		this.prefferedMode =prefferedMode;
		updatePersonalSecurity();
		pca= new FuzzyDecisionVariables();		
	}
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	private double updatePersonalSecurityPhysical() {					
		return personalSecurityPhysical =prefferedMode.setValueToAttribute(Constants.personalSecurityPhysical, 0);
	}	
	
	public double getPersonalSecurityPhysical() {
		updatePersonalSecurityPhysical();
		return personalSecurityPhysical;
	}

	private double updatePersonalSecurityCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			personalSecurityCognitive = prefferedMode.getValueOfAttribute(Constants.personalSecurityPerception);					
		}else if (prefferedMode instanceof PublicTransport){
			personalSecurityCognitive = prefferedMode.getValueOfAttribute(Constants.personalSecurityPerception);											
		}else if (prefferedMode instanceof Cycle){
			personalSecurityCognitive = prefferedMode.getValueOfAttribute(Constants.personalSecurityPerception);								
		}else if (prefferedMode instanceof Walking){
			personalSecurityCognitive = prefferedMode.getValueOfAttribute(Constants.personalSecurityPerception);													
		}				
		return personalSecurityCognitive;			
	}	
	public double getPersonalSecurityCognitive() {
		updatePersonalSecurityCognitive();
		return personalSecurityCognitive;
	}

	private double updatePersonalSecurityAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			personalSecurityAffective = prefferedMode.getValueOfAttribute(Constants.personalSecurityAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			personalSecurityAffective = prefferedMode.getValueOfAttribute(Constants.personalSecurityAffective);											
		}else if (prefferedMode instanceof Cycle){
			personalSecurityAffective = prefferedMode.getValueOfAttribute(Constants.personalSecurityAffective);								
		}else if (prefferedMode instanceof Walking){
			personalSecurityAffective = prefferedMode.getValueOfAttribute(Constants.personalSecurityAffective);													
		}			
		return personalSecurityAffective;			
	}
	
	public double getPersonalSecurityAffective() {
		updatePersonalSecurityAffective();
		return personalSecurityAffective;
	}

		////////The Fuzzy System Version
		public Vector personalSecurityValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();
			if(prefferedMode instanceof PersonalVehicle){
				getPersonalSecurityAffective();
				getPersonalSecurityCognitive();
				getPersonalSecurityPhysical();
			}else if (prefferedMode instanceof PublicTransport){
				getPersonalSecurityAffective();
				getPersonalSecurityCognitive();
				getPersonalSecurityPhysical();
			}else if (prefferedMode instanceof Cycle){
				getPersonalSecurityAffective();
				getPersonalSecurityCognitive();
				getPersonalSecurityPhysical();
			}else if (prefferedMode instanceof Walking){
				getPersonalSecurityAffective();
				getPersonalSecurityCognitive();
				getPersonalSecurityPhysical();
			}		
			personalSecurityDecisionVariables =	evaluateThreeVariablesSatisfaction.getTip(getPersonalSecurityPhysical(),getPersonalSecurityCognitive(),getPersonalSecurityAffective());			
			return personalSecurityDecisionVariables;
		}		
		public Vector getPersonalSecurityDecisionVariables() {
			return personalSecurityDecisionVariables;
		}
	///This method returns either fuzzy method or the ordinary method
		public double updatePersonalSecurity(){				
			personalSecurityValues();	
			personalSecurity =(double) getPersonalSecurityDecisionVariables().get(0);				
			return personalSecurity ;
		}	

		public double getPersonalSecurity() {
			return personalSecurity;
		}		
		public double getPrivatePersonalSecurity(){
			if(prefferedMode instanceof PersonalVehicle){
				privatePersonalSecurity = this.getPersonalSecurity();
			}//		
			return (double) privatePersonalSecurity;
		}			
		public double getPublicPersonalSecurity(){
			if(prefferedMode instanceof PublicTransport){
				publicPersonalSecurity = this.getPersonalSecurity();
			}//	
			return (double) publicPersonalSecurity;
		}
		public double getCyclePersonalSecurity(){
			if(prefferedMode instanceof Cycle){
				cyclePersonalSecurity = this.getPersonalSecurity();
			}//		
			return (double) cyclePersonalSecurity;
		}			
		public double getWalkingPersonalSecurity(){
			if(prefferedMode instanceof Walking){
				walkingPersonalSecurity = this.getPersonalSecurity();
			}//	
			return (double) walkingPersonalSecurity;
		}
		
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedPersonalSecurityCognitive() {
			personalSecurityCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getPersonalSecurityDecisionVariables().get(1);
			personalSecurityCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);						 
			return personalSecurityCognitiveList	;			
		}

		public double getFiredPrivatePersonalSecurityCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedPersonalSecurityCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicPersonalSecurityCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedPersonalSecurityCognitive());
			return publicCognitive;
		}
		public double getFiredCyclePersonalSecurityCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedPersonalSecurityCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingPersonalSecurityCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedPersonalSecurityCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivatePersonalSecurityCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSecurityCognitive());	
			return counts;
		}			
		
		public int getFiredPublicPersonalSecurityCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSecurityCognitive());		
			return counts ;
		}
		public int getFiredCyclePersonalSecurityCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSecurityCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSecurityCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSecurityCognitive());		
			return counts ;
		}		
			
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedPersonalSecurityUnpleasantCognitive() {				
			personalSecurityUnpleasentCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityUnpleasentCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);					 
			return personalSecurityUnpleasentCognitiveList	;			
		}
		public double getFiredPrivatePersonalSecurityUnpleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedPersonalSecurityUnpleasantCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicPersonalSecurityUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedPersonalSecurityUnpleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCyclePersonalSecurityUnpleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedPersonalSecurityUnpleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingPersonalSecurityUnpleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedPersonalSecurityUnpleasantCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivatePersonalSecurityUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSecurityUnpleasantCognitive());	
			return counts;
		}			
		
		public int getFiredPublicPersonalSecurityUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSecurityUnpleasantCognitive());		
			return counts ;
		}
		public int getFiredCyclePersonalSecurityUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSecurityUnpleasantCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSecurityUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSecurityUnpleasantCognitive());		
			return counts ;
		}	
		public ArrayList<Double> firedPersonalSecurityNeitherNorPleasantCognitive() {				
			personalSecurityNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);						 
			return personalSecurityNeitherNorPleasantCognitiveList	;			
		}
		public double getFiredPrivatePersonalSecurityNeitherNorPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedPersonalSecurityNeitherNorPleasantCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicPersonalSecurityNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedPersonalSecurityNeitherNorPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCyclePersonalSecurityNeitherNorPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedPersonalSecurityNeitherNorPleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingPersonalSecurityNeitherNorPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedPersonalSecurityNeitherNorPleasantCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivatePersonalSecurityNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSecurityNeitherNorPleasantCognitive());	
			return counts;
		}			
		
		public int getFiredPublicPersonalSecurityNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSecurityNeitherNorPleasantCognitive());		
			return counts ;
		}
		public int getFiredCyclePersonalSecurityNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSecurityNeitherNorPleasantCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSecurityNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSecurityNeitherNorPleasantCognitive());		
			return counts ;
		}
		public ArrayList<Double> firedPersonalSecurityPleasantCognitive() {				
			personalSecurityPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);		 				 
			return personalSecurityPleasantCognitiveList	;			
		}
		public double getFiredPrivatePersonalSecurityPleasantCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedPersonalSecurityPleasantCognitive());
			return privateCognitive;
		}
		
		public double getFiredPublicPersonalSecurityPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedPersonalSecurityPleasantCognitive());
			return publicCognitive;
		}
		public double getFiredCyclePersonalSecurityPleasantCognitive(){
			double cycleCognitive =0;;
			if(prefferedMode instanceof Cycle)
				cycleCognitive = pca.firedTravelDemands(firedPersonalSecurityPleasantCognitive());
			return cycleCognitive;
		}
		public double getFiredWalkingPersonalSecurityPleasantCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedPersonalSecurityPleasantCognitive());
			return walkingCognitive;
		}
///Counts
		public int getFiredPrivatePersonalSecurityPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSecurityPleasantCognitive());	
			return counts;
		}			
		
		public int getFiredPublicPersonalSecurityPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSecurityPleasantCognitive());		
			return counts ;
		}
		public int getFiredCyclePersonalSecurityPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSecurityPleasantCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSecurityPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSecurityPleasantCognitive());		
			return counts ;
		}	
///////The following are the list of individual level of PCA-PHYSICAL
		public ArrayList<Double> firedPersonalSecurityUnpleasantPhysical() {				
			personalSecurityUnpleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityUnpleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);
		//	System.out.println("The Unpleasant Personal Security Physical Values:");
			for (Double antecedents :personalSecurityUnpleasantPhysicalList){
		//	System.out.println(antecedents);
			} 			 
			return personalSecurityUnpleasantPhysicalList;			
		}
			
		public ArrayList<Double> firedPersonalSecurityNeitherNorPleasantPhysical() {				
			personalSecurityNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);
		//	System.out.println("The Neither pleasant nor unpleasant Personal Security Physical Values:");
			for (Double antecedents :personalSecurityNeitherNorPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return personalSecurityNeitherNorPleasantPhysicalList;			
		}
				
		public ArrayList<Double> firedPersonalSecurityPleasantPhysical() {				
			personalSecurityPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);
		//	System.out.println("The Pleasant Personal Security Physical Values:");
			for (Double antecedents :personalSecurityPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return personalSecurityPleasantPhysicalList	;			
		}
				
///////The following are the list of individual level of PCA-AFFECTIVE
		
		public ArrayList<Double> firedPersonalSecurityAffective() {
			personalSecurityAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);
			personalSecurityAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);						 
			return personalSecurityAffectiveList	;			
		}
		public double getFiredPrivatePersonalSecurityAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedPersonalSecurityAffective());
			return privateAffective;
		}
		public double getFiredPublicPersonalSecurityAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedPersonalSecurityAffective());
			return publicAffective;
		}
		public double getFiredCyclePersonalSecurityAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedPersonalSecurityAffective());
			return cycleAffective;
		}
		public double getFiredWalkingPersonalSecurityAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedPersonalSecurityAffective());
			return walkingAffective;
		}
///Counts
		public int getFiredPrivatePersonalSecurityAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSecurityAffective());	
			return counts;
		}			
		
		public int getFiredPublicPersonalSecurityAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSecurityAffective());		
			return counts ;
		}
		public int getFiredCyclePersonalSecurityAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSecurityAffective());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSecurityAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSecurityAffective());		
			return counts ;
		}
////
		public ArrayList<Double> firedPersonalSecurityUnpleasantAffective() {				
			personalSecurityUnpleasentAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);				 
			return personalSecurityUnpleasentAffectiveList;			
		}
		public double getFiredPrivatePersonalSecurityUnpleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedPersonalSecurityUnpleasantAffective());
			return privateAffective;
		}
		public double getFiredPublicPersonalSecurityUnpleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedPersonalSecurityUnpleasantAffective());
			return publicAffective;
		}
		public double getFiredCyclePersonalSecurityUnpleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedPersonalSecurityUnpleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingPersonalSecurityUnpleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedPersonalSecurityUnpleasantAffective());
			return walkingAffective;
		}
///Counts
		public int getFiredPrivatePersonalSecurityUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSecurityUnpleasantAffective());	
			return counts;
		}			
		
		public int getFiredPublicPersonalSecurityUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSecurityUnpleasantAffective());		
			return counts ;
		}
		public int getFiredCyclePersonalSecurityUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSecurityUnpleasantAffective());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSecurityUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSecurityUnpleasantAffective());		
			return counts ;
		}							
		public ArrayList<Double> firedPersonalSecurityNeitherNorPleasantAffective() {				
			personalSecurityNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
			return personalSecurityNeitherNorPleasantAffectiveList ;			
		}
		public double getFiredPrivatePersonalSecurityNeitherNorPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedPersonalSecurityNeitherNorPleasantAffective());
			return privateAffective;
		}
		public double getFiredPublicPersonalSecurityNeitherNorPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedPersonalSecurityNeitherNorPleasantAffective());
			return publicAffective;
		}
		public double getFiredCyclePersonalSecurityNeitherNorPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedPersonalSecurityNeitherNorPleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingPersonalSecurityNeitherNorPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedPersonalSecurityNeitherNorPleasantAffective());
			return walkingAffective;
		}
///Counts
		public int getFiredPrivatePersonalSecurityNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSecurityNeitherNorPleasantAffective());	
			return counts;
		}			
		
		public int getFiredPublicPersonalSecurityNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSecurityNeitherNorPleasantAffective());		
			return counts ;
		}
		public int getFiredCyclePersonalSecurityNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSecurityNeitherNorPleasantAffective());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSecurityNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSecurityNeitherNorPleasantAffective());		
			return counts ;
		}								
		public ArrayList<Double> firedPersonalSecurityPleasantAffective() {				
			personalSecurityPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getPersonalSecurityDecisionVariables().get(1);				
			personalSecurityPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);						 
			return personalSecurityPleasantAffectiveList	;			
		}
		public double getFiredPrivatePersonalSecurityPleasantAffective(){
			double privateAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateAffective =pca.firedTravelDemands(firedPersonalSecurityPleasantAffective());
			return privateAffective;
		}
		public double getFiredPublicPersonalSecurityPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedPersonalSecurityPleasantAffective());
			return publicAffective;
		}
		public double getFiredCyclePersonalSecurityPleasantAffective(){
			double cycleAffective =0.0;
			if(prefferedMode instanceof Cycle)
				cycleAffective = pca.firedTravelDemands(firedPersonalSecurityPleasantAffective());
			return cycleAffective;
		}
		public double getFiredWalkingPersonalSecurityPleasantAffective(){
			double walkingAffective =0.0;
			if(prefferedMode instanceof Walking)
				walkingAffective = pca.firedTravelDemands(firedPersonalSecurityPleasantAffective());
			return walkingAffective;
		}
///Counts
		public int getFiredPrivatePersonalSecurityPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedPersonalSecurityPleasantAffective());	
			return counts;
		}			
		
		public int getFiredPublicPersonalSecurityPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedPersonalSecurityPleasantAffective());		
			return counts ;
		}
		public int getFiredCyclePersonalSecurityPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedPersonalSecurityPleasantAffective());	
			return counts;
		}			
		
		public int getFiredWalkingPersonalSecurityPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedPersonalSecurityPleasantAffective());		
			return counts ;
		}
}
