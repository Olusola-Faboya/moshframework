package moshproject.agents.mode;

import moshproject.common.Constants;
import repast.simphony.random.RandomHelper;

public class PublicTransport extends Mode{
	
	private double reliabilityPerception;		
	public double getReliabilityPerception() {
		return this.reliabilityPerception;
	}
	private double reliabilityAffective;		
	public double getReliabilityAffective() {
		return this.reliabilityAffective;
	}
////////
	private double gettingToDestOnTimePerception;		
	public double getGettingToDestOnTimePerception() {
		return gettingToDestOnTimePerception;
	}
	private double gettingToDestOnTimeAffective;		
	public double getGettingToDestOnTimeAffective() {
		return gettingToDestOnTimeAffective;
	}	
	//////	
	private double easeOfAccessInfoPerception;		
	public double getEaseOfAccessInfoPerception() {
		return easeOfAccessInfoPerception;
	}
	private double easeOfAccessInfoAffective;		
	public double getEaseOfAccessInfoAffective() {
		return easeOfAccessInfoAffective;
	}
//////			
	private double modeFrequencyPerception;		
	public double getModeFrequencyPerception() {
		return modeFrequencyPerception;
		}
	private double modeFrequencyAffective;		
	public double getModeFrequencyAffective() {
		return modeFrequencyAffective;
	}
////	
//	private double modeParkingSpaceConcernPerception;	
//	public double getModeParkingSpaceConcernPerception() {
//		return modeParkingSpaceConcernPerception;
//	}
//	private double modeParkingSpaceConcernAffective;	
//	public double getModeParkingSpaceConcernAffective() {
//		return modeParkingSpaceConcernAffective;
//	}
	//	
	private double easeOfGettinOnOffPerception;		
	public double getEaseOfGettingOnOffPerception() {
		return easeOfGettinOnOffPerception;
	}
	private double easeOfGettingOnOffAffective;		
	public double getEaseOfGettingOnOffAffective() {
		return easeOfGettingOnOffAffective;
	}	
/////				
	private double delaysPerception;		
	public double getDelaysPerception() {
		return delaysPerception;
	}
	private double delaysAffective;		
	public double getDelaysAffective() {
		return delaysAffective;
	}
/////		
	private double signAvailabilityPerception;		
	public double getSignAvailabilityPerception() {
		return signAvailabilityPerception;
	}
	private double signAvailabilityAffective;		
	public double getSignAvailabilityAffective() {
		return signAvailabilityAffective;
	}
/////		
	private double protectionFromElementsPerception;		
	public double getProtectionFromElementsPerception() {
		return protectionFromElementsPerception;
	}
	private double protectionFromElementsAffective;		
	public double getProtectionFromElementsAffective() {
		return protectionFromElementsAffective;
	}
//////
	private double othersAttitudePerception;		
	public double getOthersAttitudePerception() {
		return othersAttitudePerception;
	}
	private double othersAttitudeAffective;		
	public double getOthersAttitudeAffective() {
		return othersAttitudeAffective;
	}
/////			
	private double personalSecurityPerception;		
	public double getPersonalSecurityPerception() {
		return personalSecurityPerception;
	}
	private double personalSecurityAffective;		
	public double getPersonalSecurityAffective() {
		return personalSecurityAffective;
	}
//////		
	private double personalSafetyPerception;		
	public double getPersonalSafetyPerception() {
		return personalSafetyPerception;
	}
	private double personalSafetyAffective;		
	public double getPersonalSafetyAffective() {
		return personalSafetyAffective;
	}
//////
	private double walkingToDestinationPerception;	
	public double getWalkingToDestinationPerception() {
		return walkingToDestinationPerception;
	}
	private double walkingToDestinationAffective;	
	public double getWalkingToDestinationAffective() {
		return walkingToDestinationAffective;
	}
//////
	private double distanceToMainModePerception;	
	public double getDistanceToMainModePerception() {
		return distanceToMainModePerception;
	}
	private double distanceToMainModeAffective;	
	public double getDistanceToMainModeAffective() {
		return distanceToMainModeAffective;
	}
	private double distanceToMainModePhysical;	
	public double getDistanceToMainModePhysical() {
		return distanceToMainModePhysical;
	}
	
	private double easeOfGettingToMainModePerception;	
	public double getEaseOfGettingToMainModePerception() {
		return easeOfGettingToMainModePerception;
	}
	private double easeOfGettingToMainModeAffective;	
	public double getEaseOfGettingToMainModeAffective() {
		return easeOfGettingToMainModeAffective;
	}
	//// This section define and gets the decision attributes weighting factors
	private double efficiencyWeight;				
	public double getEffectiveWeight() {
		return efficiencyWeight;
	}
	private double conveniencyWeight;		
	public double getConvenienceWeight() {
		return conveniencyWeight;
	}
	private double safetyWeight;	
	public double getSafetyWeight() {
		return safetyWeight;
	}
	private double userOccupation;	
	public double getUserOccupation() {return userOccupation;}
	private double userDistanceRange;		
	public double getUserDistanceRange() {return userDistanceRange;}

	//////	
	public PublicTransport() {
		// TODO Auto-generated constructor stub
		
//	This section/constructor adds modes attributes from the questionnaire 
		addAttribute(Constants.infoReliabilityPerception);
		addAttribute(Constants.infoReliabilityAffective);	
///	
		addAttribute(Constants.easeOfAccessInfoPerception);
		addAttribute(Constants.easeOfAccessInfoAffective);
///
		addAttribute(Constants.gettingToDestOnTimePerception);
		addAttribute(Constants.gettingToDestOnTimeAffective);
///
		addAttribute(Constants.modeFrequencyPerception);
		addAttribute(Constants.modeFrequencyAffective);
///
		addAttribute(Constants.distanceToMainModePerception);
		addAttribute(Constants.distanceToMainModeAffective);
///
		addAttribute(Constants.easeOfGettingToMainModePerception);
		addAttribute(Constants.easeOfGettingToMainModeAffective);
///		
		addAttribute(Constants.gettingOnOffModePerception);
		addAttribute(Constants.gettingOnOffModeAffective);
///			
		addAttribute(Constants.delaysPerception);
		addAttribute(Constants.delaysAffective);
///				
		addAttribute(Constants.signsAvailabilityPerception);
		addAttribute(Constants.signsAvailabilityAffective);
///
		addAttribute(Constants.walkingToDestinationPerception);
		addAttribute(Constants.walkingToDestinationAffective);
///
		addAttribute(Constants.personalSecurityPerception);
		addAttribute(Constants.personalSecurityAffective);
///			
		addAttribute(Constants.personalSafetyPerception);
		addAttribute(Constants.personalSafetyAffective);
///			
		addAttribute(Constants.othersAttitudePerception);
		addAttribute(Constants.othersAttitudeAffective);
///	
		addAttribute(Constants.protectionFromElementsPerception);
		addAttribute(Constants.protectionFromElementsAffective);
///
		///The attributes Importance
		addAttribute(Constants.efficiencyWeight);
		addAttribute(Constants.conveniencyWeight);
		addAttribute(Constants.safetyWeight);
///		///The Demography
		addAttribute(Constants.userDistanceRange);
		addAttribute(Constants.usersOccupation);
///// Setting up of returning values of Physical, Cognitive and Affective 			
		setReliabilityPerception();
		setReliabilityAffective();	
///////		
		setGettingToDestOnTimePerception();
		setGettingToDestOnTimeAffective();
//////			
		setEaseOfAccessInfoPerception();
		setEaseOfAccessInfoAffective();
//////			
		setModeFrequencyPerception();
		setModeFrequencyAffective();
/////		
		setEaseOfGettinOnOffPerception();
		setEaseOfGettingOnOffAffective();
//////			
		setDelaysPerception();
		setDelaysAffective();
/////		
		setSignAvailabilityPerception();
		setSignAvailabilityAffective();
/////			
		setProtectionFromElementsPerception();
		setProtectionFromElementsAffective();
//////			
		setOthersAttitudePerception();
		setOthersAttitudeAffective();
//////			
		setSecurityPerception();
		setSecurityAffective();
//////		
		setSafetyPerception();
		setSafetyAffective();
/////
		setWalkingToDestPerception();
		setWalkingToDestAffective();
////
		setDistanceToMainModePerception();
		setDistanceToMainModeAffective();
////
		setEaseOfGettingToMainModePerception();
		setEaseOfGettingToMainModeAffective();
////
		setEfficiencyWeight();
		setConveniencyWeight();
		setSafetyWeight();
////
		setUsersDistanceRange();
		setUsersOccupation();
	}
	private static final double Group1 = 0.483871; 
	private static final double Group2 = Group1+0.236559;
	private static final double Group3 = Group2+0.279569;
//Weight/Importance category	
	private static final double WtGroup1 =0.05376 ; 
	private static final double WtGroup2 =WtGroup1+0.29032 ;
	private static final double WtGroup3 = WtGroup2+0.50537;
	private static final double WtGroup4 = WtGroup3+0.15054;
//Travellers' Occupation category	
		private static final double OccuGroup1 =0.010752 ; 
		private static final double OccuGroup2 =OccuGroup1+0.00021;
		private static final double OccuGroup3 = OccuGroup2+0.0323;
		private static final double OccuGroup4 = OccuGroup3+0.054;
		private static final double OccuGroup5 = OccuGroup4+0.8925;
///Distance travel categories
	private static final double DstGroup1 =0.0430 ; 
	private static final double DstGroup2 =DstGroup1+0.0753;
	private static final double DstGroup3 = DstGroup2+0.2150;
	private static final double DstGroup4 = DstGroup3+0.6667;
//////set the Users Distance and Occupation
	private double setUsersOccupation(){
		double r = rand.nextDouble();
		if(r<=OccuGroup1){		
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.JUNIORMANAGER);			
		}else if(r<=OccuGroup2){		
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.PARTTIMESTUDENT);
		}else if (r<=OccuGroup3) {
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.MIDDLEMANAGER);
		}else if (r<=OccuGroup4){
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.OTHERACADEMICS);
		}else if (r<=OccuGroup5){
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.FULLTIMESTUDENT);
		}
		return userOccupation;
	}
	
	private double setUsersDistanceRange(){
		double r = rand.nextDouble();
		if(r<=DstGroup1){		
			setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.GREATERTWENTYFIVE);			
		}else if(r<=DstGroup2){		
			setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.BETTENANDTWENTYFIVE);
		}else if (r<=DstGroup3) {
			setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.BETFIVEANDTEN);
		}else if (r<=DstGroup4){
			setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.LESSTHANFIVE);
		}
		return userDistanceRange;
	}
	
////// set the importance values
	private double setEfficiencyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.0, 0.0625));
		}else if (r<=WtGroup2) {
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.7375, 1.0));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.7375, 1.0));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.6625, 1.0));
		}
		return efficiencyWeight;
	}
		
	private double setConveniencyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.0, 0.461538));
		}else if (r<=WtGroup2){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.557692,0.807692));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.730769, 1.0));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.192308, 0.576923));
		}
		return conveniencyWeight;
	}
	
	private double setSafetyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.0,0.529412));
		}else if (r<=WtGroup2){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.588235, 0.794118));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.823529,1.0));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.176471,0.647059));
		}
		return safetyWeight;
	}
//////	Set mode attributes P,C,A Values
	private double setReliabilityPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){	
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}
		return reliabilityPerception;						
	}
	private double setReliabilityAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.672863,0.891775));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.3680779,0.891775));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.77658162,0.89177496));
		}
		return reliabilityAffective;							
	}
	
	private double setGettingToDestOnTimePerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.0,0.75));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}
		return gettingToDestOnTimePerception;		
	}
	private double setGettingToDestOnTimeAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.67286315,0.89270506));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.221310689,0.803713389));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.76436838,0.89270506));
		}
		return gettingToDestOnTimeAffective;					
	}
	private double setEaseOfAccessInfoPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
		}
		return easeOfAccessInfoPerception;		
	}
	private double setEaseOfAccessInfoAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.67286315,0.891774956));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.254659466,0.891774956));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.776581616,0.891774956));
		}
		return easeOfAccessInfoAffective;					
	}
///////

	private double setWalkingToDestPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.walkingToDestinationCognitive,RandomHelper.nextDoubleFromTo(0.5,1.0));						
		}else if (r<=Group2){		
			setValueToAttribute(Constants.walkingToDestinationPerception,RandomHelper.nextDoubleFromTo(0.25,0.75));						
		}else if (r<=Group3){		
			setValueToAttribute(Constants.walkingToDestinationPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));						
		}
		return walkingToDestinationPerception;				
	}
	private double setWalkingToDestAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.600663,0.891775));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.2546595,0.7321041));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.4785422,0.8717329));
		}
		return walkingToDestinationAffective;						
	}
	private double setEaseOfGettinOnOffPerception(){
	double r = rand.nextDouble();
	if(r<=Group1){		
		setValueToAttribute(Constants.gettingOnOffModePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));	
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingOnOffModePerception,RandomHelper.nextDoubleFromTo(0.25,1.0));	
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingOnOffModePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));	
		}
	return easeOfGettinOnOffPerception;						
	}
	private double setEaseOfGettingOnOffAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.6006628,0.891775));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.2546595,0.871733));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.47856976,0.871732927));
		}
		return easeOfGettingOnOffAffective;						
	}
//////
	private double setDelaysPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.delaysPerception,RandomHelper.nextDoubleFromTo(0.0,1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.delaysPerception,RandomHelper.nextDoubleFromTo(0.0,0.75));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.delaysPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}
		return delaysPerception;
	}
	private double setDelaysAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.3935968,0.89270506));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.173191012,0.803713389));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.387397433,0.89270506));
		}
		return delaysAffective;
	}
///////
	private double setSignAvailabilityPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.signsAvailabilityPerception,1.0);		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.25,1));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.25,1));		
		}
		return signAvailabilityPerception;			
	}
	private double setSignAvailabilityAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.57271336,0.89177495));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.397447502,0.89177495));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.284810744,0.89177495));		
		}
		return signAvailabilityAffective;						
	}
	
	private double setProtectionFromElementsPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));		
		}
		return protectionFromElementsPerception;				
	}
	private double setProtectionFromElementsAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.672863,0.891775));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.324973,0.891775));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.284811,0.891775));		
		}
		return protectionFromElementsAffective;					
	}

////////
	private double setOthersAttitudePerception(){
			double r = rand.nextDouble();
			if(r<=Group1){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
			}else if (r<=Group2){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.25,0.75));
			}else if (r<=Group3){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
			}
			return othersAttitudePerception;					
		}
	private double setOthersAttitudeAffective(){
			double r = rand.nextDouble();
			if(r<=Group1){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.6006628,0.89177496));
			}else if (r<=Group2){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.23786719,0.80371339));
			}else if (r<=Group3){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.75715463,0.891775));
			}
			return othersAttitudeAffective;						
		}
//////
	private double setSecurityPerception(){
			double r = rand.nextDouble();
			if(r<=Group1){		
				setValueToAttribute(Constants.personalSecurityPerception,1.0);
			}else if (r<=Group2){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
			}else if (r<=Group3){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
			}
			return personalSecurityPerception;							
		}
	private double setSecurityAffective(){
			double r = rand.nextDouble();
			if(r<=Group1){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.6959958,0.89177495));					
			}else if (r<=Group2){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.214629616,0.871732927));
			}else if (r<=Group3){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.52818717,0.89177495));
			}
			return personalSecurityAffective;						
		}
//////
	private double setSafetyPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.personalSafetyPerception,1.0);
		}else if (r<=Group2){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}
		return personalSafetyPerception;								
	}
	private double setSafetyAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.67286315,0.89177495));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.254659466,0.89177495));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.32497299,0.89177495));
		}
		return personalSafetyAffective;									
	}
/////
	private double setEaseOfGettingToMainModePerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfGettingToMainModePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.easeOfGettingToMainModePerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfGettingToMainModePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}
		return easeOfGettingToMainModePerception;								
	}
	private double setEaseOfGettingToMainModeAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfGettingToMainModeAffective,RandomHelper.nextDoubleFromTo(0.67286315,0.89177495));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.easeOfGettingToMainModeAffective,RandomHelper.nextDoubleFromTo(0.254659466,0.8364376));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfGettingToMainModeAffective,RandomHelper.nextDoubleFromTo(0.695995823,0.89177495));
		}
		return easeOfGettingToMainModeAffective;									
	}
	
	private double setModeFrequencyPerception(){
	double r = rand.nextDouble();
	if(r<=Group1){		
		setValueToAttribute(Constants.modeFrequencyPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));						
	}else if (r<=Group2){		
		setValueToAttribute(Constants.modeFrequencyPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));						
	}else if (r<=Group3){		
		setValueToAttribute(Constants.modeFrequencyPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));						
	}
	return modeFrequencyPerception;				
}
private double setModeFrequencyAffective(){
	double r = rand.nextDouble();
	if(r<=Group1){		
		setValueToAttribute(Constants.modeFrequencyAffective,RandomHelper.nextDoubleFromTo(0.67286315,0.89177495));
	}else if (r<=Group2){		
		setValueToAttribute(Constants.modeFrequencyAffective,RandomHelper.nextDoubleFromTo(0.32497299,0.89177495));
	}else if (r<=Group3){		
		setValueToAttribute(Constants.modeFrequencyAffective,RandomHelper.nextDoubleFromTo(0.732104412,0.89177495));
	}
	return modeFrequencyAffective;						
}

private double setDistanceToMainModePerception(){
	double r = rand.nextDouble();
	if(r<=Group1){		
		setValueToAttribute(Constants.distanceToMainModePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
	}else if (r<=Group2){		
		setValueToAttribute(Constants.distanceToMainModePerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
	}else if (r<=Group3){		
		setValueToAttribute(Constants.distanceToMainModePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
	}
	return distanceToMainModePerception;								
}
private double setDistanceToMainModeAffective(){
	double r = rand.nextDouble();
	if(r<=Group1){		
		setValueToAttribute(Constants.distanceToMainModeAffective,RandomHelper.nextDoubleFromTo(0.52818717,0.89177495));
	}else if (r<=Group2){		
		setValueToAttribute(Constants.distanceToMainModeAffective,RandomHelper.nextDoubleFromTo(0.254659466,0.836437599));
	}else if (r<=Group3){		
		setValueToAttribute(Constants.distanceToMainModeAffective,RandomHelper.nextDoubleFromTo(0.572713365,0.871732927));
	}
	return distanceToMainModeAffective;									
}

}
