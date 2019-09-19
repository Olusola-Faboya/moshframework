package moshproject.agents.passenger;

import java.util.ArrayList;
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
import moshproject.agents.intervener.Intervener;
import moshproject.agents.mode.Cycle;
import moshproject.agents.mode.Mode;
import moshproject.agents.mode.PersonalVehicle;
import moshproject.agents.mode.PublicTransport;
import moshproject.agents.mode.Walking;
import moshproject.common.Constants;
import moshproject.common.FuzzyDecisionVariables;
import moshproject.common.NormalWeigthValues;

public class ModeAttributesPerceptions {
	////This class evaluates passengers' perceptions on mode's attributes 
	// and generates the Values and Priority Measures as the metrics
	Passenger passenger;
	Mode prefferedMode;
	Intervener intervener;
	FuzzyDecisionVariables pca; 
	InfoReliability informationReliability;
	ModeFrequency attrModefrequency;
	ModeTimeliness modeTime;
	EaseOfAccessInformation  easeOfAccessInfor;
	EaseOfGettingTOMainMode easeToMainMode;
	ParkingSpaceConcern parkingConcern;
	EaseOfGettingOnOffMode easeOfGettingOnOffMode;
	ModeDelays modeDelays;
	SignsAvailability availableSigns;
	ProtectionFromElements elementsProtection;
	OtherUsersAttitude modeUsersAttitude;
	ModeSecurity modeUserSecurity;
	ModeSafety modeUserSafety;
	WalkingToDestination walkingToUserDestination;
	DistanceToMainMode userDistanceToMode;
	
	Random rand= new Random();
	
	public Mode getPrefferedMode() {
		return prefferedMode;
	}

	ArrayList<Double> weigthValues;
	private ArrayList<Double>subMajorDecisionAttributesValue;
	
	// This section returns Decision attributes i.e. Efficiency, Comfortability and Safety
	private double modeSafety;			
	public double getModeSafety() {
		return this.modeSafety;
	}
	private double modeEfficiency;	
	public double getModeEfficiency() {
		return modeEfficiency;
	}
	private double modeComfortability;	
	public double getModeComfortability() {
		return modeComfortability;
	}
///Declaration and getters of Decision Attribute Weights	
	private double attrComfortabilityWeight;
	public double getAttrComfortabilityWeight() {
		return attrComfortabilityWeight;
	}	
	private double attrEfficiencyWeight;
	public double getAttrEfficiencyWeight() {
		return attrEfficiencyWeight;
	}
	private double attrSafetyWeight;
	public double getAttrSafetyWeight() {
		return attrSafetyWeight;
	}
	private double attrSocialWeight;	
public double getAttrSocialWeight() {
		return attrSocialWeight;
	}

	///////// Declaration and Getters for mode's attributes
////// this section if for main decision variables PCA
	private double vpReliability;	
	private double vpReliabilityCognitive;		
	private double vpReliabilityAffective;
	
	private double vpJourneyTime;	
	private double vpJourneyTimeCognitive;
	public double getVpJourneyTimeCognitive() {
		return vpJourneyTimeCognitive;
	}
	private double vpJourneyTimeAffective;	
	public double getVpJourneyTimeAffective() {
		return vpJourneyTimeAffective;
	}
	private double vpCostAndValueForMoney;	

	private double vpCostAndValueForMoneyCognitive;	
	public double getVpCostAndValueForMoneyCognitive() {
		return vpCostAndValueForMoneyCognitive;
	}
	private double vpCostAndValueForMoneyAffective;	
	public double getVpCostAndValueForMoneyAffective() {
		return vpCostAndValueForMoneyAffective;
	}
	private double vpPersonalMobility;		
	private double vpPersonalMobilityCognitive;	
	public double getVpPersonalMobilityCognitive() {
		return vpPersonalMobilityCognitive;
	}
	private double vpPersonalMobilityAffective;	
	public double getVpPersonalMobilityAffective() {
		return vpPersonalMobilityAffective;
	}	

	private double vpPersonalMobilityPhysical;	
	public double getVpPersonalMobilityPhysical() {
		return vpPersonalMobilityPhysical;
	}
	private double vpConvenience;		
	private double vpConveniencePhysical;	
	public double getVpConveniencePhysical() {
		return vpConveniencePhysical;	}

	private double vpConvenienceCognitive;
	public double getVpConvenienceCognitive() {
		return vpConvenienceCognitive;
	}
	private double vpConvenienceAffective;	
	public double getVpConvenienceAffective() {
		return vpConvenienceAffective;
	}
	private double vpComfort;	
	public double getVpComfort() {
		return vpComfort;
	}	
	private double vpComfortCognitive;	
	public double getVpComfortCognitive() {
		return vpComfortCognitive;
	}
	private double vpComfortAffective;	
	public double getVpComfortAffective() {
		return vpComfortAffective;
	}
	private double vpComfortPhysical;	
	public double getVpComfortPhysical() {
		return vpComfortPhysical;
	}
	private double vpSecurity;
	public double getVpSecurity() {
		return vpSecurity;
	}
	private double vpSecurityCognitive;	
	public double getVpSecurityCognitive() {
		return vpSecurityCognitive;
	}
	private double vpSecurityAffective;	
	public double getVpSecurityAffective() {
		return vpSecurityAffective;
	}
	private double vpSecurityAffectiveDemand;	
	public double getVpSecurityAffectiveDemand() {
		return vpSecurityAffectiveDemand;
	}
	private double vpSecurityCognitiveDemand;	
	public double getVpSecurityCognitiveDemand() {
		return vpSecurityCognitiveDemand;
	}
//////	
	private double efficiencyWeight;
	private double comfortabilityWeight;
	private double safetyWeight;
	private double socialWeight;
/////	
	private double modeEfficiencyCognitive;
	private double modeEfficiencyAffective;
	private double personalVehReliabilityCognitive;
///	private double privateModeEfficiencyCognitive;
	private double publicTransReliabilityCognitive;
	private double personalVehReliability;
	private double personalCarReliability;
	private double publicTransReliability;
	private double privateEffciciencyWt;
	private double publicEffciciencyWt;
	private double privateSafetyWt;
	private double publicSafetyWt;
	private double privateModeReliability;
	private double publicModeReliability;
	private double privateEaseOfAccessInfo;
	private double publicEaseOfAccessInfo;
	private double privateTimeliness;
	private double publicTimeliness;
	private double privateDelays;
	private double publicDelays;
	private double personalVehJourneyTime;
	private double publicTransJourneyTime;
	private double privateParkingSpaceConcern;
	
	private double personalVehCostAndValue;
	private double publicTransCostAndValue;
	private double privateEaseOfGettingOnOff;
	private double publicEaseOfGettingOnOff;
	private double privateComfortabilityWt;
	private double publicComfortabilityWt;
	private double publicModeFrequency;
	private double personalVehReliabilityCognitiveDemand;
	private double publicTransReliabilityCognitiveDemand;
	private double privateModeEfficiency;
	private double publicModeEfficiency;
	private double publicModeFrequencyCognitive;
	private double publicModeFrequencyAffective;
	private double publicModeReliabilityCognitive;
	private double publicModeReliabilityAffective;
	private double privateModeReliabilityCognitive;
	private double privateModeReliabilityAffective;
	private double privateModeTimelinessCognitive;
	private double publicModeTimelinessCognitive;
	private double publicModeTimelinessAffective;
	private double privateModeTimelinessAffective;
	private double privateModeEaseOfAccessInfoCognitive;
	private double publicModeEaseOfAccessInfoCognitive;
	private double publicModeEaseOfAccessInfoAffective;
	private double privateModeEaseOfAccessInfoAffective;
	private double privateParkingSpaceConcernCognitive;
	private double privateParkingSpaceConcernAffective;
	
	private double privateParkingSpaceConcernPhysical;
	private double privateEaseOfGettingOnOffCognitive;
	private double publicEaseOfGettingOnOffCognitive;
	private double privateEaseOfGettingOnOffAffective;
	private double publicEaseOfGettingOnOffAffective;
	private double privateEaseOfGettingOnOffPhysical;
	private double publicEaseOfGettingOnOffPhysical;
	private double privateDelaysCognitive;
	private double publicDelaysCognitive;
	private double privateDelaysAffective;
	private double publicDelaysAffective;
	private double personalVehReliabilityAffective;
	private double publicTransReliabilityAffective;
	private double personalVehReliabilityAffectiveDemand;
	private double publicTransReliabilityAffectiveDemand;
	private double modeFrequencyPhysical;
	private double modeReliabilityPhysical;
	private double modeTimelinessPhysical;
	private double modeEaseOfAccessInfoPhysical;
	private double delaysPhysical;
	private double signsAvailabiltyPhysical;
	private double protectionFromElementsPhysical;
	private double othersAttitudePhysical;
	private double personalSecurityPhysical;
	private double personalSafetyPhysical;
	private double theVectorEntries;
	private double modeEaseOfGettingToMainMode;
	private double privateEaseOfGettingToMode;
	private double publicEaseOfGettingToMode;
	private double modeEaseOfGettingOnOff;
	private double privateSignsAvailability;
	private double publicSignsAvailability;
	private double privateProtectionFromElements;
	private double publicProtectionFromElements;
	private double otherUserAttitude;
	private double privateOtherUsersAttitude;
	private double publicOtherUsersAttitude;
	private double privatePersonalSecurity;
	private double publicPersonalSecurity;
	private double privatePersonalSafety;
	private double publicPersonalSafety;
	private double publicWalkingToDestination;
	private double privateWalkingToDestination;
	private double privateDistanceToDestination;
	private double publicDistanceToDestination;
	private double privateDistanceToMainMode;
	private double publicDistanceToMainMode;
	private double vpPrivateReliabilityCognitiveDemand;
	private double vpPublicReliabilityCognitiveDemand;
	private double vpPrivateReliabilityAffectiveDemand;
	private double vpPublicTransReliabilityAffectiveDemand;
	private double personalVehJourneyTimeCognitive;
	private double publicTransJourneyTimeCognitive;
	private double vpPrivateJourneyTimeCognitiveDemand;
	private double vpPublicJourneyTimeCognitiveDemand;
	private double personalVehJourneyTimeAffective;
	private double publicTransJourneyTimeAffective;
	private double privateVehJourneyTimeAffectiveDemand;
	private double vpPublicJourneyTimeAffectiveDemand;
	private double personalVehCostAndValueCognitive;
	private double publicTransCostAndValueCognitive;
	private double vpPrivateCostsValueForMoneyCognitiveDemand;
	private double vpPublicTransCostsValueForMoneyCognitiveDemand;
	private double personalVehCostAndValueAffective;
	private double publicTransCostAndValueAffective;
	private double vpPrivateCostsValueForMoneyAffectiveDemand;
	private double vpPublicTransCostsValueForMoneyAffectiveDemand;
	private double privatePersonalMobility;
	private double publicTransPersonalMobility;
	private double privateUserPersonalMobilityCognitive;
	private double publicTransPersonalMobilityCognitive;
	private double vpPrivateUserPersonalMobilityCognitiveDemand;
	private double vpPublicTransPersonalMobilityCognitiveDemand;
	private double privateUserPersonalMobilityAffective;
	private double publicTransPersonalMobilityAffective;
	private double vpPrivateUserPersonalMobilityAffectiveDemand;
	private double vpPublicTransPersonalMobilityAffectiveDemand;
	private double privateUserPersonalMobilityPhysical;
	private double publicTransPersonalMobilityPhysical;
	private double vpPrivateUserPersonalMobilityPhysicalDemand;
	private double vpPublicTransPersonalMobilityPhysicalDemand;
	private double privateUserConvenience;
	private double publicTransConvenience;
	private double privateUserConvenienceCognitive;
	private double publicTransConvenienceCognitive;
	private double vpPrivateUserConvenienceCognitiveDemand;
	private double vpPublicTransConvenienceCognitiveDemand;
	private double privateUserConveniencePhysical;
	private double publicUserConveniencePhysical;
	private double vpPrivateUserConveniencePhysicalDemand;
	private double vpPublicTransConveniencePhysicalDemand;
	private double privateUserConvenienceAffective;
	private double publicTransConvenienceAffective;
	private double vpPrivateUserConvenienceAffectiveDemand;
	private double vpPublicTransConvenienceAffectiveDemand;
	private double privateUserComfort;
	private double publicTransComfort;
	private double publicTransComfortCognitive;
	private double privateUserComfortCognitive;
	private double vpPrivateUserComfortCognitiveDemand;
	private double vpPublicTransComfortCognitiveDemand;
	private double privateUserComfortPhysical;
	private double publicTransComfortPhysical;
	private double vpPrivateUserComfortPhysicalDemand;
	private double vpPublicTransComfortPhysicalDemand;
	private double privateUserComfortAffective;
	private double publicTransComfortAffective;
	private double vpPrivateUserComfortAffectiveDemand;
	private double vpPublicTransComfortAffectiveDemand;
	private double privateVPSecurity;
	private double publicTransVPSecurity;
	private double privateUserVPSecuirtyCognitive;
	private double publicTransVPSecurityCognitive;
	private double vpPrivateUserSecurityCognitiveDemand;
	private double vpPublicTransSecurityCognitiveDemand;
	private double privateUserVPSecuirtyAffective;
	private double publicTransVPSecurityAffective;
	private double vpPrivateUserSecurityAffectiveDemand;
	private double vpPublicTransSecurityAffectiveDemand;
	private double privateModeComfortability;
	private double publicModeComfortability;
	private double privateModeSafety;
	private double publicModeSafety;
	private double firedVPCognitive;
	private int privateReliabilityCognitiveCounts;
	private int publicReliabilityCognitiveCounts;
	private int privateReliabilityUnpleasantCognitiveCounts;
	private int publicReliabilityUnpleasantCognitiveCounts;
	private double vpReliabilityCognitiveCounts;
	private double vpPrivateReliabilityCognitiveCounts;
	private int vpPublicReliabilityCognitiveCounts;
	private double vpPrivateReliabilityCognitiveDemand1;
	private double vpPublicReliabilityAffectiveDemand;
	private double cycleEffciciencyWt;
	private double walkingEffciciencyWt;
	private double cycleSafetyWt;
	private double walkingSafetyWt;
	private double cycleComfortabilityWt;
	private double walkingComfortabilityWt;
	private double cycleReliability;
	private double walkingReliability;
	private double cycleReliabilityCognitive;
	private double walkingReliabilityCognitive;
	private double vpCycleReliabilityCognitiveDemand;
	private double vpWalkingReliabilityCognitiveDemand;
	private double vpCycleReliabilityAffectiveDemand;
	private double cycleReliabilityAffective;
	private double walkingReliabilityAffective;
	private double vpWalkingReliabilityAffectiveDemand;
	private double cycleJourneyTime;
	private double walkingJourneyTime;
	private double cycleCostAndValue;
	private double walkingCostAndValue;
	private double cyclePersonalMobility;
	private double walkingPersonalMobility;
	private double cycleConvenience;
	private double walkingConvenience;
	private double cycleComfort;
	private double walkingComfort;
	private double cycleVPSecurity;
	private double walkingVPSecurity;
	private double cycleModeEfficiency;
	private double walkingModeEfficiency;
	private double cycleModeComfortability;
	private double walkingModeComfortability;
	private double cycleModeSafety;
	private double walkingModeSafety;
	private double cycleUserConvenienceCognitive;
	private double walkingConvenienceCognitive;
	private double vpCycleUserConvenienceCognitiveDemand;
	private double vpWalkingConvenienceCognitiveDemand;
	private double cycleUserConveniencePhysical;
	private double walkingUserConveniencePhysical;
	private double vpCycleUserConveniencePhysicalDemand;
	private double vpWalkingConveniencePhysicalDemand;
	private double cycleUserConvenienceAffective;
	private double walkingConvenienceAffective;
	private double vpWalkingTransConvenienceAffectiveDemand;
	private double vpCycleUserConvenienceAffectiveDemand;
	private double cycleJourneyTimeCognitive;
	private double walkingJourneyTimeCognitive;
	private double vpCycleJourneyTimeCognitiveDemand;
	private double vpWalkingJourneyTimeCognitiveDemand;
	private double cycleJourneyTimeAffective;
	private double walkingJourneyTimeAffective;
	private double vpPrivateVehJourneyTimeAffectiveDemand;
	private double vpCycleJourneyTimeAffectiveDemand;
	private double vpWalkingJourneyTimeAffectiveDemand;
	private double cycleCostAndValueCognitive;
	private double walkingCostAndValueCognitive;
	private double vpWalkingCostsValueForMoneyCognitiveDemand;
	private double vpCycleCostsValueForMoneyCognitiveDemand;
	private double cycleCostAndValueAffective;
	private double walkingCostAndValueAffective;
	private double vpCycleCostsValueForMoneyAffectiveDemand;
	private double vpWalkingCostsValueForMoneyAffectiveDemand;
	private double cyclePersonalMobilityCognitive;
	private double walkingPersonalMobilityCognitive;
	private double vpCyclePersonalMobilityCognitiveDemand;
	private double vpWalkingPersonalMobilityCognitiveDemand;
	private double cyclePersonalMobilityAffective;
	private double walkingPersonalMobilityAffective;
	private double vpCyclePersonalMobilityAffectiveDemand;
	private double vpWalkingPersonalMobilityAffectiveDemand;
	private double cyclePersonalMobilityPhysical;
	private double walkingPersonalMobilityPhysical;
	private double vpCyclePersonalMobilityPhysicalDemand;
	private double vpWalkingPersonalMobilityPhysicalDemand;
	private double cycleComfortCognitive;
	private double walkingComfortCognitive;
	private double vpCycleComfortCognitiveDemand;
	private double vpWalkingComfortCognitiveDemand;
	private double cycleComfortPhysical;
	private double vpCycleComfortPhysicalDemand;
	private double cycleComfortAffective;
	private double walkingComfortAffective;
	private double vpCycleComfortAffectiveDemand;
	private double vpWalkingComfortAffectiveDemand;
	private double cycleVPSecurityCognitive;
	private double walkingVPSecurityCognitive;
	private double vpCycleSecurityCognitiveDemand;
	private double vpWalkingSecurityCognitiveDemand;
	private double cycleVPSecurityAffective;
	private double walkingVPSecurityAffective;
	private double vpCycleSecurityAffectiveDemand;
	private double vpWalkingSecurityAffectiveDemand;
	private double privateModeEfficiencyCognitive;
	private double publicModeEfficiencyCognitive;
	private double cycleModeEfficiencyCognitive;
	private double walkingModeEfficiencyCognitive;
	private double privateModeEfficiencyAffective;
	private double publicModeEfficiencyAffective;
	private double cycleModeEfficiencyAffective;
	private double walkingModeEfficiencyAffective;
	private double modeComfortabilityCognitive;
	private double privateModeComfortabilityCognitive;
	private double publicModeComfortabilityCognitive;
	private double cycleModeComfortabilityCognitive;
	private double walkingModeComfortabilityCognitive;
	private double modeComfortabilityAffective;
	private double privateModeComfortabilityAffective;
	private double publicModeComfortabilityAffective;
	private double walkingModeComfortabilityAffective;
	private double cycleModeComfortabilityAffective;
	private double modeComfortabilityPhysical;
	private double privateModeComfortabilityPhysical;
	private double publicModeComfortabilityPhysical;
	private double cycleModeComfortabilityPhysical;
	private double walkingModeComfortabilityPhysical;
	private double modeSafetyCognitive;
	private double privateModeSafetyCognitive;
	private double publicModeSafetyCognitive;
	private double cycleModeSafetyCognitive;
	private double walkingModeSafetyCognitive;
	private double modeSafetyAffective;
	private double privateModeSafetyAffective;
	private double publicModeSafetyAffective;
	private double cycleModeSafetyAffective;
	private double walkingModeSafetyAffective;
	private double modeSafetyPhysical;
	private double privateModeSafetyPhysical;
	private double publicModeSafetyPhysical;
	private double cycleModeSafetyPhysical;
	private double walkingModeSafetyPhysical;
	private double efficiencyWt;
	private ModeAttributesPerceptions perception;

	
	public ModeAttributesPerceptions(Mode prefferedMode){
		this.prefferedMode = prefferedMode;		
		pca= new FuzzyDecisionVariables();	
		
		informationReliability= new InfoReliability(prefferedMode);
		attrModefrequency = new ModeFrequency(prefferedMode);
		modeTime= new ModeTimeliness(prefferedMode);	
		easeOfAccessInfor =new EaseOfAccessInformation(prefferedMode);
		easeToMainMode =new EaseOfGettingTOMainMode(prefferedMode);
		parkingConcern =new ParkingSpaceConcern(prefferedMode);
		easeOfGettingOnOffMode = new EaseOfGettingOnOffMode(prefferedMode);
		modeDelays=new ModeDelays(prefferedMode);
		availableSigns = new SignsAvailability(prefferedMode);
		elementsProtection = new ProtectionFromElements(prefferedMode);
		modeUsersAttitude=new OtherUsersAttitude(prefferedMode);
		modeUserSecurity = new ModeSecurity(prefferedMode);
		modeUserSafety = new ModeSafety(prefferedMode);
		walkingToUserDestination = new WalkingToDestination(prefferedMode);
		userDistanceToMode = new DistanceToMainMode(prefferedMode);
		///
		normalisedWeight();
		/////
			
		////
		updateModeEfficiencyCognitve();
	//	updateModeSafetyCognitive();
		updateModeComfortabilityCognitive();
	//	updateModeEfficiencyAffective();
		updateModeSafetyCognitive();
		updateModeSafetyAffective();
		updateModeSafetyPhysical();
	//	updateModeSafety();	
		///////////
		getPublicModeEfficiency();
	//	getPublicEfficiencyWt();
		/////////
		evaluateVPReliability();
				
		evaluateVPReliabilityCognitive();
		getVpReliabilityCognitive();
		evaluateVPReliabilityAffective();
		getVpReliabilityAffective();	
		getPersonalVehReliabilityCognitive();
		getPublicTransReliabilityCognitive();	
///////////
		evaluateVPJourneyTime();
		evaluateVPJourneyTimeCognitive();
		evaluateVPJourneyTimeAffective();	
///////////
		evaluateVPCostsValueForMoney();
		evaluateVPCostsValueForMoneyCognitive();
		evaluateVPCostsValueForMoneyAffective();
////////
		evaluateVPConvenience();
		evaluateVPConvenienceCognitive();
		evaluateVPConvenienceAffective();
		evaluateVPConveniencePhysical();
		getCycleUserConvenienceCognitiveDemand();
		getPrivateUserConvenienceCognitiveDemand();
		getCycleUserConvenienceCognitive();
		getPrivateUserConvenienceCognitive();
///////////
		evaluateVPPersonalMobility();
		evaluateVPPersonalMobilityCognitive();
		evaluateVPPersonalMobilityAffective();
		evaluateVPPersonalMobilityPhysical();
////////
		evaluateVPConvenience();
		evaluateVPConvenienceCognitive();
		evaluateVPConvenienceAffective();
		evaluateVPConveniencePhysical();
////////
		evaluateVPComfort();
		evaluateVPComfortCognitive();
		evaluateVPComfortAffective();
		evaluateVPComfortPhysical();
////////
		evaluateVPSecurity();
		evaluateVPSecurityCognitive();
		evaluateVPSecurityAffective();
		
//////////
	
	}
	public void updatePrefferedMode(Mode prefferedMode){
		this.prefferedMode =prefferedMode;
		modeTime.setPrefferedMode(prefferedMode);
		informationReliability.setPrefferedMode(prefferedMode);
		attrModefrequency.setPrefferedMode(prefferedMode);		
		easeOfAccessInfor.setPrefferedMode(prefferedMode);
		easeToMainMode.setPrefferedMode(prefferedMode);
		parkingConcern.setPrefferedMode(prefferedMode);
		easeOfGettingOnOffMode.setPrefferedMode(prefferedMode);
		modeDelays.setPrefferedMode(prefferedMode);
		availableSigns.setPrefferedMode(prefferedMode);
		elementsProtection.setPrefferedMode(prefferedMode);
		modeUsersAttitude.setPrefferedMode(prefferedMode);
		modeUserSecurity.setPrefferedMode(prefferedMode);
		modeUserSafety.setPrefferedMode(prefferedMode);
		walkingToUserDestination.setPrefferedMode(prefferedMode);
		userDistanceToMode.setPrefferedMode(prefferedMode);		
	}
	/// This section evaluate and set the decision attributes weights 
			private double updateEfficiencyWeight() {			
				if (prefferedMode instanceof PersonalVehicle)	{
					attrEfficiencyWeight = prefferedMode.getValueOfAttribute(Constants.efficiencyWeight);				
				}else if (prefferedMode instanceof PublicTransport){
					attrEfficiencyWeight = prefferedMode.getValueOfAttribute(Constants.efficiencyWeight);											
				}else if (prefferedMode instanceof Cycle){
					attrEfficiencyWeight = prefferedMode.getValueOfAttribute(Constants.efficiencyWeight);								
				}else if (prefferedMode instanceof Walking){
					attrEfficiencyWeight = prefferedMode.getValueOfAttribute(Constants.efficiencyWeight);													
				}	
				return attrEfficiencyWeight;
			}	
			private double updateComfortabilityWeight() {			
				if (prefferedMode instanceof PersonalVehicle)	{
					attrComfortabilityWeight = prefferedMode.getValueOfAttribute(Constants.conveniencyWeight);				
				}else if (prefferedMode instanceof PublicTransport){
					attrComfortabilityWeight = prefferedMode.getValueOfAttribute(Constants.conveniencyWeight);											
				}else if (prefferedMode instanceof Cycle){
					attrComfortabilityWeight = prefferedMode.getValueOfAttribute(Constants.conveniencyWeight);								
				}else if (prefferedMode instanceof Walking){
					attrComfortabilityWeight = prefferedMode.getValueOfAttribute(Constants.conveniencyWeight);													
				}				
				return attrComfortabilityWeight;			
			}	
			private double updateSafetyWeight() {			
				if (prefferedMode instanceof PersonalVehicle)	{
					attrSafetyWeight = prefferedMode.getValueOfAttribute(Constants.safetyWeight);				
				}else if (prefferedMode instanceof PublicTransport){
					attrSafetyWeight = prefferedMode.getValueOfAttribute(Constants.safetyWeight);											
				}else if (prefferedMode instanceof Cycle){
					attrSafetyWeight = prefferedMode.getValueOfAttribute(Constants.safetyWeight);								
				}else if (prefferedMode instanceof Walking){
					attrSafetyWeight = prefferedMode.getValueOfAttribute(Constants.safetyWeight);													
				}			
				return attrSafetyWeight;			
			}
			
			private double updateSocialWeight() {				
					attrSocialWeight = rand.nextGaussian()*0.05+0.15;;							
				return attrSocialWeight;			
			}
///////
			private double[] unNormalisedData(){
				 weigthValues = new ArrayList<Double>();
				if (prefferedMode instanceof PublicTransport){					
					weigthValues.add(updateEfficiencyWeight());
					weigthValues.add(updateComfortabilityWeight());
					weigthValues.add(updateSafetyWeight());
					weigthValues.add(updateSocialWeight());
				}else if (prefferedMode instanceof PersonalVehicle){					
					weigthValues.add(updateEfficiencyWeight());
					weigthValues.add(updateComfortabilityWeight());
					weigthValues.add(updateSafetyWeight());
					weigthValues.add(updateSocialWeight());
				}else if (prefferedMode instanceof Cycle){					
					weigthValues.add(updateEfficiencyWeight());
					weigthValues.add(updateComfortabilityWeight());
					weigthValues.add(updateSafetyWeight());
					weigthValues.add(updateSocialWeight());
				}else if (prefferedMode instanceof Walking){
					weigthValues.add(updateEfficiencyWeight());
					weigthValues.add(updateComfortabilityWeight());
					weigthValues.add(updateSafetyWeight());
					weigthValues.add(updateSocialWeight());
				}			
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
				//	System.out.println("List of Normalised weigth"+ allValues[i]);
					efficiencyWeight = allValues[0];
					comfortabilityWeight = allValues[1];
					safetyWeight = allValues[2];
					socialWeight =allValues[3];
				}		
			}
			public double getEfficiencyWeight() {
				return this.efficiencyWeight;
			}			
			public double getComfortabilityWeight() {
				return this.comfortabilityWeight;
			}				
			public double getSafetyWeight() {
				return this.safetyWeight;
			}
			public double getSocialWeight() {
				return this.socialWeight;
			}
			
///////All Modes Efficiency Weights
			public double getEfficiencyWt(){
				if(prefferedMode instanceof PersonalVehicle){
					efficiencyWt = getEfficiencyWeight();
				}else if(prefferedMode instanceof PublicTransport){
					efficiencyWt = getEfficiencyWeight();
				}else if(prefferedMode instanceof Cycle){
					efficiencyWt = getEfficiencyWeight();
				}else if(prefferedMode instanceof Walking){
					efficiencyWt = getEfficiencyWeight();
				}
				return efficiencyWt;
			}
			
//			public void setEffciciencyWt(double effciencyWt) {
//				this.efficiencyWt = effciencyWt;
//			}
//			
			public double getPrivateEfficiencyWt(){
				if(prefferedMode instanceof PersonalVehicle){
					privateEffciciencyWt = getEfficiencyWeight();
				}	else {
					privateEffciciencyWt = 0.0;
				}
				return this.privateEffciciencyWt;
			}
			
			public double getPublicEfficiencyWt(){
				if(prefferedMode instanceof PublicTransport){
					publicEffciciencyWt = getEfficiencyWeight();
				}else {
					publicEffciciencyWt = 0.0;
				}		
				return this.publicEffciciencyWt;
			}
			public double getCycleEfficiencyWt(){
				if(prefferedMode instanceof Cycle){
					cycleEffciciencyWt = getEfficiencyWeight();
				}else {
					cycleEffciciencyWt = 0.0;
				}		
				return this.cycleEffciciencyWt;
			}
			public double getWalkingEfficiencyWt(){
				if(prefferedMode instanceof Walking){
					walkingEffciciencyWt = getEfficiencyWeight();
				}else {
					walkingEffciciencyWt = 0.0;
				}		
				return this.walkingEffciciencyWt;
			}
////All modes Safety Weights
			public double getPrivateSafetyWt(){
				if(prefferedMode instanceof PersonalVehicle){
					privateSafetyWt = getSafetyWeight();
				}		
				return privateSafetyWt;
			}
			public double getPublicSafetyWt(){				
				if(prefferedMode instanceof PublicTransport){
					publicSafetyWt = getSafetyWeight();
				}		
				return publicSafetyWt;
			}
			public double getCycleSafetyWt(){
					if(prefferedMode instanceof Cycle){
						cycleSafetyWt = getSafetyWeight();
					}		
					return cycleSafetyWt;
				}
			public double getWalkingSafetyWt(){					
					if(prefferedMode instanceof Walking){
						walkingSafetyWt = getSafetyWeight();
					}		
					return walkingSafetyWt;
			}
///All modes Comfortability Weights
			public double getPrivateComfortabilityWt(){
				if(prefferedMode instanceof PersonalVehicle){
					privateComfortabilityWt = getComfortabilityWeight();
				}			
				return privateComfortabilityWt;
			}
			public double getPublicComfortabilityWt(){				
				if(prefferedMode instanceof PublicTransport){
					publicComfortabilityWt = getComfortabilityWeight();
				}	
				return publicComfortabilityWt;
			}
			public double getCycleComfortabilityWt(){
				if(prefferedMode instanceof Cycle){
					cycleComfortabilityWt = getComfortabilityWeight();
				}			
				return cycleComfortabilityWt;
			}
			public double getWalkingComfortabilityWt(){				
				if(prefferedMode instanceof Walking){
					walkingComfortabilityWt = getComfortabilityWeight();
				}	
				return walkingComfortabilityWt;
			}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///// This section set the values and Priority, and the components' PCA
/// (1) Values and Priority: Reliability	
			public double evaluateVPReliability(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getPrivateEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(informationReliability.getPrivateModeReliability());				
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getPublicEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(informationReliability.getPublicModeReliability());				
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getCycleEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(informationReliability.getCycleModeReliability());				
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getWalkingEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(informationReliability.getWalkingModeReliability());				
				}			
				vpReliability = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return vpReliability;
			}
			public double getVpReliability() {
				return vpReliability;
			}			
			public void setVpReliability(double vpReliability) {
				this.vpReliability = vpReliability;
			}
			public double getPersonalVehReliability(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehReliability =this.getVpReliability();
				}	
				return personalVehReliability;					
			}
			
			public void setPersonalVehReliability(double r ){
				this.personalVehReliability = r;
			}
		
			public double getPublicTransReliability(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransReliability =this.getVpReliability();
				}		
				return publicTransReliability;					
			}			
			public void setPublicTransReliability(double r ){
				this.publicTransReliability = r;
			}
			public double getCycleReliability(){
				if (prefferedMode instanceof Cycle)	{
					cycleReliability =this.getVpReliability();
				}	
				return cycleReliability;					
			}
			public void setCycleReliability(double r ){
				this.cycleReliability = r;
			}
			public double getWalkingReliability(){
				if (prefferedMode instanceof Walking)	{
					walkingReliability =getVpReliability();
				}		
				return walkingReliability;					
			}		
			public void setWalkingReliability(double r ){
				this.walkingReliability = r;
				System.out.println("the walking Reliability is:"+ this.walkingReliability);
			}
/// (1b) Evaluate the respective Modes Reliability cognitive			
			public double evaluateVPReliabilityCognitive(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredPrivateReliabilityCognitive());				
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPublicEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredPublicReliabilityCognitive());					
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredCycleEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredCycleReliabilityCognitive());					
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredWalkingReliabilityCognitive());					
				}		
				vpReliabilityCognitive = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return vpReliabilityCognitive;				
			}			
			public double getVpReliabilityCognitive() {		
				return vpReliabilityCognitive;
			}
			public double getPersonalVehReliabilityCognitive(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehReliabilityCognitive =this.getVpReliabilityCognitive();
				}		
				return personalVehReliabilityCognitive;				
			}
			public double getPublicTransReliabilityCognitive(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransReliabilityCognitive =this.getVpReliabilityCognitive();
				}		
				return publicTransReliabilityCognitive;					
			}
			public double getCycleReliabilityCognitive(){
				if (prefferedMode instanceof Cycle)	{
					cycleReliabilityCognitive =this.getVpReliabilityCognitive();
				}		
				return cycleReliabilityCognitive;				
			}
			public double getWalkingReliabilityCognitive(){
				if (prefferedMode instanceof Walking)	{
					walkingReliabilityCognitive =this.getVpReliabilityCognitive();
				}		
				return walkingReliabilityCognitive;					
			}
/////////	This section gives respective modes relaibility cognitive Demands			
			public double getVpPrivateReliabilityCognitiveDemand(){
				return vpPrivateReliabilityCognitiveDemand = pca.evaluatePCADemand(getPersonalVehReliabilityCognitive());			
			}			
			public double getVpPublicReliabilityCognitiveDemand(){					
					return vpPublicReliabilityCognitiveDemand= pca.evaluatePCADemand(getPublicTransReliabilityCognitive());
			}
			public double getVpCycleReliabilityCognitiveDemand(){
				return vpCycleReliabilityCognitiveDemand = pca.evaluatePCADemand(getCycleReliabilityCognitive());			
			}			
			public double getVpWalkingReliabilityCognitiveDemand(){					
					return vpWalkingReliabilityCognitiveDemand= pca.evaluatePCADemand(getWalkingReliabilityCognitive());
			}
//The respective modes PCA Counts
//All Cognitive counts
			public int getPrivateVPReliabilityCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoCognitiveCounts()+
							informationReliability.getFiredPrivateReliabilityCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPReliabilityCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoCognitiveCounts()+
					 informationReliability.getFiredPublicReliabilityCognitiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPReliabilityCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoCognitiveCounts()+
							informationReliability.getFiredCycleReliabilityCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPReliabilityCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoCognitiveCounts()+
					 informationReliability.getFiredWalkingReliabilityCognitiveCounts();
				}
				return allCounts;					
			}
/////////////Unpleasant cognitive counts
		public int getPrivateVPReliabilityUnpleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoUnpleasantCognitiveCounts()+
						informationReliability.getFiredPrivateReliabilityUnpleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPReliabilityUnpleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoUnpleasantCognitiveCounts()+
				 informationReliability.getFiredPublicReliabilityUnpleasantCognitiveCounts();
			}
			return allCounts;					
		}
		public int getCycleVPReliabilityUnpleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoUnpleasantCognitiveCounts()+
						informationReliability.getFiredCycleReliabilityUnpleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPReliabilityUnpleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoUnpleasantCognitiveCounts()+
				 informationReliability.getFiredWalkingReliabilityUnpleasantCognitiveCounts();
			}
			return allCounts;					
		}
///Neither PleasantNor Pleasant Counts
		public int getPrivateVPReliabilityNeitherPleasantNorUnpleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
						informationReliability.getFiredPrivateReliabilityNeitherPleasantUnpleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPReliabilityNeitherPleasantNorUnpleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
				 informationReliability.getFiredPublicReliabilityNeitherPleasantUnpleasantCognitiveCounts();
			}
			return allCounts;					
		}	
		public int getCycleVPReliabilityNeitherPleasantNorUnpleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
						informationReliability.getFiredCycleReliabilityNeitherPleasantUnpleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPReliabilityNeitherPleasantNorUnpleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
				 informationReliability.getFiredWalkingReliabilityNeitherPleasantUnpleasantCognitiveCounts();
			}
			return allCounts;					
		}
////Pleasant Counts
		public int getPrivateVPReliabilityPleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoPleasantCognitiveCounts()+
						informationReliability.getFiredPrivateReliabilityPleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPReliabilityPleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoPleasantCognitiveCounts()+
				 informationReliability.getFiredPublicReliabilityPleasantCognitiveCounts();
			}
			return allCounts;					
		}		
		public int getCycleVPReliabilityPleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoPleasantCognitiveCounts()+
						informationReliability.getFiredCycleReliabilityPleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPReliabilityPleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoPleasantCognitiveCounts()+
				 informationReliability.getFiredWalkingReliabilityPleasantCognitiveCounts();
			}
			return allCounts;					
		}		
			
////This section for the  Reliability Affective		
			public double evaluateVPReliabilityAffective(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPrivateEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredPrivateReliabilityAffective());				
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPublicEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredPublicReliabilityAffective());					
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredCycleEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredCycleReliabilityAffective());					
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredWalkingEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredWalkingReliabilityAffective());					
				}		
				vpReliabilityAffective = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);			
				return vpReliabilityAffective;				
			}
			public double getVpReliabilityAffective() {			
				return vpReliabilityAffective;	
			}
			public double getPersonalVehReliabilityAffective(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehReliabilityAffective =this.getVpReliabilityAffective();
				}			
				return personalVehReliabilityAffective;					
			}
			public double getPublicTransReliabilityAffective(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransReliabilityAffective =this.getVpReliabilityAffective();
				}		
				return publicTransReliabilityAffective;					
			}
			public double getCycleReliabilityAffective(){
				if (prefferedMode instanceof Cycle)	{
					cycleReliabilityAffective =this.getVpReliabilityAffective();
				}			
				return cycleReliabilityAffective;					
			}
			public double getWalkingReliabilityAffective(){
				if (prefferedMode instanceof Walking)	{
					walkingReliabilityAffective =this.getVpReliabilityAffective();
				}		
				return walkingReliabilityAffective;					
			}

//// All Modes Affective Counts
			public int getPrivateVPReliabilityAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoAffectiveCounts()+
							informationReliability.getFiredPrivateReliabilityAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPReliabilityAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoAffectiveCounts()+
					 informationReliability.getFiredPublicReliabilityAffectiveCounts();
				}
				return allCounts;					
			}	
			public int getCycleVPReliabilityAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Cycle)	{
					allCounts=easeOfAccessInfor.getFiredCycleEaseOfAccessInfoAffectiveCounts()+
					 informationReliability.getFiredCycleReliabilityAffectiveCounts();
				}
				return allCounts;					
			}	
			public int getWalkingVPReliabilityAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					allCounts= easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoAffectiveCounts()+
							informationReliability.getFiredWalkingReliabilityAffectiveCounts();					
				}
				return allCounts;	
			}
			
/////UnPleasant Affective counts
			public int getPrivateVPReliabilityUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoUnpleasantAffectiveCounts()+
							informationReliability.getFiredPrivateReliabilityUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPReliabilityUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoUnpleasantAffectiveCounts()+
					 informationReliability.getFiredPublicReliabilityUnpleasantAffectiveCounts();
				}	
				return allCounts;					
			}
			public int getCycleVPReliabilityUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoUnpleasantAffectiveCounts()+
							informationReliability.getFiredCycleReliabilityUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPReliabilityUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoUnpleasantAffectiveCounts()+
					 informationReliability.getFiredWalkingReliabilityUnpleasantAffectiveCounts();
				}	
				return allCounts;					
			}
/////NEitherPleasant Nor UnPlaesant
			public int getPrivateVPReliabilityNeitherPleasantNorUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
							informationReliability.getFiredPrivateReliabilityNeitherPleasantUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPReliabilityNeitherPleasantNorUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
					 informationReliability.getFiredPublicReliabilityNeitherPleasantNorUnpleasantAffectiveCounts();
				}
				return allCounts;					
			}	
			public int getCycleVPReliabilityNeitherPleasantNorUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
							informationReliability.getFiredCycleReliabilityNeitherPleasantUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPReliabilityNeitherPleasantNorUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
					 informationReliability.getFiredWalkingReliabilityNeitherPleasantNorUnpleasantAffectiveCounts();
				}
				return allCounts;					
			}
///////Pleasant
			public int getPrivateVPReliabilityPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoPleasantAffectiveCounts()+
							informationReliability.getFiredPrivateReliabilityPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPReliabilityPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoPleasantAffectiveCounts()+
					 informationReliability.getFiredPublicReliabilityPleasantAffectiveCounts();
				}
				return allCounts;					
			}	
			public int getCycleVPReliabilityPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoPleasantAffectiveCounts()+
							informationReliability.getFiredCycleReliabilityPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPReliabilityPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoPleasantAffectiveCounts()+
					 informationReliability.getFiredWalkingReliabilityPleasantAffectiveCounts();
				}
				return allCounts;					
			}			

////THe Affective Demands			
			public double getVpPrivateReliabilityAffectiveDemand(){
				vpPrivateReliabilityAffectiveDemand = pca.evaluatePCADemand(getPersonalVehReliabilityAffective());			
				return vpPrivateReliabilityAffectiveDemand ;
			}			
			public double getVpPublicReliabilityAffectiveDemand(){
				vpPublicReliabilityAffectiveDemand = pca.evaluatePCADemand(getPublicTransReliabilityAffective());			
				return vpPublicReliabilityAffectiveDemand ;
			}
			public double getVpCycleReliabilityAffectiveDemand(){
				vpCycleReliabilityAffectiveDemand = pca.evaluatePCADemand(getCycleReliabilityAffective());			
				return vpCycleReliabilityAffectiveDemand ;
			}			
			public double getVpWalkingReliabilityAffectiveDemand(){
				vpWalkingReliabilityAffectiveDemand = pca.evaluatePCADemand(getWalkingReliabilityAffective());			
				return vpWalkingReliabilityAffectiveDemand ;
			}
		
//// (2) Values and Priority: Journey Time
/////////////////////////		
			public double evaluateVPJourneyTime(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getPrivateEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(informationReliability.getPrivateModeReliability());	
					subMajorDecisionAttributesValue.add(modeTime.getPrivateTimeliness());	
					subMajorDecisionAttributesValue.add(modeDelays.getPrivateDelays());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getPublicEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(informationReliability.getPublicModeReliability());
					subMajorDecisionAttributesValue.add(modeTime.getPublicTimeliness());
					subMajorDecisionAttributesValue.add(modeDelays.getPublicDelays());
					subMajorDecisionAttributesValue.add(attrModefrequency.getPublicModeFrequency());
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getCycleEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(informationReliability.getCycleModeReliability());
					subMajorDecisionAttributesValue.add(modeTime.getCycleTimeliness());
					subMajorDecisionAttributesValue.add(modeDelays.getCycleDelays());
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getWalkingEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(informationReliability.getWalkingModeReliability());
					subMajorDecisionAttributesValue.add(modeTime.getWalkingTimeliness());					
				}			
				vpJourneyTime = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return vpJourneyTime;	
			}	
			public void setVpJourneyTime(double vpJourneyTime) {
				this.vpJourneyTime = vpJourneyTime;
			}
			public double getVpJourneyTime() {
				return this.vpJourneyTime;
			}
			public double getPersonalVehJourneyTime(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehJourneyTime =this.getVpJourneyTime();
				}		
				return personalVehJourneyTime;					
			}				
			public void setPersonalVehJourneyTime(double r ){
				this.personalVehJourneyTime = r;
			}
			
			public double getPublicTransJourneyTime(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransJourneyTime =this.getVpJourneyTime();
				}			
				return publicTransJourneyTime;					
			}
			public void setPublicTransJourneyTime(double r ){
				this.publicTransJourneyTime = r;
			}
			public double getCycleJourneyTime(){
				if (prefferedMode instanceof Cycle)	{
					cycleJourneyTime =this.getVpJourneyTime();
				}		
				return cycleJourneyTime;					
			}	
			public void setCycleJourneyTime(double r ){
				this.cycleJourneyTime = r;
			}
			public double getWalkingJourneyTime(){
				if (prefferedMode instanceof Walking)	{
					walkingJourneyTime =this.getVpJourneyTime();
				}			
				return walkingJourneyTime;					
			}
			public void setWalkingJourneyTime(double r ){
				this.walkingJourneyTime = r;
			}
//////Journey Cognitive
			public double evaluateVPJourneyTimeCognitive(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredPrivateReliabilityCognitive());	
					subMajorDecisionAttributesValue.add(modeTime.getFiredPrivateTimelinessCognitive());	
					subMajorDecisionAttributesValue.add(modeDelays.getFiredPrivateDelaysCognitive());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPublicEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredPublicReliabilityCognitive());
					subMajorDecisionAttributesValue.add(modeTime.getFiredPublicTimelinessCognitive());
					subMajorDecisionAttributesValue.add(modeDelays.getFiredPublicDelaysCognitive());
					subMajorDecisionAttributesValue.add(attrModefrequency.getFiredPublicFrequencyCognitive());
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredCycleEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredCycleReliabilityCognitive());
					subMajorDecisionAttributesValue.add(modeTime.getFiredCycleTimelinessCognitive());
					subMajorDecisionAttributesValue.add(modeDelays.getFiredCycleDelaysCognitive());				
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredWalkingReliabilityCognitive());
					subMajorDecisionAttributesValue.add(modeTime.getFiredWalkingTimelinessCognitive());					
				}			
				return vpJourneyTimeCognitive = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
			}
			public double getPersonalVehJourneyTimeCognitive(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehJourneyTimeCognitive =this.getVpJourneyTimeCognitive();
				}	
				return personalVehJourneyTimeCognitive;					
			}
			public double getPublicTransJourneyTimeCognitive(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransJourneyTimeCognitive =this.getVpJourneyTimeCognitive();
				}	
				return publicTransJourneyTimeCognitive;					
			}
			public double getCycleJourneyTimeCognitive(){
				if (prefferedMode instanceof Cycle)	{
					cycleJourneyTimeCognitive =this.getVpJourneyTimeCognitive();
				}	
				return cycleJourneyTimeCognitive;					
			}
			public double getWalkingJourneyTimeCognitive(){
				if (prefferedMode instanceof Walking)	{
					walkingJourneyTimeCognitive =this.getVpJourneyTimeCognitive();
				}	
				return walkingJourneyTimeCognitive;					
			}
	//////////	
			public double getVpPrivateJourneyTimeCognitiveDemand(){
				return vpPrivateJourneyTimeCognitiveDemand = pca.evaluatePCADemand(getPersonalVehJourneyTimeCognitive());	
			}
			public double getVpPublicJourneyTimeCognitiveDemand(){			
				return vpPublicJourneyTimeCognitiveDemand=pca.evaluatePCADemand(getPublicTransJourneyTimeCognitive());
			}
			public double getVpCycleJourneyTimeCognitiveDemand(){			
				return vpCycleJourneyTimeCognitiveDemand=pca.evaluatePCADemand(getCycleJourneyTimeCognitive());
			}
			public double getVpWalkingJourneyTimeCognitiveDemand(){			
				return vpWalkingJourneyTimeCognitiveDemand=pca.evaluatePCADemand(getWalkingJourneyTimeCognitive());
			}

//All Journey Time Cognitive counts
			public int getPrivateVPJourneyTimeCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoCognitiveCounts()+
							informationReliability.getFiredPrivateReliabilityCognitiveCounts()+
							modeTime.getFiredPrivateTimelinessCognitiveCounts()+
							modeDelays.getFiredPrivateDelaysCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPJourneyTimeCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoCognitiveCounts()+
					 informationReliability.getFiredPublicReliabilityCognitiveCounts()+
					 modeTime.getFiredPublicTimelinessCognitiveCounts()+
					 modeDelays.getFiredPublicDelaysCognitiveCounts()+
					 attrModefrequency.getFiredPublicFrequencyCognitiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPJourneyTimeCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoCognitiveCounts()+
							informationReliability.getFiredCycleReliabilityCognitiveCounts()+
							modeTime.getFiredCycleTimelinessCognitiveCounts()+
							modeDelays.getFiredCycleDelaysCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPJourneyTimeCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoCognitiveCounts()+
					 informationReliability.getFiredWalkingReliabilityCognitiveCounts()+
					 modeTime.getFiredWalkingTimelinessCognitiveCounts();
				}
				return allCounts;					
			}
/////////////Unpleasant cognitive counts
		public int getPrivateVPJourneyTimeUnpleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoUnpleasantCognitiveCounts()+
						informationReliability.getFiredPrivateReliabilityUnpleasantCognitiveCounts()+
						+ modeTime.getFiredPrivateTimelinessUnpleasantCognitiveCounts()+
							modeDelays.getFiredPrivateDelaysUnpleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPJourneyTimeUnpleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoUnpleasantCognitiveCounts()+
				 informationReliability.getFiredPublicReliabilityUnpleasantCognitiveCounts()+
				 +modeTime.getFiredPublicTimelinessUnpleasantCognitiveCounts()+
				  modeDelays.getFiredPublicDelaysUnpleasantCognitiveCounts()+
				  attrModefrequency.getFiredPublicFrequencyUnpleasantCognitiveCounts();
			}
			return allCounts;					
		}
		public int getCycleVPJourneyTimeUnpleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoUnpleasantCognitiveCounts()+
						informationReliability.getFiredCycleReliabilityUnpleasantCognitiveCounts()+
						modeTime.getFiredCycleTimelinessUnpleasantCognitiveCounts()+
						modeDelays.getFiredCycleDelaysUnpleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPJourneyTimeUnpleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoUnpleasantCognitiveCounts()+
				 informationReliability.getFiredWalkingReliabilityUnpleasantCognitiveCounts()+
				 modeTime.getFiredWalkingTimelinessUnpleasantCognitiveCounts();
			}
			return allCounts;					
		}		
/////Neither Pleasant Nor Unpleasant Counts			
		public int getPrivateVPJourneyTimeNeitherNorPleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
						informationReliability.getFiredPrivateReliabilityNeitherPleasantUnpleasantCognitiveCounts()+
						+ modeTime.getFiredPrivateTimelinessNeitherNorPleasantCognitiveCounts()+
							modeDelays.getFiredPrivateDelaysNeitherNorPleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPJourneyTimeNeitherNorPleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
				 informationReliability.getFiredPublicReliabilityNeitherPleasantUnpleasantCognitiveCounts()+
				 +modeTime.getFiredPublicTimelinessNeitherNorPleasantCognitiveCounts()+
				  modeDelays.getFiredPublicDelaysNeitherNorPleasantCognitiveCounts()+
				  attrModefrequency.getFiredPublicFrequencyNeitherNorPleasantCognitiveCounts();
			}
			return allCounts;					
		}
		public int getCycleVPJourneyTimeNeitherNorPleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
						informationReliability.getFiredCycleReliabilityNeitherPleasantUnpleasantCognitiveCounts()+
						modeTime.getFiredCycleTimelinessNeitherNorPleasantCognitiveCounts()+
						modeDelays.getFiredCycleDelaysNeitherNorPleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPJourneyTimeNeitherNorPleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
				 informationReliability.getFiredWalkingReliabilityNeitherPleasantUnpleasantCognitiveCounts()+
				 modeTime.getFiredWalkingTimelinessNeitherNorPleasantCognitiveCounts();
			}
			return allCounts;					
		}
//////Pleasant Counts
		public int getPrivateVPJourneyTimePleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoPleasantCognitiveCounts()+
						informationReliability.getFiredPrivateReliabilityPleasantCognitiveCounts()+
						+ modeTime.getFiredPrivateTimelinessPleasantCognitiveCounts()+
							modeDelays.getFiredPrivateDelaysPleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPJourneyTimePleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoPleasantCognitiveCounts()+
				 informationReliability.getFiredPublicReliabilityPleasantCognitiveCounts()+
				 +modeTime.getFiredPublicTimelinessPleasantCognitiveCounts()+
				  modeDelays.getFiredPublicDelaysPleasantCognitiveCounts()+
				  attrModefrequency.getFiredPublicFrequencyPleasantCognitiveCounts();
			}
			return allCounts;					
		}
		public int getCycleVPJourneyTimePleasantCognitiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoPleasantCognitiveCounts()+
						informationReliability.getFiredCycleReliabilityPleasantCognitiveCounts()+
						modeTime.getFiredCycleTimelinessPleasantCognitiveCounts()+
						modeDelays.getFiredCycleDelaysPleasantCognitiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPJourneyTimePleasantCognitiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoPleasantCognitiveCounts()+
				 informationReliability.getFiredWalkingReliabilityPleasantCognitiveCounts()+
				 modeTime.getFiredWalkingTimelinessPleasantCognitiveCounts();
			}
			return allCounts;					
		}	
///////
			public double evaluateVPJourneyTimeAffective(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPrivateEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredPrivateReliabilityAffective());	
					subMajorDecisionAttributesValue.add(modeTime.getFiredPrivateTimelinessAffective());	
					subMajorDecisionAttributesValue.add(modeDelays.getFiredPrivateDelaysAffective());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPublicEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredPublicReliabilityAffective());
					subMajorDecisionAttributesValue.add(modeTime.getFiredPublicTimelinessAffective());
					subMajorDecisionAttributesValue.add(modeDelays.getFiredPublicDelaysAffective());
					subMajorDecisionAttributesValue.add(attrModefrequency.getFiredPublicFrequencyAffective());
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredCycleEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredCycleReliabilityAffective());
					subMajorDecisionAttributesValue.add(modeTime.getFiredCycleTimelinessAffective());
					subMajorDecisionAttributesValue.add(modeDelays.getFiredCycleDelaysAffective());				
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredWalkingEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(informationReliability.getFiredWalkingReliabilityAffective());
					subMajorDecisionAttributesValue.add(modeTime.getFiredWalkingTimelinessAffective());				
				}	
				vpJourneyTimeAffective = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return vpJourneyTimeAffective;				
			}
			public double getPersonalVehJourneyTimeAffective(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehJourneyTimeAffective =this.getVpJourneyTimeAffective();
				}			
				return personalVehJourneyTimeAffective;					
			}			
			public double getPublicTransJourneyTimeAffective(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransJourneyTimeAffective =this.getVpJourneyTimeAffective();
				}			
				return publicTransJourneyTimeAffective;					
			}
			public double getCycleJourneyTimeAffective(){
				if (prefferedMode instanceof Cycle)	{
					cycleJourneyTimeAffective =this.getVpJourneyTimeAffective();
				}			
				return cycleJourneyTimeAffective;					
			}
			public double getWalkingJourneyTimeAffective(){
				if (prefferedMode instanceof Walking)	{
					walkingJourneyTimeAffective =this.getVpJourneyTimeAffective();
				}			
				return walkingJourneyTimeAffective;					
			}
//////////Demands
			public double getVpPrivateJourneyTimeAffectiveDemand(){					
				return vpPrivateVehJourneyTimeAffectiveDemand =pca.evaluatePCADemand(getPersonalVehJourneyTimeAffective());
			}			
			public double getVpPublicJourneyTimeAffectiveDemand(){
				return vpPublicJourneyTimeAffectiveDemand =pca.evaluatePCADemand(getPublicTransJourneyTimeAffective());
			}
			public double getVpCycleJourneyTimeAffectiveDemand(){
				return vpCycleJourneyTimeAffectiveDemand =pca.evaluatePCADemand(getCycleJourneyTimeAffective());
			}
			public double getVpWalkingJourneyTimeAffectiveDemand(){
				return vpWalkingJourneyTimeAffectiveDemand =pca.evaluatePCADemand(getWalkingJourneyTimeAffective());
			}
//////////JourneyTime Affective counts
			public int getPrivateVPJourneyTimeAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoAffectiveCounts()+
							informationReliability.getFiredPrivateReliabilityAffectiveCounts()+
							modeTime.getFiredPrivateTimelinessAffectiveCounts()+
							modeDelays.getFiredPrivateDelaysAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPJourneyTimeAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoAffectiveCounts()+
					 informationReliability.getFiredPublicReliabilityAffectiveCounts()+
					 modeTime.getFiredPublicTimelinessAffectiveCounts()+
					 modeDelays.getFiredPublicDelaysAffectiveCounts()+
					 attrModefrequency.getFiredPublicFrequencyAffectiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPJourneyTimeAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoAffectiveCounts()+
							informationReliability.getFiredCycleReliabilityAffectiveCounts()+
							modeTime.getFiredCycleTimelinessAffectiveCounts()+
							modeDelays.getFiredCycleDelaysAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPJourneyTimeAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoAffectiveCounts()+
					 informationReliability.getFiredWalkingReliabilityAffectiveCounts()+
					 modeTime.getFiredWalkingTimelinessAffectiveCounts();
				}
				return allCounts;					
			}
/////////////Unpleasant cognitive counts
		public int getPrivateVPJourneyTimeUnpleasantAffectiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoUnpleasantAffectiveCounts()+
						informationReliability.getFiredPrivateReliabilityUnpleasantAffectiveCounts()+
						+ modeTime.getFiredPrivateTimelinessUnpleasantAffectiveCounts()+
							modeDelays.getFiredPrivateDelaysUnpleasantAffectiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPJourneyTimeUnpleasantAffectiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoUnpleasantAffectiveCounts()+
				 informationReliability.getFiredPublicReliabilityUnpleasantAffectiveCounts()+
				 +modeTime.getFiredPublicTimelinessUnpleasantAffectiveCounts()+
				  modeDelays.getFiredPublicDelaysUnpleasantAffectiveCounts()+
				  attrModefrequency.getFiredPublicFrequencyUnpleasantAffectiveCounts();
			}
			return allCounts;					
		}
		public int getCycleVPJourneyTimeUnpleasantAffectiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoUnpleasantAffectiveCounts()+
						informationReliability.getFiredCycleReliabilityUnpleasantAffectiveCounts()+
						modeTime.getFiredCycleTimelinessUnpleasantAffectiveCounts()+
						modeDelays.getFiredCycleDelaysUnpleasantAffectiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPJourneyTimeUnpleasantAffectiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoUnpleasantAffectiveCounts()+
				 informationReliability.getFiredWalkingReliabilityUnpleasantAffectiveCounts()+
				 modeTime.getFiredWalkingTimelinessUnpleasantAffectiveCounts();
			}
			return allCounts;					
		}		
/////Neither Pleasant Nor Unpleasant Counts			
		public int getPrivateVPJourneyTimeNeitherNorPleasantAffectiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
						informationReliability.getFiredPrivateReliabilityNeitherPleasantNorUnpleasantAffectiveCounts()+
						+ modeTime.getFiredPrivateTimelinessNeitherNorPleasantAffectiveCounts()+
							modeDelays.getFiredPrivateDelaysNeitherNorPleasantAffectiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPJourneyTimeNeitherNorPleasantAffectiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
				 informationReliability.getFiredPublicReliabilityNeitherPleasantNorUnpleasantAffectiveCounts()+
				 +modeTime.getFiredPublicTimelinessNeitherNorPleasantAffectiveCounts()+
				  modeDelays.getFiredPublicDelaysNeitherNorPleasantAffectiveCounts()+
				  attrModefrequency.getFiredPublicFrequencyNeitherNorPleasantAffectiveCounts();
			}
			return allCounts;					
		}
		public int getCycleVPJourneyTimeNeitherNorPleasantAffectiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
						informationReliability.getFiredCycleReliabilityNeitherPleasantNorUnpleasantAffectiveCounts()+
						modeTime.getFiredCycleTimelinessNeitherNorPleasantAffectiveCounts()+
						modeDelays.getFiredCycleDelaysNeitherNorPleasantAffectiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPJourneyTimeNeitherNorPleasantAffectiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
				 informationReliability.getFiredWalkingReliabilityNeitherPleasantNorUnpleasantAffectiveCounts()+
				 modeTime.getFiredWalkingTimelinessNeitherNorPleasantAffectiveCounts();
			}
			return allCounts;					
		}
//////Pleasant Counts
		public int getPrivateVPJourneyTimePleasantAffectiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof PersonalVehicle)	{	
				allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoPleasantAffectiveCounts()+
						informationReliability.getFiredPrivateReliabilityPleasantAffectiveCounts()+
						+ modeTime.getFiredPrivateTimelinessPleasantAffectiveCounts()+
							modeDelays.getFiredPrivateDelaysPleasantAffectiveCounts();						
			}
			return allCounts;	
		}
		public int getPublicVPJourneyTimePleasantAffectiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof PublicTransport)	{
				allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoPleasantAffectiveCounts()+
				 informationReliability.getFiredPublicReliabilityPleasantAffectiveCounts()+
				 +modeTime.getFiredPublicTimelinessPleasantAffectiveCounts()+
				  modeDelays.getFiredPublicDelaysPleasantAffectiveCounts()+
				  attrModefrequency.getFiredPublicFrequencyPleasantAffectiveCounts();
			}
			return allCounts;					
		}
		public int getCycleVPJourneyTimePleasantAffectiveCounts(){
			int allCounts =0;				
			if (prefferedMode instanceof Cycle)	{	
				allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoPleasantAffectiveCounts()+
						informationReliability.getFiredCycleReliabilityPleasantAffectiveCounts()+
						modeTime.getFiredCycleTimelinessPleasantAffectiveCounts()+
						modeDelays.getFiredCycleDelaysPleasantAffectiveCounts();						
			}
			return allCounts;	
		}
		public int getWalkingVPJourneyTimePleasantAffectiveCounts(){
			int allCounts =0;		
			if (prefferedMode instanceof Walking)	{
				allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoPleasantAffectiveCounts()+
				 informationReliability.getFiredWalkingReliabilityPleasantAffectiveCounts()+
				 modeTime.getFiredWalkingTimelinessPleasantAffectiveCounts();
			}
			return allCounts;					
		}		
/////////(3) Values and Priority: Costs and Value for money/			
			public double evaluateVPCostsValueForMoney(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{					
					subMajorDecisionAttributesValue.add(modeTime.getPrivateTimeliness());	
					subMajorDecisionAttributesValue.add(parkingConcern.getPrivateParkingSpaceConcern());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(modeTime.getPublicTimeliness());					
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(modeTime.getCycleTimeliness());	
					subMajorDecisionAttributesValue.add(parkingConcern.getCycleParkingSpaceConcern());	
				}	else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(modeTime.getWalkingTimeliness());					
				}				
				vpCostAndValueForMoney = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return vpCostAndValueForMoney;				
			}			
			public void setVpCostAndValueForMoney(double r) {
				this.vpCostAndValueForMoney = r;
			}
			public double getVpCostAndValueForMoney() {
				return this.vpCostAndValueForMoney;
			}			
			public double getPersonalVehCostAndValueForMoney(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehCostAndValue =this.getVpCostAndValueForMoney();
				}		
				return personalVehCostAndValue;					
			}			
			public double getPersonalVehCostAndValue() {
				return personalVehCostAndValue;
			}
			public void setPersonalVehCostAndValue(double r) {
				this.personalVehCostAndValue = r;
			}
			public double getPublicTransCostAndValueForMoney(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransCostAndValue =this.getVpCostAndValueForMoney();
				}			
				return publicTransCostAndValue;					
			}	
			public double getPublicTransCostAndValue() {
				return publicTransCostAndValue;
			}
			public void setPublicTransCostAndValue(double r) {
				this.publicTransCostAndValue = r;
			}
			public double getCycleCostAndValueForMoney(){
				if (prefferedMode instanceof Cycle)	{
					cycleCostAndValue =this.getVpCostAndValueForMoney();
				}		
				return cycleCostAndValue;					
			}			
			public double getCycleCostAndValue() {
				return cycleCostAndValue;
			}
			public void setCycleCostAndValue(double r) {
				this.cycleCostAndValue = r;
			}
			public double getWalkingCostAndValueForMoney(){
				if (prefferedMode instanceof Walking)	{
					walkingCostAndValue =this.getVpCostAndValueForMoney();
				}			
				return walkingCostAndValue;					
			}	
			public double getWalkingCostAndValue() {
				return walkingCostAndValue;
			}
			public void setWalkingCostAndValue(double r) {
				this.walkingCostAndValue = r;
			}
///////
			public double evaluateVPCostsValueForMoneyCognitive(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{					
					subMajorDecisionAttributesValue.add(modeTime.getFiredPrivateTimelinessCognitive());	
					subMajorDecisionAttributesValue.add(parkingConcern.getFiredPrivateParkingSpaceConcernCognitive());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(modeTime.getFiredPublicTimelinessCognitive());					
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(modeTime.getFiredCycleTimelinessCognitive());
					subMajorDecisionAttributesValue.add(parkingConcern.getFiredCycleParkingSpaceConcernCognitive());	
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(modeTime.getFiredWalkingTimelinessCognitive());					
				}			
				vpCostAndValueForMoneyCognitive = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);							
								
				return vpCostAndValueForMoneyCognitive;	
			}
			public double getPersonalVehCostAndValueForMoneyCognitive(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehCostAndValueCognitive =this.getVpCostAndValueForMoneyCognitive();
				}		
				return personalVehCostAndValueCognitive;					
			}			
			public double getPublicTransCostAndValueForMoneyCognitive(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransCostAndValueCognitive =this.getVpCostAndValueForMoneyCognitive();
				}		
				return publicTransCostAndValueCognitive;					
			}	
			public double getCycleCostAndValueForMoneyCognitive(){
				if (prefferedMode instanceof Cycle)	{
					cycleCostAndValueCognitive =this.getVpCostAndValueForMoneyCognitive();
				}		
				return cycleCostAndValueCognitive;					
			}
			public double getWalkingCostAndValueForMoneyCognitive(){
				if (prefferedMode instanceof Walking)	{
					walkingCostAndValueCognitive =this.getVpCostAndValueForMoneyCognitive();
				}		
				return walkingCostAndValueCognitive;					
			}
/////Demnads		
			public double getVpPrivateCostsValueForMoneyCognitiveDemand(){
				return vpPrivateCostsValueForMoneyCognitiveDemand=pca.evaluatePCADemand(getPersonalVehCostAndValueForMoneyCognitive());
			}	
			public double getVpPublicCostsValueForMoneyCognitiveDemand(){
				return vpPublicTransCostsValueForMoneyCognitiveDemand=pca.evaluatePCADemand(getPublicTransCostAndValueForMoneyCognitive());
			}
			public double getVpCycleCostsValueForMoneyCognitiveDemand(){
				return vpCycleCostsValueForMoneyCognitiveDemand=pca.evaluatePCADemand(getCycleCostAndValueForMoneyCognitive());
			}
			public double getVpWalkingCostsValueForMoneyCognitiveDemand(){
				return vpWalkingCostsValueForMoneyCognitiveDemand=pca.evaluatePCADemand(getWalkingCostAndValueForMoneyCognitive());
			}
////Cost and Value for Money cognitive counts
			public int getPrivateVPCostsValueForMoneyCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeTime.getFiredPrivateTimelinessCognitiveCounts()+
							   parkingConcern.getFiredPrivateParkingSpaceConcernCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPCostsValueForMoneyCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeTime.getFiredPublicTimelinessCognitiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPCostsValueForMoneyCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeTime.getFiredCycleTimelinessCognitiveCounts()+
							parkingConcern.getFiredCycleParkingSpaceConcernCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPCostsValueForMoneyCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeTime.getFiredWalkingTimelinessCognitiveCounts();
				}
				return allCounts;					
			}
/////////////Unpleasant cognitive counts
			public int getPrivateVPCostsValueForMoneyUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeTime.getFiredPrivateTimelinessUnpleasantCognitiveCounts()+
							   parkingConcern.getFiredPrivateParkingSpaceConcernUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPCostsValueForMoneyUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeTime.getFiredPublicTimelinessUnpleasantCognitiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPCostsValueForMoneyUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeTime.getFiredCycleTimelinessUnpleasantCognitiveCounts()+
							parkingConcern.getFiredCycleParkingSpaceConcernUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPCostsValueForMoneyUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeTime.getFiredWalkingTimelinessUnpleasantCognitiveCounts();
				}
				return allCounts;					
			}
/////Neither Pleasant Nor Unpleasant Counts			
			public int getPrivateVPCostsValueForMoneyNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeTime.getFiredPrivateTimelinessNeitherNorPleasantCognitiveCounts()+
							   parkingConcern.getFiredPrivateParkingSpaceConcernNeitherNorPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPCostsValueForMoneyNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeTime.getFiredPublicTimelinessNeitherNorPleasantCognitiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPCostsValueForMoneyNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeTime.getFiredCycleTimelinessNeitherNorPleasantCognitiveCounts()+
							parkingConcern.getFiredCycleParkingSpaceConcernNeitherNorPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPCostsValueForMoneyNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeTime.getFiredWalkingTimelinessNeitherNorPleasantCognitiveCounts();
				}
				return allCounts;					
			}	
//////Pleasant Counts
			public int getPrivateVPCostsValueForMoneyPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeTime.getFiredPrivateTimelinessPleasantCognitiveCounts()+
							   parkingConcern.getFiredPrivateParkingSpaceConcernPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPCostsValueForMoneyPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeTime.getFiredPublicTimelinessPleasantCognitiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPCostsValueForMoneyPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeTime.getFiredCycleTimelinessPleasantCognitiveCounts()+
							parkingConcern.getFiredCycleParkingSpaceConcernPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPCostsValueForMoneyPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeTime.getFiredWalkingTimelinessPleasantCognitiveCounts();
				}
				return allCounts;					
			}		
//Cost and Values Affective
			public double evaluateVPCostsValueForMoneyAffective(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{					
					subMajorDecisionAttributesValue.add(modeTime.getFiredPrivateTimelinessAffective());	
					subMajorDecisionAttributesValue.add(parkingConcern.getFiredPrivateParkingSpaceAffective());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(modeTime.getFiredPublicTimelinessAffective());					
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(modeTime.getFiredCycleTimelinessAffective());
					subMajorDecisionAttributesValue.add(parkingConcern.getFiredCycleParkingSpaceAffective());	
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(modeTime.getFiredWalkingTimelinessAffective());					
				}			
				vpCostAndValueForMoneyAffective = prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);							
				return vpCostAndValueForMoneyAffective;	
			}
			public double getPersonalVehCostAndValueForMoneyAffective(){
				if (prefferedMode instanceof PersonalVehicle)	{
					personalVehCostAndValueAffective =this.getVpCostAndValueForMoneyAffective();
				}		
				return personalVehCostAndValueAffective;					
			}			
			public double getPublicTransCostAndValueForMoneyAffective(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransCostAndValueAffective =this.getVpCostAndValueForMoneyAffective();
				}			
				return publicTransCostAndValueAffective;					
			}
			public double getCycleCostAndValueForMoneyAffective(){
				if (prefferedMode instanceof Cycle)	{
					cycleCostAndValueAffective =this.getVpCostAndValueForMoneyAffective();
				}			
				return cycleCostAndValueAffective;					
			}
			public double getWalkingCostAndValueForMoneyAffective(){
				if (prefferedMode instanceof Walking)	{
					walkingCostAndValueAffective =this.getVpCostAndValueForMoneyAffective();
				}			
				return walkingCostAndValueAffective;					
			}
////Demands
			public double getVpPrivateCostsValueForMoneyAffectiveDemand(){				
				return vpPrivateCostsValueForMoneyAffectiveDemand=pca.evaluatePCADemand(getPersonalVehCostAndValueForMoneyAffective());
			}	
			public double getVpPublicCostsValueForMoneyAffectiveDemand(){			
				return vpPublicTransCostsValueForMoneyAffectiveDemand=pca.evaluatePCADemand(getPublicTransCostAndValueForMoneyAffective());
			}
			public double getVpCycleCostsValueForMoneyAffectiveDemand(){			
				return vpCycleCostsValueForMoneyAffectiveDemand=pca.evaluatePCADemand(getCycleCostAndValueForMoneyAffective());
			}
			public double getVpWalkingCostsValueForMoneyAffectiveDemand(){			
				return vpWalkingCostsValueForMoneyAffectiveDemand=pca.evaluatePCADemand(getWalkingCostAndValueForMoneyAffective());
			}
//// Unpleasant Affective
			public int getPrivateVPCostsValueForMoneyAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeTime.getFiredPrivateTimelinessAffectiveCounts()+
							   parkingConcern.getFiredPrivateParkingSpaceConcernAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPCostsValueForMoneyAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeTime.getFiredPublicTimelinessAffectiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPCostsValueForMoneyAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeTime.getFiredCycleTimelinessAffectiveCounts()+
							parkingConcern.getFiredCycleParkingSpaceConcernAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPCostsValueForMoneyAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeTime.getFiredWalkingTimelinessAffectiveCounts();
				}
				return allCounts;					
			}
/////////////Unpleasant Affective counts
			public int getPrivateVPCostsValueForMoneyUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeTime.getFiredPrivateTimelinessUnpleasantAffectiveCounts()+
							   parkingConcern.getFiredPrivateParkingSpaceConcernUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPCostsValueForMoneyUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeTime.getFiredPublicTimelinessUnpleasantAffectiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPCostsValueForMoneyUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeTime.getFiredCycleTimelinessUnpleasantAffectiveCounts()+
							parkingConcern.getFiredCycleParkingSpaceConcernUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPCostsValueForMoneyUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeTime.getFiredWalkingTimelinessUnpleasantAffectiveCounts();
				}
				return allCounts;					
			}
/////Neither Pleasant Nor Unpleasant Counts			
			public int getPrivateVPCostsValueForMoneyNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeTime.getFiredPrivateTimelinessNeitherNorPleasantAffectiveCounts()+
							   parkingConcern.getFiredPrivateParkingSpaceConcernNeitherNorPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPCostsValueForMoneyNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeTime.getFiredPublicTimelinessNeitherNorPleasantAffectiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPCostsValueForMoneyNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeTime.getFiredCycleTimelinessNeitherNorPleasantAffectiveCounts()+
							parkingConcern.getFiredCycleParkingSpaceConcernNeitherNorPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPCostsValueForMoneyNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeTime.getFiredWalkingTimelinessNeitherNorPleasantAffectiveCounts();
				}
				return allCounts;					
			}	
//////Pleasant Counts
			public int getPrivateVPCostsValueForMoneyPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeTime.getFiredPrivateTimelinessPleasantAffectiveCounts()+
							   parkingConcern.getFiredPrivateParkingSpaceConcernPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPCostsValueForMoneyPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeTime.getFiredPublicTimelinessPleasantAffectiveCounts();
				}
				return allCounts;					
			}
			public int getCycleVPCostsValueForMoneyPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeTime.getFiredCycleTimelinessPleasantAffectiveCounts()+
							parkingConcern.getFiredCycleParkingSpaceConcernPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPCostsValueForMoneyPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeTime.getFiredWalkingTimelinessPleasantAffectiveCounts();
				}
				return allCounts;					
			}	
//////
////(4) Values and Priority: Personal Mobility	
			public double evaluateVPPersonalMobility(){			
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getPrivateEaseOfGettingOnOff());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getPrivateWalkingToDestination());
					subMajorDecisionAttributesValue.add(availableSigns.getPrivateModeSignsAvailability());
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getPublicEaseOfGettingOnOff());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getPublicWalkingToDestination());
					subMajorDecisionAttributesValue.add(availableSigns.getPublicModeSignsAvailability());	
					subMajorDecisionAttributesValue.add(easeToMainMode.getPublicEaseOfGettingToMainMode());	
				}	else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getCycleEaseOfGettingOnOff());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getCycleWalkingToDestination());
					subMajorDecisionAttributesValue.add(availableSigns.getCycleModeSignsAvailability());			
				}	else if(prefferedMode instanceof Walking){					
					subMajorDecisionAttributesValue.add(availableSigns.getWalkingModeSignsAvailability());			
				}			
				vpPersonalMobility= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);			
				return vpPersonalMobility;				
			}	
			public double getVpPersonalMobility() {
			return this. vpPersonalMobility;
		}
			public double getPrivateUserPersonalMobility(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privatePersonalMobility  =this.getVpPersonalMobility();
				}	
				return privatePersonalMobility;					
			}			
			public double getPublicTransPersonalMobility(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransPersonalMobility =this.getVpPersonalMobility();
				}		
				return publicTransPersonalMobility;					
			}	
			public double getCyclePersonalMobility(){
				if (prefferedMode instanceof Cycle)	{
					cyclePersonalMobility  =this.getVpPersonalMobility();
				}	
				return cyclePersonalMobility;					
			}			
			public double getWalkingPersonalMobility(){
				if (prefferedMode instanceof Walking)	{
					walkingPersonalMobility =this.getVpPersonalMobility();
				}		
				return walkingPersonalMobility;					
			}	
///Personal Mobility Cognitive		
			public double evaluateVPPersonalMobilityCognitive(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffCognitive());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPrivateWalkingToDestinationCognitive());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredPrivateSignsAvailabilityCognitive());
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffCognitive());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPublicWalkingToDestinationCognitive());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredPublicSignsAvailabilityCognitive());	
					subMajorDecisionAttributesValue.add(easeToMainMode.getFiredPublicEaseOfGettingToMainModeCognitive());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffCognitive());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredCycleWalkingToDestinationCognitive());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredCycleSignsAvailabilityCognitive());			
				}	else if(prefferedMode instanceof Walking){					
					subMajorDecisionAttributesValue.add(availableSigns.getFiredWalkingSignsAvailabilityCognitive());			
				}			
				vpPersonalMobilityCognitive= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return vpPersonalMobilityCognitive;	
			}	
			public double getPrivateUserPersonalMobilityCognitive(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserPersonalMobilityCognitive =this.getVpPersonalMobilityCognitive();
				}	
				return privateUserPersonalMobilityCognitive;					
			}			
			public double getPublicUserPersonalMobilityCognitive(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransPersonalMobilityCognitive =this.getVpPersonalMobilityCognitive();
				}	
				return publicTransPersonalMobilityCognitive;					
			}	
			public double getCycleUserPersonalMobilityCognitive(){
				if (prefferedMode instanceof Cycle)	{
					cyclePersonalMobilityCognitive =this.getVpPersonalMobilityCognitive();
				}	
				return cyclePersonalMobilityCognitive;					
			}
			public double getWalkingUserPersonalMobilityCognitive(){
				if (prefferedMode instanceof Walking)	{
					walkingPersonalMobilityCognitive =this.getVpPersonalMobilityCognitive();
				}	
				return walkingPersonalMobilityCognitive;					
			}
/////Demands
/////Demands
			public double getVpPrivatePersonalMobilityCognitiveDemand(){
				return vpPrivateUserPersonalMobilityCognitiveDemand=pca.evaluatePCADemand(getPrivateUserPersonalMobilityCognitive());
			}	
			public double getVpPublicPersonalMobilityCognitiveDemand(){				
				return vpPublicTransPersonalMobilityCognitiveDemand =pca.evaluatePCADemand(getPublicUserPersonalMobilityCognitive());
			}	
			public double getVpCyclePersonalMobilityCognitiveDemand(){				
				return vpCyclePersonalMobilityCognitiveDemand =pca.evaluatePCADemand(getCycleUserPersonalMobilityCognitive());
			}
			public double getVpWalkingPersonalMobilityCognitiveDemand(){				
				return vpWalkingPersonalMobilityCognitiveDemand =pca.evaluatePCADemand(getWalkingUserPersonalMobilityCognitive());
			}
/// Personal Mobility Cognitive Counts
			public int getPrivateVPPersonalMobilityCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffCognitiveCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationCognitiveCounts()+
							   availableSigns.getFiredPrivateSignsAvailabilityCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffCognitiveCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationCognitiveCounts()+
							   availableSigns.getFiredPublicSignsAvailabilityCognitiveCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModeCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffCognitiveCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationCognitiveCounts()+
							   availableSigns.getFiredCycleSignsAvailabilityCognitiveCounts();					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= availableSigns.getFiredWalkingSignsAvailabilityCognitiveCounts();
				}
				return allCounts;					
			}
////Neither Pleasant NorUnpleasant
			public int getPrivateVPPersonalMobilityNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationNeitherNorPleasantCognitiveCounts()+
							   availableSigns.getFiredPrivateSignsAvailabilityNeitherNorPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationNeitherNorPleasantCognitiveCounts()+
							   availableSigns.getFiredPublicSignsAvailabilityNeitherNorPleasantCognitiveCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModeNeitherNorPleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationNeitherNorPleasantCognitiveCounts()+
							   availableSigns.getFiredCycleSignsAvailabilityNeitherNorPleasantCognitiveCounts();					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= availableSigns.getFiredWalkingSignsAvailabilityNeitherNorPleasantCognitiveCounts();
				}
				return allCounts;					
			}
////Unpleasant 
			public int getPrivateVPPersonalMobilityUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationUnpleasantCognitiveCounts()+
							   availableSigns.getFiredPrivateSignsAvailabilityUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffUnpleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationUnpleasantCognitiveCounts()+
							   availableSigns.getFiredPublicSignsAvailabilityUnpleasantCognitiveCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModeUnpleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationUnpleasantCognitiveCounts()+
							   availableSigns.getFiredCycleSignsAvailabilityUnpleasantCognitiveCounts();					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= availableSigns.getFiredWalkingSignsAvailabilityUnpleasantCognitiveCounts();
				}
				return allCounts;					
			}
/////Pleasant 
			public int getPrivateVPPersonalMobilityPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationPleasantCognitiveCounts()+
							   availableSigns.getFiredPrivateSignsAvailabilityPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationPleasantCognitiveCounts()+
							   availableSigns.getFiredPublicSignsAvailabilityPleasantCognitiveCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModePleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantCognitiveCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationPleasantCognitiveCounts()+
							   availableSigns.getFiredCycleSignsAvailabilityPleasantCognitiveCounts();					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= availableSigns.getFiredWalkingSignsAvailabilityPleasantCognitiveCounts();
				}
				return allCounts;					
			}
////////Personal Mobility Affective
//// Personal Mobility	Affective
			public double evaluateVPPersonalMobilityAffective(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffAffective());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPrivateWalkingToDestinationAffective());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredPrivateSignsAvailabilityAffective());
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffAffective());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPublicWalkingToDestinationAffective());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredPublicSignsAvailabilityAffective());	
					subMajorDecisionAttributesValue.add(easeToMainMode.getFiredPublicEaseOfGettingToMainModeAffective());
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffAffective());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredCycleWalkingToDestinationAffective());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredCycleSignsAvailabilityAffective());			
				}else if(prefferedMode instanceof Walking){					
					subMajorDecisionAttributesValue.add(availableSigns.getFiredWalkingSignsAvailabilityAffective());			
				}			
				vpPersonalMobilityAffective= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);			
				return vpPersonalMobilityAffective;	
			}		
			public double getPrivateUserPersonalMobilityAffective(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserPersonalMobilityAffective =this.getVpPersonalMobilityAffective();
				}		
				return privateUserPersonalMobilityAffective;					
			}			
			public double getPublicUserPersonalMobilityAffective(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransPersonalMobilityAffective =this.getVpPersonalMobilityAffective();
				}	
				return publicTransPersonalMobilityAffective;					
			}	
			public double getCycleUserPersonalMobilityAffective(){
				if (prefferedMode instanceof Cycle)	{
					cyclePersonalMobilityAffective =this.getVpPersonalMobilityAffective();
				}	
				return cyclePersonalMobilityAffective;					
			}	
			public double getWalkingUserPersonalMobilityAffective(){
				if (prefferedMode instanceof Walking)	{
					walkingPersonalMobilityAffective =this.getVpPersonalMobilityAffective();
				}	
				return walkingPersonalMobilityAffective;					
			}	
////////Demands
			public double getVpPrivatePersonalMobilityAffectiveDemand(){			
				return vpPrivateUserPersonalMobilityAffectiveDemand=pca.evaluatePCADemand(getPrivateUserPersonalMobilityAffective());
			}			
			public double getVpPublicPersonalMobilityAffectiveDemand(){
				return vpPublicTransPersonalMobilityAffectiveDemand =pca.evaluatePCADemand(getPublicUserPersonalMobilityAffective());
			}	
			public double getVpCyclePersonalMobilityAffectiveDemand(){
				return vpCyclePersonalMobilityAffectiveDemand =pca.evaluatePCADemand(getCycleUserPersonalMobilityAffective());
			}
			public double getVpWalkingPersonalMobilityAffectiveDemand(){
				return vpWalkingPersonalMobilityAffectiveDemand =pca.evaluatePCADemand(getWalkingUserPersonalMobilityAffective());
			}
/////Affective
			public int getPrivateVPPersonalMobilityAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffAffectiveCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationAffectiveCounts()+
							   availableSigns.getFiredPrivateSignsAvailabilityAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffAffectiveCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationAffectiveCounts()+
							   availableSigns.getFiredPublicSignsAvailabilityAffectiveCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModeAffectivecount();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffAffectiveCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationAffectiveCounts()+
							   availableSigns.getFiredCycleSignsAvailabilityAffectiveCounts();					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= availableSigns.getFiredWalkingSignsAvailabilityAffectiveCounts();
				}
				return allCounts;					
			}
////Neither Pleasant NorUnpleasant
			public int getPrivateVPPersonalMobilityNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationNeitherNorPleasantAffectiveCounts()+
							   availableSigns.getFiredPrivateSignsAvailabilityNeitherNorPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationNeitherNorPleasantAffectiveCounts()+
							   availableSigns.getFiredPublicSignsAvailabilityNeitherNorPleasantAffectiveCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModeNeitherNorPleasantAffectivecount();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationNeitherNorPleasantAffectiveCounts()+
							   availableSigns.getFiredCycleSignsAvailabilityNeitherNorPleasantAffectiveCounts();					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= availableSigns.getFiredWalkingSignsAvailabilityNeitherNorPleasantAffectiveCounts();
				}
				return allCounts;					
			}
////Unpleasant 
			public int getPrivateVPPersonalMobilityUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationUnpleasantAffectiveCounts()+
							   availableSigns.getFiredPrivateSignsAvailabilityUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffUnpleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationUnpleasantAffectiveCounts()+
							   availableSigns.getFiredPublicSignsAvailabilityUnpleasantAffectiveCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModeUnpleasantAffectivecount();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationUnpleasantAffectiveCounts()+
							   availableSigns.getFiredCycleSignsAvailabilityUnpleasantAffectiveCounts();					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= availableSigns.getFiredWalkingSignsAvailabilityUnpleasantAffectiveCounts();
				}
				return allCounts;					
			}
/////Pleasant 
			public int getPrivateVPPersonalMobilityPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationPleasantAffectiveCounts()+
							   availableSigns.getFiredPrivateSignsAvailabilityPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationPleasantAffectiveCounts()+
							   availableSigns.getFiredPublicSignsAvailabilityPleasantAffectiveCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModePleasantAffectivecount();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantAffectiveCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationPleasantAffectiveCounts()+
							   availableSigns.getFiredCycleSignsAvailabilityPleasantAffectiveCounts();					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= availableSigns.getFiredWalkingSignsAvailabilityPleasantAffectiveCounts();
				}
				return allCounts;					
			}
			
////Personal Mobility Physical			
			public double evaluateVPPersonalMobilityPhysical(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPhysical());	
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPrivateWalkingToDestinationPhysical());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPhysical());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPublicWalkingToDestinationPhysical());	
					subMajorDecisionAttributesValue.add(easeToMainMode.getFiredPublicEaseOfGettingToMainModePhysical());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPhysical());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredCycleWalkingToDestinationPhysical());							
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredWalkingUserWalkingToDestinationPhysical());							
				}			
				vpPersonalMobilityPhysical= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);									
				return vpPersonalMobilityPhysical;	
			}
			public double getPrivateUserPersonalMobilityPhysical(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserPersonalMobilityPhysical =this.getVpPersonalMobilityPhysical();
				}		
				return privateUserPersonalMobilityPhysical;					
			}			
			public double getPublicUserPersonalMobilityPhysical(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransPersonalMobilityPhysical =this.getVpPersonalMobilityPhysical();
				}			
				return publicTransPersonalMobilityPhysical;					
			}
			public double getCycleUserPersonalMobilityPhysical(){
				if (prefferedMode instanceof Cycle)	{
					cyclePersonalMobilityPhysical =this.getVpPersonalMobilityPhysical();
				}			
				return cyclePersonalMobilityPhysical;					
			}
			public double getWalkingUserPersonalMobilityPhysical(){
				if (prefferedMode instanceof Walking)	{
					walkingPersonalMobilityPhysical =this.getVpPersonalMobilityPhysical();
				}			
				return walkingPersonalMobilityPhysical;					
			}
////Demands
			public double getVpPrivatePersonalMobilityPhysicalDemand(){			
				return vpPrivateUserPersonalMobilityPhysicalDemand =pca.evaluatePCADemand(getPrivateUserPersonalMobilityPhysical());
			}			
			public double getVpPublicPersonalMobilityPhysicalDemand(){
				return vpPublicTransPersonalMobilityPhysicalDemand=pca.evaluatePCADemand(getPublicUserPersonalMobilityPhysical()) ;
			}	
			public double getVpCylcePersonalMobilityPhysicalDemand(){
				return vpCyclePersonalMobilityPhysicalDemand=pca.evaluatePCADemand(getCycleUserPersonalMobilityPhysical()) ;
			}	
			public double getVpWalkingPersonalMobilityPhysicalDemand(){
				return vpWalkingPersonalMobilityPhysicalDemand=pca.evaluatePCADemand(getWalkingUserPersonalMobilityPhysical()) ;
			}	
//
			public int getPrivateVPPersonalMobilityPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPhysicalCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationPhysicalCounts();		
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPhysicalCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationPhysicalCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModePhysicalcount();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPhysicalCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationPhysicalCounts();							 					
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= walkingToUserDestination.getFiredWalkingUserWalkingToDestinationPhysicalCounts();
				}
				return allCounts;					
			}
////Neither Pleasant NorUnpleasant
			public int getPrivateVPPersonalMobilityNeitherNorPleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationNeitherNorPleasantPhysicalCounts();							  	
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityNeitherNorPleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffNeitherNorPleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationNeitherNorPleasantPhysicalCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModePleasantPhysicalcount();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityNeitherNorPleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationNeitherNorPleasantPhysicalCounts();	
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityNeitherNorPleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= walkingToUserDestination.getFiredWalkingUserWalkingToDestinationNeitherNorPleasantPhysicalCounts();	
				}
				return allCounts;					
			}
////Unpleasant 
			public int getPrivateVPPersonalMobilityUnpleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationUnpleasantPhysicalCounts();			
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityUnpleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffUnpleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationUnpleasantPhysicalCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModeUnpleasantPhysicalcount();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityUnpleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationUnpleasantPhysicalCounts();	
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityUnpleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=  walkingToUserDestination.getFiredWalkingUserWalkingToDestinationUnpleasantPhysicalCounts();
				}
				return allCounts;					
			}
/////Pleasant 
			public int getPrivateVPPersonalMobilityPleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredPrivateWalkingToDestinationPleasantPhysicalCounts();		
				}
				return allCounts;	
			}
			public int getPublicVPPersonalMobilityPleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredPublicWalkingToDestinationPleasantPhysicalCounts()+
							    easeToMainMode.getFiredPublicEaseOfGettingToMainModePleasantPhysicalcount();	
				}
				return allCounts;					
			}
			public int getCycleVPPersonalMobilityPleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantPhysicalCounts()+
							   walkingToUserDestination.getFiredCycleWalkingToDestinationPleasantPhysicalCounts();		
				}
				return allCounts;	
			}
			public int getWalkingVPPersonalMobilityPleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= walkingToUserDestination.getFiredWalkingUserWalkingToDestinationPleasantPhysicalCounts();
				}
				return allCounts;					
			}			
			
////(5) Convenience
//////////(5) Values and Priority: Convenience			
			public double evaluateVPConvenience(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getPrivateEaseOfGettingOnOff());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getPrivateEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(availableSigns.getPrivateModeSignsAvailability());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getPrivateOtherUsersAttitude());
					subMajorDecisionAttributesValue.add(parkingConcern.getPrivateParkingSpaceConcern());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getPublicEaseOfGettingOnOff());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getPublicEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(availableSigns.getPublicModeSignsAvailability());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getPublicOtherUsersAttitude());		
					subMajorDecisionAttributesValue.add(userDistanceToMode.getPublicDistanceToMainMode());
					subMajorDecisionAttributesValue.add(easeToMainMode.getPublicEaseOfGettingToMainMode());
				}	else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getCycleEaseOfGettingOnOff());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getCycleEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(availableSigns.getCycleModeSignsAvailability());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getCycleOtherUsersAttitude());
					subMajorDecisionAttributesValue.add(parkingConcern.getCycleParkingSpaceConcern());	
				}else if(prefferedMode instanceof Walking){				
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getWalkingEaseOfAccessInfo());
					subMajorDecisionAttributesValue.add(availableSigns.getWalkingModeSignsAvailability());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getWalkingOtherUsersAttitude());				
				}		
				vpConvenience= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);			
				return vpConvenience;
			}
			public void setVpConvenience(double vpConvenience) {
				this.vpConvenience = vpConvenience;
			}
			public double getVpConvenience() {
				return this.vpConvenience;
			}			
			public double getPrivateUserConvenience(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserConvenience  =this.getVpConvenience();
				}			
				return privateUserConvenience;					
			}			
			public void setPrivateUserConvenience(double r) {
				this.privateUserConvenience = r;
			}
			public double getPublicTransConvenience(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransConvenience =this.getVpConvenience();
				}		
				return publicTransConvenience;					
			}
			public void setPublicTransConvenience(double r) {				
				this.publicTransConvenience = r;
			}
			public double getCycleUserConvenience(){
				if (prefferedMode instanceof Cycle)	{
					cycleConvenience  =this.getVpConvenience();
				}			
				return cycleConvenience;					
			}			
			public double getCycleConvenience() {
				return cycleConvenience;
			}
			public void setCycleConvenience(double r) {
				this.cycleConvenience = r;
			}
			public double getWalkingConvenience(){
				if (prefferedMode instanceof Walking)	{
					walkingConvenience =this.getVpConvenience();
				}		
				return walkingConvenience;					
			}
			
public void setWalkingConvenience(double r) {
				this.walkingConvenience = r;
			}
			//////Convenience Cognitive
			public double evaluateVPConvenienceCognitive(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffCognitive());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredPrivateSignsAvailabilityCognitive());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPrivateOtherUsersAttitudeCognitive());
					subMajorDecisionAttributesValue.add(parkingConcern.getFiredPrivateParkingSpaceConcernCognitive());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffCognitive());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPublicEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredPublicSignsAvailabilityCognitive());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPublicOtherUsersAttitudeCognitive());		
					subMajorDecisionAttributesValue.add(userDistanceToMode.getFiredPublicDistanceToMainModeCognitive());
					subMajorDecisionAttributesValue.add(easeToMainMode.getFiredPublicEaseOfGettingToMainModeCognitive());
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffCognitive());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredCycleEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredCycleSignsAvailabilityCognitive());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredCycleOtherUsersAttitudeCognitive());
					subMajorDecisionAttributesValue.add(parkingConcern.getFiredCycleParkingSpaceConcernCognitive());	
				}else if(prefferedMode instanceof Walking){			
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoCognitive());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredWalkingSignsAvailabilityCognitive());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredWalkingOtherUsersAttitudeCognitive());				
				}
				vpConvenienceCognitive= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);					
				return vpConvenienceCognitive;	
			}
			public double getPrivateUserConvenienceCognitive(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserConvenienceCognitive  =this.getVpConvenienceCognitive();
				}		
				return privateUserConvenienceCognitive;					
			}			
			public double getPublicTransConvenienceCognitive(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransConvenienceCognitive =this.getVpConvenienceCognitive();
				}		
				return publicTransConvenienceCognitive;					
			}				
			public double getCycleUserConvenienceCognitive(){
				if (prefferedMode instanceof Cycle)	{
					cycleUserConvenienceCognitive  =this.getVpConvenienceCognitive();
				}		
				return cycleUserConvenienceCognitive;					
			}			
			public double getWalkingConvenienceCognitive(){
				if (prefferedMode instanceof Walking)	{
					walkingConvenienceCognitive =this.getVpConvenienceCognitive();
				}		
				return walkingConvenienceCognitive;					
			}
/////All Cognitive convenience counts			
////Counts		
			public int getPrivateVPConvenienceCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffCognitiveCounts()+
							availableSigns.getFiredPrivateSignsAvailabilityCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPConvenienceCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffCognitiveCounts()+
							availableSigns.getFiredPublicSignsAvailabilityCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeCognitiveCounts()+
							userDistanceToMode.getFiredPublicDistanceToMainModeCognitiveCounts()+
							easeToMainMode.getFiredPublicEaseOfGettingToMainModeCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPConvenienceCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffCognitiveCounts()+
							availableSigns.getFiredCycleSignsAvailabilityCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPConvenienceCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoCognitiveCounts()+
							availableSigns.getFiredWalkingSignsAvailabilityCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeCognitiveCounts();	
				}
				return allCounts;					
			}
/////Unpleasant Convenience cognitive
/////Unpleasant
			public int getPrivateVPConvenienceUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoUnpleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantCognitiveCounts()+
							availableSigns.getFiredPrivateSignsAvailabilityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPConvenienceUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoUnpleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffUnpleasantCognitiveCounts()+
							availableSigns.getFiredPublicSignsAvailabilityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeUnpleasantCognitiveCounts()+
							userDistanceToMode.getFiredPublicDistanceToMainModeUnpleasantCognitiveCounts()+
							easeToMainMode.getFiredPublicEaseOfGettingToMainModeUnpleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPConvenienceUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoUnpleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantCognitiveCounts()+
							availableSigns.getFiredCycleSignsAvailabilityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPConvenienceUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoUnpleasantCognitiveCounts()+
							availableSigns.getFiredWalkingSignsAvailabilityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeUnpleasantCognitiveCounts();	
				}
				return allCounts;					
			}		
/////Pleasant Convenience cognitive
			public int getPrivateVPConveniencePleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoPleasantCognitiveCounts()+
								easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantCognitiveCounts()+
								availableSigns.getFiredPrivateSignsAvailabilityPleasantCognitiveCounts()+
								modeUsersAttitude.getFiredPrivateOtherUsersAttitudePleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPConveniencePleasantCognitiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoPleasantCognitiveCounts()+
									easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPleasantCognitiveCounts()+
									availableSigns.getFiredPublicSignsAvailabilityPleasantCognitiveCounts()+
									modeUsersAttitude.getFiredPublicOtherUsersAttitudePleasantCognitiveCounts()+
									userDistanceToMode.getFiredPublicDistanceToMainModePleasantCognitiveCounts()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModePleasantCognitiveCounts();	
						}
						return allCounts;					
			}
			public int getCycleVPConveniencePleasantCognitiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoPleasantCognitiveCounts()+
									easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantCognitiveCounts()+
									availableSigns.getFiredCycleSignsAvailabilityPleasantCognitiveCounts()+
									modeUsersAttitude.getFiredCycleOtherUsersAttitudePleasantCognitiveCounts();						
						}
						return allCounts;	
			}
			public int getWalkingVPConveniencePleasantCognitiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
							allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoPleasantCognitiveCounts()+
									availableSigns.getFiredWalkingSignsAvailabilityPleasantCognitiveCounts()+
									modeUsersAttitude.getFiredWalkingOtherUsersAttitudePleasantCognitiveCounts();	
						}
						return allCounts;					
			}	
/////Neither Nor Pleasant Convenience cognitive
			public int getPrivateVPConvenienceNeitherNorPleasantCognitiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
									easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
									availableSigns.getFiredPrivateSignsAvailabilityNeitherNorPleasantCognitiveCounts()+
									modeUsersAttitude.getFiredPrivateOtherUsersAttitudeNeitherNorPleasantCognitiveCounts();						
						}
						return allCounts;	
			}
			public int getPublicVPConvenienceNeitherNorPleasantCognitiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
									easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
									availableSigns.getFiredPublicSignsAvailabilityNeitherNorPleasantCognitiveCounts()+
									modeUsersAttitude.getFiredPublicOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
									userDistanceToMode.getFiredPublicDistanceToMainModeNeitherNorPleasantCognitiveCounts()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModeNeitherNorPleasantCognitiveCounts();	
						}
						return allCounts;					
			}
			public int getCycleVPConvenienceNeitherNorPleasantCognitiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
									easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
									availableSigns.getFiredCycleSignsAvailabilityNeitherNorPleasantCognitiveCounts()+
									modeUsersAttitude.getFiredCycleOtherUsersAttitudeNeitherNorPleasantCognitiveCounts();						
						}
						return allCounts;	
			}
			public int getWalkingVPConvenienceNeitherNorPleasantCognitiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
							allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoNeitherNorPleasantCognitiveCounts()+
									availableSigns.getFiredWalkingSignsAvailabilityNeitherNorPleasantCognitiveCounts()+
									modeUsersAttitude.getFiredWalkingOtherUsersAttitudeNeitherNorPleasantCognitiveCounts();	
						}
						return allCounts;					
			}
/////////the convenience cognitive demnads		
///Demands
			public double getPrivateUserConvenienceCognitiveDemand(){				
				return vpPrivateUserConvenienceCognitiveDemand =pca.evaluatePCADemand(getPrivateUserConvenienceCognitive());
			}	
			public double getPublicUserConvenienceCognitiveDemand(){			
				return vpPublicTransConvenienceCognitiveDemand= pca.evaluatePCADemand(getPublicTransConvenienceCognitive());
			}	
			public double getCycleUserConvenienceCognitiveDemand(){				
				return vpCycleUserConvenienceCognitiveDemand =pca.evaluatePCADemand(getCycleUserConvenienceCognitive());
			}	
			public double getWalkersConvenienceCognitiveDemand(){			
				return vpWalkingConvenienceCognitiveDemand= pca.evaluatePCADemand(getWalkingConvenienceCognitive());
			}
////Convenience Physical section			
/////Physical Convenieice
			public double evaluateVPConveniencePhysical(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPhysical());					
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPrivateWalkingToDestinationPhysical());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPhysical());					
					subMajorDecisionAttributesValue.add(userDistanceToMode.getFiredPublicDistanceToMainModePhysical());
					subMajorDecisionAttributesValue.add(easeToMainMode.getFiredPublicEaseOfGettingToMainModePhysical());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPublicWalkingToDestinationPhysical());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPhysical());	
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredCycleWalkingToDestinationPhysical());	
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredWalkingUserWalkingToDestinationPhysical());					
				}
				vpConveniencePhysical= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return vpConveniencePhysical;	
			}
			public double getPrivateUserConveniencePhysical(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserConveniencePhysical  =this.getVpConveniencePhysical();
				}		
				return privateUserConveniencePhysical;					
			}			
			public double getPublicUserConveniencePhysical(){
				if (prefferedMode instanceof PublicTransport)	{
					publicUserConveniencePhysical =this.getVpConveniencePhysical();
				}		
				return publicUserConveniencePhysical;					
			}
			public double getCycleUserConveniencePhysical(){
				if (prefferedMode instanceof Cycle)	{
					cycleUserConveniencePhysical  =this.getVpConveniencePhysical();
				}		
				return cycleUserConveniencePhysical;					
			}			
			public double getWalkingUserConveniencePhysical(){
				if (prefferedMode instanceof Walking)	{
					walkingUserConveniencePhysical =this.getVpConveniencePhysical();
				}		
				return walkingUserConveniencePhysical;					
			}
////All Physical convenience counts
					public int getPrivateVPConveniencePhysicalCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPhysicalCounts()+
									walkingToUserDestination.getFiredPrivateWalkingToDestinationPhysicalCounts();					
						}
						return allCounts;	
					}
					public int getPublicVPConveniencePhysicalCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=userDistanceToMode.getFiredPublicDistanceToMainModePhysicalcount()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModePhysicalcount()+
									walkingToUserDestination.getFiredPublicWalkingToDestinationPhysicalCounts();	
						}
						return allCounts;					
					}
					public int getCycleVPConveniencePhysicalCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPhysicalCounts()+
									walkingToUserDestination.getFiredCycleWalkingToDestinationPhysicalCounts();													
						}
						return allCounts;	
					}
					public int getWalkingVPConveniencePhysicalCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
							walkingToUserDestination.getFiredWalkingUserWalkingToDestinationPhysicalCounts();
						}
						return allCounts;					
					}
					
////Unpleasant Physical convenience counts
					public int getPrivateVPConvenienceUnpleasantPhysicalCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantPhysicalCounts()+
									walkingToUserDestination.getFiredPrivateWalkingToDestinationUnpleasantPhysicalCounts();					
						}
						return allCounts;	
					}
					public int getPublicVPConvenienceUnpleasantPhysicalCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=userDistanceToMode.getFiredPublicDistanceToMainModeUnpleasantPhysicalCount()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModeUnpleasantPhysicalcount()+
									walkingToUserDestination.getFiredPublicWalkingToDestinationUnpleasantPhysicalCounts();	
						}
						return allCounts;					
					}
					public int getCycleVPConvenienceUnpleasantPhysicalCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantPhysicalCounts()+
									walkingToUserDestination.getFiredCycleWalkingToDestinationUnpleasantPhysicalCounts();													
						}
						return allCounts;	
					}
					public int getWalkingVPConvenienceUnpleasantPhysicalCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
					//		userDistanceToMode.getFiredWalkingDistanceToMainModeUnpleasantPhysicalCount();
							walkingToUserDestination.getFiredWalkingUserWalkingToDestinationUnpleasantPhysicalCounts();
						}
						return allCounts;					
					}
////Pleasant Physical convenience counts
					public int getPrivateVPConveniencePleasantPhysicalCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantPhysicalCounts();					
						}
						return allCounts;	
					}
					public int getPublicVPConveniencePleasantPhysicalCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=userDistanceToMode.getFiredPublicDistanceToMainModePleasantPhysicalcount()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModePleasantPhysicalcount();	
						}
						return allCounts;					
					}
					public int getCycleVPConveniencePleasantPhysicalCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantPhysicalCounts();													
						}
						return allCounts;	
					}
					public int getWalkingVPConveniencePleasantPhysicalCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{	
							walkingToUserDestination.getFiredWalkingUserWalkingToDestinationPleasantPhysicalCounts();
						}
						return allCounts;					
					}	
////Neither Nor Pleasant Physical convenience counts
					public int getPrivateVPConvenienceNeitherNorPleasantPhysicalCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantPhysicalCounts();					
						}
						return allCounts;	
					}
					public int getPublicVPConvenienceNeitherNorPleasantPhysicalCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=userDistanceToMode.getFiredPublicDistanceToMainModeNeitherNorPleasantPhysicalCount()+
									easeToMainMode.getFiredPublicDistanceToMainModeNeitherNorPleasantPhysicalCount();	
						}
						return allCounts;					
					}
					public int getCycleVPConvenienceNeitherNorPleasantPhysicalCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantPhysicalCounts();													
						}
						return allCounts;	
					}
					public int getWalkingVPConvenienceNeitherNorPleasantPhysicalCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
							walkingToUserDestination.getFiredWalkingUserWalkingToDestinationNeitherNorPleasantPhysicalCounts();
						}
						return allCounts;					
					}
//////Demands					
//////Physical Conveniece Demand
			public double evaluatePrivateVPConveniencePhysicalDemand(){			
				return vpPrivateUserConveniencePhysicalDemand=pca.evaluatePCADemand(getPrivateUserConveniencePhysical()) ;
			}	
			public double evaluatePublicVPConveniencePhysicalDemand(){			
				return vpPublicTransConveniencePhysicalDemand=pca.evaluatePCADemand(getPublicUserConveniencePhysical());
			}	
			public double evaluateCycleVPConveniencePhysicalDemand(){			
				return vpCycleUserConveniencePhysicalDemand=pca.evaluatePCADemand(getCycleUserConveniencePhysical()) ;
			}	
			public double evaluateWalkingVPConveniencePhysicalDemand(){			
				return vpWalkingConveniencePhysicalDemand=pca.evaluatePCADemand(getWalkingUserConveniencePhysical());
			}	
///////Affective		
///Affective section
			public double evaluateVPConvenienceAffective(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffAffective());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPrivateEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredPrivateSignsAvailabilityAffective());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPrivateOtherUsersAttitudeAffective());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPrivateWalkingToDestinationAffective());	
					subMajorDecisionAttributesValue.add(parkingConcern.getFiredPrivateParkingSpaceAffective());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffAffective());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredPublicEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredPublicSignsAvailabilityAffective());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPublicOtherUsersAttitudeAffective());		
					subMajorDecisionAttributesValue.add(userDistanceToMode.getFiredPublicDistanceToMainModeAffective());
					subMajorDecisionAttributesValue.add(easeToMainMode.getFiredPublicEaseOfGettingToMainModeAffective());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredPublicWalkingToDestinationAffective());
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffAffective());
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredCycleEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredCycleSignsAvailabilityAffective());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredCycleOtherUsersAttitudeAffective());	
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredCycleWalkingToDestinationAffective());
					subMajorDecisionAttributesValue.add(parkingConcern.getFiredCycleParkingSpaceAffective());	
				}else if(prefferedMode instanceof Walking){					
					subMajorDecisionAttributesValue.add(easeOfAccessInfor.getFiredWalkingEaseOfAccessAffective());
					subMajorDecisionAttributesValue.add(availableSigns.getFiredWalkingSignsAvailabilityAffective());
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredWalkingOtherUsersAttitudeAffective());
					subMajorDecisionAttributesValue.add(walkingToUserDestination.getFiredWalkingUserWalkingToDestinationAffective());
				}
				vpConvenienceAffective= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);				
				return vpConvenienceAffective;	
			}
			public double getPrivateUserConvenienceAffective(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserConvenienceAffective  =this.getVpConvenienceAffective();
				}			
				return privateUserConvenienceAffective;					
			}			
			public double getPublicTransConvenienceAffective(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransConvenienceAffective =this.getVpConvenienceAffective();
				}			
				return publicTransConvenienceAffective;					
			}	
			public double getCycleUserConvenienceAffective(){
				if (prefferedMode instanceof Cycle)	{
					cycleUserConvenienceAffective  =this.getVpConvenienceAffective();
				}			
				return cycleUserConvenienceAffective;					
			}			
			public double getWalkingConvenienceAffective(){
				if (prefferedMode instanceof Walking)	{
					walkingConvenienceAffective =this.getVpConvenienceAffective();
				}			
				return walkingConvenienceAffective;					
			}	
////All Convenience Affective Counts		
					public int getPrivateVPConvenienceAffectiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffAffectiveCounts()+
									availableSigns.getFiredPrivateSignsAvailabilityAffectiveCounts()+
									modeUsersAttitude.getFiredPrivateOtherUsersAttitudeAffectiveCounts()+
									walkingToUserDestination.getFiredPrivateWalkingToDestinationPhysicalCounts();						
						}
						return allCounts;	
					}
					public int getPublicVPConvenienceAffectiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffAffectiveCounts()+
									availableSigns.getFiredPublicSignsAvailabilityAffectiveCounts()+
									modeUsersAttitude.getFiredPublicOtherUsersAttitudeAffectiveCounts()+
									userDistanceToMode.getFiredPublicDistanceToMainModeAffectivecount()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModeAffectivecount()+
									walkingToUserDestination.getFiredPublicWalkingToDestinationPhysicalCounts();	
						}
						return allCounts;					
					}
					public int getCycleVPConvenienceAffectiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffAffectiveCounts()+
									availableSigns.getFiredCycleSignsAvailabilityAffectiveCounts()+
									modeUsersAttitude.getFiredCycleOtherUsersAttitudeAffectiveCounts()+
									walkingToUserDestination.getFiredCycleWalkingToDestinationPhysicalCounts();						
						}
						return allCounts;	
					}
					public int getWalkingVPConvenienceAffectiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
							allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoAffectiveCounts()+
									availableSigns.getFiredWalkingSignsAvailabilityAffectiveCounts()+
									modeUsersAttitude.getFiredWalkingOtherUsersAttitudeAffectiveCounts()+
									walkingToUserDestination.getFiredWalkingUserWalkingToDestinationPhysicalCounts();	
						}
						return allCounts;					
					}		
////Unpleasant Convenience Affective Counts		
					public int getPrivateVPConvenienceUnpleasantAffectiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoUnpleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantAffectiveCounts()+
									availableSigns.getFiredPrivateSignsAvailabilityUnpleasantAffectiveCounts()+
									modeUsersAttitude.getFiredPrivateOtherUsersAttitudeUnpleasantAffectiveCounts()+
									walkingToUserDestination.getFiredPrivateWalkingToDestinationUnpleasantAffectiveCounts();						
						}
						return allCounts;	
					}
					public int getPublicVPConvenienceUnpleasantAffectiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoUnpleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffUnpleasantAffectiveCounts()+
									availableSigns.getFiredPublicSignsAvailabilityUnpleasantAffectiveCounts()+
									modeUsersAttitude.getFiredPublicOtherUsersAttitudeUnpleasantAffectiveCounts()+
									userDistanceToMode.getFiredPublicDistanceToMainModeUnpleasantAffectivecount()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModeUnpleasantAffectivecount()+	
									walkingToUserDestination.getFiredPublicWalkingToDestinationUnpleasantAffectiveCounts();
						}
						return allCounts;					
					}
					public int getCycleVPConvenienceUnpleasantAffectiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoUnpleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantAffectiveCounts()+
									availableSigns.getFiredCycleSignsAvailabilityUnpleasantAffectiveCounts()+
									modeUsersAttitude.getFiredCycleOtherUsersAttitudeUnpleasantAffectiveCounts()+
									walkingToUserDestination.getFiredCycleWalkingToDestinationUnpleasantAffectiveCounts();
						}
						return allCounts;	
					}
					public int getWalkingVPConvenienceUnpleasantAffectiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
							allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoUnpleasantAffectiveCounts()+
									availableSigns.getFiredWalkingSignsAvailabilityUnpleasantAffectiveCounts()+
									modeUsersAttitude.getFiredWalkingOtherUsersAttitudeUnpleasantAffectiveCounts()+
									walkingToUserDestination.getFiredWalkingUserWalkingToDestinationUnpleasantAffectiveCounts();	
						}
						return allCounts;					
					}	
////Pleasant Convenience Affective Counts		
					public int getPrivateVPConveniencePleasantAffectiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoPleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantAffectiveCounts()+
									availableSigns.getFiredPrivateSignsAvailabilityPleasantAffectiveCounts()+
									modeUsersAttitude.getFiredPrivateOtherUsersAttitudePleasantAffectiveCounts()+
									walkingToUserDestination.getFiredPrivateWalkingToDestinationPleasantAffectiveCounts();;						
						}
						return allCounts;	
					}
					public int getPublicVPConveniencePleasantAffectiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoPleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPleasantAffectiveCounts()+
									availableSigns.getFiredPublicSignsAvailabilityPleasantAffectiveCounts()+
									modeUsersAttitude.getFiredPublicOtherUsersAttitudePleasantAffectiveCounts()+
									userDistanceToMode.getFiredPublicDistanceToMainModePleasantAffectivecount()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModePleasantAffectivecount()+
									walkingToUserDestination.getFiredPublicWalkingToDestinationPleasantAffectiveCounts();;	
						}
						return allCounts;					
					}
					public int getCycleVPConveniencePleasantAffectiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoPleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantAffectiveCounts()+
									availableSigns.getFiredCycleSignsAvailabilityPleasantAffectiveCounts()+
									modeUsersAttitude.getFiredCycleOtherUsersAttitudePleasantAffectiveCounts()+
									walkingToUserDestination.getFiredCycleWalkingToDestinationPleasantAffectiveCounts();						
						}
						return allCounts;	
					}
					public int getWalkingVPConveniencePleasantAffectiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
							allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoPleasantAffectiveCounts()+
									availableSigns.getFiredWalkingSignsAvailabilityPleasantAffectiveCounts()+
									modeUsersAttitude.getFiredWalkingOtherUsersAttitudePleasantAffectiveCounts()+
									walkingToUserDestination.getFiredWalkingUserWalkingToDestinationPleasantAffectiveCounts();;	
						}
						return allCounts;					
					}
////NeitherNorpleasant Convenience Affective Counts		
					public int getPrivateVPConvenienceNeitherNorPleasantAffectiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							allCounts= easeOfAccessInfor.getFiredPrivateEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
									availableSigns.getFiredPrivateSignsAvailabilityNeitherNorPleasantAffectiveCounts()+
									modeUsersAttitude.getFiredPrivateOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
									walkingToUserDestination.getFiredPrivateWalkingToDestinationNeitherNorPleasantAffectiveCounts();						
						}
						return allCounts;	
					}
					public int getPublicVPConvenienceNeitherNorPleasantAffectiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof PublicTransport)	{
							allCounts=easeOfAccessInfor.getFiredPublicEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
									availableSigns.getFiredPublicSignsAvailabilityNeitherNorPleasantAffectiveCounts()+
									modeUsersAttitude.getFiredPublicOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
									userDistanceToMode.getFiredPublicDistanceToMainModeNeitherNorPleasantAffectivecount()+
									easeToMainMode.getFiredPublicEaseOfGettingToMainModeNeitherNorPleasantAffectivecount()+
									walkingToUserDestination.getFiredPublicWalkingToDestinationNeitherNorPleasantAffectiveCounts();	
						}
						return allCounts;					
					}
					public int getCycleVPConvenienceNeitherNorPleasantAffectiveCounts(){
						int allCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							allCounts= easeOfAccessInfor.getFiredCycleEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
									easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
									availableSigns.getFiredCycleSignsAvailabilityNeitherNorPleasantAffectiveCounts()+
									modeUsersAttitude.getFiredCycleOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
									walkingToUserDestination.getFiredCycleWalkingToDestinationNeitherNorPleasantAffectiveCounts();						
						}
						return allCounts;	
					}
					public int getWalkingVPConvenienceNeitherNorPleasantAffectiveCounts(){
						int allCounts =0;		
						if (prefferedMode instanceof Walking)	{
							allCounts=easeOfAccessInfor.getFiredWalkingEaseOfAccessInfoNeitherNorPleasantAffectiveCounts()+
									availableSigns.getFiredWalkingSignsAvailabilityNeitherNorPleasantAffectiveCounts()+
									modeUsersAttitude.getFiredWalkingOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
									walkingToUserDestination.getFiredWalkingUserWalkingToDestinationNeitherNorPleasantAffectiveCounts();	
						}
						return allCounts;					
					}
///Convenience Affective Demands
			public double evaluatePrivateVPConvenienceAffectiveDemand(){				
				return vpPrivateUserConvenienceAffectiveDemand=pca.evaluatePCADemand(getPrivateUserConvenienceAffective()) ;
			}	
			public double evaluatePublicVPConvenienceAffectiveDemand(){			
				return vpPublicTransConvenienceAffectiveDemand =pca.evaluatePCADemand(getPublicTransConvenienceAffective());
			}
			public double evaluateCycleVPConvenienceAffectiveDemand(){				
				return vpCycleUserConvenienceAffectiveDemand=pca.evaluatePCADemand(getCycleUserConvenienceAffective()) ;
			}	
			public double evaluateWalkingVPConvenienceAffectiveDemand(){			
				return vpWalkingTransConvenienceAffectiveDemand =pca.evaluatePCADemand(getWalkingConvenienceAffective());
			}
		
///////////(6) Value and Priority: Comfort
			public double evaluateVPComfort(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getPrivateEaseOfGettingOnOff());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getPrivateOtherUsersAttitude());
					subMajorDecisionAttributesValue.add(elementsProtection.getPrivateProtectionFromElements());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getPrivatePersonalSecurity());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getPrivatePersonalSafety());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getPublicEaseOfGettingOnOff());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getPublicOtherUsersAttitude());	
					subMajorDecisionAttributesValue.add(elementsProtection.getPublicProtectionFromElements());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getPublicPersonalSecurity());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getPublicPersonalSafety());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getCycleEaseOfGettingOnOff());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getCycleOtherUsersAttitude());	
					subMajorDecisionAttributesValue.add(elementsProtection.getCycleProtectionFromElements());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getCyclePersonalSecurity());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getCyclePersonalSafety());	
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getWalkingOtherUsersAttitude());	
					subMajorDecisionAttributesValue.add(elementsProtection.getWalkingProtectionFromElements());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getWalkingPersonalSecurity());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getWalkingPersonalSafety());	
				}			
				vpComfort= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return vpComfort;	
			}			
			public void setVpComfort(double r) {
				this.vpComfort = r;
			}
			public double getPrivateUserComfort(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserComfort  =this.getVpComfort();
				}		
				return privateUserComfort;					
			}			
			public void setPrivateUserComfort(double r) {
				this.privateUserComfort = r;
			}
			public double getPublicTransComfort(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransComfort =this.getVpComfort();
				}	
				return publicTransComfort;					
			}
			public void setPublicTransComfort(double r) {
				this.publicTransComfort = r;
			}
			public double getCycleUserComfort(){
				if (prefferedMode instanceof Cycle)	{
					cycleComfort  =this.getVpComfort();
				}		
				return cycleComfort;					
			}			
			public void setCycleComfort(double r) {
				this.cycleComfort = r;
			}
			public double getWalkingComfort(){
				if (prefferedMode instanceof Walking)	{
					walkingComfort =this.getVpComfort();
				}	
				return walkingComfort;					
			}
public void setWalkingComfort(double r) {
				this.walkingComfort = r;
			}
			////Comfort Cognitive			
			public double evaluateVPComfortCognitive(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffCognitive());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPrivateOtherUsersAttitudeCognitive());
					subMajorDecisionAttributesValue.add(elementsProtection.getFiredPrivateProtectionFromElementsCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredPrivatePersonalSecurityCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredPrivatePersonalSafetyCognitive());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffCognitive());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPublicOtherUsersAttitudeCognitive());	
					subMajorDecisionAttributesValue.add(elementsProtection.getFiredPublicProtectionFromElementsCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredPublicPersonalSecurityCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredPublicPersonalSafetyCognitive());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffCognitive());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredCycleOtherUsersAttitudeCognitive());	
					subMajorDecisionAttributesValue.add(elementsProtection.getFiredCycleProtectionFromElementsCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredCyclePersonalSecurityCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredCyclePersonalSafetyCognitive());	
				}else if(prefferedMode instanceof Walking){									
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredWalkingOtherUsersAttitudeCognitive());	
					subMajorDecisionAttributesValue.add(elementsProtection.getFiredWalkingProtectionFromElementsCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredWalkingPersonalSecurityCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredWalkingPersonalSafetyCognitive());	
				}			
				vpComfortCognitive= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return vpComfortCognitive;	
			}
			public double getPrivateUserComfortCognitive(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserComfortCognitive  =this.getVpComfortCognitive();
				}	
				return privateUserComfortCognitive;					
			}			
			public double getPublicTransComfortCognitive(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransComfortCognitive =this.getVpComfortCognitive();
				}	
				return publicTransComfortCognitive;					
			}
			public double getCycleComfortCognitive(){
				if (prefferedMode instanceof Cycle)	{
					cycleComfortCognitive =this.getVpComfortCognitive();
				}	
				return cycleComfortCognitive;					
			}
			public double getWalkingComfortCognitive(){
				if (prefferedMode instanceof Walking)	{
					walkingComfortCognitive =this.getVpComfortCognitive();
				}	
				return walkingComfortCognitive;					
			}	
////Demands
			public double evaluatePrivateVPComfortCognitiveDemand(){
				return vpPrivateUserComfortCognitiveDemand=pca.evaluatePCADemand(getPrivateUserComfortCognitive());
			}	
			public double evaluatePublicVPComfortCognitiveDemand(){				
				return vpPublicTransComfortCognitiveDemand =pca.evaluatePCADemand(getPublicTransComfortCognitive());
			}
			public double evaluateCycleVPComfortCognitiveDemand(){				
				return vpCycleComfortCognitiveDemand =pca.evaluatePCADemand(getCycleComfortCognitive());
			}
			public double evaluateWalkingVPComfortCognitiveDemand(){				
				return vpWalkingComfortCognitiveDemand =pca.evaluatePCADemand(getWalkingComfortCognitive());
			}
////Counts
			public int getPrivateVPComfortCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= elementsProtection.getFiredPrivateProtectionFromElementsCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffCognitiveCounts()+
							modeUserSecurity.getFiredPrivatePersonalSecurityCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeCognitiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPComfortCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= elementsProtection.getFiredPublicProtectionFromElementsCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffCognitiveCounts()+
							modeUserSecurity.getFiredPublicPersonalSecurityCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeCognitiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPComfortCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= elementsProtection.getFiredCycleProtectionFromElementsCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffCognitiveCounts()+
							modeUserSecurity.getFiredCyclePersonalSecurityCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeCognitiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPComfortCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= elementsProtection.getFiredWalkingProtectionFromElementsCognitiveCounts()+							
							modeUserSecurity.getFiredWalkingPersonalSecurityCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeCognitiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyCognitiveCounts();
				}
				return allCounts;					
			}
/////Unpleasant Comfort cognitive
			public int getPrivateVPComfortUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= elementsProtection.getFiredPrivateProtectionFromElementsUnpleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantCognitiveCounts()+
							modeUserSecurity.getFiredPrivatePersonalSecurityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeUnpleasantCognitiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPComfortUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= elementsProtection.getFiredPublicProtectionFromElementsUnpleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffUnpleasantCognitiveCounts()+
							modeUserSecurity.getFiredPublicPersonalSecurityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeUnpleasantCognitiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyUnpleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPComfortUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= elementsProtection.getFiredCycleProtectionFromElementsUnpleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantCognitiveCounts()+
							modeUserSecurity.getFiredCyclePersonalSecurityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeUnpleasantCognitiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPComfortUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= elementsProtection.getFiredWalkingProtectionFromElementsUnpleasantCognitiveCounts()+							
							modeUserSecurity.getFiredWalkingPersonalSecurityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeUnpleasantCognitiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyUnpleasantCognitiveCounts();
				}
				return allCounts;					
			}
////Neither Nor
			public int getPrivateVPComfortNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= elementsProtection.getFiredPrivateProtectionFromElementsNeitherNorPleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
							modeUserSecurity.getFiredPrivatePersonalSecurityNeitherNorPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyNeitherNorPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPComfortNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= elementsProtection.getFiredPublicProtectionFromElementsNeitherNorPleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
							modeUserSecurity.getFiredPublicPersonalSecurityNeitherNorPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyNeitherNorPleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPComfortNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= elementsProtection.getFiredCycleProtectionFromElementsNeitherNorPleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantCognitiveCounts()+
							modeUserSecurity.getFiredCyclePersonalSecurityNeitherNorPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyNeitherNorPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPComfortNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= elementsProtection.getFiredWalkingProtectionFromElementsNeitherNorPleasantCognitiveCounts()+							
							modeUserSecurity.getFiredWalkingPersonalSecurityNeitherNorPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyNeitherNorPleasantCognitiveCounts();
				}
				return allCounts;					
			}
///Pleasant
			public int getPrivateVPComfortPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= elementsProtection.getFiredPrivateProtectionFromElementsPleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantCognitiveCounts()+
							modeUserSecurity.getFiredPrivatePersonalSecurityPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudePleasantCognitiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPComfortPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= elementsProtection.getFiredPublicProtectionFromElementsPleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPleasantCognitiveCounts()+
							modeUserSecurity.getFiredPublicPersonalSecurityPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudePleasantCognitiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyPleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPComfortPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= elementsProtection.getFiredCycleProtectionFromElementsPleasantCognitiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantCognitiveCounts()+
							modeUserSecurity.getFiredCyclePersonalSecurityPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudePleasantCognitiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPComfortPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= elementsProtection.getFiredWalkingProtectionFromElementsPleasantCognitiveCounts()+							
							modeUserSecurity.getFiredWalkingPersonalSecurityPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudePleasantCognitiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyPleasantCognitiveCounts();
				}
				return allCounts;					
			}
////Physical Comfort Section
			public double evaluateVPComfortPhysical(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPhysical());					
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPhysical());					
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPhysical());					
				}			
				vpComfortPhysical= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
			return vpComfortPhysical;	
		}
			public double getPrivateUserComfortPhysical(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserComfortPhysical  =this.getVpComfortPhysical();
				}		
				return privateUserComfortPhysical;					
			}			
			public double getPublicTransComfortPhysical(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransComfortPhysical =this.getVpComfortPhysical();
				}	
				return publicTransComfortPhysical;					
			}	
			public double getCycleComfortPhysical(){
				if (prefferedMode instanceof Cycle)	{
					cycleComfortPhysical =this.getVpComfortPhysical();
				}	
				return cycleComfortPhysical;					
			}
///demands
			public double evaluatePrivateVPComfortPhysicalDemand(){
				return vpPrivateUserComfortPhysicalDemand =pca.evaluatePCADemand(getPrivateUserComfortPhysical());
			}	
			public double evaluatePublicVPComfortPhysicalDemand(){			
				return vpPublicTransComfortPhysicalDemand =pca.evaluatePCADemand(getPublicTransComfortPhysical());
			}
			public double evaluateCycleVPComfortPhysicalDemand(){			
				return vpCycleComfortPhysicalDemand =pca.evaluatePCADemand(getCycleComfortPhysical());
			}
///Counts
			public int getPrivateVPComfortPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPhysicalCounts();		
				}
				return allCounts;	
			}
			public int getPublicVPComfortPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPhysicalCounts();			
				}
				return allCounts;					
			}
			public int getCycleVPComfortPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPhysicalCounts();							
				}
				return allCounts;	
			}
/////Unpleasant Comfort cognitive
			public int getPrivateVPComfortUnpleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantPhysicalCounts();		
				}
				return allCounts;	
			}
			public int getPublicVPComfortUnpleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffUnpleasantPhysicalCounts();			
				}
				return allCounts;					
			}
			public int getCycleVPComfortUnpleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantPhysicalCounts();							
				}
				return allCounts;	
			}
////Neither Nor
			public int getPrivateVPComfortNeitherNorPleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantPhysicalCounts();		
				}
				return allCounts;	
			}
			public int getPublicVPComfortNeitherNorPleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffNeitherNorPleasantPhysicalCounts();			
				}
				return allCounts;					
			}
			public int getCycleVPComfortNeitherNorPleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantPhysicalCounts();							
				}
				return allCounts;	
			}
///Pleasant
			public int getPrivateVPComfortPleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantPhysicalCounts();		
				}
				return allCounts;	
			}
			public int getPublicVPComfortPleasantPhysicalCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPleasantPhysicalCounts();			
				}
				return allCounts;					
			}
			public int getCycleVPComfortPleasantPhysicalCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantPhysicalCounts();							
				}
				return allCounts;	
			}
			
///Comfort Affective 
			public double evaluateVPComfortAffective(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{	
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffAffective());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPrivateOtherUsersAttitudeAffective());
					subMajorDecisionAttributesValue.add(elementsProtection.getFiredPrivateProtectionFromElementsAffective());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredPrivatePersonalSecurityAffective());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredPrivatePersonalSafetyAffective());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffAffective());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPublicOtherUsersAttitudeAffective());	
					subMajorDecisionAttributesValue.add(elementsProtection.getFiredPublicProtectionFromElementsAffective());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredPublicPersonalSecurityAffective());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredPublicPersonalSafetyAffective());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffAffective());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredCycleOtherUsersAttitudeAffective());	
					subMajorDecisionAttributesValue.add(elementsProtection.getFiredCycleProtectionFromElementsAffective());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredCyclePersonalSecurityAffective());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredCyclePersonalSafetyAffective());	
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(easeOfGettingOnOffMode.getFiredWalkingEaseOfGetOnOffAffective());					
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredWalkingOtherUsersAttitudeAffective());	
					subMajorDecisionAttributesValue.add(elementsProtection.getFiredWalkingProtectionFromElementsAffective());	
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredWalkingPersonalSecurityAffective());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredWalkingPersonalSafetyAffective());	
				}			
				vpComfortAffective= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);
				return vpComfortAffective;	
			}
			public double getPrivateUserComfortAffective(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserComfortAffective  =this.getVpComfortAffective();
				}
				return privateUserComfortAffective;					
			}			
			public double getPublicTransComfortAffective(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransComfortAffective =this.getVpComfortAffective();
				}
				return publicTransComfortAffective;					
			}		
			public double getCycleComfortAffective(){
				if (prefferedMode instanceof Cycle)	{
					cycleComfortAffective =this.getVpComfortAffective();
				}
				return cycleComfortAffective;					
			}
			public double getWalkingComfortAffective(){
				if (prefferedMode instanceof Walking)	{
					walkingComfortAffective =this.getVpComfortAffective();
				}
				return walkingComfortAffective;					
			}
///Demands
			public double evaluatePrivateVPComfortAffectiveDemand(){			
				return vpPrivateUserComfortAffectiveDemand =pca.evaluatePCADemand(getPrivateUserComfortAffective());
			}	
			public double evaluatePublicVPComfortAffectiveDemand(){
				return vpPublicTransComfortAffectiveDemand =pca.evaluatePCADemand(getPublicTransComfortAffective());
			}
			public double evaluateCycleVPComfortAffectiveDemand(){
				return vpCycleComfortAffectiveDemand =pca.evaluatePCADemand(getCycleComfortAffective());
			}
			public double evaluateWalkingVPComfortAffectiveDemand(){
				return vpWalkingComfortAffectiveDemand =pca.evaluatePCADemand(getWalkingComfortAffective());
			}
///Counts
			public int getPrivateVPComfortAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= elementsProtection.getFiredPrivateProtectionFromElementsAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffAffectiveCounts()+
							modeUserSecurity.getFiredPrivatePersonalSecurityAffectiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeAffectiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPComfortAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= elementsProtection.getFiredPublicProtectionFromElementsAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffAffectiveCounts()+
							modeUserSecurity.getFiredPublicPersonalSecurityAffectiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeAffectiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyAffectiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPComfortAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= elementsProtection.getFiredCycleProtectionFromElementsAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffAffectiveCounts()+
							modeUserSecurity.getFiredCyclePersonalSecurityAffectiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeAffectiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPComfortAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= elementsProtection.getFiredWalkingProtectionFromElementsAffectiveCounts()+							
							modeUserSecurity.getFiredWalkingPersonalSecurityAffectiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeAffectiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyAffectiveCounts();
				}
				return allCounts;					
			}	
////Unpleasant
			public int getPrivateVPComfortUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= elementsProtection.getFiredPrivateProtectionFromElementsUnpleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffUnpleasantAffectiveCounts()+
							modeUserSecurity.getFiredPrivatePersonalSecurityUnpleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeUnpleasantAffectiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPComfortUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= elementsProtection.getFiredPublicProtectionFromElementsUnpleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffUnpleasantAffectiveCounts()+
							modeUserSecurity.getFiredPublicPersonalSecurityUnpleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeUnpleasantAffectiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyUnpleasantAffectiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPComfortUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= elementsProtection.getFiredCycleProtectionFromElementsUnpleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffUnpleasantAffectiveCounts()+
							modeUserSecurity.getFiredCyclePersonalSecurityUnpleasantAffectiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeUnpleasantAffectiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPComfortUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= elementsProtection.getFiredWalkingProtectionFromElementsUnpleasantAffectiveCounts()+							
							modeUserSecurity.getFiredWalkingPersonalSecurityUnpleasantAffectiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeUnpleasantAffectiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyUnpleasantAffectiveCounts();
				}
				return allCounts;					
			}
/////Neither Nor
			public int getPrivateVPComfortNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= elementsProtection.getFiredPrivateProtectionFromElementsNeitherNorPleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
							modeUserSecurity.getFiredPrivatePersonalSecurityNeitherNorPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyNeitherNorPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPComfortNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= elementsProtection.getFiredPublicProtectionFromElementsNeitherNorPleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
							modeUserSecurity.getFiredPublicPersonalSecurityNeitherNorPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyNeitherNorPleasantAffectiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPComfortNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= elementsProtection.getFiredCycleProtectionFromElementsNeitherNorPleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffNeitherNorPleasantAffectiveCounts()+
							modeUserSecurity.getFiredCyclePersonalSecurityNeitherNorPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyNeitherNorPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPComfortNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= elementsProtection.getFiredWalkingProtectionFromElementsNeitherNorPleasantAffectiveCounts()+							
							modeUserSecurity.getFiredWalkingPersonalSecurityNeitherNorPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyNeitherNorPleasantAffectiveCounts();
				}
				return allCounts;					
			}
/////Pleasant
			public int getPrivateVPComfortPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= elementsProtection.getFiredPrivateProtectionFromElementsPleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredPrivateEaseOfGetOnOffPleasantAffectiveCounts()+
							modeUserSecurity.getFiredPrivatePersonalSecurityPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudePleasantAffectiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPComfortPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= elementsProtection.getFiredPublicProtectionFromElementsPleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredPublicEaseOfGetOnOffPleasantAffectiveCounts()+
							modeUserSecurity.getFiredPublicPersonalSecurityPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudePleasantAffectiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyPleasantAffectiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPComfortPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= elementsProtection.getFiredCycleProtectionFromElementsPleasantAffectiveCounts()+
							easeOfGettingOnOffMode.getFiredCycleEaseOfGetOnOffPleasantAffectiveCounts()+
							modeUserSecurity.getFiredCyclePersonalSecurityPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudePleasantAffectiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPComfortPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= elementsProtection.getFiredWalkingProtectionFromElementsPleasantAffectiveCounts()+							
							modeUserSecurity.getFiredWalkingPersonalSecurityPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudePleasantAffectiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyPleasantAffectiveCounts();
				}
				return allCounts;					
			}
//////(7) Values and Priority: Security
			public double evaluateVPSecurity(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{								
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getPrivateOtherUsersAttitude());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getPrivatePersonalSecurity());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getPrivatePersonalSafety());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getPublicOtherUsersAttitude());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getPublicPersonalSecurity());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getPublicPersonalSafety());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getCycleOtherUsersAttitude());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getCyclePersonalSecurity());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getCyclePersonalSafety());	
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getWalkingOtherUsersAttitude());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getWalkingPersonalSecurity());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getWalkingPersonalSafety());	
				}			
				vpSecurity= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return vpSecurity;	
			}
			public double getPrivateVPSecurity(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateVPSecurity  =this.getVpSecurity();
				}	
				return privateVPSecurity;					
			}			
			public double getPublicVPSecurity(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransVPSecurity =this.getVpSecurity();
				}		
				return publicTransVPSecurity;					
			}
			public double getCycleVPSecurity(){
				if (prefferedMode instanceof Cycle)	{
					cycleVPSecurity  =this.getVpSecurity();
				}	
				return cycleVPSecurity;					
			}			
			public double getWalkingVPSecurity(){
				if (prefferedMode instanceof Walking)	{
					walkingVPSecurity =this.getVpSecurity();
				}		
				return walkingVPSecurity;					
			}
/////Security Cognitive
			public double evaluateVPSecurityCognitive(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{								
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPrivateOtherUsersAttitudeCognitive());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredPrivatePersonalSecurityCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredPrivatePersonalSafetyCognitive());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPublicOtherUsersAttitudeCognitive());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredPublicPersonalSecurityCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredPublicPersonalSafetyCognitive());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredCycleOtherUsersAttitudeCognitive());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredCyclePersonalSecurityCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredCyclePersonalSafetyCognitive());	
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredWalkingOtherUsersAttitudeCognitive());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredWalkingPersonalSecurityCognitive());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredWalkingPersonalSafetyCognitive());	
				}			
				vpSecurityCognitive= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return vpSecurityCognitive;	
			}
			public double getPrivateUserVPSecurityCognitive(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserVPSecuirtyCognitive  =this.getVpSecurityCognitive();
				}	
				return privateUserVPSecuirtyCognitive;					
			}			
			public double getPublicTransVPSecurityCognitive(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransVPSecurityCognitive =this.getVpSecurityCognitive();
				}		
				return publicTransVPSecurityCognitive;					
			}			
			public double getCycleVPSecurityCognitive(){
				if (prefferedMode instanceof Cycle)	{
					cycleVPSecurityCognitive =this.getVpSecurityCognitive();
				}		
				return cycleVPSecurityCognitive;					
			}
			public double getWalkingVPSecurityCognitive(){
				if (prefferedMode instanceof Walking)	{
					walkingVPSecurityCognitive =this.getVpSecurityCognitive();
				}		
				return walkingVPSecurityCognitive;					
			}
///Demands
			public double evaluatePrivateVPSecurityCognitiveDemand(){
				return vpPrivateUserSecurityCognitiveDemand=pca.evaluatePCADemand(getPrivateUserVPSecurityCognitive());
			}	
			public double evaluatePublicVPSecurityCognitiveDemand(){				
				return vpPublicTransSecurityCognitiveDemand =pca.evaluatePCADemand(getPublicTransVPSecurityCognitive());
			}
			public double evaluateCycleVPSecurityCognitiveDemand(){				
				return vpCycleSecurityCognitiveDemand =pca.evaluatePCADemand(getCycleVPSecurityCognitive());
			}
			public double evaluateWalkingVPSecurityCognitiveDemand(){				
				return vpWalkingSecurityCognitiveDemand =pca.evaluatePCADemand(getWalkingVPSecurityCognitive());
			}
////Cognitive counts
			public int getPrivateVPSecurityCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeUserSecurity.getFiredPrivatePersonalSecurityCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeCognitiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPSecurityCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeUserSecurity.getFiredPublicPersonalSecurityCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeCognitiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPSecurityCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeUserSecurity.getFiredCyclePersonalSecurityCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeCognitiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPSecurityCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeUserSecurity.getFiredWalkingPersonalSecurityCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeCognitiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyCognitiveCounts();
				}
				return allCounts;					
			}			
//////Unpleasant counts
			public int getPrivateVPSecurityUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeUserSecurity.getFiredPrivatePersonalSecurityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeUnpleasantCognitiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPSecurityUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeUserSecurity.getFiredPublicPersonalSecurityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeUnpleasantCognitiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyUnpleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPSecurityUnpleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeUserSecurity.getFiredCyclePersonalSecurityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeUnpleasantCognitiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyUnpleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPSecurityUnpleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeUserSecurity.getFiredWalkingPersonalSecurityUnpleasantCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeUnpleasantCognitiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyUnpleasantCognitiveCounts();
				}
				return allCounts;					
			}			
/////// Neither nor
			public int getPrivateVPSecurityNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeUserSecurity.getFiredPrivatePersonalSecurityNeitherNorPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyNeitherNorPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPSecurityNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeUserSecurity.getFiredPublicPersonalSecurityNeitherNorPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyNeitherNorPleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPSecurityNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeUserSecurity.getFiredCyclePersonalSecurityNeitherNorPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyNeitherNorPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPSecurityNeitherNorPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeUserSecurity.getFiredWalkingPersonalSecurityNeitherNorPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeNeitherNorPleasantCognitiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyNeitherNorPleasantCognitiveCounts();
				}
				return allCounts;					
			}
////////Pleasant
			public int getPrivateVPSecurityPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeUserSecurity.getFiredPrivatePersonalSecurityPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudePleasantCognitiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPSecurityPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeUserSecurity.getFiredPublicPersonalSecurityPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudePleasantCognitiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyPleasantCognitiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPSecurityPleasantCognitiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeUserSecurity.getFiredCyclePersonalSecurityPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudePleasantCognitiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyPleasantCognitiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPSecurityPleasantCognitiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeUserSecurity.getFiredWalkingPersonalSecurityPleasantCognitiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudePleasantCognitiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyPleasantCognitiveCounts();
				}
				return allCounts;					
			}
/////			
////Affective Section			
			public double evaluateVPSecurityAffective(){
				subMajorDecisionAttributesValue= new ArrayList<Double>();
				if (prefferedMode instanceof PersonalVehicle)	{								
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPrivateOtherUsersAttitudeAffective());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredPrivatePersonalSecurityAffective());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredPrivatePersonalSafetyAffective());	
				}else if(prefferedMode instanceof PublicTransport){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredPublicOtherUsersAttitudeAffective());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredPublicPersonalSecurityAffective());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredPublicPersonalSafetyAffective());	
				}else if(prefferedMode instanceof Cycle){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredCycleOtherUsersAttitudeAffective());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredCyclePersonalSecurityAffective());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredCyclePersonalSafetyAffective());	
				}else if(prefferedMode instanceof Walking){
					subMajorDecisionAttributesValue.add(modeUsersAttitude.getFiredWalkingOtherUsersAttitudeAffective());			
					subMajorDecisionAttributesValue.add(modeUserSecurity.getFiredWalkingPersonalSecurityAffective());	
					subMajorDecisionAttributesValue.add(modeUserSafety.getFiredWalkingPersonalSafetyAffective());	
				}			
				vpSecurityAffective= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return vpSecurityAffective;	
			}
			public double getPrivateUserVPSecurityAffective(){
				if (prefferedMode instanceof PersonalVehicle)	{
					privateUserVPSecuirtyAffective  =this.getVpSecurityAffective();
				}	
				return privateUserVPSecuirtyAffective;					
			}			
			public double getPublicTransVPSecurityAffective(){
				if (prefferedMode instanceof PublicTransport)	{
					publicTransVPSecurityAffective =this.getVpSecurityAffective();
				}		
				return publicTransVPSecurityAffective;					
			}
			public double getCycleVPSecurityAffective(){
				if (prefferedMode instanceof Cycle)	{
					cycleVPSecurityAffective =this.getVpSecurityAffective();
				}		
				return cycleVPSecurityAffective;					
			}
			public double getWalkingVPSecurityAffective(){
				if (prefferedMode instanceof Walking)	{
					walkingVPSecurityAffective =this.getVpSecurityAffective();
				}		
				return walkingVPSecurityAffective;					
			}
/////Demands
			public double evaluatePrivateVPSecurityAffectiveDemand(){				
				return vpPrivateUserSecurityAffectiveDemand=pca.evaluatePCADemand(getPrivateUserVPSecurityAffective());
			}	
			public double evaluatePublicVPSecurityAffectiveDemand(){				
				return vpPublicTransSecurityAffectiveDemand=pca.evaluatePCADemand(getPublicTransVPSecurityAffective()) ;
			}
			public double evaluateCycleVPSecurityAffectiveDemand(){				
				return vpCycleSecurityAffectiveDemand=pca.evaluatePCADemand(getCycleVPSecurityAffective()) ;
			}
			public double evaluateWalkingVPSecurityAffectiveDemand(){				
				return vpWalkingSecurityAffectiveDemand=pca.evaluatePCADemand(getWalkingVPSecurityAffective()) ;
			}
////Affective counts
			public int getPrivateVPSecurityAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeUserSecurity.getFiredPrivatePersonalSecurityAffectiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeAffectiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPSecurityAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeUserSecurity.getFiredPublicPersonalSecurityAffectiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeAffectiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyAffectiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPSecurityAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeUserSecurity.getFiredCyclePersonalSecurityAffectiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeAffectiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPSecurityAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeUserSecurity.getFiredWalkingPersonalSecurityAffectiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeAffectiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyAffectiveCounts();
				}
				return allCounts;					
			}			
//////Unpleasant
			public int getPrivateVPSecurityUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeUserSecurity.getFiredPrivatePersonalSecurityUnpleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeUnpleasantAffectiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPSecurityUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeUserSecurity.getFiredPublicPersonalSecurityUnpleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeUnpleasantAffectiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyUnpleasantAffectiveCounts();	
				}
				return allCounts;					
			}
			
			public int getCycleVPSecurityUnpleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeUserSecurity.getFiredCyclePersonalSecurityUnpleasantAffectiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeUnpleasantAffectiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyUnpleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPSecurityUnpleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeUserSecurity.getFiredWalkingPersonalSecurityUnpleasantAffectiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeUnpleasantAffectiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyUnpleasantAffectiveCounts();
				}
				return allCounts;					
			}
//////Neither Nor			
			public int getPrivateVPSecurityNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeUserSecurity.getFiredPrivatePersonalSecurityNeitherNorPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyNeitherNorPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPSecurityNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeUserSecurity.getFiredPublicPersonalSecurityNeitherNorPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyNeitherNorPleasantAffectiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPSecurityNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeUserSecurity.getFiredCyclePersonalSecurityNeitherNorPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyNeitherNorPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPSecurityNeitherNorPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeUserSecurity.getFiredWalkingPersonalSecurityNeitherNorPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudeNeitherNorPleasantAffectiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyNeitherNorPleasantAffectiveCounts();
				}
				return allCounts;					
			}
/////Pleasant
			public int getPrivateVPSecurityPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					allCounts= modeUserSecurity.getFiredPrivatePersonalSecurityPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPrivateOtherUsersAttitudePleasantAffectiveCounts()+
							modeUserSafety.getFiredPrivatePersonalSafetyPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getPublicVPSecurityPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof PublicTransport)	{
					allCounts= modeUserSecurity.getFiredPublicPersonalSecurityPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredPublicOtherUsersAttitudePleasantAffectiveCounts()+
							modeUserSafety.getFiredPublicPersonalSafetyPleasantAffectiveCounts();	
				}
				return allCounts;					
			}
			public int getCycleVPSecurityPleasantAffectiveCounts(){
				int allCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					allCounts= modeUserSecurity.getFiredCyclePersonalSecurityPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredCycleOtherUsersAttitudePleasantAffectiveCounts()+
							modeUserSafety.getFiredCyclePersonalSafetyPleasantAffectiveCounts();						
				}
				return allCounts;	
			}
			public int getWalkingVPSecurityPleasantAffectiveCounts(){
				int allCounts =0;		
				if (prefferedMode instanceof Walking)	{
					allCounts= modeUserSecurity.getFiredWalkingPersonalSecurityPleasantAffectiveCounts()+
							modeUsersAttitude.getFiredWalkingOtherUsersAttitudePleasantAffectiveCounts()+
							modeUserSafety.getFiredWalkingPersonalSafetyPleasantAffectiveCounts();
				}
				return allCounts;					
			}
/////////This section update the major decision variables for the Functional Purpose of a mode: Efficiency, Comfortability and Safety
////(1) Efficiency
			public double updateModeEfficiency(){
				 	subMajorDecisionAttributesValue= new ArrayList<Double>();
				 if (prefferedMode instanceof PersonalVehicle){	
					 subMajorDecisionAttributesValue.add(getPersonalVehReliability());
					 subMajorDecisionAttributesValue.add(getPersonalVehJourneyTime());
					 subMajorDecisionAttributesValue.add(getPersonalVehCostAndValueForMoney());				
				 }else if (prefferedMode instanceof PublicTransport){					
					 subMajorDecisionAttributesValue.add(getPublicTransReliability());
					 subMajorDecisionAttributesValue.add(getPublicTransJourneyTime());
					 subMajorDecisionAttributesValue.add(getPublicTransCostAndValueForMoney());
				 }else if (prefferedMode instanceof Cycle){					
					 subMajorDecisionAttributesValue.add(getCycleReliability());
					 subMajorDecisionAttributesValue.add(getCycleJourneyTime());
					 subMajorDecisionAttributesValue.add(getCycleCostAndValueForMoney());
				 }else if (prefferedMode instanceof Walking){					
					 subMajorDecisionAttributesValue.add(getWalkingReliability());
					 subMajorDecisionAttributesValue.add(getWalkingJourneyTime());
					 subMajorDecisionAttributesValue.add(getWalkingCostAndValueForMoney());
				 }
				 modeEfficiency= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);
				return modeEfficiency;				
			}					
			public double getPrivateModeEfficiency(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeEfficiency = this.getModeEfficiency();
				}	
				return privateModeEfficiency;
			}
			
			public double getPublicModeEfficiency(){
				if(prefferedMode instanceof PublicTransport){
					publicModeEfficiency = this.getModeEfficiency();
				}	
				return publicModeEfficiency;
			}
			public double getCycleModeEfficiency(){
				if(prefferedMode instanceof Cycle){
					cycleModeEfficiency = this.getModeEfficiency();
				}	
				return cycleModeEfficiency;
			}
			
			public double getWalkingModeEfficiency(){
				if(prefferedMode instanceof Walking){
					walkingModeEfficiency = this.getModeEfficiency();
				}	
				return walkingModeEfficiency;
			}
///Effciency Cognitive
		public double updateModeEfficiencyCognitve(){
			 	subMajorDecisionAttributesValue= new ArrayList<Double>();
			 if (prefferedMode instanceof PersonalVehicle){	
				 subMajorDecisionAttributesValue.add(getPersonalVehReliabilityCognitive());
				 subMajorDecisionAttributesValue.add(getPersonalVehJourneyTimeCognitive());
				 subMajorDecisionAttributesValue.add(getPersonalVehCostAndValueForMoneyCognitive());				
			 }else if (prefferedMode instanceof PublicTransport){					
				 subMajorDecisionAttributesValue.add(getPublicTransReliabilityCognitive());
				 subMajorDecisionAttributesValue.add(getPublicTransJourneyTimeCognitive());
				 subMajorDecisionAttributesValue.add(getPublicTransCostAndValueForMoneyCognitive());
			 }else if (prefferedMode instanceof Cycle){					
				 subMajorDecisionAttributesValue.add(getCycleReliabilityCognitive());
				 subMajorDecisionAttributesValue.add(getCycleJourneyTimeCognitive());
				 subMajorDecisionAttributesValue.add(getCycleCostAndValueForMoneyCognitive());
			 }else if (prefferedMode instanceof Walking){					
				 subMajorDecisionAttributesValue.add(getWalkingReliabilityCognitive());
				 subMajorDecisionAttributesValue.add(getWalkingJourneyTimeCognitive());
				 subMajorDecisionAttributesValue.add(getWalkingCostAndValueForMoneyCognitive());
			 }
			 modeEfficiencyCognitive= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);
			return modeEfficiencyCognitive;				
		}	
		public double getModeEfficiencyCognitive() {			
		return modeEfficiencyCognitive;
		}
		public double getPrivateModeEfficiencyCognitive(){
			if(prefferedMode instanceof PersonalVehicle){
				privateModeEfficiencyCognitive = this.getModeEfficiencyCognitive();
			}	
			return privateModeEfficiencyCognitive;
		}		
		public double getPublicModeEfficiencyCognitive(){
			if(prefferedMode instanceof PublicTransport){
				publicModeEfficiencyCognitive = this.getModeEfficiencyCognitive();
			}	
			return publicModeEfficiencyCognitive;
		}
		public double getCycleModeEfficiencyCognitive(){
			if(prefferedMode instanceof Cycle){
				cycleModeEfficiencyCognitive = this.getModeEfficiencyCognitive();
			}	
			return cycleModeEfficiencyCognitive;
		}
		public double getWalkingModeEfficiencyCognitive(){
			if(prefferedMode instanceof Walking){
				walkingModeEfficiencyCognitive = this.getModeEfficiencyCognitive();
			}	
			return walkingModeEfficiencyCognitive;
		}
////Efficiency Affective
	public double updateModeEfficiencyAffective(){
		 	subMajorDecisionAttributesValue= new ArrayList<Double>();
		 if (prefferedMode instanceof PersonalVehicle){	
			 subMajorDecisionAttributesValue.add(getPersonalVehReliabilityAffective());
			 subMajorDecisionAttributesValue.add(getPersonalVehJourneyTimeAffective());
			 subMajorDecisionAttributesValue.add(getPersonalVehCostAndValueForMoneyAffective());				
		 }else if (prefferedMode instanceof PublicTransport){					
			 subMajorDecisionAttributesValue.add(getPublicTransReliabilityAffective());
			 subMajorDecisionAttributesValue.add(getPublicTransJourneyTimeAffective());
			 subMajorDecisionAttributesValue.add(getPublicTransCostAndValueForMoneyAffective());
		 }else if (prefferedMode instanceof Cycle){					
			 subMajorDecisionAttributesValue.add(getCycleReliabilityAffective());
			 subMajorDecisionAttributesValue.add(getCycleJourneyTimeAffective());
			 subMajorDecisionAttributesValue.add(getCycleCostAndValueForMoneyAffective());
		 }else if (prefferedMode instanceof Walking){					
			 subMajorDecisionAttributesValue.add(getWalkingReliabilityAffective());
			 subMajorDecisionAttributesValue.add(getWalkingJourneyTimeAffective());
			 subMajorDecisionAttributesValue.add(getWalkingCostAndValueForMoneyAffective());
		 }
		 modeEfficiencyAffective= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);
		return modeEfficiencyAffective;				
	}	
	public double getModeEfficiencyAffective() {			
	return modeEfficiencyAffective;
	}
	public double getPrivateModeEfficiencyAffective(){
		if(prefferedMode instanceof PersonalVehicle){
			privateModeEfficiencyAffective = this.getModeEfficiencyAffective();
		}	
		return privateModeEfficiencyAffective;
	}		
	public double getPublicModeEfficiencyAffective(){
		if(prefferedMode instanceof PublicTransport){
			publicModeEfficiencyAffective = this.getModeEfficiencyAffective();
		}	
		return publicModeEfficiencyAffective;
	}
	public double getCycleModeEfficiencyAffective(){
		if(prefferedMode instanceof Cycle){
			cycleModeEfficiencyAffective = this.getModeEfficiencyAffective();
		}	
		return cycleModeEfficiencyAffective;
	}
	public double getWalkingModeEfficiencyAffective(){
		if(prefferedMode instanceof Walking){
			walkingModeEfficiencyAffective = this.getModeEfficiencyAffective();
		}	
		return walkingModeEfficiencyAffective;
	}
////Effciciency Cognitive Counts
	public int getPrivateEfficiencyCognitiveCounts(){
		int privateEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateEfficiencyCognitiveCounts= getPrivateVPReliabilityCognitiveCounts()+
					getPrivateVPJourneyTimeCognitiveCounts()+
					getPrivateVPCostsValueForMoneyCognitiveCounts();
					}
		return privateEfficiencyCognitiveCounts;	
	}
	public int getPublicEfficiencyCognitiveCounts(){
		int publicEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof PublicTransport)	{	
			publicEfficiencyCognitiveCounts= getPublicVPReliabilityCognitiveCounts()+
					getPublicVPJourneyTimeCognitiveCounts()+
					getPublicVPCostsValueForMoneyCognitiveCounts();
					}
		return publicEfficiencyCognitiveCounts;	
	}
	public int getCycleEfficiencyCognitiveCounts(){
		int cycleEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof Cycle)	{	
			cycleEfficiencyCognitiveCounts= getCycleVPReliabilityCognitiveCounts()+
					getCycleVPJourneyTimeCognitiveCounts()+
					getCycleVPCostsValueForMoneyCognitiveCounts();
					}
		return cycleEfficiencyCognitiveCounts;	
	}
	public int getWalkingEfficiencyCognitiveCounts(){
		int walkingEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof Walking)	{	
			walkingEfficiencyCognitiveCounts= getWalkingVPReliabilityCognitiveCounts()+
					getWalkingVPJourneyTimeCognitiveCounts()+
					getWalkingVPCostsValueForMoneyCognitiveCounts();
					}
		return walkingEfficiencyCognitiveCounts;	
	}
////Efficiency Unpleasant
	public int getPrivateEfficiencyUnpleasantCognitiveCounts(){
		int privateEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateEfficiencyCognitiveCounts= getPrivateVPReliabilityUnpleasantCognitiveCounts()+
					getPrivateVPJourneyTimeUnpleasantCognitiveCounts()+
					getPrivateVPCostsValueForMoneyUnpleasantCognitiveCounts();
					}
		return privateEfficiencyCognitiveCounts;	
	}
	public int getPublicEfficiencyUnpleasantCognitiveCounts(){
		int publicEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof PublicTransport)	{	
			publicEfficiencyCognitiveCounts= getPublicVPReliabilityUnpleasantCognitiveCounts()+
					getPublicVPJourneyTimeUnpleasantCognitiveCounts()+
					getPublicVPCostsValueForMoneyUnpleasantCognitiveCounts();
					}
		return publicEfficiencyCognitiveCounts;	
	}
	public int getCycleEfficiencyUnpleasantCognitiveCounts(){
		int cycleEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof Cycle)	{	
			cycleEfficiencyCognitiveCounts= getCycleVPReliabilityUnpleasantCognitiveCounts()+
					getCycleVPJourneyTimeUnpleasantCognitiveCounts()+
					getCycleVPCostsValueForMoneyUnpleasantCognitiveCounts();
					}
		return cycleEfficiencyCognitiveCounts;	
	}
	public int getWalkingEfficiencyUnpleasantCognitiveCounts(){
		int walkingEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof Walking)	{	
			walkingEfficiencyCognitiveCounts= getWalkingVPReliabilityUnpleasantCognitiveCounts()+
					getWalkingVPJourneyTimeUnpleasantCognitiveCounts()+
					getWalkingVPCostsValueForMoneyUnpleasantCognitiveCounts();
					}
		return walkingEfficiencyCognitiveCounts;	
	}
////Neither Pleasant Nor Unpleasant
	public int getPrivateEfficiencyNeitherNorPleasantCognitiveCounts(){
		int privateEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateEfficiencyCognitiveCounts= getPrivateVPReliabilityNeitherPleasantNorUnpleasantCognitiveCounts()+
					getPrivateVPJourneyTimeNeitherNorPleasantCognitiveCounts()+
					getPrivateVPCostsValueForMoneyNeitherNorPleasantCognitiveCounts();
					}
		return privateEfficiencyCognitiveCounts;	
	}
	public int getPublicEfficiencyNeitherNorPleasantCognitiveCounts(){
		int publicEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof PublicTransport)	{	
			publicEfficiencyCognitiveCounts= getPublicVPReliabilityNeitherPleasantNorUnpleasantCognitiveCounts()+
					getPublicVPJourneyTimeNeitherNorPleasantCognitiveCounts()+
					getPublicVPCostsValueForMoneyNeitherNorPleasantCognitiveCounts();
					}
		return publicEfficiencyCognitiveCounts;	
	}
	public int getCycleEfficiencyNeitherNorPleasantCognitiveCounts(){
		int cycleEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof Cycle)	{	
			cycleEfficiencyCognitiveCounts= getCycleVPReliabilityNeitherPleasantNorUnpleasantCognitiveCounts()+
					getCycleVPJourneyTimeNeitherNorPleasantCognitiveCounts()+
					getCycleVPCostsValueForMoneyNeitherNorPleasantCognitiveCounts();
					}
		return cycleEfficiencyCognitiveCounts;	
	}
	public int getWalkingEfficiencyNeitherNorPleasantCognitiveCounts(){
		int walkingEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof Walking)	{	
			walkingEfficiencyCognitiveCounts= getWalkingVPReliabilityNeitherPleasantNorUnpleasantCognitiveCounts()+
					getWalkingVPJourneyTimeNeitherNorPleasantCognitiveCounts()+
					getWalkingVPCostsValueForMoneyNeitherNorPleasantCognitiveCounts();
					}
		return walkingEfficiencyCognitiveCounts;	
	}
/////Cognitive Pleasant
	public int getPrivateEfficiencyPleasantCognitiveCounts(){
		int privateEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateEfficiencyCognitiveCounts= getPrivateVPReliabilityPleasantCognitiveCounts()+
					getPrivateVPJourneyTimePleasantCognitiveCounts()+
					getPrivateVPCostsValueForMoneyPleasantCognitiveCounts();
					}
		return privateEfficiencyCognitiveCounts;	
	}
	public int getPublicEfficiencyPleasantCognitiveCounts(){
		int publicEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof PublicTransport)	{	
			publicEfficiencyCognitiveCounts= getPublicVPReliabilityPleasantCognitiveCounts()+
					getPublicVPJourneyTimePleasantCognitiveCounts()+
					getPublicVPCostsValueForMoneyPleasantCognitiveCounts();
					}
		return publicEfficiencyCognitiveCounts;	
	}
	public int getCycleEfficiencyPleasantCognitiveCounts(){
		int cycleEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof Cycle)	{	
			cycleEfficiencyCognitiveCounts= getCycleVPReliabilityPleasantCognitiveCounts()+
					getCycleVPJourneyTimePleasantCognitiveCounts()+
					getCycleVPCostsValueForMoneyPleasantCognitiveCounts();
					}
		return cycleEfficiencyCognitiveCounts;	
	}
	public int getWalkingEfficiencyPleasantCognitiveCounts(){
		int walkingEfficiencyCognitiveCounts =0;				
		if (prefferedMode instanceof Walking)	{	
			walkingEfficiencyCognitiveCounts= getWalkingVPReliabilityPleasantCognitiveCounts()+
					getWalkingVPJourneyTimePleasantCognitiveCounts()+
					getWalkingVPCostsValueForMoneyPleasantCognitiveCounts();
					}
		return walkingEfficiencyCognitiveCounts;	
	}
/////Efficicnecy Affective Counts
	public int getPrivateEfficiencyAffectiveCounts(){
		int privateEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateEfficiencyAffectiveCounts= getPrivateVPReliabilityAffectiveCounts()+
					getPrivateVPJourneyTimeAffectiveCounts()+
					getPrivateVPCostsValueForMoneyAffectiveCounts();
					}
		return privateEfficiencyAffectiveCounts;	
	}
	public int getPublicEfficiencyAffectiveCounts(){
		int publicEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof PublicTransport)	{	
			publicEfficiencyAffectiveCounts= getPublicVPReliabilityAffectiveCounts()+
					getPublicVPJourneyTimeAffectiveCounts()+
					getPublicVPCostsValueForMoneyAffectiveCounts();
					}
		return publicEfficiencyAffectiveCounts;	
	}
	public int getCycleEfficiencyAffectiveCounts(){
		int cycleEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof Cycle)	{	
			cycleEfficiencyAffectiveCounts= getCycleVPReliabilityAffectiveCounts()+
					getCycleVPJourneyTimeAffectiveCounts()+
					getCycleVPCostsValueForMoneyAffectiveCounts();
					}
		return cycleEfficiencyAffectiveCounts;	
	}
	public int getWalkingEfficiencyAffectiveCounts(){
		int walkingEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof Walking)	{	
			walkingEfficiencyAffectiveCounts= getWalkingVPReliabilityAffectiveCounts()+
					getWalkingVPJourneyTimeAffectiveCounts()+
					getWalkingVPCostsValueForMoneyAffectiveCounts();
					}
		return walkingEfficiencyAffectiveCounts;	
	}	
////Efficiency Affective Unpleasant
	public int getPrivateEfficiencyUnpleasantAffectiveCounts(){
		int privateEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateEfficiencyAffectiveCounts= getPrivateVPReliabilityUnpleasantAffectiveCounts()+
					getPrivateVPJourneyTimeUnpleasantAffectiveCounts()+
					getPrivateVPCostsValueForMoneyUnpleasantAffectiveCounts();
					}
		return privateEfficiencyAffectiveCounts;	
	}
	public int getPublicEfficiencyUnpleasantAffectiveCounts(){
		int publicEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof PublicTransport)	{	
			publicEfficiencyAffectiveCounts= getPublicVPReliabilityUnpleasantAffectiveCounts()+
					getPublicVPJourneyTimeUnpleasantAffectiveCounts()+
					getPublicVPCostsValueForMoneyUnpleasantAffectiveCounts();
					}
		return publicEfficiencyAffectiveCounts;	
	}
	public int getCycleEfficiencyUnpleasantAffectiveCounts(){
		int cycleEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof Cycle)	{	
			cycleEfficiencyAffectiveCounts= getCycleVPReliabilityUnpleasantAffectiveCounts()+
					getCycleVPJourneyTimeUnpleasantAffectiveCounts()+
					getCycleVPCostsValueForMoneyUnpleasantAffectiveCounts();
					}
		return cycleEfficiencyAffectiveCounts;	
	}
	public int getWalkingEfficiencyUnpleasantAffectiveCounts(){
		int walkingEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof Walking)	{	
			walkingEfficiencyAffectiveCounts= getWalkingVPReliabilityUnpleasantAffectiveCounts()+
					getWalkingVPJourneyTimeUnpleasantAffectiveCounts()+
					getWalkingVPCostsValueForMoneyUnpleasantAffectiveCounts();
					}
		return walkingEfficiencyAffectiveCounts;	
	}
////Eff Affective Neither Pleasant Nor Unpleasant
	public int getPrivateEfficiencyNeitherNorPleasantAffectiveCounts(){
		int privateEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateEfficiencyAffectiveCounts= getPrivateVPReliabilityNeitherPleasantNorUnpleasantAffectiveCounts()+
					getPrivateVPJourneyTimeNeitherNorPleasantAffectiveCounts()+
					getPrivateVPCostsValueForMoneyNeitherNorPleasantAffectiveCounts();
					}
		return privateEfficiencyAffectiveCounts;	
	}
	public int getPublicEfficiencyNeitherNorPleasantAffectiveCounts(){
		int publicEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof PublicTransport)	{	
			publicEfficiencyAffectiveCounts= getPublicVPReliabilityNeitherPleasantNorUnpleasantAffectiveCounts()+
					getPublicVPJourneyTimeNeitherNorPleasantAffectiveCounts()+
					getPublicVPCostsValueForMoneyNeitherNorPleasantAffectiveCounts();
					}
		return publicEfficiencyAffectiveCounts;	
	}
	public int getCycleEfficiencyNeitherNorPleasantAffectiveCounts(){
		int cycleEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof Cycle)	{	
			cycleEfficiencyAffectiveCounts= getCycleVPReliabilityNeitherPleasantNorUnpleasantAffectiveCounts()+
					getCycleVPJourneyTimeNeitherNorPleasantAffectiveCounts()+
					getCycleVPCostsValueForMoneyNeitherNorPleasantAffectiveCounts();
					}
		return cycleEfficiencyAffectiveCounts;	
	}
	public int getWalkingEfficiencyNeitherNorPleasantAffectiveCounts(){
		int walkingEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof Walking)	{	
			walkingEfficiencyAffectiveCounts= getWalkingVPReliabilityNeitherPleasantNorUnpleasantAffectiveCounts()+
					getWalkingVPJourneyTimeNeitherNorPleasantAffectiveCounts()+
					getWalkingVPCostsValueForMoneyNeitherNorPleasantAffectiveCounts();
					}
		return walkingEfficiencyAffectiveCounts;	
	}
/////Efficuency Affective Pleasant
	public int getPrivateEfficiencyPleasantAffectiveCounts(){
		int privateEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateEfficiencyAffectiveCounts= getPrivateVPReliabilityPleasantAffectiveCounts()+
					getPrivateVPJourneyTimePleasantAffectiveCounts()+
					getPrivateVPCostsValueForMoneyPleasantAffectiveCounts();
					}
		return privateEfficiencyAffectiveCounts;	
	}
	public int getPublicEfficiencyPleasantAffectiveCounts(){
		int publicEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof PublicTransport)	{	
			publicEfficiencyAffectiveCounts= getPublicVPReliabilityPleasantAffectiveCounts()+
					getPublicVPJourneyTimePleasantAffectiveCounts()+
					getPublicVPCostsValueForMoneyPleasantAffectiveCounts();
					}
		return publicEfficiencyAffectiveCounts;	
	}
	public int getCycleEfficiencyPleasantAffectiveCounts(){
		int cycleEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof Cycle)	{	
			cycleEfficiencyAffectiveCounts= getCycleVPReliabilityPleasantAffectiveCounts()+
					getCycleVPJourneyTimePleasantAffectiveCounts()+
					getCycleVPCostsValueForMoneyPleasantAffectiveCounts();
					}
		return cycleEfficiencyAffectiveCounts;	
	}
	public int getWalkingEfficiencyPleasantAffectiveCounts(){
		int walkingEfficiencyAffectiveCounts =0;				
		if (prefferedMode instanceof Walking)	{	
			walkingEfficiencyAffectiveCounts= getWalkingVPReliabilityPleasantAffectiveCounts()+
					getWalkingVPJourneyTimePleasantAffectiveCounts()+
					getWalkingVPCostsValueForMoneyPleasantAffectiveCounts();
					}
		return walkingEfficiencyAffectiveCounts;	
	}
	
/////(2) Comfortability			
			public double updateModeComfortability(){
				 subMajorDecisionAttributesValue= new ArrayList<Double>();	
				 if (prefferedMode instanceof PersonalVehicle){						
					 subMajorDecisionAttributesValue.add(getPrivateUserPersonalMobility());
					 subMajorDecisionAttributesValue.add(getPrivateUserConvenience());
					 subMajorDecisionAttributesValue.add(getPrivateUserComfort());				
				 }else if (prefferedMode instanceof PublicTransport){					
					 subMajorDecisionAttributesValue.add(getPublicTransPersonalMobility());
					 subMajorDecisionAttributesValue.add(getPublicTransConvenience());
					 subMajorDecisionAttributesValue.add(getPublicTransComfort());	
				 }else if (prefferedMode instanceof Cycle){					
					 subMajorDecisionAttributesValue.add(getCyclePersonalMobility());
					 subMajorDecisionAttributesValue.add(getCycleUserConvenience());
					 subMajorDecisionAttributesValue.add(getCycleUserComfort());	
				 }else if (prefferedMode instanceof Walking){					
					 subMajorDecisionAttributesValue.add(getWalkingPersonalMobility());
					 subMajorDecisionAttributesValue.add(getWalkingConvenience());
					 subMajorDecisionAttributesValue.add(getWalkingComfort());	
				 }
				 modeComfortability= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return modeComfortability;				
			}					
			public double getPrivateModeComfortability(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeComfortability = this.getModeComfortability();
				}	
				return privateModeComfortability;
			}
			public double getPublicModeComfortability(){
				if(prefferedMode instanceof PublicTransport){
					publicModeComfortability = this.getModeComfortability();
				}		
				return publicModeComfortability;
			}
			public double getCycleModeComfortability(){
				if(prefferedMode instanceof Cycle){
					cycleModeComfortability = this.getModeComfortability();
				}	
				return cycleModeComfortability;
			}
			public double getWalkingModeComfortability(){
				if(prefferedMode instanceof Walking){
					walkingModeComfortability = this.getModeComfortability();
				}		
				return walkingModeComfortability;
			}
////// comfortability Cognitive
			public double updateModeComfortabilityCognitive(){
				 subMajorDecisionAttributesValue= new ArrayList<Double>();	
				 if (prefferedMode instanceof PersonalVehicle){						
					 subMajorDecisionAttributesValue.add(getPrivateUserPersonalMobilityCognitive());
					 subMajorDecisionAttributesValue.add(getPrivateUserConvenienceCognitive());
					 subMajorDecisionAttributesValue.add(getPrivateUserComfortCognitive());				
				 }else if (prefferedMode instanceof PublicTransport){					
					 subMajorDecisionAttributesValue.add(getPublicUserPersonalMobilityCognitive());
					 subMajorDecisionAttributesValue.add(getPublicTransConvenienceCognitive());
					 subMajorDecisionAttributesValue.add(getPublicTransComfortCognitive());	
				 }else if (prefferedMode instanceof Cycle){					
					 subMajorDecisionAttributesValue.add(getCycleUserPersonalMobilityCognitive());
					 subMajorDecisionAttributesValue.add(getCycleUserConvenienceCognitive());
					 subMajorDecisionAttributesValue.add(getCycleComfortCognitive());	
				 }else if (prefferedMode instanceof Walking){					
					 subMajorDecisionAttributesValue.add(getWalkingUserPersonalMobilityCognitive());
					 subMajorDecisionAttributesValue.add(getWalkingConvenienceCognitive());
					 subMajorDecisionAttributesValue.add(getWalkingComfortCognitive());	
				 }
				 modeComfortabilityCognitive= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return modeComfortabilityCognitive;				
			}					
			public double getModeComfortabilityCognitive() {
				return modeComfortabilityCognitive;
			}
			public double getPrivateModeComfortabilityCognitive(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeComfortabilityCognitive = this.getModeComfortabilityCognitive();
				}	
				return privateModeComfortabilityCognitive;
			}
			public double getPublicModeComfortabilityCognitive(){
				if(prefferedMode instanceof PublicTransport){
					publicModeComfortabilityCognitive = this.getModeComfortabilityCognitive();
				}	
				return publicModeComfortabilityCognitive;
			}
			public double getCycleModeComfortabilityCognitive(){
				if(prefferedMode instanceof Cycle){
					cycleModeComfortabilityCognitive = this.getModeComfortabilityCognitive();
				}	
				return cycleModeComfortabilityCognitive;
			}
			public double getWalkingModeComfortabilityCognitive(){
				if(prefferedMode instanceof Walking){
					walkingModeComfortabilityCognitive = this.getModeComfortabilityCognitive();
				}	
				return walkingModeComfortabilityCognitive;
			}

///Affective
			public double updateModeComfortabilityAffective(){
				 subMajorDecisionAttributesValue= new ArrayList<Double>();	
				 if (prefferedMode instanceof PersonalVehicle){						
					 subMajorDecisionAttributesValue.add(getPrivateUserPersonalMobilityAffective());
					 subMajorDecisionAttributesValue.add(getPrivateUserConvenienceAffective());
					 subMajorDecisionAttributesValue.add(getPrivateUserComfortAffective());				
				 }else if (prefferedMode instanceof PublicTransport){					
					 subMajorDecisionAttributesValue.add(getPublicUserPersonalMobilityAffective());
					 subMajorDecisionAttributesValue.add(getPublicTransConvenienceAffective());
					 subMajorDecisionAttributesValue.add(getPublicTransComfortAffective());	
				 }else if (prefferedMode instanceof Cycle){					
					 subMajorDecisionAttributesValue.add(getCycleUserPersonalMobilityAffective());
					 subMajorDecisionAttributesValue.add(getCycleUserConvenienceAffective());
					 subMajorDecisionAttributesValue.add(getCycleComfortAffective());	
				 }else if (prefferedMode instanceof Walking){					
					 subMajorDecisionAttributesValue.add(getWalkingUserPersonalMobilityAffective());
					 subMajorDecisionAttributesValue.add(getWalkingConvenienceAffective());
					 subMajorDecisionAttributesValue.add(getWalkingComfortAffective());	
				 }
				 modeComfortabilityAffective= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return modeComfortabilityAffective;				
			}					
			public double getModeComfortabilityAffective() {
				return modeComfortabilityAffective;
			}
			public double getPrivateModeComfortabilityAffective(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeComfortabilityAffective = this.getModeComfortabilityAffective();
				}	
				return privateModeComfortabilityAffective;
			}
			public double getPublicModeComfortabilityAffective(){
				if(prefferedMode instanceof PublicTransport){
					publicModeComfortabilityAffective = this.getModeComfortabilityAffective();
				}	
				return publicModeComfortabilityAffective;
			}
			public double getCycleModeComfortabilityAffective(){
				if(prefferedMode instanceof Cycle){
					cycleModeComfortabilityAffective= this.getModeComfortabilityAffective();
				}	
				return cycleModeComfortabilityAffective;
			}
			public double getWalkingModeComfortabilityAffective(){
				if(prefferedMode instanceof Walking){
					walkingModeComfortabilityAffective = this.getModeComfortabilityAffective();
				}	
				return walkingModeComfortabilityAffective;
			}
//////Physical
			public double updateModeComfortabilityPhysical(){
				 subMajorDecisionAttributesValue= new ArrayList<Double>();	
				 if (prefferedMode instanceof PersonalVehicle){						
					 subMajorDecisionAttributesValue.add(getPrivateUserPersonalMobilityPhysical());
					 subMajorDecisionAttributesValue.add(getPrivateUserConveniencePhysical());
					 subMajorDecisionAttributesValue.add(getPrivateUserComfortPhysical());				
				 }else if (prefferedMode instanceof PublicTransport){					
					 subMajorDecisionAttributesValue.add(getPublicUserPersonalMobilityPhysical());
					 subMajorDecisionAttributesValue.add(getPublicUserConveniencePhysical());
					 subMajorDecisionAttributesValue.add(getPublicTransComfortPhysical());	
				 }else if (prefferedMode instanceof Cycle){					
					 subMajorDecisionAttributesValue.add(getCycleUserPersonalMobilityPhysical());
					 subMajorDecisionAttributesValue.add(getCycleUserConveniencePhysical());
					 subMajorDecisionAttributesValue.add(getCycleComfortPhysical());	
				 }else if (prefferedMode instanceof Walking){					
					 subMajorDecisionAttributesValue.add(getWalkingUserPersonalMobilityPhysical());
					 subMajorDecisionAttributesValue.add(getWalkingUserConveniencePhysical());
				//	 subMajorDecisionAttributesValue.add(getWalkingUserComfortPhysical());	
				 }
				 modeComfortabilityPhysical= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);		
				return modeComfortabilityPhysical;				
			}					
			public double getModeComfortabilityPhysical() {
				return modeComfortabilityPhysical;
			}
			public double getPrivateModeComfortabilityPhysical(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeComfortabilityPhysical = this.getModeComfortabilityPhysical();
				}	
				return privateModeComfortabilityPhysical;
			}
			public double getPublicModeComfortabilityPhysical(){
				if(prefferedMode instanceof PublicTransport){
					publicModeComfortabilityPhysical = this.getModeComfortabilityPhysical();
				}	
				return publicModeComfortabilityPhysical;
			}
			public double getCycleModeComfortabilityPhysical(){
				if(prefferedMode instanceof Cycle){
					cycleModeComfortabilityPhysical= this.getModeComfortabilityPhysical();
				}	
				return cycleModeComfortabilityPhysical;
			}
			public double getWalkingModeComfortabilityPhysical(){
				if(prefferedMode instanceof Walking){
					walkingModeComfortabilityPhysical = this.getModeComfortabilityPhysical();
				}	
				return walkingModeComfortabilityPhysical;
			}
/////Comfortability Counts
////Comfortability Cognitive
			public int getPrivateComfortabilityCognitiveCounts(){
				int privateComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateComfortabilityCognitiveCounts= getPrivateVPPersonalMobilityCognitiveCounts()+
							getPrivateVPConvenienceCognitiveCounts()+
							getPrivateVPComfortCognitiveCounts();
							}
				return privateComfortabilityCognitiveCounts;	
			}			
			public int getPublicComfortabilityCognitiveCounts(){
				int publicComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicComfortabilityCognitiveCounts= getPublicVPPersonalMobilityCognitiveCounts()+
							getPublicVPConvenienceCognitiveCounts()+
							getPublicVPComfortCognitiveCounts();
							}
				return publicComfortabilityCognitiveCounts;	
			}
			public int getCycleComfortabilityCognitiveCounts(){
				int cycleComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleComfortabilityCognitiveCounts= getCycleVPPersonalMobilityCognitiveCounts()+
							getCycleVPConvenienceCognitiveCounts()+
							getCycleVPComfortCognitiveCounts();
							}
				return cycleComfortabilityCognitiveCounts;	
			}
			public int getWalkingComfortabilityCognitiveCounts(){
				int walkingComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingComfortabilityCognitiveCounts= getWalkingVPPersonalMobilityCognitiveCounts()+
							getWalkingVPConvenienceCognitiveCounts()+
							getWalkingVPComfortCognitiveCounts();
							}
				return walkingComfortabilityCognitiveCounts;	
			}
///Comfortabillity Cognitive LEvels
////Unpleasant
			public int getPrivateComfortabilityUnpleasantCognitiveCounts(){
				int privateComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateComfortabilityCognitiveCounts= getPrivateVPPersonalMobilityUnpleasantCognitiveCounts()+
							getPrivateVPConvenienceUnpleasantCognitiveCounts()+
							getPrivateVPComfortUnpleasantCognitiveCounts();
							}
				return privateComfortabilityCognitiveCounts;	
			}
			public int getPublicComfortabilityUnpleasantCognitiveCounts(){
				int publicComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicComfortabilityCognitiveCounts= getPublicVPPersonalMobilityUnpleasantCognitiveCounts()+
							getPublicVPConvenienceUnpleasantCognitiveCounts()+
							getPublicVPComfortUnpleasantCognitiveCounts();
							}
				return publicComfortabilityCognitiveCounts;	
			}
			public int getCycleComfortabilityUnpleasantCognitiveCounts(){
				int cycleComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleComfortabilityCognitiveCounts= getCycleVPPersonalMobilityUnpleasantCognitiveCounts()+
							getCycleVPConvenienceUnpleasantCognitiveCounts()+
							getCycleVPComfortUnpleasantCognitiveCounts();
							}
				return cycleComfortabilityCognitiveCounts;	
			}
			public int getWalkingComfortabilityUnpleasantCognitiveCounts(){
				int walkingComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingComfortabilityCognitiveCounts= getWalkingVPPersonalMobilityUnpleasantCognitiveCounts()+
							getWalkingVPConvenienceUnpleasantCognitiveCounts()+
							getWalkingVPComfortUnpleasantCognitiveCounts();
							}
				return walkingComfortabilityCognitiveCounts;	
			}
//Neither Nor Pleasant
			public int getPrivateComfortabilityNeitherNorPleasantCognitiveCounts(){
				int privateComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateComfortabilityCognitiveCounts= getPrivateVPPersonalMobilityNeitherNorPleasantCognitiveCounts()+
							getPrivateVPConvenienceNeitherNorPleasantCognitiveCounts()+
							getPrivateVPComfortNeitherNorPleasantCognitiveCounts();
							}
				return privateComfortabilityCognitiveCounts;	
			}
			public int getPublicComfortabilityNeitherNorPleasantCognitiveCounts(){
				int publicComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicComfortabilityCognitiveCounts= getPublicVPPersonalMobilityNeitherNorPleasantCognitiveCounts()+
							getPublicVPConvenienceNeitherNorPleasantCognitiveCounts()+
							getPublicVPComfortNeitherNorPleasantCognitiveCounts();
							}
				return publicComfortabilityCognitiveCounts;	
			}
			public int getCycleComfortabilityNeitherNorPleasantCognitiveCounts(){
				int cycleComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleComfortabilityCognitiveCounts= getCycleVPPersonalMobilityNeitherNorPleasantCognitiveCounts()+
							getCycleVPConvenienceNeitherNorPleasantCognitiveCounts()+
							getCycleVPComfortNeitherNorPleasantCognitiveCounts();
							}
				return cycleComfortabilityCognitiveCounts;	
			}
			public int getWalkingComfortabilityNeitherNorPleasantCognitiveCounts(){
				int walkingComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingComfortabilityCognitiveCounts= getWalkingVPPersonalMobilityNeitherNorPleasantCognitiveCounts()+
							getWalkingVPConvenienceNeitherNorPleasantCognitiveCounts()+
							getWalkingVPComfortNeitherNorPleasantCognitiveCounts();
							}
				return walkingComfortabilityCognitiveCounts;	
			}
//////Pleasant Comfortability Cognitive
			public int getPrivateComfortabilityPleasantCognitiveCounts(){
				int privateComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateComfortabilityCognitiveCounts= getPrivateVPPersonalMobilityPleasantCognitiveCounts()+
							getPrivateVPConveniencePleasantCognitiveCounts()+
							getPrivateVPComfortPleasantCognitiveCounts();
							}
				return privateComfortabilityCognitiveCounts;	
			}
			public int getPublicComfortabilityPleasantCognitiveCounts(){
				int publicComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicComfortabilityCognitiveCounts= getPublicVPPersonalMobilityPleasantCognitiveCounts()+
							getPublicVPConveniencePleasantCognitiveCounts()+
							getPublicVPComfortPleasantCognitiveCounts();
							}
				return publicComfortabilityCognitiveCounts;	
			}
			public int getCycleComfortabilityPleasantCognitiveCounts(){
				int cycleComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleComfortabilityCognitiveCounts= getCycleVPPersonalMobilityPleasantCognitiveCounts()+
							getCycleVPConveniencePleasantCognitiveCounts()+
							getCycleVPComfortPleasantCognitiveCounts();
							}
				return cycleComfortabilityCognitiveCounts;	
			}
			public int getWalkingComfortabilityPleasantCognitiveCounts(){
				int walkingComfortabilityCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingComfortabilityCognitiveCounts= getWalkingVPPersonalMobilityPleasantCognitiveCounts()+
							getWalkingVPConveniencePleasantCognitiveCounts()+
							getWalkingVPComfortPleasantCognitiveCounts();
							}
				return walkingComfortabilityCognitiveCounts;	
			}
////Comfortability Affective			
			public int getPrivateComfortabilityAffectiveCounts(){
				int privateComfortabilityAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateComfortabilityAffectiveCounts= getPrivateVPPersonalMobilityAffectiveCounts()+
							getPrivateVPConvenienceAffectiveCounts()+
							getPrivateVPComfortAffectiveCounts();
							}
				return privateComfortabilityAffectiveCounts;	
			}			
			public int getPublicComfortabilityAffectiveCounts(){
				int publicComfortabilityAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicComfortabilityAffectiveCounts= getPublicVPPersonalMobilityAffectiveCounts()+
							getPublicVPConvenienceAffectiveCounts()+
							getPublicVPComfortAffectiveCounts();
							}
				return publicComfortabilityAffectiveCounts;	
			}
			public int getCycleComfortabilityAffectiveCounts(){
				int cycleComfortabilityAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleComfortabilityAffectiveCounts= getCycleVPPersonalMobilityAffectiveCounts()+
							getCycleVPConvenienceAffectiveCounts()+
							getCycleVPComfortAffectiveCounts();
							}
				return cycleComfortabilityAffectiveCounts;	
			}
			public int getWalkingComfortabilityAffectiveCounts(){
				int walkingComfortabilityAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingComfortabilityAffectiveCounts= getWalkingVPPersonalMobilityAffectiveCounts()+
							getWalkingVPConvenienceAffectiveCounts()+
							getWalkingVPComfortAffectiveCounts();
							}
				return walkingComfortabilityAffectiveCounts;	
			}
///Comfortabillity Affective LEvels
///Unpleasant
	public int getPrivateComfortabilityUnpleasantAffectiveCounts(){
	int privateComfortabilityAffectiveCounts =0;				
		if (prefferedMode instanceof PersonalVehicle)	{	
			privateComfortabilityAffectiveCounts= getPrivateVPPersonalMobilityUnpleasantAffectiveCounts()+
			getPrivateVPConvenienceUnpleasantAffectiveCounts()+
			getPrivateVPComfortUnpleasantAffectiveCounts();
		}
	return privateComfortabilityAffectiveCounts;	
	}
	public int getPublicComfortabilityUnpleasantAffectiveCounts(){
						int publicComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof PublicTransport)	{	
							publicComfortabilityAffectiveCounts= getPublicVPPersonalMobilityUnpleasantAffectiveCounts()+
									getPublicVPConvenienceUnpleasantAffectiveCounts()+
									getPublicVPComfortUnpleasantAffectiveCounts();
									}
						return publicComfortabilityAffectiveCounts;	
	}
	public int getCycleComfortabilityUnpleasantAffectiveCounts(){
						int cycleComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							cycleComfortabilityAffectiveCounts= getCycleVPPersonalMobilityUnpleasantAffectiveCounts()+
									getCycleVPConvenienceUnpleasantAffectiveCounts()+
									getCycleVPComfortUnpleasantAffectiveCounts();
									}
						return cycleComfortabilityAffectiveCounts;	
	}
	public int getWalkingComfortabilityUnpleasantAffectiveCounts(){
						int walkingComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof Walking)	{	
							walkingComfortabilityAffectiveCounts= getWalkingVPPersonalMobilityUnpleasantAffectiveCounts()+
									getWalkingVPConvenienceUnpleasantAffectiveCounts()+
									getWalkingVPComfortUnpleasantAffectiveCounts();
									}
						return walkingComfortabilityAffectiveCounts;	
	}
//Neither Nor Pleasant
	public int getPrivateComfortabilityNeitherNorPleasantAffectiveCounts(){
						int privateComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							privateComfortabilityAffectiveCounts= getPrivateVPPersonalMobilityNeitherNorPleasantAffectiveCounts()+
									getPrivateVPConvenienceNeitherNorPleasantAffectiveCounts()+
									getPrivateVPComfortNeitherNorPleasantAffectiveCounts();
									}
						return privateComfortabilityAffectiveCounts;	
	}
	public int getPublicComfortabilityNeitherNorPleasantAffectiveCounts(){
						int publicComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof PublicTransport)	{	
							publicComfortabilityAffectiveCounts= getPublicVPPersonalMobilityNeitherNorPleasantAffectiveCounts()+
									getPublicVPConvenienceNeitherNorPleasantAffectiveCounts()+
									getPublicVPComfortNeitherNorPleasantAffectiveCounts();
									}
						return publicComfortabilityAffectiveCounts;	
	}
	public int getCycleComfortabilityNeitherNorPleasantAffectiveCounts(){
						int cycleComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							cycleComfortabilityAffectiveCounts= getCycleVPPersonalMobilityNeitherNorPleasantAffectiveCounts()+
									getCycleVPConvenienceNeitherNorPleasantAffectiveCounts()+
									getCycleVPComfortNeitherNorPleasantAffectiveCounts();
									}
						return cycleComfortabilityAffectiveCounts;	
	}
	public int getWalkingComfortabilityNeitherNorPleasantAffectiveCounts(){
						int walkingComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof Walking)	{	
							walkingComfortabilityAffectiveCounts= getWalkingVPPersonalMobilityNeitherNorPleasantAffectiveCounts()+
									getWalkingVPConvenienceNeitherNorPleasantAffectiveCounts()+
									getWalkingVPComfortNeitherNorPleasantAffectiveCounts();
									}
						return walkingComfortabilityAffectiveCounts;	
	}
////Pleasant Comfortability Affective
					public int getPrivateComfortabilityPleasantAffectiveCounts(){
						int privateComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof PersonalVehicle)	{	
							privateComfortabilityAffectiveCounts= getPrivateVPPersonalMobilityPleasantAffectiveCounts()+
									getPrivateVPConveniencePleasantAffectiveCounts()+
									getPrivateVPComfortPleasantAffectiveCounts();
									}
						return privateComfortabilityAffectiveCounts;	
					}
					public int getPublicComfortabilityPleasantAffectiveCounts(){
						int publicComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof PublicTransport)	{	
							publicComfortabilityAffectiveCounts= getPublicVPPersonalMobilityPleasantAffectiveCounts()+
									getPublicVPConveniencePleasantAffectiveCounts()+
									getPublicVPComfortPleasantAffectiveCounts();
									}
						return publicComfortabilityAffectiveCounts;	
					}
					public int getCycleComfortabilityPleasantAffectiveCounts(){
						int cycleComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof Cycle)	{	
							cycleComfortabilityAffectiveCounts= getCycleVPPersonalMobilityPleasantAffectiveCounts()+
									getCycleVPConveniencePleasantAffectiveCounts()+
									getCycleVPComfortPleasantAffectiveCounts();
									}
						return cycleComfortabilityAffectiveCounts;	
					}
					public int getWalkingComfortabilityPleasantAffectiveCounts(){
						int walkingComfortabilityAffectiveCounts =0;				
						if (prefferedMode instanceof Walking)	{	
							walkingComfortabilityAffectiveCounts= getWalkingVPPersonalMobilityPleasantAffectiveCounts()+
									getWalkingVPConveniencePleasantAffectiveCounts()+
									getWalkingVPComfortPleasantAffectiveCounts();
									}
						return walkingComfortabilityAffectiveCounts;	
					}
			
///Comfortability Physical
			public int getPrivateComfortabilityPhysicalCounts(){
				int privateComfortabilityPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateComfortabilityPhysicalCounts= getPrivateVPPersonalMobilityPhysicalCounts()+
							getPrivateVPConveniencePhysicalCounts()+
							getPrivateVPComfortPhysicalCounts();
							}
				return privateComfortabilityPhysicalCounts;	
			}			
			public int getPublicComfortabilityPhysicalCounts(){
				int publicComfortabilityPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicComfortabilityPhysicalCounts= getPublicVPPersonalMobilityPhysicalCounts()+
							getPublicVPConveniencePhysicalCounts()+
							getPublicVPComfortPhysicalCounts();
							}
				return publicComfortabilityPhysicalCounts;	
			}
			public int getCycleComfortabilityPhysicalCounts(){
				int cycleComfortabilityPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleComfortabilityPhysicalCounts= getCycleVPPersonalMobilityPhysicalCounts()+
							getCycleVPConveniencePhysicalCounts()+
							getCycleVPComfortPhysicalCounts();
							}
				return cycleComfortabilityPhysicalCounts;	
			}
			public int getWalkingComfortabilityPhysicalCounts(){
				int walkingComfortabilityPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingComfortabilityPhysicalCounts= getWalkingVPPersonalMobilityPhysicalCounts()+
							getWalkingVPConveniencePhysicalCounts();
							}
				return walkingComfortabilityPhysicalCounts;	
			}
///Comfortability PhysicalUnpleasant
			public int getPrivateComfortabilityUnpleasantPhysicalCounts(){
				int privateComfortabilityPhysicalCounts =0;				
					if (prefferedMode instanceof PersonalVehicle)	{	
						privateComfortabilityPhysicalCounts= getPrivateVPPersonalMobilityUnpleasantPhysicalCounts()+
						getPrivateVPConvenienceUnpleasantPhysicalCounts()+
						getPrivateVPComfortUnpleasantPhysicalCounts();
					}
				return privateComfortabilityPhysicalCounts;	
				}
			public int getPublicComfortabilityUnpleasantPhysicalCounts(){
									int publicComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof PublicTransport)	{	
										publicComfortabilityPhysicalCounts= getPublicVPPersonalMobilityUnpleasantPhysicalCounts()+
												getPublicVPConvenienceUnpleasantPhysicalCounts()+
												getPublicVPComfortUnpleasantPhysicalCounts();
												}
									return publicComfortabilityPhysicalCounts;	
			}
			public int getCycleComfortabilityUnpleasantPhysicalCounts(){
									int cycleComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof Cycle)	{	
										cycleComfortabilityPhysicalCounts= getCycleVPPersonalMobilityUnpleasantPhysicalCounts()+
												getCycleVPConvenienceUnpleasantPhysicalCounts()+
												getCycleVPComfortUnpleasantPhysicalCounts();
												}
									return cycleComfortabilityPhysicalCounts;	
		}
		public int getWalkingComfortabilityUnpleasantPhysicalCounts(){
									int walkingComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof Walking)	{	
										walkingComfortabilityPhysicalCounts= getWalkingVPPersonalMobilityUnpleasantPhysicalCounts()+
												getWalkingVPConvenienceUnpleasantPhysicalCounts();
												}
									return walkingComfortabilityPhysicalCounts;	
		}
//Neither Nor Pleasant
								public int getPrivateComfortabilityNeitherNorPleasantPhysicalCounts(){
									int privateComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof PersonalVehicle)	{	
										privateComfortabilityPhysicalCounts= getPrivateVPPersonalMobilityNeitherNorPleasantPhysicalCounts()+
												getPrivateVPConvenienceNeitherNorPleasantPhysicalCounts()+
												getPrivateVPComfortNeitherNorPleasantPhysicalCounts();
												}
									return privateComfortabilityPhysicalCounts;	
								}
								public int getPublicComfortabilityNeitherNorPleasantPhysicalCounts(){
									int publicComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof PublicTransport)	{	
										publicComfortabilityPhysicalCounts= getPublicVPPersonalMobilityNeitherNorPleasantPhysicalCounts()+
												getPublicVPConvenienceNeitherNorPleasantPhysicalCounts()+
												getPublicVPComfortNeitherNorPleasantPhysicalCounts();
												}
									return publicComfortabilityPhysicalCounts;	
								}
								public int getCycleComfortabilityNeitherNorPleasantPhysicalCounts(){
									int cycleComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof Cycle)	{	
										cycleComfortabilityPhysicalCounts= getCycleVPPersonalMobilityNeitherNorPleasantPhysicalCounts()+
												getCycleVPConvenienceNeitherNorPleasantPhysicalCounts()+
												getCycleVPComfortNeitherNorPleasantPhysicalCounts();
												}
									return cycleComfortabilityPhysicalCounts;	
								}
								public int getWalkingComfortabilityNeitherNorPleasantPhysicalCounts(){
									int walkingComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof Walking)	{	
										walkingComfortabilityPhysicalCounts= getWalkingVPPersonalMobilityNeitherNorPleasantPhysicalCounts()+
												getWalkingVPConvenienceNeitherNorPleasantPhysicalCounts();
												}
									return walkingComfortabilityPhysicalCounts;	
								}
			////Pleasant Comfortability Affective
								public int getPrivateComfortabilityPleasantPhysicalCounts(){
									int privateComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof PersonalVehicle)	{	
										privateComfortabilityPhysicalCounts= getPrivateVPPersonalMobilityPleasantPhysicalCounts()+
												getPrivateVPConveniencePleasantPhysicalCounts()+
												getPrivateVPComfortPleasantPhysicalCounts();
												}
									return privateComfortabilityPhysicalCounts;	
								}
								public int getPublicComfortabilityPleasantPhysicalCounts(){
									int publicComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof PublicTransport)	{	
										publicComfortabilityPhysicalCounts= getPublicVPPersonalMobilityPleasantPhysicalCounts()+
												getPublicVPConveniencePleasantPhysicalCounts()+
												getPublicVPComfortPleasantPhysicalCounts();
												}
									return publicComfortabilityPhysicalCounts;	
								}
								public int getCycleComfortabilityPleasantPhysicalCounts(){
									int cycleComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof Cycle)	{	
										cycleComfortabilityPhysicalCounts= getCycleVPPersonalMobilityPleasantPhysicalCounts()+
												getCycleVPConveniencePleasantPhysicalCounts()+
												getCycleVPComfortPleasantPhysicalCounts();
												}
									return cycleComfortabilityPhysicalCounts;	
								}
								public int getWalkingComfortabilityPleasantPhysicalCounts(){
									int walkingComfortabilityPhysicalCounts =0;				
									if (prefferedMode instanceof Walking)	{	
										walkingComfortabilityPhysicalCounts= getWalkingVPPersonalMobilityPleasantPhysicalCounts()+
												getWalkingVPConveniencePleasantPhysicalCounts();
												}
									return walkingComfortabilityPhysicalCounts;	
								}
			
///(3) Safety:
			public double updateModeSafety(){
				 subMajorDecisionAttributesValue= new ArrayList<Double>();	
				 if (prefferedMode instanceof PersonalVehicle){						
					 subMajorDecisionAttributesValue.add(getPrivateUserPersonalMobility());
					 subMajorDecisionAttributesValue.add(getPrivateVPSecurity());
					 subMajorDecisionAttributesValue.add(getPrivateUserComfort());				
				 }else if (prefferedMode instanceof PublicTransport){						
					 subMajorDecisionAttributesValue.add(getPublicTransPersonalMobility());
					 subMajorDecisionAttributesValue.add(getPublicVPSecurity());
					 subMajorDecisionAttributesValue.add(getPublicTransComfort());	
				 }else if (prefferedMode instanceof Cycle){						
					 subMajorDecisionAttributesValue.add(getCyclePersonalMobility());
					 subMajorDecisionAttributesValue.add(getCycleVPSecurity());
					 subMajorDecisionAttributesValue.add(getCycleUserComfort());	
				 }else if (prefferedMode instanceof Walking){						
					 subMajorDecisionAttributesValue.add(getWalkingPersonalMobility());
					 subMajorDecisionAttributesValue.add(getWalkingVPSecurity());
					 subMajorDecisionAttributesValue.add(getWalkingComfort());	
				 }
				 modeSafety= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return modeSafety;				
			}	
			public double getPrivateModeSafety(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeSafety = this.getModeSafety();
				}		
				return privateModeSafety;
			}
			public double getPublicModeSafety(){
				if(prefferedMode instanceof PublicTransport){
					publicModeSafety = this.getModeSafety();
				}			
				return publicModeSafety;
			}
			public double getCycleModeSafety(){
				if(prefferedMode instanceof Cycle){
					cycleModeSafety = this.getModeSafety();
				}		
				return cycleModeSafety;
			}
			public double getWalkingModeSafety(){
				if(prefferedMode instanceof Walking){
					walkingModeSafety = this.getModeSafety();
				}			
				return walkingModeSafety;
			}
////Safety Cognitive
			public double updateModeSafetyCognitive(){
				 subMajorDecisionAttributesValue= new ArrayList<Double>();	
				 if (prefferedMode instanceof PersonalVehicle){						
					 subMajorDecisionAttributesValue.add(getPrivateUserPersonalMobilityCognitive());
					 subMajorDecisionAttributesValue.add(getPrivateUserVPSecurityCognitive());
					 subMajorDecisionAttributesValue.add(getPrivateUserComfortCognitive());				
				 }else if (prefferedMode instanceof PublicTransport){						
					 subMajorDecisionAttributesValue.add(getPublicUserPersonalMobilityCognitive());
					 subMajorDecisionAttributesValue.add(getPublicTransVPSecurityCognitive());
					 subMajorDecisionAttributesValue.add(getPublicTransComfortCognitive());	
				 }else if (prefferedMode instanceof Cycle){						
					 subMajorDecisionAttributesValue.add(getCycleUserPersonalMobilityCognitive());
					 subMajorDecisionAttributesValue.add(getCycleVPSecurityCognitive());
					 subMajorDecisionAttributesValue.add(getCycleComfortCognitive());	
				 }else if (prefferedMode instanceof Walking){						
					 subMajorDecisionAttributesValue.add(getWalkingUserPersonalMobilityCognitive());
					 subMajorDecisionAttributesValue.add(getWalkingVPSecurityCognitive());
					 subMajorDecisionAttributesValue.add(getWalkingComfortCognitive());	
				 }
				 modeSafetyCognitive= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return modeSafetyCognitive;				
			}				
			public double getModeSafetyCognitive() {
				return modeSafetyCognitive;
			}
			public double getPrivateModeSafetyCognitive(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeSafetyCognitive = this.getModeSafetyCognitive();
				}		
				return privateModeSafetyCognitive;
			}
			public double getPublicModeSafetyCognitive(){
				if(prefferedMode instanceof PublicTransport){
					publicModeSafetyCognitive = this.getModeSafetyCognitive();
				}			
				return publicModeSafetyCognitive;
			}
			public double getCycleModeSafetyCognitive(){
				if(prefferedMode instanceof Cycle){
					cycleModeSafetyCognitive = this.getModeSafetyCognitive();
				}		
				return cycleModeSafetyCognitive;
			}
			public double getWalkingModeSafetyCognitive(){
				if(prefferedMode instanceof Walking){
					walkingModeSafetyCognitive = this.getModeSafetyCognitive();
				}			
				return walkingModeSafetyCognitive;
			}
/////////Safety Affective
			public double updateModeSafetyAffective(){
				 subMajorDecisionAttributesValue= new ArrayList<Double>();	
				 if (prefferedMode instanceof PersonalVehicle){						
					 subMajorDecisionAttributesValue.add(getPrivateUserPersonalMobilityAffective());
					 subMajorDecisionAttributesValue.add(getPrivateUserVPSecurityAffective());
					 subMajorDecisionAttributesValue.add(getPrivateUserComfortAffective());				
				 }else if (prefferedMode instanceof PublicTransport){						
					 subMajorDecisionAttributesValue.add(getPublicUserPersonalMobilityAffective());
					 subMajorDecisionAttributesValue.add(getPublicTransVPSecurityAffective());
					 subMajorDecisionAttributesValue.add(getPublicTransComfortAffective());	
				 }else if (prefferedMode instanceof Cycle){						
					 subMajorDecisionAttributesValue.add(getCycleUserPersonalMobilityAffective());
					 subMajorDecisionAttributesValue.add(getCycleVPSecurityAffective());
					 subMajorDecisionAttributesValue.add(getCycleComfortAffective());	
				 }else if (prefferedMode instanceof Walking){						
					 subMajorDecisionAttributesValue.add(getWalkingUserPersonalMobilityAffective());
					 subMajorDecisionAttributesValue.add(getWalkingVPSecurityAffective());
					 subMajorDecisionAttributesValue.add(getWalkingComfortAffective());	
				 }
				 modeSafetyAffective= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return modeSafetyAffective;				
			}				
			public double getModeSafetyAffective() {
				return modeSafetyAffective;
			}
			public double getPrivateModeSafetyAffective(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeSafetyAffective = this.getModeSafetyAffective();
				}		
				return privateModeSafetyAffective;
			}
			public double getPublicModeSafetyAffective(){
				if(prefferedMode instanceof PublicTransport){
					publicModeSafetyAffective = this.getModeSafetyAffective();
				}			
				return publicModeSafetyAffective;
			}
			public double getCycleModeSafetyAffective(){
				if(prefferedMode instanceof Cycle){
					cycleModeSafetyAffective = this.getModeSafetyAffective();
				}		
				return cycleModeSafetyAffective;
			}
			public double getWalkingModeSafetyAffective(){
				if(prefferedMode instanceof Walking){
					walkingModeSafetyAffective = this.getModeSafetyAffective();
				}			
				return walkingModeSafetyAffective;
			}
///////Safety Physical
			public double updateModeSafetyPhysical(){
				 subMajorDecisionAttributesValue= new ArrayList<Double>();	
				 if (prefferedMode instanceof PersonalVehicle){						
					 subMajorDecisionAttributesValue.add(getPrivateUserPersonalMobilityPhysical());	
					 subMajorDecisionAttributesValue.add(getPrivateUserComfortPhysical());				
				 }else if (prefferedMode instanceof PublicTransport){						
					 subMajorDecisionAttributesValue.add(getPublicUserPersonalMobilityPhysical());					
					 subMajorDecisionAttributesValue.add(getPublicTransComfortPhysical());	
				 }else if (prefferedMode instanceof Cycle){						
					 subMajorDecisionAttributesValue.add(getCycleUserPersonalMobilityPhysical());					
					 subMajorDecisionAttributesValue.add(getCycleComfortPhysical());	
				 }else if (prefferedMode instanceof Walking){						
					 subMajorDecisionAttributesValue.add(getWalkingUserPersonalMobilityPhysical());				
				 }
				 modeSafetyPhysical= prefferedMode.evaluateHigherAbstraction(subMajorDecisionAttributesValue);	
				return modeSafetyPhysical;				
			}				
			public double getModeSafetyPhysical() {
				return modeSafetyPhysical;
			}
			public double getPrivateModeSafetyPhysical(){
				if(prefferedMode instanceof PersonalVehicle){
					privateModeSafetyPhysical = this.getModeSafetyPhysical();
				}		
				return privateModeSafetyPhysical;
			}
			public double getPublicModeSafetyPhysical(){
				if(prefferedMode instanceof PublicTransport){
					publicModeSafetyPhysical = this.getModeSafetyPhysical();
				}			
				return publicModeSafetyPhysical;
			}
			public double getCycleModeSafetyPhysical(){
				if(prefferedMode instanceof Cycle){
					cycleModeSafetyPhysical = this.getModeSafetyPhysical();
				}		
				return cycleModeSafetyPhysical;
			}
			public double getWalkingModeSafetyPhysical(){
				if(prefferedMode instanceof Walking){
					walkingModeSafetyPhysical = this.getModeSafetyPhysical();
				}			
				return walkingModeSafetyPhysical;
			}
//////// Safety Counts
////Safety Cognitive counts
			public int getPrivateSafetyCognitiveCounts(){
				int privateSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyCognitiveCounts= getPrivateVPPersonalMobilityCognitiveCounts()+
							getPrivateVPSecurityCognitiveCounts()+
							getPrivateVPComfortCognitiveCounts();
							}
				return privateSafetyCognitiveCounts;	
			}		
			public int getPublicSafetyCognitiveCounts(){
				int publicSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyCognitiveCounts= getPublicVPPersonalMobilityCognitiveCounts()+
							getPublicVPSecurityCognitiveCounts()+
							getPublicVPComfortCognitiveCounts();
							}
				return publicSafetyCognitiveCounts;	
			}
			public int getCycleSafetyCognitiveCounts(){
				int cycleSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyCognitiveCounts= getCycleVPPersonalMobilityCognitiveCounts()+
							getCycleVPSecurityCognitiveCounts()+
							getCycleVPComfortCognitiveCounts();
							}
				return cycleSafetyCognitiveCounts;	
			}
			public int getWalkingSafetyCognitiveCounts(){
				int walkingSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyCognitiveCounts= getWalkingVPPersonalMobilityCognitiveCounts()+
							getWalkingVPSecurityCognitiveCounts()+
							getWalkingVPComfortCognitiveCounts();
							}
				return walkingSafetyCognitiveCounts;	
			}
///Safety Cognitive Levels
///unpleasant
			public int getPrivateSafetyUnpleasantCognitiveCounts(){
				int privateSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyCognitiveCounts= getPrivateVPPersonalMobilityUnpleasantCognitiveCounts()+
							getPrivateVPSecurityUnpleasantCognitiveCounts()+
							getPrivateVPComfortUnpleasantCognitiveCounts();
							}
				return privateSafetyCognitiveCounts;	
			}		
			public int getPublicSafetyUnpleasantCognitiveCounts(){
				int publicSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyCognitiveCounts= getPublicVPPersonalMobilityUnpleasantCognitiveCounts()+
							getPublicVPSecurityUnpleasantCognitiveCounts()+
							getPublicVPComfortUnpleasantCognitiveCounts();
							}
				return publicSafetyCognitiveCounts;	
			}
			public int getCycleSafetyUnpleasantCognitiveCounts(){
				int cycleSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyCognitiveCounts= getCycleVPPersonalMobilityUnpleasantCognitiveCounts()+
							getCycleVPSecurityUnpleasantCognitiveCounts()+
							getCycleVPComfortUnpleasantCognitiveCounts();
							}
				return cycleSafetyCognitiveCounts;	
			}
			public int getWalkingSafetyUnpleasantCognitiveCounts(){
				int walkingSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyCognitiveCounts= getWalkingVPPersonalMobilityUnpleasantCognitiveCounts()+
							getWalkingVPSecurityUnpleasantCognitiveCounts()+
							getWalkingVPComfortUnpleasantCognitiveCounts();
							}
				return walkingSafetyCognitiveCounts;	
			}
///Neither Pleasant Nor Unplesant
			public int getPrivateSafetyNeitherNorPleasantCognitiveCounts(){
				int privateSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyCognitiveCounts= getPrivateVPPersonalMobilityNeitherNorPleasantCognitiveCounts()+
							getPrivateVPSecurityNeitherNorPleasantCognitiveCounts()+
							getPrivateVPComfortNeitherNorPleasantCognitiveCounts();
							}
				return privateSafetyCognitiveCounts;	
			}		
			public int getPublicSafetyNeitherNorPleasantCognitiveCounts(){
				int publicSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyCognitiveCounts= getPublicVPPersonalMobilityNeitherNorPleasantCognitiveCounts()+
							getPublicVPSecurityNeitherNorPleasantCognitiveCounts()+
							getPublicVPComfortNeitherNorPleasantCognitiveCounts();
							}
				return publicSafetyCognitiveCounts;	
			}
			public int getCycleSafetyNeitherNorPleasantCognitiveCounts(){
				int cycleSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyCognitiveCounts= getCycleVPPersonalMobilityNeitherNorPleasantCognitiveCounts()+
							getCycleVPSecurityNeitherNorPleasantCognitiveCounts()+
							getCycleVPComfortNeitherNorPleasantCognitiveCounts();
							}
				return cycleSafetyCognitiveCounts;	
			}
			public int getWalkingSafetyNeitherNorPleasantCognitiveCounts(){
				int walkingSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyCognitiveCounts= getWalkingVPPersonalMobilityNeitherNorPleasantCognitiveCounts()+
							getWalkingVPSecurityNeitherNorPleasantCognitiveCounts()+
							getWalkingVPComfortNeitherNorPleasantCognitiveCounts();
							}
				return walkingSafetyCognitiveCounts;	
			}
/////Pleasant
			public int getPrivateSafetyPleasantCognitiveCounts(){
				int privateSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyCognitiveCounts= getPrivateVPPersonalMobilityPleasantCognitiveCounts()+
							getPrivateVPSecurityPleasantCognitiveCounts()+
							getPrivateVPComfortPleasantCognitiveCounts();
							}
				return privateSafetyCognitiveCounts;	
			}		
			public int getPublicSafetyPleasantCognitiveCounts(){
				int publicSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyCognitiveCounts= getPublicVPPersonalMobilityPleasantCognitiveCounts()+
							getPublicVPSecurityPleasantCognitiveCounts()+
							getPublicVPComfortPleasantCognitiveCounts();
							}
				return publicSafetyCognitiveCounts;	
			}
			public int getCycleSafetyPleasantCognitiveCounts(){
				int cycleSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyCognitiveCounts= getCycleVPPersonalMobilityPleasantCognitiveCounts()+
							getCycleVPSecurityPleasantCognitiveCounts()+
							getCycleVPComfortPleasantCognitiveCounts();
							}
				return cycleSafetyCognitiveCounts;	
			}
			public int getWalkingSafetyPleasantCognitiveCounts(){
				int walkingSafetyCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyCognitiveCounts= getWalkingVPPersonalMobilityPleasantCognitiveCounts()+
							getWalkingVPSecurityPleasantCognitiveCounts()+
							getWalkingVPComfortPleasantCognitiveCounts();
							}
				return walkingSafetyCognitiveCounts;	
			}		
/////Safety Affective
			public int getPrivateSafetyAffectiveCounts(){
				int privateSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyAffectiveCounts= getPrivateVPPersonalMobilityAffectiveCounts()+
							getPrivateVPSecurityAffectiveCounts()+
							getPrivateVPComfortAffectiveCounts();
							}
				return privateSafetyAffectiveCounts;	
			}		
			public int getPublicSafetyAffectiveCounts(){
				int publicSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyAffectiveCounts= getPublicVPPersonalMobilityAffectiveCounts()+
							getPublicVPSecurityAffectiveCounts()+
							getPublicVPComfortAffectiveCounts();
							}
				return publicSafetyAffectiveCounts;	
			}
			public int getCycleSafetyAffectiveCounts(){
				int cycleSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyAffectiveCounts= getCycleVPPersonalMobilityAffectiveCounts()+
							getCycleVPSecurityAffectiveCounts()+
							getCycleVPComfortAffectiveCounts();
							}
				return cycleSafetyAffectiveCounts;	
			}
			public int getWalkingSafetyAffectiveCounts(){
				int walkingSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyAffectiveCounts= getWalkingVPPersonalMobilityAffectiveCounts()+
							getWalkingVPSecurityAffectiveCounts()+
							getWalkingVPComfortAffectiveCounts();
							}
				return walkingSafetyAffectiveCounts;	
			}
//Safety Affective Levels
//unpleasant
			public int getPrivateSafetyUnpleasantAffectiveCounts(){
				int privateSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyAffectiveCounts= getPrivateVPPersonalMobilityUnpleasantAffectiveCounts()+
							getPrivateVPSecurityUnpleasantAffectiveCounts()+
							getPrivateVPComfortUnpleasantAffectiveCounts();
							}
				return privateSafetyAffectiveCounts;	
			}		
			public int getPublicSafetyUnpleasantAffectiveCounts(){
				int publicSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyAffectiveCounts= getPublicVPPersonalMobilityUnpleasantAffectiveCounts()+
							getPublicVPSecurityUnpleasantAffectiveCounts()+
							getPublicVPComfortUnpleasantAffectiveCounts();
							}
				return publicSafetyAffectiveCounts;	
			}
			public int getCycleSafetyUnpleasantAffectiveCounts(){
				int cycleSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyAffectiveCounts= getCycleVPPersonalMobilityUnpleasantAffectiveCounts()+
							getCycleVPSecurityUnpleasantAffectiveCounts()+
							getCycleVPComfortUnpleasantAffectiveCounts();
							}
				return cycleSafetyAffectiveCounts;	
			}
			public int getWalkingSafetyUnpleasantAffectiveCounts(){
				int walkingSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyAffectiveCounts= getWalkingVPPersonalMobilityUnpleasantAffectiveCounts()+
							getWalkingVPSecurityUnpleasantAffectiveCounts()+
							getWalkingVPComfortUnpleasantAffectiveCounts();
							}
				return walkingSafetyAffectiveCounts;	
			}			
///Neither pleasant nor Unpleasant
			public int getPrivateSafetyNeitherNorPleasantAffectiveCounts(){
				int privateSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyAffectiveCounts= getPrivateVPPersonalMobilityNeitherNorPleasantAffectiveCounts()+
							getPrivateVPSecurityNeitherNorPleasantAffectiveCounts()+
							getPrivateVPComfortNeitherNorPleasantAffectiveCounts();
							}
				return privateSafetyAffectiveCounts;	
			}		
			public int getPublicSafetyNeitherNorPleasantAffectiveCounts(){
				int publicSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyAffectiveCounts= getPublicVPPersonalMobilityNeitherNorPleasantAffectiveCounts()+
							getPublicVPSecurityNeitherNorPleasantAffectiveCounts()+
							getPublicVPComfortNeitherNorPleasantAffectiveCounts();
							}
				return publicSafetyAffectiveCounts;	
			}
			public int getCycleSafetyNeitherNorPleasantAffectiveCounts(){
				int cycleSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyAffectiveCounts= getCycleVPPersonalMobilityNeitherNorPleasantAffectiveCounts()+
							getCycleVPSecurityNeitherNorPleasantAffectiveCounts()+
							getCycleVPComfortNeitherNorPleasantAffectiveCounts();
							}
				return cycleSafetyAffectiveCounts;	
			}
			public int getWalkingSafetyNeitherNorPleasantAffectiveCounts(){
				int walkingSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyAffectiveCounts= getWalkingVPPersonalMobilityNeitherNorPleasantAffectiveCounts()+
							getWalkingVPSecurityNeitherNorPleasantAffectiveCounts()+
							getWalkingVPComfortNeitherNorPleasantAffectiveCounts();
							}
				return walkingSafetyAffectiveCounts;	
			}
///Pleasant
			public int getPrivateSafetyPleasantAffectiveCounts(){
				int privateSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyAffectiveCounts= getPrivateVPPersonalMobilityPleasantAffectiveCounts()+
							getPrivateVPSecurityPleasantAffectiveCounts()+
							getPrivateVPComfortPleasantAffectiveCounts();
							}
				return privateSafetyAffectiveCounts;	
			}		
			public int getPublicSafetyPleasantAffectiveCounts(){
				int publicSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyAffectiveCounts= getPublicVPPersonalMobilityPleasantAffectiveCounts()+
							getPublicVPSecurityPleasantAffectiveCounts()+
							getPublicVPComfortPleasantAffectiveCounts();
							}
				return publicSafetyAffectiveCounts;	
			}
			public int getCycleSafetyPleasantAffectiveCounts(){
				int cycleSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyAffectiveCounts= getCycleVPPersonalMobilityPleasantAffectiveCounts()+
							getCycleVPSecurityPleasantAffectiveCounts()+
							getCycleVPComfortPleasantAffectiveCounts();
							}
				return cycleSafetyAffectiveCounts;	
			}
			public int getWalkingSafetyPleasantAffectiveCounts(){
				int walkingSafetyAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyAffectiveCounts= getWalkingVPPersonalMobilityPleasantAffectiveCounts()+
							getWalkingVPSecurityPleasantAffectiveCounts()+
							getWalkingVPComfortPleasantAffectiveCounts();
							}
				return walkingSafetyAffectiveCounts;	
			}			
/////Safety Physical
			public int getPrivateSafetyPhysicalCounts(){
				int privateSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyPhysicalCounts= getPrivateVPPersonalMobilityPhysicalCounts()+
							getPrivateVPComfortPhysicalCounts();
							}
				return privateSafetyPhysicalCounts;	
			}		
			public int getPublicSafetyPhysicalCounts(){
				int publicSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyPhysicalCounts= getPublicVPPersonalMobilityPhysicalCounts()+
							getPublicVPComfortPhysicalCounts();
							}
				return publicSafetyPhysicalCounts;	
			}
			public int getCycleSafetyPhysicalCounts(){
				int cycleSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyPhysicalCounts= getCycleVPPersonalMobilityPhysicalCounts()+
							getCycleVPComfortPhysicalCounts();
							}
				return cycleSafetyPhysicalCounts;	
			}
			public int getWalkingSafetyPhysicalCounts(){
				int walkingSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyPhysicalCounts= getWalkingVPPersonalMobilityPhysicalCounts();
							}
				return walkingSafetyPhysicalCounts;	
			}
///Unpleasant
			public int getPrivateSafetyUnpleasantPhysicalCounts(){
				int privateSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyPhysicalCounts= getPrivateVPPersonalMobilityUnpleasantPhysicalCounts()+							
							getPrivateVPComfortUnpleasantPhysicalCounts();
				}
				return privateSafetyPhysicalCounts;	
			}		
			public int getPublicSafetyUnpleasantPhysicalCounts(){
				int publicSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyPhysicalCounts= getPublicVPPersonalMobilityUnpleasantPhysicalCounts()+							
							getPublicVPComfortUnpleasantPhysicalCounts();
							}
				return publicSafetyPhysicalCounts;	
			}
			public int getCycleSafetyUnpleasantPhysicalCounts(){
				int cycleSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyPhysicalCounts= getCycleVPPersonalMobilityUnpleasantPhysicalCounts()+							
							getCycleVPComfortUnpleasantPhysicalCounts();
							}
				return cycleSafetyPhysicalCounts;	
			}
			public int getWalkingSafetyUnpleasantPhysicalCounts(){
				int walkingSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyPhysicalCounts= getWalkingVPPersonalMobilityUnpleasantPhysicalCounts();
							}
				return walkingSafetyPhysicalCounts;	
			}			
///Neither pleasant nor Unpleasant
			public int getPrivateSafetyNeitherNorPleasantPhysicalCounts(){
				int privateSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyPhysicalCounts= getPrivateVPPersonalMobilityNeitherNorPleasantPhysicalCounts()+							
							getPrivateVPComfortNeitherNorPleasantPhysicalCounts();
							}
				return privateSafetyPhysicalCounts;	
			}		
			public int getPublicSafetyNeitherNorPleasantPhysicalCounts(){
				int publicSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyPhysicalCounts= getPublicVPPersonalMobilityNeitherNorPleasantPhysicalCounts()+							
							getPublicVPComfortNeitherNorPleasantPhysicalCounts();
							}
				return publicSafetyPhysicalCounts;	
			}
			public int getCycleSafetyNeitherNorPleasantPhysicalCounts(){
				int cycleSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyPhysicalCounts= getCycleVPPersonalMobilityNeitherNorPleasantPhysicalCounts()+							
							getCycleVPComfortNeitherNorPleasantPhysicalCounts();
							}
				return cycleSafetyPhysicalCounts;	
			}
			public int getWalkingSafetyNeitherNorPleasantPhysicalCounts(){
				int walkingSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyPhysicalCounts= getWalkingVPPersonalMobilityNeitherNorPleasantPhysicalCounts();							
				}
				return walkingSafetyPhysicalCounts;	
			}
///Pleasant
			public int getPrivateSafetyPleasantPhysicalCounts(){
				int privateSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSafetyPhysicalCounts= getPrivateVPPersonalMobilityPleasantPhysicalCounts()+							
							getPrivateVPComfortPleasantPhysicalCounts();
							}
				return privateSafetyPhysicalCounts;	
			}		
			public int getPublicSafetyPleasantPhysicalCounts(){
				int publicSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSafetyPhysicalCounts= getPublicVPPersonalMobilityPleasantPhysicalCounts()+							
							getPublicVPComfortPleasantPhysicalCounts();
							}
				return publicSafetyPhysicalCounts;	
			}
			public int getCycleSafetyPleasantPhysicalCounts(){
				int cycleSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSafetyPhysicalCounts= getCycleVPPersonalMobilityPleasantPhysicalCounts()+						
							getCycleVPComfortPleasantPhysicalCounts();
							}				
				return cycleSafetyPhysicalCounts;	
			}
			public int getWalkingSafetyPleasantPhysicalCounts(){
				int walkingSafetyPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSafetyPhysicalCounts= getWalkingVPPersonalMobilityPleasantPhysicalCounts();							
				}
				return walkingSafetyPhysicalCounts;	
			}		
			
///Satisfaction APC
///Satisfaction Affective To determine total affective counts at top level
			public int getPrivateSatisfactionAffectiveCounts(){
				int privateSatisfactionAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionAffectiveCounts= getPrivateEfficiencyAffectiveCounts()+
							getPrivateComfortabilityAffectiveCounts()+
							getPrivateSafetyAffectiveCounts();
							}
				return privateSatisfactionAffectiveCounts;	
			}	
			public int getPublicSatisfactionAffectiveCounts(){
				int publicSatisfactionAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionAffectiveCounts= getPublicEfficiencyAffectiveCounts()+
							getPublicComfortabilityAffectiveCounts()+
							getPublicSafetyAffectiveCounts();
							}
				return publicSatisfactionAffectiveCounts;	
			}
			public int getCycleSatisfactionAffectiveCounts(){
				int cycleSatisfactionAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionAffectiveCounts= getCycleEfficiencyAffectiveCounts()+
							getCycleComfortabilityAffectiveCounts()+
							getCycleSafetyAffectiveCounts();
							}
				return cycleSatisfactionAffectiveCounts;	
			}
			public int getWalkingSatisfactionAffectiveCounts(){
				int walkingSatisfactionAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionAffectiveCounts= getWalkingEfficiencyAffectiveCounts()+
							getWalkingComfortabilityAffectiveCounts()+
							getWalkingSafetyAffectiveCounts();
							}
				return walkingSatisfactionAffectiveCounts;	
			}
///Satisfaction Cognitive to determine the total cognitive count at top level		
			public int getPrivateSatisfactionCognitiveCounts(){
				int privateSatisfactionCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionCognitiveCounts= getPrivateEfficiencyCognitiveCounts()+
							getPrivateComfortabilityCognitiveCounts()+
							getPrivateSafetyCognitiveCounts();
							}
				return privateSatisfactionCognitiveCounts;	
			}	
			public int getPublicSatisfactionCognitiveCounts(){
				int publicSatisfactionCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionCognitiveCounts= getPublicEfficiencyCognitiveCounts()+
							getPublicComfortabilityCognitiveCounts()+
							getPublicSafetyCognitiveCounts();
							}
				return publicSatisfactionCognitiveCounts;	
			}
			public int getCycleSatisfactionCognitiveCounts(){
				int cycleSatisfactionCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionCognitiveCounts= getCycleEfficiencyCognitiveCounts()+
							getCycleComfortabilityCognitiveCounts()+
							getCycleSafetyCognitiveCounts();
							}
				return cycleSatisfactionCognitiveCounts;	
			}
			public int getWalkingSatisfactionCognitiveCounts(){
				int walkingSatisfactionCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionCognitiveCounts= getWalkingEfficiencyCognitiveCounts()+
							getWalkingComfortabilityCognitiveCounts()+
							getWalkingSafetyCognitiveCounts();
							}
				return walkingSatisfactionCognitiveCounts;	
			}
///Satisfcation Physical: To deterine the total physical count at top level
			public int getPrivateSatisfactionPhysicalCounts(){
				int privateSatisfactionPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionPhysicalCounts= getPrivateComfortabilityPhysicalCounts()+
							getPrivateSafetyPhysicalCounts();
							}
				return privateSatisfactionPhysicalCounts;	
			}	
			public int getPublicSatisfactionPhysicalCounts(){
				int publicSatisfactionPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionPhysicalCounts= getPublicComfortabilityPhysicalCounts()+
							getPublicSafetyPhysicalCounts();
							}
				return publicSatisfactionPhysicalCounts;	
			}
			public int getCycleSatisfactionPhysicalCounts(){
				int cycleSatisfactionPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionPhysicalCounts= getCycleComfortabilityPhysicalCounts()+
							getCycleSafetyPhysicalCounts();
							}
				return cycleSatisfactionPhysicalCounts;	
			}
			public int getWalkingSatisfactionPhysicalCounts(){
				int walkingSatisfactionPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionPhysicalCounts= getWalkingComfortabilityPhysicalCounts()+
							getWalkingSafetyCognitiveCounts();
							}
				return walkingSatisfactionPhysicalCounts;	
			}
///Satisfaction Cognitive levels: to determine total unpleasant cognitive counts at top level
			public int getPrivateSatisfactionUnpleasantCognitiveCounts(){
				int privateSatisfactionUnpleasantCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionUnpleasantCognitiveCounts= getPrivateEfficiencyUnpleasantCognitiveCounts()+
							getPrivateComfortabilityUnpleasantCognitiveCounts()+
							getPrivateSafetyUnpleasantCognitiveCounts();
							}
				return privateSatisfactionUnpleasantCognitiveCounts;	
			}
			public int getPublicSatisfactionUnpleasantCognitiveCounts(){
				int publicSatisfactionUnpleasantCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionUnpleasantCognitiveCounts= getPublicEfficiencyUnpleasantCognitiveCounts()+
							getPublicComfortabilityUnpleasantCognitiveCounts()+
							getPublicSafetyUnpleasantCognitiveCounts();
							}
				return publicSatisfactionUnpleasantCognitiveCounts;	
			}
			public int getCycleSatisfactionUnpleasantCognitiveCounts(){
				int cycleSatisfactionUnpleasantCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionUnpleasantCognitiveCounts= getCycleEfficiencyUnpleasantCognitiveCounts()+
							getCycleComfortabilityUnpleasantCognitiveCounts()+
							getCycleSafetyUnpleasantCognitiveCounts();
							}
				return cycleSatisfactionUnpleasantCognitiveCounts;	
			}
			public int getWalkingSatisfactionUnpleasantCognitiveCounts(){
				int walkingSatisfactionUnpleasantCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionUnpleasantCognitiveCounts= getWalkingEfficiencyUnpleasantCognitiveCounts()+
							getWalkingComfortabilityUnpleasantCognitiveCounts()+
							getWalkingSafetyUnpleasantCognitiveCounts();
							}
				return walkingSatisfactionUnpleasantCognitiveCounts;	
			}
/////to determine total Neither Pleasant nor Unplesanat Satsifaction Cognitive counts
			public int getPrivateSatisfactionNeitherNorPleasantCognitiveCounts(){
				int privateSatisfactionNeitherNorPleasantCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionNeitherNorPleasantCognitiveCounts= getPrivateEfficiencyNeitherNorPleasantCognitiveCounts()+
							getPrivateComfortabilityNeitherNorPleasantCognitiveCounts()+
							getPrivateSafetyNeitherNorPleasantCognitiveCounts();
							}
				return privateSatisfactionNeitherNorPleasantCognitiveCounts;	
			}
			public int getPublicSatisfactionNeitherNorPleasantCognitiveCounts(){
				int publicSatisfactionNeitherNorPleasantCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionNeitherNorPleasantCognitiveCounts= getPublicEfficiencyNeitherNorPleasantCognitiveCounts()+
							getPublicComfortabilityNeitherNorPleasantCognitiveCounts()+
							getPublicSafetyNeitherNorPleasantCognitiveCounts();
							}
				return publicSatisfactionNeitherNorPleasantCognitiveCounts;	
			}
			public int getCycleSatisfactionNeitherNorPleasantCognitiveCounts(){
				int cycleSatisfactionNeitherNorPleasantCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionNeitherNorPleasantCognitiveCounts= getCycleEfficiencyNeitherNorPleasantCognitiveCounts()+
							getCycleComfortabilityNeitherNorPleasantCognitiveCounts()+
							getCycleSafetyNeitherNorPleasantCognitiveCounts();
							}
				return cycleSatisfactionNeitherNorPleasantCognitiveCounts;	
			}
			public int getWalkingSatisfactionNeitherNorPleasantCognitiveCounts(){
				int walkingSatisfactionNeitherNorPleasantCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionNeitherNorPleasantCognitiveCounts= getWalkingEfficiencyNeitherNorPleasantCognitiveCounts()+
							getWalkingComfortabilityNeitherNorPleasantCognitiveCounts()+
							getWalkingSafetyNeitherNorPleasantCognitiveCounts();
							}
				return walkingSatisfactionNeitherNorPleasantCognitiveCounts;	
			}
///to determine total Pleasant Satsifaction at top level
			public int getPrivateSatisfactionPleasantCognitiveCounts(){
				int privateSatisfactionPleasantCognitiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionPleasantCognitiveCounts= getPrivateEfficiencyPleasantCognitiveCounts()+
							getPrivateComfortabilityPleasantCognitiveCounts()+
							getPrivateSafetyPleasantCognitiveCounts();
							}
				return privateSatisfactionPleasantCognitiveCounts;	
			}
			public int getPublicSatisfactionPleasantCognitiveCounts(){
				int publicSatisfactionPleasantCognitiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionPleasantCognitiveCounts= getPublicEfficiencyPleasantCognitiveCounts()+
							getPublicComfortabilityPleasantCognitiveCounts()+
							getPublicSafetyPleasantCognitiveCounts();
							}
				return publicSatisfactionPleasantCognitiveCounts;	
			}
			public int getCycleSatisfactionPleasantCognitiveCounts(){
				int cycleSatisfactionPleasantCognitiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionPleasantCognitiveCounts= getCycleEfficiencyPleasantCognitiveCounts()+
							getCycleComfortabilityPleasantCognitiveCounts()+
							getCycleSafetyPleasantCognitiveCounts();
							}
				return cycleSatisfactionPleasantCognitiveCounts;	
			}
			public int getWalkingSatisfactionPleasantCognitiveCounts(){
				int walkingSatisfactionPleasantCognitiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionPleasantCognitiveCounts= getWalkingEfficiencyPleasantCognitiveCounts()+
							getWalkingComfortabilityPleasantCognitiveCounts()+
							getWalkingSafetyPleasantCognitiveCounts();
							}
				return walkingSatisfactionPleasantCognitiveCounts;	
			}
//to determine total Unpleasant affective Satisfaction at top level
			public int getPrivateSatisfactionUnpleasantAffectiveCounts(){
				int privateSatisfactionUnpleasantAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionUnpleasantAffectiveCounts= getPrivateEfficiencyUnpleasantAffectiveCounts()+
							getPrivateComfortabilityUnpleasantAffectiveCounts()+
							getPrivateSafetyUnpleasantAffectiveCounts();
							}
				return privateSatisfactionUnpleasantAffectiveCounts;	
			}
			public int getPublicSatisfactionUnpleasantAffectiveCounts(){
				int publicSatisfactionUnpleasantAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionUnpleasantAffectiveCounts= getPublicEfficiencyUnpleasantAffectiveCounts()+
							getPublicComfortabilityUnpleasantAffectiveCounts()+
							getPublicSafetyUnpleasantAffectiveCounts();
							}
				return publicSatisfactionUnpleasantAffectiveCounts;	
			}
			public int getCycleSatisfactionUnpleasantAffectiveCounts(){
				int cycleSatisfactionUnpleasantAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionUnpleasantAffectiveCounts= getCycleEfficiencyUnpleasantAffectiveCounts()+
							getCycleComfortabilityUnpleasantAffectiveCounts()+
							getCycleSafetyUnpleasantAffectiveCounts();
							}
				return cycleSatisfactionUnpleasantAffectiveCounts;	
			}
			public int getWalkingSatisfactionUnpleasantAffectiveCounts(){
				int walkingSatisfactionUnpleasantAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionUnpleasantAffectiveCounts= getWalkingEfficiencyUnpleasantAffectiveCounts()+
							getWalkingComfortabilityUnpleasantAffectiveCounts()+
							getWalkingSafetyUnpleasantAffectiveCounts();
							}
				return walkingSatisfactionUnpleasantAffectiveCounts;	
			}
///// To determine total Neither Pleasant nor Unpleasant Affective Satisfaction counts at top level
			public int getPrivateSatisfactionNeitherNorPleasantAffectiveCounts(){
				int privateSatisfactionNeitherNorPleasantAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionNeitherNorPleasantAffectiveCounts= getPrivateEfficiencyNeitherNorPleasantAffectiveCounts()+
							getPrivateComfortabilityNeitherNorPleasantAffectiveCounts()+
							getPrivateSafetyNeitherNorPleasantAffectiveCounts();
							}
				return privateSatisfactionNeitherNorPleasantAffectiveCounts;	
			}
			public int getPublicSatisfactionNeitherNorPleasantAffectiveCounts(){
				int publicSatisfactionNeitherNorPleasantAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionNeitherNorPleasantAffectiveCounts= getPublicEfficiencyNeitherNorPleasantAffectiveCounts()+
							getPublicComfortabilityNeitherNorPleasantAffectiveCounts()+
							getPublicSafetyNeitherNorPleasantAffectiveCounts();
							}
				return publicSatisfactionNeitherNorPleasantAffectiveCounts;	
			}
			public int getCycleSatisfactionNeitherNorPleasantAffectiveCounts(){
				int cycleSatisfactionNeitherNorPleasantAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionNeitherNorPleasantAffectiveCounts= getCycleEfficiencyNeitherNorPleasantAffectiveCounts()+
							getCycleComfortabilityNeitherNorPleasantAffectiveCounts()+
							getCycleSafetyNeitherNorPleasantAffectiveCounts();
							}
				return cycleSatisfactionNeitherNorPleasantAffectiveCounts;	
			}
			public int getWalkingSatisfactionNeitherNorPleasantAffectiveCounts(){
				int walkingSatisfactionNeitherNorPleasantAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionNeitherNorPleasantAffectiveCounts= getWalkingEfficiencyNeitherNorPleasantAffectiveCounts()+
							getWalkingComfortabilityNeitherNorPleasantAffectiveCounts()+
							getWalkingSafetyNeitherNorPleasantAffectiveCounts();
							}
				return walkingSatisfactionNeitherNorPleasantAffectiveCounts;	
			}
///To determien total Pleasant Affective Satisfaction Count at top level;
			public int getPrivateSatisfactionPleasantAffectiveCounts(){
				int privateSatisfactionPleasantAffectiveCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionPleasantAffectiveCounts= getPrivateEfficiencyPleasantAffectiveCounts()+
							getPrivateComfortabilityPleasantAffectiveCounts()+
							getPrivateSafetyPleasantAffectiveCounts();
							}
				return privateSatisfactionPleasantAffectiveCounts;	
			}
			public int getPublicSatisfactionPleasantAffectiveCounts(){
				int publicSatisfactionPleasantAffectiveCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionPleasantAffectiveCounts= getPublicEfficiencyPleasantAffectiveCounts()+
							getPublicComfortabilityPleasantAffectiveCounts()+
							getPublicSafetyPleasantAffectiveCounts();
							}
				return publicSatisfactionPleasantAffectiveCounts;	
			}
			public int getCycleSatisfactionPleasantAffectiveCounts(){
				int cycleSatisfactionPleasantAffectiveCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionPleasantAffectiveCounts= getCycleEfficiencyPleasantAffectiveCounts()+
							getCycleComfortabilityPleasantAffectiveCounts()+
							getCycleSafetyPleasantAffectiveCounts();
							}
				return cycleSatisfactionPleasantAffectiveCounts;	
			}
			public int getWalkingSatisfactionPleasantAffectiveCounts(){
				int walkingSatisfactionPleasantAffectiveCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionPleasantAffectiveCounts= getWalkingEfficiencyPleasantAffectiveCounts()+
							getWalkingComfortabilityPleasantAffectiveCounts()+
							getWalkingSafetyPleasantAffectiveCounts();
							}
				return walkingSatisfactionPleasantAffectiveCounts;	
			}	
///To determine total Unpleasant physical Satisfaction at top level			
			public int getPrivateSatisfactionUnpleasantPhysicalCounts(){
				int privateSatisfactionUnpleasantPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionUnpleasantPhysicalCounts= getPrivateComfortabilityUnpleasantPhysicalCounts()+
							getPrivateSafetyUnpleasantPhysicalCounts();
							}
				return privateSatisfactionUnpleasantPhysicalCounts;	
			}
			public int getPublicSatisfactionUnpleasantPhysicalCounts(){
				int publicSatisfactionUnpleasantPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionUnpleasantPhysicalCounts= getPublicComfortabilityUnpleasantPhysicalCounts()+
							getPublicSafetyUnpleasantPhysicalCounts();
							}
				return publicSatisfactionUnpleasantPhysicalCounts;	
			}
			public int getCycleSatisfactionUnpleasantPhysicalCounts(){
				int cycleSatisfactionUnpleasantPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionUnpleasantPhysicalCounts= getCycleComfortabilityUnpleasantPhysicalCounts()+
							getCycleSafetyUnpleasantPhysicalCounts();
							}
				return cycleSatisfactionUnpleasantPhysicalCounts;	
			}
			public int getWalkingSatisfactionUnpleasantPhysicalCounts(){
				int walkingSatisfactionUnpleasantPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionUnpleasantPhysicalCounts= getWalkingComfortabilityUnpleasantPhysicalCounts()+
							getWalkingSafetyUnpleasantPhysicalCounts();
							}
				return walkingSatisfactionUnpleasantPhysicalCounts;	
			}
///To detremine total NeitherPleasantNorUnpleasant physical Satisfaction at top level				
			public int getPrivateSatisfactionNeitherNorPleasantPhysicalCounts(){
				int privateSatisfactionNeitherNorPleasantPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionNeitherNorPleasantPhysicalCounts= getPrivateComfortabilityNeitherNorPleasantPhysicalCounts()+
							getPrivateSafetyUnpleasantPhysicalCounts();
							}
				return privateSatisfactionNeitherNorPleasantPhysicalCounts;	
			}
			public int getPublicSatisfactionNeitherNorPleasantPhysicalCounts(){
				int publicSatisfactionNeitherNorPleasantPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionNeitherNorPleasantPhysicalCounts= getPublicComfortabilityNeitherNorPleasantPhysicalCounts()+
							getPublicSafetyNeitherNorPleasantPhysicalCounts();
							}
				return publicSatisfactionNeitherNorPleasantPhysicalCounts;	
			}
			public int getCycleSatisfactionNeitherNorPleasantPhysicalCounts(){
				int cycleSatisfactionNeitherNorPleasantPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionNeitherNorPleasantPhysicalCounts= getCycleComfortabilityNeitherNorPleasantPhysicalCounts()+
							getCycleSafetyNeitherNorPleasantPhysicalCounts();
							}
				return cycleSatisfactionNeitherNorPleasantPhysicalCounts;	
			}
			public int getWalkingSatisfactionNeitherNorPleasantPhysicalCounts(){
				int walkingSatisfactionNeitherNorPleasantPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionNeitherNorPleasantPhysicalCounts= getWalkingComfortabilityNeitherNorPleasantPhysicalCounts()+
							getWalkingSafetyNeitherNorPleasantPhysicalCounts();
							}
				return walkingSatisfactionNeitherNorPleasantPhysicalCounts;	
			}
////To determine total Pleasant physical Satisfaction at top level 
			public int getPrivateSatisfactionPleasantPhysicalCounts(){
				int privateSatisfactionPleasantPhysicalCounts =0;				
				if (prefferedMode instanceof PersonalVehicle)	{	
					privateSatisfactionPleasantPhysicalCounts= getPrivateComfortabilityPleasantPhysicalCounts()+
							getPrivateSafetyPleasantPhysicalCounts();
							}
				return privateSatisfactionPleasantPhysicalCounts;	
			}
			public int getPublicSatisfactionPleasantPhysicalCounts(){
				int publicSatisfactionPleasantPhysicalCounts =0;				
				if (prefferedMode instanceof PublicTransport)	{	
					publicSatisfactionPleasantPhysicalCounts= getPublicComfortabilityPleasantPhysicalCounts()+
							getPublicSafetyPleasantPhysicalCounts();
							}
				return publicSatisfactionPleasantPhysicalCounts;	
			}
			public int getCycleSatisfactionPleasantPhysicalCounts(){
				int cycleSatisfactionPleasantPhysicalCounts =0;				
				if (prefferedMode instanceof Cycle)	{	
					cycleSatisfactionPleasantPhysicalCounts= getCycleComfortabilityPleasantPhysicalCounts()+
							getCycleSafetyPleasantPhysicalCounts();
							}
				return cycleSatisfactionPleasantPhysicalCounts;	
			}
			public int getWalkingSatisfactionPleasantPhysicalCounts(){
				int walkingSatisfactionPleasantPhysicalCounts =0;				
				if (prefferedMode instanceof Walking)	{	
					walkingSatisfactionPleasantPhysicalCounts= getWalkingComfortabilityPleasantPhysicalCounts()+
							getWalkingSafetyPleasantPhysicalCounts();
							}
				return walkingSatisfactionPleasantPhysicalCounts;	
			}
}
