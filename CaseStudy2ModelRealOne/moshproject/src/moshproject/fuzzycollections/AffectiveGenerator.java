package moshproject.fuzzycollections;

import generic.Input;
import generic.Output;
import generic.Tuple;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import type1.sets.T1MF_Gaussian;
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
public class AffectiveGenerator {
	
	private String[] columns = {"AffectiveValue"};
	
	
	//create a workbook
	Workbook workbook = new XSSFWorkbook();
	
	//create a Sheet
	Sheet sheet = workbook.createSheet(" The Affective Value");
	
	//create Row
	Row headerRow = sheet.createRow(0);
	
	//creating Cells
	
	
	Input arousing, pleasantness; //the inputs to the FLS
	Output affective; // the output of the FLS
	T1_Rulebase rulebase; // the rulebase captures the entire FLS
	
	double pleasantValue;
	double magnitudeValue;
	
	AffectiveExcelReader reader = new AffectiveExcelReader();
	
    List<AffectiveComponent> newList;
    List<Double> affectiveList = new ArrayList<Double>();
	
	// public double emotion;
	public AffectiveGenerator() {		
		String excelFilePath = "src/AffectiveProperties.xlsx";
		//ExcelReaderExample3 reader = new ExcelReaderExample3();
	    try {
			 newList = reader.readPropertiesFromExcelFile(excelFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Define the inputs
		pleasantness = new Input("Pleasantness Level", new Tuple(-1,1)); // a rating given by a person between -10 and 10
		arousing = new Input("Arousing Level", new Tuple(-1,1)); // a rating given by a person between 0 and 10
		affective = new Output(" Emotional Affective Level", new Tuple(-1,3)); // a percentage for the tip
		
		
		T1MF_Gaussian unPleasantMF = new T1MF_Gaussian("MF for unPleasantness", -1.0, 0.25);
		T1MF_Gaussian fairlyUnpleasantMF = new T1MF_Gaussian("MF for fairlyUnpleasantness",-0.5, 0.25);
		T1MF_Gaussian neitherPleasantNorUnpleasantMF = new T1MF_Gaussian("MF for neitherPleasantNorUnpleasantness", 0.0,0.25);
		T1MF_Gaussian fairlyPleasantMF = new T1MF_Gaussian("MF for fairlyPleasantness",0.5,0.25);
		T1MF_Gaussian pleasantMF = new T1MF_Gaussian("MF for Pleasantness", 1.0,0.25);

		T1MF_Gaussian unArouseMF = new T1MF_Gaussian("MF for unArousing", -1.0, 0.25);
		T1MF_Gaussian fairlyUnarousingMF = new T1MF_Gaussian("MF for fairlyUnarousing", -0.5, 0.25);
		T1MF_Gaussian neitherArouseNorUnarouseMF = new T1MF_Gaussian("MF for neitherArouseNorUnarouse", 0.0,0.25);
		T1MF_Gaussian fairlyArouseMF = new T1MF_Gaussian("MF for fairlyArousing", 0.5,0.25);
		T1MF_Gaussian arouseMF = new T1MF_Gaussian("MF for Arouse",1.0,0.25);
		
// This section set up the output membership functions
// Very Pleasant Membership Function
		T1MF_Gaussian excitedMF = new T1MF_Gaussian("Pleasant and Arouse Affective", 1.0, 0.02);
		T1MF_Gaussian enthusiaticMF = new T1MF_Gaussian(" Pleasant and Fairly Arouse Affective", 0.92, 0.02);
		T1MF_Gaussian pleasedMF = new T1MF_Gaussian("Pleasant and NeitherArouseNorUnarouse Affective", 0.84,0.02);
		T1MF_Gaussian contentedMF = new T1MF_Gaussian("Pleasant and FairlyUnarouse Affective",0.76,0.02);
		T1MF_Gaussian relaxedMF = new T1MF_Gaussian("Pleasant and Unarouse Affective",0.68,0.02);
// Fairly Pleasant Membership Function
		T1MF_Gaussian stimulatedMF = new T1MF_Gaussian("Fairly Pleasant and Arouse Affective", 0.60, 0.02);
		T1MF_Gaussian elatedMF = new T1MF_Gaussian("Fairly Pleasant and Fairly Arouse Affective", 0.52, 0.02);
		T1MF_Gaussian happyMF = new T1MF_Gaussian("Fairly Pleasant and NeitherArouseNorUnarouse Affective", 0.44,0.02);
		T1MF_Gaussian comfortableMF = new T1MF_Gaussian("Fairly Pleasant  and FairlyUnarouse Affective",0.36,0.02);
		T1MF_Gaussian calmMF = new T1MF_Gaussian("Fairly Pleasant and Unarouse Affective",0.28,0.02);

// Neither Pleasant Nor Unpleasant Membership Function
		T1MF_Gaussian afraidMF = new T1MF_Gaussian("NeitherPleasantNorUnpleasant and Arouse Affective", 0.20, 0.02);
		T1MF_Gaussian anxiousMF = new T1MF_Gaussian("NeitherPleasantNorUnpleasant and Fairly Arouse Affective", 0.12, 0.02);
		T1MF_Gaussian neutralMF = new T1MF_Gaussian("NeitherPleasantNorArouse Affective", 0.04,0.02);
		T1MF_Gaussian fatiguedMF = new T1MF_Gaussian("NeitherPleasantNorUnpleasant and FairlyUnarouse Affective",-0.04,0.02);
		T1MF_Gaussian depressedMF = new T1MF_Gaussian("NeitherPleasantNorUnpleasant and Unarouse Affective",-0.12,0.02);

// Fairly Unpleasant Membership Function
		T1MF_Gaussian angryMF = new T1MF_Gaussian("Fairly Unpleasant and Arouse Affective", -0.20, 0.02);
		T1MF_Gaussian frustratedMF = new T1MF_Gaussian("Fairly Unpleasant and Fairly Arouse Affective", -0.28, 0.02);
		T1MF_Gaussian dissatisfiedMF = new T1MF_Gaussian("Fairly Unpleasant and NeitherArouseNorUnarouse Affective",-0.36,0.02);
		T1MF_Gaussian uncomfortableMF = new T1MF_Gaussian("Fairly Unpleasant  and FairlyUnarouse Affective",-0.44,0.02);
		T1MF_Gaussian boredMF = new T1MF_Gaussian("Fairly Unpleasant and Unarouse Affective",-0.52,0.02);

// Unpleasant Membership Function
		T1MF_Gaussian disgustedMF = new T1MF_Gaussian("Unpleasant and Arouse Affective", -0.60, 0.02);
		T1MF_Gaussian discontentMF = new T1MF_Gaussian("Unpleasant and Fairly Arouse Affective", -0.68, 0.02);
		T1MF_Gaussian disappointedMF = new T1MF_Gaussian("Unpleasant and NeitherArouseNorUnarouse Affective",-0.76,0.02);
		T1MF_Gaussian sadMF = new T1MF_Gaussian("Unpleasant  and FairlyUnarouse Affective",-0.84,0.02);			
		T1MF_Gaussian dejectedMF = new T1MF_Gaussian("Unpleasant and Unarouse Affective",-0.94,0.02);
		
// Set up the antecedents and consequents - note how the inputs are
// associated...
		T1_Antecedent unPleasant = new T1_Antecedent("Unpleasant", unPleasantMF, pleasantness);
		T1_Antecedent fairlyUnPleasant = new T1_Antecedent("FairlyUnpleasant", fairlyUnpleasantMF, pleasantness);
		T1_Antecedent neitherPleasantNorUnPleasant = new T1_Antecedent("NeitherPleasantNorUnpleasant", neitherPleasantNorUnpleasantMF, pleasantness);
		T1_Antecedent fairlyPleasant = new T1_Antecedent("FairlyUnpleasant", fairlyPleasantMF, pleasantness);
		T1_Antecedent pleasant = new T1_Antecedent("Pleasant", pleasantMF,pleasantness);
		// /
		T1_Antecedent unArouse = new T1_Antecedent("Unarouse", unArouseMF,arousing);
		T1_Antecedent fairlyUnarouse = new T1_Antecedent("FairlyUnarousing", fairlyUnarousingMF, arousing);
		T1_Antecedent neitherArouseNorUnarouse = new T1_Antecedent("NeitherPleasantNorUnpleasant", neitherArouseNorUnarouseMF, arousing);
		T1_Antecedent fairlyArouse = new T1_Antecedent("FairlyArouse", fairlyArouseMF, arousing);
		T1_Antecedent arouse = new T1_Antecedent("Arouse", arouseMF, arousing);

//Consequent-Very Pleasant Experiences
		T1_Consequent excited = new T1_Consequent("Excited", excitedMF, affective);
		T1_Consequent enthusiastic = new T1_Consequent("Enthusiastic", enthusiaticMF,affective);
		T1_Consequent pleased = new T1_Consequent("Pleased", pleasedMF, affective);
		T1_Consequent contented = new T1_Consequent("Contended", contentedMF, affective);
		T1_Consequent relaxed = new T1_Consequent("Relaxed", relaxedMF, affective);
/// Consequent Fairly Pleasant Experiences
		T1_Consequent stimulated = new T1_Consequent("Stimulated ", stimulatedMF, affective);
		T1_Consequent elated = new T1_Consequent("Elated", elatedMF,affective);
		T1_Consequent happy = new T1_Consequent("Happy", happyMF, affective);
		T1_Consequent comfortable = new T1_Consequent("Comfortable", comfortableMF, affective);
		T1_Consequent calm = new T1_Consequent("Calm", calmMF, affective);
		
/// Consequent Neither Pleasant Nor unpleasant Experiences
		T1_Consequent afraid = new T1_Consequent("Afraid ", afraidMF, affective);
		T1_Consequent anxious = new T1_Consequent("Anxious", anxiousMF,affective);
		T1_Consequent neutral = new T1_Consequent("Neutral", neutralMF, affective);
		T1_Consequent fatigued = new T1_Consequent("Fatigued", fatiguedMF, affective);
		T1_Consequent depressed= new T1_Consequent("Calm", depressedMF, affective);
		
/// Consequent Fairly unpleasant Experiences
		T1_Consequent angry = new T1_Consequent("Angry ", angryMF, affective);
		T1_Consequent frustrated = new T1_Consequent("Frustrated", frustratedMF,affective);
		T1_Consequent dissatisfied = new T1_Consequent("Dissatisfied", dissatisfiedMF, affective);
		T1_Consequent uncomfortable = new T1_Consequent("Uncomfortable", uncomfortableMF, affective);
		T1_Consequent bored= new T1_Consequent("Bored", boredMF, affective);

/// Consequent Unpleasant Experiences
		T1_Consequent disgusted = new T1_Consequent("Disgusted ", disgustedMF, affective);
		T1_Consequent discontent = new T1_Consequent("Discontent", discontentMF,affective);
		T1_Consequent disappointed = new T1_Consequent("Disappointed", disappointedMF, affective);
		T1_Consequent sad = new T1_Consequent("Sad", sadMF, affective);
		T1_Consequent dejected= new T1_Consequent("Dejected", dejectedMF, affective);
				
				
		// Set up the rulebase and add rules
		rulebase = new T1_Rulebase(25);
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasant, arouse }, excited));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasant, fairlyArouse }, enthusiastic));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasant, neitherArouseNorUnarouse }, pleased));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasant, fairlyUnarouse }, contented));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { pleasant, unArouse }, relaxed));
		///
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyPleasant, arouse }, stimulated));	
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyPleasant,fairlyArouse }, elated));	
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyPleasant,neitherArouseNorUnarouse }, happy));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyPleasant,fairlyUnarouse }, comfortable));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyPleasant, unArouse }, calm));
		
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasant, arouse }, afraid));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasant, fairlyArouse }, anxious));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasant, neitherArouseNorUnarouse }, neutral));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasant, fairlyUnarouse },depressed));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { neitherPleasantNorUnPleasant, unArouse }, fatigued));		
		
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyUnPleasant, arouse },angry));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyUnPleasant,fairlyArouse },frustrated));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyUnPleasant, neitherArouseNorUnarouse },dissatisfied));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyUnPleasant,fairlyUnarouse },uncomfortable));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { fairlyUnPleasant,unArouse },bored));
		
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasant, arouse },disgusted));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasant,fairlyArouse },discontent));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasant, neitherArouseNorUnarouse },disappointed));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasant,fairlyUnarouse },sad));
		rulebase.addRule(new T1_Rule(new T1_Antecedent[] { unPleasant,unArouse },dejected));
		

		// just an example of setting the discretisation level of an output -
		// the usual level is 100
		affective.setDiscretisationLevel(50);

		// get some outputs
		for (AffectiveComponent list: newList){
			pleasantValue = list.getPleasantness();
			magnitudeValue =list.getMagnitude();			
			getTip(pleasantValue, magnitudeValue);		
		}
		
		
//		for (Double newAffective: affectiveList){
//		System.out.println(newAffective)	;	
//		}
	
		for (int i =0; i< columns.length; i++){
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
		int rowNum=1;
		for (Double newAffective: affectiveList ){
			
			Row row =sheet.createRow(rowNum++);
			
			row.createCell(0).setCellValue(newAffective);
			
		}
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("The_Affective-File.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	
	

	/**
	 * Basic method that prints the output for a given set of inputs.
	 * 
	 * @param pleasantLevel
	 * @param arouseLevel
	 */
	private void getTip(double pleasantLevel, double arouseLevel) {		
		// first, set the inputs		
	//	System.out.println(pleasantLevel);
		pleasantness.setInput(pleasantLevel);
		arousing.setInput(arouseLevel);
		// now execute the FLS and print output
//		System.out.println("The Level of Pleasantness was: " + pleasantness.getInput());
//		System.out.println("The Level of Aroussness was: "	+ arousing.getInput());		
//		System.out.println("Using centroid defuzzification, the FLS recommends a tip of" + "tip of: " + rulebase.evaluate(1).get(affective));
		// emotion =rulebase.evaluate(1).get(affective);

		// return emotion;
		affectiveList.add(rulebase.evaluate(1).get(affective));
	}

	



	public static void main(String args[]) {
		new AffectiveGenerator();
	}
}
