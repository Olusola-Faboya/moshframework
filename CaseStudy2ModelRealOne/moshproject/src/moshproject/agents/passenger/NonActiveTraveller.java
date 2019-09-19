package moshproject.agents.passenger;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class NonActiveTraveller extends PassengerType {
			
	public NonActiveTraveller(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
		System.out.println("I'm a non-active traveller");
	}

	
}
