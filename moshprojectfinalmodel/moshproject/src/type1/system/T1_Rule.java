/*
 * Rule.java
 *
 * Created on 19 November 2008, 11:37
 *
 * Copyright 2008 Christian Wagner All Rights Reserved.
 */

package type1.system;

import generic.Input;
import generic.Output;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Rule for a Type-1 Fuzzy System.
 * @author Christian Wagner
 */
public class T1_Rule implements Serializable
{

    private T1_Antecedent[] antecedents;
    private HashMap<Output, T1_Consequent> consequents;
    
    private final boolean DEBUG = false;
    private final byte PRODUCT = 0;
    private final byte MINIMUM = 1;
     T1_Rulebase myCode;
     
   Map<String,Double> myFiringMap;
   List<String> attriname;
   List<Double> firingStrent;
    
    
    
    private double[] fStrengths;    
    private byte implicationMethod = 1;
    private Vector<T1_Rule> rules;
	private String []getAntecedentsName;
    /**
     * Creates a new instance of Rule with a single consequent
     * @param antecedents The array of antecedents
     * @param consequent The consequent (only a single consequent is supported here)
     */
    public T1_Rule(T1_Antecedent[] antecedents, T1_Consequent consequent) {
        this.antecedents = antecedents;
        this.consequents  = new HashMap(1);
        this.consequents.put(consequent.getOutput(), consequent);
      //  this.rules= new Vector<T1_Rule>();
    }
    
    /**
     * Creates a new instance of Rule
     * @param antecedents The array of antecedents
     * @param consequents The array of consequents 
     */
    public T1_Rule(T1_Antecedent[] antecedents, T1_Consequent[] consequents) {
        this.antecedents = antecedents;
        this.consequents  = new HashMap(consequents.length);
        for(int i=0;i<consequents.length;i++)
            this.consequents.put(consequents[i].getOutput(), consequents[i]);        
    }    
    
    public int getNumberOfAntecedents()
    {
        return antecedents.length;
    }
    public int getNumberOfConsequents()
    {
        return consequents.size();
    }
    public T1_Antecedent[] getAntecedents()
    {
        return antecedents;
    }
    
//    public T1_Antecedent [] getNumberOfAntecedents(T1_Rule r) {
//    	
//    	
//		return antecedents;
//		// TODO Auto-generated method stub
//		
//	}
    
    public T1_Consequent[] getConsequents()
    {
        T1_Consequent[] cons = new T1_Consequent[consequents.size()];
        consequents.values().toArray(cons);
        return cons;
    }

    
    /**
     * 
     * @return An iterator over the consequents included in this rule.
     */
    public Iterator<T1_Consequent> getConsequentsIterator()
    {
        return consequents.values().iterator();
    }
    
    /**
     * Returns the inputs of the antecedents used in the current rule.
     */
    public Input[] getInputs()
    {
       Input[] inputs = new Input[this.getNumberOfAntecedents()];
       for (int i=0;i<this.getNumberOfAntecedents();i++)
           inputs[i] = this.getAntecedents()[i].getInput();

       return inputs;
    }
    
    public String[] getAntecedentNames()
    {
       String[] names = new String[this.getNumberOfAntecedents()];
       for (int i=0;i<this.getNumberOfAntecedents();i++)
           names[i] = this.getAntecedents()[i].getName();

       return names;
    }
    
    /**
     *Performs a comparison operation by comparing the rule objects solely based 
     * on their antecedents. The method returns true of the antecedents of both
     * rules are the same.
     */
    public boolean compareBasedOnAntecedents(T1_Rule r)
    {
        if(this.getNumberOfAntecedents()==r.getNumberOfAntecedents())
        {
            for (int i=0;i<this.getNumberOfAntecedents();i++)
            {             
                if(this.getAntecedents()[i].compareTo(r.getAntecedents()[i])!=0)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the rule's firing strength. The method relies on the transparent 
     * updating of the inputs of the fuzzy system through the Input classes 
     * attached to the antecedents.
     * @param tNorm Either product (0) or minimum (1) is currently supported.
     * @return The firing strength.
     */
    public double getFStrength(byte tNorm)
    {
        double fStrength = 1.0; //initialize for multiplication       

        myFiringMap =new HashMap<>();        
        attriname = new ArrayList<>();
        firingStrent =new ArrayList<>();  
        
        
        if(tNorm==PRODUCT)
        {
            //mutliply antecedents (left and right)
            for(int i = 0;i<antecedents.length;i++)
            {
                if(DEBUG)System.out.println("Antecedent "+i+" gives a FS of: "+antecedents[i].getFS()+" with an input of: "+antecedents[i].getInput().getInput());
                fStrength*=antecedents[i].getFS();
            }
        }
        else    //use minimum
        {
        //	System.out.println("=================================== ");
            for(int i = 0;i<antecedents.length;i++)
            {
                if(DEBUG){
                    System.out.println("Antecedent "+antecedents[i].getName()+" gives a FS of: "+antecedents[i].getFS()+" with an input of: "+antecedents[i].getInput().getInput());
                //    System.out.println("This is the rule size"+ getNumberOfRules()) ;
                    }
                fStrength=Math.min(fStrength,antecedents[i].getFS()); 
               
//                if (fStrength>0){
//                	String name =antecedents[i].getName();
//                	attriname.add(name);
//                	firingStrent.add(fStrength);                	
//                }          
//                Iterator<String> nameItr = attriname.iterator();
//                Iterator<Double> firingIterator = firingStrent.iterator();         
//                while (nameItr.hasNext()&&firingIterator.hasNext()){
//               	myFiringMap.put(nameItr.next(), firingIterator.next());    
//               }                 
            }            
//            for(Map.Entry<String,Double> newCollections: myFiringMap.entrySet()){            	
//				 System.out.println("Name ="+ newCollections.getKey()+ ",FiringStrenght =" + newCollections.getValue());
//				
//            }
        } 
        
        return fStrength;
    } 
   
   
    
    
    
    
    
    ///////

    @Override
    public String toString()
    {
        String s;
        s="IF ";
        for (int i=0;i<this.getNumberOfAntecedents();i++)
        {
            s+=this.getAntecedents()[i].getName()+" ";
            if((i+1)<this.getNumberOfAntecedents())
                s+="AND ";
            else
                s+="THEN ";
        }
        for (int i=0;i<this.getNumberOfConsequents();i++)
        {
            s+= this.getConsequents()[i].getName()+" ";
            if((i+1)<this.getNumberOfConsequents())
                s+="AND ";
        }
        return s;
    }

	
}


