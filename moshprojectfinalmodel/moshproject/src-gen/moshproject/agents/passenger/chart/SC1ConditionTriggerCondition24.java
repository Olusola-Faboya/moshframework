package moshproject.agents.passenger.chart;

import repast.simphony.statecharts.*;
import repast.simphony.parameter.Parameters;
import static repast.simphony.random.RandomHelper.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import static repast.simphony.essentials.RepastEssentials.*;

import moshproject.agents.passenger.*;

/**
 * Condition trigger condition for Transition 89, from = Uncertain, to = Unsatisfied.
 */
@GeneratedFor("_Y_bxsLXgEeiaqMHnnobXEA")
public class SC1ConditionTriggerCondition24 implements
		ConditionTriggerCondition<Passenger> {
	@Override
	public boolean condition(Passenger agent, Transition<Passenger> transition,
			Parameters params) throws Exception {
		return agent.isUnsatisfied();

	}
}