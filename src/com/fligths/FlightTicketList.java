package com.fligths;

import java.io.Serializable;
import java.util.List;

public class FlightTicketList implements Serializable {
    private List<String> list;
    FlightTicketList(List<String> list){
        this.list = list;
    }
    public List<String> getList(){
        return list;
    }
}
