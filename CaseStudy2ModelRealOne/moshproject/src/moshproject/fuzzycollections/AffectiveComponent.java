package moshproject.fuzzycollections;

public class AffectiveComponent {
	//private String title;
    private double pleasantness;
    private double magnitude;
 
    public AffectiveComponent(double pleasantness, double magnitude) {
    	this.pleasantness=pleasantness;
    	this.magnitude =magnitude;
    	
    }
 
    public String toString() {
        return String.format(" %.2f-%.2f", pleasantness, magnitude);
    }

//	public String getTitle() {
//		return title;
//	}

//	public void setTitle(String title) {
//		this.title = title;
//	}

	public double getPleasantness() {
		return pleasantness;
	}

	public void setPleasantness(double pleasantness) {
		this.pleasantness=pleasantness;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
    
    
}
