package moshproject.agents.passenger.chart;

import java.util.Map;
import java.util.HashMap;

import repast.simphony.statecharts.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import moshproject.agents.passenger.*;

@GeneratedFor("_CBrNwM2eEeahM40jKv-Uyw")
public class CognitiveStatechart extends
		DefaultStateChart<moshproject.agents.passenger.Passenger> {

	public static CognitiveStatechart createStateChart(
			moshproject.agents.passenger.Passenger agent, double begin) {
		CognitiveStatechart result = createStateChart(agent);
		StateChartScheduler.INSTANCE.scheduleBeginTime(begin, result);
		return result;
	}

	public static CognitiveStatechart createStateChart(
			moshproject.agents.passenger.Passenger agent) {
		CognitiveStatechartGenerator generator = new CognitiveStatechartGenerator();
		return generator.build(agent);
	}

	private CognitiveStatechart(moshproject.agents.passenger.Passenger agent) {
		super(agent);
	}

	private static class MyStateChartBuilder extends
			StateChartBuilder<moshproject.agents.passenger.Passenger> {

		public MyStateChartBuilder(
				moshproject.agents.passenger.Passenger agent,
				AbstractState<moshproject.agents.passenger.Passenger> entryState,
				String entryStateUuid) {
			super(agent, entryState, entryStateUuid);
			setPriority(0.0);
		}

		@Override
		public CognitiveStatechart build() {
			CognitiveStatechart result = new CognitiveStatechart(getAgent());
			setStateChartProperties(result);
			return result;
		}
	}

	private static class CognitiveStatechartGenerator {

		private Map<String, AbstractState<Passenger>> stateMap = new HashMap<String, AbstractState<Passenger>>();

		public CognitiveStatechart build(Passenger agent) {
			// Cognitive Processing 
			CompositeState<Passenger> cs1 = createCS1();
			MyStateChartBuilder mscb = new MyStateChartBuilder(agent, cs1,
					"_EXUPcM2eEeahM40jKv-Uyw");

			createTransitions(mscb);
			return mscb.build();

		}

		// Creates CompositeState 'Cognitive Processing '
		private CompositeState<Passenger> createCS1() {

			// Individual: Automatic and Reasoning Processing
			CompositeState<Passenger> cs2 = createCS2();

			CompositeStateBuilder<Passenger> csBuilder1 = new CompositeStateBuilder<Passenger>(
					"Cognitive Processing ", cs2, "_QXZWIc2eEeahM40jKv-Uyw");

			// Social Interactions: Automatic and Reasoning Processing
			CompositeState<Passenger> cs3 = createCS3();
			csBuilder1.addChildState(cs3, "_5VcoAM2eEeahM40jKv-Uyw");

			CompositeState<Passenger> cs1 = csBuilder1.build();
			stateMap.put("_EXUPcM2eEeahM40jKv-Uyw", cs1);
			return cs1;
		}

		// Creates CompositeState 'Individual: Automatic and Reasoning Processing'
		private CompositeState<Passenger> createCS2() {

			SimpleStateBuilder<Passenger> ssBuilder4 = new SimpleStateBuilder<Passenger>(
					"Repetition");
			SimpleState<Passenger> s4 = ssBuilder4.build();
			stateMap.put("_fDySEc2eEeahM40jKv-Uyw", s4);

			CompositeStateBuilder<Passenger> csBuilder2 = new CompositeStateBuilder<Passenger>(
					"Individual: Automatic and Reasoning Processing", s4,
					"_fDySEc2eEeahM40jKv-Uyw");

			SimpleStateBuilder<Passenger> ssBuilder5 = new SimpleStateBuilder<Passenger>(
					"Optimising");
			SimpleState<Passenger> s5 = ssBuilder5.build();
			stateMap.put("_i-JO0c2eEeahM40jKv-Uyw", s5);
			csBuilder2.addChildState(s5, "_i-JO0c2eEeahM40jKv-Uyw");

			CompositeState<Passenger> cs2 = csBuilder2.build();
			stateMap.put("_QXZWIc2eEeahM40jKv-Uyw", cs2);
			return cs2;
		}

		// Creates CompositeState 'Social Interactions: Automatic and Reasoning Processing'
		private CompositeState<Passenger> createCS3() {

			SimpleStateBuilder<Passenger> ssBuilder6 = new SimpleStateBuilder<Passenger>(
					"Imitation");
			SimpleState<Passenger> s6 = ssBuilder6.build();
			stateMap.put("_IKg-0c2fEeahM40jKv-Uyw", s6);

			CompositeStateBuilder<Passenger> csBuilder3 = new CompositeStateBuilder<Passenger>(
					"Social Interactions: Automatic and Reasoning Processing",
					s6, "_IKg-0c2fEeahM40jKv-Uyw");

			SimpleStateBuilder<Passenger> ssBuilder7 = new SimpleStateBuilder<Passenger>(
					"Inquiring");
			SimpleState<Passenger> s7 = ssBuilder7.build();
			stateMap.put("_Nz4bEc2fEeahM40jKv-Uyw", s7);
			csBuilder3.addChildState(s7, "_Nz4bEc2fEeahM40jKv-Uyw");

			CompositeState<Passenger> cs3 = csBuilder3.build();
			stateMap.put("_5VcoAM2eEeahM40jKv-Uyw", cs3);
			return cs3;
		}

		private void createTransitions(MyStateChartBuilder mscb) {
			// creates transition Transition 19
			createTransition1(mscb);
			// creates transition Transition 20
			createTransition2(mscb);
			// creates transition Transition 27
			createTransition3(mscb);
			// creates transition Transition 28
			createTransition4(mscb);
			// creates transition Transition 29
			createTransition5(mscb);
			// creates transition Transition 30
			createTransition6(mscb);
			// creates transition Transition 33
			createTransition7(mscb);
			// creates transition Transition 34
			createTransition8(mscb);

		}

		// creates transition Transition 19, from = Individual: Automatic and Reasoning Processing, to = Social Interactions: Automatic and Reasoning Processing
		private void createTransition1(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 19", stateMap.get("_QXZWIc2eEeahM40jKv-Uyw"),
					stateMap.get("_5VcoAM2eEeahM40jKv-Uyw"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC2ConditionTriggerCondition1(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_UlYkEc2fEeahM40jKv-Uyw");
		}

		// creates transition Transition 20, from = Social Interactions: Automatic and Reasoning Processing, to = Individual: Automatic and Reasoning Processing
		private void createTransition2(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 20", stateMap.get("_5VcoAM2eEeahM40jKv-Uyw"),
					stateMap.get("_QXZWIc2eEeahM40jKv-Uyw"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC2ConditionTriggerCondition2(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_ZBxP0c2fEeahM40jKv-Uyw");
		}

		// creates transition Transition 27, from = Repetition, to = Repetition
		private void createTransition3(MyStateChartBuilder mscb) {
			// true
			SelfTransitionBuilder<Passenger> tb = new SelfTransitionBuilder<Passenger>(
					"Transition 27", stateMap.get("_fDySEc2eEeahM40jKv-Uyw"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC2ConditionTriggerCondition3(), 1.0));
			tb.setPriority(0.0);
			mscb.addSelfTransition(tb.build(), "_jZc1UM2fEeahM40jKv-Uyw");
		}

		// creates transition Transition 28, from = Imitation, to = Imitation
		private void createTransition4(MyStateChartBuilder mscb) {
			// true
			SelfTransitionBuilder<Passenger> tb = new SelfTransitionBuilder<Passenger>(
					"Transition 28", stateMap.get("_IKg-0c2fEeahM40jKv-Uyw"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC2ConditionTriggerCondition4(), 1.0));
			tb.setPriority(0.0);
			mscb.addSelfTransition(tb.build(), "_kpUhwc2fEeahM40jKv-Uyw");
		}

		// creates transition Transition 29, from = Optimising, to = Repetition
		private void createTransition5(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 29", stateMap.get("_i-JO0c2eEeahM40jKv-Uyw"),
					stateMap.get("_fDySEc2eEeahM40jKv-Uyw"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC2ConditionTriggerCondition5(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_oX0z0c2fEeahM40jKv-Uyw");
		}

		// creates transition Transition 30, from = Repetition, to = Optimising
		private void createTransition6(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 30", stateMap.get("_fDySEc2eEeahM40jKv-Uyw"),
					stateMap.get("_i-JO0c2eEeahM40jKv-Uyw"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC2ConditionTriggerCondition6(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_pO0GMc2fEeahM40jKv-Uyw");
		}

		// creates transition Transition 33, from = Imitation, to = Inquiring
		private void createTransition7(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 33", stateMap.get("_IKg-0c2fEeahM40jKv-Uyw"),
					stateMap.get("_Nz4bEc2fEeahM40jKv-Uyw"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC2ConditionTriggerCondition7(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_sktrAc2fEeahM40jKv-Uyw");
		}

		// creates transition Transition 34, from = Inquiring, to = Imitation
		private void createTransition8(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 34", stateMap.get("_Nz4bEc2fEeahM40jKv-Uyw"),
					stateMap.get("_IKg-0c2fEeahM40jKv-Uyw"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC2ConditionTriggerCondition8(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_vkFmMc2fEeahM40jKv-Uyw");
		}

	}
}
