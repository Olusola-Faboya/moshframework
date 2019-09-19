package moshproject.fuzzycollections;

import generic.Input;
import generic.Output;
import generic.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import tools.JMathPlotter;
import type1.sets.T1MF_Interface;
import type1.sets.T1MF_Trapezoidal;
import type1.sets.T1MF_Triangular;
import type1.system.T1_Antecedent;
import type1.system.T1_Consequent;
import type1.system.T1_Rule;
import type1.system.T1_Rulebase;

/*
 * SimpleT1FLS.java
 *
 * Created on May 20th 2012
 *
 * Copyright 2012 Christian Wagner All Rights Reserved.
 */

/**
 * A simple example of a type-1 FLS based on the "How much to tip the waiter"
 * scenario. We have two inputs: food quality and service level and as an output
 * we would like to generate the applicable tip.
 * 
 * @author Christian Wagner
 */
public class FuzzyDecisionGenerator {	
	Input physical, cognitive, affective; //the inputs to the FLS
	Output satisfaction; // the output of the FLS
	T1_Rulebase rulebase; // the rulebase captures the entire FLS
	T1_Rule rule;
	T1_Antecedent[] antecedents;
	T1_Consequent consequent;
	Vector<T1_Rule> rules;
    double[] groupFStrengths;
    
    Map<String,List<Double>> myFiringMap;
    List<String> attriname;
    List<Double> firingStrenght;
     
	
	double physicalValue;
	double cognitiveValue;
	double affectiveValue;
	public double satisfactionLevel;
	
	
	public FuzzyDecisionGenerator() {		

		// Define the inputs
		cognitive = new Input("Cognitive Level", new Tuple(0,1)); // a rating given by a person between -10 and 10
		physical = new Input("Physical Level", new Tuple(0,1)); // a rating given by a person between 0 and 10
		affective = new Input("Affective Level", new Tuple(0,1)); // a rating given by a person between 0 and 10
		satisfaction = new Output("Final Decision", new Tuple(0,1)); // a percentage for the tip
		
		
		T1MF_Triangular unPleasantPhysicalExpMF = new T1MF_Triangular("MF for unPleasantPhysicalExp", 0.0,0.0,0.375);
		//	T1MF_Gaussian fairlyUnpleasantPhysicalExpMF = new T1MF_Gaussian("MF for fairlyUnpleasantPhysicalExp",0.5,0.25);
			T1MF_Triangular neitherPleasantNorUnpleasantPhysicalExpMF = new T1MF_Triangular("MF for neitherPleasantNorUnpleasantPhyExp",0.25,0.5,0.75);
		//	T1MF_Gaussian fairlyPleasantPhysicalMF = new T1MF_Gaussian("MF for fairlyPleasantPhysicalExp",-0.5,0.25);
			T1MF_Triangular pleasantPhysicalMF = new T1MF_Triangular("MF for PleasantPhysicalExp",0.625,1.0,1.0);

			T1MF_Triangular unPleasantCognitiveExpMF = new T1MF_Triangular("MF for unPleasantCognitiveExp", 0.0,0.0,0.375);
		//	T1MF_Gaussian fairlyUnPleasantCognitiveExpMF = new T1MF_Gaussian("MF for fairlyUnPleasantCognitiveExp", 0.5,0.25);
			T1MF_Triangular neitherPleasantNorUnpleasantCognitiveExpMF = new T1MF_Triangular("MF for neitherPleasantNorUnPleasantCogExp",0.25,0.5,0.75);
		//	T1MF_Gaussian fairlyPleasantCognitiveExpMF = new T1MF_Gaussian("MF for fairlyPleasantCognitiveExp",-0.5,0.25);
			T1MF_Triangular pleasantCognitiveExpMF = new T1MF_Triangular("MF for PleasantCognitveExp",0.625,1.0,1.0);

			T1MF_Triangular unPleasantAffectExpMF = new T1MF_Triangular("MF for unPleasantAffectiveExp",0.0,0.0,0.375);
		//	T1MF_Gaussian fairlyUnPleasantAffectExpMF = new T1MF_Gaussian("MF for fairlyUnPleasantAffectiveExp", -0.5,0.25);
			T1MF_Triangular neitherPleasantNorUnpleasantAffectExpMF = new T1MF_Triangular("MF for neitherPleasantNorUnPleasantAffectiveExp",0.25,0.5,0.75);
		//	T1MF_Gaussian fairlyPleasantAffectExpMF = new T1MF_Gaussian("MF for fairlyPleasantAffectiveExp", 0.5,0.25);
			T1MF_Triangular pleasantAffectExpMF = new T1MF_Triangular("MF for pleasantAffectiveExp",0.625,1.0,1.0);
			
			// This section set up the output membership functions
			///
			T1MF_Triangular lowSatisfactionMF = new T1MF_Triangular("Low Satisfaction Level", 0.0,0.0,0.5);
			T1MF_Triangular fairlyLowSatisfactionMF = new T1MF_Triangular("Fairly Low Satisfaction Level", 0.0,0.0,0.5);
			T1MF_Triangular averageSatisfactionMF = new T1MF_Triangular("Medium Satisfaction Level",0.375,0.625,0.875);
			T1MF_Triangular fairlyHighSatisfactionMF = new T1MF_Triangular("Fairly High Satisfaction Level",0.5, 1.0,1.0);
			T1MF_Triangular highSatisfactionMF = new T1MF_Triangular("High Satisafction Level",0.5, 1.0,1.0);

		// Set up the antecedents and consequents - note how the inputs are
		// associated...
		T1_Antecedent unPleasantPhysical = new T1_Antecedent("UnpleasantPhysical", unPleasantPhysicalExpMF, physical);
	//	T1_Antecedent fairlyUnPleasantPhysical = new T1_Antecedent("FairlyUnpleasant Physical", fairlyUnpleasantPhysicalExpMF, physical);
		T1_Antecedent neitherPleasantNorUnPleasantPhysical = new T1_Antecedent("NeitherPleasantNorUnpleasantPhysical", neitherPleasantNorUnpleasantPhysicalExpMF, physical);
	//	T1_Antecedent fairlyPleasantPhysical = new T1_Antecedent("FairlyUnpleasant Physical", fairlyPleasantPhysicalMF, physical);
		T1_Antecedent pleasantPhysical = new T1_Antecedent("PleasantPhysical", pleasantPhysicalMF,physical);
		// /
		T1_Antecedent unPleasantCognitive = new T1_Antecedent("UnpleasantCognitive", unPleasantCognitiveExpMF,cognitive);
	//	T1_Antecedent fairlyUnPleasantCognitive = new T1_Antecedent("FairlyUnpleasant Cognitive", fairlyUnPleasantCognitiveExpMF, cognitive);
		T1_Antecedent neitherPleasantNorUnPleasantCognitive = new T1_Antecedent("NeitherPleasantNorUnpleasantCognitive", neitherPleasantNorUnpleasantCognitiveExpMF, cognitive);
	//	T1_Antecedent fairlyPleasantCognitive = new T1_Antecedent("FairlyPleasant Cognitive", fairlyPleasantCognitiveExpMF, cognitive);
		T1_Antecedent pleasantCognitive = new T1_Antecedent("PleasantCognitive", pleasantCognitiveExpMF, cognitive);
		
		T1_Antecedent unPleasantAffect = new T1_Antecedent("UnpleasantAffective", unPleasantAffectExpMF,affective);
	//	T1_Antecedent fairlyUnPleasantAffect = new T1_Antecedent("FairlyUnPleasantAffect", fairlyUnPleasantAffectExpMF, affective);
		T1_Antecedent neitherPleasantNorUnpleasantAffect = new T1_Antecedent("NeitherPleasantNorUnpleasantAffect", neitherPleasantNorUnpleasantAffectExpMF, affective);
	//	T1_Antecedent fairlyPleasantAffect = new T1_Antecedent("FairlyPleasantAffect", fairlyPleasantAffectExpMF, affective);
		T1_Antecedent pleasantAffect = new T1_Antecedent("PlesantAffect", pleasantAffectExpMF, affective);

		T1_Consequent lowSatisfaction = new T1_Consequent("Low Satisfaction", lowSatisfactionMF, satisfaction);
		T1_Consequent fairlyLowSatisfaction = new T1_Consequent("Fairly Low Satisfcation", fairlyLowSatisfactionMF,satisfaction);
		T1_Consequent averageSatisfaction = new T1_Consequent(" Average Satisfaction", averageSatisfactionMF, satisfaction);
		T1_Consequent fairlyHighSatisfaction = new T1_Consequent("Fairly High Satisfaction", fairlyHighSatisfactionMF, satisfaction);
		T1_Consequent highSatisfaction = new T1_Consequent("High Satisfaction", highSatisfactionMF, satisfaction);

		// Set up the rulebase and add rules
		rulebase = new T1_Rulebase(27);
		
		//
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical, pleasantCognitive, pleasantAffect }, highSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical, pleasantCognitive, neitherPleasantNorUnpleasantAffect }, fairlyHighSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical, pleasantCognitive, unPleasantAffect }, averageSatisfaction));
		///
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical,neitherPleasantNorUnPleasantCognitive,pleasantAffect }, fairlyHighSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical,neitherPleasantNorUnPleasantCognitive,neitherPleasantNorUnpleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical,neitherPleasantNorUnPleasantCognitive,unPleasantAffect }, averageSatisfaction));
		///
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical,unPleasantCognitive,pleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical,unPleasantCognitive,neitherPleasantNorUnpleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasantPhysical,unPleasantCognitive,unPleasantAffect }, fairlyHighSatisfaction));
		///
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,pleasantCognitive,pleasantAffect }, fairlyHighSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,pleasantCognitive,neitherPleasantNorUnpleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,pleasantCognitive,unPleasantAffect }, averageSatisfaction));
		///
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,neitherPleasantNorUnPleasantCognitive,pleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,neitherPleasantNorUnPleasantCognitive,neitherPleasantNorUnpleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,neitherPleasantNorUnPleasantCognitive,unPleasantAffect }, fairlyLowSatisfaction));
		////
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,unPleasantCognitive,pleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,unPleasantCognitive,neitherPleasantNorUnpleasantAffect }, fairlyLowSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasantPhysical,unPleasantCognitive,unPleasantAffect }, lowSatisfaction));
		///
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical,pleasantCognitive,pleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical,pleasantCognitive,neitherPleasantNorUnpleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical,pleasantCognitive,unPleasantAffect }, fairlyLowSatisfaction));
		////
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical,neitherPleasantNorUnPleasantCognitive,pleasantAffect }, averageSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical,neitherPleasantNorUnPleasantCognitive,neitherPleasantNorUnpleasantAffect }, fairlyLowSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical,neitherPleasantNorUnPleasantCognitive,unPleasantAffect }, lowSatisfaction));
		////

		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical, unPleasantCognitive,pleasantAffect },fairlyLowSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical, unPleasantCognitive,neitherPleasantNorUnpleasantAffect }, lowSatisfaction));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasantPhysical, unPleasantCognitive,unPleasantAffect }, lowSatisfaction));
			
		// just an example of setting the discretisation level of an output -
		// the usual level is 100
		satisfaction.setDiscretisationLevel(50);

//		// get some outputs
//		getTip(0.5,0,0.34);
//		
//
//		// plot some sets, discretizing each input into 100 steps.
//				plotMFs("Physical Membership Functions", new T1MF_Interface[] {
//						unPleasantPhysicalExpMF, neitherPleasantNorUnpleasantPhysicalExpMF,pleasantPhysicalMF }, physical.getDomain(), 100);
//				plotMFs("Cognitive Membership Functions", new T1MF_Interface[] {
//						unPleasantCognitiveExpMF, neitherPleasantNorUnpleasantCognitiveExpMF, pleasantCognitiveExpMF },
//						cognitive.getDomain(), 100);
//				plotMFs("Affective Membership Functions", new T1MF_Interface[] {
//						unPleasantAffectExpMF, neitherPleasantNorUnpleasantAffectExpMF, pleasantAffectExpMF},
//						affective.getDomain(), 100);
//				plotMFs("Level of Perception Membership Functions", new T1MF_Interface[] {
//						lowSatisfactionMF, fairlyLowSatisfactionMF, averageSatisfactionMF,fairlyHighSatisfactionMF, highSatisfactionMF}, satisfaction.getDomain(), 100);

			
	}
	/**
	 * Basic method that prints the output for a given set of inputs.
	 * @param object 
	 * 
	 * @param pleasantLevel
	 * @param arouseLevel
	 */
	public Vector getTip(double physicalLevel, double cognitiveLevel, double affectiveLevel) {
		double fStrength =1.0;
		String name = "";
		double value =0; double strenght=0;
		
		Vector<Object> myVector = new Vector<Object>();
		myFiringMap =new HashMap<>();        
	    attriname = new ArrayList<>();
	    firingStrenght =new ArrayList<>();
		// first, set the inputs	
	    if(physicalLevel>1){
	    	physicalLevel=1;
	    }else if (physicalLevel<0){
	    	physicalLevel=0.0;
	    }
	    if(cognitiveLevel>1){
	    	cognitiveLevel=1;
	    }else if (cognitiveLevel<0){
	    	cognitiveLevel=0.0;
	    }
	    if(affectiveLevel>1){
	    	affectiveLevel=1;
	    }else if (affectiveLevel<0){
	    	affectiveLevel=0.0;
	    }	    
		physical.setInput(physicalLevel);
		cognitive.setInput(cognitiveLevel);
		affective.setInput(affectiveLevel);		
		// now execute the FLS and print output
		 satisfactionLevel =rulebase.evaluate(1).get(satisfaction);		 
	// add the fuzzy Logic system output to the vector
		 myVector.add(satisfactionLevel);

//// This section code the firing Strength. 	 
			for(int i=0; i<rulebase.getRules().size();i++){			
				rule=	rulebase.getRules().elementAt(i);
					//	System.out.println("=========================================== ");
					//	System.out.println("The rule number is:"+i);				
						String minName = null;
						int minIdx = -1;
						double minStrength = Double.POSITIVE_INFINITY;						
						for(int j=0; j<rule.getAntecedents().length;j++){		               
						name =	rule.getAntecedents()[j].getName().toString();
						 value =	rule.getAntecedents()[j].getInput().getInput();
						strenght =	rule.getAntecedents()[j].getFS();	               
					//		System.out.println("The Input Value of "+ name+ "  is  "+ value+" with Strenght of: "+ strenght);
							
		                if (strenght >= 0 && strenght < minStrength){
		                	minIdx = j;
		                	minStrength = strenght;           	
		                	minName = name;	                	
		                } 	                
				}  	
						
						
					//	System.out.println("The chosen firing Strenght for "+i+" is:"+ minStrength +" From "+ minName);
						 
						List<Double> FiredAntecedentValue = myFiringMap.get(minName);
	                	if (FiredAntecedentValue == null) {
	                		FiredAntecedentValue = new ArrayList<>();
	                		myFiringMap.put(minName, FiredAntecedentValue);
	                	}
	                	
	                	FiredAntecedentValue.add(minStrength);
	                	
	                	
//					for(Entry<String, List<Double>> newCollections: myFiringMap.entrySet()){
//						System.out.println("=========================================== ");
//						String name1 =newCollections.getKey();
//						List<Double> value1 = newCollections.getValue();
//					 System.out.println( name1 + "=" +value1); 
//					 System.out.println("=========================================== ");	
//					}
			}	
			
			
			myVector.add(myFiringMap);
				 
	//	 return satisfactionLevel;
		 return myVector;
		
		//decisionVariableList.add(rulebase.evaluate(1).get(decision));
	}

	private void plotMFs(String name, T1MF_Interface[] sets, Tuple xAxisRange,
			int discretizationLevel) {
		JMathPlotter plotter = new JMathPlotter(17, 17, 15);
		for (int i = 0; i < sets.length; i++) {
			plotter.plotMF(sets[i].getName(), sets[i], discretizationLevel,
					xAxisRange, new Tuple(0.0, 1.0), false);
		}
		plotter.show(name);
	}

//	private void plotControlSurface(boolean useCentroidDefuzzification,
//			int input1Discs, int input2Discs) {
//		double output;
//		double[] x = new double[input1Discs];
//		double[] y = new double[input2Discs];
//		double[][] z = new double[y.length][x.length];
//		double incrX, incrY;
//		incrX = food.getDomain().getSize() / (input1Discs - 1.0);
//		incrY = service.getDomain().getSize() / (input2Discs - 1.0);
//
//		// first, get the values
//		for (int currentX = 0; currentX < input1Discs; currentX++) {
//			x[currentX] = currentX * incrX;
//		}
//		for (int currentY = 0; currentY < input2Discs; currentY++) {
//			y[currentY] = currentY * incrY;
//		}
//
//		for (int currentX = 0; currentX < input1Discs; currentX++) {
//			food.setInput(x[currentX]);
//			for (int currentY = 0; currentY < input2Discs; currentY++) {
//				service.setInput(y[currentY]);
//				if (useCentroidDefuzzification)
//					output = rulebase.evaluate(1).get(tip);
//				else
//					output = rulebase.evaluate(0).get(tip);
//				z[currentY][currentX] = output;
//			}
////		}
//
//		// now do the plotting
//		JMathPlotter plotter = new JMathPlotter(17, 17, 14);
//		plotter.plotControlSurface("Control Surface",
//				new String[] { food.getName(), service.getName(), "Tip" }, x,
//				y, z, new Tuple(0.0, 30.0), true);
//		plotter.show("Type-1 Fuzzy Logic System Control Surface for Tipping Example");
//	}

	public static void main(String args[]) {
		new FuzzyDecisionGenerator();
	}

	
}
