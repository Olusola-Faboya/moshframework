package moshproject.agents.passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.codehaus.groovy.tools.shell.ExitNotification;

import moshproject.agent.behaviour.Behaviour;
import moshproject.agent.enums.DecisionStrategy;
import moshproject.agent.enums.NeedWeight;
import moshproject.agent.enums.SocialWeight;
import moshproject.agents.mode.Cycle;
import moshproject.agents.mode.Mode;
import moshproject.agents.mode.PersonalVehicle;
import moshproject.agents.mode.PublicTransport;
import moshproject.agents.mode.Walking;
import moshproject.common.Constants;
//import moshproject.agents.passenger.chart.TravellerPhysicalStatechart;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Passenger {	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;		
	Random  rand = new Random();	
	Map map = new HashMap<>();
	int count;
	Constants.Occupation occupation;
	public Constants.Occupation getOccupation() {
		return occupation;
	}	
	Constants.DistanceRange distanceRange;	
	public Constants.DistanceRange getDistanceRange() {
		return distanceRange;
	}
	Passenger others;
	private int tickCount;
	//private double satisfiedThreshold =0.6;
	private double uncertaintyThreshold;//=0.5;
	
	private double similarityValue;	
	 public double getSimilarityValue() {return similarityValue;}
	public void setSimilarityValue(double similarityValue) {this.similarityValue = similarityValue;}
	
	ArrayList<Passenger> SelectedFriends;
	 
	 public void setSelectedFriends(ArrayList<Passenger> selectedFriends) {
		SelectedFriends = selectedFriends;
	}
	public ArrayList<Passenger> getSelectedFriends() {
		return SelectedFriends =passengerType.findWeakLinksInteractee();
	}
	ArrayList<Double>value;
	private ModeAttributesPerceptions myPerception;
	public void setMyPerception(ModeAttributesPerceptions myPerception) {
		this.myPerception = myPerception;
		this.myPerception.passenger=this;	
	}
	public ModeAttributesPerceptions getMyPerception() {
		return myPerception;
	}
	private void updateMyPerception(ModeAttributesPerceptions myPerception){
		this.myPerception =myPerception;
		
	}
	 public PassengerType passengerType;
	public PassengerType getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(PassengerType passengerType) {
		this.passengerType = passengerType;
		this.passengerType.myPassenger = this;
	}
	private Mode interracteePrefferedMode;	
	public void setInterracteePrefferedMode(Mode interracteePrefferedMode) {
		this.interracteePrefferedMode = interracteePrefferedMode;
	}
	public Mode getInterracteePrefferedMode() {	
		return interracteePrefferedMode;
	}
	
	private boolean checkOtherInfo;
	
	public boolean isCheckOtherInfo() {
		return checkOtherInfo;
	}
	public void setCheckOtherInfo(boolean checkOtherInfo) {
		this.checkOtherInfo = checkOtherInfo;
	}
	private boolean interacteeFound;	
	public boolean isInteracteeFound() {
		return interacteeFound;
	}
	public void setInteracteeFound(boolean findInteractee) {
		this.interacteeFound = findInteractee;
	}
	private boolean inquiryInteracteeFound;		
	public boolean isInquiryInteracteeFound() {
		return inquiryInteracteeFound;
	}
	public void setInquiryInteracteeFound(boolean inquiryInteracteeFound) {
		this.inquiryInteracteeFound = inquiryInteracteeFound;
	}
	
	public Mode prefferedMode;
	public Mode getPrefferedMode() {
		return prefferedMode;
	}
	public Mode setPrefferedMode(Mode prefferedMode) {
		return this.prefferedMode = prefferedMode;		
	}	

	public double modeSatisfaction;	
	public void setModeSatisfaction(double modeSatisfaction) {
		this.modeSatisfaction = modeSatisfaction;
	}	
	
	private double existenceSatisfaction;		
	public double getExistenceSatisfaction() {
		return existenceSatisfaction;
	}
	private ArrayList<Double> socialAgreeabilityValue;	
	public ArrayList<Double> getSocialAgreeabilityValue() {		
		return socialAgreeabilityValue;
	}
	public void setSocialAgreeabilityValue(ArrayList<Double> socialAgreabilityValues) {
		this.socialAgreeabilityValue = socialAgreabilityValues;
	}
	private double socialUncertainty;	
	public double getSocialUncertainty() {return socialUncertainty;	}
	public void setSocialUncertainty(double socialUncertainty) {
		this.socialUncertainty = socialUncertainty;
	}
	
	private double personalUncertainty;	
	public double getPersonalUncertainty() {return personalUncertainty;}
	public void setPersonalUncertainty(double personalUncertainty) {this.personalUncertainty = personalUncertainty;}
	
	private double socialSatisfaction;	
	public double getSocialSatisfaction() {return socialSatisfaction;}
	public void setSocialSatisfaction(double socialSatisfaction) {this.socialSatisfaction = socialSatisfaction;}
	
	public List<Double> myModeSatisfactionList = new ArrayList<Double>();	
	public List<Double> myModeEfficiencyList = new ArrayList<Double>();
	public List<Double> myModeComfortabilityList = new ArrayList<Double>();
	public List<Double> myModeSafetyList = new ArrayList<Double>();
	
	public Map<NeedWeight, Double> dNeedsWeight;
	Map<SocialWeight, Double> dSocialFactor;
	
	private double comformistValue;	
	public double getComformistValue() {return comformistValue;}
	public void setComformistValue(double comformistValue) {
		this.comformistValue = comformistValue;
	}

	private double antiComformistValue;	
	public double getAntiComformistValue() {return antiComformistValue;}
	public void setAntiComformistValue(double antiComformistValue) {
		this.antiComformistValue = antiComformistValue;
	}

	private double superiorityValue;
	public double getSuperiorityValue() {return superiorityValue;	}
	public void setSuperiorityValue(double superiorityValue) {
		this.superiorityValue = superiorityValue;
	}

	private double uncertainty;	
	public double getUncertainty() {
		return uncertainty;
	}
	public void setUncertainty(double uncertainty) {
		this.uncertainty = uncertainty;
	}
	private double uncertaintyTolerance;
	
	public double getUncertaintyTolerance() {
		return uncertaintyTolerance;
	}
	public void setUncertaintyTolerance(double uncertaintyTolerance) {
		this.uncertaintyTolerance = uncertaintyTolerance;
	}
	public double aspirationLevel;
	public double getAspirationLevel() {
		return aspirationLevel;
	}
	public void setAspirationLevel(double aspirationLevel) {
		this.aspirationLevel = aspirationLevel;
	}
///Previous
	public double  previousExperience;
	public double getPreviousExperience() {return previousExperience;}
	public void setPreviousExperience(double previousExperience) {this.previousExperience = previousExperience;}	
	
	public double  previousEfficiency;
	public double getPreviousEfficicency() {return previousEfficiency;}
	public void setPreviousEfficiency(double previousEfficiency) {this.previousEfficiency = previousEfficiency;}
	
	public double  previousComfortability;	
	public double getPreviousComfortability() {return previousComfortability;}
	public void setPreviousComfortability(double previousComfortability) {
		this.previousComfortability = previousComfortability;
	}
	public double  previousSafety;	
	public double getPreviousSafety() {return previousSafety;	}
	public void setPreviousSafety(double previousSafety) {
		this.previousSafety = previousSafety;
	}
	private double changeInExperience;
	public double getChangeInExperience() {return changeInExperience;}	
	public void setChangeInExperience(double changeInExperience) {this.changeInExperience = changeInExperience;}
	
	private double changeInEfficiencyExperience;
	public double getChangeInEfficiencyExperience() {return changeInEfficiencyExperience;}
	public void setChangeInEfficiencyExperience(double changeInExperience) {this.changeInEfficiencyExperience = changeInExperience;	}
	
	private double changeInComfortabilityExperience;
	public double getChangeInComfortabilityExperience() {return changeInComfortabilityExperience;}
	public void setChangeInComfortabilityExperience(double changeInComfortExperience) {this.changeInComfortabilityExperience = changeInComfortExperience;	}
	
	private double changeInSafetyExperience;
	public double getChangeInSafetyExperience() {return changeInSafetyExperience;}
	public void setChangeInSafetyExperience(double changeInSafetyExperience) {this.changeInSafetyExperience = changeInSafetyExperience;	}
	
	private double levelOfNeedsSatisfaction;
	private boolean socialAgreeability;
	private double assumedMaximumDifference;
	private double socialInformation;
//	public double transitionBehaviour;	
	
	private double publicModeSatisfaction;
		
	private double privateModeSatisfaction;
	private double cycleModeSatisfaction;
	private double walkingModeSatisfaction;
	private int privateSatisfactionAffectiveCounts;
	private int cycleSatisfactionAffectiveCounts;
	private int privateSatisfactionCognitiveCounts;
	private int cycleSatisfactionCognitiveCounts;
	private int privateSatisfactionPhysicalCounts;
	private int cycleSatisfactionPhysicalCounts;
	private int privateSatisfactionUnpleasantCognitiveCounts;
	private int cycleSatisfactionUnpleasantCognitiveCounts;
	private int privateSatisfactionNeitherNorPleasantCognitiveCounts;
	private int cycleSatisfactionNeitherNorPleasantCognitiveCounts;
	private int cycleSatisfactionPleasantCognitiveCounts;
	private int privateSatisfactionPleasantCognitiveCounts;
	private DecisionStrategy behaviour;
	
	public void setBehaviour(DecisionStrategy behaviour) {
		this.behaviour = behaviour;
	}
	
	
	//private int tick;
	private double modeEfficiency;	
	public void setModeEfficiency(double modeEfficiency) {this.modeEfficiency = modeEfficiency;}
	public double getModeEfficiency() {return modeEfficiency;}
	
	private double modeComfortability;	
	public void setModeComfortability(double modeComfortability) {this.modeComfortability = modeComfortability;	}
	public double getModeComfortability() {	return modeComfortability;}
	
	private double modeSafety;	
	public void setModeSafety(double modeSafety) {this.modeSafety = modeSafety;	}
	public double getModeSafety() {return modeSafety;}
////
	private boolean cycleOwnership;
	private boolean isTraveldays;
	private double needsDifference;
	private boolean carOwnership;

	private double socialFrequency;
	private int publicSatisfactionAffectiveCounts;
	private int walkingSatisfactionAffectiveCounts;
	private int walkingSatisfactionCognitiveCounts;
	private int publicSatisfactionCognitiveCounts;
	private int publicSatisfactionPhysicalCounts;
	private int walkingSatisfactionPhysicalCounts;
	private int publicSatisfactionUnpleasantCognitiveCounts;
	private int walkingSatisfactionUnpleasantCognitiveCounts;
	private int publicSatisfactionNeitherNorPleasantCognitiveCounts;
	private int walkingSatisfactionNeitherNorPleasantCognitiveCounts;
	private int publicSatisfactionPleasantCognitiveCounts;
	private int walkingSatisfactionPleasantCognitiveCounts;
	private int privateSatisfactionUnpleasantPhysicalCounts;
	private int cycleSatisfactionUnpleasantPhysicalCounts;
	private int publicSatisfactionUnpleasantPhysicalCounts;
	private int walkingSatisfactionUnpleasantPhysicalCounts;
	private int privateSatisfactionNeitherNorPleasantPhysicalCounts;
	private int cycleSatisfactionNeitherNorPleasantPhysicalCounts;
	private int publicSatisfactionNeitherNorPleasantPhysicalCounts;
	private int walkingSatisfactionNeitherNorPleasantPhysicalCounts;
	private int privateSatisfactionPleasantPhysicalCounts;
	private int cycleSatisfactionPleasantPhysicalCounts;
	private int publicSatisfactionPleasantPhysicalCounts;
	private int walkingSatisfactionPleasantPhysicalCounts;
	private int privateSatisfactionUnpleasantAffectiveCounts;
	private int cycleSatisfactionUnpleasantAffectiveCounts;
	private int publicSatisfactionUnpleasantAffectiveCounts;
	private int walkingSatisfactionUnpleasantAffectiveCounts;
	private int privateSatisfactionNeitherNorPleasantAffectiveCounts;
	private int cycleSatisfactionNeitherNorPleasantAffectiveCounts;
	private int publicSatisfactionNeitherNorPleasantAffectiveCounts;
	private int walkingSatisfactionNeitherNorPleasantAffectiveCounts;
	private int privateSatisfactionPleasantAffectiveCounts;
	private int cycleSatisfactionPleasantAffectiveCounts;
	private int publicSatisfactionPleasantAffectiveCounts;
	private int walkingSatisfactionPleasantAffectiveCounts;
	
	private double privateModeAspiration;
	private double publicModeAspiration;
	private double cycleModeAspiration;
	private double walkingModeAspiration;
	private double walkingModeUncertainty;
	private double cycleModeUncertainty;
	private double publicModeUncertainty;
	private double privateModeUncertainty;
	private boolean isCertainty;
	private boolean isSatisfied;
	private boolean isCertain;
	private double personalSatisfaction2;

	private double ambition;	
	public double getAmbition() {return ambition;}	
	public double getSocialFrequency() {return socialFrequency;	}
	public double getNeedsDifference() {return needsDifference;	}
	
	public double getSocialInformation() {return socialInformation;	}
	public void setSocialInformation(double socialInformation) {this.socialInformation = socialInformation;	}
	
	public double getAssumedMaximumDifference() {return assumedMaximumDifference;}
	public double getLevelOfNeedsSatisfaction() {return levelOfNeedsSatisfaction;}
	public void setLevelOfNeedsSatisfaction(double levelOfNeedsSatisfaction) {this.levelOfNeedsSatisfaction = levelOfNeedsSatisfaction;}
	///***This section if for current and previous existences values
	private double dEfficiency;	
	public double getdEfficiency() {return dEfficiency;}
	public void setdEfficiency(double dEfficiency) {this.dEfficiency = dEfficiency;}
	private double dComfortability;	
	public double getdComfortability() {return dComfortability;}
	public void setdComfortability(double dComfortability) {this.dComfortability = dComfortability;}
	private double dSafety;
	public double getdSafety() {return dSafety;}
	private double dSocial;
	public double getdSocial() {return dSocial;}	
	public void setdSocial(double dSocial) {this.dSocial = dSocial;}
	public void setdSafety(double dSafety) {this.dSafety = dSafety;}
	private double dEfficiencyVariance;
	
	private double dComfortabilityVariance;
	
	private double dSafetyVariance;	
	public double getdSafetyVariance() {return dSafetyVariance;	}
	public double getdComfortabilityVariance() {return dComfortabilityVariance;}
	public double getdEfficiencyVariance() {return dEfficiencyVariance;	}
	private double dEfficiencyUncertainty;
	public double getdEfficiencyUncertainty() {	return dEfficiencyUncertainty;}
	private double dSafetyUncertainty;
	public double getdSafetyUncertainty() {return dSafetyUncertainty;}
	private double dComfortabilityUncertainty;		
	public double getdComfortabilityUncertainty() {return dComfortabilityUncertainty;}
	
	private double dUncertainty;
	public double getdUncertainty() {return dUncertainty;	}
	
	private double levelOfEfficiencyNeedsSatisfaction;
	public void setLevelOfEfficiencyNeedsSatisfaction(double levelOfEfficiencyNeedsSatisfaction) {
		this.levelOfEfficiencyNeedsSatisfaction = levelOfEfficiencyNeedsSatisfaction;
	}
	public double getLevelOfEfficiencyNeedsSatisfaction() {return levelOfEfficiencyNeedsSatisfaction;}
	
	private double levelOfComfortabilityNeedsSatisfaction;
	public void setLevelOfComfortabilityNeedsSatisfaction(double levelOfComfortNeedsSatisfaction) {
		this.levelOfComfortabilityNeedsSatisfaction = levelOfComfortNeedsSatisfaction;
	}
	public double getLevelOfComfortabilityNeedsSatisfaction() {return levelOfComfortabilityNeedsSatisfaction;}
	
	private double levelOfSafetyNeedsSatisfaction;
	private double privateUncertaintyTolerance;
	private double publicUncertaintyTolerance;
	private double cycleUncertaintyTolerance;
	private double walkingUncertaintyTolerance;	
	public void setLevelOfSafetyNeedsSatisfaction(double levelOfSafetyNeedsSatisfaction) {
		this.levelOfSafetyNeedsSatisfaction = levelOfSafetyNeedsSatisfaction;
	}
	public double getLevelOfSafetyNeedsSatisfaction() {return levelOfSafetyNeedsSatisfaction;}	
	
	
	//private double uncertaintyThreshold;	
		public double getUncertaintyThreshold() {
		return uncertaintyThreshold;
	}
		public Passenger(ContinuousSpace<Object> space, Grid<Object> grid) {
			this.space = space;
			this.grid = grid;
			cycleOwnership= false;
			carOwnership =false;			
			socialAgreeability= false;
			socialFrequency=0.2;
			interacteeFound=false;
			inquiryInteracteeFound =false;
			isTraveldays =false;		
			dNeedsWeight = new HashMap<>();
			dSocialFactor=new HashMap<>();				
			similarityValue=0.0;
			checkOtherInfo=false;
		}
		@ScheduledMethod(start =0, priority =0)
		public void initialize(){			
			myPerception = new ModeAttributesPerceptions(this.prefferedMode);	
			updateOccupation();
			updateUserDistanceRange();
			assumedMaximumDifference= 0.3;			
			socialSatisfaction = 0.0;		
			uncertaintyThreshold = rand.nextGaussian()*0.005+0.3;
			aspirationLevel =  ComputeAmbition();			
			dEfficiency=0.0;
			dComfortability=0.0;
			dSafety=0.0;	
			dEfficiencyVariance=0.0;
			dComfortabilityVariance=0.0;
			previousExperience=0.0;
			dSafetyVariance=0.0;
			dNeedsWeight = NeedsWeight();
			map= new HashMap<>();	
			behaviour=DecisionStrategy.REPEAT;
		} 
		public Map<SocialWeight, Double> getdSocialFactor() {
			return dSocialFactor;
		}
		@ScheduledMethod(start =1, interval=1, priority =1)
		public void step() {			
		if (isTraveldays()){
			System.out.println("**************************************************");
			System.out.println("Tick:"+ this.tickCount);	
			System.out.println("The preferrred mode is "+getPrefferedMode());			
			if (getTickCount()>5){		
				myPerception = new ModeAttributesPerceptions(this.prefferedMode);	
				myPerception.normalisedWeight();			
			updatePreviousExperiences();	
			updateModePCA();
			determineExistenceSatisfaction();				
			getSocialFactor();	
			computeSocialSatisfaction();		
			computeExperienceSatisfaction();		
			System.out.println("The dSatisfaction is:"+modeSatisfaction);
			System.out.println("The Uncerity Threshold is:"+getUncertaintyThreshold());				
			getSelectedFriends();			
			updateSatisfactionLists();				
			updateLevelOfNeeedsSatisfactions();				
			ComputeAmbition();			
			selectBehaviour();			
			computeExistenceUncertainty();
			}
			System.out.println(" The Uncertainty"+ getdUncertainty());	
			///******************************************************************			/////	
			
			move();
			}else if (!(isTraveldays())){
				System.out.println("======The travel day status is false======");
			}		
		}
		
		private void updatePreviousExperiences(){
			passengerType.updatePreviousExperience();	
			passengerType.updateExistenceEfficiency();
			passengerType.updateExistenceComfortability();			
			passengerType.updateExistenceSafety();	
		//	System.out.println("*******I can see all Previous experinces******************");
		}
		private void updateModePCA(){
			myPerception.updateModeEfficiencyCognitve();
			myPerception.updateModeSafetyCognitive();
			myPerception.updateModeComfortabilityCognitive();		
			myPerception.updateModeEfficiencyAffective();
			myPerception.updateModeSafetyAffective();
			myPerception.updateModeComfortabilityAffective();			
			myPerception.updateModeSafetyPhysical();
			myPerception.updateModeComfortabilityPhysical();
		}
		private void updateOccupation(){	
			 occupation= getPrefferedMode().getValueOfAttributeOccupation(Constants.usersOccupation);		
		}
		private void updateUserDistanceRange(){	
			 distanceRange= getPrefferedMode().getValueOfAttributeDistanceRange(Constants.userDistanceRange);		
		}
		private void updateSatisfactionLists(){
			updateModeStatisfactionList();	
			updateModeEfficiencyList();
			updateModeComfortabilityList();
			updateModeSafetyList();
		}
		private void updateLevelOfNeeedsSatisfactions(){
			levelOfNeedsSatisfaction= getModeSatisfaction();	
			levelOfEfficiencyNeedsSatisfaction= getdEfficiency();	
			levelOfComfortabilityNeedsSatisfaction= getdComfortability();	
			levelOfSafetyNeedsSatisfaction= getdSafety();	
		}
		public double ComputeAmbition(){
			double totalAmbitions= (((myPerception.getAttrEfficiencyWeight()+myPerception.getAttrComfortabilityWeight()+myPerception.getAttrSafetyWeight())/3)*0.9);
		//	System.out.println("   =====The ambition level is :"+totalAmbitions);
			return totalAmbitions;				
		}
		public Map<NeedWeight, Double> NeedsWeight(){
			dNeedsWeight= new HashMap<NeedWeight, Double>();		
			dNeedsWeight.put(NeedWeight.EFFICIENCY, myPerception.getEfficiencyWeight());
			dNeedsWeight.put(NeedWeight.COMFORTABILITY, myPerception.getComfortabilityWeight());
			dNeedsWeight.put(NeedWeight.SAFETY, myPerception.getSafetyWeight());
			dNeedsWeight.put(NeedWeight.SOCIAL, myPerception.getSocialWeight());	
			return dNeedsWeight;				
		}			 
		public Map<NeedWeight, Double> getNeedWeight() {
			return dNeedsWeight;
		}
		
		public Map<SocialWeight, Double> getSocialFactor() {
			return dSocialFactor=passengerType.getSocialSatisfactionFactors(getSelectedFriends());
		}
	//	@ScheduledMethod(start =0, interval=1, priority=0)
		private void determineExistenceSatisfaction(){	
			double existSatisfaction =0;
				myPerception.updateModeEfficiency();
				myPerception.updateModeComfortability();
				myPerception.updateModeSafety();
				modeEfficiency= myPerception.getModeEfficiency();//*dNeedsWeight.get(NeedWeight.EFFICIENCY);
				modeComfortability= myPerception.getModeComfortability();//*dNeedsWeight.get(NeedWeight.COMFORTABILITY);
				modeSafety= myPerception.getModeSafety();//*dNeedsWeight.get(NeedWeight.SAFETY);	
				existSatisfaction= ((modeEfficiency+modeComfortability+modeSafety)/3);
				existenceSatisfaction= existSatisfaction;			
		}
		
		private double computeExperienceSatisfaction(){	
			double satisfaction=0.0;
			dEfficiency =(Math.pow(getModeEfficiency(),myPerception.getEfficiencyWeight()));
			dComfortability=(Math.pow(getModeComfortability(),myPerception.getComfortabilityWeight()));
			dSafety=(Math.pow(getModeSafety(),myPerception.getSafetyWeight()));
			dSocial = (Math.pow(getSocialSatisfaction(),myPerception.getSocialWeight()));	
			satisfaction = ((dEfficiency*dComfortability*dSafety)+(dSocial*0.1));//+(getSocialInformation()*0.1));
			modeSatisfaction=satisfaction;			
			return satisfaction;
		}	

		private double computeSocialSatisfaction(){
			passengerType.socialSatisfactions(getSelectedFriends());
			double computeSocialSat =0;
			double conformity = getComformistValue();
			double antiConformity=getAntiComformistValue();
			double superiority =getSuperiorityValue();					
		
				 if (conformity!=0){
					  computeSocialSat=conformity;
				 }else if (antiConformity!=0){
					 computeSocialSat =(1-antiConformity);
				 }
			 
				 if(superiority!=0){
					 socialSatisfaction = ((superiority+computeSocialSat)/2);
				 }else{
					 socialSatisfaction=computeSocialSat;
				 }
		//	 System.out.println("********************The dSocialSatisfaction is:"+socialSatisfaction);
			return socialSatisfaction;
		}
/// this moving avreage not useful
		public void determineVarianceInSatisfaction (){	// this is moving average variance			
			double efficiencySatisfaction= getModeEfficiency();//*dNeedsWeight.get(NeedWeight.EFFICIENCY);
			double comfortabilitySatisfaction= getModeComfortability();//*dNeedsWeight.get(NeedWeight.COMFORTABILITY);
			double safetySatisfaction= getModeSafety();//*dNeedsWeight.get(NeedWeight.SAFETY);		
			getTickCount();//count++;
			if(tickCount==1)
		{
			dEfficiency=efficiencySatisfaction;
			dComfortability=comfortabilitySatisfaction;					
			dSafety= safetySatisfaction;	
			System.out.println("d Effic******* is:"+dEfficiency);
			System.out.println("d Comfort******* is:"+dComfortability);
			System.out.println("d Safety******* is:"+dSafety);
			return;
		}	
			double delta=efficiencySatisfaction-dEfficiency;
			dEfficiency =dEfficiency*0.9+efficiencySatisfaction*0.1;
			dEfficiencyVariance= dEfficiencyVariance*0.9+(delta*(efficiencySatisfaction-dEfficiency))*0.1f;
			dComfortability =dComfortability*0.9+comfortabilitySatisfaction*0.1;
			dComfortabilityVariance= dComfortabilityVariance*0.9+(delta*(comfortabilitySatisfaction-dComfortability))*0.1f;
			dSafety =dSafety*0.9+safetySatisfaction*0.1;
			dSafetyVariance= dSafetyVariance*0.9+(delta*(safetySatisfaction-dSafety))*0.1f;
			System.out.println("************************************:");			
			System.out.println("d Effic is:"+dEfficiency);
			System.out.println("d Effic variance is:"+dEfficiencyVariance);
			System.out.println("d Comfort is:"+dComfortability);
			System.out.println("d Comfort variance is:"+dComfortabilityVariance);
			System.out.println("d Safety is:"+dSafety);
			System.out.println("d Safety variance is:"+dSafetyVariance);			
	}	

		private double computeExistenceUncertainty(){
			double	uncertainty=0;
			double socialUncertain=0;
			passengerType.changeInEfficiencyExperience();	
			passengerType.changeInComfortabilityExperience();	
			passengerType.changeInSafetyExperience();
		//	System.out.println(" The efficiency change in experience is"+ getChangeInEfficiencyExperience());
			///
			dEfficiencyUncertainty = (Math.pow(getChangeInEfficiencyExperience(),dNeedsWeight.get(NeedWeight.EFFICIENCY)));
			dComfortabilityUncertainty = (Math.pow(getChangeInComfortabilityExperience(),dNeedsWeight.get(NeedWeight.COMFORTABILITY)));
			dSafetyUncertainty = (Math.pow(getChangeInSafetyExperience(),dNeedsWeight.get(NeedWeight.SAFETY)));
	
			//System.out.println("the Social uncertainty is:"+ getSocialUncertainty());
		//	if (getSocialUncertainty()==0){
			socialUncertain = (Math.pow(getSocialUncertainty(), dNeedsWeight.get(NeedWeight.SOCIAL)));
			
			uncertainty = Double.isNaN((dEfficiencyUncertainty*dComfortabilityUncertainty*dSafetyUncertainty)+((socialUncertain)))?0:
				((dEfficiencyUncertainty*dComfortabilityUncertainty*dSafetyUncertainty)+((socialUncertain)));
			//System.out.println(" The total uncertain is "+ uncertainty);

			 dUncertainty =uncertainty;
			return uncertainty;
			
		}
////
		private int getTickCount(){
			 tickCount = (int) RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
			return tickCount;
		}			
		public double getModeSatisfaction() {
			return modeSatisfaction;
		}
////****This section serve as memory that collects previous experiences***
		private void updateModeStatisfactionList(){	myModeSatisfactionList.add(modeSatisfaction);}				
		public List<Double> getModeSatisfactionList() {return myModeSatisfactionList;}
		private void updateModeEfficiencyList(){myModeEfficiencyList.add(dEfficiency);}				
		public List<Double> getModeEffciencyList() {return myModeEfficiencyList;}
		private void updateModeComfortabilityList(){myModeComfortabilityList.add(dComfortability);}				
		public List<Double> getModeComfortabilityList() {return myModeComfortabilityList;}
		private void updateModeSafetyList(){myModeSafetyList.add(dSafety);}				
		public List<Double> getModeSafetyList() {return myModeSafetyList;}

		private void move(){ 
			GridPoint pt = grid.getLocation(this);
			NdPoint mypoint = space.getLocation(this);
			NdPoint otherpoint = new NdPoint(pt.getX(),pt.getY());
			double angle = SpatialMath.calcAngleFor2DMovement(space, mypoint, otherpoint);
			space.moveByVector(this, 5, angle,0);
			mypoint=space.getLocation(this);
			grid.moveTo(this,(int)mypoint.getX(),(int)mypoint.getY());
		}
		
		//@ScheduledMethod(start =5, interval=1,priority =0)
		public void selectBehaviour(){
			if ((getdUncertainty()>getUncertaintyThreshold())&&(getModeSatisfaction()<=getAspirationLevel())){
				passengerType.inquiring();
				behaviour = DecisionStrategy.INQUIRE;
			}else 
				if ((getdUncertainty()<getUncertaintyThreshold())&&(getModeSatisfaction()<=getAspirationLevel())){					
				setCheckOtherInfo(true);
				passengerType.optimising();
				behaviour = DecisionStrategy.OPTIMISE;
			}else if ((getdUncertainty()>getUncertaintyThreshold())&&(getModeSatisfaction()>=getAspirationLevel())){				
				passengerType.imitation();
				behaviour= DecisionStrategy.IMITATE;
			}else if ((getdUncertainty()<getUncertaintyThreshold())&&(getModeSatisfaction()>=getAspirationLevel())){				
				passengerType.repetition();	
				behaviour= DecisionStrategy.REPEAT;
			}	
		//	this.behaviour =behaviour;
			adoptInterracteeMode();
		//	updateModeSatisfaction();				
		}
		public DecisionStrategy getBehaviour() {
			return this.behaviour;
		}
	
		public boolean isUncertain(){
			if ((getdUncertainty()>getUncertaintyThreshold())){
				return true;
			}else return false;
		}	
		
		public boolean isSatisfied(){
			if ((getModeSatisfaction()>=getAspirationLevel())){
				return true;
			}else return false;
		}

		public int Uncertain(){
			if ((getdUncertainty()>getUncertaintyThreshold())){
				return 1;
			}else return 0;
		}	
		
		public int Satisfied(){
			if ((getModeSatisfaction()>=getAspirationLevel())){
				return 1;
			}else return 0;
		}
	
////////
		public int imitating(){
			if (this.behaviour == DecisionStrategy.IMITATE){
				return 1;
			}else return 0;
		}
		
		public int inquiring(){
			if (this.behaviour == DecisionStrategy.INQUIRE){
				return 1;
			}else return 0;
		}
		
		public int optimising(){
			if (this.behaviour == DecisionStrategy.OPTIMISE){
				return 1;
			}else return 0;
		}
		public int repeating(){
			if (this.behaviour == DecisionStrategy.REPEAT){
				return 1;
			}else return 0;
		}

		public void adoptInterracteeMode(){
			if (this.getInterracteePrefferedMode()instanceof PublicTransport){
				this.prefferedMode = new PublicTransport();		
			} else if ((this.getInterracteePrefferedMode()instanceof PersonalVehicle)&&(this.isCarOwnership())){
				this.prefferedMode = new PersonalVehicle();
			}else if ((this.getInterracteePrefferedMode()instanceof Cycle)){//&&(this.isCycleOwnership())){
				this.prefferedMode = new Cycle();
			}else if (this.getInterracteePrefferedMode()instanceof Walking){
				this.prefferedMode = new Walking();
			}				
		}
//////
public boolean isTraveldays(){
	double 	r = rand. nextDouble();
	int tickCounts = getTickCount();
	if((this.passengerType instanceof Cyclist &&(tickCounts!=0)&&(r<=0.0244))&&
			((tickCounts%30==1)||(tickCounts%30==2))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof Cyclist)&&(tickCounts!=0)&&(r<=0.0244+0.439))&&
			((tickCounts%7==1)||(tickCounts%7==2))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof Cyclist)&&(tickCounts!=0)&&(r<=0.0244+0.439+0.5366))&&
			((((tickCounts%7==0)||(tickCounts%7==1)||(tickCounts%7==2))||(tickCounts%7==3)||(tickCounts%7==4))||(tickCounts%7==5)||(tickCounts%7==6))){ 
		isTraveldays =true;
	}
	if(((this.passengerType instanceof Pedestrian)&&(tickCounts!=0)&&(r<=0.0109))&&
			((tickCounts%30==2)||(tickCounts%30==3))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof Pedestrian)&&(tickCounts!=0)&&(r<=0.0109+0.0109))&&
			((tickCounts%7==1)||(tickCounts%7==2))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof Pedestrian)&&(tickCounts!=0)&&(r<=0.0109+0.0109+0.228))&&
			((tickCounts%7==3)||(tickCounts%7==4)||(tickCounts%7==5)||(tickCounts%7==6))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof Pedestrian)&&(tickCounts!=0)&&(r<=0.0109+0.0109+0.228+0.75))&&
			((((tickCounts%7==0)||(tickCounts%7==1)||(tickCounts%7==2))||(tickCounts%7==3)||(tickCounts%7==4))||(tickCounts%7==5)||(tickCounts%7==6))){ 
		isTraveldays =true;
	}
	if(((this.passengerType instanceof PublicTransportUser)&&(tickCounts!=0)&&(r<=0.0215))&&
			((tickCounts%30==1)||(tickCounts%30==2))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof PublicTransportUser)&&(tickCounts!=0)&&(r<=0.0215+0.03226))&&
			((tickCounts%7==1)||(tickCounts%7==2))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof PublicTransportUser)&&(tickCounts!=0)&&(r<=0.0215+0.03226+0.03226))&&
			((tickCounts%7==3)||(tickCounts%7==4)||(tickCounts%7==5)||(tickCounts%7==6))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof PublicTransportUser)&&(tickCounts!=0)&&(r<=0.0215+0.03226+0.03226+0.6236))&&
			((((tickCounts%7==0)||(tickCounts%7==1)||(tickCounts%7==2))||(tickCounts%7==3)||(tickCounts%7==4))||(tickCounts%7==5)||(tickCounts%7==6))){ 
		isTraveldays =true;
	}
	if(((this.passengerType instanceof PersonalVehicleUser)&&(tickCounts!=0)&&(r<=0.037))&&
			((tickCounts%30==1)||(tickCounts%30==2))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof PersonalVehicleUser)&&(tickCounts!=0)&&(r<=0.037+0.025))&&
			((tickCounts%7==1)||(tickCounts%7==2))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof PersonalVehicleUser)&&(tickCounts!=0)&&(r<=0.037+0.025+0.4815))&&
			((tickCounts%7==3)||(tickCounts%7==4)||(tickCounts%7==5)||(tickCounts%7==6))){
		isTraveldays =true;
	}else if (((this.passengerType instanceof PersonalVehicleUser)&&(tickCounts!=0)&&(r<=0.037+0.025+0.4815+0.4568))&&
			((((tickCounts%7==0)||(tickCounts%7==1)||(tickCounts%7==2))||(tickCounts%7==3)||(tickCounts%7==4))||(tickCounts%7==5)||(tickCounts%7==6))){ 
		isTraveldays =true;
	}
	return isTraveldays;	
}
//////	
public boolean isCycleOwnership() {
	double 	r = rand. nextDouble();
	if((this.prefferedMode instanceof PublicTransport)&&(r<=0.5)||
	(this.prefferedMode instanceof PersonalVehicle)&&(r<=0.5)||
	(this.prefferedMode instanceof Walking)&&(r<=0.3)||(this.prefferedMode instanceof Cycle)){
		cycleOwnership =true;		
	}
	return cycleOwnership;
}

public boolean isCarOwnership() {
	double 	r = rand. nextDouble();
	if((this.prefferedMode instanceof PublicTransport)&&(r<=0.021)||
	(this.prefferedMode instanceof Cycle)&&(r<=0.05)||
	(this.prefferedMode instanceof Walking)&&(r<=0.02)||(this.prefferedMode instanceof PersonalVehicle)){
		carOwnership =true;		
	}
	return carOwnership;
}
//////
	public int personalVehicle(){
			if (this.getPrefferedMode() instanceof PersonalVehicle){
				return 1;
			}else return 0;
		}	
		public int cyle(){
			if (this.getPrefferedMode() instanceof Cycle){
				return 1;
			}else return 0;
		}
		public int walking(){
			if (this.getPrefferedMode() instanceof Walking){
				return 1;
			}else return 0;
		}
		public int publicTrans(){
			if (this.getPrefferedMode() instanceof PublicTransport){
				return 1;
			}else return 0;
	}

//////////THE OUTPUT AND DATA COLLECTION SECTION
///Mode Satisfaction
		public double getPrivateModeSatisfaction(){
			if(prefferedMode instanceof PersonalVehicle){
				privateModeSatisfaction = getModeSatisfaction();
				return privateModeSatisfaction;
			}else 
			return -1;		
		}
	///
		public double getPublicTransModeSatisfaction(){
			if(prefferedMode instanceof PublicTransport){
				publicModeSatisfaction = getModeSatisfaction();	
				return publicModeSatisfaction;
			}
			return -1;
		}
	/////
		public double getCycleModeSatisfaction(){	
			if(prefferedMode instanceof Cycle){
				cycleModeSatisfaction = getModeSatisfaction();
				return cycleModeSatisfaction;
			}	
			return -1;
		}
	////
		public double getWalkingSatisfaction(){
			if(prefferedMode instanceof Walking){
				walkingModeSatisfaction = getModeSatisfaction();
				return walkingModeSatisfaction;
			}
			return -1;
		}	
////

///////Mode Aspiration
				public double getPrivateModeAspiration(){
					if(prefferedMode instanceof PersonalVehicle){
						privateModeAspiration = this.getAspirationLevel();				
					}
					return privateModeAspiration;				
				}
			///
				public double getPublicTransModeAspiration(){
					if(prefferedMode instanceof PublicTransport){
						publicModeAspiration = this.getAspirationLevel();				
					}
					return publicModeAspiration;
				}
			/////
				public double getCycleModeAspiration(){	
					if(prefferedMode instanceof Cycle){
						cycleModeAspiration = this.getAspirationLevel();					
					}
					return cycleModeAspiration;
				}
			////
				public double getWalkingAspiration(){
					if(prefferedMode instanceof Walking){
						walkingModeAspiration = this.getAspirationLevel();					
					}
					return walkingModeAspiration;
				}	
////
///Mode Uncertainty
				public double getPrivateModeUncertainty(){
					if(prefferedMode instanceof PersonalVehicle){
						privateModeUncertainty = this.getdUncertainty();					
					}
					return privateModeUncertainty;				
				}
			///
				public double getPublicTransModeUncertainty(){
					if(prefferedMode instanceof PublicTransport){
						publicModeUncertainty = this.getdUncertainty();				
					}
					return publicModeUncertainty;
				}
			/////
				public double getCycleModeUncertainty(){	
					if(prefferedMode instanceof Cycle){
						cycleModeUncertainty = this.getdUncertainty();					
					}
					return cycleModeUncertainty;
				}
			////
				public double getWalkingUncertainty(){
					if(prefferedMode instanceof Walking){
						walkingModeUncertainty = this.getdUncertainty();						
					}
					return walkingModeUncertainty;
				}	
///UncertaintyTolerance
				public double getPrivateUncertaintyTolerance(){
					if(prefferedMode instanceof PersonalVehicle){
						privateUncertaintyTolerance = this.getUncertaintyThreshold();					
					}
					return privateUncertaintyTolerance;				
				}
			///
				public double getPublicTransUncertaintyTolerance(){
					if(prefferedMode instanceof PublicTransport){
						publicUncertaintyTolerance = this.getUncertaintyThreshold();				
					}
					return publicUncertaintyTolerance;
				}
			/////
				public double getCycleUncertaintyTolerance(){	
					if(prefferedMode instanceof Cycle){
						cycleUncertaintyTolerance = this.getUncertaintyThreshold();					
					}
					return cycleUncertaintyTolerance;
				}
			////
				public double getWalkingUncertaintyTolerance(){
					if(prefferedMode instanceof Walking){
						walkingUncertaintyTolerance = this.getUncertaintyThreshold();						
					}
					return walkingUncertaintyTolerance;
				}	
///Private Car ECS
//		public double getPrivateModeEfficiency(){
//			double modeEfficiency =getdEfficiency();
//			if(prefferedMode instanceof PersonalVehicle){
//				return modeEfficiency;
//			}else
//				return -1;	
//		}
//		public double getPrivateModeSafety(){
//			double modeSafety =getdSafety();
//			if(prefferedMode instanceof PersonalVehicle){
//				return modeSafety;
//			}else
//				return -1;	
//		}
//		public double getPrivateModeComfort(){
//			double modeComfortability =getdComfortability();
//			if(prefferedMode instanceof PersonalVehicle){
//				return modeComfortability;
//			}else
//				return -1;	
//		}
////Cycle ECS		
//		public double getCycleModeEfficiency(){
//			double modeEfficiency =getdEfficiency();
//			if(prefferedMode instanceof Cycle){
//				return modeEfficiency;
//			}else
//				return -1;	
//		}
//		public double getCycleModeSafety(){
//			double modeSafety = getdSafety();
//			if(prefferedMode instanceof Cycle){
//				return modeSafety;
//			}else
//				return -1;	
//		}
//		public double getCycleModeComfort(){
//			double modeComfortability = getdComfortability();
//			if(prefferedMode instanceof Cycle){
//				return modeComfortability;
//			}else
//				return -1;	
//		}
////PublicTrans ECS
//		public double getPublicModeEfficiency(){
//			double modeEfficiency =getdEfficiency();
//			if(prefferedMode instanceof PublicTransport){
//				return modeEfficiency;
//			}else
//				return -1;	
//		}
//		public double getPublicModeSafety(){
//			double modeSafety =getdSafety();
//			if(prefferedMode instanceof PublicTransport){
//				return modeSafety;
//			}else
//				return -1;	
//		}
//		public double getPublicModeComfort(){
//			double modeComfortability =getdComfortability();
//			if(prefferedMode instanceof PublicTransport){
//				return modeComfortability;
//			}else
//				return -1;	
//		}
//////Walking and ECs
//		public double getWalkingModeEfficiency(){
//			double modeEfficiency =getdEfficiency();
//			if(prefferedMode instanceof Walking){
//				return modeEfficiency;
//			}else
//				return -1;	
//		}
//		public double getWalkingModeSafety(){
//			double modeSafety =getdSafety();
//			if(prefferedMode instanceof Walking){
//				return modeSafety;
//			}else
//				return -1;	
//		}
//		public double getWalkingModeComfort(){
//			double modeComfortability =getdComfortability();
//			if(prefferedMode instanceof Walking){
//				return modeComfortability;
//			}else
//				return -1;	
//		}
//		
///New Mode ECS		
		public double getPrivateModeEfficiency(){
			double modeEfficiency =myPerception.getPrivateModeEfficiency();
			if(prefferedMode instanceof PersonalVehicle){
				return modeEfficiency;
			}else
				return -1;	
		}
		public double getPrivateModeSafety(){
			double modeSafety =myPerception.getPrivateModeSafety();
			if(prefferedMode instanceof PersonalVehicle){
				return modeSafety;
			}else
				return -1;	
		}
		public double getPrivateModeComfort(){
			double modeComfortability =myPerception.getPrivateModeComfortability();
			if(prefferedMode instanceof PersonalVehicle){
				return modeComfortability;
			}else
				return -1;	
		}
//////
		public double getCycleModeEfficiency(){
			double modeEfficiency =myPerception.getCycleModeEfficiency();
			if(prefferedMode instanceof Cycle){
				return modeEfficiency;
			}else
				return -1;	
		}
		public double getCycleModeSafety(){
			double modeSafety = myPerception.getCycleModeSafety();
			if(prefferedMode instanceof Cycle){
				return modeSafety;
			}else
				return -1;	
		}
		public double getCycleModeComfort(){
			double modeComfortability = myPerception.getCycleModeComfortability();
			if(prefferedMode instanceof Cycle){
				return modeComfortability;
			}else
				return -1;	
		}
//////
		public double getPublicModeEfficiency(){
			double modeEfficiency =myPerception.getPublicModeEfficiency();
			if(prefferedMode instanceof PublicTransport){
				return modeEfficiency;
			}else
				return -1;	
		}
		public double getPublicModeSafety(){
			double modeSafety =myPerception.getPublicModeSafety();
			if(prefferedMode instanceof PublicTransport){
				return modeSafety;
			}else
				return -1;	
		}
		public double getPublicModeComfort(){
			double modeComfortability =myPerception.getPublicModeComfortability();
			if(prefferedMode instanceof PublicTransport){
				return modeComfortability;
			}else
				return -1;	
		}
////Walking and ECs
		public double getWalkingModeEfficiency(){
			double modeEfficiency =myPerception.getWalkingModeEfficiency();
			if(prefferedMode instanceof Walking){
				return modeEfficiency;
			}else
				return -1;	
		}
		public double getWalkingModeSafety(){
			double modeSafety =myPerception.getWalkingModeSafety();
			if(prefferedMode instanceof Walking){
				return modeSafety;
			}else
				return -1;	
		}
		public double getWalkingModeComfort(){
			double modeComfortability =myPerception.getWalkingModeComfortability();
			if(prefferedMode instanceof Walking){
				return modeComfortability;
			}else
				return -1;	
		}
/////////////
////All Mode PCA dis-aggregate involvement
//Physical
		public double getPrivateModePhysicalSatisfaction(){							
			if(prefferedMode instanceof PersonalVehicle){
				double privateModePhysicalSatisfaction =((myPerception.getPrivateModeSafetyPhysical()) + (myPerception.getPrivateModeComfortabilityPhysical())/2);	
				return privateModePhysicalSatisfaction;
			}else
				return -1;	
		}
		public double getPublicModePhysicalSatisfaction(){					
			if(prefferedMode instanceof PublicTransport){	
				double publicModePhysicalSatisfaction =((myPerception.getPublicModeSafetyPhysical()) + (myPerception.getPublicModeComfortabilityPhysical())/2);	
				return publicModePhysicalSatisfaction;
			}else
				return -1;	
		}
		public double getCycleModePhysicalSatisfaction(){			
			if(prefferedMode instanceof Cycle){		
				double cycleModePhysicalSatisfaction =((myPerception.getCycleModeSafetyPhysical()) + (myPerception.getCycleModeComfortabilityPhysical())/2);
				return cycleModePhysicalSatisfaction;
			}else
				return -1;	
		}
		public double getWalkingModePhysicalSatisfaction(){							
			if(prefferedMode instanceof Walking){	
				double walkingModePhysicalSatisfaction =((myPerception.getWalkingModeSafetyPhysical()) + (myPerception.getWalkingModeComfortabilityPhysical())/2);
				return walkingModePhysicalSatisfaction;
			}else
				return -1;	
		}	
/////
		public double getPrivateModeCognitiveSatisfaction(){		
			if(prefferedMode instanceof PersonalVehicle){
				double privateModeCognitiveSatisfaction =((myPerception.getPrivateModeSafetyCognitive()) +(myPerception.getPrivateModeEfficiencyCognitive())+
						(myPerception.getPrivateModeComfortabilityCognitive())/3);	
				return privateModeCognitiveSatisfaction;
			}else
				return -1;	
		}
		public double getPublicModeCognitiveSatisfaction(){		
			if(prefferedMode instanceof PublicTransport){
				double publicModeCognitiveSatisfaction =((myPerception.getPublicModeSafetyCognitive()) +(myPerception.getPublicModeEfficiencyCognitive())+
						(myPerception.getPublicModeComfortabilityCognitive())/3);	
				return publicModeCognitiveSatisfaction;
			}else
				return -1;	
		}
		public double getCycleModeCognitiveSatisfaction(){			
			if(prefferedMode instanceof Cycle){
				double cycleModeCognitiveSatisfaction =((myPerception.getCycleModeSafetyCognitive()) +(myPerception.getCycleModeEfficiencyCognitive())+
						(myPerception.getCycleModeComfortabilityCognitive())/3);	
				return cycleModeCognitiveSatisfaction;
			}else
				return -1;	
		}
		public double getWalkingModeCognitiveSatisfaction(){			
			if(prefferedMode instanceof Walking){
				double walkingModeCognitiveSatisfaction =((myPerception.getWalkingModeSafetyCognitive()) +(myPerception.getWalkingModeEfficiencyCognitive())+
						(myPerception.getWalkingModeComfortabilityCognitive())/3);	
				return walkingModeCognitiveSatisfaction;
			}else
				return -1;	
		}
////
		public double getPrivateModeAffectiveSatisfaction(){		
			if(prefferedMode instanceof PersonalVehicle){
				double privateModeAffectiveSatisfaction =((myPerception.getPrivateModeSafetyAffective()) +(myPerception.getPrivateModeEfficiencyAffective())+
						(myPerception.getPrivateModeComfortabilityAffective())/3);	
				return privateModeAffectiveSatisfaction;
			}else
				return -1;	
		}
		public double getPublicModeAffectiveSatisfaction(){		
			if(prefferedMode instanceof PublicTransport){
				double publicModeAffectiveSatisfaction =((myPerception.getPublicModeSafetyAffective()) +(myPerception.getPublicModeEfficiencyAffective())+
						(myPerception.getPublicModeComfortabilityAffective())/3);	
				return publicModeAffectiveSatisfaction;
			}else
				return -1;	
		}
		public double getCycleModeAffectiveSatisfaction(){			
			if(prefferedMode instanceof Cycle){
				double cycleModeAffectiveSatisfaction =((myPerception.getCycleModeSafetyAffective()) +(myPerception.getCycleModeEfficiencyAffective())+
						(myPerception.getCycleModeComfortabilityAffective())/3);	
				return cycleModeAffectiveSatisfaction;
			}else
				return -1;	
		}
		public double getWalkingModeAffectiveSatisfaction(){			
			if(prefferedMode instanceof Walking){
				double walkingModeAffectiveSatisfaction =((myPerception.getWalkingModeSafetyAffective()) +(myPerception.getWalkingModeEfficiencyAffective())+
						(myPerception.getWalkingModeComfortabilityAffective())/3);	
				return walkingModeAffectiveSatisfaction;
			}else
				return -1;	
		}


//Private ESC-PCA
///Efficiency
	public double getPrivateModeEfficiencyCognitive(){
		double modeEfficiencyCognitive =myPerception.getPrivateModeEfficiencyCognitive();
		if(prefferedMode instanceof PersonalVehicle){
			return modeEfficiencyCognitive;
		}else
			return -1;	
	}
	public double getPrivateModeEfficiencyAffective(){
		double modeEfficiencyAffective =myPerception.getPrivateModeEfficiencyAffective();
		if(prefferedMode instanceof PersonalVehicle){
			return modeEfficiencyAffective;
		}else
			return -1;	
	}
////	Safety
	public double getPrivateModeSafetyCognitive(){
		double modeSafetyCognitive =myPerception.getPrivateModeSafetyCognitive();
		if(prefferedMode instanceof PersonalVehicle){
			return modeSafetyCognitive;
		}else
			return -1;	
	}
	public double getPrivateModeSafetyAffective(){
		double modeSafetyAffective =myPerception.getPrivateModeSafetyAffective();
		if(prefferedMode instanceof PersonalVehicle){
			return modeSafetyAffective;
		}else
			return -1;	
	}
	public double getPrivateModeSafetyPhysical(){
		double modeSafetyPhysical =myPerception.getPrivateModeSafetyPhysical();
		if(prefferedMode instanceof PersonalVehicle){
			return modeSafetyPhysical;
		}else
			return -1;	
	}
////Comfortability
	public double getPrivateModeComfortabilityCognitive(){
		double modeSafetyCognitive =myPerception.getPrivateModeComfortabilityCognitive();
		if(prefferedMode instanceof PersonalVehicle){
			return modeSafetyCognitive;
		}else
			return -1;	
	}
	public double getPrivateModeComfortabilityAffective(){
		double modeSafetyAffective =myPerception.getPrivateModeComfortabilityAffective();
		if(prefferedMode instanceof PersonalVehicle){
			return modeSafetyAffective;
		}else
			return -1;	
	}
	public double getPrivateModeComfortabilityPhysical(){
		double modeSafetyPhysical =myPerception.getPrivateModeComfortabilityPhysical();
		if(prefferedMode instanceof PersonalVehicle){
			return modeSafetyPhysical;
		}else
			return -1;	
	}	
///Cycle
///Cycle ECS_PCA
//Efficiency
	public double getCycleModeEfficiencyCognitive(){
		double modeEfficiencyCognitive =myPerception.getCycleModeEfficiencyCognitive();
		if(prefferedMode instanceof Cycle){
			return modeEfficiencyCognitive;
		}else
			return -1;	
	}
	public double getCycleModeEfficiencyAffective(){
		double modeEfficiencyAffective =myPerception.getCycleModeEfficiencyAffective();
		if(prefferedMode instanceof Cycle){
			return modeEfficiencyAffective;
		}else
			return -1;	
	}
////Safety
	public double getCycleModeSafetyCognitive(){
		double modeSafetyCognitive =myPerception.getCycleModeSafetyCognitive();
		if(prefferedMode instanceof Cycle){
			return modeSafetyCognitive;
		}else
			return -1;	
	}
	public double getCycleModeSafetyAffective(){
		double modeSafetyAffective =myPerception.getCycleModeSafetyAffective();
		if(prefferedMode instanceof Cycle){
			return modeSafetyAffective;
		}else
			return -1;	
	}
	public double getCycleModeSafetyPhysical(){
		double modeSafetyPhysical =myPerception.getCycleModeSafetyPhysical();
		if(prefferedMode instanceof Cycle){
			return modeSafetyPhysical;
		}else
			return -1;	
	}
///Comfortability
	public double getCycleModeComfortabilityCognitive(){
		double modeSafetyCognitive =myPerception.getCycleModeComfortabilityCognitive();
		if(prefferedMode instanceof Cycle){
			return modeSafetyCognitive;
		}else
			return -1;	
	}
	public double getCycleModeComfortabilityAffective(){
		double modeSafetyAffective =myPerception.getCycleModeComfortabilityAffective();
		if(prefferedMode instanceof Cycle){
			return modeSafetyAffective;
		}else
			return -1;	
	}
	public double getCycleModeComfortabilityPhysical(){
		double modeSafetyPhysical =myPerception.getCycleModeComfortabilityPhysical();
		if(prefferedMode instanceof Cycle){
			return modeSafetyPhysical;
		}else
			return -1;	
	}
///Satisfaction APC Counts
///Affective counts
	public double getPrivateSatisfactionAffectiveCounts(){			
		return 	privateSatisfactionAffectiveCounts=this.myPerception.getPrivateSatisfactionAffectiveCounts();			
	}
	public double getCycleSatisfactionAffectiveCounts(){			
		return 	cycleSatisfactionAffectiveCounts=this.myPerception.getCycleSatisfactionAffectiveCounts();			
	}
	public double getPublicSatisfactionAffectiveCounts(){			
		return 	publicSatisfactionAffectiveCounts=this.myPerception.getPublicSatisfactionAffectiveCounts();			
	}
	public double getWalkingSatisfactionAffectiveCounts(){			
		return 	walkingSatisfactionAffectiveCounts=this.myPerception.getWalkingSatisfactionAffectiveCounts();			
	}
///Cognitive Counts
	public double getPrivateSatisfactionCognitiveCounts(){			
		return 	privateSatisfactionCognitiveCounts=this.myPerception.getPrivateSatisfactionCognitiveCounts();			
	}
	public double getCycleSatisfactionCognitiveCounts(){			
		return 	cycleSatisfactionCognitiveCounts=this.myPerception.getCycleSatisfactionCognitiveCounts();			
	}
	public double getPublicSatisfactionCognitiveCounts(){			
		return 	publicSatisfactionCognitiveCounts=this.myPerception.getPublicSatisfactionCognitiveCounts();			
	}
	public double getWalkingSatisfactionCognitiveCounts(){			
		return 	walkingSatisfactionCognitiveCounts=this.myPerception.getWalkingSatisfactionCognitiveCounts();			
	}
////Physical Counts
	public double getPrivateSatisfactionPhysicalCounts(){			
		return 	privateSatisfactionPhysicalCounts=this.myPerception.getPrivateSatisfactionPhysicalCounts();			
	}
	public double getCycleSatisfactionPhysicalCounts(){			
		return 	cycleSatisfactionPhysicalCounts=this.myPerception.getCycleSatisfactionPhysicalCounts();			
	}
	public double getPublicSatisfactionPhysicalCounts(){			
		return 	publicSatisfactionPhysicalCounts=this.myPerception.getPublicSatisfactionPhysicalCounts();			
	}
	public double getWalkingSatisfactionPhysicalCounts(){			
		return 	walkingSatisfactionPhysicalCounts=this.myPerception.getWalkingSatisfactionPhysicalCounts();			
	}
///Satisfaction Cognitive Levels at the ECS level for all mode  at top level
//Unpleasant
	public double getPrivateSatisfactionUnpleasantCognitiveCounts(){			
		return 	privateSatisfactionUnpleasantCognitiveCounts=this.myPerception.getPrivateSatisfactionUnpleasantCognitiveCounts();			
	}
	public double getCycleSatisfactionUnpleasantCognitiveCounts(){			
		return 	cycleSatisfactionUnpleasantCognitiveCounts=this.myPerception.getCycleSatisfactionUnpleasantCognitiveCounts();			
	}
	public double getPublicSatisfactionUnpleasantCognitiveCounts(){			
		return 	publicSatisfactionUnpleasantCognitiveCounts=this.myPerception.getPublicSatisfactionUnpleasantCognitiveCounts();			
	}
	public double getWalkingSatisfactionUnpleasantCognitiveCounts(){			
		return 	walkingSatisfactionUnpleasantCognitiveCounts=this.myPerception.getWalkingSatisfactionUnpleasantCognitiveCounts();			
	}
////NeitherNor
	public double getPrivateSatisfactionNeitherNorPleasantCognitiveCounts(){			
		return 	privateSatisfactionNeitherNorPleasantCognitiveCounts=this.myPerception.getPrivateSatisfactionNeitherNorPleasantCognitiveCounts();			
	}
	public double getCycleSatisfactionNeitherNorPleasantCognitiveCounts(){			
		return 	cycleSatisfactionNeitherNorPleasantCognitiveCounts=this.myPerception.getCycleSatisfactionNeitherNorPleasantCognitiveCounts();			
	}
	public double getPublicSatisfactionNeitherNorPleasantCognitiveCounts(){			
		return 	publicSatisfactionNeitherNorPleasantCognitiveCounts=this.myPerception.getPublicSatisfactionNeitherNorPleasantCognitiveCounts();			
	}
	public double getWalkingSatisfactionNeitherNorPleasantCognitiveCounts(){			
		return 	walkingSatisfactionNeitherNorPleasantCognitiveCounts=this.myPerception.getWalkingSatisfactionNeitherNorPleasantCognitiveCounts();			
	}
//Pleasant
	public double getPrivateSatisfactionPleasantCognitiveCounts(){			
		return 	privateSatisfactionPleasantCognitiveCounts=this.myPerception.getPrivateSatisfactionPleasantCognitiveCounts();			
	}
	public double getCycleSatisfactionPleasantCognitiveCounts(){			
		return 	cycleSatisfactionPleasantCognitiveCounts=this.myPerception.getCycleSatisfactionPleasantCognitiveCounts();			
	}
	public double getPublicSatisfactionPleasantCognitiveCounts(){			
		return 	publicSatisfactionPleasantCognitiveCounts=this.myPerception.getPublicSatisfactionPleasantCognitiveCounts();			
	}
	public double getWalkingSatisfactionPleasantCognitiveCounts(){			
		return 	walkingSatisfactionPleasantCognitiveCounts=this.myPerception.getWalkingSatisfactionPleasantCognitiveCounts();			
	}
///Satisfaction Physical counts at the ECS Levels for all mode at top level
//Unpleasant
		public double getPrivateSatisfactionUnpleasantPhysicalCounts(){			
			return 	privateSatisfactionUnpleasantPhysicalCounts=this.myPerception.getPrivateSatisfactionUnpleasantPhysicalCounts();			
		}
		public double getCycleSatisfactionUnpleasantPhysicalCounts(){			
			return 	cycleSatisfactionUnpleasantPhysicalCounts=this.myPerception.getCycleSatisfactionUnpleasantPhysicalCounts();			
		}
		public double getPublicSatisfactionUnpleasantPhysicalCounts(){			
			return 	publicSatisfactionUnpleasantPhysicalCounts=this.myPerception.getPublicSatisfactionUnpleasantPhysicalCounts();			
		}
		public double getWalkingSatisfactionUnpleasantPhysicalCounts(){			
			return 	walkingSatisfactionUnpleasantPhysicalCounts=this.myPerception.getWalkingSatisfactionUnpleasantPhysicalCounts();			
		}
////NeitherNor
		public double getPrivateSatisfactionNeitherNorPleasantPhysicalCounts(){			
			return 	privateSatisfactionNeitherNorPleasantPhysicalCounts=this.myPerception.getPrivateSatisfactionNeitherNorPleasantPhysicalCounts();			
		}
		public double getCycleSatisfactionNeitherNorPleasantPhysicalCounts(){			
			return 	cycleSatisfactionNeitherNorPleasantPhysicalCounts=this.myPerception.getCycleSatisfactionNeitherNorPleasantPhysicalCounts();			
		}
		public double getPublicSatisfactionNeitherNorPleasantPhysicalCounts(){			
			return 	publicSatisfactionNeitherNorPleasantPhysicalCounts=this.myPerception.getPublicSatisfactionNeitherNorPleasantPhysicalCounts();			
		}
		public double getWalkingSatisfactionNeitherNorPleasantPhysicalCounts(){			
			return 	walkingSatisfactionNeitherNorPleasantPhysicalCounts=this.myPerception.getWalkingSatisfactionNeitherNorPleasantPhysicalCounts();			
		}
//Pleasant
		public double getPrivateSatisfactionPleasantPhysicalCounts(){			
			return 	privateSatisfactionPleasantPhysicalCounts=this.myPerception.getPrivateSatisfactionPleasantPhysicalCounts();			
		}
		public double getCycleSatisfactionPleasantPhysicalCounts(){			
			return 	cycleSatisfactionPleasantPhysicalCounts=this.myPerception.getCycleSatisfactionPleasantPhysicalCounts();			
		}
		public double getPublicSatisfactionPleasantPhysicalCounts(){			
			return 	publicSatisfactionPleasantPhysicalCounts=this.myPerception.getPublicSatisfactionPleasantPhysicalCounts();			
		}
		public double getWalkingSatisfactionPleasantPhysicalCounts(){			
			return 	walkingSatisfactionPleasantPhysicalCounts=this.myPerception.getWalkingSatisfactionPleasantPhysicalCounts();			
		}
//Satisfaction Affective Level for all mode together at top level
//All mode pleasant affective counts at top level:Unpleasant
		public double getPrivateSatisfactionUnpleasantAffectiveCounts(){			
			return 	privateSatisfactionUnpleasantAffectiveCounts=this.myPerception.getPrivateSatisfactionUnpleasantAffectiveCounts();			
		}
		public double getCycleSatisfactionUnpleasantAffectiveCounts(){			
			return 	cycleSatisfactionUnpleasantAffectiveCounts=this.myPerception.getCycleSatisfactionUnpleasantAffectiveCounts();			
		}
		public double getPublicSatisfactionUnpleasantAffectiveCounts(){			
			return 	publicSatisfactionUnpleasantAffectiveCounts=this.myPerception.getPublicSatisfactionUnpleasantAffectiveCounts();			
		}
		public double getWalkingSatisfactionUnpleasantAffectiveCounts(){			
			return 	walkingSatisfactionUnpleasantAffectiveCounts=this.myPerception.getWalkingSatisfactionUnpleasantAffectiveCounts();			
		}
//All mode pleasant affective counts at top level:Pleasant Nor Unpleasant	
		public double getPrivateSatisfactionNeitherNorPleasantAffectiveCounts(){			
			return 	privateSatisfactionNeitherNorPleasantAffectiveCounts=this.myPerception.getPrivateSatisfactionNeitherNorPleasantAffectiveCounts();			
		}
		public double getCycleSatisfactionNeitherNorPleasantAffectiveCounts(){			
			return 	cycleSatisfactionNeitherNorPleasantAffectiveCounts=this.myPerception.getCycleSatisfactionNeitherNorPleasantAffectiveCounts();			
		}
		public double getPublicSatisfactionNeitherNorPleasantAffectiveCounts(){			
			return 	publicSatisfactionNeitherNorPleasantAffectiveCounts=this.myPerception.getPublicSatisfactionNeitherNorPleasantAffectiveCounts();			
		}
		public double getWalkingSatisfactionNeitherNorPleasantAffectiveCounts(){			
			return 	walkingSatisfactionNeitherNorPleasantAffectiveCounts=this.myPerception.getWalkingSatisfactionNeitherNorPleasantAffectiveCounts();			
		}
///All mode pleasant affective counts at top level: Pleasant 
		public double getPrivateSatisfactionPleasantAffectiveCounts(){			
			return 	privateSatisfactionPleasantAffectiveCounts=this.myPerception.getPrivateSatisfactionPleasantAffectiveCounts();			
		}
		public double getCycleSatisfactionPleasantAffectiveCounts(){			
			return 	cycleSatisfactionPleasantAffectiveCounts=this.myPerception.getCycleSatisfactionPleasantAffectiveCounts();			
		}
		public double getPublicSatisfactionPleasantAffectiveCounts(){			
			return 	publicSatisfactionPleasantAffectiveCounts=this.myPerception.getPublicSatisfactionPleasantAffectiveCounts();			
		}
		public double getWalkingSatisfactionPleasantAffectiveCounts(){			
			return 	walkingSatisfactionPleasantAffectiveCounts=this.myPerception.getWalkingSatisfactionPleasantAffectiveCounts();			
		}
		
	//////All counts
//				public double getPrivateAllConvenienceCognitiveCounts(){			
//					return 	privateAllConvenienceCognitiveCounts=this.myPerception.getPrivateVPConvenienceCognitiveCounts();			
//				}
//				public double getCycleAllConvenienceCognitiveCounts(){			
//					return 	cycleAllConvenienceCognitiveCounts=this.myPerception.getCycleVPConvenienceCognitiveCounts();			
//				}
//				public double getPrivateAllConveniencePhysicalCounts(){			
//					return 	privateAllConveniencePhysicalCounts=this.myPerception.getPrivateVPConveniencePhysicalCounts();			
//				}
//				public double getCycleAllConveniencePhysicalCounts(){			
//					return 	cycleAllConveniencePhysicalCounts=this.myPerception.getCycleVPConveniencePhysicalCounts();			
//				}
//				public double getPrivateAllConvenienceAffectiveCounts(){			
//					return 	privateAllConvenienceAffectiveCounts=this.myPerception.getPrivateVPConvenienceAffectiveCounts();			
//				}
//				public double getCycleAllConvenienceAffectiveCounts(){			
//					return 	cycleAllConvenienceAffectiveCounts=this.myPerception.getCycleVPConvenienceAffectiveCounts();			
//				}
	//
}

		
	
	

	
	
			
			
			
			
