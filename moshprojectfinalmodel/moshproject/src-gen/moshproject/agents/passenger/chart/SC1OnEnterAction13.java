package moshproject.agents.passenger.chart;

import repast.simphony.statecharts.*;
import repast.simphony.parameter.Parameters;
import static repast.simphony.random.RandomHelper.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import static repast.simphony.essentials.RepastEssentials.*;

import moshproject.agents.passenger.*;

/**
 * Action for Unsatisfied.
 */
@GeneratedFor("_Y_bxsLXgEeiaqMHnnobXEA")
public class SC1OnEnterAction13 implements StateAction<Passenger> {
	@Override
	public void action(Passenger agent, AbstractState<Passenger> state,
			Parameters params) throws Exception {
		agent.optimise();

	}
}
