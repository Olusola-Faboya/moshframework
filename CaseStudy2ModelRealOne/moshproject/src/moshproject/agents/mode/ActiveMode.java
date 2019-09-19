package moshproject.agents.mode;

import java.util.ArrayList;
import java.util.List;

import moshproject.common.Constants;
import repast.simphony.random.RandomHelper;

public class ActiveMode extends Mode{

	
	public ActiveMode() {	
//	This section/constructor adds modes attributes from the questionnaire 
		setSideWalk();
		setRoadCrossing();
		setRouteAvailability();
		setOthersAttitude();
		setFacilitiesAtDest();
		setJourneyTime();
		setLuggageCarrier();		
	}
////// set the importance values
	private static final double Group1 = 0.238; 
	private static final double Group2 = Group1+0.428;
	private static final double Group3 = Group2+0.333;
	
	private void setSideWalk(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.paths, RandomHelper.nextDoubleFromTo(0.25,0.75));			
		}else if(r<=Group2){		
			setValueToAttribute(Constants.paths,RandomHelper.nextDoubleFromTo(0.5,1.0) );
		}else if (r<=Group3) {
			setValueToAttribute(Constants.paths,RandomHelper.nextDoubleFromTo(0.25,1.0) );
		}
	}
	private void setRoadCrossing(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.crossingAndRoadsigns, RandomHelper.nextDoubleFromTo(0.5,1.0));			
		}else if(r<=Group2){		
			setValueToAttribute(Constants.crossingAndRoadsigns,RandomHelper.nextDoubleFromTo(0.25,1.0) );
		}else if (r<=Group3) {
			setValueToAttribute(Constants.crossingAndRoadsigns,RandomHelper.nextDoubleFromTo(0.75,1.0) );
		}
	}
	private void setOthersAttitude(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.attitudeOfOthers, RandomHelper.nextDoubleFromTo(0.25,0.75));			
		}else if(r<=Group2){		
			setValueToAttribute(Constants.attitudeOfOthers,RandomHelper.nextDoubleFromTo(0.0,1.0) );
		}else if (r<=Group3) {
			setValueToAttribute(Constants.attitudeOfOthers,RandomHelper.nextDoubleFromTo(0.0,1.0) );
		}
	}
	private void setRouteAvailability(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.routeAvailabilityAndObstruction, RandomHelper.nextDoubleFromTo(0.5,0.75));			
		}else if(r<=Group2){		
			setValueToAttribute(Constants.routeAvailabilityAndObstruction, RandomHelper.nextDoubleFromTo(0.25,0.75));
		}else if (r<=Group3) {
			setValueToAttribute(Constants.routeAvailabilityAndObstruction, RandomHelper.nextDoubleFromTo(0.5,1.0));
		}
	}
	private void setFacilitiesAtDest(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.facilitiesAtDestination, RandomHelper.nextDoubleFromTo(0.75,1.0));			
		}else if(r<=Group2){		
			setValueToAttribute(Constants.facilitiesAtDestination, RandomHelper.nextDoubleFromTo(0.5,1.0));
		}else if (r<=Group3) {
			setValueToAttribute(Constants.facilitiesAtDestination, RandomHelper.nextDoubleFromTo(0.5,1.0));
		}
	}
	private void setJourneyTime(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.journeyTimeConsideration, RandomHelper.nextDoubleFromTo(0.5,1.0));			
		}else if(r<=Group2){		
			setValueToAttribute(Constants.journeyTimeConsideration, RandomHelper.nextDoubleFromTo(0.25,1.0));
		}else if (r<=Group3) {
			setValueToAttribute(Constants.journeyTimeConsideration, RandomHelper.nextDoubleFromTo(0.25,1.0));
		}
	}
	private void setLuggageCarrier(){
		double r = rand.nextDouble();
		if(r<=Group1){		
			setValueToAttribute(Constants.capabilityForLuggageCarrier, 0.5);			
		}else if(r<=Group2){		
			setValueToAttribute(Constants.capabilityForLuggageCarrier, RandomHelper.nextDoubleFromTo(0.25,0.75));
		}else if (r<=Group3) {
			setValueToAttribute(Constants.capabilityForLuggageCarrier, RandomHelper.nextDoubleFromTo(0.25,1.0));
		}
	}

}
