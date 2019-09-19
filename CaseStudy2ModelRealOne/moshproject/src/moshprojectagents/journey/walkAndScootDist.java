package moshprojectagents.journey;

import repast.simphony.random.RandomHelper;

public class walkAndScootDist extends JourneyType {
	
	walkAndScootDist journey;
	public double WalkingAndScootDistRange() {
		 double lower= 0.0;
		 double upper =5.0;
			double  walkingRange = RandomHelper.nextDoubleFromTo(lower, upper);
			return walkingRange;
	}
		
		

}
