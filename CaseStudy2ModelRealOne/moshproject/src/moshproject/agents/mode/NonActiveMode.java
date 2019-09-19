package moshproject.agents.mode;

import moshproject.agents.intervener.Intervener2;
import moshproject.common.Constants;
import repast.simphony.random.RandomHelper;

public class NonActiveMode extends Mode {
	//////		
	public NonActiveMode() {	
		setOthersAttitude();
		setSideWalk();
		setRoadCrossing();
		setRouteAvailability();		
		setFacilitiesAtDest();
		setJourneyTime();
		setLuggageCarrier();	
	}

	private void setSideWalk(){
		setValueToAttribute(Constants.paths, RandomHelper.nextDoubleFromTo(0.01,0.1));			
	}
	private void setRoadCrossing(){		
		setValueToAttribute(Constants.crossingAndRoadsigns, RandomHelper.nextDoubleFromTo(0.01,0.1));		
	}
	private void setOthersAttitude(){			
		setValueToAttribute(Constants.attitudeOfOthers, RandomHelper.nextDoubleFromTo(0.01,0.1));	
		
	}
	private void setRouteAvailability(){
		setValueToAttribute(Constants.routeAvailabilityAndObstruction, RandomHelper.nextDoubleFromTo(0.01,0.1));			
	}
	private void setFacilitiesAtDest(){
		setValueToAttribute(Constants.facilitiesAtDestination, RandomHelper.nextDoubleFromTo(0.01,0.1));			
	}
	private void setJourneyTime(){
		setValueToAttribute(Constants.journeyTimeConsideration, RandomHelper.nextDoubleFromTo(0.01,0.1));			
	}
	private void setLuggageCarrier(){
		setValueToAttribute(Constants.capabilityForLuggageCarrier, RandomHelper.nextDoubleFromTo(0.01,0.1));				
	}
		
}
