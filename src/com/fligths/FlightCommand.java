package com.fligths;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightCommand implements Serializable {
    private String Name;
    private String[] Params;

    public FlightCommand(String Name, String[] Params){
        this.Name = Name;
        this.Params = Params;
    }
    public List<String> getName(){
        return Collections.singletonList(Name);
    }
    public List<String> getParams(){
        List<String> List = new ArrayList<>();
        Collections.addAll(List, Params);
        return List;
    }
}