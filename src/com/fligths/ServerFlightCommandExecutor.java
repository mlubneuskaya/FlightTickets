package com.fligths;
import java.util.List;

public class ServerFlightCommandExecutor {

    private FlightTicketSearch flightTicket;

    public ServerFlightCommandExecutor(FlightTicketSearch flightTicket) {
        this.flightTicket = flightTicket;
    }

    public List<String> executeCommand(FlightCommand command){
        switch(command.getName().get(0)) {
            case "findTicket":
                System.out.println("find ticket");
                System.out.println(command.getParams().get(0) + command.getParams().get(1));
                return flightTicket.findTicket(command.getParams().get(0), command.getParams().get(1));
            case "findCheapestTicket":
                System.out.println("cheapest ticket");
                return flightTicket.findCheapest(command.getParams().get(0));
            case "getDepartureList":
                return flightTicket.departureList(command.getParams().get(0));
            case "getDestinationList":
                return flightTicket.destinationList(command.getParams().get(0));
            default:
                return null;
        }
    }
}