package moshproject.agents.passenger;

import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Scooter extends PassengerType{

	double distance;

	public Scooter(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
		System.out.println("I'm using Scooter");
		setScootingDistance();
	}
	
	
	public double setScootingDistance(){
		//	double distanceRange = 0;
			if( rand.nextDouble() <=0.3636){
				distance =RandomHelper.nextDoubleFromTo(0.1,1.0);
			}else if( rand.nextDouble() <=0.3636+0.1818){
				distance =RandomHelper.nextDoubleFromTo(0.1,2.0);
			}else if( rand.nextDouble() <=0.3636+0.1818+0.3636){
				distance =RandomHelper.nextDoubleFromTo(0.1,3.0);
			}else if( rand.nextDouble() <=0.3636+0.1818+0.3636+0.0909){
				distance=RandomHelper.nextDoubleFromTo(3.0,5.0);
			}
			return distance;
		}

}
