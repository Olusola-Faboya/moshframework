package moshproject.agents.passenger;

import java.util.Random;

import moshproject.agents.mode.Mode;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class PublicTransportUser extends PassengerType {
	private boolean cycleOwnership ;
	private boolean carOwnership;
	Random  rand = new Random();	
	public PublicTransportUser(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
	//	cycleOwnership();
	//	carOwnership();
	}
	public boolean cycleOwnership() {
		double 	r = rand. nextDouble();
		if(r<=0.9){
			cycleOwnership =true;		
		}
		return cycleOwnership;
	}
	public boolean carOwnership() {
		double 	r = rand. nextDouble();
		if(r<=0.9){
			carOwnership =true;		
		}
		return carOwnership;
	}

}
