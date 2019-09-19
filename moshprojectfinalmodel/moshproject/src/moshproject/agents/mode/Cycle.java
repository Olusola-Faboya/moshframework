
package moshproject.agents.mode;

import java.util.ArrayList;
import java.util.List;

import moshproject.agents.intervener.Intervener;
import moshproject.common.Constants;
import repast.simphony.random.RandomHelper;

public class Cycle extends Mode{

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
	public Cycle() {	
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
///THe Demography
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
///		
		setUsersDistanceRange();
		setUsersOccupation();
	}
	private static final double Group1 =0.31707 ; 
	private static final double Group2 = 0.31707+0.25609;
	private static final double Group3 =0.31707+0.25609+0.15854 ;
	private static final double Group4 = 0.31707+0.25609+0.15854+0.268293;
	
	private static final double WtGroup1 = 0.3415; 
	private static final double WtGroup2 = 0.3415+0.0976;
	private static final double WtGroup3 = 0.3415+0.0976+0.0244;
	private static final double WtGroup4 = 0.3415+0.0976+0.0244+0.5365;
/////Travellers' Occupation category
	private static final double OccuGroup1 =0.0122 ; 
	private static final double OccuGroup2 =OccuGroup1+0.0002;
	private static final double OccuGroup3 = OccuGroup2+0.0488;
	private static final double OccuGroup4 = OccuGroup3+0.0732;
	private static final double OccuGroup5 = OccuGroup4+0.0854;
	private static final double OccuGroup6 = OccuGroup5+0.0976;
	private static final double OccuGroup7 = OccuGroup6+0.634;
///Distance travel categories
	private static final double DstGroup1 =0.0122 ; 
	private static final double DstGroup2 =DstGroup1+0.1829;
	private static final double DstGroup3 = DstGroup2+0.3902;
	private static final double DstGroup4 = DstGroup3+0.4146;
//////set the Users Distance and Occupation	
	private double setUsersOccupation(){
		double r = rand.nextDouble();
		if(r<=OccuGroup1){		
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.SKILLEDMANUAL);			
		}else if(r<=OccuGroup2){		
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.SENIORMANAGER);
		}else if (r<=OccuGroup3) {
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.UNSKILLEDMANUAL);
		}else if (r<=OccuGroup4){
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.MIDDLEMANAGER);
		}else if (r<=OccuGroup5){
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.OTHERACADEMICS);
		}else if (r<=OccuGroup6){
			setValueToAttributeOccupation(Constants.usersOccupation,Constants.Occupation.JUNIORMANAGER);
		}else if (r<=OccuGroup7){
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
			setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.LESSTHANFIVE);
		}else if (r<=DstGroup4){
			setValueToAttributeDistanceRange(Constants.userDistanceRange,Constants.DistanceRange.BETFIVEANDTEN);
		}
		return userDistanceRange;
	}
////// set the importance values
	private double setEfficiencyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.43617, 0.893617));
		}else if (r<=WtGroup2) {
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.43617, 0.829787));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.0, 0.095745));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.efficiencyWeight,RandomHelper.nextDoubleFromTo(0.691489, 1.0));
		}
		return efficiencyWeight;
	}
		
	private double setConveniencyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.641509, 0.830189));
		}else if (r<=WtGroup2){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.188679,0.509434));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.0, 0.301887));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.conveniencyWeight,RandomHelper.nextDoubleFromTo(0.792453, 0.943396));
		}
		return conveniencyWeight;
	}
	
	private double setSafetyWeight(){
		double r = rand.nextDouble();
		if(r<=WtGroup1){		
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.540541,0.864865));
		}else if (r<=WtGroup2){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.0, 0.540541));
		}else if (r<=WtGroup3){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.0,0.27027));
		}else if (r<=WtGroup4){
			setValueToAttribute(Constants.safetyWeight,RandomHelper.nextDoubleFromTo(0.702703,0.864865));
		}
		return safetyWeight;
	}
//////	Set mode attributes P,C,A Values
	private double setReliabilityPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){	
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.25,0.75));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.0,0.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.infoReliabilityPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
		}
		return reliabilityPerception;						
	}
	private double setReliabilityAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.52819,0.80371));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.572713,0.600662));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.572713,0.891775));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.infoReliabilityAffective,RandomHelper.nextDoubleFromTo(0.5281872,0.891775));
		}
		return reliabilityAffective;							
	}
	
	
	private double setGettingToDestOnTimePerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.75, 1.0));
		}else if(r<=Group2){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,0.75);
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,1.0);
		}else if (r<=Group4){		
			setValueToAttribute(Constants.gettingToDestOnTimePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}
		return gettingToDestOnTimePerception;		
	}
	private double setGettingToDestOnTimeAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.732104,0.892705));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.732104,0.892705));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.803713389,0.892705));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.gettingToDestOnTimeAffective,RandomHelper.nextDoubleFromTo(0.52818717,0.892705));
		}
		return gettingToDestOnTimeAffective;					
	}
//////

	private double setEaseOfAccessInfoPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if(r<=Group2){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.0,0.0));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.0,0.0));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.easeOfAccessInfoPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
		}
		return easeOfAccessInfoPerception;		
	}
	private double setEaseOfAccessInfoAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.2546594,0.89177495));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.47856976,0.60066282));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.52818717,0.52818717));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.easeOfAccessInfoAffective,RandomHelper.nextDoubleFromTo(0.47854216,0.89177495));
		}
		return easeOfAccessInfoAffective;					
	}
///////
	private double setParkingSpaceConcernPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.parkingSpaceConcernPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));						
		}else if (r<=Group2){		
			setValueToAttribute(Constants.parkingSpaceConcernPerception,RandomHelper.nextDoubleFromTo(0.0, 1.0));						
		}else if (r<=Group3){		
			setValueToAttribute(Constants.parkingSpaceConcernPerception,RandomHelper.nextDoubleFromTo(0.5, 1.0));						
		}else if (r<=Group4){		
			setValueToAttribute(Constants.parkingSpaceConcernPerception,RandomHelper.nextDoubleFromTo(0.5, 1.0));						
		}
		return parkingSpaceConcernPerception;				
	}
	private double setParkingSpaceConcernAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.parkingSpaceConcernAffective,RandomHelper.nextDoubleFromTo(0.3974475,0.8917749));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.parkingSpaceConcernAffective,RandomHelper.nextDoubleFromTo(0.34766444,0.89177495));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.parkingSpaceConcernAffective,RandomHelper.nextDoubleFromTo(0.79750478,0.89177495));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.parkingSpaceConcernAffective,RandomHelper.nextDoubleFromTo(0.284810744,0.89177495));
		}
		return parkingSpaceConcernAffective;						
	}

	private double setWalkingToDestPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.walkingToDestinationCognitive,0.75);						
		}else if(r<=Group2){		
			setValueToAttribute(Constants.walkingToDestinationPerception,RandomHelper.nextDoubleFromTo(0.75, 1.0));						
		}else if (r<=Group3){		
			setValueToAttribute(Constants.walkingToDestinationPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));						
		}else if (r<=Group4){		
			setValueToAttribute(Constants.walkingToDestinationPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));						
		}
		return walkingToDestinationPerception;				
	}
	private double setWalkingToDestAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.walkingToDestinationAffective,0.80371339);
		}else if (r<=Group2){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.47854216,0.89177496));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.57271337,0.89177496));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.walkingToDestinationAffective,RandomHelper.nextDoubleFromTo(0.36807799,0.89177496));
		}
		return walkingToDestinationAffective;						
	}
	private double setEaseOfGettinOnOffPerception(){
	double r = rand.nextDouble();
	if(r<=Group1){		
		setValueToAttribute(Constants.gettingOnOffModePerception,RandomHelper.nextDoubleFromTo(0.75,1.0));	
		}else if (r<=Group2){		
			setValueToAttribute(Constants.gettingOnOffModePerception,1.0);	
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingOnOffModePerception,1.0);	
		}else if (r<=Group4){		
			setValueToAttribute(Constants.gettingOnOffModePerception,RandomHelper.nextDoubleFromTo(0.75,1.0));	
		}
	return easeOfGettinOnOffPerception;						
	}
	private double setEaseOfGettingOnOffAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.803713389,0.89177495));
		}else if(r<=Group2){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.7321041,0.89177495));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.8717329,0.89177495));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.gettingOnOffModeAffective,RandomHelper.nextDoubleFromTo(0.478542162,0.89177495));
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
		}else if (r<=Group3){		
			setValueToAttribute(Constants.delaysPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.delaysPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
		}
		return delaysPerception;
	}
	private double setDelaysAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.475057938,0.60066282));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.32497299,0.89270506));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.47856976,0.836437599));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.delaysAffective,RandomHelper.nextDoubleFromTo(0.47854216,0.890277518));
		}
		return delaysAffective;
	}
///////
	private double setSignAvailabilityPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.signsAvailabilityPerception,0.75);		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));		
		}else if (r<=Group4){		
			setValueToAttribute(Constants.signsAvailabilityPerception,RandomHelper.nextDoubleFromTo(0.5,1.0));		
		}
		return signAvailabilityPerception;			
	}
	private double setSignAvailabilityAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.signsAvailabilityAffective,0.803713389);		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.32497299,0.836437599));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.77658161,0.803713389));		
		}else if (r<=Group4){		
			setValueToAttribute(Constants.signsAvailabilityAffective,RandomHelper.nextDoubleFromTo(0.77658161,0.89177495));		
		}
		return signAvailabilityAffective;						
	}
	
	private double setProtectionFromElementsPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.0,0.75));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.0,0.75));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.5, 0.75));		
		}else if (r<=Group4){		
			setValueToAttribute(Constants.protectionFromElementsPerception,RandomHelper.nextDoubleFromTo(0.0,1.0));		
		}
		return protectionFromElementsPerception;				
	}
	private double setProtectionFromElementsAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.10911986,0.89177495));		
		}else if (r<=Group2){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.10911986,0.803713389));		
		}else if (r<=Group3){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.478569767,0.803713389));		
		}else if (r<=Group4){		
			setValueToAttribute(Constants.protectionFromElementsAffective,RandomHelper.nextDoubleFromTo(0.32497299,0.89177495));		
		}
		return protectionFromElementsAffective;					
	}

////////
	private double setOthersAttitudePerception(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.othersAttitudePerception,0.5);
			}else if (r<=Group2){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.0,0.75));
			}else if (r<=Group3){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
			}else if (r<=Group4){		
				setValueToAttribute(Constants.othersAttitudePerception,RandomHelper.nextDoubleFromTo(0.5,1.0));
			}
			return othersAttitudePerception;					
		}
	private double setOthersAttitudeAffective(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.591941617,0.600662823));
			}else if (r<=Group2){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.10911986,0.82504977));
			}else if (r<=Group3){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.572713365,0.89177495));
			}else if (r<=Group4){		
				setValueToAttribute(Constants.othersAttitudeAffective,RandomHelper.nextDoubleFromTo(0.52818717,0.89177495));
			}
			return othersAttitudeAffective;						
		}
//////
	private double setSecurityPerception(){
			double r = rand.nextDouble();
			if(r<=Group1){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.25, 1.0));
			}else if (r<=Group2){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.25, 1.0));
			}else if (r<=Group3){		
				setValueToAttribute(Constants.personalSecurityPerception,0.75);
			}else if (r<=Group4){		
				setValueToAttribute(Constants.personalSecurityPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
			}
			return personalSecurityPerception;							
		}
	private double setSecurityAffective(){
			double r = rand.nextDouble();
			if (r<=Group1){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.397447502,0.89177495));					
			}else if (r<=Group2){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.284810744,0.803713389));
			}else if (r<=Group3){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.776581616,0.803713389));
			}else if (r<=Group4){		
				setValueToAttribute(Constants.personalSecurityAffective,RandomHelper.nextDoubleFromTo(0.73210412,0.89177495));
			}
			return personalSecurityAffective;						
		}
//////
	private double setSafetyPerception(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.25,0.75));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.0,0.75));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.personalSafetyPerception,RandomHelper.nextDoubleFromTo(0.75,1.0));
		}
		return personalSafetyPerception;								
	}
	private double setSafetyAffective(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.2546595,0.803713389));
		}else if (r<=Group2){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.1091199,0.8037134));
		}else if (r<=Group3){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.3974475,0.89177495));
		}else if (r<=Group4){		
			setValueToAttribute(Constants.personalSafetyAffective,RandomHelper.nextDoubleFromTo(0.7321041,0.89177495));
		}
		return personalSafetyAffective;									
	}
	
}
