package moshproject.agents.mode;

import moshproject.common.Constants;

public class ModeAttribute {
	String name;
	double value;
	Object otherValue;
	
	public ModeAttribute(String name) {
		this.name = name;
	}
	
	public ModeAttribute(String name, double value) {
		this.name = name;
		this.value =value;
	}
	
	public ModeAttribute(String name, Constants.Occupation otherValue) {
		this.name = name;
		this.otherValue =otherValue;
	}
}
