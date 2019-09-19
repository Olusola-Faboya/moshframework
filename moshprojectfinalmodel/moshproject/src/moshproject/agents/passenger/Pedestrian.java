package moshproject.agents.passenger;

import java.util.Random;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Pedestrian extends PassengerType{
	private boolean cycleOwnership ;
	private boolean carOwnership;
	Random  rand = new Random();	
	public Pedestrian(ContinuousSpace<Object> space, Grid<Object> grid) {
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
