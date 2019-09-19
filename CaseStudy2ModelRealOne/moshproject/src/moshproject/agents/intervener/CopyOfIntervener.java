package moshproject.agents.intervener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import moshproject.agent.behaviour.Behaviour;
import moshproject.agents.passenger.NonActiveTraveller;
import moshproject.agents.passenger.Passenger;
import moshproject.common.Constants;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class CopyOfIntervener {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	Random rand = new Random();
	InterventionPanel interventionPanel;
	
	ArrayList<Passenger> passengers;	
	public ArrayList<Passenger> getPassengers() {
		return passengers;
}

	boolean sideWalksStatus;
	public void setSideWalksStatus(boolean b){this.sideWalksStatus = b;}
	public boolean isSideWalksStatus(){return  sideWalksStatus;}
	
	boolean journeyTimeStatus;
	public boolean isJourneyTimeStatus() {return journeyTimeStatus;}
	public void setJourneyTimeStatus(boolean journeyTimeStatus) {this.journeyTimeStatus = journeyTimeStatus;}
	
	boolean roadCrossingStatus;	
	public boolean isRoadCrossingStatus(){return roadCrossingStatus;}
	public void setRoadCrossingStatus(boolean roadCrossingStatus){this.roadCrossingStatus = roadCrossingStatus;}

	boolean routeAvailabilityStatus;	
	public boolean isRouteAvailabilityStatus() {return routeAvailabilityStatus;}
	public void setRouteAvailabilityStatus(boolean routeAvailabilityStatus){this.routeAvailabilityStatus = routeAvailabilityStatus;}

	boolean attitudeOfOthersStatus;	
	public boolean isAttitudeOfOthersStatus() {return attitudeOfOthersStatus;}
	public void setAttitudeOfOthersStatus(boolean attitudeOfOthersStatus){this.attitudeOfOthersStatus = attitudeOfOthersStatus;}

	boolean luggageCarrierStatus;	
	public boolean isLuggageCarrierStatus() {return luggageCarrierStatus;}
	public void setLuggageCarrierStatus(boolean luggageCarrierStatus) {this.luggageCarrierStatus = luggageCarrierStatus;}

	boolean facilitiesAtDestStatus;
	public boolean isFacilitiesAtDestStatus() {return facilitiesAtDestStatus;}
	public void setFacilitiesAtDestStatus(boolean facilitiesAtDestStatus) {this.facilitiesAtDestStatus = facilitiesAtDestStatus;}
	
	public CopyOfIntervener(ContinuousSpace<Object> space, Grid<Object> grid) {	
		this.space = space;
		this.grid = grid;		
		this.sideWalksStatus=false;
		this.journeyTimeStatus=false;
		this.roadCrossingStatus=false;
		this.routeAvailabilityStatus=false;
		this.attitudeOfOthersStatus=false;
		this.luggageCarrierStatus=false;
		this.facilitiesAtDestStatus=false;	
	}	
	public void initialize() {
		// TODO Auto-generated method stub		
	}		
////Gather Non Active Travellers
	public List<Passenger> getNonActiveTravellers(){
		Iterable<Passenger> passenger = RunState.getInstance().getMasterContext().getObjects(Passenger.class);
		passengers = new ArrayList<Passenger>();
			for(Passenger passes: passenger){
				if(passes.passType instanceof NonActiveTraveller){//&&(passes.getBehaviour()== Behaviour.OPTIMISE)){//&&(passes.getModeSatisfaction()<=0.5)){
					passengers.add(passes);
				}
			}		
		return passengers;		
	}
	
	public List<Passenger> getCheckedNonActiveTravellers(){
		ArrayList<Passenger> checkedPassengers;
		getNonActiveTravellers();
		checkedPassengers = new ArrayList<Passenger>();
			for(Passenger passes: passengers){
				if(isSideWalksStatus()){
					passes.setSideWalkInterventionSet(true);					
				}
				if(isRoadCrossingStatus()){
					passes.setRoadCrossingInterventionSet(true);					
				}
				if(isAttitudeOfOthersStatus()){
					passes.setAttitudeOfOthersInterventionSet(true);					
				}
				if(isRouteAvailabilityStatus()){
					passes.setRouteAvailabilityInterventionSet(true);					
				}
				if(isFacilitiesAtDestStatus()){
					passes.setFacilitiesAtDestInterventionSet(true);					
				}
				if(isJourneyTimeStatus()){
					passes.setJourneyStatusInterventionSet(true);					
				}
				if(isLuggageCarrierStatus()){
					passes.setLuggageCarrierInterventionSet(true);					
				}				
				checkedPassengers.add(passes);
			}		
		return checkedPassengers;		
	}
	
	@ScheduledMethod(start =15, interval=1)
	public void applyIntervention(){		
	ArrayList<Passenger> selectedPassenger;
	Passenger pass =null;	
		if(isSideWalksStatus()){
			getCheckedNonActiveTravellers();
			System.out.println("sidewalk is selected");
			double r =rand.nextDouble();
			double Group1 =0.125;
			double Group2=Group1+0.0625;
			double Group3=Group2+0.125;
			double Group4 =Group3+0.3125;			
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.333);
					for (int i =0; i< numberOfElements; i++){
						pass=passengers.get(i);				
								selectedPassenger.add(pass) ;						
					}	
				}				
				for(Passenger passenger: selectedPassenger){					
					System.out.println(passenger);
					//if(passenger.getBehaviour()== Behaviour.OPTIMISE){
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
				//		System.out.println("sidewalk is " +isSideWalksStatus());
						if(r<=Group1){
							passenger.getPrefferedMode().setValueToAttribute(Constants.paths,0.0);
						}else if (r<=Group2){
							passenger.getPrefferedMode().setValueToAttribute(Constants.paths,0.25);
						}else if (r<=Group3){
							passenger.getPrefferedMode().setValueToAttribute(Constants.paths,0.5);
						}else if (r<=Group4){
							passenger.getPrefferedMode().setValueToAttribute(Constants.paths,0.75);
						}else{
							passenger.getPrefferedMode().setValueToAttribute(Constants.paths,1.0);
						}
					//}
				}	
			}
		
			
		if(isRoadCrossingStatus()){
			getCheckedNonActiveTravellers();
			System.out.println("Road crossing is selected");
			double r =rand.nextDouble();
			double Group1 =0.25;
			double Group2=Group1+0.125;		
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.04);
					for (int i =0; i< numberOfElements; i++){
						pass=passengers.get(i);					
								selectedPassenger.add(pass) ;
					}	
				}	
				for(Passenger passenger: selectedPassenger){
					//if(passenger.getBehaviour()== Behaviour.OPTIMISE){
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
					//	passenger.setRoadCrossingInterventionSet(true);
						System.out.println(passenger);
						if(r<=Group1){
							passenger.getPrefferedMode().setValueToAttribute(Constants.crossingAndRoadsigns,0.0);
						}else if (r<=Group2){
							passenger.getPrefferedMode().setValueToAttribute(Constants.crossingAndRoadsigns,0.25);
						}else{
							passenger.getPrefferedMode().setValueToAttribute(Constants.crossingAndRoadsigns,0.75);
						}
					//}					
				}				
		}
		
		if(isAttitudeOfOthersStatus()){
			getCheckedNonActiveTravellers();
			System.out.println("attitude of Others is selected");
			double r =rand.nextDouble();
			double Group1 =0.1304;
			double Group2=Group1+0.087;	
			double Group3=Group2+0.435;
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){
					int numberOfElements = (int) (((int) passengers.size())*0.314);
					for (int i =0; i< numberOfElements; i++){
						pass=passengers.get(i);					
								selectedPassenger.add(pass) ;
					}	
				}	
				for(Passenger passenger: selectedPassenger){
					//if(passenger.getBehaviour()== Behaviour.OPTIMISE){
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
					//	passenger.setAttitudeOfOthersInterventionSet(true);
						System.out.println(passenger);
							if(r<=Group1){
								passenger.getPrefferedMode().setValueToAttribute(Constants.attitudeOfOthers,0.0);
							}else if (r<=Group2){
								passenger.getPrefferedMode().setValueToAttribute(Constants.attitudeOfOthers,0.5);
							}else if (r<=Group3){
								passenger.getPrefferedMode().setValueToAttribute(Constants.attitudeOfOthers,0.75);
							}else{
								passenger.getPrefferedMode().setValueToAttribute(Constants.attitudeOfOthers,1.0);
							}
					//}
					
				}				
		}
		
		if(isRouteAvailabilityStatus()){
			getCheckedNonActiveTravellers();
			System.out.println("Routes Availability is selected");
			double r =rand.nextDouble();
			double Group1 =0.154;
			double Group2=Group1+0.077;	
			double Group3=Group2+0.3077;			
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){
					int numberOfElements = (int) (((int) passengers.size())*0.1765);
					for (int i =0; i< numberOfElements; i++){
						pass=passengers.get(i);						
								selectedPassenger.add(pass) ;
						}										
				}
				for(Passenger passenger: selectedPassenger){
					//if(passenger.getBehaviour()== Behaviour.OPTIMISE){
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
					//	passenger.setRouteAvailabilityInterventionSet(true);
						System.out.println(passenger);
						if(r<=Group1){
							passenger.getPrefferedMode().setValueToAttribute(Constants.routeAvailabilityAndObstruction,0.0);
						}else if (r<=Group2){
							passenger.getPrefferedMode().setValueToAttribute(Constants.routeAvailabilityAndObstruction,0.5);
						}else if (r<=Group3){
							passenger.getPrefferedMode().setValueToAttribute(Constants.routeAvailabilityAndObstruction,0.75);
						}else{
							passenger.getPrefferedMode().setValueToAttribute(Constants.routeAvailabilityAndObstruction,1.0);
					}
				//}
					
			}				
		}
		
		if(isFacilitiesAtDestStatus()){
			getCheckedNonActiveTravellers();
			System.out.println("Facilities At destinantion is selected");
			double r =rand.nextDouble();
			double Group1 =0.231;
			double Group2=Group1+0.3077;			
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){
					int numberOfElements = (int) (((int) passengers.size())*0.1765);
					for (int i =0; i< numberOfElements; i++){
						pass=passengers.get(i);					
								selectedPassenger.add(pass) ;
					}							
				}
				for(Passenger passenger: selectedPassenger){
					//if(passenger.getBehaviour()== Behaviour.OPTIMISE){
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
					//	passenger.setFacilitiesAtDestInterventionSet(true);
						//passenger.setBehaviour(Behaviour.OPTIMISE);
						System.out.println(passenger);
						if(r<=Group1){
							passenger.getPrefferedMode().setValueToAttribute(Constants.facilitiesAtDestination,0.0);
						}else if (r<=Group2){
							passenger.getPrefferedMode().setValueToAttribute(Constants.facilitiesAtDestination,0.5);
						}else{
							passenger.getPrefferedMode().setValueToAttribute(Constants.facilitiesAtDestination,0.75);
						}
					//}
				}				
		}
		
		if(isJourneyTimeStatus()){
			getCheckedNonActiveTravellers();
			System.out.println("Journey Time is selected");
			double r =rand.nextDouble();
			double Group1 =0.5;
			double Group2=Group1+0.375;				
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){
					int numberOfElements = (int) (((int) passengers.size())*0.118);
					for (int i =0; i< numberOfElements; i++){
						pass=passengers.get(i);					
								selectedPassenger.add(pass) ;
					}						
				}
				for(Passenger passenger: selectedPassenger){
				//	if(passenger.getBehaviour()== Behaviour.OPTIMISE){
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
					//	passenger.setJourneyStatusInterventionSet(true);
						System.out.println(passenger);
						if(r<=Group1){
							passenger.getPrefferedMode().setValueToAttribute(Constants.journeyTimeConsideration,0.0);
						}else if (r<=Group2){
							passenger.getPrefferedMode().setValueToAttribute(Constants.journeyTimeConsideration,0.75);
						}else{
							passenger.getPrefferedMode().setValueToAttribute(Constants.journeyTimeConsideration,1.0);
						}
					//}
				}				
		}
		
		if(isLuggageCarrierStatus()){
			getCheckedNonActiveTravellers();
			System.out.println("Luggage Carrier is selected");
			double r =rand.nextDouble();
			double Group1 =0.4375;
			double Group2=Group1+0.1875;	
			double Group3=Group2+0.1875;		
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.118);
					for (int i =0; i< numberOfElements; i++){
						pass=passengers.get(i);						
						 selectedPassenger.add(pass) ;
					}						
				}
				for(Passenger passenger: selectedPassenger){
					//if(passenger.getBehaviour()== Behaviour.OPTIMISE){
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
					//	passenger.setLuggageCarrierInterventionSet(true);
						System.out.println(passenger);
						if(r<=Group1){
							passenger.getPrefferedMode().setValueToAttribute(Constants.capabilityForLuggageCarrier,0.0);
						}else if (r<=Group2){
							passenger.getPrefferedMode().setValueToAttribute(Constants.capabilityForLuggageCarrier,0.5);
						}else if (r<=Group2){
							passenger.getPrefferedMode().setValueToAttribute(Constants.capabilityForLuggageCarrier,0.75);
						}else{
							passenger.getPrefferedMode().setValueToAttribute(Constants.capabilityForLuggageCarrier,1.0);
					}	
				//}
			}				
		}
	}

	public void reset(){			
		getCheckedNonActiveTravellers();
		if(passengers.size()>0){
			for(Passenger passenger: passengers){
				if(isSideWalksStatus()){
					passenger.setSideWalkInterventionSet(false);
				//	passenger.getPrefferedMode().getValueOfAttribute((Constants.paths)+0.0);
				}						
				if (isJourneyTimeStatus()){
					passenger.setJourneyStatusInterventionSet(false);
				//	passenger.getPrefferedMode().getValueOfAttribute((Constants.journeyTimeConsideration)+0.0);
				}
				if (isRoadCrossingStatus()){
					passenger.setRoadCrossingInterventionSet(false);
				//	passenger.getPrefferedMode().getValueOfAttribute((Constants.crossingAndRoadsigns)+0.0);
				}
				if (isAttitudeOfOthersStatus()){
					passenger.setAttitudeOfOthersInterventionSet(false);
				//	passenger.getPrefferedMode().getValueOfAttribute((Constants.attitudeOfOthers)+0.0);
				}
				if (isRouteAvailabilityStatus()){
					passenger.setRouteAvailabilityInterventionSet(false);
				//	passenger.getPrefferedMode().getValueOfAttribute((Constants.routeAvailabilityAndObstruction)+0.0);
				}
				if (isFacilitiesAtDestStatus()){
					passenger.setFacilitiesAtDestInterventionSet(false);
				//	passenger.getPrefferedMode().getValueOfAttribute((Constants.facilitiesAtDestination)+0.0);
				}
				if (isLuggageCarrierStatus()){
					passenger.setLuggageCarrierInterventionSet(false);
				//	passenger.getPrefferedMode().getValueOfAttribute((Constants.capabilityForLuggageCarrier)+0.0);
				}
						
			}
		}	
		this.sideWalksStatus=false;
		this.journeyTimeStatus=false;
		this.roadCrossingStatus=false;
		this.routeAvailabilityStatus=false;
		this.attitudeOfOthersStatus=false;
		this.luggageCarrierStatus=false;
		this.facilitiesAtDestStatus=false;
	}
}
