package moshproject.agents.passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import moshproject.agent.behaviour.Behaviour;
import moshproject.agents.enums.SocialWeight;
import moshproject.agents.intervener.Intervener;
import moshproject.agents.intervener.Intervener2;
import moshproject.agents.mode.ActiveMode;
import moshproject.agents.mode.Mode;
import moshproject.agents.mode.NonActiveMode;
import moshproject.common.Constants;
import moshproject.common.NormalWeigthValues;
import moshprojectagents.journey.JourneyCategory;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.integration.eclipse.NewDataDescriptorFileWizard;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

import com.sun.javafx.collections.MappingChange.Map;

public class Passenger {	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;		
	Random  rand = new Random();	
//	Map map = new HashMap<>();	
	ArrayList<Double> weigthValues;
	Intervener intervener;
	Demography passDemography;
	public Intervener interventions;
	//java.util.Map<SocialWeight, Double> dSocialFactor;
	private static final double modeSatisfactionThreshold =0.7; 
	
	ArrayList<Passenger> SelectedFriends;
	public ArrayList<Passenger> setSelectedFriends(ArrayList<Passenger> selectedFriends) {
		return SelectedFriends = selectedFriends;
	}
	public ArrayList<Passenger> getSelectedFriends() {
		return SelectedFriends =passType.findWeakLinksInteractee();
	}
	
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

	private double similarityValue;	
	 public double getSimilarityValue() {return similarityValue;}
	public void setSimilarityValue(double similarityValue) {this.similarityValue = similarityValue;}
	public JourneyCategory passJourney;
	
	private double distanceRange;	
	public double getDistanceRange() {
		return distanceRange;
	}

	public PassengerType passType;
	public PassengerType getPassengerType() {
		return passType;
	}
	public void setPassengerType(PassengerType passengerType) {
		this.passType = passengerType;
		this.passType.myPassenger = this;
	}

	private Mode interracteePrefferedMode;	
	public void setInterracteePrefferedMode(Mode interracteePrefferedMode) {
		this.interracteePrefferedMode = interracteePrefferedMode;
	}
	public Mode getInterracteePrefferedMode() {	
		return interracteePrefferedMode;
	}
	
	private PassengerType interracteePassengerType;	
	public void setInterracteePassengerType(PassengerType passType2) {
		this.interracteePassengerType = passType2;
	}
	public PassengerType getInterracteePassengerType() {	
		return interracteePassengerType;
	}
	
	private boolean isTraveldays;
	

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
	
	private boolean sideWalkInterventionSet;	

	public boolean isSideWalkInterventionSet() { return sideWalkInterventionSet;}
	public void setSideWalkInterventionSet(boolean sideWalkInterventionSet) {this.sideWalkInterventionSet = sideWalkInterventionSet;}
	
	private boolean journeyStatusInterventionSet;
	public boolean isJourneyStatusInterventionSet() {return journeyStatusInterventionSet;}
	public void setJourneyStatusInterventionSet(boolean journeyStatusInterventionSet) {this.journeyStatusInterventionSet = journeyStatusInterventionSet;}
	
	private boolean roadCrossingInterventionSet;
	public boolean isRoadCrossingInterventionSet() {return roadCrossingInterventionSet;}
	public void setRoadCrossingInterventionSet(boolean roadCrossingInterventionSet) {this.roadCrossingInterventionSet = roadCrossingInterventionSet;}
	
	private boolean routeAvailabilityInterventionSet;
	public boolean isRouteAvailabilityInterventionSet() {return routeAvailabilityInterventionSet;}
	public void setRouteAvailabilityInterventionSet(boolean routeAvailabilityInterventionSet) {this.routeAvailabilityInterventionSet = routeAvailabilityInterventionSet;}
	
	private boolean attitudeOfOthersInterventionSet;
	public boolean isAttitudeOfOthersInterventionSet() {return attitudeOfOthersInterventionSet;}
	public void setAttitudeOfOthersInterventionSet(boolean attitudeOfOthersInterventionSet) {this.attitudeOfOthersInterventionSet = attitudeOfOthersInterventionSet;}
	
	private boolean luggageCarrierInterventionSet;	
	public boolean isLuggageCarrierInterventionSet() {return luggageCarrierInterventionSet;}
	public void setLuggageCarrierInterventionSet(boolean luggageCarrierInterventionSet) {this.luggageCarrierInterventionSet = luggageCarrierInterventionSet;}

	private boolean facilitiesAtDestInterventionSet;
	public boolean isFacilitiesAtDestInterventionSet() {return facilitiesAtDestInterventionSet;}
	public void setFacilitiesAtDestInterventionSet(boolean facilitiesAtDestInterventionSet) {this.facilitiesAtDestInterventionSet = facilitiesAtDestInterventionSet;}

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
	
	private double personalSatisfaction;	
	public double getPersonalSatisfaction() {
		return personalSatisfaction;
	}
	
	private double socialUncertainty;	
	public double getSocialUncertainty() {
		return socialUncertainty;
	}
	public void setSocialUncertainty(double socialUncertainty) {
		this.socialUncertainty = socialUncertainty;
	}
	
	private double socialSatisfaction;	
	public double getSocialSatisfaction() {
		return socialSatisfaction;
	}
	public void setSocialSatisfaction(double socialSatisfaction) {
		this.socialSatisfaction = socialSatisfaction;
	}

	public List<Double> myModeSatisfactionList = new ArrayList<Double>();
	
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

	public double  previousExperience;
	public double getPreviousExperience() {
		return previousExperience;
	}
	public void setPreviousExperience(double previousExperience) {
		this.previousExperience = previousExperience;
	}	
	
	private double changeInExperience;
	public double getChangeInExperience() {
		return changeInExperience;
	}
	public void setChangeInExperience(double changeInExperience) {	
		this.changeInExperience = changeInExperience;
	}

	private double levelOfNeedsSatisfaction;
	public double getLevelOfNeedsSatisfaction() {
		return levelOfNeedsSatisfaction;
	}
	public void setLevelOfNeedsSatisfaction(double levelOfNeedsSatisfaction) {
		this.levelOfNeedsSatisfaction = levelOfNeedsSatisfaction;
	}

	private boolean socialAgreeability;

	private double assumedMaximumDifference;
	public double getAssumedMaximumDifference() {
		return assumedMaximumDifference;
	}

	private double socialInformation;
	public double getSocialInformation() {
		return socialInformation;
	}
	public void setSocialInformation(double socialInformation) {
		this.socialInformation = socialInformation;
	}

	private double socialFrequency;
	public double getSocialFrequency() {
		return socialFrequency;
	}
	
	public double transitionBehaviour;		
	
	private Behaviour behaviour;
	
	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}
	public Behaviour getBehaviour() {
		return behaviour;
	}

	private int tick;	
	private double changeInExpectation;
	private double sidewalks;
	private double sidewalksWeight;
	private double roadCrossing;
	private double attitudeOfOthers;
	private double routeAvailability;
	private double facilitiesAtDest;
	private double journeyTime;
	private double luggageCarrier;
	private double roadCrossingWeight;
	private double attitudeOfOthersWeight;
	private double routeAvailabilityWeight;
	private double facilitiesAtDestWeight;
	private double journeyTimeWeight;
	private double luggageCarrierWeight;
	private int travelFreq;
	
	public Passenger(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.space = space;
		this.grid = grid;
		socialAgreeability= false;
		socialInformation =0.0;
		socialFrequency=0.3;
		interacteeFound=false;
		inquiryInteracteeFound =false;
		isTraveldays =false;		
		this.passDemography= new Demography(); 
		interventions= new Intervener(space, grid);
	}

	public void initialize(){
		dailyTrip();
		travelFrequency();
		uncertaintyTolerance= rand.nextGaussian()*0.05+0.35;
		assumedMaximumDifference= 0.01;	
		previousExperience=0.0;//aspirationLevel =  ComputeAmbition();
	//	socialUncertainty= rand.nextGaussian()*0.0035+0.1;
		behaviour=Behaviour.REPEAT;
	//	uncertainty=0.5;
	}
	
	@ScheduledMethod(start =1, interval=1, priority =2)
	public void step() {	
		System.out.println("Tick:"+ this.getTickCount());	

		System.out.println("======My travel frequency is:" + travelFreq);		
		normalisedWeight();
		System.out.println("The Type of Passenger is" + this.getPassengerType());
		System.out.println("The actual passenger" + this.toString());

		updateAllFactors();	
		updateSatisfaction();
		myPreviousModeStatisfaction();
		levelOfNeedsSatisfaction= getModeSatisfaction();
		aspirationLevel =  ComputeAmbition();
		setPassengerType(this.passType);	
	
		getSelectedFriends();

		System.out.println(" The social uncertainty is"+ getSocialUncertainty());	
		System.out.println(" The Uncertainty"+ getUncertainty());	
		System.out.println(" The uncertainty tolerance is :"+uncertaintyTolerance);	
		System.out.println("  ==The ambition level is :"+aspirationLevel);
		computeSocialSatisfaction();		
		System.out.println("  ****The sintervention status is :"+ this.isSideWalkInterventionSet());
		System.out.println("  ****The Journey Time status is :"+ this.isJourneyStatusInterventionSet());
	
		if (getTickCount()>25){	
			selectBehaviour();
		}
	
		System.out.println("My new Type is" + this.getPassengerType());
		move();
	
}
	
	public void travelFrequency(){		
		if((this.passType instanceof Cyclist)||(this.passType instanceof Pedestrian)||(this.passType instanceof Scooter)){
			this.travelFreq =passDemography.travelFrequency();
		}				
	}
	
	@ScheduledMethod(start =1, interval=1, priority=0)
	private void updatePreviousExperience(){
		previousExperience = passType.updatePreviousExperience();	
	}
 	public void dailyTrip(){
		if (passType instanceof Pedestrian){		
			passDemography.distance = ((Pedestrian) passType).setWalkingDistance();
			passDemography.distanceRange();			
		}else if (passType instanceof Scooter){
			passDemography.distance =((Scooter) passType).setScootingDistance();
			passDemography.distanceRange();
		}else if (passType instanceof Cyclist){
			passDemography.distance =((Cyclist) passType).setCyclingDistance();
			passDemography.distanceRange();
		}
	}
	
	private void updateSatisfaction(){
		if (prefferedMode instanceof ActiveMode){
			modeSatisfaction= ((Math.pow(sidewalks,sidewalksWeight))*(Math.pow(roadCrossing,roadCrossingWeight))*
					(Math.pow(routeAvailability, routeAvailabilityWeight))*(Math.pow(attitudeOfOthers,attitudeOfOthersWeight))*
							(Math.pow(facilitiesAtDest,facilitiesAtDestWeight))*(Math.pow(journeyTime,journeyTimeWeight))
							*(Math.pow(luggageCarrier,luggageCarrierWeight)));
			System.out.println(" The Satisfaction is :"+ modeSatisfaction);
						
		}else if (prefferedMode instanceof NonActiveMode){
			modeSatisfaction= ((Math.pow(sidewalks,sidewalksWeight))*(Math.pow(roadCrossing,roadCrossingWeight))*
					(Math.pow(routeAvailability, routeAvailabilityWeight))*(Math.pow(attitudeOfOthers,attitudeOfOthersWeight))*
							(Math.pow(facilitiesAtDest,facilitiesAtDestWeight))*(Math.pow(journeyTime,journeyTimeWeight))
							*(Math.pow(luggageCarrier,luggageCarrierWeight)));
			System.out.println(" The Non Active Satisfaction is :"+ modeSatisfaction);
						
		}
	}
	
	private int getTickCount(){
		int tickCount = (int) RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		return tickCount;
	}			
	public double getModeSatisfaction() {
		return modeSatisfaction;
	}	
	private void myPreviousModeStatisfaction(){	
		myModeSatisfactionList.add(modeSatisfaction);			
	}				
	public List<Double> getModeSatisfactionList() {
		return myModeSatisfactionList;
	}

	//@ScheduledMethod(start =1, interval=1, priority=1)
	private void move(){ 
		GridPoint pt = grid.getLocation(this);
		NdPoint mypoint = space.getLocation(this);
		NdPoint otherpoint = new NdPoint(pt.getX(),pt.getY());
		double angle = SpatialMath.calcAngleFor2DMovement(space, mypoint, otherpoint);
		space.moveByVector(this, 5, angle,0);
		mypoint=space.getLocation(this);
		grid.moveTo(this,(int)mypoint.getX(),(int)mypoint.getY());
	}
//	@ScheduledMethod(start =1, interval=1, priority=4)
	public void selectBehaviour(){
		if((passType instanceof NonActiveTraveller)){
	//	if(isInterventionApplies()){
			if ((uncertainty>=uncertaintyTolerance)&&(modeSatisfaction<=aspirationLevel)){
				passType.inquiring();
				behaviour = Behaviour.INQUIRE;
			//	adoptInterracteeType();
			}else if ((uncertainty<=uncertaintyTolerance)&&(modeSatisfaction<aspirationLevel)){				
				passType.optimising();
				behaviour =Behaviour.OPTIMISE;
			//	selectType();
			}else if ((uncertainty>uncertaintyTolerance)&&(modeSatisfaction>=aspirationLevel)){				
				passType.imitation();
				behaviour=Behaviour.IMITATE;
			//	adoptInterracteeType();
			}else if ((uncertainty<uncertaintyTolerance)&&(modeSatisfaction>=aspirationLevel)){				
				passType.repetition();	
				behaviour=Behaviour.REPEAT;
			}	
			//adoptInterracteeMode();				
		}
		adoptInterracteeType();
	//	}
	}

	public int isImitating() {
	//	if ((this.passType instanceof NonActiveTraveller)&&(this.getBehaviour()==Behaviour.IMITATE))
		if ((this.getBehaviour()==Behaviour.IMITATE))
			return 1;
		else return 0;
	}
	public int isInquiring() {
		if ((this.getBehaviour()==Behaviour.INQUIRE))
			return 1;
		else return 0;
	}
	public int isOptimising() {
		if ((this.getBehaviour()== Behaviour.OPTIMISE))
			return 1;
		else return 0;
	}
	public int isRepeating() {
		if ((this.getBehaviour()==Behaviour.REPEAT))
			return 1;
		else return 0;
	}
//		private void checkFriends(){
//			getSelectedFriends();
//			for (Passenger pass: SelectedFriends){
//				System.out.println("*****Tthese are seelcted"+ pass);
//			}
//		}
		public double ComputeAmbition(){
			double totalAmbitions= (((getSidewalks()+getJourneyTime()+getLuggageCarrier()+getRoadCrossing()
					+getAttitudeOfOthers()+getFacilitiesAtDest()+getRouteAvailability())/7)*1.09);
			//System.out.println("   =====The ambition level is :"+totalAmbitions);
			return totalAmbitions;				
		}
////
		/// This failed expectation after interaction
				
				@ScheduledMethod(start =1, interval=1, priority =3)
				public double uncertainty(){
					double personaluncertainty =0.0;
					if(passType instanceof NonActiveTraveller){
						uncertainty = rand.nextGaussian()*0.05+0.2;
					}else{									
						double passuncertainty = getLevelOfNeedsSatisfaction()- getPreviousExperience();
							if(passuncertainty<0.0){
								personaluncertainty= (1-(Math.abs(passuncertainty)));
							}else{
								personaluncertainty= passuncertainty;
							}
						double socialUncertainty = getSocialUncertainty();
						double	totalUncertainty= (personaluncertainty + socialUncertainty)/2;
							if(totalUncertainty<0.0){
								uncertainty= (1-(Math.abs(totalUncertainty)));
							}else{
								uncertainty= totalUncertainty;
							}
					}
					return uncertainty;
				}
////
//		public Map<SocialWeight, Double> getSocialFactor() {
//			return (Map<SocialWeight, Double>) (dSocialFactor=passType.getSocialSatisfactionFactors(getSelectedFriends()));
//		}		

		private double computeSocialSatisfaction(){
			passType.socialSatisfactions(SelectedFriends);
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
		

		public Mode adoptInterracteeMode(){
			if (this.getInterracteePrefferedMode()instanceof ActiveMode){
				this.prefferedMode = new ActiveMode();
			}	
			return this.prefferedMode;	
		}

		public void adoptInterracteeType(){
			if(passType instanceof NonActiveTraveller){
				if (this.getInterracteePassengerType() instanceof Cyclist){
					this.prefferedMode = new ActiveMode();
					this.passType = new Cyclist(space, grid);
				}else if(this.getInterracteePassengerType() instanceof Pedestrian){
					this.prefferedMode = new ActiveMode();
					this.passType = new Pedestrian(space, grid);
				}else if(this.getInterracteePassengerType() instanceof Scooter){
					this.prefferedMode = new ActiveMode();
					this.passType = new Scooter(space, grid);
				}else if ((this.getInterracteePassengerType() instanceof NonActiveTraveller)||
						(this.modeSatisfaction>modeSatisfactionThreshold)){
					double r = rand.nextDouble();
					if(r<=0.1){							
						this.prefferedMode = new ActiveMode();
						this.passType = new Scooter(space, grid);
					}else if (r<=0.1+0.1){		
						this.prefferedMode = new ActiveMode();
						this.passType = new Cyclist(space, grid);	
					}else if (r<=0.1+0.1+0.1){		
						this.prefferedMode = new ActiveMode();
						this.passType = new Pedestrian(space, grid);	
					}else {		
						this.prefferedMode = new NonActiveMode();
						this.passType = new NonActiveTraveller(space, grid);
					}
				}else {//if ((this.getInterracteePassengerType() instanceof NonActiveTraveller)||
					//	(this.modeSatisfaction<modeSatisfactionThreshold)){
					this.prefferedMode = new NonActiveMode();
					this.passType = new NonActiveTraveller(space, grid);
				}
		}
			travelFrequency();
			updateSatisfaction();	
		}
	public double updateStatechartTransition(){			
				transitionBehaviour =modeSatisfaction;			
		return transitionBehaviour;
	}
///// Update the factors from Modes
//////Sidewalk 
  private void updateAllFactors(){
	updateSidewalks();
	updateAttitudeOfOthers();
	updateFacilities();
	updateJourneyTime();
	updateLuggageCarrier();
	updateRoadCrossing();
	updateRouteAvailability();			
  }
   private double updateSidewalks() {
		if (prefferedMode instanceof ActiveMode)	{				
			sidewalks= prefferedMode.getValueOfAttribute(Constants.paths);				
		}else if (prefferedMode instanceof NonActiveMode){
			sidewalks= prefferedMode.getValueOfAttribute(Constants.paths);
		}	
	//	System.out.println(" value for Sidewalk :"+ sidewalks);	
		return sidewalks;
	}
   public double getSidewalks() {return sidewalks;	}
   public void setSidewalks(double sidewalks) {this.sidewalks = sidewalks;}
	
	private double updateRoadCrossing() {
		if (prefferedMode instanceof ActiveMode)	{				
			roadCrossing= prefferedMode.getValueOfAttribute(Constants.crossingAndRoadsigns);				
		}else if (prefferedMode instanceof NonActiveMode){
			roadCrossing= prefferedMode.getValueOfAttribute(Constants.crossingAndRoadsigns);
		}	
		return roadCrossing;
	}
	public double getRoadCrossing() {return roadCrossing;	}
	public void setRoadCrossing(double roadCrossing) {this.roadCrossing = roadCrossing;}
	
	private double updateAttitudeOfOthers() {
		if (prefferedMode instanceof ActiveMode)	{				
			attitudeOfOthers= prefferedMode.getValueOfAttribute(Constants.attitudeOfOthers);				
		}else if (prefferedMode instanceof NonActiveMode){
			attitudeOfOthers= prefferedMode.getValueOfAttribute(Constants.attitudeOfOthers);
		}	
		return attitudeOfOthers;
	}
	public double getAttitudeOfOthers() {return attitudeOfOthers;	}
	public void setAttitudeOfOthers(double attitudeOfOthers) {this.attitudeOfOthers = attitudeOfOthers;}
	
	private double updateRouteAvailability() {
			if (prefferedMode instanceof ActiveMode)	{				
				routeAvailability= prefferedMode.getValueOfAttribute(Constants.routeAvailabilityAndObstruction);				
			}else if (prefferedMode instanceof NonActiveMode){
				routeAvailability= prefferedMode.getValueOfAttribute(Constants.routeAvailabilityAndObstruction);
			}	
			return routeAvailability;
		}
	public double getRouteAvailability() {return routeAvailability;	}
	public void setRouteAvailability(double routeAvailAndObstr) {this.routeAvailability = routeAvailAndObstr;}
	
	private double updateFacilities() {
		if (prefferedMode instanceof ActiveMode)	{				
			facilitiesAtDest= prefferedMode.getValueOfAttribute(Constants.facilitiesAtDestination);				
		}else if (prefferedMode instanceof NonActiveMode){
			facilitiesAtDest= prefferedMode.getValueOfAttribute(Constants.facilitiesAtDestination);
		}	
		return facilitiesAtDest;
	}
    public double getFacilitiesAtDest() {return facilitiesAtDest;	}
    public void setFacilitiesAtDest(double facilities) {this.facilitiesAtDest = facilities;}	
   
   private double updateJourneyTime() {
		if (prefferedMode instanceof ActiveMode)	{				
			journeyTime= prefferedMode.getValueOfAttribute(Constants.journeyTimeConsideration);				
		}else if (prefferedMode instanceof NonActiveMode){
			journeyTime= prefferedMode.getValueOfAttribute(Constants.journeyTimeConsideration);
		}	
		return journeyTime;
	}
   public double getJourneyTime() {return journeyTime;	}
   public void setJourneyTime(double journey) {this.journeyTime = journey;}
   
  private double updateLuggageCarrier() {
		if (prefferedMode instanceof ActiveMode)	{				
			luggageCarrier= prefferedMode.getValueOfAttribute(Constants.capabilityForLuggageCarrier);				
		}else if (prefferedMode instanceof NonActiveMode){
			luggageCarrier= prefferedMode.getValueOfAttribute(Constants.capabilityForLuggageCarrier);
		}	
		return luggageCarrier;
	}
  public double getLuggageCarrier() {return luggageCarrier;	}
  public void setLuggageCarrier(double luggageCarrier) {this.luggageCarrier = luggageCarrier;}
   
////Weight Normalisation section			
	private double[] unNormalisedData(){
		weigthValues = new ArrayList<Double>();					
		weigthValues.add(updateSidewalks());
		weigthValues.add(updateRoadCrossing());
		weigthValues.add(updateAttitudeOfOthers());
		weigthValues.add(updateRouteAvailability());
		weigthValues.add(updateFacilities());
		weigthValues.add(updateJourneyTime());
		weigthValues.add(updateLuggageCarrier());
			
		double allValues[] = new double[weigthValues.size()];			
		for (int i =0; i<allValues.length; i++){
			allValues[i]= (weigthValues.get(i));				
		}					
		return allValues;
	}
	public void normalisedWeight(){
		double allValues[];
		allValues = NormalWeigthValues.scaleValues(unNormalisedData());		
		for (int i =0; i<allValues.length; i++){		
			sidewalksWeight = allValues[0];
			roadCrossingWeight = allValues[1];
			attitudeOfOthersWeight = allValues[2];
			routeAvailabilityWeight =allValues[3];
			facilitiesAtDestWeight = allValues[4];
			journeyTimeWeight = allValues[5];
			luggageCarrierWeight =allValues[6];
		}		
	}
	public double getSidewalksWt() {
		return this.sidewalksWeight;		
	}
	public double getRoadCrossingWt() {return this.roadCrossingWeight;}
	public double getAttitudeOfOthersWt() {return this.attitudeOfOthersWeight;}
	public double getRouteAvailabilityWt() {return this.routeAvailabilityWeight;}
	public double getFacilitiesAtDestWt() {return this.facilitiesAtDestWeight;}
	public double getJourneyTimeWt() {return this.journeyTimeWeight;}
	public double getLuggageCarrierWt() {return this.luggageCarrierWeight;}
/////getting passenger types	
	public int isScooters() {
		if (passType instanceof Scooter)
			return 1;
		else return 0;
	}
	public int isCyclist() {
		if (this.passType instanceof Cyclist)
			return 1;
		else return 0;
	}
	public int isNonActiveTraveller() {
		if (this.passType instanceof NonActiveTraveller)
			return 1;
		else return 0;
	}
	public int isPedestrian() {
		if (this.passType instanceof Pedestrian)
			return 1;
		else return 0;
	}
}


		
	
	

	
	
			
			
			
			

