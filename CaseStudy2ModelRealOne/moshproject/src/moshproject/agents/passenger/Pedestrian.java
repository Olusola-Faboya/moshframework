package moshproject.agents.passenger;

import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Pedestrian extends PassengerType{
	double distance;
	public Pedestrian(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
		System.out.println("I'm a pedestrian");
		setWalkingDistance();
	}
	
	public double setWalkingDistance(){
		//double distanceRange = 0;
		if( rand.nextDouble() <=0.2273){
			distance =RandomHelper.nextDoubleFromTo(0.1,1.0);
		}else if( rand.nextDouble() <=0.2273+0.4545){
			distance =RandomHelper.nextDoubleFromTo(0.1,2.0);
		}else if( rand.nextDouble() <=0.2273+0.4545+0.2727){
			distance =RandomHelper.nextDoubleFromTo(0.1,3.0);
		}else if( rand.nextDouble() <=0.2273+0.4545+0.2727+0.0454){
			distance=RandomHelper.nextDoubleFromTo(3.0,5.0);
		}
		return distance;
	}
	
}
