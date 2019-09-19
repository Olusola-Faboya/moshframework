package moshproject.agents.passenger;

import java.util.Random;

import repast.simphony.random.RandomHelper;

public class Demography {
	int age;
	double distance;
	boolean disAbility =false;
	String sex;
	String distanceRange;
	String ageGroup;
	int travelFreq;
	Random rand = new Random();
	
	public Demography(){			
		ageGroup();
		distanceRange();
		sex();
		disAbility();
		travelFrequency();
	}
	
	public String distanceRange(){
		if((distance>=0)&&(distance<=1)){
			distanceRange ="BetweenZeroAndOne";
		}else if((distance>=0)&&(distance<=2)){
			distanceRange ="BetweenZeroAndTwo";
		}else if((distance>=0)&&(distance<=3)){
			distanceRange ="BetweenZeroAndThree";
		}else if((distance>=0)&&(distance<=4)){
			distanceRange ="BetweenZeroAndFour";
		}else if((distance>=0)&&(distance<=5)){
			distanceRange ="BetweenZeroAndFive";
		}else if(age>5){
			distanceRange ="BetweenFiveAndTen";
		}
		return distanceRange;		
	}
	public String ageGroup(){
		if((age>=1)&&(age<=12)){
			ageGroup ="Children";
		}else if((age>=13)&&(age<=19)){
			ageGroup= "Teenager";
		}else if((age>=20)&&(age<=39)){
			ageGroup= "Young People";
		}else if((age>=40)&&(age<=55)){
			ageGroup= "Average Age";
		}else if((age>=56)&&(age<=65)){
			ageGroup= "Sixty Plus";
		}else if((age>65)){
			ageGroup= "Seventy Plus";
		}
		return ageGroup;		
	}
	
	
	public void sex(){
		sex ="Male";
		if (rand.nextDouble()<=0.6){
		sex ="Female";
			
		} 
		
	}
	
	public void disAbility(){
		if (rand.nextDouble()<=0.10){
			disAbility=true;			
		} 
	}
	
	public int travelFrequency(){			
		if(rand.nextDouble()<=0.0459){
			travelFreq= RandomHelper.nextIntFromTo(1,2);
		}else if(rand.nextDouble()<=0.0459+0.182){
			travelFreq=RandomHelper.nextIntFromTo(2,3);
		}else if(rand.nextDouble()<=0.0459+0.182+0.227){
			travelFreq=RandomHelper.nextIntFromTo(3,5);	
		}else{
			travelFreq=RandomHelper.nextIntFromTo(5,7);		
		}
		return travelFreq;
	}
		
}
