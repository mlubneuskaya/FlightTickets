package com.fligths;

public class FlightCommand{
    private String Name;
    private String[] Params;

    public FlightCommand(String name, String[] params){
        Name = name;
        Params = params;
    }
    public String getName(){return Name;}
    public String[] getParams(){return Params;}
}