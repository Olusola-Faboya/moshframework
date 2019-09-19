package moshproject.agents.intervener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class CopyOfIntervener {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	Random rand = new Random();
	Passenger passenger;	
	InterventionPanel interventionPanel;
	List<Passenger> passengers;	
	
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
	
	public CopyOfIntervener(ContinuousSpace<Object> space, Grid<Object> grid) {	
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
		
//		setGettingToDestOnTimePerception();
//		setGettingToDestOnTimeAffective();
//		setDelaysPerception();
//		setDelaysAffective();
	}	
	public void initialize() {
		// TODO Auto-generated method stub		
	}		
		
	ModeAttributesPerceptions intervenerPerception;
	private double gettingToDestinationOnTimePerception;
	private double gettingToDestinationOnTimeAffective;
	private double delaysPerception;
	private double delaysAffective;
	public void setPassenger(Passenger pass){
		this.passenger = pass;
		this.passenger.setMyPerception(pass.getMyPerception());
	}
	
	public List<Passenger> getAllPassengers() {
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		passengers = new ArrayList<Passenger>();
			for(Passenger passes: passenger){			
				//	while (passes.getModeSatisfaction()<0.5){
					passengers.add(passes);
		//	}
		}
		return passengers;
	}
////Gather Cyclists	
	public List<Passenger> getCyclists() {
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		passengers = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if((passes.passengerType instanceof Cyclist)){//&&(passes.getModeSatisfaction()<=0.6)){
				passengers.add(passes);
			}
		}
		return passengers;
	}
////Gather Public Transport USers
	public List<Passenger> getPublicTransUsers() {
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		passengers = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if((passes.passengerType instanceof PublicTransportUser)&&(passes.getModeSatisfaction()<=0.5)){
				passengers.add(passes);
			}
		}
		return passengers;
	}
////Gather Walkers
	public List<Passenger> getWalkers(){
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		passengers = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if((passes.passengerType instanceof Pedestrian)&&(passes.getModeSatisfaction()<=0.5)){
			passengers.add(passes);
				}
			}
		return passengers;
	}
////Gather PrivateUsers
	public List<Passenger> getPrivateUsers(){
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		passengers = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if((passes.passengerType instanceof PersonalVehicleUser)&&(passes.getModeSatisfaction()<=0.4)){
			passengers.add(passes);
				}
			}
		return passengers;
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
	private double setGettingToDestOnTimePerception(){
		getAllPassengers();
		for(Passenger passenger: passengers){
		if(passenger.getPrefferedMode()instanceof Cycle){
			getCyclists();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
						System.out.println("Check value"+passenger.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof PersonalVehicle){
			getPrivateUsers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof PublicTransport){
			getPublicTransUsers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof Walking){
			getWalkers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
					}
			}
		}
	}
		return gettingToDestinationOnTimePerception;
	}
	public double getGettingToDestinationOnTimePerception() {
		return gettingToDestinationOnTimePerception;
	}
	
	private double setGettingToDestOnTimeAffective(){
		getAllPassengers();
		for(Passenger passenger: passengers){
		if(passenger.getPrefferedMode()instanceof Cycle){
			getCyclists();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof PersonalVehicle){
			getPrivateUsers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof PublicTransport){
			getPublicTransUsers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof Walking){
			getWalkers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
					}
			}
		}
	}
		return gettingToDestinationOnTimeAffective;					
	}	
	public double getGettingToDestinationOnTimeAffective() {
		return gettingToDestinationOnTimeAffective;
	}
	
	private double setDelaysPerception(){
		getAllPassengers();
		for(Passenger passenger: passengers){
		if(passenger.getPrefferedMode()instanceof Cycle){
			getCyclists();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof PersonalVehicle){
			getPrivateUsers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof PublicTransport){
			getPublicTransUsers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof Walking){
			getWalkers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));
					}
			}
		}
	}
		return delaysPerception;
	}		
	public double getDelaysPerception() {
		return delaysPerception;
	}
	private double setDelaysAffective(){
		getAllPassengers();
		for(Passenger passenger: passengers){
		if(passenger.getPrefferedMode()instanceof Cycle){
			getCyclists();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof PersonalVehicle){
			getPrivateUsers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof PublicTransport){
			getPublicTransUsers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));
					}
			}
		}else if(passenger.getPrefferedMode() instanceof Walking){
			getWalkers();
			if (passengers.size()>0){
				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
					for (int i=0; i<=numberOfElements;i++){
						int randomIndex =rand.nextInt(passengers.size());
						passenger =passengers.get(randomIndex);
						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));
					}
			}
		}
		}
		return delaysAffective;					
	}	
	public double getDelaysAffective() {
		return delaysAffective;
	}
	/////////////////////////////////////
//	@ScheduledMethod(start =1, interval=1)	
//	public void applyPrivateUsersIntervention(){		
//		rand =new Random();
//		getPrivateUsers();
//		if (getReliabilityStatus()){		
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.095);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger  =passengers.get(randomIndex);					
//						passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfAccessInfoPerception,evaluateEaseOfAccessInfo(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.infoReliabilityPerception,evaluateInfoReliability(passenger));	
//						///
//						passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfAccessInfoAffective,evaluateEaseOfAccessInfoAffective(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.infoReliabilityAffective,evaluateInfoReliabilityAffective(passenger));					
//					}
//				
//			}			
//		}
//		if (getJourneyTimeStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.333);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);											 
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));
//					////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));						
//					}
//			}			
//		}
//		if (getConvenienceStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.333);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.walkingToDestinationPerception,evaluateWalkingToDest(passenger));
//			////////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.walkingToDestinationAffective,evaluateWalkingToDestAffective(passenger));				
//					}
//			}			
//		}
//		if (getValueForMoneyStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.19);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.parkingSpaceConcernPerception,evaluateParkingConcerns(passenger));	
//				///////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.parkingSpaceConcernAffective,evaluateParkingConcernsAffective(passenger));												
//					}
//			}			
//		}
//		if (getComfortStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.047);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudePerception,evaluateOthersAttitude(passenger));	
//				//////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudeAffective,evaluateOthersAttitudeAffective(passenger));						
//					}
//			}			
//		}
//	}
///////////////////////////////////////////////////
////	@ScheduledMethod(start =1, interval=1)
//	public void applyPedestrianIntervention(){		
//		rand =new Random();	
//		getWalkers();
//		if (getReliabilityStatus()){		
//				System.out.println("Reliabillity box is checked");				
//					if (passengers.size()>0){
//						int numberOfElements = (int) (((int) passengers.size())*0.023);
//							for (int i=0; i<=numberOfElements;i++){
//								int randomIndex =rand.nextInt(passengers.size());
//								 passenger  =passengers.get(randomIndex);								
//								 passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfAccessInfoPerception,evaluateEaseOfAccessInfo(passenger));
//								 passenger.getPrefferedMode().setValueToAttribute(Constants.infoReliabilityPerception,evaluateInfoReliability(passenger));
//								///
//								passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfAccessInfoAffective,evaluateEaseOfAccessInfoAffective(passenger));
//								passenger.getPrefferedMode().setValueToAttribute(Constants.infoReliabilityAffective,evaluateInfoReliabilityAffective(passenger));						
//							}
//					}	
//		}				
//		if(getJourneyTimeStatus()){
//			System.out.println("Journey Time box is checked");					
//						if (passengers.size()>0){
//							int numberOfElements = (int) (((int) passengers.size())*0.064);
//								for (int i=0; i<=numberOfElements;i++){
//									int randomIndex =rand.nextInt(passengers.size());
//									passenger  =passengers.get(randomIndex);	
//									 passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
//									 passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));
//									////
//									passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
//									passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));		
//								}
//						}			
//		}
//		if (getComfortStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.68);
//					for (int i=0; i<=numberOfElements;i++){
//							int randomIndex =rand.nextInt(passengers.size());
//							passenger =passengers.get(randomIndex);
//							passenger.getPrefferedMode().setValueToAttribute(Constants.protectionFromElementsPerception,evaluateProtectionFromElements(passenger));
//					///////
//							passenger.getPrefferedMode().setValueToAttribute(Constants.protectionFromElementsAffective,evaluateProtectionFromElementsAffective(passenger));											
//					}
//			}			
//		}		
//		if (getSecurityStatus()){
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.205);
//					for (int i=0; i<=numberOfElements;i++){
//							int randomIndex =rand.nextInt(passengers.size());
//							passenger =passengers.get(randomIndex);
//							passenger.getPrefferedMode().setValueToAttribute(Constants.signsAvailabilityPerception,evaluateSignsAvailablity(passenger));
//							passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudePerception,evaluateOthersAttitude(passenger));
//					////////
//							passenger.getPrefferedMode().setValueToAttribute(Constants.signsAvailabilityAffective,evaluateSignsAvailablityAffective(passenger));
//							passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudeAffective,evaluateOthersAttitudeAffective(passenger));							
//					}
//			}
//		}
//		if (getMobilityStatus()){
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.0232);
//					for (int i=0; i<=numberOfElements;i++){
//							int randomIndex =rand.nextInt(passengers.size());
//							passenger =passengers.get(randomIndex);
//							passenger.getPrefferedMode().setValueToAttribute(Constants.signsAvailabilityPerception,evaluateSignsAvailablity(passenger));
//					/////
//							passenger.getPrefferedMode().setValueToAttribute(Constants.signsAvailabilityAffective,evaluateSignsAvailablityAffective(passenger));
//					}
//			}
//		}
//	}
/////////////
////	@ScheduledMethod(start =1, interval=1)	
//	public void applyPublicTransIntervention(){		
//		rand =new Random();
//		getPublicTransUsers();
//		if (getReliabilityStatus()){		
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.127);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger  =passengers.get(randomIndex);					
//						passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfAccessInfoPerception,evaluateEaseOfAccessInfo(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.infoReliabilityPerception,evaluateInfoReliability(passenger));	
//						///
//						passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfAccessInfoAffective,evaluateEaseOfAccessInfoAffective(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.infoReliabilityAffective,evaluateInfoReliabilityAffective(passenger));						
//					}
//			}			
//		}
//		if (getJourneyTimeStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.527);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.modeFrequencyPerception,evaluateFrequency(passenger));
//				///////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.modeFrequencyAffective,evaluateFrequencyAffective(passenger));
//					}
//			}			
//		}
//		if (getConvenienceStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.218);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfGettingToMainModePerception,evaluateGettingToMainMode(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));								 
//						passenger.getPrefferedMode().setValueToAttribute(Constants.walkingToDestinationPerception,evaluateWalkingToDest(passenger));
//				///////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.easeOfGettingToMainModeAffective,evaluateGettingToMainModeAffective(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));								 
//						passenger.getPrefferedMode().setValueToAttribute(Constants.walkingToDestinationAffective,evaluateWalkingToDestAffective(passenger));
//					}
//			}			
//		}
//		if (getComfortStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size())*0.127);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.protectionFromElementsPerception,evaluateProtectionFromElements(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingOnOffModePerception,evaluateGettingOnOff(passenger));								 
//						passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudePerception,evaluateOthersAttitude(passenger));
//				////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.protectionFromElementsAffective,evaluateProtectionFromElementsAffective(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingOnOffModeAffective,evaluateGettingOnOffAffective(passenger));								 
//						passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudeAffective,evaluateOthersAttitudeAffective(passenger));						
//					}
//			}			
//		}
//	}
//////
	//@ScheduledMethod(start =50, interval=1)	
	public void applyCyclistIntervention(){
		if(getJourneyTimeStatus()){
			setGettingToDestOnTimePerception();
			setGettingToDestOnTimeAffective();
			setDelaysPerception();
			setDelaysAffective();
		}
	
//		rand =new Random();
//		getCyclists();
//		if (getJourneyTimeStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size()));//*0.15);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysPerception,evaluateDelays(passenger));
//					//	
//						passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
//						passenger.getPrefferedMode().setValueToAttribute(Constants.delaysAffective,evaluateDelaysAffective(passenger));
//					}
//			}
////			for (int i=0; i<= passengers.size(); i++){
////				int randomIndex =rand.nextInt(passengers.size());
////			passenger =passengers.get(randomIndex);
////			System.out.println("Timeliness perception is "+ passenger.prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimePerception));
////			System.out.println("delays perception is "+ passenger.prefferedMode.getValueOfAttribute(Constants.delaysPerception));
////			
////			System.out.println("Timeliness affective is "+ passenger.prefferedMode.getValueOfAttribute(Constants.gettingToDestOnTimeAffective));
////			System.out.println("delays affective is "+ passenger.prefferedMode.getValueOfAttribute(Constants.delaysAffective));
////			}
//		}
//		if (getComfortStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size()));//*0.02);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.protectionFromElementsPerception,evaluateProtectionFromElements(passenger));
//				///////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.protectionFromElementsAffective,evaluateProtectionFromElementsAffective(passenger));
//					}
//			}			
//		}
//		if (getConvenienceStatus()){			
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size()));//*0.09);
//					for (int i=0; i<=numberOfElements;i++){
//						int randomIndex =rand.nextInt(passengers.size());
//						passenger =passengers.get(randomIndex);
//						passenger.getPrefferedMode().setValueToAttribute(Constants.parkingSpaceConcernPerception,evaluateParkingConcerns(passenger));
//				///////
//						passenger.getPrefferedMode().setValueToAttribute(Constants.parkingSpaceConcernAffective,evaluateParkingConcernsAffective(passenger));
//					}
//			}			
//		}
//		if (getSecurityStatus()){
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size()));//*0.71);
//					for (int i=0; i<=numberOfElements;i++){
//							int randomIndex =rand.nextInt(passengers.size());
//							passenger =passengers.get(randomIndex);
//							passenger.getPrefferedMode().setValueToAttribute(Constants.signsAvailabilityPerception,evaluateSignsAvailablity(passenger));							
//							passenger.getPrefferedMode().setValueToAttribute(Constants.personalSafetyPerception,evaluateSafety(passenger));
//							passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudePerception,evaluateOthersAttitude(passenger));	
//					////////
//							passenger.getPrefferedMode().setValueToAttribute(Constants.signsAvailabilityAffective,evaluateSignsAvailablityAffective(passenger));							
//							passenger.getPrefferedMode().setValueToAttribute(Constants.personalSafetyAffective,evaluateSafetyAffective(passenger));
//							passenger.getPrefferedMode().setValueToAttribute(Constants.othersAttitudeAffective,evaluateOthersAttitudeAffective(passenger));						
//					}
//			}
//		}
//		if (getMobilityStatus()){
//			if (passengers.size()>0){
//				int numberOfElements = (int) (((int) passengers.size()));//*0.09);
//					for (int i=0; i<=numberOfElements;i++){
//							int randomIndex =rand.nextInt(passengers.size());
//							passenger =passengers.get(randomIndex);
//							passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimePerception,evaluateTimeliness(passenger));
//							//	
//							passenger.getPrefferedMode().setValueToAttribute(Constants.gettingToDestOnTimeAffective,evaluateTimelinessAffective(passenger));
//					}
//			}
//		}
//		
	}
////
	
////
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
			if(p.getPrefferedMode() instanceof PersonalVehicle){			
					easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception+ RandomHelper.nextDoubleFromTo(0.0, initialIntervention)));		
				} else if(p.getPrefferedMode() instanceof PublicTransport){				
					easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception+RandomHelper.nextDoubleFromTo(0.0, initialIntervention)));	
				}else if(p.getPrefferedMode() instanceof Cycle){				
					easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception+RandomHelper.nextDoubleFromTo(0.0, initialIntervention)));	
				}else if(p.getPrefferedMode() instanceof Walking){				
					easeOfAccessInfo = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception+RandomHelper.nextDoubleFromTo(0.0, initialIntervention)));
			}	
	//		System.out.println("my real rel is"+p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoPerception)
	//				+"The new relaibilty is " + easeOfAccessInfo + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.0, initialIntervention));
		return easeOfAccessInfo;
	}	
	public double evaluateEaseOfAccessInfoAffective(Passenger p) {		
		double easeOfAccessInfoAffect=0;			
		double initialIntervention = getReliability()/10;
			if(p.getPrefferedMode() instanceof PersonalVehicle){			
					easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+RandomHelper.nextDoubleFromTo(0.0, initialIntervention));		
				} else if(p.getPrefferedMode() instanceof PublicTransport){				
					easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+RandomHelper.nextDoubleFromTo(0.0, initialIntervention));	
				}else if(p.getPrefferedMode() instanceof Cycle){				
					easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+RandomHelper.nextDoubleFromTo(0.0, initialIntervention));	
				}else if(p.getPrefferedMode() instanceof Walking){				
					easeOfAccessInfoAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)+RandomHelper.nextDoubleFromTo(0.0, initialIntervention));
			}		
	//		System.out.println("my real rel is"+p.getPrefferedMode().getValueOfAttribute(Constants.easeOfAccessInfoAffective)
	//				+"The new relaibilty is " + easeOfAccessInfoAffect + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.0, initialIntervention));
		return easeOfAccessInfoAffect;
	}	
////

	public double evaluateInfoReliability(Passenger p) {
		double InfoReliability=0;			
		double initialIntervention = getReliability()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			InfoReliability = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}		
		System.out.println("my real rel is"+p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityPerception)
				+"The new relaibilty is " + InfoReliability + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return InfoReliability;
	}
	public double evaluateInfoReliabilityAffective(Passenger p) {
		double InfoReliabilityAffect=0;		
		double initialIntervention = getReliability()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			InfoReliabilityAffect = (p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}
		System.out.println("my real relAffective is"+p.getPrefferedMode().getValueOfAttribute(Constants.infoReliabilityAffective)
				+"The new relaibilty Affect is " + InfoReliabilityAffect + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return InfoReliabilityAffect;
	}
//	
	public double evaluateTimeliness(Passenger p) {
		double timeliness=0;		
		double initialIntervention = getJourneyTime()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			timeliness = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return timeliness;
	}
	public double evaluateTimelinessAffective(Passenger p) {
		double timelinessAffective=0;		
		double initialIntervention = getJourneyTime()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			timelinessAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingToDestOnTimeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return timelinessAffective;
	}
////	
	public double evaluateDelays(Passenger p) {
		double delays=0;		
		double initialIntervention = getJourneyTime()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			delays = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		System.out.println(" the real delays value is "+p.getPrefferedMode().getValueOfAttribute(Constants.delaysPerception)
				+"The new delays value is " + delays + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return delays;
	}
	public double evaluateDelaysAffective(Passenger p) {
		double delaysAffective=0;		
		double initialIntervention = getJourneyTime()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			delaysAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.delaysAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}				
		return delaysAffective;
	}
////
	public double evaluateFrequency(Passenger p) {
		double frequency=0;			
		double initialIntervention =getJourneyTime()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			frequency = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			frequency = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			frequency = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			frequency = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return frequency;
	}
	public double evaluateFrequencyAffective(Passenger p) {
		double frequencyAffective=0;			
		double initialIntervention =getJourneyTime()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			frequencyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			frequencyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			frequencyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			frequencyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.modeFrequencyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return frequencyAffective;
	}
/////	
	public double evaluateGettingOnOff(Passenger p) {
		double gettingOnOff=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			gettingOnOff = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			gettingOnOff = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			gettingOnOff = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			gettingOnOff = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return gettingOnOff;
	}
	public double evaluateGettingOnOffAffective(Passenger p) {
		double gettingOnOffAffective=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			gettingOnOffAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			gettingOnOffAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			gettingOnOffAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			gettingOnOffAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return gettingOnOffAffective;
	}
	public double evaluateGettingOnOffPhysical(Passenger p) {
		double gettingOnOffPhysical=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			gettingOnOffPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePhysical)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			gettingOnOffPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePhysical)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			gettingOnOffPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePhysical)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			gettingOnOffPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.gettingOnOffModePhysical)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return gettingOnOffPhysical;
	}
/////
	public double evaluateSignsAvailablity(Passenger p) {
		double roadSigns=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			roadSigns = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			roadSigns = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			roadSigns = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			roadSigns = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return roadSigns;
	}
	public double evaluateSignsAvailablityAffective(Passenger p) {
		double roadSignsAffective=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			roadSignsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			roadSignsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			roadSignsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			roadSignsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.signsAvailabilityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return roadSignsAffective;
	}
/////
	public double evaluateOthersAttitude(Passenger p) {
		double othersAttitude=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			othersAttitude = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return othersAttitude;
	}
	public double evaluateOthersAttitudeAffective(Passenger p) {
		double othersAttitudeAffective=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			othersAttitudeAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.othersAttitudeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return othersAttitudeAffective;
	}
//////
	public double evaluateParkingConcerns(Passenger p) {
		double parkingConcern=0;			
		double initialIntervention =getValueForMoney()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			parkingConcern = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernPerception)-RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}else if(p.getPrefferedMode() instanceof Cycle){				
			parkingConcern = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return parkingConcern;
	}
	public double evaluateParkingConcernsAffective(Passenger p) {
		double parkingConcernAffective=0;			
		double initialIntervention =getValueForMoney()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			parkingConcernAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernAffective)-RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}else if(p.getPrefferedMode() instanceof Cycle){				
			parkingConcernAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.parkingSpaceConcernAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return parkingConcernAffective;
	}
	public double evaluateParkingConcernsPhysical(Passenger p) {
		double parkingConcernPhysical=0;			
		double initialIntervention =getValueForMoney()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			parkingConcernPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}else if(p.getPrefferedMode() instanceof Cycle){				
			parkingConcernPhysical = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return parkingConcernPhysical;
	}
/////
	public double evaluateProtectionFromElements(Passenger p) {
		double protectionFromElements=0;			
		double initialIntervention =getComfort()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			protectionFromElements = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			protectionFromElements = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			protectionFromElements = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			protectionFromElements = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		System.out.println("my real protection is "+p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsPerception)
				+"The new protection is " + protectionFromElements + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return protectionFromElements;
	}
	public double evaluateProtectionFromElementsAffective(Passenger p) {
		double protectionFromElementsAffective=0;			
		double initialIntervention =getComfort()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			protectionFromElementsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			protectionFromElementsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			protectionFromElementsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			protectionFromElementsAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		System.out.println("my protection affective is"+p.getPrefferedMode().getValueOfAttribute(Constants.protectionFromElementsAffective)
		+"The new protection affective is " + protectionFromElementsAffective + "and the intervenion is"+ RandomHelper.nextDoubleFromTo(0.01, initialIntervention));
		return protectionFromElementsAffective;
	}
///////
	public double evaluateSecurity(Passenger p) {
		double security=0;			
		double initialIntervention =getComfort()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			security = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			security = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			security = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			security = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return security;
	}
	public double evaluateSecurityAffective(Passenger p) {
		double securityAffective=0;			
		double initialIntervention =getComfort()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			securityAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			securityAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			securityAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			securityAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSecurityAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return securityAffective;
	}
///////
	public double evaluateSafety(Passenger p) {
		double safety=0;			
		double initialIntervention =getComfort()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			safety = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			safety = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			safety = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			safety = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return safety;
	}
	public double evaluateSafetyAffective(Passenger p) {
		double safetyAffective=0;			
		double initialIntervention =getComfort()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			safetyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			safetyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			safetyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			safetyAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.personalSafetyAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return safetyAffective;
	}
/////
	public double evaluateWalkingToDest(Passenger p) {
		double walkingToDest=0;			
		double initialIntervention =getPersonalMobility()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationPerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return walkingToDest;
	}
	public double evaluateWalkingToDestAffective(Passenger p) {
		double walkingToDestAffective=0;			
		double initialIntervention =getPersonalMobility()/10;
		if(p.getPrefferedMode() instanceof PersonalVehicle){			
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		} else if(p.getPrefferedMode() instanceof PublicTransport){				
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Cycle){				
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}else if(p.getPrefferedMode() instanceof Walking){				
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.walkingToDestinationAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));		
		}	
		return walkingToDestAffective;
	}
	
	public double evaluateGettingToMainMode(Passenger p) {
		double walkingToDest=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PublicTransport){				
			walkingToDest = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfGettingToMainModePerception)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return walkingToDest;
	}
	
	public double evaluateGettingToMainModeAffective(Passenger p) {
		double walkingToDestAffective=0;			
		double initialIntervention =getConvenience()/10;
		if(p.getPrefferedMode() instanceof PublicTransport){				
			walkingToDestAffective = (p.getPrefferedMode().getValueOfAttribute(Constants.easeOfGettingToMainModeAffective)+RandomHelper.nextDoubleFromTo(0.01, initialIntervention));	
		}	
		return walkingToDestAffective;
	}
////
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
