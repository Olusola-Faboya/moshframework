package moshproject.agents.passenger;

import java.util.Random;

public class Demography {
	int age;
	int occupation;
	boolean disAbility =false;
	double distance;
	String workStatus;
	String sex;
	String distanceRange;
	String ageGroup;
	Random rand = new Random();
	
	public Demography(){			
		ageGroup();
		workStatus();
		sex();
		disAbility();
		distanceRange();
	}
	
	public String workStatus(){
		if(occupation==1){
			workStatus= "Professor";
		}else if(occupation==2){
			workStatus= "OtherAcademics";
		}else if(occupation==3){
			workStatus= "SeniorManager";
		}else if(occupation==4){
			workStatus= "MiddleManager";
		}else if(occupation==5){
			workStatus= "JuniorManager";
		}else if(occupation==6){
			workStatus= "SkilledManual";
		}else if(occupation==7){
			workStatus= "UnskilledManual";
		}else if(occupation==8){
			workStatus= "FullTimeStudent";
		}else if(occupation==9){
			workStatus= "PartimeStudent";
		}else if(occupation==10){
			workStatus= "Retired";
		}
		return workStatus;		
	}
	
	public String distanceRange(){
		if((distance==0)&&(distance<=3)){
			distanceRange= "WalkDistance";
		}else if((distance>3)&&(distance<=7)){
			distanceRange= "CycleDistance";
		}else {
			distanceRange= "Others";
		}
		return distanceRange;		
	}
	public String ageGroup(){
		if((age>=18)&&(age<=35)){
			ageGroup= "Young People";
		}else if((age>=35)&&(age<=50)){
			ageGroup= "Average Age";
		}else if((age>=51)&&(age<=60)){
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
	
		
}
