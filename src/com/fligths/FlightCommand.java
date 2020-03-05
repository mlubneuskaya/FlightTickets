package com.fligths;

import java.io.Serializable;

public class FlightCommand implements Serializable {
    private String Name;
    private String[] Params;

    public FlightCommand(String name, String[] params){
        Name = name;
        Params = params;
    }
    public String getName(){return Name;}
    public String[] getParams(){return Params;}
}