package com.fligths;

import java.io.Serializable;
import java.util.List;

public class FlightCommandExecutor{

    private FlightTicketSearch flightTicket;

    public FlightCommandExecutor(FlightTicketSearch flightTicket) {
        this.flightTicket = flightTicket;
    }

    public List<String> executeCommand(FlightCommand command){
        switch(command.getName().get(0)) {
            case "findTicket":
                return flightTicket.findTicket(command.getParams().get(0), command.getParams().get(1));
            case "findCheapestTicket":
                System.out.println("cheapest ticket");
                return flightTicket.findCheapest(command.getParams().get(0));
            case "getDepartureList":
                return flightTicket.destinationList(command.getParams().get(0));
            case "getDestinationList":
                return flightTicket.destinationList(command.getParams().get(1));
            default:
                return null;
        }
    }
}