package moshproject.agents.passenger;

import java.util.Random;

import moshproject.agents.mode.Cycle;
import moshproject.agents.mode.Mode;
import moshproject.agents.mode.PublicTransport;
import moshproject.agents.mode.Walking;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Cyclist extends PassengerType{
	//Mode prefferedMode;
//	ModeAttributesPerceptions myPerception;
	private boolean cycleOwnership ;
	private boolean carOwnership;
	Random  rand = new Random();	
	public Cyclist(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
	//	cycleOwnership = true;
	//	carOwnership();
	}
	public boolean carOwnership() {
		double 	r = rand. nextDouble();
		if(r<=0.9){
			carOwnership =true;		
		}
		return carOwnership;
	}

}
