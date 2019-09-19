package moshproject.agent.mode.attributes;

import java.util.ArrayList;
import java.util.HashMap;
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

public class EaseOfAccessInformation {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	
	
	
	private double modeEaseOfAccessInfoPhysical;
	private double modeEaseOfAccessInfoCognitive;
	private double publicModeEaseOfAccessInfoCognitive;
	private double privateModeEaseOfAccessInfoCognitive;
	private double modeEaseOfAccessInfoAffective;
	private double publicModeEaseOfAccessInfoAffective;
	private double privateEaseOfAccessInfo;
	private double publicEaseOfAccessInfo;
	private double privateModeEaseOfAccessInfoAffective;
	private double modeEaseOfAccessInfo;
	private Vector easeOfAccessInformationDecisionVariables;
	private ArrayList<Double> easeOfAccessInfoCognitiveList;
	private ArrayList<Double> easeOfAccessInfoUnpleasantCognitiveList;
	private ArrayList<Double> easeOfAccessInfoNeitherNorPleasantCognitiveList;
	private ArrayList<Double> easeOfAccessInfoPleasantCognitiveList;
	private ArrayList<Double> easeOfAccessInfoUnpleasentPhysicalList;
	private ArrayList<Double> easeOfAccessInfoNeitherNorPleasantPhysicalList;
	private ArrayList<Double> easeOfAccessInfoPleasantPhysicalList;
	private ArrayList<Double> easeOfAccessInfoUnpleasentAffectiveList;
	private ArrayList<Double> easeOfAccessInfoNeitherNorPleasantAffectiveList;
	private ArrayList<Double> easeOfAccessInfoPleasantAffectiveList;
	private ArrayList<Double> easeOfAccessInfoPhysicalList;
	private ArrayList<Double> easeOfAccessInfoAffectiveList;
	private double cycleEaseOfAccessInfo;
	private double walkingEaseOfAccessInfo;

	public EaseOfAccessInformation(Mode preferredMode){
		this.prefferedMode = preferredMode;	
		updateEaseOfAccessInfo();
		pca= new FuzzyDecisionVariables();
	}
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}	
	
	private double updateEaseOfAccessInfoPhysical() {			
		return modeEaseOfAccessInfoPhysical = prefferedMode.setValueToAttribute(Constants.easeOfAccessInfoPhysical,0);								
	}		
	public double getModeEaseOfAccessInfoPhysical() {
		updateEaseOfAccessInfoPhysical();
		return modeEaseOfAccessInfoPhysical;
	}

	private double updateEaseOfAccessInfoCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			modeEaseOfAccessInfoCognitive = prefferedMode.getValueOfAttribute(Constants.easeOfAccessInfoPerception);				
		}else if (prefferedMode instanceof PublicTransport){
			modeEaseOfAccessInfoCognitive = prefferedMode.getValueOfAttribute(Constants.easeOfAccessInfoPerception);											
		}else if (prefferedMode instanceof Cycle){
			modeEaseOfAccessInfoCognitive = prefferedMode.getValueOfAttribute(Constants.easeOfAccessInfoPerception);								
		}else if (prefferedMode instanceof Walking){
			modeEaseOfAccessInfoCognitive = prefferedMode.getValueOfAttribute(Constants.easeOfAccessInfoPerception);													
		}	
		return modeEaseOfAccessInfoCognitive;			
	}
	public double getModeEaseOfAccessInfoCognitive() {
		updateEaseOfAccessInfoCognitive();
		return modeEaseOfAccessInfoCognitive;
	}	
	private double updateEaseOfAccessInfoAffective() {	
		if (prefferedMode instanceof PersonalVehicle)	{
			modeEaseOfAccessInfoAffective = prefferedMode.getValueOfAttribute(Constants.easeOfAccessInfoAffective);				
		}else if (prefferedMode instanceof PublicTransport){
			modeEaseOfAccessInfoAffective = prefferedMode.getValueOfAttribute(Constants.easeOfAccessInfoAffective);											
		}else if (prefferedMode instanceof Cycle){
			modeEaseOfAccessInfoAffective = prefferedMode.getValueOfAttribute(Constants.easeOfAccessInfoAffective);								
		}else if (prefferedMode instanceof Walking){
			modeEaseOfAccessInfoAffective = prefferedMode.getValueOfAttribute(Constants.easeOfAccessInfoAffective);													
		}	
		return modeEaseOfAccessInfoAffective;			
	}	
	
		public double getModeEaseOfAccessInfoAffective() {
			updateEaseOfAccessInfoAffective();
		return modeEaseOfAccessInfoAffective;
	}

		////////The Fuzzy System Version
		public Vector easeOfAccessInfoValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();
			if(prefferedMode instanceof PersonalVehicle){
				getModeEaseOfAccessInfoPhysical();
				getModeEaseOfAccessInfoCognitive();
				getModeEaseOfAccessInfoAffective();			
			}else if (prefferedMode instanceof PublicTransport){
				getModeEaseOfAccessInfoPhysical();
				getModeEaseOfAccessInfoCognitive();
				getModeEaseOfAccessInfoAffective();					
			}	else if (prefferedMode instanceof Cycle){
				getModeEaseOfAccessInfoPhysical();
				getModeEaseOfAccessInfoCognitive();
				getModeEaseOfAccessInfoAffective();					
			}	else if (prefferedMode instanceof Walking){
				getModeEaseOfAccessInfoPhysical();
				getModeEaseOfAccessInfoCognitive();
				getModeEaseOfAccessInfoAffective();				
			}					
			return easeOfAccessInformationDecisionVariables=evaluateThreeVariablesSatisfaction.getTip(getModeEaseOfAccessInfoPhysical(),getModeEaseOfAccessInfoCognitive(), getModeEaseOfAccessInfoAffective());
		}	
		public Vector getEaseOfAccessInformationDecisionVariables() {
			return easeOfAccessInformationDecisionVariables;
		}

///This method returns either fuzzy method or the ordinary method
		public double updateEaseOfAccessInfo(){				
			easeOfAccessInfoValues();	
			modeEaseOfAccessInfo =(double)getEaseOfAccessInformationDecisionVariables().get(0);		
			return modeEaseOfAccessInfo ;
		}	
		
		public double getModeEaseOfAccessInfo() {
			return modeEaseOfAccessInfo;
		}
////Ease of Accessing Info by Modes
		public double getPrivateEaseOfAccessInfo(){
			if(prefferedMode instanceof PersonalVehicle){
				privateEaseOfAccessInfo = this.getModeEaseOfAccessInfo();					
			}
			return (double) privateEaseOfAccessInfo;
		}
		public double getPublicEaseOfAccessInfo(){
			if(prefferedMode instanceof PublicTransport){
				publicEaseOfAccessInfo = this.getModeEaseOfAccessInfo();					
			}	
			return (double) publicEaseOfAccessInfo;
		}
		
		public double getCycleEaseOfAccessInfo(){
			if(prefferedMode instanceof Cycle){
				cycleEaseOfAccessInfo = this.getModeEaseOfAccessInfo();					
			}
			return (double) cycleEaseOfAccessInfo;
		}
		public double getWalkingEaseOfAccessInfo(){
			if(prefferedMode instanceof Walking){
				walkingEaseOfAccessInfo = this.getModeEaseOfAccessInfo();					
			}	
			return (double) walkingEaseOfAccessInfo;
		}
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
//All Cognitive
		public ArrayList<Double> firedEaseOfAccessInfoCognitive() {
			easeOfAccessInfoCognitiveList = new ArrayList<>();		
			otherVariables = (Map<String, List<Double>>)getEaseOfAccessInformationDecisionVariables().get(1);		
			easeOfAccessInfoCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);	 				 
			return easeOfAccessInfoCognitiveList	;			
		}
		
////Cognitive Travel demands by Modes
		public double getFiredPrivateEaseOfAccessInfoCognitive(){
			double privateCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedEaseOfAccessInfoCognitive());	
			return privateCognitive;
		}
		
		public double getFiredPublicEaseOfAccessInfoCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedEaseOfAccessInfoCognitive());	
			return publicCognitive;
		}
		
		public double getFiredCycleEaseOfAccessInfoCognitive(){
			double cycleCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedEaseOfAccessInfoCognitive());	
			return cycleCognitive;
		}
		
		public double getFiredWalkingEaseOfAccessInfoCognitive(){
			double walkingCognitive =0;;
			if(prefferedMode instanceof Walking)
				walkingCognitive = pca.firedTravelDemands(firedEaseOfAccessInfoCognitive());	
			return walkingCognitive;
		}
///cognitive counts by mode
		public int getFiredPrivateEaseOfAccessInfoCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfAccessInfoCognitive());	
			return counts;
		}			
		
		public int getFiredPublicEaseOfAccessInfoCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfAccessInfoCognitive());		
			return counts ;
		}
		public int getFiredCycleEaseOfAccessInfoCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfAccessInfoCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingEaseOfAccessInfoCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfAccessInfoCognitive());		
			return counts ;
		}

///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedEaseOfAccessInfoUnpleasantCognitive() {				
			easeOfAccessInfoUnpleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfAccessInformationDecisionVariables() .get(1);				
			easeOfAccessInfoUnpleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);	 			 
			return easeOfAccessInfoUnpleasantCognitiveList;			
		}
///Unpleasant cognitive satisfaction levels
		public double getFiredPrivateEaseOfAccessInfoUnpleasantCognitive(){
			double unPleasantCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				unPleasantCognitive =pca.firedTravelDemands(firedEaseOfAccessInfoUnpleasantCognitive());	
			return unPleasantCognitive;
		}
		
		public double getFiredPublicEaseOfAccessInfoUnpleasantCognitive(){
			double unPleasantCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				unPleasantCognitive = pca.firedTravelDemands(firedEaseOfAccessInfoUnpleasantCognitive());
			return unPleasantCognitive;
		}
		public double getFiredCycleEaseOfAccessInfoUnpleasantCognitive(){
			double unPleasantCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				unPleasantCognitive =pca.firedTravelDemands(firedEaseOfAccessInfoUnpleasantCognitive());	
			return unPleasantCognitive;
		}
		
		public double getFiredWalkingEaseOfAccessInfoUnpleasantCognitive(){
			double unPleasantCognitive =0;;
			if(prefferedMode instanceof Walking)
				unPleasantCognitive = pca.firedTravelDemands(firedEaseOfAccessInfoUnpleasantCognitive());
			return unPleasantCognitive;
		}
///Unpleasant cognitive satisfaction levels counts
		public int getFiredPrivateEaseOfAccessInfoUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfAccessInfoUnpleasantCognitive());	
			return counts;
		}			
		public int getFiredPublicEaseOfAccessInfoUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfAccessInfoUnpleasantCognitive());		
			return counts ;
		}
		public int getFiredCycleEaseOfAccessInfoUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfAccessInfoUnpleasantCognitive());	
			return counts;
		}			
		public int getFiredWalkingEaseOfAccessInfoUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfAccessInfoUnpleasantCognitive());		
			return counts ;
		}
	//////	
		public ArrayList<Double> firedEaseOfAccessInfoNeitherNorPleasantCognitive() {				
			easeOfAccessInfoNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfAccessInformationDecisionVariables() .get(1);				
			easeOfAccessInfoNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);				 
			return easeOfAccessInfoNeitherNorPleasantCognitiveList	;			
		}
///NeitherPleasantNorUnpleasant cognitive satisfaction levels
		public double getFiredPrivateEaseOfAccessInfoNeitherNorPleasantCognitive(){
			double neitherPleasantNorUnpleasantCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				neitherPleasantNorUnpleasantCognitive =pca.firedTravelDemands(firedEaseOfAccessInfoNeitherNorPleasantCognitive());
			return neitherPleasantNorUnpleasantCognitive;
		}
		
		public double getFiredPublicEaseOfAccessInfoNeitherNorPleasantCognitive(){
			double neitherPleasantNorUnpleasantCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				neitherPleasantNorUnpleasantCognitive = pca.firedTravelDemands(firedEaseOfAccessInfoNeitherNorPleasantCognitive());	
			return neitherPleasantNorUnpleasantCognitive;
		}
		public double getFiredCycleEaseOfAccessInfoNeitherNorPleasantCognitive(){
			double neitherPleasantNorUnpleasantCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				neitherPleasantNorUnpleasantCognitive =pca.firedTravelDemands(firedEaseOfAccessInfoNeitherNorPleasantCognitive());
			return neitherPleasantNorUnpleasantCognitive;
		}
		
		public double getFiredWalkingEaseOfAccessInfoNeitherNorPleasantCognitive(){
			double neitherPleasantNorUnpleasantCognitive =0;;
			if(prefferedMode instanceof Walking)
				neitherPleasantNorUnpleasantCognitive = pca.firedTravelDemands(firedEaseOfAccessInfoNeitherNorPleasantCognitive());	
			return neitherPleasantNorUnpleasantCognitive;
		}
//NeitherPleasantNorUnpleasant cognitive levels counts
		public int getFiredPrivateEaseOfAccessInfoNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfAccessInfoNeitherNorPleasantCognitive());	
			return counts;
		}			
		
		public int getFiredPublicEaseOfAccessInfoNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfAccessInfoNeitherNorPleasantCognitive());		
			return counts ;
		}
		public int getFiredCycleEaseOfAccessInfoNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfAccessInfoNeitherNorPleasantCognitive());	
			return counts;
		}			
		
		public int getFiredWalkingEaseOfAccessInfoNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfAccessInfoNeitherNorPleasantCognitive());		
			return counts ;
		}
	////////		
		public ArrayList<Double> firedEaseOfAccessInfoPleasantCognitive() {				
			easeOfAccessInfoPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfAccessInformationDecisionVariables() .get(1);				
			easeOfAccessInfoPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);	 				 
			return easeOfAccessInfoPleasantCognitiveList	;			
		}
///Pleasant cognitive satisfaction levels
		public double getFiredPrivateEaseOfAccessInfoPleasantCognitive(){
			double pleasantCognitive =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				pleasantCognitive =pca.firedTravelDemands(firedEaseOfAccessInfoPleasantCognitive());	
			return pleasantCognitive;
		}
		
		public double getFiredPublicEaseOfAccessInfoPleasantCognitive(){
			double pleasantCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				pleasantCognitive = pca.firedTravelDemands(firedEaseOfAccessInfoPleasantCognitive());
			return pleasantCognitive;
		}
		public double getFiredCycleEaseOfAccessInfoPleasantCognitive(){
			double pleasantCognitive =0.0;
			if(prefferedMode instanceof Cycle)
				pleasantCognitive =pca.firedTravelDemands(firedEaseOfAccessInfoPleasantCognitive());	
			return pleasantCognitive;
		}
		
		public double getFiredWalkingEaseOfAccessInfoPleasantCognitive(){
			double pleasantCognitive =0;;
			if(prefferedMode instanceof Walking)
				pleasantCognitive = pca.firedTravelDemands(firedEaseOfAccessInfoPleasantCognitive());
			return pleasantCognitive;
		}
///Pleasant cognitive levels counts
		public int getFiredPrivateEaseOfAccessInfoPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfAccessInfoPleasantCognitive() );	
			return counts;
		}			
		
		public int getFiredPublicEaseOfAccessInfoPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfAccessInfoPleasantCognitive() );		
			return counts ;
		}
		public int getFiredCycleEaseOfAccessInfoPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfAccessInfoPleasantCognitive() );	
			return counts;
		}			
		
		public int getFiredWalkingEaseOfAccessInfoPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfAccessInfoPleasantCognitive() );		
			return counts ;
		}
			
///////The following are the list of individual level of PCA-PHYSICAL
	///////The following are the list of individual level of PCA-PHYSICAL
		public ArrayList<Double> firedEaseOfAccessInfoPhysical() {
					easeOfAccessInfoPhysicalList = new ArrayList<>();
					otherVariables = (Map<String, List<Double>>) getEaseOfAccessInformationDecisionVariables() .get(1);	
					easeOfAccessInfoPhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);						 
					return easeOfAccessInfoPhysicalList	;			
				}		
		public ArrayList<Double> firedEaseOfAccessInfoUnpleasantPhysical() {				
			easeOfAccessInfoUnpleasentPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfAccessInformationDecisionVariables().get(1);				
			easeOfAccessInfoUnpleasentPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);
		//	System.out.println("The Unpleasant Ease of Access Physical Values:");
			for (Double antecedents :easeOfAccessInfoUnpleasentPhysicalList){
		//	System.out.println(antecedents);
			} 			 
			return easeOfAccessInfoUnpleasentPhysicalList;			
		}
			
		public ArrayList<Double> firedEaseOfAccessInfoNeitherNorPleasantPhysical() {				
			easeOfAccessInfoNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfAccessInformationDecisionVariables().get(1);				
			easeOfAccessInfoNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);
		//	System.out.println("The Neither pleasant nor unpleasant Ease of Access  Physical Values:");
			for (Double antecedents :easeOfAccessInfoNeitherNorPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return easeOfAccessInfoNeitherNorPleasantPhysicalList;			
		}
				
		public ArrayList<Double> firedEaseOfAccessInfoPleasantPhysical() {				
			easeOfAccessInfoPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfAccessInformationDecisionVariables().get(1);				
			easeOfAccessInfoPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);
		//	System.out.println("The Pleasant Ease of Access Physical Values:");
			for (Double antecedents :easeOfAccessInfoPleasantPhysicalList){
		//	System.out.println(antecedents);
			} 				 
			return easeOfAccessInfoPleasantPhysicalList	;			
		}
				
///////The following are the list of individual level of PCA-AFFECTIVE
///All Affective		
		public ArrayList<Double> firedEaseOfAccessInfoAffective() {
			easeOfAccessInfoAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfAccessInformationDecisionVariables().get(1);
			easeOfAccessInfoAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);						 
			return easeOfAccessInfoAffectiveList;			
		}
///// All Affective satisfaction level
		public double getFiredPrivateEaseOfAccessAffective(){
			double eoiAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				eoiAffective =pca.firedTravelDemands(firedEaseOfAccessInfoAffective());		
			return eoiAffective;
		}		
		public double getFiredPublicEaseOfAccessAffective(){
			double eoiAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				eoiAffective = pca.firedTravelDemands(firedEaseOfAccessInfoAffective());		
			return eoiAffective;
		}
		public double getFiredCycleEaseOfAccessAffective(){
			double eoiAffective =0.0;
			if(prefferedMode instanceof Cycle)
				eoiAffective =pca.firedTravelDemands(firedEaseOfAccessInfoAffective());		
			return eoiAffective;
		}		
		public double getFiredWalkingEaseOfAccessAffective(){
			double eoiAffective =0.0;
			if(prefferedMode instanceof Walking)
				eoiAffective = pca.firedTravelDemands(firedEaseOfAccessInfoAffective());		
			return eoiAffective;
		}
/////All Affective satisfaction counts
		public int getFiredPrivateEaseOfAccessInfoAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfAccessInfoAffective());		
			return counts;
		}			
		public int getFiredPublicEaseOfAccessInfoAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfAccessInfoAffective());	
			return counts ;
		}	
		public int getFiredCycleEaseOfAccessInfoAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfAccessInfoAffective());		
			return counts;
		}			
		public int getFiredWalkingEaseOfAccessInfoAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfAccessInfoAffective());	
			return counts ;
		}	
///// Unpleasant Affective			
		public ArrayList<Double> firedEaseOfAccessInfoUnpleasantAffective() {				
			easeOfAccessInfoUnpleasentAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfAccessInformationDecisionVariables().get(1);				
			easeOfAccessInfoUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);					 
			return easeOfAccessInfoUnpleasentAffectiveList;			
		}
///// Unpleasant Affective satisfaction level					
		public double getFiredPrivateEaseOfAccessInfoUnpleasantAffective(){
			double eoiUnpleasantAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				eoiUnpleasantAffective =pca.firedTravelDemands(firedEaseOfAccessInfoUnpleasantAffective());	
			return eoiUnpleasantAffective;
		}		
		public double getFiredPublicEaseOfAccessInfoUnpleasantAffective(){
			double eoiUnpleasantAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				eoiUnpleasantAffective = pca.firedTravelDemands(firedEaseOfAccessInfoUnpleasantAffective());	
			return eoiUnpleasantAffective;
		}
		public double getFiredCycleEaseOfAccessInfoUnpleasantAffective(){
			double eoiUnpleasantAffective =0.0;
			if(prefferedMode instanceof Cycle)
				eoiUnpleasantAffective =pca.firedTravelDemands(firedEaseOfAccessInfoUnpleasantAffective());	
			return eoiUnpleasantAffective;
		}		
		public double getFiredWalkingEaseOfAccessInfoUnpleasantAffective(){
			double eoiUnpleasantAffective =0;;
			if(prefferedMode instanceof Walking)
				eoiUnpleasantAffective = pca.firedTravelDemands(firedEaseOfAccessInfoUnpleasantAffective());	
			return eoiUnpleasantAffective;
		}
/////Unpleasant Affective satisfaction counts
		public int getFiredPrivateEaseOfAccessInfoUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfAccessInfoUnpleasantAffective());	
			return counts;
		}		
		public int getFiredPublicEaseOfAccessInfoUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfAccessInfoUnpleasantAffective());		
			return counts ;
		}
		public int getFiredCycleEaseOfAccessInfoUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfAccessInfoUnpleasantAffective());	
			return counts;
		}		
		public int getFiredWalkingEaseOfAccessInfoUnpleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfAccessInfoUnpleasantAffective());		
			return counts ;
		}
		public ArrayList<Double> firedEaseOfAccessInfoNeitherNorPleasantAffective() {				
			easeOfAccessInfoNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfAccessInformationDecisionVariables().get(1);				
			easeOfAccessInfoNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);			 				 
			return easeOfAccessInfoNeitherNorPleasantAffectiveList ;			
		}
///// NeitherPleasantNorUnpleasant Affective satisfaction level	
		public double getFiredPrivateEaseOfAccessInfoNeitherNorPleasantAffective(){
			double eoiNeitherNorPleasantAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				eoiNeitherNorPleasantAffective =pca.firedTravelDemands(firedEaseOfAccessInfoNeitherNorPleasantAffective());
			return eoiNeitherNorPleasantAffective;
		}		
		public double getFiredPublicEaseOfAccessInfoNeitherNorPleasantAffective(){
			double eoiNeitherNorPleasantAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				eoiNeitherNorPleasantAffective = pca.firedTravelDemands(firedEaseOfAccessInfoNeitherNorPleasantAffective());	
			return eoiNeitherNorPleasantAffective;
		}
		public double getFiredCycleEaseOfAccessInfoNeitherNorPleasantAffective(){
			double eoiNeitherNorPleasantAffective =0.0;
			if(prefferedMode instanceof Cycle)
				eoiNeitherNorPleasantAffective =pca.firedTravelDemands(firedEaseOfAccessInfoNeitherNorPleasantAffective());
			return eoiNeitherNorPleasantAffective;
		}		
		public double getFiredWalkingEaseOfAccessInfoNeitherNorPleasantAffective(){
			double eoiNeitherNorPleasantAffective =0;;
			if(prefferedMode instanceof Walking)
				eoiNeitherNorPleasantAffective = pca.firedTravelDemands(firedEaseOfAccessInfoNeitherNorPleasantAffective());	
			return eoiNeitherNorPleasantAffective;
		}
/////NEitherPleasantNorUnpleasant Affective satisfaction counts
		public int getFiredPrivateEaseOfAccessInfoNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfAccessInfoNeitherNorPleasantAffective());		
			return counts;
		}		
		public int getFiredPublicEaseOfAccessInfoNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfAccessInfoNeitherNorPleasantAffective());		
			return counts ;
		}
		public int getFiredCycleEaseOfAccessInfoNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfAccessInfoNeitherNorPleasantAffective());		
			return counts;
		}		
		public int getFiredWalkingEaseOfAccessInfoNeitherNorPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfAccessInfoNeitherNorPleasantAffective());		
			return counts ;
		}
////////Pleasant Affective
		public ArrayList<Double> firedEaseOfAccessInfoPleasantAffective() {				
			easeOfAccessInfoPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfAccessInformationDecisionVariables().get(1);				
			easeOfAccessInfoPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);		 				 
			return easeOfAccessInfoPleasantAffectiveList	;			
		}
///// Pleasant Affective satisfaction level		
		public double getFiredPrivateEaseOfAccessInfoPleasantAffective(){
			double eoiPleasantAffective =0.0;
			if(prefferedMode instanceof PersonalVehicle)
				eoiPleasantAffective =pca.firedTravelDemands(firedEaseOfAccessInfoPleasantAffective());
			return eoiPleasantAffective;
		}		
		public double getFiredPublicEaseOfAccessInfoPleasantAffective(){
			double eoiPleasantAffective =0;;
			if(prefferedMode instanceof PublicTransport)
				eoiPleasantAffective = pca.firedTravelDemands(firedEaseOfAccessInfoPleasantAffective());	
			return eoiPleasantAffective;
		}
		public double getFiredCycleEaseOfAccessInfoPleasantAffective(){
			double eoiPleasantAffective =0.0;
			if(prefferedMode instanceof Cycle)
				eoiPleasantAffective =pca.firedTravelDemands(firedEaseOfAccessInfoPleasantAffective());
			return eoiPleasantAffective;
		}		
		public double getFiredWalkingEaseOfAccessInfoPleasantAffective(){
			double eoiPleasantAffective =0;;
			if(prefferedMode instanceof Walking)
				eoiPleasantAffective = pca.firedTravelDemands(firedEaseOfAccessInfoPleasantAffective());	
			return eoiPleasantAffective;
		}
/////Pleasant Affective satisfaction counts
		public int getFiredPrivateEaseOfAccessInfoPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PersonalVehicle)
				counts =pca.firedCounts(firedEaseOfAccessInfoPleasantAffective());		
			return counts;
		}		
		public int getFiredPublicEaseOfAccessInfoPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfAccessInfoPleasantAffective());		
			return counts ;
		}	public int getFiredCycleEaseOfAccessInfoPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Cycle)
				counts =pca.firedCounts(firedEaseOfAccessInfoPleasantAffective());		
			return counts;
		}		
		public int getFiredWalkingEaseOfAccessInfoPleasantAffectiveCounts(){
			int counts =0;
			if(prefferedMode instanceof Walking)
				counts  = pca.firedCounts(firedEaseOfAccessInfoPleasantAffective());		
			return counts ;
		}	
	

}
