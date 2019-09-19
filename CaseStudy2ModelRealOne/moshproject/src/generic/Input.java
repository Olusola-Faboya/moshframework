/*
 * Input.java
 *
 * Created on 16 May 2007, 11:25
 *
 * Author: Christian Wagner
 * Copyright 2006 Christian Wagner All Rights Reserved.
 */

package generic;

import java.io.Serializable;

/**
 * The Input class allows the management and updating of one input, for example
 * as part of fuzzy membership functions.
 * @author Christian Wagner
 */
public class Input implements Serializable
{
    protected double x;
    private String name;
    private Tuple domain;

    public Input(String name, Tuple domain)
    {
            this.name = name;
            this.domain = domain;
            this.x = 0;            
    } 
    public Input(String name, Tuple domain, double x)
    {
            this.name = name;
            this.domain = domain;        
            this.x=x;
    }

    public Input(){}   //no args constructor for serialization

    public String getName()
    {
        return name;
    }
    public void setName(String name) 
    {
        this.name = name;
    }   
    public double getInput()
    {
        return x;
    }
    public Tuple getDomain()
    {
        return domain;
    }
    public void setDomain(Tuple domain) 
    {
        this.domain = domain;
    }

    /**
     * Set the numeric input value x for this input.
     * @param x The numeric value
     */
    public void setInput(double x)
    {
        if(domain.contains(x))
            this.x = x;
        else
            throw new BadParameterException("The input value "+x+" was rejected "
                    + "as it is outside of the domain for this input: "
                    + "["+domain.getLeft()+", "+domain.getRight()+"].");
    }    
    
    public String toString()
    {
        return "Input: '"+name+"' with value: "+x;
    }
}


