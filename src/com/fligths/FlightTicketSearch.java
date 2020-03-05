package com.fligths;
import java.util.List;

public interface FlightTicketSearch {

    List<String> destination_list(String departure);

    List<String> departure_list(String destination);

    String find_cheapest(String dep);

    String find_ticket(String dep, String des);
}