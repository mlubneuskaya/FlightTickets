package com.fligths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FlightTicketServerSearch implements FlightTicketSearch {

    private final List<FlightTicket> TicketList;

    public FlightTicketServerSearch(List<FlightTicket> TicketList){
        this.TicketList = TicketList;

    };

    @Override
    public List<String> destinationList(String departure){
        List<String> destinations = TicketList.stream()
                .filter(ticket -> (ticket.getDeparture().equals(departure)))
                .map(FlightTicket::getDestination)
                .distinct()
                .collect(Collectors.toList());
        destinations.add(0, "");
        System.out.println(destinations);
        return destinations;
    }

    @Override
    public List<String> departureList(){
        List<String> departures = TicketList.stream()
                .map(FlightTicket::getDeparture)
                .distinct()
                .collect(Collectors.toList());
        return departures;
    }

    @Override
    public List<String> findCheapest(String dep){
        Iterator<FlightTicket> FT = TicketList.iterator();
        FlightTicket F;
        StringBuilder cheapTickets = new StringBuilder();
        while(true){
            if ((F = FT.next()).getDeparture().equals(dep)) break;
        }
        for(FlightTicket ticket:TicketList){
            if(ticket.getPrice().equals(F.getPrice())&&(ticket.getDeparture().equals(dep))){
                cheapTickets.append(ticket).append("\n");
            }
        }
        return Collections.singletonList(cheapTickets.toString());
    }

    @Override
    public List<String> findTicket(String dep, String des){
        StringBuilder tickets = new StringBuilder();
        if(dep.equals("Minsk")) System.out.println("true");
        for(FlightTicket ticket:TicketList){
            System.out.println(ticket.getDestination());
            if ((dep.equals(ticket.getDeparture())&&(des.equals(ticket.getDestination())))){
                tickets.append(ticket).append("\n");
            }
        }
        return Collections.singletonList(tickets.toString());
    }

    public static FlightTicketSearch createSearchFromFile(File source){
        String temp;
        try{
            Scanner scanner = new Scanner(source);
            List<FlightTicket> ticketList = new ArrayList<>();
            while(scanner.hasNext()){
                temp = scanner.nextLine();
                String[] tempTickets = temp.split(",");
                ticketList.add(new FlightTicket(tempTickets[0], tempTickets[1], tempTickets[2], tempTickets[3]));
            }
            scanner.close();
            ticketList.sort(Comparator.comparingDouble(FlightTicket::getPrice));
            return new FlightTicketServerSearch(ticketList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
           return null;
        }
    }
}
