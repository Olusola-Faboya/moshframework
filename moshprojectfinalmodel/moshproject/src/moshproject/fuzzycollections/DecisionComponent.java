package moshproject.fuzzycollections;

public class DecisionComponent {
	//private String title;
    private double physical;
    private double cognitive;
    private double affective;
 
    public DecisionComponent(double physical,double cognitive, double affective) {
    	this.physical=physical;
    	this.cognitive =cognitive;
    	this.affective=affective;
    	
    }
 
    public String toString() {
        return String.format(" %.2f-%.2f -%.2f", physical, cognitive, affective);
    }

	public double getPleasantness() {
		return physical;
	}

	public void setPleasantness(double pleasantness) {
		this.physical=pleasantness;
	}

	public double getMagnitude() {
		return cognitive;
	}

	public void setMagnitude(double magnitude) {
		this.cognitive = magnitude;
	}

	public double getAffective() {
		return affective;
	}

	public void setAffective(double affective) {
		this.affective = affective;
	}
    
	
    
}
