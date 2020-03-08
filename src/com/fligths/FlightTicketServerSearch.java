package com.fligths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FlightTicketServerSearch implements FlightTicketSearch {
    private static ArrayList<FlightTicket> TicketList = new ArrayList<>();

    public FlightTicketServerSearch(){}

    @Override
    public List<String> destinationList(String departure){
        /*List<String> destinations = TicketList.stream()
                .filter(ticket -> ticket.getDestination().equals(departure))
                .map(FlightTicket::getDestination)
                .distinct()
                .collect(Collectors.toList());;
        destinations.add(0, "");
        System.out.println(destinations);*/
        List<String> destinations = new ArrayList<>();
        destinations.add(departure);
        destinations.add("Minsk");
        destinations.add("Vienna");
        return destinations;
    }

    @Override
    public List<String> departureList(String destination){
        List<String> departures = TicketList.stream()
                .filter(ticket -> ticket.getDeparture().equals(destination))
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
        for(FlightTicket T:TicketList){
            if(T.getPrice().equals(F.getPrice())&&(T.getDeparture().equals(dep))){
                cheapTickets.append(T).append("\n");
            }
        }
        return Collections.singletonList(cheapTickets.toString());
    }

    @Override
    public List<String> findTicket(String dep, String des){
        StringBuilder tickets = new StringBuilder();
        for(FlightTicket ticket:TicketList){
            System.out.println(ticket.getDeparture() + ticket.getDestination());
            if ((ticket.getDeparture().equals(dep))&&(ticket.getDestination().equals(des))){
                tickets.append(ticket).append("\n");
            }
        }
        return Collections.singletonList(tickets.toString());
    }

    public static void getTickets(){
        String temp;
        try{
            Scanner scanner = new Scanner(new File("C:/projects/flights.csv"));
            while(scanner.hasNext()){
                temp = scanner.nextLine();
                String[] tempTickets = temp.split(",");
                TicketList.add(new FlightTicket(tempTickets[0], tempTickets[1], tempTickets[2], tempTickets[3]));
            }
            scanner.close();
            TicketList.sort(Comparator.comparingDouble(FlightTicket::getPrice));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }
    }
}
