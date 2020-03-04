package com.fligths;

import java.util.ArrayList;

public class FlightCommandExecutor {
    private FlightTicket FT = new FlightTicket();
    public String executeCommand(FlightCommand command){
        switch(command.getName()) {
            case "find_ticket":
                return FT.find_ticket(command.getParams()[0], command.getParams()[1]);
            case "find_cheapest_ticket":
                return FT.find_cheapest(command.getParams()[0]);
            default:
                return null;
        }
    }
}