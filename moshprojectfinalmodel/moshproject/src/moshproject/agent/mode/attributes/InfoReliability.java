package moshproject.agent.mode.attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import moshproject.agents.mode.Cycle;
import moshproject.agents.mode.Mode;
import moshproject.agents.mode.PersonalVehicle;
import moshproject.agents.mode.PublicTransport;
import moshproject.agents.mode.Walking;
import moshproject.agents.passenger.Passenger;
import moshproject.common.Constants;
import moshproject.common.FuzzyDecisionVariables;
//import moshproject.common.FuzzyDecisionVariables;
import moshproject.fuzzycollections.FuzzyDecisionGenerator;

	public class InfoReliability {
		Passenger passenger;
		Mode prefferedMode;
		FuzzyDecisionVariables pca; 
		
		private double modeReliabilityPhysical;
		private double modeReliabilityCognitive;
		private double publicModeReliabilityCognitive;
		private double privateModeReliabilityCognitive;
		private double modeReliabilityAffective;
		private double privateModeReliabilityAffective;
		private double publicModeReliabilityAffective;
		private double modeReliability;
		private ArrayList<Double> reliabilityCognitiveList;
		private ArrayList<Double> reliabilityPhysicalList;
		private ArrayList<Double> reliabiityAffectiveList;
		private double privateModeReliability;
		private double publicModeReliability;
		FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
		Map<String, List<Double>> otherVariables;		
		ArrayList<Double> weigthValues;	
		private Vector modeReliabilityDecisionVariables;
		private ArrayList<Double> reliabilityUnpleasentCognitiveList;
		private ArrayList<Double> reliabilityNeitherNorPleasantCognitiveList;
		private ArrayList<Double> reliabilityPleasantCognitiveList;
		private ArrayList<Double> reliabilityUnpleasentPhysicalList;
		private ArrayList<Double> reliabilityNeitherNorPleasantPhysicalList;
		private ArrayList<Double> reliabilityPleasantPhysicalList;
		private ArrayList<Double> reliabilityUnpleasentAffectiveList;
		private ArrayList<Double> reliabilityNeitherNorPleasantAffectiveList;
		private ArrayList<Double> reliabilityPleasantAffectiveList;
		private ArrayList<Double> reliabilityAffectiveList;
		private double privateAffective;
		private double cycleModeReliability;
		private double walkingModeReliability;

		public  InfoReliability(Mode prefferedMode){
			this.prefferedMode = prefferedMode;						
			pca= new FuzzyDecisionVariables();
			updateModeReliability() ;
		}
		
		public Mode getPrefferedMode() {
			return prefferedMode;
		}
		public void setPrefferedMode(Mode prefferedMode) {
			this.prefferedMode = prefferedMode;
		}
			
		private double updateModeReliabilityPhysical() {			
			return modeReliabilityPhysical = prefferedMode.setValueToAttribute(Constants.infoReliabilityPhysical, 0);																	
		}			
		public double getModeReliabilityPhysical() {
			updateModeReliabilityPhysical();
			return modeReliabilityPhysical;
		}

			public double updateModeReliabilityCognitive() {			
				if (prefferedMode instanceof PersonalVehicle)	{
					modeReliabilityCognitive = prefferedMode.getValueOfAttribute(Constants.infoReliabilityPerception);				
				}else if (prefferedMode instanceof PublicTransport){
					modeReliabilityCognitive = prefferedMode.getValueOfAttribute(Constants.infoReliabilityPerception);											
				}else if (prefferedMode instanceof Cycle){
					modeReliabilityCognitive = prefferedMode.getValueOfAttribute(Constants.infoReliabilityPerception);								
				}else if (prefferedMode instanceof Walking){
					modeReliabilityCognitive = prefferedMode.getValueOfAttribute(Constants.infoReliabilityPerception);													
				}			
				return modeReliabilityCognitive;			
			}					
			public double getModeReliabilityCognitive() {
				updateModeReliabilityCognitive();
				return modeReliabilityCognitive;
			}

			public double updateModeReliabilityAffective() {			
				if (prefferedMode instanceof PersonalVehicle)	{
					modeReliabilityAffective = prefferedMode.getValueOfAttribute(Constants.infoReliabilityAffective);				
				}else if (prefferedMode instanceof PublicTransport){
					modeReliabilityAffective = prefferedMode.getValueOfAttribute(Constants.infoReliabilityAffective);											
				}else if (prefferedMode instanceof Cycle){
					modeReliabilityAffective = prefferedMode.getValueOfAttribute(Constants.infoReliabilityAffective);								
				}else if (prefferedMode instanceof Walking){
					modeReliabilityAffective = prefferedMode.getValueOfAttribute(Constants.infoReliabilityAffective);													
				}				
				return modeReliabilityAffective;			
			}
			
			public double getModeReliabilityAffective() {
				updateModeReliabilityAffective();
				return modeReliabilityAffective;
			}

				////////The Fuzzy System Version
				public Vector modeReliabilityValues() {	
					evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();			
					if(prefferedMode instanceof PersonalVehicle){
						getModeReliabilityAffective();
						getModeReliabilityCognitive();
						getModeReliabilityPhysical();
					}else if (prefferedMode instanceof PublicTransport){
						getModeReliabilityAffective();
						getModeReliabilityCognitive();
						getModeReliabilityPhysical();
					}else if (prefferedMode instanceof Cycle){
						getModeReliabilityAffective();
						getModeReliabilityCognitive();
						getModeReliabilityPhysical();
					}else if (prefferedMode instanceof Walking){
						getModeReliabilityAffective();
						getModeReliabilityCognitive();
						getModeReliabilityPhysical();
					}				
					return modeReliabilityDecisionVariables =	evaluateThreeVariablesSatisfaction.getTip(getModeReliabilityPhysical(),getModeReliabilityCognitive(),getModeReliabilityAffective());
				}				

				public Vector getModeReliabilityDecisionVariables() {
					return modeReliabilityDecisionVariables;
				}

///This method returns either fuzzy method or the ordinary method
			public double updateModeReliability(){				
					modeReliabilityValues();	
					modeReliability =(double) getModeReliabilityDecisionVariables().get(0);				
					return modeReliability ;
			}	
			public double getModeReliability() {
				return modeReliability;
			}
			public double getPrivateModeReliability(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeReliability = this.getModeReliability();
				}	
				return (double) privateModeReliability;
			}			
			public double getPublicModeReliability(){
				if(prefferedMode instanceof PublicTransport){
					publicModeReliability = this.getModeReliability();
				}	
				return (double) publicModeReliability;
			}	
			public double getCycleModeReliability(){
				if(prefferedMode instanceof Cycle){
					cycleModeReliability = this.getModeReliability();
				}	
				return (double) cycleModeReliability;
			}			
			public double getWalkingModeReliability(){
				if(prefferedMode instanceof Walking){
					walkingModeReliability = this.getModeReliability();
				}	
				return (double) walkingModeReliability;
			}
//// This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		//ALL Cognitive
			public ArrayList<Double> firedModeReliabilityCognitive() {
				reliabilityCognitiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);
				reliabilityCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);				 
				return reliabilityCognitiveList	;			
			}
			public double getFiredPrivateReliabilityCognitive(){
				double privateCognitive =0.0;
				if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedModeReliabilityCognitive());		
				return privateCognitive;
			}			
			public double getFiredPublicReliabilityCognitive(){
				double publicCognitive =0;;
				if(prefferedMode instanceof PublicTransport)
					publicCognitive = pca.firedTravelDemands(firedModeReliabilityCognitive());				
				return publicCognitive;
			}
			public double getFiredCycleReliabilityCognitive(){
				double cycleCognitive =0.0;
				if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedModeReliabilityCognitive());		
				return cycleCognitive;
			}			
			public double getFiredWalkingReliabilityCognitive(){
				double walkingCognitive =0;;
				if(prefferedMode instanceof Walking)
					walkingCognitive = pca.firedTravelDemands(firedModeReliabilityCognitive());				
				return walkingCognitive;
			}
		//The Counts of all Cognitive that fired	
			public int getFiredPrivateReliabilityCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedModeReliabilityCognitive());			
				return counts;
			}				
			public int getFiredPublicReliabilityCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeReliabilityCognitive());			
				return counts ;
			}
			public int getFiredCycleReliabilityCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedModeReliabilityCognitive());			
				return counts;
			}				
			public int getFiredWalkingReliabilityCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedModeReliabilityCognitive());			
				return counts ;
			}
///////The following are the list of individual level of PCA-COGNITIVE: The Unpleasant, Pleasant, NeitherNor...
////Unpleasant Cognitive
			public ArrayList<Double> firedModeReliabilityUnpleasantCognitive() {				
				reliabilityUnpleasentCognitiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);			 
				return reliabilityUnpleasentCognitiveList	;			
			}
			public double getFiredPrivateReliabilityUnpleasantCognitive(){
				double privateCognitive =0.0;
				if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedModeReliabilityUnpleasantCognitive());			
				return privateCognitive;
			}			
			public double getFiredPublicReliabilityUnpleasantCognitive(){
				double publicCognitive =0;;
				if(prefferedMode instanceof PublicTransport)
					publicCognitive = pca.firedTravelDemands(firedModeReliabilityUnpleasantCognitive());
				return publicCognitive;
			}
			public double getFiredCycleReliabilityUnpleasantCognitive(){
				double cycleCognitive =0.0;
				if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedModeReliabilityUnpleasantCognitive());			
				return cycleCognitive;
			}			
			public double getFiredWalkingReliabilityUnpleasantCognitive(){
				double walkingCognitive =0;;
				if(prefferedMode instanceof Walking)
					walkingCognitive = pca.firedTravelDemands(firedModeReliabilityUnpleasantCognitive());
				return walkingCognitive;
			}
			public int getFiredPrivateReliabilityUnpleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedModeReliabilityUnpleasantCognitive());
				return counts;
			}			
			public int getFiredPublicReliabilityUnpleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeReliabilityUnpleasantCognitive());
				return counts ;
			}
			public int getFiredCycleReliabilityUnpleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedModeReliabilityUnpleasantCognitive());
				return counts;
			}			
			public int getFiredWalkingReliabilityUnpleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedModeReliabilityUnpleasantCognitive());
				return counts ;
			}
			
//////////NeitherPleasantNorUnpleasant Cognitive
			public ArrayList<Double> firedModeReliabilityNeitherNorPleasantCognitive() {				
				reliabilityNeitherNorPleasantCognitiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);				
				reliabilityNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);						 
				return reliabilityNeitherNorPleasantCognitiveList	;			
			}
			public double getFiredPrivateReliabilityNeitherNorPleasantCognitive(){
				double privateCognitive =0.0;
				if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedModeReliabilityNeitherNorPleasantCognitive());
				return privateCognitive;
			}			
			public double getFiredPublicReliabilityNeitherNorPleasantCognitive(){
				double publicCognitive =0;;
				if(prefferedMode instanceof PublicTransport)
					publicCognitive = pca.firedTravelDemands(firedModeReliabilityNeitherNorPleasantCognitive());
				return publicCognitive;
			}
			public double getFiredCycleReliabilityNeitherNorPleasantCognitive(){
				double cycleCognitive =0.0;
				if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedModeReliabilityNeitherNorPleasantCognitive());
				return cycleCognitive;
			}			
			public double getFiredWalkingReliabilityNeitherNorPleasantCognitive(){
				double walkingCognitive =0;;
				if(prefferedMode instanceof Walking)
					walkingCognitive = pca.firedTravelDemands(firedModeReliabilityNeitherNorPleasantCognitive());
				return walkingCognitive;
			}
			public int getFiredPrivateReliabilityNeitherPleasantUnpleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedModeReliabilityNeitherNorPleasantCognitive());
				return counts;
			}			
			public int getFiredPublicReliabilityNeitherPleasantUnpleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeReliabilityNeitherNorPleasantCognitive());
				return counts ;
			}
			public int getFiredCycleReliabilityNeitherPleasantUnpleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedModeReliabilityNeitherNorPleasantCognitive());
				return counts;
			}			
			public int getFiredWalkingReliabilityNeitherPleasantUnpleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedModeReliabilityNeitherNorPleasantCognitive());
				return counts ;
			}
	/////Pleasant Cognitive
			public ArrayList<Double> firedModeReliabilityPleasantCognitive() {				
				reliabilityPleasantCognitiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);						 
				return reliabilityPleasantCognitiveList	;			
			}
			public double getFiredPrivateReliabilityPleasantCognitive(){
				double privateCognitive =0.0;
				if(prefferedMode instanceof PersonalVehicle)
				privateCognitive =pca.firedTravelDemands(firedModeReliabilityPleasantCognitive());
				return privateCognitive;
			}			
			public double getFiredPublicReliabilityPleasantCognitive(){
				double publicCognitive =0;;
				if(prefferedMode instanceof PublicTransport)
					publicCognitive = pca.firedTravelDemands(firedModeReliabilityPleasantCognitive());
				return publicCognitive;
			}
			public double getFiredCycleReliabilityPleasantCognitive(){
				double cycleCognitive =0.0;
				if(prefferedMode instanceof Cycle)
				cycleCognitive =pca.firedTravelDemands(firedModeReliabilityPleasantCognitive());
				return cycleCognitive;
			}			
			public double getFiredWalkingReliabilityPleasantCognitive(){
				double walkingCognitive =0;;
				if(prefferedMode instanceof Walking)
					walkingCognitive = pca.firedTravelDemands(firedModeReliabilityPleasantCognitive());
				return walkingCognitive;
			}
			public int getFiredPrivateReliabilityPleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedModeReliabilityPleasantCognitive());
				return counts;
			}			
			public int getFiredPublicReliabilityPleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeReliabilityPleasantCognitive());
				return counts ;
			}
			public int getFiredCycleReliabilityPleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedModeReliabilityPleasantCognitive());
				return counts;
			}			
			public int getFiredWalkingReliabilityPleasantCognitiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedModeReliabilityPleasantCognitive());
				return counts ;
			}
				
///////The following are the list of individual level of PCA-PHYSICAL
			public ArrayList<Double> firedModeReliabilityPhysical() {
				reliabilityPhysicalList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);
				reliabilityPhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);						 
				return reliabilityPhysicalList	;			
			}		
			public ArrayList<Double> firedModeReliabilityUnpleasantPhysical() {				
				reliabilityUnpleasentPhysicalList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);				
				reliabilityUnpleasentPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);					 
				return reliabilityUnpleasentPhysicalList;			
			}				
			public ArrayList<Double> firedModeReliabilityNeitherNorPleasantPhysical() {				
				reliabilityNeitherNorPleasantPhysicalList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);				
				reliabilityNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);					 
				return reliabilityNeitherNorPleasantPhysicalList;			
			}
			public ArrayList<Double> firedModeReliabilityPleasantPhysical() {				
				reliabilityPleasantPhysicalList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);				
				reliabilityPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);						 
				return reliabilityPleasantPhysicalList	;			
			}
					
///////The following are the list of individual level of PCA-AFFECTIVE
			public ArrayList<Double> firedModeReliabilityAffective() {
				reliabilityAffectiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);
				reliabilityAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);						 
				return reliabilityAffectiveList	;			
			}
			public double getFiredPrivateReliabilityAffective(){
				double privateAffective =0.0;
				if(prefferedMode instanceof PersonalVehicle)
					privateAffective =pca.firedTravelDemands(firedModeReliabilityAffective());
				return privateAffective;
			}			
			public double getFiredPublicReliabilityAffective(){
				double publicAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					publicAffective = pca.firedTravelDemands(firedModeReliabilityAffective());
				return publicAffective;
			}
			public double getFiredCycleReliabilityAffective(){
				double cycleAffective =0.0;
				if(prefferedMode instanceof Cycle)
					cycleAffective =pca.firedTravelDemands(firedModeReliabilityAffective());
				return cycleAffective;
			}			
			public double getFiredWalkingReliabilityAffective(){
				double walkingAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					walkingAffective = pca.firedTravelDemands(firedModeReliabilityAffective());
				return walkingAffective;
			}
//The Counts of all Affective that fired	
			public int getFiredPrivateReliabilityAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedModeReliabilityAffective());
				return counts;
			}				
			public int getFiredPublicReliabilityAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeReliabilityAffective());
				return counts ;
			}	
			public int getFiredCycleReliabilityAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedModeReliabilityAffective());
				return counts;
			}				
			public int getFiredWalkingReliabilityAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedModeReliabilityAffective());
				return counts ;
			}	
////The Affective Unpleasant
			public ArrayList<Double> firedModeReliabilityUnpleasantAffective() {				
				reliabilityUnpleasentAffectiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);				
				reliabilityUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);					 
				return reliabilityUnpleasentAffectiveList;			
			}
			public double getFiredPrivateReliabilityUnpleasantAffective(){
				double privateAffective =0.0;
				if(prefferedMode instanceof PersonalVehicle)
					privateAffective =pca.firedTravelDemands(firedModeReliabilityUnpleasantAffective() );
				return privateAffective;
			}			
			public double getFiredPublicReliabilityUnpleasantAffective(){
				double publicAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					publicAffective = pca.firedTravelDemands(firedModeReliabilityUnpleasantAffective() );
				return publicAffective;
			}
			public double getFiredCycleReliabilityUnpleasantAffective(){
				double cycleAffective =0.0;
				if(prefferedMode instanceof Cycle)
					cycleAffective =pca.firedTravelDemands(firedModeReliabilityUnpleasantAffective() );
				return cycleAffective;
			}			
			public double getFiredWalkingReliabilityUnpleasantAffective(){
				double walkingAffective =0.0;
				if(prefferedMode instanceof Walking)
					walkingAffective = pca.firedTravelDemands(firedModeReliabilityUnpleasantAffective() );
				return walkingAffective;
			}
			public int getFiredPrivateReliabilityUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedModeReliabilityUnpleasantAffective());
				return counts;
			}				
			public int getFiredPublicReliabilityUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeReliabilityUnpleasantAffective());
				return counts ;
			}
			public int getFiredCycleReliabilityUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedModeReliabilityUnpleasantAffective());
				return counts;
			}				
			public int getFiredWalkingReliabilityUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedModeReliabilityUnpleasantAffective());
				return counts ;
			}
	//////
			public ArrayList<Double> firedModeReliabilityNeitherNorPleasantAffective() {				
				reliabilityNeitherNorPleasantAffectiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);				
				reliabilityNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
				return reliabilityNeitherNorPleasantAffectiveList ;			
			}
			public double getPrivateReliabilityNeitherNorPleasantAffective(){
				double privateAffective =0.0;
				if(prefferedMode instanceof PersonalVehicle)
					privateAffective =pca.firedTravelDemands(firedModeReliabilityNeitherNorPleasantAffective() );
				return privateAffective;
			}			
			public double getPublicReliabilityNeitherNorPleasantAffective(){
				double publicAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					publicAffective = pca.firedTravelDemands(firedModeReliabilityNeitherNorPleasantAffective() );
				return publicAffective;
			}
			public double getCycleReliabilityNeitherNorPleasantAffective(){
				double cycleAffective =0.0;
				if(prefferedMode instanceof Cycle)
					cycleAffective =pca.firedTravelDemands(firedModeReliabilityNeitherNorPleasantAffective() );
				return cycleAffective;
			}			
			public double getWalkingReliabilityNeitherNorPleasantAffective(){
				double walkingAffective =0.0;
				if(prefferedMode instanceof Walking)
					walkingAffective = pca.firedTravelDemands(firedModeReliabilityNeitherNorPleasantAffective() );
				return walkingAffective;
			}
/////////Counts
			public int getFiredPrivateReliabilityNeitherPleasantNorUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedModeReliabilityNeitherNorPleasantAffective());
				return counts;
			}				
			public int getFiredPublicReliabilityNeitherPleasantNorUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeReliabilityNeitherNorPleasantAffective());
				return counts ;
			}	
			public int getFiredCycleReliabilityNeitherPleasantNorUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedModeReliabilityNeitherNorPleasantAffective());
				return counts;
			}				
			public int getFiredWalkingReliabilityNeitherPleasantNorUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedModeReliabilityNeitherNorPleasantAffective());
				return counts ;
			}	
			public ArrayList<Double> firedModeReliabilityPleasantAffective() {				
				reliabilityPleasantAffectiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeReliabilityDecisionVariables().get(1);				
				reliabilityPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);						 
				return reliabilityPleasantAffectiveList	;			
			}
			public double getPrivateReliabilityPleasantAffective(){
				double privateAffective =0.0;
				if(prefferedMode instanceof PersonalVehicle)
					privateAffective =pca.firedTravelDemands(firedModeReliabilityPleasantAffective());
				return privateAffective;
			}			
			public double getPublicReliabilityPleasantAffective(){
				double publicAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					publicAffective = pca.firedTravelDemands(firedModeReliabilityPleasantAffective());
				return publicAffective;
			}
			public double getCycleReliabilityPleasantAffective(){
				double cycleAffective =0.0;
				if(prefferedMode instanceof Cycle)
					cycleAffective =pca.firedTravelDemands(firedModeReliabilityPleasantAffective());
				return cycleAffective;
			}			
			public double getWalkingReliabilityPleasantAffective(){
				double walkingAffective =0.0;
				if(prefferedMode instanceof Walking)
					walkingAffective = pca.firedTravelDemands(firedModeReliabilityPleasantAffective());
				return walkingAffective;
			}
////Counts
			public int getFiredPrivateReliabilityPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PersonalVehicle)
					counts =pca.firedCounts(firedModeReliabilityPleasantAffective());
				return counts;
			}				
			public int getFiredPublicReliabilityPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeReliabilityPleasantAffective());
				return counts ;
			}
			public int getFiredCycleReliabilityPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Cycle)
					counts =pca.firedCounts(firedModeReliabilityPleasantAffective());
				return counts;
			}				
			public int getFiredWalkingReliabilityPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof Walking)
					counts  = pca.firedCounts(firedModeReliabilityPleasantAffective());
				return counts ;
			}
	}

		