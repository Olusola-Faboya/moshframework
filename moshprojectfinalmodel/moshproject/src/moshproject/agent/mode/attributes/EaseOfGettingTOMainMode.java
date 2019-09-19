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

public class EaseOfGettingTOMainMode {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca;
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	
	private double easeOfGettingToMainModeCognitive;
	private double easeOfGettingToMainModePhysical;
	private double easeOfGettingToMainModeAffective;
	private double easeOfGettingToMainMode;
	private double publicEaseToMainMode;
	private double privateEaseToMainMode;
	private Vector easeOfGettingToMainModeDecisionVariables;
	private ArrayList<Double> easeOfGettingToMainModeCognitiveList;
	private ArrayList<Double> easeOfGettingToMainModeUnpleasantCognitiveList;
	private ArrayList<Double> easeOfGettingToMainModeNeitherNorPleasantCognitiveList;
	private ArrayList<Double> easeOfGettingToMainModePleasantCognitiveList;
	private ArrayList<Double> easeOfGettingToMainModeUnpleasantPhysicalList;
	private ArrayList<Double> easeOfGettingToMainModeNeitherNorPleasantPhysicalList;
	private ArrayList<Double> easeOfGettingToMainModePleasantPhysicalList;
	private ArrayList<Double> easeOfGettingToMainModeUnpleasentAffectiveList;
	private ArrayList<Double> easeOfGettingToMainModeNeitherNorPleasantAffectiveList;
	private ArrayList<Double> easeOfGettingToMainModePleasantAffectiveList;
	private ArrayList<Double> easeOfGettingToMainModeAffectiveList;
	private ArrayList<Double> easeOfGettingToMainModePhysicalList; 
	
	public EaseOfGettingTOMainMode(Mode preferredMode){
		this.prefferedMode= preferredMode;
		updateEaseOfGettingToMainMode();
		pca= new FuzzyDecisionVariables();	
	}
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}	
	private double updateEaseOfGettingToModeCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
		//	modeEaseOfGettingToModeCognitive = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModePerception);				
		}else if (prefferedMode instanceof PublicTransport){
			easeOfGettingToMainModeCognitive = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModePerception);											
		}else if (prefferedMode instanceof Cycle){
			easeOfGettingToMainModeCognitive = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModePerception);								
		}else if (prefferedMode instanceof Walking){
			easeOfGettingToMainModeCognitive = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModePerception);													
		}			
		return easeOfGettingToMainModeCognitive;			
	}	
	public double getEaseOfGettingToMainModeCognitive() {
		updateEaseOfGettingToModeCognitive();
		return easeOfGettingToMainModeCognitive;
	}
	private double updateEaseOfGettingToModePhysical() {			
		if (prefferedMode instanceof PersonalVehicle)	{
		//	modeEaseOfGettingToModePhysical = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModePerception);				
		}else if (prefferedMode instanceof PublicTransport){
			easeOfGettingToMainModePhysical = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModePerception);											
		}else if (prefferedMode instanceof Cycle){
			easeOfGettingToMainModePhysical = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModePerception);								
		}else if (prefferedMode instanceof Walking){
			easeOfGettingToMainModePhysical = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModePerception);													
		}			
		return easeOfGettingToMainModePhysical;			
	}
	
	public double getEaseOfGettingToMainModePhysical() {
		updateEaseOfGettingToModePhysical();
		return easeOfGettingToMainModePhysical;
	}
	private double updateEaseOfGettingToModeAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
		//	modeEaseOfGettingToModeAffective = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModeAffective);				
		}else if (prefferedMode instanceof PublicTransport){
			easeOfGettingToMainModeAffective = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModeAffective);											
		}else if (prefferedMode instanceof Cycle){
			easeOfGettingToMainModeAffective = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModeAffective);								
		}else if (prefferedMode instanceof Walking){
			easeOfGettingToMainModeAffective = prefferedMode.getValueOfAttribute(Constants.easeOfGettingToMainModeAffective);													
		}				
		return easeOfGettingToMainModeAffective;			
	}		
	
		
	public double getEaseOfGettingToMainModeAffective() {
	updateEaseOfGettingToModeAffective() ;
		return easeOfGettingToMainModeAffective;
	}
		////////The Fuzzy System Version
		public Vector easeOfGettingToMainModeValues() {	
			evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();			
			if(prefferedMode instanceof PersonalVehicle){
				getEaseOfGettingToMainModeAffective();
				getEaseOfGettingToMainModeCognitive();
				getEaseOfGettingToMainModePhysical();
			}else if (prefferedMode instanceof PublicTransport){
				getEaseOfGettingToMainModeAffective();
				getEaseOfGettingToMainModeCognitive();
				getEaseOfGettingToMainModePhysical();
			}		
			easeOfGettingToMainModeDecisionVariables = evaluateThreeVariablesSatisfaction.getTip(getEaseOfGettingToMainModePhysical(),getEaseOfGettingToMainModeCognitive(),getEaseOfGettingToMainModeAffective());
			
			return easeOfGettingToMainModeDecisionVariables; 
		}
		public Vector getEaseOfGettingToMainModeDecisionVariables() {
			return easeOfGettingToMainModeDecisionVariables;
		}

		///This method returns either fuzzy method or the ordinary method
		public double updateEaseOfGettingToMainMode(){				
			easeOfGettingToMainModeValues();	
			 easeOfGettingToMainMode =(double) getEaseOfGettingToMainModeDecisionVariables().get(0);			
			return  easeOfGettingToMainMode ;
		}	
		
		public double getEaseOfGettingToMainMode() {
			return easeOfGettingToMainMode;
		}			
		public double getPublicEaseOfGettingToMainMode(){
			if(prefferedMode instanceof PublicTransport){
				publicEaseToMainMode = this.getEaseOfGettingToMainMode();
			}	
			return (double) publicEaseToMainMode;
		}
		
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
		public ArrayList<Double> firedEaseOfGettingToMainModeCognitive() {
			easeOfGettingToMainModeCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingToMainModeDecisionVariables().get(1);
			easeOfGettingToMainModeCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);				 
			return easeOfGettingToMainModeCognitiveList	;			
		}
		public double getFiredPublicEaseOfGettingToMainModeCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
			publicCognitive = pca.firedTravelDemands(firedEaseOfGettingToMainModeCognitive());	
			return publicCognitive;
		}
		public int getFiredPublicEaseOfGettingToMainModeCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingToMainModeCognitive());		
			return counts ;
		}	
			
///////The following are the list of individual level of PCA-COGNITIVE
		public ArrayList<Double> firedEaseOfGettingToMainModeUnpleasantCognitive() {				
			easeOfGettingToMainModeUnpleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModeUnpleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);			 
			return easeOfGettingToMainModeUnpleasantCognitiveList	;			
		}
		public double getFiredPublicEaseOfGettingToMainModeUnpleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedEaseOfGettingToMainModeUnpleasantCognitive());	
			return publicCognitive;
		}	
		public int getFiredPublicEaseOfGettingToMainModeUnpleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingToMainModeUnpleasantCognitive());		
			return counts ;
		}	
	
		public ArrayList<Double> firedEaseOfGettingToMainModeNeitherNorPleasantCognitive() {				
			easeOfGettingToMainModeNeitherNorPleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModeNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);	 				 
			return easeOfGettingToMainModeNeitherNorPleasantCognitiveList	;			
		}
		public double getFiredPublicEaseOfGettingToMainModeNeitherNorPleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedEaseOfGettingToMainModeNeitherNorPleasantCognitive());	
			return publicCognitive;
		}
		public int getFiredPublicEaseOfGettingToMainModeNeitherNorPleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingToMainModeNeitherNorPleasantCognitive());		
			return counts ;
		}	
		
		public ArrayList<Double> firedEaseOfGettingToMainModePleasantCognitive() {				
			easeOfGettingToMainModePleasantCognitiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModePleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);	 				 
			return easeOfGettingToMainModePleasantCognitiveList	;			
		}
		public double getFiredPublicEaseOfGettingToMainModePleasantCognitive(){
			double publicCognitive =0;;
			if(prefferedMode instanceof PublicTransport)
				publicCognitive = pca.firedTravelDemands(firedEaseOfGettingToMainModePleasantCognitive());	
			return publicCognitive;
		}
		public int getFiredPublicEaseOfGettingToMainModePleasantCognitiveCounts(){
			int counts =0;
			if(prefferedMode instanceof PublicTransport)
				counts  = pca.firedCounts(firedEaseOfGettingToMainModePleasantCognitive());		
			return counts ;
		}
			
///////The following are the list of individual level of PCA-PHYSICAL
		
		public ArrayList<Double> firedEaseOfGettingToMainModePhysical() {
			easeOfGettingToMainModePhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingToMainModeDecisionVariables().get(1);
			easeOfGettingToMainModePhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);	 				 
			return easeOfGettingToMainModePhysicalList	;			
		}
		public double getFiredPublicEaseOfGettingToMainModePhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedEaseOfGettingToMainModePhysical() );	
			return publicPhysical;
		}
		public int getFiredPublicEaseOfGettingToMainModePhysicalcount(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedEaseOfGettingToMainModePhysical() );		
			return publicPhyCount;
		}	
		public ArrayList<Double> firedEaseOfGettingToMainModeUnpleasantPhysical() {				
			easeOfGettingToMainModeUnpleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModeUnpleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);			 
			return easeOfGettingToMainModeUnpleasantPhysicalList;			
		}
		public double getFiredPublicEaseOfGettingToMainModeUnpleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedEaseOfGettingToMainModeUnpleasantPhysical());	
			return publicPhysical;
		}
		public int getFiredPublicEaseOfGettingToMainModeUnpleasantPhysicalcount(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedEaseOfGettingToMainModeUnpleasantPhysical());		
			return publicPhyCount;
		}	
			
		public ArrayList<Double> firedEaseOfGettingToMainModeNeitherNorPleasantPhysical() {				
			easeOfGettingToMainModeNeitherNorPleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModeNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);				 
			return easeOfGettingToMainModeNeitherNorPleasantPhysicalList;			
		}			
		public double getFiredPublicDistanceToMainModeNeitherNorPleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedEaseOfGettingToMainModeNeitherNorPleasantPhysical());	
			return publicPhysical;
		}
		public int getFiredPublicDistanceToMainModeNeitherNorPleasantPhysicalCount(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedEaseOfGettingToMainModeNeitherNorPleasantPhysical());		
			return publicPhyCount;
		}
		public ArrayList<Double> firedEaseOfGettingToMainModePleasantPhysical() {				
			easeOfGettingToMainModePleasantPhysicalList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModePleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);					 
			return easeOfGettingToMainModePleasantPhysicalList	;			
		}
		public double getFiredPublicEaseOfGettingToMainModePleasantPhysical(){
			double publicPhysical =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhysical = pca.firedTravelDemands(firedEaseOfGettingToMainModePleasantPhysical());	
			return publicPhysical;
		}
		public int getFiredPublicEaseOfGettingToMainModePleasantPhysicalcount(){
			int publicPhyCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicPhyCount =pca.firedCounts(firedEaseOfGettingToMainModePleasantPhysical());
			return publicPhyCount;
		}	
///////The following are the list of individual level of PCA-AFFECTIVE
		public ArrayList<Double> firedEaseOfGettingToMainModeAffective() {
			easeOfGettingToMainModeAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingToMainModeDecisionVariables().get(1);
			easeOfGettingToMainModeAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);					 
			return easeOfGettingToMainModeAffectiveList	;			
		}
		public double getFiredPublicEaseOfGettingToMainModeAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedEaseOfGettingToMainModeAffective());	
			return publicAffective;
		}
		public int getFiredPublicEaseOfGettingToMainModeAffectivecount(){
			int publicAffectiveCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffectiveCount =pca.firedCounts(firedEaseOfGettingToMainModeAffective());
			return publicAffectiveCount;
		}		
		
		public ArrayList<Double> firedEaseOfGettingToMainModeUnpleasantAffective() {				
			easeOfGettingToMainModeUnpleasentAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModeUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);				 
			return easeOfGettingToMainModeUnpleasentAffectiveList;			
		}
		public double getFiredPublicEaseOfGettingToMainModeUnpleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedEaseOfGettingToMainModeUnpleasantAffective());	
			return publicAffective;
		}		
		public int getFiredPublicEaseOfGettingToMainModeUnpleasantAffectivecount(){
			int publicAffectiveCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffectiveCount =pca.firedCounts(firedEaseOfGettingToMainModeUnpleasantAffective());
			return publicAffectiveCount;
		}			
		public ArrayList<Double> firedEaseOfGettingToMainModeNeitherNorPleasantAffective() {				
			easeOfGettingToMainModeNeitherNorPleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>)getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModeNeitherNorPleasantAffectiveList  = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);					 
			return easeOfGettingToMainModeNeitherNorPleasantAffectiveList ;			
		}
		public double getFiredPublicEaseOfGettingToMainModeNeitherNorPleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedEaseOfGettingToMainModeNeitherNorPleasantAffective());	
			return publicAffective;
		}	
		public int getFiredPublicEaseOfGettingToMainModeNeitherNorPleasantAffectivecount(){
			int publicAffectiveCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffectiveCount =pca.firedCounts(firedEaseOfGettingToMainModeNeitherNorPleasantAffective());
			return publicAffectiveCount;
		}	
								
		public ArrayList<Double> firedEaseOfGettingToMainModePleasantAffective() {				
			easeOfGettingToMainModePleasantAffectiveList = new ArrayList<>();
			otherVariables = (Map<String, List<Double>>) getEaseOfGettingToMainModeDecisionVariables().get(1);				
			easeOfGettingToMainModePleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);						 
			return easeOfGettingToMainModePleasantAffectiveList	;			
		}
		public double getFiredPublicEaseOfGettingToMainModePleasantAffective(){
			double publicAffective =0.0;
			if(prefferedMode instanceof PublicTransport)
				publicAffective = pca.firedTravelDemands(firedEaseOfGettingToMainModePleasantAffective());	
			return publicAffective;
		}	
		public int getFiredPublicEaseOfGettingToMainModePleasantAffectivecount(){
			int publicAffectiveCount =0;;
			if(prefferedMode instanceof PublicTransport)
				publicAffectiveCount =pca.firedCounts(firedEaseOfGettingToMainModePleasantAffective());
			return publicAffectiveCount;
		}
	
	

}
