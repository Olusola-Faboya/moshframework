package moshproject.agents.passenger;

import java.util.Random;

import moshproject.agents.mode.Mode;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class PersonalVehicleUser extends PassengerType {
	private boolean cycleOwnership ;
	private boolean carOwnership;
	Random  rand = new Random();		
	public PersonalVehicleUser(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
	//	carOwnership = true;
	//	cycleOwnership();
	}

	public boolean cycleOwnership() {
		double 	r = rand. nextDouble();
		if(r<=0.9){
			cycleOwnership =true;		
		}
		return cycleOwnership;
	}
}
