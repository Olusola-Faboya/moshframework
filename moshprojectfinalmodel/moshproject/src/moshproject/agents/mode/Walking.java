package moshproject.agents.mode;

import java.util.ArrayList;
import java.util.List;

import moshproject.common.Constants;
import repast.simphony.random.RandomHelper;

public class Walking extends Mode{

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
//// The demographic data
	private double userOccupation;	
	public double getUserOccupation() {return userOccupation;}
	private double userDistanceRange;		
	public double getUserDistanceRange() {return userDistanceRange;}
//////		
	public Walking() {	
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
		///The attributes Importance
		addAttribute(Constants.efficiencyWeight);
		addAttribute(Constants.conveniencyWeight);
		addAttribute(Constants.safetyWeight);
///The Demography
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

		setEfficiencyWeight();
		setConveniencyWeight();
		setSafetyWeight();
////	
		setUsersDistanceRange();
		setUsersOccupation();
	}
////// set the importance values
	private static final double Group1 = 0.413043; 
	private static final double Group2 = 0.413043+0.369565;
	private static final double Group3 = 0.413043+0.369565+0.217391;
	
	private static final double WtGroup1 = 0.44565; 
	private static final double WtGroup2 = 0.44565+0.380435;
	private static final double WtGroup3 = 0.44565+0.380435+0.17391;
/// Travellers' Occupation
	private static final double OccuGroup1 =0.01087; 
	private static final double OccuGroup2 =OccuGroup1+0.00021;
	private static final double OccuGroup3 = OccuGroup2+0.0543;
	private static final double OccuGroup4 = OccuGroup3+0.924;
	
///Distance travel categories
private static final double DstGroup1 =0.0109 ; 
private static final double DstGroup2 =DstGroup1+0.9891;

//////set the Users Distance and Occupation
private double setUsersOccupation(){
	double r = rand.nextDouble();
	if(r<=OccuGroup1){		
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.PARTTIMESTUDENT);			
	}else if(r<=OccuGroup2){		
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.SENIORMANAGER);
	}else if (r<=OccuGroup3) {
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.OTHERACADEMICS);
	}else if (r<=OccuGroup4){
		setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.FULLTIMESTUDENT);
	}
	return userOccupation;
}

private double setUsersDistanceRange(){
	double r = rand.nextDouble();
	if(r<=DstGroup1){		
		setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.BETFIVEANDTEN);			
	}else if(r<=DstGroup2){		
		setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.LESSTHANFIVE);
	}
	return userDistanceRange;
}
////
	private double setEfficiencyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.583333, 0.983333));
		}else if (r<=WtGroup2) {
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.283333, 0.9));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.0, 1.0));
		}
		return efficiencyWeight;
	}
		
	private double setConveniencyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.787234, 1.0));
		}else if (r<=WtGroup2){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.510638,0.829787));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.0, 0.510638));
		}
		return conveniencyWeight;
	}
	
	private double setSafetyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.675,0.875));
		}else if (r<=WtGroup2){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.375, 0.8));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.0,0.35));
		}
		return safetyWeight;
	}
//////	Set mode attributes P,C,A Values
	private double setReliabilityPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){	
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.0,1.0));
		}
		return reliabilityPerception;						
	}
	private double setReliabilityAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.3974475,0.891775));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.4785422,0.891775));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.3974475,0.891775));
		}
		return reliabilityAffective;							
	}
	
	private double setGettingToDestOnTimePerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.5, 1.0));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.0,1.0));
		}
		return gettingToDestOnTimePerception;		
	}
	private double setGettingToDestOnTimeAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.732104,0.892705));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.5919416,0.892705));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.10200294,0.892705));
		}
		return gettingToDestOnTimeAffective;					
	}

	private double setEaseOfAccessInfoPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if(r<=Group2){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.5,0.75));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.0,1.0));
		}
		return easeOfAccessInfoPerception;		
	}
	private double setEaseOfAccessInfoAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.397447502,0.8917749));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.478542162,0.8917749));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.173191012,0.8917749));
		}
		return easeOfAccessInfoAffective;					
	}
///////
	private double setSignAvailabilityPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.5,1));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.5,1));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.0,1.0));		
		}
		return signAvailabilityPerception;			
	}
	private double setSignAvailabilityAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.7321041,0.89177495));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.4785422,0.89177495));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.1091199,0.89177495));		
		}
		return signAvailabilityAffective;						
	}
	
	private double setProtectionFromElementsPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.0,0.5));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.0, 1.0));		
		}
		return protectionFromElementsPerception;				
	}
	private double setProtectionFromElementsAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.52818717,0.89177495));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.13105418,0.60066282));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.10911986,0.89177495));		
		}
		return protectionFromElementsAffective;					
	}

////////
	private double setOthersAttitudePerception(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
			}else if  (r<=Group2){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
			}else if  (r<=Group3){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.0,0.75));
			}
			return othersAttitudePerception;					
		}
	private double setOthersAttitudeAffective(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.387447502,0.89177495));
			}else if  (r<=Group2){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.471734864,0.89177495));
			}else if  (r<=Group3){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.284810744,0.891774956));
			}
			return othersAttitudeAffective;						
		}
//////
	private double setSecurityPerception(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.personalSecurityPerception, 1.0);
			}else if  (r<=Group2){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
			}else if  (r<=Group3){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.0,0.75));
			}
			return personalSecurityPerception;							
		}
	private double setSecurityAffective(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.73210412,0.89177495));					
			}else if  (r<=Group2){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.47854216,0.803713389));
			}else if  (r<=Group3){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.10911986,0.73210412));
			}
			return personalSecurityAffective;						
		}
//////
	private double setSafetyPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.personalSafetyPerception,1.0);
		}else if (r<=Group2){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.25,0.5));
		}
		return personalSafetyPerception;								
	}
	private double setSafetyAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.77658161,0.89177495));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.47854216,0.89177495));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.28481074,0.77658162));
		}
		return personalSafetyAffective;									
	}
	

}
