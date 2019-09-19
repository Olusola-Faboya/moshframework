package moshproject.agents.intervener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import moshproject.agent.enums.DecisionStrategy;
import moshproject.agent.mode.attributes.DistanceToMainMode;
import moshproject.agent.mode.attributes.EaseOfAccessInformation;
import moshproject.agent.mode.attributes.EaseOfGettingOnOffMode;
import moshproject.agent.mode.attributes.EaseOfGettingTOMainMode;
import moshproject.agent.mode.attributes.InfoReliability;
import moshproject.agent.mode.attributes.ModeDelays;
import moshproject.agent.mode.attributes.ModeFrequency;
import moshproject.agent.mode.attributes.ModeSafety;
import moshproject.agent.mode.attributes.ModeSecurity;
import moshproject.agent.mode.attributes.ModeTimeliness;
import moshproject.agent.mode.attributes.OtherUsersAttitude;
import moshproject.agent.mode.attributes.ParkingSpaceConcern;
import moshproject.agent.mode.attributes.ProtectionFromElements;
import moshproject.agent.mode.attributes.SignsAvailability;
import moshproject.agent.mode.attributes.WalkingToDestination;
import moshproject.agents.passenger.Pedestrian;
import moshproject.agents.mode.Cycle;
import moshproject.agents.mode.PersonalVehicle;
import moshproject.agents.mode.PublicTransport;
import moshproject.agents.mode.Walking;
import moshproject.agents.passenger.Cyclist;
import moshproject.agents.passenger.ModeAttributesPerceptions;
import moshproject.agents.passenger.Passenger;
import moshproject.agents.passenger.PersonalVehicleUser;
import moshproject.agents.passenger.PublicTransportUser;
import moshproject.common.Constants;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameter;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Intervener {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	Random rand = new Random();
	Passenger passenger;	
	InterventionPanel interventionPanel;
	List<Passenger> allCarUsers;	
	List<Passenger> selectedCyclists;	
	List<Passenger> selectedPedestrians;	
	List<Passenger> selectedCarUsers;
	List<Passenger> selectedPublicTransUsers;
	
	boolean reliabilityStatus;
	boolean journeyTimeStatus;
	boolean convenienceStatus;
	boolean valueForMoneyStatus;
	boolean comfortStatus;
	boolean mobilityStatus;
	boolean securityStatus;
	
	private double reliability;
	private double journeyTime;
	private double valueForMoney;
	private double security;
	private double mobility;
	private double convenience;
	private double comfort;
	
	public Intervener(ContinuousSpace<Object> space, Grid<Object> grid) {	
		this.space = space;
		this.grid = grid;		
		this.reliabilityStatus=false;
		this.journeyTimeStatus=false;
		this.convenienceStatus=false;
		this.valueForMoneyStatus=false;
		this.comfortStatus=false;
		this.mobilityStatus=false;
		this.securityStatus=false;
			
		this.reliability=0.0;
		this.journeyTime=0.0;	
		this.valueForMoney=0.0;
		this.security=0.0;
		this.mobility=0.0;
		this.convenience=0.0;
		this.comfort=0.0;
	}	
	public void initialize() {
		// TODO Auto-generated method stub		
	}		
		
	ModeAttributesPerceptions intervenerPerception;
	private double gettingToDestinationOnTimePerception;
	private double gettingToDestinationOnTimeAffective;
	private double delaysPerception;
	private double delaysAffective;
	private double othersAttitudePerception;
	private double othersAttitudeAffective;
	private double parkingSpaceConcernPerception;
	private double parkingSpaceConcernAffective;
	private double signsAvailabilityPerception;
	private double signsAvailabilityAffective;
	private double personalSafetyAffective;
	private double personalSafetyPerception;
	private double walkingtoDestinationPerception;
	private double walkingToDestinationAffective;
	private double easeOfAcessInfoPerception;
	private double easeOfAccessInfoAffective;
	private double infoReliabilityPerception;
	private double infoReliabilityAffective;
	private double modeFrequencyPerception;
	private double modeFrequencyAffective;
	private double protectionFromElementsPerception;
	private double protectionFromElementsAffective;
	private double gettingOnOffmodePerception;
	private double gettingOnOffmodeAffective;
	private double gettingOnOffmodePhysical;
	private double parkingSpaceConcernPhysical;
	private double easeOfGettingToMainmodePerception;
	
	public void setPassenger(Passenger pass){
		this.passenger = pass;
		this.passenger.setMyPerception(pass.getMyPerception());
	}
	

	public List<Passenger> getCyclists() {
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		selectedCyclists = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if(passes.getPrefferedMode() instanceof Cycle){//&&(passes.getBehaviour()==DecisionStrategy.OPTIMISE)){
					selectedCyclists.add(passes);
			}
		}
		return selectedCyclists;
	}
	public List<Passenger> getSelectedCyclists() {
		return selectedCyclists;
	}
	////Gather Public Transport USers
	public List<Passenger> getPublicTransUsers() {
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		selectedPublicTransUsers = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if(passes.getPrefferedMode() instanceof PublicTransport){//&&(passes.getBehaviour()==DecisionStrategy.OPTIMISE)){
					selectedPublicTransUsers.add(passes);
			}
		}
		return selectedPublicTransUsers;
	}	
	public List<Passenger> getSelectedPublicTransUsers() {
		return selectedPublicTransUsers;
	}
	////Gather Walkers
	public List<Passenger> getWalkers(){
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		selectedPedestrians = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if(passes.getPrefferedMode() instanceof Walking){//&&(passes.getBehaviour()==DecisionStrategy.OPTIMISE)){
					selectedPedestrians.add(passes);
				}
			}
			
		return selectedPedestrians;
	}
	public List<Passenger> getSelectedPedestrians() {
		return selectedPedestrians;
	}
	////Gather PrivateUsers
	public List<Passenger> getPrivateUsers(){
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		selectedCarUsers = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if(passes.getPrefferedMode() instanceof PersonalVehicle){//&&(passes.getBehaviour()==DecisionStrategy.OPTIMISE)){
					selectedCarUsers.add(passes);
				}
			}
		return selectedCarUsers;
	}
	public List<Passenger> getSelectedCarUsers() {
		return selectedCarUsers;
	}
	

	public void setReliabiltyStatus(boolean b) {
		this.reliabilityStatus = b;
	}
	public boolean getReliabilityStatus() {
		return  reliabilityStatus;
	}
	public void setJourneyTimeStatus(boolean b) {
		this.journeyTimeStatus = b;
	}
	public boolean getJourneyTimeStatus() {
		return  journeyTimeStatus;
	}
	public void setConvenienceStatus(boolean b) {
		this.convenienceStatus = b;
	}
	public boolean getConvenienceStatus() {
		return  convenienceStatus;
	}
	public void setValueForMoneyStatus(boolean b) {
		this.valueForMoneyStatus = b;
	}
	public boolean getValueForMoneyStatus() {
		return  valueForMoneyStatus;
	}
	public void setComfortStatus(boolean b) {
		this.comfortStatus = b;
	}
	public boolean getComfortStatus() {
		return  comfortStatus;
	}
	public void setMobilityStatus(boolean b) {
		this.mobilityStatus = b;
	}
	public boolean getMobilityStatus() {
		return  mobilityStatus;
	}
	public void setSecurityStatus(boolean b) {
		this.securityStatus = b;
	}
	public boolean getSecurityStatus() {
		return  securityStatus;
	}
///////////
	//@ScheduledMethod(start =70, interval=1)	
	public void applyAllModesIntervention(){		
		applyCyclistIntervention();
		applyPedestrianIntervention();
	///	applyPrivateUsersIntervention();
		applyPublicTransIntervention();
	}
	
	//@ScheduledMethod(start =70, interval=1)	
		public void applyCyclistIntervention(){		
			getCyclists();
			if(getJourneyTimeStatus()){					
				for(Passenger passenger: selectedCyclists){					
					if(passenger.isCheckOtherInfo()){
						System.out.println(passenger.isCheckOtherInfo());
						setGettingToDestOnTimePerception(passenger);
						setGettingToDestOnTimeAffective(passenger);
						setDelaysPerception(passenger);
						setDelaysAffective(passenger);
					}
				}
			}
			if(getComfortStatus()){				
				for(Passenger passenger: selectedCyclists){
					if(passenger.isCheckOtherInfo()){
						System.out.println(passenger.isCheckOtherInfo());
						setOthersAttitudePerception(passenger);
						setOthersAttitudeAffective(passenger);
					}
				}
			}
			if(getConvenienceStatus()){			
				for(Passenger passenger: selectedCyclists){
					if(passenger.isCheckOtherInfo()){
						System.out.println(passenger.isCheckOtherInfo());
						setParkingSpaceConcernPerception(passenger);
						setParkingSpaceConcernAffective(passenger);
					}
				}
			}
			if(getSecurityStatus()){				
				for(Passenger passenger: selectedCyclists){
					if(passenger.isCheckOtherInfo()){
						System.out.println(passenger.isCheckOtherInfo());
						setSignsAvailabilityPerception(passenger);
						setSignsAvailabilityAffective(passenger);
						setPersonalSafetyPerception(passenger);
						setPersonalSafetyAffective(passenger);
							if(!(getComfortStatus())){
								System.out.println("I will set other attitude");
								setOthersAttitudePerception(passenger);
								setOthersAttitudeAffective(passenger);
							}
					}
				}
			}
			if(getMobilityStatus()){			
				for(Passenger passenger:selectedCyclists){
					if(passenger.isCheckOtherInfo()){
						System.out.println(passenger.isCheckOtherInfo());
						if(!(getJourneyTimeStatus())){
						setGettingToDestOnTimePerception(passenger);
						setGettingToDestOnTimeAffective(passenger);
						}
					}
				}
			}
		//	affectedCarUsers();
		}
	@ScheduledMethod(start =70, interval=1)
		public void applyPrivateUsersIntervention(){
			getPrivateUsers();
			if(getReliabilityStatus()){				
				for(Passenger passenger: selectedCarUsers){
					if(passenger.isCheckOtherInfo()){
					setEaseOfAccessInfoPerception(passenger);
					setEaseOfAccessInfoAffective(passenger);
					setInfoReliabilityPerception(passenger);
					setInfoReliabilityAffective(passenger);
					}
				}
			}
			if(getJourneyTimeStatus()){			
				for(Passenger passenger: selectedCarUsers){
					if(passenger.isCheckOtherInfo()){
					setGettingToDestOnTimePerception(passenger);
					setGettingToDestOnTimeAffective(passenger);
					setDelaysPerception(passenger);
					setDelaysAffective(passenger);
					}
				}
			}
			if(getComfortStatus()){				
				for(Passenger passenger: selectedCarUsers){
					if(passenger.isCheckOtherInfo()){
					setOthersAttitudePerception(passenger);
					setOthersAttitudeAffective(passenger);
					}
				}
			}
			if(getConvenienceStatus()){				
				for(Passenger passenger: selectedCarUsers){
					if(passenger.isCheckOtherInfo()){
					setWalkingToDestinationPerception(passenger);
					setWalkingToDestinationAffective(passenger);
					}
				}
			}
			if(getValueForMoneyStatus()){				
				for(Passenger passenger:selectedCarUsers){
					if(passenger.isCheckOtherInfo()){
					setParkingSpaceConcernPerception(passenger);
					setParkingSpaceConcernAffective(passenger);
					}
				}
			}			
		}
	//@ScheduledMethod(start =70, interval=1)
		public void applyPedestrianIntervention(){	
		getWalkers();		
			if(getReliabilityStatus()){				
				for(Passenger passenger: selectedPedestrians){	
					if(passenger.isCheckOtherInfo()){
					setEaseOfAccessInfoPerception(passenger);
					setEaseOfAccessInfoAffective(passenger);
					setInfoReliabilityPerception(passenger);
					setInfoReliabilityAffective(passenger);
					}
				}
			}
			if(getJourneyTimeStatus()){				
				for(Passenger passenger:selectedPedestrians){
					if(passenger.isCheckOtherInfo()){
					setGettingToDestOnTimePerception(passenger);
					setGettingToDestOnTimeAffective(passenger);
					setDelaysPerception(passenger);
					setDelaysAffective(passenger);
					}
				}
			}
			if(getComfortStatus()){				
				for(Passenger passenger:selectedPedestrians){
					if(passenger.isCheckOtherInfo()){
					setProtectionFromElementsPerception(passenger);
					setProtectionFromElementsAffective(passenger);
					}
				}
			}
			if(getSecurityStatus()){			
				for(Passenger passenger: selectedPedestrians){
					if(passenger.isCheckOtherInfo()){
					setSignsAvailabilityPerception(passenger);
					setSignsAvailabilityAffective(passenger);				
					setOthersAttitudePerception(passenger);
					setOthersAttitudeAffective(passenger);
					}
				}
			}
			if(getMobilityStatus()){			
				for(Passenger passenger: selectedPedestrians){
					if(!(getSecurityStatus())){
						if(passenger.isCheckOtherInfo()){
						setSignsAvailabilityPerception(passenger);
						setSignsAvailabilityAffective(passenger);
						}
					}					
				}
			}	
		//	affectedCarUsers();
		}
	//@ScheduledMethod(start =70, interval=1)
		public void applyPublicTransIntervention(){
			getPublicTransUsers();			
			if(getReliabilityStatus()){				
				for(Passenger passenger: selectedPublicTransUsers){
					if(passenger.isCheckOtherInfo()){
					setEaseOfAccessInfoPerception(passenger);
					setEaseOfAccessInfoAffective(passenger);
					setInfoReliabilityPerception(passenger);
					setInfoReliabilityAffective(passenger);
					}
				}
			}
			if(getJourneyTimeStatus()){				
				for(Passenger passenger: selectedPublicTransUsers){
					if(passenger.isCheckOtherInfo()){
					setGettingToDestOnTimePerception(passenger);
					setGettingToDestOnTimeAffective(passenger);
					setDelaysPerception(passenger);
					setDelaysAffective(passenger);
					setModeFrequencyPerception(passenger);
					setModeFrequencyAffective(passenger);
					}
				}
			}
			if(getComfortStatus()){				
				for(Passenger passenger: selectedPublicTransUsers){
					if(passenger.isCheckOtherInfo()){
					setProtectionFromElementsPerception(passenger);
					setProtectionFromElementsAffective(passenger);
					setGettingOnOffModePerception(passenger);
					setGettingOnOffModeAffective(passenger);
					setGettingOnOffModePhysical(passenger);
					setOthersAttitudePerception(passenger);
					setOthersAttitudeAffective(passenger);
					}
				}
			}
			if(getConvenienceStatus()){				
				for(Passenger passenger: selectedPublicTransUsers){
					if(passenger.isCheckOtherInfo()){
					setEaseOfGettingToMainModePerception(passenger);
					setEaseOfGettingToMainModeAffective(passenger);
					setWalkingToDestinationPerception(passenger);
					setWalkingToDestinationAffective(passenger);
						if (!(getJourneyTimeStatus())){
						setGettingToDestOnTimePerception(passenger);
						setGettingToDestOnTimeAffective(passenger);
						}
					}					
				}
			}	
		//	affectedCarUsers();			
		}
///
	 	private void affectedCarUsers(){
	 		getPrivateUsers();					
				 if(getReliabilityStatus()){				
						for(Passenger passenger: selectedCarUsers){
							setEaseOfAccessInfoPerception(passenger);
							setEaseOfAccessInfoAffective(passenger);
							setInfoReliabilityPerception(passenger);
							setInfoReliabilityAffective(passenger);
						}
					}
					if(getJourneyTimeStatus()){			
						for(Passenger passenger: selectedCarUsers){
							setGettingToDestOnTimePerception(passenger);
							setGettingToDestOnTimeAffective(passenger);
							setDelaysPerception(passenger);
							setDelaysAffective(passenger);
						}
					}
					if(getComfortStatus()){				
						for(Passenger passenger: selectedCarUsers){
							setOthersAttitudePerception(passenger);
							setOthersAttitudeAffective(passenger);
						}
					}
					if(getConvenienceStatus()){				
						for(Passenger passenger: selectedCarUsers){
							setWalkingToDestinationPerception(passenger);
							setWalkingToDestinationAffective(passenger);
						}
					}
					if(getValueForMoneyStatus()){				
						for(Passenger passenger:selectedCarUsers){
							setParkingSpaceConcernPerception(passenger);
							setParkingSpaceConcernAffective(passenger);
						}
					}		
	 	}
///
	private void setGettingToDestOnTimePerception(Passenger passenger){							
		passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));		
	}
	private void setGettingToDestOnTimeAffective(Passenger passenger){		
		passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));					
	}
	private void setEaseOfAccessInfoPerception(Passenger passenger){			
		passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfAccessInfoPerception,evaluateEaseOfAccessInfo(passenger));					
	}	
	private void setEaseOfAccessInfoAffective(Passenger passenger){			
		passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfAccessInfoAffective,evaluateEaseOfAccessInfoAffective(passenger));
	}
	private void setInfoReliabilityPerception(Passenger passenger){				
		passenger.getPrefferedMode().setValueToAttribute(Constants.infoReliabilityPerception,evaluateInfoReliability(passenger));			
	}
	private void setInfoReliabilityAffective(Passenger passenger){
			
		passenger.getPrefferedMode().setValueToAttribute(Constants.infoReliabilityAffective,evaluateInfoReliabilityAffective(passenger));
	}
	private void setDelaysPerception(Passenger passenger){	
				
		passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));					
	}
	private void setDelaysAffective(Passenger passenger){			
		passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));					
	}
	private void setOthersAttitudePerception(Passenger passenger){		
		passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudePerception,evaluateOthersAttitude(passenger));
	}
	private void setOthersAttitudeAffective(Passenger passenger){		
		passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudeAffective,evaluateOthersAttitudeAffective(passenger));
	}
	private void setParkingSpaceConcernPerception(Passenger passenger){		
		passenger.getPrefferedMode().setValueToAttribute(Constants.parkingSpaceConcernPerception,evaluateParkingConcerns(passenger));
	}
	private void setParkingSpaceConcernPhysical(Passenger passenger){				
		passenger.getPrefferedMode().setValueToAttribute(Constants.parkingSpaceConcernPerception,evaluateParkingConcerns(passenger));
	}
	private void setParkingSpaceConcernAffective(Passenger passenger){		
		passenger.getPrefferedMode().setValueToAttribute(Constants.parkingSpaceConcernAffective,evaluateParkingConcernsAffective(passenger));
	}
	private void setSignsAvailabilityPerception(Passenger passenger){			
		passenger.getPrefferedMode().setValueToAttribute(Constants.signsAvailabilityPerception,evaluateSignsAvailablity(passenger));
	}
	private void setSignsAvailabilityAffective(Passenger passenger){			
		passenger.getPrefferedMode().setValueToAttribute(Constants.signsAvailabilityAffective,evaluateSignsAvailablityAffective(passenger));
	}
	private void setPersonalSafetyPerception(Passenger passenger){
		passenger.getPrefferedMode().setValueToAttribute(Constants.personalSafetyPerception,evaluateSafety(passenger));
	}
	private void setPersonalSafetyAffective(Passenger passenger){		
		passenger.getPrefferedMode().setValueToAttribute(Constants.personalSafetyAffective,evaluateSafetyAffective(passenger));
	}
	private void setWalkingToDestinationPerception(Passenger passenger){			
		passenger.getPrefferedMode().setValueToAttribute(Constants.walkingToDestinationPerception,evaluateWalkingToDest(passenger));
	}
	private void setWalkingToDestinationAffective(Passenger passenger){				
		passenger.getPrefferedMode().setValueToAttribute(Constants.walkingToDestinationAffective,evaluateWalkingToDestAffective(passenger));
	}
	private void setModeFrequencyPerception(Passenger passenger){				
		passenger.getPrefferedMode().setValueToAttribute(Constants.modeFrequencyPerception,evaluateFrequency(passenger));		
	}
	private void setModeFrequencyAffective(Passenger passenger){						
		passenger.getPrefferedMode().setValueToAttribute(Constants.modeFrequencyAffective,evaluateFrequencyAffective(passenger));
	}
	private void setProtectionFromElementsPerception(Passenger passenger){						
		passenger.getPrefferedMode().setValueToAttribute(Constants.protectionFromElementsPerception,evaluateProtectionFromElements(passenger));
	}
	private void setProtectionFromElementsAffective(Passenger passenger){				
				passenger.getPrefferedMode().setValueToAttribute(Constants.protectionFromElementsAffective,evaluateProtectionFromElementsAffective(passenger));
	}
	private void setGettingOnOffModePerception(Passenger passenger){		
		passenger.getPrefferedMode().setValueToAttribute(Constants.gettingOnOffModePerception,evaluateGettingOnOff(passenger));
	}
	private void setGettingOnOffModeAffective(Passenger passenger){					
		passenger.getPrefferedMode().setValueToAttribute(Constants.gettingOnOffModeAffective,evaluateGettingOnOffAffective(passenger));		
	}
	private void setGettingOnOffModePhysical(Passenger passenger){					
		passenger.getPrefferedMode().setValueToAttribute(Constants.gettingOnOffModePhysical,evaluateGettingOnOffPhysical(passenger));				
	}
	private void setEaseOfGettingToMainModePerception(Passenger passenger){				
		passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfGettingToMainModePerception,evaluateGettingToMainMode(passenger));		
	}
	private void setEaseOfGettingToMainModeAffective(Passenger passenger){				
		passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfGettingToMainModeAffective,evaluateGettingToMainModeAffective(passenger));		
	}

/////////////////////////////////////////

	@Parameter(displayName="Reliability", usageName="Reliability")
	public double getReliability() {	
		return reliability;
	}	
	public void setReliability(double reliability){
		this.reliability = reliability;
	}
////	
	@Parameter(displayName="JourneyTime", usageName="JourneyTime")
	public double getJourneyTime() {	
		return journeyTime;
	}	
	public void setJourneyTime(double journeyTime){
		this.journeyTime = journeyTime;
	}
////
	@Parameter(displayName="Value For Money", usageName="Value For Money")
	public double getValueForMoney() {
		return valueForMoney;
	}
	public void setValueForMoney(double newValueForMoney) {
		this.valueForMoney = newValueForMoney;
	}
///
	@Parameter(displayName=" Security", usageName="Security")
	public double getSecurity() {
		return security;
	}
	public void setSecurity(double newSecurity) {
		this.security = newSecurity;
	}
///
	@Parameter(displayName=" Mobility", usageName="Mobility")
	public double getPersonalMobility() {
		return mobility;
	}
	public void setMobility(double newMobility) {
		this.mobility = newMobility;
	}
///	
	@Parameter(displayName="Convenience", usageName="Convenience")
	public double getConvenience() {
		return convenience;
	}
	public void setConvenience(double newConvenience) {
		this.convenience = newConvenience;
	}
///	
	@Parameter(displayName="Comfort", usageName="Comfort")
	public double getComfort() {
		return comfort;
	}
	public void setComfort(double newComfort) {
		this.comfort = newComfort;
	}
//////
	public double evaluateEaseOfAccessInfo(Passenger p) {
		double easeOfAccessInfo=0;			
		double initialIntervention = getReliability()/10;
			if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
				//	easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
				//	easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception)+0.0);		
				} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
					easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
				}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
					easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
				}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
					easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			}	
			System.out.println("The"+ p.getPrefferedMode() +" real easo fo ifo is"+p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception)
					+"The new ease of info is is " + easeOfAccessInfo + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.0, initialIntervention));
		return easeOfAccessInfo;
	}	
	public double evaluateEaseOfAccessInfoAffective(Passenger p) {		
		double easeOfAccessInfoAffect=0;			
		double initialIntervention = getReliability()/10;
			if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
				//	easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
				//	easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+0.0);		
				} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
					easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
				}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
					easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
				}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
					easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			}		
			System.out.println("The"+ p.getPrefferedMode() +" ease of info affective is"+p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)
					+"The new ease of info affective  is " + easeOfAccessInfoAffect + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.0, initialIntervention));
		return easeOfAccessInfoAffect;
	}	
////

	public double evaluateInfoReliability(Passenger p) {
		double InfoReliability=0;			
		double initialIntervention = getReliability()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			//InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			//	InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+0.0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}		
		System.out.println("The"+ p.getPrefferedMode() +" my real reliablity is"+p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)
				+"The new relaibilty is " + InfoReliability + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return InfoReliability;
	}
	public double evaluateInfoReliabilityAffective(Passenger p) {
		double InfoReliabilityAffect=0;		
		double initialIntervention = getReliability()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			//InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			//	InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+0.0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		System.out.println("The"+ p.getPrefferedMode() +"my real rel Affective is"+p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)
				+"The new relaibilty Affect is " + InfoReliabilityAffect + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return InfoReliabilityAffect;
	}
//	
	public double evaluateTimeliness(Passenger p) {
		double timeliness=0;		
		double initialIntervention = getJourneyTime()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){	
		//	timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
	//		timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		System.out.println("The "+ p.getPrefferedMode()+ "real timeliness is"+p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)
				+"The new timeliness is " + timeliness + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return timeliness;
	}
	public double evaluateTimelinessAffective(Passenger p) {
		double timelinessAffective=0;		
		double initialIntervention = getJourneyTime()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
		//	timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			//	timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		System.out.println("The "+ p.getPrefferedMode()+ " timeliness Affective is"+p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)
			+"The new timeliness Affect is " + timelinessAffective + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return timelinessAffective;
	}
////	
	public double evaluateDelays(Passenger p) {
		double delays=0;		
		double initialIntervention = getJourneyTime()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
		//	delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			//	delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		System.out.println(" The"+ p.getPrefferedMode()+ " real delays value is "+p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)
				+"The new delays value is " + delays + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return delays;
	}
	public double evaluateDelaysAffective(Passenger p) {
		double delaysAffective=0;		
		double initialIntervention = getJourneyTime()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			//delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			//	delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		System.out.println("The"+ p.getPrefferedMode()+ "real delays Affective value is "+p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)
				+"The new delays value is " + delaysAffective + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return delaysAffective;
	}
////
	public double evaluateFrequency(Passenger p) {
		double frequency=0;			
		double initialIntervention =getJourneyTime()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
		//	frequency = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			frequency = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			frequency = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			frequency = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return frequency;
	}
	public double evaluateFrequencyAffective(Passenger p) {
		double frequencyAffective=0;			
		double initialIntervention =getJourneyTime()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
		//	frequencyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			frequencyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			frequencyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			frequencyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return frequencyAffective;
	}
/////	
	public double evaluateGettingOnOff(Passenger p) {
		double gettingOnOff=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
	//		gettingOnOff = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePerception)-RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			gettingOnOff = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			gettingOnOff = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			gettingOnOff = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return gettingOnOff;
	}
	public double evaluateGettingOnOffAffective(Passenger p) {
		double gettingOnOffAffective=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){		
		//	gettingOnOffAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModeAffective)-RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			gettingOnOffAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			gettingOnOffAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			gettingOnOffAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return gettingOnOffAffective;
	}
	public double evaluateGettingOnOffPhysical(Passenger p) {
		double gettingOnOffPhysical=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
		//	gettingOnOffPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePhysical)-RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			gettingOnOffPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePhysical)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			gettingOnOffPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePhysical)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			gettingOnOffPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePhysical)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return gettingOnOffPhysical;
	}
/////
	public double evaluateSignsAvailablity(Passenger p) {
		double roadSigns=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
	//		roadSigns = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityPerception)+0.0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			roadSigns = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			roadSigns = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			roadSigns = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return roadSigns;
	}
	public double evaluateSignsAvailablityAffective(Passenger p) {
		double roadSignsAffective=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){		
	//		roadSignsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityAffective)+0.0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			roadSignsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			roadSignsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			roadSignsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		return roadSignsAffective;
	}
/////
	public double evaluateOthersAttitude(Passenger p) {
		double othersAttitude=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			//othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			//	othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){		
			othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return othersAttitude;
	}
	public double evaluateOthersAttitudeAffective(Passenger p) {
		double othersAttitudeAffective=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			//othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			//	othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return othersAttitudeAffective;
	}
//////
	public double evaluateParkingConcerns(Passenger p) {
		double parkingConcern=0;			
		double initialIntervention =getValueForMoney()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			//parkingConcern = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			parkingConcern = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernPerception)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			parkingConcern = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return parkingConcern;
	}
	public double evaluateParkingConcernsAffective(Passenger p) {
		double parkingConcernAffective=0;			
		double initialIntervention =getValueForMoney()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){		
			//parkingConcernAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			parkingConcernAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernAffective)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			parkingConcernAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return parkingConcernAffective;
	}
	public double evaluateParkingConcernsPhysical(Passenger p) {
		double parkingConcernPhysical=0;			
		double initialIntervention =getValueForMoney()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
			//parkingConcernPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			parkingConcernPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			parkingConcernPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}
		return parkingConcernPhysical;
	}
/////
	public double evaluateProtectionFromElements(Passenger p) {
		double protectionFromElements=0;			
		double initialIntervention =getComfort()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
	//		protectionFromElements = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)+0.0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			protectionFromElements = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			protectionFromElements = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			protectionFromElements = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		return protectionFromElements;
	}
	public double evaluateProtectionFromElementsAffective(Passenger p) {
		double protectionFromElementsAffective=0;			
		double initialIntervention =getComfort()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
	//		protectionFromElementsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)+ 0.0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			protectionFromElementsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			protectionFromElementsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			protectionFromElementsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		return protectionFromElementsAffective;
	}
///////
	public double evaluateSecurity(Passenger p) {
		double security=0;			
		double initialIntervention =getComfort()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
	//		security = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityPerception)+0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			security = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			security = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			security = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		return security;
	}
	public double evaluateSecurityAffective(Passenger p) {
		double securityAffective=0;			
		double initialIntervention =getComfort()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){		
	//		securityAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityAffective)+0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			securityAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){		
			securityAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			securityAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return securityAffective;
	}
///////
	public double evaluateSafety(Passenger p) {
		double safety=0;			
		double initialIntervention =getComfort()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){			
	//		safety = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyPerception)+0.0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			safety = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			safety = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			safety = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		return safety;
	}
	public double evaluateSafetyAffective(Passenger p) {
		double safetyAffective=0;			
		double initialIntervention =getComfort()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){		
	//		safetyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyAffective)+0.0);		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			safetyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			safetyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			safetyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		return safetyAffective;
	}
/////
	public double evaluateWalkingToDest(Passenger p) {
		double walkingToDest=0;			
		double initialIntervention =getPersonalMobility()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){	
			//walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
					walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		return walkingToDest;
	}
	public double evaluateWalkingToDestAffective(Passenger p) {
		double walkingToDestAffective=0;			
		double initialIntervention =getPersonalMobility()/10;
		if((p.getPrefferedMode() instanceof PersonalVehicle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
		//	walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)-RandomHelper.nextDoubleFromTo(0.08, initialIntervention));		
		} else if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Cycle)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){					
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if((p.getPrefferedMode() instanceof Walking)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return walkingToDestAffective;
	}
	
	public double evaluateGettingToMainMode(Passenger p) {
		double walkingToDest=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){		
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfGettingToMainModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return walkingToDest;
	}
	
	public double evaluateGettingToMainModeAffective(Passenger p) {
		double walkingToDestAffective=0;			
		double initialIntervention =getConvenience()/10;
		if((p.getPrefferedMode() instanceof PublicTransport)){//&&(p.getBehaviour()==DecisionStrategy.OPTIMISE)){				
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfGettingToMainModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return walkingToDestAffective;
	}

/////
	public void reset(){		
		this.reliabilityStatus=false;
		this.journeyTimeStatus=false;
		this.convenienceStatus=false;
		this.valueForMoneyStatus=false;
		this.comfortStatus=false;
		this.mobilityStatus=false;
		this.securityStatus=false;
	}
}