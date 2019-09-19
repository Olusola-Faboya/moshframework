package moshprojectagents.journey;

import repast.simphony.random.RandomHelper;

public class CyclingRange extends JourneyType {
	
	CyclingRange journey;
	
	public double cyclingDistRange() {
		 double lower= 0.0;
		 double upper =10.0;
			double  cyclingRange = RandomHelper.nextDoubleFromTo(lower, upper);
			return cyclingRange;
	}

}
