package moshproject.agents.passenger.chart;

import repast.simphony.statecharts.*;
import repast.simphony.parameter.Parameters;
import static repast.simphony.random.RandomHelper.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import static repast.simphony.essentials.RepastEssentials.*;

import moshproject.agents.passenger.*;

/**
 * Condition trigger condition for Transition 33, from = Imitation, to = Inquiring.
 */
@GeneratedFor("_CBrNwM2eEeahM40jKv-Uyw")
public class SC2ConditionTriggerCondition7 implements
		ConditionTriggerCondition<Passenger> {
	@Override
	public boolean condition(Passenger agent, Transition<Passenger> transition,
			Parameters params) throws Exception {
		return agent.getModeSatisfaction() <= agent.getAspirationLevel();

	}
}