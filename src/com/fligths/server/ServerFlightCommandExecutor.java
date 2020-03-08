package com.fligths.server;
import com.fligths.FlightCommand;
import com.fligths.FlightTicketSearch;

import java.util.List;

class ServerFlightCommandExecutor {

    private FlightTicketSearch flightTicket;

    ServerFlightCommandExecutor(FlightTicketSearch flightTicket) {
        this.flightTicket = flightTicket;
    }

    List<String> executeCommand(FlightCommand command){
        switch(command.getName().get(0)) {
            case "findTicket":
                return flightTicket.findTicket(command.getParams().get(0), command.getParams().get(1));
            case "findCheapestTicket":
                return flightTicket.findCheapest(command.getParams().get(0));
            case "getDepartureList":
                return flightTicket.departureList();
            case "getDestinationList":
                return flightTicket.destinationList(command.getParams().get(0));
            default:
                return null;
        }
    }
}