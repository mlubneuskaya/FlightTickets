package com.fligths;

public class FlightCommandExecutor {

    private FlightTicketSearch flightTicket;

    public FlightCommandExecutor(FlightTicketSearch flightTicket) {
        this.flightTicket = flightTicket;
    }

    public String executeCommand(FlightCommand command){
        switch(command.getName()) {
            case "find_ticket":
                return flightTicket.find_ticket(command.getParams()[0], command.getParams()[1]);
            case "find_cheapest_ticket":
                return flightTicket.find_cheapest(command.getParams()[0]);
            default:
                return null;
        }
    }
}