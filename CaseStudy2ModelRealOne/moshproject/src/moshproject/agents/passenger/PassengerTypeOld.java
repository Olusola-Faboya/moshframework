package moshproject.agents.passenger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import moshproject.agents.mode.Mode;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.SimUtilities;

public abstract class PassengerTypeOld {
	
	public Passenger myPassenger;	
	private int strongLinks;
	private int weakLinks;
	private Mode interracteeMode;
	private Mode inquiringInterracteeMode;
	private Mode imitationInterracteeMode;
	private double changeInExperience;
	public double getChangeInExperience() {
		return changeInExperience;
	}
	
	private double uncertaintyTolerance;
	public double getUncertaintyTolerance() {
		return uncertaintyTolerance;
	}
	public double setUncertaintyTolerance(double uncertaintyTolerance) {
		return this.uncertaintyTolerance = uncertaintyTolerance;
	}

	private ContinuousSpace<Object> space;
	private Grid<Object> grid;

	List<Mode> prefferedModeList = new ArrayList<Mode>();
	
	public PassengerTypeOld(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.space = space;
		this.grid = grid;	
		strongLinks =2;
		weakLinks=8;
	}
		
	public double changeInExperience(){
		changeInExperience = myPassenger.getLevelOfNeedsSatisfaction()-myPassenger.getPreviousExperience();
		System.out.printf("===> TEST: %f | %f\n", myPassenger.getLevelOfNeedsSatisfaction(),
				myPassenger.getPreviousExperience());
//		if(changeInExperience<0){
//			changeInExperience=0.0;
//		}
		return changeInExperience;
	}
			
//	public void setChangeInExperience(double changeInExperience) {
//		this.changeInExperience = changeInExperience;
//	}
	public double updatePreviousExperience(){
		List<Double> previousExperienceList= myPassenger.getModeSatisfactionList();
		if(previousExperienceList.size()==0){
			myPassenger.setPreviousExperience(0.0);
		}else{													
			myPassenger.setPreviousExperience(previousExperienceList.get(previousExperienceList.size()-1));						
		}
		return myPassenger.getPreviousExperience();
	}
	
	//Passenger Behaviours: Imitation, Inquiring, Optimising and Repetition	
	
	//Repetition behaviour update	
	public void repetition() {
		double changeInExperience = myPassenger.getLevelOfNeedsSatisfaction()-myPassenger.getPreviousExperience();
	// changeInSatisfaction =  changeInExperience;
	//	myPassenger.setSocialSatisfaction(changeInSatisfaction);
		myPassenger.setChangeInExperience(changeInExperience);
		System.out.println("I will repeat my last choice process");
	}
	///Optimising behaviour update	
	public void optimising() {
		double changeInExperience = myPassenger.getLevelOfNeedsSatisfaction()-myPassenger.getPreviousExperience();
		double changeInSatisfaction =  myPassenger.getSocialInformation();		
		myPassenger.setSocialSatisfaction(changeInSatisfaction );
		myPassenger.setChangeInExperience(changeInExperience);
		System.out.println("I'm, Optimising");
	}
	//This section returns passenger to interact with during imitation
	///Imitation behaviour interaction and update
	public Passenger findImitationInteractee(){
		double diffInLevelOfSatisfaction=0.0;
		GridPoint pt = grid.getLocation(myPassenger);
//		 System.out.println("The OriginalPassenger @ location %s" + grid.getLocation(myPassenger).toString());
		GridCellNgh<Passenger> nghCreator = new GridCellNgh<Passenger>(grid, pt,Passenger.class,strongLinks,strongLinks);			
		List<GridCell<Passenger>> gridCells=nghCreator.getNeighborhood(false);
		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
		Passenger passenger =null;
		ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
		for(GridCell<Passenger> cell:gridCells){
			if(cell.size()>0){
				Iterable<Passenger> items = cell.items();
				Iterator<Passenger> iterator = items.iterator();								
				while (iterator.hasNext()) {
					passenger = (Passenger) iterator.next();		
					if (passenger.passType==myPassenger.passType){
						//diffInLevelOfSatisfaction=	passenger.getPreviousExperience()-myPassenger.getLevelOfNeedsSatisfaction();
						//if ((diffInLevelOfSatisfaction>0)&&(diffInLevelOfSatisfaction<=myPassenger.getAssumedMaximumDifference())){
							passengerList.add(passenger);
						//}
					}
											
				}					
			}		
		}
	
		if(passengerList.size()>0){
			int numberOfElements = (int) (((int) passengerList.size())*myPassenger.getSocialFrequency());//*0.15);
			//for (int i=0; i<=numberOfElements;i++)
			try{
				int index = RandomHelper.nextIntFromTo(0,numberOfElements);
				 passenger = passengerList.get(index);
			//	 System.out.println("The selected Passenger @ location %s" + grid.getLocation(passenger).toString());				
			}catch (Exception e){
			//	System.out.println("No Passenger match for Imitation" );
			}
		}
		return passenger;
	}
		
	@SuppressWarnings("unused")
	public void imitation() {	
		System.out.println(" Imitation zone");	
		Passenger interractee = findImitationInteractee();		
		if (interractee == null){
			myPassenger.isInteracteeFound();
			System.out.println("no interractee found");
			return;	
		} else myPassenger.setInteracteeFound(true);
		System.out.println("interractee found");
		double changeInSatisfaction =0.0;	
		double changeInExperience = interractee.getPreviousExperience() - myPassenger.getPreviousExperience();
		if(changeInExperience<0){
		myPassenger.setSocialUncertainty(changeInExperience);//	changeInExperience=0.0;
		}else {
			 changeInSatisfaction = changeInExperience;	
		}		
		interracteeMode = interractee.prefferedMode;
		myPassenger.setChangeInExperience( changeInExperience );
		myPassenger.setSocialSatisfaction( changeInSatisfaction );	
		myPassenger.setInterracteePrefferedMode(interracteeMode);
	//	myPassenger.setPrefferedMode(interracteeMode);
		System.out.println("The imitation interactee is" + interracteeMode);
	}

	////This section evaluates Inquiring process	
	//Note try to use constant as in stupid model here to generate set of passenger to deal with.
	private ArrayList<Double> interAgentDifference(){
				double diffInLevelOfSatisfaction ;
				GridPoint pt = grid.getLocation(myPassenger);
				GridCellNgh<Passenger> nghCreator = new GridCellNgh<Passenger>(grid, pt,Passenger.class,weakLinks,weakLinks);			
				List<GridCell<Passenger>> gridCells=nghCreator.getNeighborhood(false);
				SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
				Passenger passenger =null;
				ArrayList<Double> differenceList = new ArrayList<Double>();
				for(GridCell<Passenger> cell:gridCells){
					if(cell.size()>0){
						RandomHelper.nextIntFromTo(0,cell.size()-1);
						Iterable<Passenger> items = cell.items();
					
						Iterator<Passenger> iterator = items.iterator();								
						while (iterator.hasNext()) {
							passenger = (Passenger) iterator.next();						
						//	if((myPassenger.passengerType == passenger.passengerType)){	
								diffInLevelOfSatisfaction= passenger.getPreviousExperience()-myPassenger.getPreviousExperience();						
								
								if (diffInLevelOfSatisfaction>0){
									differenceList.add(diffInLevelOfSatisfaction);
								}
						//	}
						}			
						
					}
				}
					
				return differenceList;
			}

	private ArrayList<Passenger> socialComformist(){
				double diffInLevelOfSatisfaction;
				GridPoint pt = grid.getLocation(myPassenger);
				GridCellNgh<Passenger> nghCreator = new GridCellNgh<Passenger>(grid, pt,Passenger.class,weakLinks,weakLinks);			
				List<GridCell<Passenger>> gridCells=nghCreator.getNeighborhood(false);
				SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
				Passenger passenger =null;
				ArrayList<Passenger> passengerList2 = new ArrayList<Passenger>();
				for(GridCell<Passenger> cell:gridCells){
					if(cell.size()>0){
						Iterable<Passenger> items = cell.items();				
						Iterator<Passenger> iterator = items.iterator();								
						while (iterator.hasNext()) {
							passenger = (Passenger) iterator.next();											
							diffInLevelOfSatisfaction= passenger.getPreviousExperience()-myPassenger.getPreviousExperience();								
								if (diffInLevelOfSatisfaction>0){
									passengerList2.add(passenger);
								}
						}			
						
					}
				}
				return passengerList2;
			}
		
	private boolean checkSocialAgreeability(){
		ArrayList<Double> differenceList = new ArrayList<Double>();
		differenceList= interAgentDifference();
		if (differenceList != null) {
			for(Double diff: differenceList){
				if (diff>=myPassenger.getAssumedMaximumDifference());
				
			}				
		}
		return true;				
	}	
	///Inquiring behaviour interaction and update
	private Passenger findInquiringInteractee(){//this is for 			
		Passenger passenger=null;
		ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
		if (checkSocialAgreeability());
		passengerList= socialComformist();	
		try{
			if(passengerList.size()>0){
				int numberOfElements = (int) (((int) passengerList.size())*myPassenger.getSocialFrequency());//*0.15);
				int index = RandomHelper.nextIntFromTo(0,numberOfElements);
				 passenger = passengerList.get(index);		
			}
	//		System.out.println("The inquiring Passenger is @ location %s" + grid.getLocation(passenger).toString());
		}catch(Exception e){
			
		}
		return passenger;			
	}
	
	
	public void inquiring() {		
		double changeInSatisfaction =0.0;
		Passenger interactee = findInquiringInteractee();	
		if (interactee == null) {
			myPassenger.isInquiryInteracteeFound();
			return;
		}else myPassenger.setInquiryInteracteeFound(true);		
			
		double changeInExperience = interactee.getLevelOfNeedsSatisfaction() - myPassenger.getPreviousExperience();
		if(changeInExperience<0){
			myPassenger.setSocialUncertainty(changeInExperience);//	changeInExperience=0.0;
		}else {
			 changeInSatisfaction = changeInExperience;	
		}
		changeInSatisfaction =  changeInExperience;
		interracteeMode=interactee.prefferedMode;
		myPassenger.setChangeInExperience(changeInExperience);
		myPassenger.setSocialSatisfaction(changeInSatisfaction);
	//	myPassenger.setPrefferedMode(interracteeMode);
		myPassenger.setInterracteePrefferedMode(interracteeMode);
		System.out.print("I'm Inquiring");		
	}	
}
