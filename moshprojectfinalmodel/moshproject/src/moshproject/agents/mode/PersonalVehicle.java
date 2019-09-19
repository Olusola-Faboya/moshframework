package moshproject.agents.mode;

import moshproject.common.Constants;
import repast.simphony.random.RandomHelper;

public class PersonalVehicle extends Mode {
	
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
	private double parkingSpaceConcernPerception;		
	public double getParkingSpaceConcernPerception() {
		return parkingSpaceConcernPerception;
		}
	private double parkingSpaceConcernAffective;		
	public double getParkingSpaceConcernAffective() {
		return parkingSpaceConcernAffective;
	}
////		
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
	//// This section define and gets the deciion attributes weighting factors
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
	private double vpReliability;
	
	public double getVpReliability() {
		return vpReliability;
	}

	private double userOccupation;	
	public double getUserOccupation() {return userOccupation;}
	private double userDistanceRange;		
	public double getUserDistanceRange() {return userDistanceRange;}
	//////		
	public PersonalVehicle() {	
//	This section/constructor adds modes attributes from the questionnaire 
		addAttribute(Constants.vpReliability);
///////
		addAttribute(Constants.infoReliabilityPerception);
		addAttribute(Constants.infoReliabilityAffective);	
///	
		addAttribute(Constants.easeOfAccessInfoPerception);
		addAttribute(Constants.easeOfAccessInfoAffective);
///
		addAttribute(Constants.gettingToDestOnTimePerception);
		addAttribute(Constants.gettingToDestOnTimeAffective);
///
//		addAttribute(Constants.modeFrequencyPerception);
//		addAttribute(Constants.modeFrequencyAffective);
///
		addAttribute(Constants.distanceToMainModePerception);
		addAttribute(Constants.distanceToMainModeAffective);
///
		addAttribute(Constants.easeOfGettingToMainModePerception);
		addAttribute(Constants.easeOfGettingToMainModeAffective);
///
		addAttribute(Constants.parkingSpaceConcernPerception);
		addAttribute(Constants.parkingSpaceConcernAffective);
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
		addAttribute(Constants.userDistanceRange);
		addAttribute(Constants.usersOccupation);
///The attributes Importance
		addAttribute(Constants.efficiencyWeight);
		addAttribute(Constants.conveniencyWeight);
		addAttribute(Constants.safetyWeight);
///// Setting up of returning values of Physical, Cognitive and Affective 
		setvpReliability();
//////
		setReliabilityPerception();
		setReliabilityAffective();	
///////		
		setGettingToDestOnTimePerception();
		setGettingToDestOnTimeAffective();
//////			
		setEaseOfAccessInfoPerception();
		setEaseOfAccessInfoAffective();
//////			
		setParkingSpaceConcernPerception();
		setParkingSpaceConcernAffective();
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
		setEfficiencyWeight();
		setConveniencyWeight();
		setSafetyWeight();
////		
		setUsersDistanceRange();
		setUsersOccupation();
	}
//	private void setVpReliability() {
//		 Auto-generated method stub
//		
//	}
//	////// set the importance values
	private static final double Group1 = 0.30864; 
	private static final double Group2 = 0.30864+0.34567;
	private static final double Group3 = 0.30864+0.34567+0.34567;
	
	private static final double WtGroup1 = 0.45679; 
	private static final double WtGroup2 = 0.45679+0.11111;
	private static final double WtGroup3 = 0.45679+0.11111+0.34568;
	private static final double WtGroup4 = 0.45679+0.11111+0.34568+0.08642;
//Travellers' Occupation category
	private static final double OccuGroup1 =0.01235 ; 
	private static final double OccuGroup2 =OccuGroup1+0.00021;
	private static final double OccuGroup3 =OccuGroup2+0.02469;
	private static final double OccuGroup4 = OccuGroup3+0.037;
	private static final double OccuGroup5 = OccuGroup4+0.0864;
	private static final double OccuGroup6 = OccuGroup5+0.0988;
	private static final double OccuGroup7 = OccuGroup6+0.14815;
	private static final double OccuGroup8 = OccuGroup7+0.1605;
	private static final double OccuGroup9 = OccuGroup8+0.4197;
///Distance travel categories
private static final double DstGroup1 =0.06173 ; 
private static final double DstGroup2 =DstGroup1+0.1481;
private static final double DstGroup3 = DstGroup2+0.3580;
private static final double DstGroup4 = DstGroup3+0.4321;
//////set the Users Distance and Occupation
private double setUsersOccupation(){
	double r = rand.nextDouble();
	if(r<=OccuGroup1){		
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.PROFESSOR);			
	}else if(r<=OccuGroup2){		
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.UNSKILLEDMANUAL);
	}else if (r<=OccuGroup3) {
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.PARTTIMESTUDENT);
	}else if (r<=OccuGroup4){
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.SENIORMANAGER);
	}else if (r<=OccuGroup5){
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.SKILLEDMANUAL);
	}else if (r<=OccuGroup6){
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.JUNIORMANAGER);
	}else if (r<=OccuGroup7){
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.OTHERACADEMICS);
	}else if (r<=OccuGroup8){
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.MIDDLEMANAGER);
	}else if (r<=OccuGroup9){
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.FULLTIMESTUDENT);
	}
	return userOccupation;
}

private double setUsersDistanceRange(){
	double r = rand.nextDouble();
	if(r<=DstGroup1){		
		setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.LESSTHANFIVE);			
	}else if(r<=DstGroup2){		
		setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.BETFIVEANDTEN);
	}else if (r<=DstGroup3) {
		setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.GREATERTWENTYFIVE);
	}else if (r<=DstGroup4){
		setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.BETTENANDTWENTYFIVE);
	}
	return userDistanceRange;
}
	private double setEfficiencyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.694118, 0.964706));
		}else if (r<=WtGroup2) {
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.882353, 0.929412));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.447059, 0.847059));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.0, 0.752941));
		}
		return efficiencyWeight;
	}
		
	private double setConveniencyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.5625, 0.75));
		}else if (r<=WtGroup2){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.75,0.875));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.3125, 0.5625));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.125, 0.3125));
		}
		return conveniencyWeight;
	}
	
	private double setSafetyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.545455,0.818182));
		}else if (r<=WtGroup2){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.818182,0.818182));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.545455,0.818182));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.212121,0.545455));
		}
		return safetyWeight;
	}
/////set the values of values and priority
	public double setvpReliability(){
		getValueOfAttribute(Constants.vpReliability);
		return vpReliability;
	}
	
//////	Set mode attributes P,C,A Values
	private double setReliabilityPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){	
			setValueToAttribute(Constants.infoReliabilityPerception,1.0);
		}else if (r<=Group2){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.0,0.875));
		}
		return reliabilityPerception;						
	}
	private double setReliabilityAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.528187,0.891775));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.368078,0.891775));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.397448,0.891775));
		}
		return reliabilityAffective;							
	}
	
	private double setGettingToDestOnTimePerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.75, 1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,1.0);
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}
		return gettingToDestOnTimePerception;		
	}
	private double setGettingToDestOnTimeAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.732104,0.892705));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.799541,0.892705));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.368077,0.892705));
		}
		return gettingToDestOnTimeAffective;					
	}

	private double setEaseOfAccessInfoPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,1.0);
		}else if(r<=Group2){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}
		return easeOfAccessInfoPerception;		
	}
	private double setEaseOfAccessInfoAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.478542,0.8917749));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.324972,0.8917749));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.254659,0.8917749));
		}
		return easeOfAccessInfoAffective;					
	}
///////
	private double setParkingSpaceConcernPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.parkingSpaceConcernPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));						
		}else if (r<=Group2){		
			setValueToAttribute(Constants.parkingSpaceConcernPerception,RandomHelper.nextDoubleFromTo(0.5, 0.75));						
		}else if (r<=Group3){		
			setValueToAttribute(Constants.parkingSpaceConcernPerception,RandomHelper.nextDoubleFromTo(0.0, 0.75));						
		}
		return parkingSpaceConcernPerception;				
	}
	private double setParkingSpaceConcernAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.parkingSpaceConcernAffective,RandomHelper.nextDoubleFromTo(0.3476644,0.892705));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.parkingSpaceConcernAffective,RandomHelper.nextDoubleFromTo(0.5727133,0.884364));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.parkingSpaceConcernAffective,RandomHelper.nextDoubleFromTo(0.1739650,0.865600));
		}
		return parkingSpaceConcernAffective;						
	}

	private double setWalkingToDestPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.walkingToDestinationCognitive,RandomHelper.nextDoubleFromTo(0.5,1.0));						
		}else if(r<=Group2){		
			setValueToAttribute(Constants.walkingToDestinationPerception,RandomHelper.nextDoubleFromTo(0.5, 1.0));						
		}else if (r<=Group3){		
			setValueToAttribute(Constants.walkingToDestinationPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));						
		}
		return walkingToDestinationPerception;				
	}
	private double setWalkingToDestAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.4785421,0.8917749));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.73210412,0.8717329));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.3249729,0.87173292));
		}
		return walkingToDestinationAffective;						
	}
	private double setEaseOfGettinOnOffPerception(){
	double r = rand.nextDouble();
	if(r<=Group1){		
		setValueToAttribute(Constants.gettingOnOffModePerception,RandomHelper.nextDoubleFromTo(0.75,1.0));	
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingOnOffModePerception,RandomHelper.nextDoubleFromTo(0.75,1.0));	
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingOnOffModePerception,1.0);	
		}
	return easeOfGettinOnOffPerception;						
	}
	private double setEaseOfGettingOnOffAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.4785421,0.89177495));
		}else if(r<=Group2){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.7321041,0.89177495));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.32497299,0.871732927));
		}
		return easeOfGettingOnOffAffective;						
	}
//////
	private double setDelaysPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.delaysPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.delaysPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}
		else if (r<=Group3){		
			setValueToAttribute(Constants.delaysPerception,RandomHelper.nextDoubleFromTo(0.0,0.5));
		}
		return delaysPerception;
	}
	private double setDelaysAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.3787784,0.8917749));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.2378671,0.8364375));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.1731910,0.600662));
		}
		return delaysAffective;
	}
///////
	private double setSignAvailabilityPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.5,1));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.75,1));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.5,0.75));		
		}
		return signAvailabilityPerception;			
	}
	private double setSignAvailabilityAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.7321041,0.89177495));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.77658161,0.89177495));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.528187173,0.89177495));		
		}
		return signAvailabilityAffective;						
	}
	
	private double setProtectionFromElementsPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.protectionFromElementsPerception,1.0);		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.protectionFromElementsPerception,1.0);		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.75, 1.0));		
		}
		return protectionFromElementsPerception;				
	}
	private double setProtectionFromElementsAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.871732927,0.89177495));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.protectionFromElementsAffective,0.89177495);		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.77658161,0.89177495));		
		}
		return protectionFromElementsAffective;					
	}

////////
	private double setOthersAttitudePerception(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
			}else if  (r<=Group2){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.5,0.75));
			}else if  (r<=Group3){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.0,1.0));
			}
			return othersAttitudePerception;					
		}
	private double setOthersAttitudeAffective(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.528187173,0.89177495));
			}else if  (r<=Group2){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.551850668,0.89177495));
			}else if  (r<=Group3){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.368077985,0.890277518));
			}
			return othersAttitudeAffective;						
		}
//////
	private double setSecurityPerception(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.75, 1.0));
			}else if  (r<=Group2){		
				setValueToAttribute(Constants.personalSecurityPerception,0.75);
			}else if  (r<=Group3){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
			}
			return personalSecurityPerception;							
		}
	private double setSecurityAffective(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.67286315,0.89177495));					
			}else if  (r<=Group2){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.776581616,0.803713389));
			}else if  (r<=Group3){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.47854216,0.89177495));
			}
			return personalSecurityAffective;						
		}
//////
	private double setSafetyPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}
		return personalSafetyPerception;								
	}
	private double setSafetyAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.77658161,0.803713389));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.77658161,0.89177495));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.77658161,0.89177495));
		}
		return personalSafetyAffective;									
	}
	
}
