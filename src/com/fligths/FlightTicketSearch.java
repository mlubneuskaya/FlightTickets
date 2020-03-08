package com.fligths;
import java.util.List;

public interface FlightTicketSearch {

    List<String> destinationList(String departure);

    List<String> departureList();

    List<String> findCheapest(String dep);

    List<String> findTicket(String dep, String des);
}