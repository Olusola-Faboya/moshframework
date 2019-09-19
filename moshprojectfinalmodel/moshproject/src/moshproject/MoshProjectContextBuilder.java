package moshproject;

import moshproject.agents.intervener.Intervener;
import moshproject.agents.intervener.InterventionPanel;
import moshproject.agents.mode.Cycle;
import moshproject.agents.mode.PersonalVehicle;
import moshproject.agents.mode.PublicTransport;
import moshproject.agents.mode.Walking;
import moshproject.agents.passenger.Cyclist;
import moshproject.agents.passenger.Passenger;
import moshproject.agents.passenger.PersonalVehicleUser;
import moshproject.agents.passenger.PublicTransportUser;
import moshproject.agents.passenger.Pedestrian;
import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
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
		int  cyclistCounts = params.getInteger("cyclistCounts");
		int  walkerCounts = params.getInteger("walkerCounts");
		int  personalVehicleCounts = params.getInteger("personalVehicleCounts");
		int  publicTransportCounts = params.getInteger("publicTransportCounts");	
		
		Intervener intervener = new Intervener(space, grid);
		intervener.initialize();			
		context.add(intervener);
		
		
		if (!RunEnvironment.getInstance().isBatch()) {
			InterventionPanel panel = new InterventionPanel(intervener);	
			RSApplication.getRSApplicationInstance().addCustomUserPanel(panel);
			}
		
		////////////////////////////////////////////////////////////////////
		
		for (int i = 0; i < cyclistCounts; i++) {
			Passenger passe = new Passenger(space, grid);
			passe.setPassengerType(new Cyclist(space, grid));
			passe.prefferedMode=new Cycle();
			passe.initialize();
			context.add(passe);
		}
		
		for (int i = 0; i < walkerCounts; i++) {
			Passenger passe = new Passenger(space, grid);
			passe.setPassengerType(new Pedestrian(space, grid));
			passe.prefferedMode=new Walking();
			passe.initialize();
			context.add(passe);
		}
		
		for (int i = 0; i < personalVehicleCounts; i++) {
			Passenger passe = new Passenger(space, grid);
			passe.setPassengerType(new PersonalVehicleUser(space, grid));
			passe.prefferedMode=new PersonalVehicle();
			passe.initialize();
			context.add(passe);
		}
		
		for (int i = 0; i < publicTransportCounts; i++) {
			Passenger passe = new Passenger(space, grid);
			passe.setPassengerType(new PublicTransportUser(space, grid));
			passe.prefferedMode=new PublicTransport();
			passe.initialize();
			context.add(passe);
		}
		
		for (Object obj : context) {			
			NdPoint pt = space.getLocation(obj);
			grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
		}
		
		RunEnvironment.getInstance().endAt(365);
		
		return context;
	}

}
