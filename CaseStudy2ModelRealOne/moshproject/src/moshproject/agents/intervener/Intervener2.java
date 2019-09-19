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

public class Intervener2 {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	Random rand = new Random();
	//Passenger passenger;	
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
	
	public Intervener2(ContinuousSpace<Object> space, Grid<Object> grid) {	
		this.space = space;
		this.grid = grid;		
		this.sideWalksStatus=false;
		this.journeyTimeStatus=false;
		this.roadCrossingStatus=false;
		this.routeAvailabilityStatus=false;
		this.attitudeOfOthersStatus=false;
		this.luggageCarrierStatus=false;
		this.facilitiesAtDestStatus=false;
	//	applyIntervention();
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
	
	@ScheduledMethod(start =1, interval=1)
	public void applyIntervention(){		
	ArrayList<Passenger> selectedPassenger;
	Passenger pass =null;
	//getNonActiveTravellers();
		if(isSideWalksStatus()){
			getNonActiveTravellers();
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
						 pass = passengers.get(i);							
						 selectedPassenger.add(pass) ;						
					}				
				for(Passenger passenger: selectedPassenger){					
					System.out.println(passenger);
					if(passenger.getBehaviour()== Behaviour.OPTIMISE){
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
						passenger.setSideWalkInterventionSet(isSideWalksStatus());
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
					}
				}	
			}
		}
			
		if(isRoadCrossingStatus()){
			getNonActiveTravellers();
			System.out.println("Road crossing is selected");
			double r =rand.nextDouble();
			double Group1 =0.25;
			double Group2=Group1+0.125;		
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.04);
					for (int i =0; i< numberOfElements; i++){										
						 pass = passengers.get(i);
						 selectedPassenger.add(pass) ;
					}									
				for(Passenger passenger: selectedPassenger){
					System.out.println(passenger);
					if(passenger.getBehaviour()== Behaviour.OPTIMISE){	
						System.out.println("selected passenger is" +passenger);
						System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
						passenger.setRoadCrossingInterventionSet(isRoadCrossingStatus());
						System.out.println(passenger);
						if(r<=Group1){
						passenger.getPrefferedMode().setValueToAttribute(Constants.crossingAndRoadsigns,0.0);
						}else if (r<=Group2){
						passenger.getPrefferedMode().setValueToAttribute(Constants.crossingAndRoadsigns,0.25);
						}else{
						passenger.getPrefferedMode().setValueToAttribute(Constants.crossingAndRoadsigns,0.75);
						}
					}
				}	
			}
		}
		
		if(isAttitudeOfOthersStatus()){
			getNonActiveTravellers();
			System.out.println("attitude of Others is selected");
			double r =rand.nextDouble();
			double Group1 =0.1304;
			double Group2=Group1+0.087;	
			double Group3=Group2+0.435;
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.314);
					for (int i =0; i< numberOfElements; i++){						
						 pass = passengers.get(i);
						 selectedPassenger.add(pass) ;
					}					
						for(Passenger passenger: selectedPassenger){
							if(passenger.getBehaviour()== Behaviour.OPTIMISE){	
								System.out.println("selected passenger is" +passenger);
								System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
								passenger.setAttitudeOfOthersInterventionSet(isAttitudeOfOthersStatus());
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
							}
						}				
				}				
		}
		
		if(isRouteAvailabilityStatus()){
			getNonActiveTravellers();
			System.out.println("Routes Availability is selected");
			double r =rand.nextDouble();
			double Group1 =0.154;
			double Group2=Group1+0.077;	
			double Group3=Group2+0.3077;			
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.1765);
					for (int i =0; i< numberOfElements; i++){					
						 pass = passengers.get(i);
						 selectedPassenger.add(pass) ;
					}								
					for(Passenger passenger: selectedPassenger){
						if(passenger.getBehaviour()== Behaviour.OPTIMISE){	
							System.out.println("selected passenger is" +passenger);
							System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
							passenger.setRouteAvailabilityInterventionSet(isRouteAvailabilityStatus());
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
						}
					}					
				}			
		}
		
		if(isFacilitiesAtDestStatus()){
			getNonActiveTravellers();
			System.out.println("Facilities At destinantion is selected");
			double r =rand.nextDouble();
			double Group1 =0.231;
			double Group2=Group1+0.3077;			
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.1765);
					for (int i =0; i< numberOfElements; i++){						
						 pass = passengers.get(i);
						 selectedPassenger.add(pass) ;
					}						
						for(Passenger passenger: selectedPassenger){
							if(passenger.getBehaviour()== Behaviour.OPTIMISE){	
								System.out.println("selected passenger is" +passenger);
								System.out.println("selected passenger behaviour is" +passenger.getBehaviour());
								passenger.setFacilitiesAtDestInterventionSet(isFacilitiesAtDestStatus());
								System.out.println(passenger);
									if(r<=Group1){
										passenger.getPrefferedMode().setValueToAttribute(Constants.facilitiesAtDestination,0.0);
									}else if (r<=Group2){
										passenger.getPrefferedMode().setValueToAttribute(Constants.facilitiesAtDestination,0.5);
									}else{
										passenger.getPrefferedMode().setValueToAttribute(Constants.facilitiesAtDestination,0.75);
									}				
							}
						}
				}
		}
		
		if(isJourneyTimeStatus()){
			getNonActiveTravellers();
			System.out.println("Journey Time is selected");
			double r =rand.nextDouble();
			double Group1 =0.5;
			double Group2=Group1+0.375;				
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.118);
					for (int i =0; i< numberOfElements; i++){						
						 pass = passengers.get(i);
						 selectedPassenger.add(pass) ;
					}					
					for(Passenger passenger: selectedPassenger){
						if(passenger.getBehaviour()== Behaviour.OPTIMISE){	
							System.out.println("selected passenger is" +passenger);
							System.out.println("selected passenger behaviour is" +passenger.getBehaviour());				
							passenger.setJourneyStatusInterventionSet(isJourneyTimeStatus());
							System.out.println(passenger);
								if(r<=Group1){
									passenger.getPrefferedMode().setValueToAttribute(Constants.journeyTimeConsideration,0.0);
								}else if (r<=Group2){
									passenger.getPrefferedMode().setValueToAttribute(Constants.journeyTimeConsideration,0.75);
								}else{
									passenger.getPrefferedMode().setValueToAttribute(Constants.journeyTimeConsideration,1.0);
								}
						}
					}
				}				
			}
		
		if(isLuggageCarrierStatus()){
			getNonActiveTravellers();
			System.out.println("Luggage Carrier is selected");
			double r =rand.nextDouble();
			double Group1 =0.4375;
			double Group2=Group1+0.1875;	
			double Group3=Group2+0.1875;		
			 selectedPassenger= new ArrayList<Passenger>();
				if(passengers.size()>0){					
					int numberOfElements = (int) (((int) passengers.size())*0.118);
					for (int i =0; i< numberOfElements; i++){						
						 pass = passengers.get(i);
						 selectedPassenger.add(pass) ;
					}
					for(Passenger passenger: passengers){
						if(passenger.getBehaviour()== Behaviour.OPTIMISE){	
							System.out.println("selected passenger is" +passenger);
							System.out.println("selected passenger behaviour is" +passenger.getBehaviour());				
							passenger.setLuggageCarrierInterventionSet(isLuggageCarrierStatus());
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
						}
					}
				}
		}
	}

	public void reset(){		
		this.sideWalksStatus=false;
		this.journeyTimeStatus=false;
		this.roadCrossingStatus=false;
		this.attitudeOfOthersStatus=false;
		this.routeAvailabilityStatus=false;
		this.facilitiesAtDestStatus=false;
		this.luggageCarrierStatus=false;
	}
}
