package moshproject.agents.passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import moshproject.agents.enums.SocialWeight;
import moshproject.agents.intervener.Intervener2;
import moshproject.agents.mode.ActiveMode;
import moshproject.agents.mode.Mode;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.SimUtilities;
import st4hidden.org.antlr.runtime.EarlyExitException;

public abstract class PassengerType {
	
	public Passenger myPassenger;	
	
	
	private Mode prefMode;
	private int strongLinks;
	private int weakLinks;
	private Mode interracteeMode;
	
	//public Map<SocialWeight, Double> SocialWeight;
	Random rand =new Random();
	Intervener2  newPolicy;
	Map<Passenger,Double> interAgentDiff;	 
	private ArrayList<Passenger> passengerList;	
	public void setPassengerList(ArrayList<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	public ArrayList<Passenger> getPassengerList() {return passengerList;}

	private ArrayList<Passenger> selectedPass;		
	
	private ArrayList<Passenger> antiComformist;		
	public ArrayList<Passenger> getAntiComformist() {return antiComformist;	}
	public void setAntiComformist(ArrayList<Passenger> antiComformist) {this.antiComformist = antiComformist;}
	
	private ArrayList<Passenger>comformist;
	public ArrayList<Passenger> getComformist() {return comformist;	}
	public void setComformist(ArrayList<Passenger> comformist) {this.comformist = comformist;}
	
	private ArrayList<Double>superiority;
	public ArrayList<Double> getSuperiority() {return superiority;}
	public void setSuperiority(ArrayList<Double> superiority) {this.superiority = superiority;}

	private ArrayList<Double> socialAgreabilityValue;	
	public ArrayList<Double> getSocialAgreabilityValue() {return socialAgreabilityValue;}
	public void setSocialAgreabilityValue(ArrayList<Double> socialAgreabilityValue) {this.socialAgreabilityValue = socialAgreabilityValue;}

	private double changeInExperience;
	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;

	List<Mode> prefferedModeList = new ArrayList<Mode>();
	private double socialSatisfaction;
	private double socialUncertainty;	
	public double getSocialUncertainty() {
		return socialUncertainty;
	}
	
	public double getSocialSatisfaction() {return socialSatisfaction;}
	
	
	public PassengerType(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.space = space;
		this.grid = grid;	
		strongLinks =4;
		weakLinks=5;
		interracteeMode =null;
	}

/// Change in Experiences that form the uncertainty
	public double changeInExperience(){
		changeInExperience = (myPassenger.getLevelOfNeedsSatisfaction()- (myPassenger.getPreviousExperience()));//*0.9));
		 myPassenger.setChangeInExperience(changeInExperience);
		return myPassenger.getChangeInExperience();
	}	
///	If there are many existence factors--many change in experience can be formed
//	public void changeInEfficiencyExperience(){
//		changeInEfficiencyExperience = (myPassenger.getLevelOfEfficiencyNeedsSatisfaction()-(myPassenger.getPreviousEfficicency()));//*0.9));
//		if (changeInEfficiencyExperience<0){
//			changeInEfficiencyExperience=0;
//		}
//		 myPassenger.setChangeInEfficiencyExperience(changeInEfficiencyExperience);	
//	}	
	
///******THIS SECTION EVALUTATES PREVIOUS EXPERINECES	
	public double updatePreviousExperience(){
		List<Double> previousExperienceList= myPassenger.getModeSatisfactionList();
		if(previousExperienceList.size()==0){
			myPassenger.setPreviousExperience(0.0);
		}else{													
			myPassenger.setPreviousExperience(previousExperienceList.get(previousExperienceList.size()-1));						
		}
		return myPassenger.getPreviousExperience();	
	}
///	If there are many existence factors--many change in experience can be formed	
//	public void  updateExistenceEfficiency(){
//		List<Double> previousEfficiencyList= myPassenger.getModeEffciencyList();		
//		if(previousEfficiencyList.size()==0){
//		//	System.out.println("The element of previous Efficicency list"+previousEfficiencyList.size());
//			myPassenger.setPreviousEfficiency(0.0);
//		}else{													
//			myPassenger.setPreviousEfficiency(previousEfficiencyList.get(previousEfficiencyList.size()-1));
//	//		System.out.println("The element of previous Efficicency list"+previousEfficiencyList.get(previousEfficiencyList.size()-1));
//		}
//	//	return	myPassenger.getdEfficiency();
//	}

//Passenger Behaviours: Imitation, Inquiring, Optimising and Repetition		
	//Repetition behaviour update	
	public void repetition() {	
		System.out.println("I will repeat my last choice process");	
	}
	///Optimising behaviour update	
	public void optimising() {	
		System.out.println("I'm, Optimising");
		try{
		//	if(myPassenger.isAttitudeOfOthersInterventionSet()||myPassenger.isFacilitiesAtDestInterventionSet()||myPassenger.isJourneyStatusInterventionSet()
		//			||myPassenger.isLuggageCarrierInterventionSet()||myPassenger.isRoadCrossingInterventionSet()||myPassenger.isRouteAvailabilityInterventionSet()||
		//			myPassenger.isSideWalkInterventionSet()){
				myPassenger.interventions.applyIntervention();
				System.out.println("I have optimised");
	//		}	
		}catch (Exception e){
			System.out.println(" No passenegr selected");
		}
	//	myPassenger.selectType();
	}
			
	public ArrayList<Passenger> findWeakLinksInteractee(){		
		GridPoint pt = grid.getLocation(myPassenger);
		GridCellNgh<Passenger> nghCreator = new GridCellNgh<Passenger>(grid, pt,Passenger.class,5,5);			
		List<GridCell<Passenger>> gridCells=nghCreator.getNeighborhood(false);
		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
		Passenger passenger =null;
		
		 passengerList = new ArrayList<Passenger>();		
		for(GridCell<Passenger> cell:gridCells){
			if(cell.size()>0){
				Iterable<Passenger> items = cell.items();
				Iterator<Passenger> iterator = items.iterator();								
				while (iterator.hasNext()) {
					passenger = (Passenger) iterator.next();						
					passengerList.add(passenger);					
				}					
			}		
		}	
		for (Passenger passeng:passengerList){
			//	System.out.println(" the selected passengers are "+ passeng);
		}
		myPassenger.setSelectedFriends(passengerList);
		return  passengerList;
	}
	
	private double socialUncertainty(ArrayList<Passenger> selectedPassLists){	
		ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
		passengerList =selectedPassLists;
		ArrayList<Passenger> antiComformist= new ArrayList<>();
		ArrayList<Passenger> comformist= new ArrayList<>();	
		ArrayList<Passenger> superiority= new ArrayList<>();
		double totalPassenger=0.0;

		try{
			if (passengerList.size()>0){
				for (Passenger passenger:passengerList){
//					if ((interractee.passDemography.distanceRange == myPassenger.passDemography.distanceRange)&&
//							(interractee.getPassengerType() instanceof  Cyclist)||(interractee.getPassengerType() instanceof  Pedestrian)
//							||(interractee.getPassengerType() instanceof Scooter)){
//						selectedFriendsList.add(interractee) ;								
//					}
				//	System.out.println(" Friend prefered mode"+ passenger. getPrefferedMode()+"mypassenegr mode"+ myPassenger.getPrefferedMode());
					if ((passenger.passDemography.distanceRange == myPassenger.passDemography.distanceRange)||
					(passenger.passDemography.travelFreq <= myPassenger.passDemography.travelFreq)){
						 comformist.add(passenger);			
					}else{
						antiComformist.add(passenger);
					}
					
					if(passenger.getModeSatisfaction()<myPassenger.getModeSatisfaction()){
						superiority.add(passenger);
					}		
					++totalPassenger;
				}			
			}
			
		}catch(Exception e){
			
		}	
		if(passengerList.size()==0){
			 socialUncertainty =1;	
		}else {
			 socialUncertainty= Double.isNaN(1-comformist.size()/totalPassenger)?0:(1-comformist.size()/totalPassenger);
		}
			myPassenger.setSocialUncertainty(socialUncertainty);			
			System.out.println(" The Socila Uncertainty is "+socialUncertainty );
			return socialUncertainty;
	}
	
	public void socialSatisfactions(ArrayList<Passenger> selectedPassLists){	
	ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
	//passengerList =getPassengerList();
	passengerList =selectedPassLists;
	ArrayList<Passenger> antiComformist= new ArrayList<>();
	ArrayList<Passenger> comformist= new ArrayList<>();	
	ArrayList<Passenger> superiority= new ArrayList<>();
	double totalPassenger=0.0;

	try{
		if (passengerList.size()>0){
			for (Passenger passenger:passengerList){
			//	System.out.println(" Friend prefered mode"+ passenger. getPrefferedMode()+"mypassenegr mode"+ myPassenger.getPrefferedMode());
				if ((passenger.getPassengerType() instanceof  Cyclist)||(passenger.getPassengerType() instanceof  Pedestrian)
						||(passenger.getPassengerType() instanceof Scooter)){//&&((myPassenger.getPassengerType() instanceof  Cyclist)||(myPassenger.getPassengerType() instanceof  Pedestrian)
								//||(myPassenger.getPassengerType() instanceof Scooter))){
					 comformist.add(passenger);			
				}else{
					antiComformist.add(passenger);
				}
				
				if(passenger.getModeSatisfaction()<myPassenger.getModeSatisfaction()){
					superiority.add(passenger);
				}		
				++totalPassenger;
			}			
		}
		
	}catch(Exception e){
		
	}	
//	System.out.println("The FIRST ONE BEGINS HERE ======================== ");
//	System.out.println("The comformist number is "+ comformist.size());
//	System.out.println("The Anticomformist number is "+ antiComformist.size());
//	System.out.println("The Superiority number is "+ superiority.size());
//	System.out.println("The total number is "+ totalPassenger);
	double	comformistNumber = Double.isNaN(comformist.size()/totalPassenger)?0:comformist.size()/totalPassenger;
	double	antiComformistNumber = Double.isNaN(antiComformist.size()/totalPassenger)?0:antiComformist.size()/totalPassenger;
	double	superiorityNumber = Double.isNaN(superiority.size()/totalPassenger)?0:superiority.size()/totalPassenger;
	
//	System.out.println("The comformist value is "+ comformistNumber);
//	System.out.println("The Anticomformist value is "+ antiComformistNumber);
//	System.out.println("The superiority value is "+ superiorityNumber);
////	
//	System.out.println("The FIRST ONE ENDS HERE ========================");	
		myPassenger.setComformistValue(comformistNumber);	
		myPassenger.setAntiComformistValue(antiComformistNumber);		
		myPassenger.setSuperiorityValue(superiorityNumber);		
//		System.out.println(" The Socila Uncertainty is "+socialUncertainty );
	}
///This method computes the social factors weight and determined the social conformity and superiority
	public Map<SocialWeight, Double> getSocialSatisfactionFactors(ArrayList<Passenger> passengersList){			
		Map<SocialWeight, Double> socialFactors = new HashMap<SocialWeight, Double>();		
		double sameMode =0.0;		
		double superioritySatisfaction= 0.0;
		double totalPassenger =0.0;	
		try{
			if (passengersList.size()>0){
				for (Passenger passenger:passengersList){
				//	System.out.println(" Friend prefered mode"+ passenger. getPrefferedMode()+"mypassenegr mode"+ myPassenger.getPrefferedMode());
					if ((passenger.getPassengerType() instanceof  Cyclist)||(passenger.getPassengerType() instanceof  Pedestrian)
							||(passenger.getPassengerType() instanceof Scooter)){
						++sameMode;
					}
					if(passenger.getModeSatisfaction()<myPassenger.getModeSatisfaction()){
						++superioritySatisfaction;
					}			
					++totalPassenger;
				}
			}
		}catch(Exception e){
			
		}	
		try{
			socialFactors.put(SocialWeight.CONFORMITY, (double) (Double.isNaN(sameMode/totalPassenger)?0:sameMode/totalPassenger));	
			socialFactors.put(SocialWeight.ANTI_CONFORMITY, (double) (Double.isNaN((totalPassenger-sameMode)/totalPassenger)?0:(totalPassenger-sameMode)/totalPassenger));
			socialFactors.put(SocialWeight.SUPERIORITY, (double) (Double.isNaN(superioritySatisfaction/totalPassenger)?0:superioritySatisfaction/totalPassenger));			
			}catch (Exception e) {
				// TODO: handle exception
		}

		return socialFactors;
	}

	@SuppressWarnings("unused")
	public void imitation() {	
		System.out.println(" Imitation zone");
		ArrayList<Passenger> passengerList = new ArrayList<Passenger>();	
		ArrayList<Passenger> selectedFriendsList = new ArrayList<Passenger>();	
		Passenger interractee =null;		
		passengerList = getPassengerList();	
		
		if (passengerList == null) {	
			System.out.println("No Passenger match for Imitation" );
			myPassenger.isInteracteeFound();
			return;
		}
		for (Passenger pass: passengerList){
			System.out.println("*****Tthese are seelcted"+ pass.getPassengerType());
		}
		if (passengerList.size()>0) {
			System.out.println("+++++++++The no of passenger is "+ passengerList.size());
				int numberOfElements = (int) (((int) passengerList.size())*myPassenger.getSocialFrequency());//*0.15);
				System.out.println("+++++++++The no of elements selected is "+ numberOfElements);
					for (int i =0; i<= numberOfElements; i++){
						 interractee = passengerList.get(i);
						 if(interractee.passType instanceof NonActiveTraveller){
							 System.out.println(" Non Active traveller" );
								myPassenger.isInteracteeFound();
								return; 
						 } 	
						 if (((interractee.passDemography.distanceRange == myPassenger.passDemography.distanceRange)||
								 (interractee.passDemography.travelFreq <= myPassenger.passDemography.travelFreq))&&
								(interractee.getPassengerType() instanceof  Cyclist)||(interractee.getPassengerType() instanceof  Pedestrian)
								||(interractee.getPassengerType() instanceof Scooter)){
							selectedFriendsList.add(interractee) ;								
						}
					}
			try{				
					int index = RandomHelper.nextIntFromTo(0,selectedFriendsList.size()-1);	
					interractee = selectedFriendsList.get(index);									
					System.out.println("Interractee found");
					 myPassenger.setInteracteeFound(true);	
					 System.out.println("The Interactee is "+ interractee.getPassengerType());
					 System.out.println("The Interactee mode stais is "+ interractee.getModeSatisfaction());
					 myPassenger.setInterracteePassengerType(interractee.passType);					
					 socialUncertainty(selectedFriendsList);					
					 System.out.println("Interactee Distance range is" + interractee.passDemography.distance);					
					 System.out.println("I'm imitating" );									
			}catch(Exception e){
			
			}				
		}		
	}

	
///////
		public double determineSimilarity(Passenger passenger)
		{				
			double travelpattern;
			double distanceRange;
			double previousExperience;
			
			if(passenger.passDemography.travelFreq <= myPassenger.passDemography.travelFreq){
				travelpattern=1;
			}else{
				travelpattern=0;
			}
			if(passenger.passDemography.distanceRange() == myPassenger.passDemography.distanceRange()){
				distanceRange=1;
			}else{
				distanceRange=0;
			}
			previousExperience = 1-Math.abs(passenger.getModeSatisfaction()- myPassenger.getModeSatisfaction());				
			double similarity =  ((previousExperience+ distanceRange+travelpattern)/3);
		//	System.out.println("The total similarity is "+ similarity);
			myPassenger.setSimilarityValue(similarity);
			return similarity;
		}
	////This section evaluates Inquiring process	
	//Note try to use constant as in stupid model here to generate set of passenger to deal with.
	public void inquiring() {		
		System.out.println(" Inquiry zone");
		ArrayList<Passenger> passengerList = new ArrayList<Passenger>();	
		ArrayList<Passenger> selectedFriendsList = new ArrayList<>();
		double similarity=0;;
		passengerList = getPassengerList();
		Passenger interractee =null;
		
		if (passengerList == null) {	
			System.out.println("No Passenger match for Inqury" );
			myPassenger.isInteracteeFound();
			return;
		}		
		if (passengerList.size()>0) {
			System.out.println("+++++++++The no of passenger is "+ passengerList.size());
			int numberOfElements = (int) (((int) passengerList.size())*myPassenger.getSocialFrequency());//*0.15);	
			System.out.println("+++++++++The no of elements selected is "+ numberOfElements);
			for (int i =0; i<= numberOfElements; i++){
				 interractee = passengerList.get(i);
				 if(interractee.passType instanceof NonActiveTraveller){
					 System.out.println(" Non Active traveller" );
						myPassenger.isInteracteeFound();
						return; 
				 } 
				 if ((interractee.passDemography.distanceRange == myPassenger.passDemography.distanceRange)&&
							(interractee.getPassengerType() instanceof  Cyclist)||(interractee.getPassengerType() instanceof  Pedestrian)
							||(interractee.getPassengerType() instanceof Scooter)){
						selectedFriendsList.add(interractee) ;								
					}
			}			
			try{	
					int numberOfTries = 3;
					for (int t = 0; t < numberOfTries; ++t) {
						int index = RandomHelper.nextIntFromTo(0,selectedFriendsList.size()-1);	
						interractee = selectedFriendsList.get(index);
						if (interractee.getPreviousExperience()>myPassenger.getPreviousExperience()) {
							determineSimilarity(interractee);	
														
							if ((myPassenger.getSimilarityValue() > myPassenger.getAssumedMaximumDifference())&&(interractee.passDemography.distanceRange()==myPassenger.passDemography.distanceRange())){
								System.out.println("Interractee found");
								myPassenger.setInteracteeFound(true);	
								socialUncertainty(selectedFriendsList);
								System.out.println("The social Unceracity is" + myPassenger.getSocialUncertainty());
								 myPassenger.setInterracteePassengerType(interractee.passType);		
								System.out.println("I'm Inquiring");									 
							   return;
							}
						}
					}
					
					// TODO: none found
					System.out.println("none found after " + numberOfTries);
					
				}catch(Exception e){
				
					}					
			}
	}
		

	
}
