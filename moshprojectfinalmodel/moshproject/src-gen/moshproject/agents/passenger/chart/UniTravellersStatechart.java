package moshproject.agents.passenger.chart;

import java.util.Map;
import java.util.HashMap;

import repast.simphony.statecharts.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import moshproject.agents.passenger.*;

@GeneratedFor("_Y_bxsLXgEeiaqMHnnobXEA")
public class UniTravellersStatechart extends
		DefaultStateChart<moshproject.agents.passenger.Passenger> {

	public static UniTravellersStatechart createStateChart(
			moshproject.agents.passenger.Passenger agent, double begin) {
		UniTravellersStatechart result = createStateChart(agent);
		StateChartScheduler.INSTANCE.scheduleBeginTime(begin, result);
		return result;
	}

	public static UniTravellersStatechart createStateChart(
			moshproject.agents.passenger.Passenger agent) {
		UniTravellersStatechartGenerator generator = new UniTravellersStatechartGenerator();
		return generator.build(agent);
	}

	private UniTravellersStatechart(moshproject.agents.passenger.Passenger agent) {
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
		public UniTravellersStatechart build() {
			UniTravellersStatechart result = new UniTravellersStatechart(
					getAgent());
			setStateChartProperties(result);
			return result;
		}
	}

	private static class UniTravellersStatechartGenerator {

		private Map<String, AbstractState<Passenger>> stateMap = new HashMap<String, AbstractState<Passenger>>();

		public UniTravellersStatechart build(Passenger agent) {
			// UniTravellersModeShiftStatechart
			CompositeState<Passenger> cs1 = createCS1();
			MyStateChartBuilder mscb = new MyStateChartBuilder(agent, cs1,
					"_gr68QLXgEeiaqMHnnobXEA");

			createTransitions(mscb);
			return mscb.build();

		}

		// Creates CompositeState 'UniTravellersModeShiftStatechart'
		private CompositeState<Passenger> createCS1() {

			// Travel Mode Selection
			CompositeState<Passenger> cs2 = createCS2();

			CompositeStateBuilder<Passenger> csBuilder1 = new CompositeStateBuilder<Passenger>(
					"UniTravellersModeShiftStatechart", cs2,
					"_tzoZYbXgEeiaqMHnnobXEA");

			// Mental Model
			CompositeState<Passenger> cs3 = createCS3();
			csBuilder1.addChildState(cs3, "_V6Th4bXiEeiaqMHnnobXEA");

			CompositeState<Passenger> cs1 = csBuilder1.build();
			stateMap.put("_gr68QLXgEeiaqMHnnobXEA", cs1);
			return cs1;
		}

		// Creates CompositeState 'Travel Mode Selection'
		private CompositeState<Passenger> createCS2() {

			SimpleStateBuilder<Passenger> ssBuilder4 = new SimpleStateBuilder<Passenger>(
					"SelectMode");
			SimpleState<Passenger> s4 = ssBuilder4.build();
			stateMap.put("_7ttuobXgEeiaqMHnnobXEA", s4);

			CompositeStateBuilder<Passenger> csBuilder2 = new CompositeStateBuilder<Passenger>(
					"Travel Mode Selection", s4, "_7ttuobXgEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder5 = new SimpleStateBuilder<Passenger>(
					"Cyclists");
			ssBuilder5.registerOnEnter(new SC1OnEnterAction5());
			SimpleState<Passenger> s5 = ssBuilder5.build();
			stateMap.put("_EaX8wbXhEeiaqMHnnobXEA", s5);
			csBuilder2.addChildState(s5, "_EaX8wbXhEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder6 = new SimpleStateBuilder<Passenger>(
					"CarUsers");
			ssBuilder6.registerOnEnter(new SC1OnEnterAction6());
			SimpleState<Passenger> s6 = ssBuilder6.build();
			stateMap.put("_Jr6H0bXhEeiaqMHnnobXEA", s6);
			csBuilder2.addChildState(s6, "_Jr6H0bXhEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder7 = new SimpleStateBuilder<Passenger>(
					"Pedestrians");
			ssBuilder7.registerOnEnter(new SC1OnEnterAction7());
			SimpleState<Passenger> s7 = ssBuilder7.build();
			stateMap.put("_PfoZ8bXhEeiaqMHnnobXEA", s7);
			csBuilder2.addChildState(s7, "_PfoZ8bXhEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder8 = new SimpleStateBuilder<Passenger>(
					"PublicTransUsers");
			ssBuilder8.registerOnEnter(new SC1OnEnterAction8());
			SimpleState<Passenger> s8 = ssBuilder8.build();
			stateMap.put("_VhFmEbXhEeiaqMHnnobXEA", s8);
			csBuilder2.addChildState(s8, "_VhFmEbXhEeiaqMHnnobXEA");

			CompositeState<Passenger> cs2 = csBuilder2.build();
			stateMap.put("_tzoZYbXgEeiaqMHnnobXEA", cs2);
			return cs2;
		}

		// Creates CompositeState 'Mental Model'
		private CompositeState<Passenger> createCS3() {

			SimpleStateBuilder<Passenger> ssBuilder9 = new SimpleStateBuilder<Passenger>(
					"ReviewPrevExperience");
			SimpleState<Passenger> s9 = ssBuilder9.build();
			stateMap.put("_eftRcbXiEeiaqMHnnobXEA", s9);

			CompositeStateBuilder<Passenger> csBuilder3 = new CompositeStateBuilder<Passenger>(
					"Mental Model", s9, "_eftRcbXiEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder10 = new SimpleStateBuilder<Passenger>(
					"Certain");
			SimpleState<Passenger> s10 = ssBuilder10.build();
			stateMap.put("_1o5vsbXkEeiaqMHnnobXEA", s10);
			csBuilder3.addChildState(s10, "_1o5vsbXkEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder11 = new SimpleStateBuilder<Passenger>(
					"Uncertain");
			SimpleState<Passenger> s11 = ssBuilder11.build();
			stateMap.put("_4AggQbXkEeiaqMHnnobXEA", s11);
			csBuilder3.addChildState(s11, "_4AggQbXkEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder12 = new SimpleStateBuilder<Passenger>(
					"Satisfied");
			ssBuilder12.registerOnEnter(new SC1OnEnterAction12());
			SimpleState<Passenger> s12 = ssBuilder12.build();
			stateMap.put("_W2U94bXlEeiaqMHnnobXEA", s12);
			csBuilder3.addChildState(s12, "_W2U94bXlEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder13 = new SimpleStateBuilder<Passenger>(
					"Unsatisfied");
			ssBuilder13.registerOnEnter(new SC1OnEnterAction13());
			SimpleState<Passenger> s13 = ssBuilder13.build();
			stateMap.put("_d-ViYbXlEeiaqMHnnobXEA", s13);
			csBuilder3.addChildState(s13, "_d-ViYbXlEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder14 = new SimpleStateBuilder<Passenger>(
					"Satisfied");
			ssBuilder14.registerOnEnter(new SC1OnEnterAction14());
			SimpleState<Passenger> s14 = ssBuilder14.build();
			stateMap.put("_joOjEbXlEeiaqMHnnobXEA", s14);
			csBuilder3.addChildState(s14, "_joOjEbXlEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder15 = new SimpleStateBuilder<Passenger>(
					"Unsatisfied");
			ssBuilder15.registerOnEnter(new SC1OnEnterAction15());
			SimpleState<Passenger> s15 = ssBuilder15.build();
			stateMap.put("_wiI0wbXlEeiaqMHnnobXEA", s15);
			csBuilder3.addChildState(s15, "_wiI0wbXlEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder16 = new SimpleStateBuilder<Passenger>(
					"PreferredMode");
			ssBuilder16.registerOnEnter(new SC1OnEnterAction16());
			SimpleState<Passenger> s16 = ssBuilder16.build();
			stateMap.put("_A9Ui4bXoEeiaqMHnnobXEA", s16);
			csBuilder3.addChildState(s16, "_A9Ui4bXoEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder17 = new SimpleStateBuilder<Passenger>(
					"Repetition");
			ssBuilder17.registerOnEnter(new SC1OnEnterAction17());
			SimpleState<Passenger> s17 = ssBuilder17.build();
			stateMap.put("_S_GtUbXoEeiaqMHnnobXEA", s17);
			csBuilder3.addChildState(s17, "_S_GtUbXoEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder18 = new SimpleStateBuilder<Passenger>(
					"Optimising");
			ssBuilder18.registerOnEnter(new SC1OnEnterAction18());
			SimpleState<Passenger> s18 = ssBuilder18.build();
			stateMap.put("_cH1MYbXoEeiaqMHnnobXEA", s18);
			csBuilder3.addChildState(s18, "_cH1MYbXoEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder19 = new SimpleStateBuilder<Passenger>(
					"Imitating");
			ssBuilder19.registerOnEnter(new SC1OnEnterAction19());
			SimpleState<Passenger> s19 = ssBuilder19.build();
			stateMap.put("_e6XlcbXoEeiaqMHnnobXEA", s19);
			csBuilder3.addChildState(s19, "_e6XlcbXoEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder20 = new SimpleStateBuilder<Passenger>(
					"Inquiring");
			ssBuilder20.registerOnEnter(new SC1OnEnterAction20());
			SimpleState<Passenger> s20 = ssBuilder20.build();
			stateMap.put("_l4KeAbXoEeiaqMHnnobXEA", s20);
			csBuilder3.addChildState(s20, "_l4KeAbXoEeiaqMHnnobXEA");

			SimpleStateBuilder<Passenger> ssBuilder21 = new SimpleStateBuilder<Passenger>(
					"AdoptNewMode");
			ssBuilder21.registerOnEnter(new SC1OnEnterAction21());
			SimpleState<Passenger> s21 = ssBuilder21.build();
			stateMap.put("_6SU8ILdIEei56ZJa_bxOdQ", s21);
			csBuilder3.addChildState(s21, "_6SU8ILdIEei56ZJa_bxOdQ");

			SimpleStateBuilder<Passenger> ssBuilder22 = new SimpleStateBuilder<Passenger>(
					"PreferredMode");
			ssBuilder22.registerOnEnter(new SC1OnEnterAction22());
			SimpleState<Passenger> s22 = ssBuilder22.build();
			stateMap.put("_IoESoLdJEei56ZJa_bxOdQ", s22);
			csBuilder3.addChildState(s22, "_IoESoLdJEei56ZJa_bxOdQ");

			CompositeState<Passenger> cs3 = csBuilder3.build();
			stateMap.put("_V6Th4bXiEeiaqMHnnobXEA", cs3);
			return cs3;
		}

		private void createTransitions(MyStateChartBuilder mscb) {
			// creates transition Transition 10
			createTransition1(mscb);
			// creates transition Transition 11
			createTransition2(mscb);
			// creates transition Transition 12
			createTransition3(mscb);
			// creates transition Transition 13
			createTransition4(mscb);
			// creates transition Transition 14
			createTransition5(mscb);
			// creates transition Transition 15
			createTransition6(mscb);
			// creates transition Transition 16
			createTransition7(mscb);
			// creates transition Transition 17
			createTransition8(mscb);
			// creates transition Transition 52
			createTransition9(mscb);
			// creates transition Transition 53
			createTransition10(mscb);
			// creates transition Transition 54
			createTransition11(mscb);
			// creates transition Transition 55
			createTransition12(mscb);
			// creates transition Transition 56
			createTransition13(mscb);
			// creates transition Transition 57
			createTransition14(mscb);
			// creates transition Transition 68
			createTransition15(mscb);
			// creates transition Transition 69
			createTransition16(mscb);
			// creates transition Transition 72
			createTransition17(mscb);
			// creates transition Transition 73
			createTransition18(mscb);
			// creates transition Transition 74
			createTransition19(mscb);
			// creates transition Transition 75
			createTransition20(mscb);
			// creates transition Transition 86
			createTransition21(mscb);
			// creates transition Transition 87
			createTransition22(mscb);
			// creates transition Transition 88
			createTransition23(mscb);
			// creates transition Transition 89
			createTransition24(mscb);
			// creates transition Transition 90
			createTransition25(mscb);
			// creates transition Transition 91
			createTransition26(mscb);
			// creates transition Transition 92
			createTransition27(mscb);
			// creates transition Transition 93
			createTransition28(mscb);
			// creates transition Transition 94
			createTransition29(mscb);
			// creates transition Transition 95
			createTransition30(mscb);
			// creates transition Transition 102
			createTransition31(mscb);
			// creates transition Transition 103
			createTransition32(mscb);
			// creates transition Transition 146
			createTransition33(mscb);
			// creates transition Transition 147
			createTransition34(mscb);

		}

		// creates transition Transition 10, from = SelectMode, to = Cyclists
		private void createTransition1(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 10", stateMap.get("_7ttuobXgEeiaqMHnnobXEA"),
					stateMap.get("_EaX8wbXhEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition1(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_pyTcgbXhEeiaqMHnnobXEA");
		}

		// creates transition Transition 11, from = SelectMode, to = CarUsers
		private void createTransition2(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 11", stateMap.get("_7ttuobXgEeiaqMHnnobXEA"),
					stateMap.get("_Jr6H0bXhEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition2(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_rExxAbXhEeiaqMHnnobXEA");
		}

		// creates transition Transition 12, from = SelectMode, to = PublicTransUsers
		private void createTransition3(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 12", stateMap.get("_7ttuobXgEeiaqMHnnobXEA"),
					stateMap.get("_VhFmEbXhEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition3(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_sQjAkbXhEeiaqMHnnobXEA");
		}

		// creates transition Transition 13, from = SelectMode, to = Pedestrians
		private void createTransition4(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 13", stateMap.get("_7ttuobXgEeiaqMHnnobXEA"),
					stateMap.get("_PfoZ8bXhEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition4(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_tmrtEbXhEeiaqMHnnobXEA");
		}

		// creates transition Transition 14, from = Cyclists, to = SelectMode
		private void createTransition5(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 14", stateMap.get("_EaX8wbXhEeiaqMHnnobXEA"),
					stateMap.get("_7ttuobXgEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition5(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_w0r6IbXhEeiaqMHnnobXEA");
		}

		// creates transition Transition 15, from = CarUsers, to = SelectMode
		private void createTransition6(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 15", stateMap.get("_Jr6H0bXhEeiaqMHnnobXEA"),
					stateMap.get("_7ttuobXgEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition6(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_yy4sMbXhEeiaqMHnnobXEA");
		}

		// creates transition Transition 16, from = Pedestrians, to = SelectMode
		private void createTransition7(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 16", stateMap.get("_PfoZ8bXhEeiaqMHnnobXEA"),
					stateMap.get("_7ttuobXgEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition7(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_z9gsQbXhEeiaqMHnnobXEA");
		}

		// creates transition Transition 17, from = PublicTransUsers, to = SelectMode
		private void createTransition8(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 17", stateMap.get("_VhFmEbXhEeiaqMHnnobXEA"),
					stateMap.get("_7ttuobXgEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition8(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_3c-cwbXhEeiaqMHnnobXEA");
		}

		// creates transition Transition 52, from = ReviewPrevExperience, to = Certain
		private void createTransition9(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 52", stateMap.get("_eftRcbXiEeiaqMHnnobXEA"),
					stateMap.get("_1o5vsbXkEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition9(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_ec40sbXnEeiaqMHnnobXEA");
		}

		// creates transition Transition 53, from = Certain, to = ReviewPrevExperience
		private void createTransition10(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 53", stateMap.get("_1o5vsbXkEeiaqMHnnobXEA"),
					stateMap.get("_eftRcbXiEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition10(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_fkANwbXnEeiaqMHnnobXEA");
		}

		// creates transition Transition 54, from = ReviewPrevExperience, to = Uncertain
		private void createTransition11(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 54", stateMap.get("_eftRcbXiEeiaqMHnnobXEA"),
					stateMap.get("_4AggQbXkEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition11(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_gggSQbXnEeiaqMHnnobXEA");
		}

		// creates transition Transition 55, from = Uncertain, to = ReviewPrevExperience
		private void createTransition12(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 55", stateMap.get("_4AggQbXkEeiaqMHnnobXEA"),
					stateMap.get("_eftRcbXiEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition12(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_iEOu0bXnEeiaqMHnnobXEA");
		}

		// creates transition Transition 56, from = Certain, to = Satisfied
		private void createTransition13(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 56", stateMap.get("_1o5vsbXkEeiaqMHnnobXEA"),
					stateMap.get("_W2U94bXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition13(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_lkiz4LXnEeiaqMHnnobXEA");
		}

		// creates transition Transition 57, from = Certain, to = Unsatisfied
		private void createTransition14(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 57", stateMap.get("_1o5vsbXkEeiaqMHnnobXEA"),
					stateMap.get("_d-ViYbXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition14(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_mYbu8bXnEeiaqMHnnobXEA");
		}

		// creates transition Transition 68, from = Satisfied, to = Repetition
		private void createTransition15(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 68", stateMap.get("_W2U94bXlEeiaqMHnnobXEA"),
					stateMap.get("_S_GtUbXoEeiaqMHnnobXEA"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_2emr0bXoEeiaqMHnnobXEA");
		}

		// creates transition Transition 69, from = Unsatisfied, to = Optimising
		private void createTransition16(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 69", stateMap.get("_d-ViYbXlEeiaqMHnnobXEA"),
					stateMap.get("_cH1MYbXoEeiaqMHnnobXEA"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_3SV14bXoEeiaqMHnnobXEA");
		}

		// creates transition Transition 72, from = Repetition, to = PreferredMode
		private void createTransition17(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 72", stateMap.get("_S_GtUbXoEeiaqMHnnobXEA"),
					stateMap.get("_A9Ui4bXoEeiaqMHnnobXEA"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_5a1zcLXoEeiaqMHnnobXEA");
		}

		// creates transition Transition 73, from = Optimising, to = PreferredMode
		private void createTransition18(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 73", stateMap.get("_cH1MYbXoEeiaqMHnnobXEA"),
					stateMap.get("_A9Ui4bXoEeiaqMHnnobXEA"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_6XV38LXoEeiaqMHnnobXEA");
		}

		// creates transition Transition 74, from = Inquiring, to = AdoptNewMode
		private void createTransition19(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 74", stateMap.get("_l4KeAbXoEeiaqMHnnobXEA"),
					stateMap.get("_6SU8ILdIEei56ZJa_bxOdQ"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_7IMGEbXoEeiaqMHnnobXEA");
		}

		// creates transition Transition 75, from = Imitating, to = AdoptNewMode
		private void createTransition20(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 75", stateMap.get("_e6XlcbXoEeiaqMHnnobXEA"),
					stateMap.get("_6SU8ILdIEei56ZJa_bxOdQ"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_8hW4cbXoEeiaqMHnnobXEA");
		}

		// creates transition Transition 86, from = Satisfied, to = Unsatisfied
		private void createTransition21(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 86", stateMap.get("_W2U94bXlEeiaqMHnnobXEA"),
					stateMap.get("_d-ViYbXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition21(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_FdBbYLZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 87, from = Unsatisfied, to = Satisfied
		private void createTransition22(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 87", stateMap.get("_d-ViYbXlEeiaqMHnnobXEA"),
					stateMap.get("_W2U94bXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition22(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_OJe1MbZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 88, from = Uncertain, to = Satisfied
		private void createTransition23(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 88", stateMap.get("_4AggQbXkEeiaqMHnnobXEA"),
					stateMap.get("_joOjEbXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition23(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_fGlPgbZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 89, from = Uncertain, to = Unsatisfied
		private void createTransition24(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 89", stateMap.get("_4AggQbXkEeiaqMHnnobXEA"),
					stateMap.get("_wiI0wbXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition24(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_f1DfELZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 90, from = Unsatisfied, to = Satisfied
		private void createTransition25(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 90", stateMap.get("_d-ViYbXlEeiaqMHnnobXEA"),
					stateMap.get("_joOjEbXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition25(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_oSJnMLZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 91, from = Satisfied, to = Unsatisfied
		private void createTransition26(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 91", stateMap.get("_joOjEbXlEeiaqMHnnobXEA"),
					stateMap.get("_d-ViYbXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition26(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_q1CasbZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 92, from = Satisfied, to = Unsatisfied
		private void createTransition27(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 92", stateMap.get("_joOjEbXlEeiaqMHnnobXEA"),
					stateMap.get("_wiI0wbXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition27(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_wC7b4LZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 93, from = Unsatisfied, to = Satisfied
		private void createTransition28(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 93", stateMap.get("_wiI0wbXlEeiaqMHnnobXEA"),
					stateMap.get("_joOjEbXlEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition28(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_0ESYgLZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 94, from = Satisfied, to = Imitating
		private void createTransition29(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 94", stateMap.get("_joOjEbXlEeiaqMHnnobXEA"),
					stateMap.get("_e6XlcbXoEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition29(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_7nwxALZ3EeisUf7INNtpJg");
		}

		// creates transition Transition 95, from = Unsatisfied, to = Inquiring
		private void createTransition30(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 95", stateMap.get("_wiI0wbXlEeiaqMHnnobXEA"),
					stateMap.get("_l4KeAbXoEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition30(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_D6uFsLZ4EeisUf7INNtpJg");
		}

		// creates transition Transition 102, from = Mental Model, to = Travel Mode Selection
		private void createTransition31(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 102", stateMap.get("_V6Th4bXiEeiaqMHnnobXEA"),
					stateMap.get("_tzoZYbXgEeiaqMHnnobXEA"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_sp3Y4bZ4EeisUf7INNtpJg");
		}

		// creates transition Transition 103, from = Travel Mode Selection, to = Mental Model
		private void createTransition32(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 103", stateMap.get("_tzoZYbXgEeiaqMHnnobXEA"),
					stateMap.get("_V6Th4bXiEeiaqMHnnobXEA"));
			tb.addTrigger(new ConditionTrigger<Passenger>(
					new SC1ConditionTriggerCondition32(), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_vJVE8bZ4EeisUf7INNtpJg");
		}

		// creates transition Transition 146, from = AdoptNewMode, to = PreferredMode
		private void createTransition33(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 146", stateMap.get("_6SU8ILdIEei56ZJa_bxOdQ"),
					stateMap.get("_IoESoLdJEei56ZJa_bxOdQ"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_emjjMbdJEei56ZJa_bxOdQ");
		}

		// creates transition Transition 147, from = PreferredMode, to = PreferredMode
		private void createTransition34(MyStateChartBuilder mscb) {
			TransitionBuilder<Passenger> tb = new TransitionBuilder<Passenger>(
					"Transition 147", stateMap.get("_A9Ui4bXoEeiaqMHnnobXEA"),
					stateMap.get("_IoESoLdJEei56ZJa_bxOdQ"));
			tb.addTrigger(new AlwaysTrigger(1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_fcN_4bdJEei56ZJa_bxOdQ");
		}

	}
}
