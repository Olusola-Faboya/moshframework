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

public class ModeFrequency {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca;
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;	
	private Vector modeFrequencyDecisionVariables;
	Map<String, List<Double>> otherVariables;
	private ArrayList<Double> physical;
	private ArrayList<Double> affective;
	private ArrayList<Double> frequencyAffectiveList;
	private ArrayList<Double> cognitive;
	private ArrayList<Double> frequencyPhysicalList;
	private ArrayList<Double> frequencyCognitiveList;
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	private double modeFrequencyPhysical;
	private double modeFrequencyCognitive;
	private double publicModeFrequencyCognitive;
	private double modeFrequencyAffective;
	private double publicModeFrequencyAffective;
	private double modeFrequency;
	private double publicModeFrequency;
	private ArrayList<Double> frequencyUnpleasentCognitiveList;
	private ArrayList<Double> frequencyNeitherNorPleasantCognitiveList;
	private ArrayList<Double> frequencyPleasantCognitiveList;
	private ArrayList<Double> frequencyUnpleasentPhysicalList;
	private ArrayList<Double> frequencyNeitherNorPleasantPhysicalList;
	private ArrayList<Double> frequencyPleasantPhysicalList;
	private ArrayList<Double> frequencyUnpleasentAffectiveList;
	private ArrayList<Double> frequencyNeitherNorPleasantAffectiveList;
	private ArrayList<Double> frequencyPleasantAffectiveList; 
	
	public ModeFrequency(Mode preferredMode){
		this.prefferedMode=preferredMode;
		pca= new FuzzyDecisionVariables();		
		 updateModeFrequency() ;
	}	
	
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	private double updateModeFrequencyPhysical() {			
		return modeFrequencyPhysical = prefferedMode.setValueToAttribute(Constants.modeFrequencyPhysical, 0);													
	}		
	public double getModeFrequencyPhysical() {
		updateModeFrequencyPhysical();
		return modeFrequencyPhysical;
	}

	public double updateModeFrequencyCognitive() {					
		 if (prefferedMode instanceof PublicTransport){
			modeFrequencyCognitive = prefferedMode.getValueOfAttribute(Constants.modeFrequencyPerception);											
		}else if (prefferedMode instanceof Cycle){
			modeFrequencyCognitive = prefferedMode.getValueOfAttribute(Constants.modeFrequencyPerception);								
		}else if (prefferedMode instanceof Walking){
			modeFrequencyCognitive = prefferedMode.getValueOfAttribute(Constants.modeFrequencyPerception);													
		}	
		return modeFrequencyCognitive;			
	}	
	public double getModeFrequencyCognitive() {
		updateModeFrequencyCognitive();
		return modeFrequencyCognitive;
	}

	public double updateModeFrequencyAffective() {					
		 if (prefferedMode instanceof PublicTransport){
			modeFrequencyAffective = prefferedMode.getValueOfAttribute(Constants.modeFrequencyAffective);											
		}else if (prefferedMode instanceof Cycle){
			modeFrequencyAffective = prefferedMode.getValueOfAttribute(Constants.modeFrequencyAffective);								
		}else if (prefferedMode instanceof Walking){
			modeFrequencyAffective = prefferedMode.getValueOfAttribute(Constants.modeFrequencyAffective);													
		}		
		return modeFrequencyAffective;			
	}			
	public double getModeFrequencyAffective() {
		updateModeFrequencyAffective();
		return modeFrequencyAffective;
	}

	public double getModeFrequency() {
		return modeFrequency;
	}
	
	public double getPublicModeFrequency(){
		if(prefferedMode instanceof PublicTransport){
			publicModeFrequency = this.getModeFrequency();					
		}		
		return publicModeFrequency;
	}
////The mode frequency decision evaluation			
//	@SuppressWarnings("unchecked")////Add conditions for Cyclist later
	private Vector modeFrequencyValues() {
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();			
			getModeFrequencyPhysical();
			 getModeFrequencyCognitive();
			 getModeFrequencyAffective();				
		return modeFrequencyDecisionVariables =	evaluateThreeVariablesSatisfaction.getTip(getModeFrequencyPhysical(),getModeFrequencyCognitive(),getModeFrequencyAffective());
	}	
	public Vector getModeFrequencyDecisionVariables(){
		return modeFrequencyDecisionVariables;
	}
	
	public double updateModeFrequency() {
		modeFrequencyValues();	
		modeFrequency =(double)getModeFrequencyDecisionVariables().get(0);					 
		return modeFrequency;		
	}	
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant	////
	public ArrayList<Double> firedModeFrequencyCognitive() {
		frequencyCognitiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>)getModeFrequencyDecisionVariables().get(1);
		frequencyCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);						 
		return frequencyCognitiveList;	
	}		
	
	public double getFiredPublicFrequencyCognitive(){
			double publicCognitive =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedModeFrequencyCognitive());
			return publicCognitive;
	}
///cognitive counts by mode
	public int getFiredPublicFrequencyCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedModeFrequencyCognitive());		
			return counts ;
	}
			
//////////////The following are the list of individual level of PCA-COGNITIVE
	public ArrayList<Double> firedModeFrequencyUnpleasantCognitive() {				
		frequencyUnpleasentCognitiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getModeFrequencyDecisionVariables().get(1);				
		frequencyUnpleasentCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);			 
		return frequencyUnpleasentCognitiveList;			
	}
	public double getFiredPublicFrequencyUnpleasantCognitive(){
		double publicCognitive =0.0;
		if(prefferedMode instanceof PublicTransport)
			publicCognitive = pca.firedTravelDemands(firedModeFrequencyUnpleasantCognitive());
		return publicCognitive;
	}
///cognitive counts by mode
	public int getFiredPublicFrequencyUnpleasantCognitiveCounts(){
		int counts =0;
		if(prefferedMode instanceof PublicTransport)
			counts  = pca.firedCounts(firedModeFrequencyUnpleasantCognitive());		
		return counts ;
	}	
	public ArrayList<Double> firedModeFrequencyNeitherNorPleasantCognitive() {				
		frequencyNeitherNorPleasantCognitiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getModeFrequencyDecisionVariables().get(1);				
		frequencyNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);
				 
		return frequencyNeitherNorPleasantCognitiveList;			
	}
	public double getFiredPublicFrequencyNeitherNorPleasantCognitive(){
		double publicCognitive =0.0;
		if(prefferedMode instanceof PublicTransport)
			publicCognitive = pca.firedTravelDemands(firedModeFrequencyNeitherNorPleasantCognitive());
		return publicCognitive;
	}
///cognitive counts by mode
	public int getFiredPublicFrequencyNeitherNorPleasantCognitiveCounts(){
		int counts =0;
		if(prefferedMode instanceof PublicTransport)
			counts  = pca.firedCounts(firedModeFrequencyNeitherNorPleasantCognitive());		
		return counts ;
	}	
	
	public ArrayList<Double> firedModeFrequencyPleasantCognitive() {				
		frequencyPleasantCognitiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getModeFrequencyDecisionVariables().get(1);				
		frequencyPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);					 
		return frequencyPleasantCognitiveList;			
	}
	public double getFiredPublicFrequencyPleasantCognitive(){
		double publicCognitive =0.0;
		if(prefferedMode instanceof PublicTransport)
			publicCognitive = pca.firedTravelDemands(firedModeFrequencyPleasantCognitive());
		return publicCognitive;
	}
///cognitive counts by mode
	public int getFiredPublicFrequencyPleasantCognitiveCounts(){
		int counts =0;
		if(prefferedMode instanceof PublicTransport)
			counts  = pca.firedCounts(firedModeFrequencyPleasantCognitive());		
		return counts ;
	}						
///////The following are the list of individual level of PCA-AFFECTIVE			
			public ArrayList<Double> firedModeFrequencyAffective() {
				frequencyAffectiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeFrequencyDecisionVariables().get(1);
				frequencyAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);							 
				return frequencyAffectiveList	;			
			}
			public double getFiredPublicFrequencyAffective(){
				double publicAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					publicAffective = pca.firedTravelDemands(firedModeFrequencyAffective());
				return publicAffective;
			}
			///cognitive counts by mode
			public int getFiredPublicFrequencyAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeFrequencyAffective());		
				return counts ;
			}		
			public ArrayList<Double> firedModeFrequencyUnpleasantAffective() {				
				frequencyUnpleasentAffectiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeFrequencyDecisionVariables().get(1);				
				frequencyUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);
						 
				return frequencyUnpleasentAffectiveList;			
			}
			public double getFiredPublicFrequencyUnpleasantAffective(){
				double publicAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					publicAffective = pca.firedTravelDemands(firedModeFrequencyUnpleasantAffective());
				return publicAffective;
			}
			///cognitive counts by mode
			public int getFiredPublicFrequencyUnpleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeFrequencyUnpleasantAffective());		
				return counts ;
			}				
			public ArrayList<Double> firedModeFrequencyNeitherNorPleasantAffective() {				
				frequencyNeitherNorPleasantAffectiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeFrequencyDecisionVariables().get(1);				
				frequencyNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);						 
				return frequencyNeitherNorPleasantAffectiveList;			
			}
			public double getFiredPublicFrequencyNeitherNorPleasantAffective(){
				double publicAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					publicAffective = pca.firedTravelDemands(firedModeFrequencyNeitherNorPleasantAffective());
				return publicAffective;
			}
			///cognitive counts by mode
			public int getFiredPublicFrequencyNeitherNorPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeFrequencyNeitherNorPleasantAffective());		
				return counts ;
			}								
			public ArrayList<Double> firedModeFrequencyPleasantAffective() {				
				frequencyPleasantAffectiveList = new ArrayList<>();
				otherVariables = (Map<String, List<Double>>) getModeFrequencyDecisionVariables().get(1);				
				frequencyPleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);
						 
				return frequencyPleasantAffectiveList	;			
			}
			public double getFiredPublicFrequencyPleasantAffective(){
				double publicAffective =0.0;
				if(prefferedMode instanceof PublicTransport)
					publicAffective = pca.firedTravelDemands(firedModeFrequencyPleasantAffective());
				return publicAffective;
			}
			///cognitive counts by mode
			public int getFiredPublicFrequencyPleasantAffectiveCounts(){
				int counts =0;
				if(prefferedMode instanceof PublicTransport)
					counts  = pca.firedCounts(firedModeFrequencyPleasantAffective());		
				return counts ;
			}	
}
