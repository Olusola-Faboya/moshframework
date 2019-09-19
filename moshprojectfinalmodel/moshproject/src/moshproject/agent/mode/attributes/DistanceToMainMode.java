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

public class DistanceToMainMode {
	Passenger passenger;
	Mode prefferedMode;
	FuzzyDecisionVariables pca; 
	
	FuzzyDecisionGenerator  evaluateThreeVariablesSatisfaction;
	Map<String, List<Double>> otherVariables;		
	ArrayList<Double> weigthValues;	
	private Vector distanceToMainModeDecisionVariables;
	private double distanceToMainModeAffective;
	private double distanceToMainModeCognitive;
	private double distanceToMainModePhysical;
	private double distanceToMainMode;
	private double privateDistanceToMainMode;
	private double publicDistanceToMainMode;
	private double publicDistanceToMainModeAffective;
	private double privateDistanceToMainModeAffective;
	private double publicDistanceToMainModeCognitive;
	private double privateDistanceToMainModeCognitive;
	private double publicDistanceToMainModePhysical;
	private double privateDistanceToMainModePhysical;
	private ArrayList<Double> distanceToMainModeCognitiveList;
	private ArrayList<Double> distanceToMainModeUnpleasentCognitiveList;
	private ArrayList<Double> distanceToMainModeNeitherNorPleasantCognitiveList;
	private ArrayList<Double> distanceToMainModePleasantCognitiveList;
	private ArrayList<Double> distanceToMainModeUnpleasentPhysicalList;
	private ArrayList<Double> distanceToMainModeNeitherNorPleasantPhysicalList;
	private ArrayList<Double> distanceToMainModePleasantPhysicalList;
	private ArrayList<Double> distanceToMainModeUnpleasentAffectiveList;
	private ArrayList<Double> distanceToMainModeNeitherNorPleasantAffectiveList;
	private ArrayList<Double> distanceToMainModePleasantAffectiveList;
	private ArrayList<Double> distanceToMainModePhysicalList;
	private ArrayList<Double> distanceToMainModeAffectiveList;
		
	public DistanceToMainMode (Mode prefferedMode) {
		this.prefferedMode =prefferedMode;
		updateDistanceToMainMode();
		pca= new FuzzyDecisionVariables();	
	}
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public void setPrefferedMode(Mode prefferedMode) {
		this.prefferedMode = prefferedMode;
	}
	private double updateDistanceToMainModeAffective() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			//distanceToMainModeAffective = prefferedMode.getValueOfAttribute(Constants.distanceToMainModeAffective);					
		}else if (prefferedMode instanceof PublicTransport){
			distanceToMainModeAffective = prefferedMode.getValueOfAttribute(Constants.distanceToMainModeAffective);											
		}else if (prefferedMode instanceof Cycle){
			distanceToMainModeAffective = prefferedMode.getValueOfAttribute(Constants.distanceToMainModeAffective);								
		}else if (prefferedMode instanceof Walking){
			distanceToMainModeAffective = prefferedMode.getValueOfAttribute(Constants.distanceToMainModeAffective);												
		}			
		return distanceToMainModeAffective;			
	}			
	public double getDistanceToMainModeAffective() {
		updateDistanceToMainModeAffective();
		return distanceToMainModeAffective;
	}

	private double updateDistanceToMainModeCognitive() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			//distanceToMainModePhysical = prefferedMode.getValueOfAttribute(Constants.distanceToMainModePerception);					
		}else if (prefferedMode instanceof PublicTransport){
			distanceToMainModeCognitive = prefferedMode.getValueOfAttribute(Constants.distanceToMainModePerception);											
		}else if (prefferedMode instanceof Cycle){
			distanceToMainModeCognitive = prefferedMode.getValueOfAttribute(Constants.distanceToMainModePerception);								
		}else if (prefferedMode instanceof Walking){
			distanceToMainModeCognitive = prefferedMode.getValueOfAttribute(Constants.distanceToMainModePerception);													
		}			
		return distanceToMainModeCognitive;			
	}	
	public double getDistanceToMainModeCognitive() {
		updateDistanceToMainModeCognitive();
		return distanceToMainModeCognitive;
	}

	private double updateDistanceToMainModePhysical() {			
		if (prefferedMode instanceof PersonalVehicle)	{
			//distanceToMainModePhysical = prefferedMode.getValueOfAttribute(Constants.distanceToMainModePerception);					
		}else if (prefferedMode instanceof PublicTransport){
			distanceToMainModePhysical = prefferedMode.getValueOfAttribute(Constants.distanceToMainModePerception);											
		}else if (prefferedMode instanceof Cycle){
			distanceToMainModePhysical = prefferedMode.getValueOfAttribute(Constants.distanceToMainModePerception);								
		}else if (prefferedMode instanceof Walking){
			distanceToMainModePhysical = prefferedMode.getValueOfAttribute(Constants.distanceToMainModePerception);													
		}			
		return distanceToMainModePhysical;			
	}		
	public double getDistanceToMainModePhysical() {
		updateDistanceToMainModePhysical();
		return distanceToMainModePhysical;
	}

	//////////////////////////Fuzzy part	
	public Vector distanceToMainModeValues(){	
		evaluateThreeVariablesSatisfaction= new FuzzyDecisionGenerator();		
		if(prefferedMode instanceof PersonalVehicle){
			getDistanceToMainModeAffective();
			getDistanceToMainModeCognitive();
			getDistanceToMainModePhysical();			
		}else if (prefferedMode instanceof PublicTransport){
			getDistanceToMainModeAffective();
			getDistanceToMainModeCognitive();
			getDistanceToMainModePhysical();		
		}else if (prefferedMode instanceof Cycle){
			getDistanceToMainModeAffective();
			getDistanceToMainModeCognitive();
			getDistanceToMainModePhysical();			
		}else if (prefferedMode instanceof Walking){
			getDistanceToMainModeAffective();
			getDistanceToMainModeCognitive();
			getDistanceToMainModePhysical();			
		}		
		return distanceToMainModeDecisionVariables=evaluateThreeVariablesSatisfaction.getTip(getDistanceToMainModePhysical(),getDistanceToMainModeCognitive(),getDistanceToMainModeAffective());
	}
	public Vector getDistanceToMainModeDecisionVariables() {
		return distanceToMainModeDecisionVariables;
	}

///This method returns either fuzzy method or the ordinary method
	public double updateDistanceToMainMode(){				
		distanceToMainModeValues();	
		distanceToMainMode =(double) getDistanceToMainModeDecisionVariables().get(0);		
		return distanceToMainMode ;
	}	
	public double getDistanceToMainMode() {
		return distanceToMainMode;
	}
	public double getPublicDistanceToMainMode(){
		if(prefferedMode instanceof PublicTransport){
			publicDistanceToMainMode = this.getDistanceToMainMode();
		}	
		return (double) publicDistanceToMainMode;
	}	
////This method returns the List of all levels of cognitive i.e. pleasant, unpleasant, and neither nor pleasant
	public ArrayList<Double> firedDistanceToMainModeCognitive() {
		distanceToMainModeCognitiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);
		distanceToMainModeCognitiveList = (ArrayList<Double>)pca.uNboundledMapPCACognitive(otherVariables);							 
		return distanceToMainModeCognitiveList;			
	}
	public double getFiredPublicDistanceToMainModeCognitive(){
		double publicCognitive =0;;
		if(prefferedMode instanceof PublicTransport)
		publicCognitive = pca.firedTravelDemands(firedDistanceToMainModeCognitive());	
		return publicCognitive;
	}
	public int getFiredPublicDistanceToMainModeCognitiveCounts(){
		int counts =0;
		if(prefferedMode instanceof PublicTransport)
			counts  = pca.firedCounts(firedDistanceToMainModeCognitive());		
		return counts ;
	}	
		
///////The following are the list of individual level of PCA-COGNITIVE
	public ArrayList<Double> firedDistanceToMainModeUnpleasantCognitive() {				
		distanceToMainModeUnpleasentCognitiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>)getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModeUnpleasentCognitiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantCognitive(otherVariables);				 
		return distanceToMainModeUnpleasentCognitiveList;			
	}
	public double getFiredPublicDistanceToMainModeUnpleasantCognitive(){
		double publicCognitive =0;;
		if(prefferedMode instanceof PublicTransport)
			publicCognitive = pca.firedTravelDemands(firedDistanceToMainModeUnpleasantCognitive());	
		return publicCognitive;
	}	
	public int getFiredPublicDistanceToMainModeUnpleasantCognitiveCounts(){
		int counts =0;
		if(prefferedMode instanceof PublicTransport)
			counts  = pca.firedCounts(firedDistanceToMainModeUnpleasantCognitive());		
		return counts ;
	}	

	public ArrayList<Double> firedDistanceToMainModeNeitherNorPleasantCognitive() {				
		distanceToMainModeNeitherNorPleasantCognitiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModeNeitherNorPleasantCognitiveList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantCognitive(otherVariables);			 
		return distanceToMainModeNeitherNorPleasantCognitiveList	;			
	}
	public double getFiredPublicDistanceToMainModeNeitherNorPleasantCognitive(){
		double publicCognitive =0;;
		if(prefferedMode instanceof PublicTransport)
			publicCognitive = pca.firedTravelDemands(firedDistanceToMainModeNeitherNorPleasantCognitive());	
		return publicCognitive;
	}
	public int getFiredPublicDistanceToMainModeNeitherNorPleasantCognitiveCounts(){
		int counts =0;
		if(prefferedMode instanceof PublicTransport)
			counts  = pca.firedCounts(firedDistanceToMainModeNeitherNorPleasantCognitive());		
		return counts ;
	}	
	public ArrayList<Double> firedDistanceToMainModePleasantCognitive() {				
		distanceToMainModePleasantCognitiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModePleasantCognitiveList  = (ArrayList<Double>)pca.uNboundledMapPleasantCognitive(otherVariables);	 				 
		return distanceToMainModePleasantCognitiveList ;			
	}
	public double getFiredPublicDistanceToMainModePleasantCognitive(){
		double publicCognitive =0;;
		if(prefferedMode instanceof PublicTransport)
			publicCognitive = pca.firedTravelDemands(firedDistanceToMainModePleasantCognitive());	
		return publicCognitive;
	}
	public int getFiredPublicDistanceToMainModePleasantCognitiveCounts(){
		int counts =0;
		if(prefferedMode instanceof PublicTransport)
			counts  = pca.firedCounts(firedDistanceToMainModePleasantCognitive());		
		return counts ;
	}
///////The following are the list of individual level of PCA-PHYSICAL
	public ArrayList<Double> firedDistanceToMainModePhysical() {
		distanceToMainModePhysicalList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);
		distanceToMainModePhysicalList = (ArrayList<Double>)pca.uNboundledMapPCAPhysical(otherVariables);	 				 
		return distanceToMainModePhysicalList	;			
	}
	public double getFiredPublicDistanceToMainModePhysical(){
		double publicPhysical =0;;
		if(prefferedMode instanceof PublicTransport)
			publicPhysical = pca.firedTravelDemands(firedDistanceToMainModePhysical());	
		return publicPhysical;
	}
	public int getFiredPublicDistanceToMainModePhysicalcount(){
		int publicPhyCount =0;;
		if(prefferedMode instanceof PublicTransport)
			publicPhyCount =pca.firedCounts(firedDistanceToMainModePhysical());		
		return publicPhyCount;
	}	
	public ArrayList<Double> firedDistanceToMainModeUnpleasantPhysical() {				
		distanceToMainModeUnpleasentPhysicalList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModeUnpleasentPhysicalList = (ArrayList<Double>)pca.uNboundledMapUnpleasantPhysical(otherVariables);
	//	System.out.println("The Unpleasant Distance to Mainmode Physical Values:");
		for (Double antecedents :distanceToMainModeUnpleasentPhysicalList){
	//	System.out.println(antecedents);
		} 			 
		return distanceToMainModeUnpleasentPhysicalList;			
	}
	public double getFiredPublicDistanceToMainModeUnpleasantPhysical(){
		double publicPhysical =0;;
		if(prefferedMode instanceof PublicTransport)
			publicPhysical = pca.firedTravelDemands(firedDistanceToMainModeUnpleasantPhysical());	
		return publicPhysical;
	}
	public int getFiredPublicDistanceToMainModeUnpleasantPhysicalCount(){
		int publicPhyCount =0;;
		if(prefferedMode instanceof PublicTransport)
			publicPhyCount =pca.firedCounts(firedDistanceToMainModeUnpleasantPhysical());		
		return publicPhyCount;
	}		
	public ArrayList<Double> firedDistanceToMainModeNeitherNorPleasantPhysical() {				
		distanceToMainModeNeitherNorPleasantPhysicalList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModeNeitherNorPleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantPhysical(otherVariables);					 
		return distanceToMainModeNeitherNorPleasantPhysicalList;			
	}
	public double getFiredPublicDistanceToMainModeNeitherNorPleasantPhysical(){
		double publicPhysical =0;;
		if(prefferedMode instanceof PublicTransport)
			publicPhysical = pca.firedTravelDemands(firedDistanceToMainModeNeitherNorPleasantPhysical());	
		return publicPhysical;
	}
	public int getFiredPublicDistanceToMainModeNeitherNorPleasantPhysicalCount(){
		int publicPhyCount =0;;
		if(prefferedMode instanceof PublicTransport)
			publicPhyCount =pca.firedCounts(firedDistanceToMainModeNeitherNorPleasantPhysical());		
		return publicPhyCount;
	}
			
	public ArrayList<Double> firedDistanceToMainModePleasantPhysical() {				
		distanceToMainModePleasantPhysicalList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModePleasantPhysicalList = (ArrayList<Double>)pca.uNboundledMapPleasantPhysical(otherVariables);	
		return distanceToMainModePleasantPhysicalList;			
	}
	public double getFiredPublicDistanceToMainModePleasantPhysical(){
		double publicPhysical =0;;
		if(prefferedMode instanceof PublicTransport)
			publicPhysical = pca.firedTravelDemands(firedDistanceToMainModePleasantPhysical());	
		return publicPhysical;
	}
	public int getFiredPublicDistanceToMainModePleasantPhysicalcount(){
		int publicPhyCount =0;;
		if(prefferedMode instanceof PublicTransport)
			publicPhyCount =pca.firedCounts(firedDistanceToMainModePleasantPhysical());
		return publicPhyCount;
	}
///////The following are the list of individual level of PCA-AFFECTIVE
	
	public ArrayList<Double> firedDistanceToMainModeAffective() {
		distanceToMainModeAffectiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);
		distanceToMainModeAffectiveList = (ArrayList<Double>)pca.uNboundledMapPCAAffective(otherVariables);					 
		return distanceToMainModeAffectiveList	;			
	}
	
	public double getFiredPublicDistanceToMainModeAffective(){
		double publicAffective =0.0;
		if(prefferedMode instanceof PublicTransport)
			publicAffective = pca.firedTravelDemands(firedDistanceToMainModeAffective());	
		return publicAffective;
	}
	public int getFiredPublicDistanceToMainModeAffectivecount(){
		int publicAffectiveCount =0;;
		if(prefferedMode instanceof PublicTransport)
			publicAffectiveCount =pca.firedCounts(firedDistanceToMainModeAffective());
		return publicAffectiveCount;
	}	
	public ArrayList<Double> firedDistanceToMainModeUnpleasantAffective() {				
		distanceToMainModeUnpleasentAffectiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModeUnpleasentAffectiveList = (ArrayList<Double>)pca.uNboundledMapUnpleasantAffective(otherVariables);				 
		return distanceToMainModeUnpleasentAffectiveList;			
	}
	public double getFiredPublicDistanceToMainModeUnpleasantAffective(){
		double publicAffective =0.0;
		if(prefferedMode instanceof PublicTransport)
			publicAffective = pca.firedTravelDemands(firedDistanceToMainModeUnpleasantAffective());	
		return publicAffective;
	}		
	public int getFiredPublicDistanceToMainModeUnpleasantAffectivecount(){
		int publicAffectiveCount =0;;
		if(prefferedMode instanceof PublicTransport)
			publicAffectiveCount =pca.firedCounts(firedDistanceToMainModeUnpleasantAffective());
		return publicAffectiveCount;
	}	
	
	public ArrayList<Double> firedDistanceToMainModeNeitherNorPleasantAffective() {				
		distanceToMainModeNeitherNorPleasantAffectiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModeNeitherNorPleasantAffectiveList   = (ArrayList<Double>)pca.uNboundledMapNeitherNorPleasantAffective(otherVariables);	 				 
		return distanceToMainModeNeitherNorPleasantAffectiveList  ;			
	}
	public double getFiredPublicDistanceToMainModeNeitherNorPleasantAffective(){
		double publicAffective =0.0;
		if(prefferedMode instanceof PublicTransport)
			publicAffective = pca.firedTravelDemands(firedDistanceToMainModeNeitherNorPleasantAffective());	
		return publicAffective;
	}	
	public int getFiredPublicDistanceToMainModeNeitherNorPleasantAffectivecount(){
		int publicAffectiveCount =0;;
		if(prefferedMode instanceof PublicTransport)
			publicAffectiveCount =pca.firedCounts(firedDistanceToMainModeNeitherNorPleasantAffective());
		return publicAffectiveCount;
	}	
	public ArrayList<Double> firedDistanceToMainModePleasantAffective() {				
		distanceToMainModePleasantAffectiveList = new ArrayList<>();
		otherVariables = (Map<String, List<Double>>) getDistanceToMainModeDecisionVariables().get(1);				
		distanceToMainModePleasantAffectiveList = (ArrayList<Double>)pca.uNboundledMapPleasantAffective(otherVariables);				 
		return distanceToMainModePleasantAffectiveList	;			
	}
	
	public double getFiredPublicDistanceToMainModePleasantAffective(){
		double publicAffective =0.0;
		if(prefferedMode instanceof PublicTransport)
			publicAffective = pca.firedTravelDemands(firedDistanceToMainModePleasantAffective());	
		return publicAffective;
	}	
	public int getFiredPublicDistanceToMainModePleasantAffectivecount(){
		int publicAffectiveCount =0;;
		if(prefferedMode instanceof PublicTransport)
			publicAffectiveCount =pca.firedCounts(firedDistanceToMainModePleasantAffective());
		return publicAffectiveCount;
	}		
}
