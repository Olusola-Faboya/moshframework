package moshproject.agents.passenger;

import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Cyclist extends PassengerType {

	double distance;
	public Cyclist(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
		System.out.println("I'm a cyclist");
		setCyclingDistance();
	}
	public double setCyclingDistance(){
		//double distanceRange = 0;
		if( rand.nextDouble() <=0.0454){
			distance =RandomHelper.nextDoubleFromTo(0.1,1.0);
		}else if( rand.nextDouble() <=0.04546+0.0454){
			distance =RandomHelper.nextDoubleFromTo(0.1,2.0);
		}else if( rand.nextDouble() <=0.0454+0.0454+0.0454){
			distance =RandomHelper.nextDoubleFromTo(0.1,3.0);
		}else if( rand.nextDouble() <=0.0454+0.0454+0.0454+0.1818){
			distance =RandomHelper.nextDoubleFromTo(0.1,4.0);
		}else if( rand.nextDouble() <=0.0454+0.0454+0.0454+0.1818+0.2273){
			distance =RandomHelper.nextDoubleFromTo(0.1,5.0);
		}else if( rand.nextDouble() <=0.0454+0.0454+0.0454+0.1818+0.4545){
			distance=RandomHelper.nextDoubleFromTo(5.0,10.0);
		}
		return distance;
	}

}
