package moshproject.agents.passenger;

import generic.Input;
import generic.Output;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import moshproject.agents.mode.ModeAttribute;
import moshproject.fuzzycollections.AffectiveComponent;
import type1.system.T1_Antecedent;
import type1.system.T1_Consequent;
import type1.system.T1_Rule;
import type1.system.T1_Rulebase;

public class TravelRequirements implements Serializable{
	
	private List<ModeAttribute> physicalFireStrenght;//= new ArrayList<Double>();
	private List<ModeAttribute> cognitiveFireStrenght;//= new ArrayList<Double>();
	private List<ModeAttribute> affectiveFireStrenght;//= new ArrayList<Double>();
	private ArrayList<ModeAttribute> physical_cognitive_AffectiveFsList;//= new ArrayList<Double>();
	
		
//		public class T1_Rule implements Serializable
//		{

		    private T1_Antecedent[] antecedents;
		    private HashMap<Output, T1_Consequent> consequents;
		    
		    private final boolean DEBUG = true;
		    private final byte PRODUCT = 0;
		    private final byte MINIMUM = 1;
		    
		    
		    private double[] fStrengths;    
		    private byte implicationMethod = 1;
		    private Vector<T1_Rule> rules;
			private HashMap<String, ArrayList<Double>> firingStrenghtMappping;
		    
			List <T1_Antecedent> antecedentList = new ArrayList<T1_Antecedent>();
		    /**
		     * Creates a new instance of Rule with a single consequent
		     * @param antecedents The array of antecedents
		     * @param consequent The consequent (only a single consequent is supported here)
		     */
		    public TravelRequirements(T1_Antecedent[] antecedents, T1_Consequent consequent) {
		        this.antecedents = antecedents;
		        this.consequents  = new HashMap(1);
		        this.consequents.put(consequent.getOutput(), consequent);
		    }
		    
		    /**
		     * Creates a new instance of Rule
		     * @param antecedents The array of antecedents
		     * @param consequents The array of consequents 
		     */
		    public TravelRequirements(T1_Antecedent[] antecedents, T1_Consequent[] consequents) {
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
		    public void getFStrength(byte tNorm)
		    {
		    	physical_cognitive_AffectiveFsList= new ArrayList<ModeAttribute>();
		    	firingStrenghtMappping = new HashMap <String, ArrayList<Double>>();
		        double fStrength = 1.0; //initialize for multiplication
		        String fsAntecedentName;
		        double fsAntecedent;
		        if(tNorm==PRODUCT)
		        {
		            //mutliply antecedents (left and right)
		            for(int i = 0;i<antecedents.length;i++)
		            {
		                if(DEBUG)System.out.println("Antecedent " + antecedents[i].getName() +" gives a FS of: "+antecedents[i].getFS()+" with an input of: "+antecedents[i].getInput().getInput());
		                fStrength*=antecedents[i].getFS();
		            }
		        }
		        else    //use minimum
		        {
		        	int minIdx = 0;
		        			
		            for(int i = 0;i<antecedents.length;i++)
		            {
		                if(DEBUG){
		                    System.out.println("Antecedent "+antecedents[i].getName()+" gives a FS of: "+antecedents[i].getFS()+" with an input of: "+antecedents[i].getInput().getInput());
		                    }	
		                
	                    fsAntecedentName= antecedents[i].getName();
	                    fsAntecedent= antecedents[i].getFS();
	                    
			            
			            // physical_cognitive_AffectiveFsList.add(fsAntecedentName,fsAntecedent);
		                
			            if (antecedents[i].getFS() < antecedents[minIdx].getFS()) {
			            	minIdx = i;
			            }
			            
		                fStrength=Math.min(fStrength,antecedents[i].getFS());
		            }
		            
		            System.out.println("Minimum Antecedent : "+antecedents[minIdx].getName()+" gives a FS of: "+antecedents[minIdx].getFS()+" with an input of: "+antecedents[minIdx].getInput().getInput());
		            antecedentList.add(antecedents[minIdx]);
		            
		            for(int i = 0;i<antecedentList.size();i++)
		            { 
		            	if (antecedentList.get(i).getName().equals("affective")) {
		            		
		            	}
		            }
		            
		        } 
		        
		       // return fStrength;      
		        
		    }

		    

		    private void antecedent1FS(){
		    fStrengths = new double[rules.size()];
		    for (int i=0;i<rules.size();i++)
		    {
		            fStrengths[i] = ((T1_Rule)rules.elementAt(i)).getFStrength(implicationMethod);
		            //((T1_Rule)rules.elementAt(i)))
		            
		            if(DEBUG)System.out.println("fStrength of rule "+i+" is: "+fStrengths[i]);
		            System.out.println("==========");
		    }
		  }

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

//		physicalFireStrenght = new ArrayList<Double>();
//	//	physicalFireStrenght=fireStrenghtAggregate.physicalFsList;
//		
//		if(physicalFireStrenght !=null){			
//		for (Double attr1 : physicalFireStrenght){	
//			System.out.println("the name is "+ attr1);			
//			}
//		}else{
//			System.out.print(" the list is empty");
//		}
//		//double freqPhysicalFireStrenght = prefferedMode.evaluateFiringStrenght(physicalFireStrenght);
	



