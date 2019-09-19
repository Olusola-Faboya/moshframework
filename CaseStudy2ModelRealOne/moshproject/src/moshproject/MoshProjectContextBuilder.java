package moshproject;

import moshproject.agents.intervener.Intervener;
import moshproject.agents.intervener.Intervener2;
import moshproject.agents.intervener.InterventionPanel;
import moshproject.agents.mode.NonActiveMode;
import moshproject.agents.mode.ActiveMode;
import moshproject.agents.passenger.Cyclist;
import moshproject.agents.passenger.Passenger;
import moshproject.agents.passenger.NonActiveTraveller;
import moshproject.agents.passenger.Pedestrian;
import moshproject.agents.passenger.Scooter;
import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.ui.RSApplication;

public class MoshProjectContextBuilder implements ContextBuilder<Object>{
	
	@Override
	public Context build(Context<Object> context) {
		context.setId("moshproject");
		
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("space", context, new RandomCartesianAdder<Object>(), 
				new repast.simphony.space.continuous.WrapAroundBorders(), 100,100);
		
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context, 
				new GridBuilderParameters<Object>(new WrapAroundBorders(),
						new SimpleGridAdder<Object>(), true, 100,100));
		
		Parameters params = RunEnvironment.getInstance().getParameters();
		int  pedestrianCounts = params.getInteger("pedestrianCounts");
		int  cyclistCounts = params.getInteger("cyclistCounts");
		int  nonActiveTravellerCounts = params.getInteger("nonActiveCounts");
		int  scooterCounts = params.getInteger("scooterCounts");	
		
		Intervener intervener = new Intervener(space, grid);
		intervener.initialize();			
		context.add(intervener);
		
		if (!RunEnvironment.getInstance().isBatch()) {
			InterventionPanel panel = new InterventionPanel(intervener);	
			RSApplication.getRSApplicationInstance().addCustomUserPanel(panel);
			}		
		////////////////////////////////////////////////////////////////////
//		ISchedule schedule =RunEnvironment.getInstance().getCurrentSchedule();
//		ScheduleParameters param=ScheduleParameters.createOneTime(1);
//		schedule.schedule(param,this);	
		
		///////////////////////////////////////////////////////////////////
		for (int i = 0; i < nonActiveTravellerCounts; i++) {
			Passenger passe = new Passenger(space, grid);
			passe.setPassengerType(new NonActiveTraveller(space,grid));
			passe.prefferedMode=new NonActiveMode();
			passe.initialize();
			context.add(passe);
		}
		
		for (int i = 0; i < pedestrianCounts; i++) {
			Passenger passe = new Passenger(space, grid);
			//passe.passType = new Pedestrian(space, grid);
			passe.setPassengerType(new Pedestrian(space,grid));
			passe.prefferedMode=new ActiveMode();
			passe.initialize();
			context.add(passe);
		}
		
		for (int i = 0; i < cyclistCounts; i++) {
			Passenger passe = new Passenger(space, grid);
		//	passe.passType =new Cyclist(space, grid);
			passe.setPassengerType(new Cyclist(space,grid));
			passe.prefferedMode=new ActiveMode();
			passe.initialize();
			context.add(passe);
		}
		
		for (int i = 0; i < scooterCounts; i++) {
			Passenger passe = new Passenger(space, grid);
			//passe.passType =new Scooter(space, grid);
			passe.setPassengerType(new Scooter(space,grid));
			passe.prefferedMode=new ActiveMode();
			passe.initialize();
			context.add(passe);
		}
		
		for (Object obj : context) {			
			NdPoint pt = space.getLocation(obj);
			grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
		}
		
	//	RunEnvironment.getInstance().endAt(365);
		
		return context;
	}
	

}
