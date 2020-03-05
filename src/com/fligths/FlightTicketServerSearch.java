package com.fligths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class FlightTicketServerSearch implements FlightTicketSearch {
    private static ArrayList<FlightTicket> TicketList = new ArrayList<>();
    private static SortedSet<String> TEMP = new TreeSet<>();

    public FlightTicketServerSearch(){}

    @Override
    public ArrayList<String> destination_list(String departure){
        TEMP.clear();
        FlightTicketServerSearch.get_tickets();
        for(FlightTicket FT: TicketList){
            if(FT.getDestination().equals(departure)){
                TEMP.add(FT.getDestination());
            }
        }
        ArrayList<String> destinations = new ArrayList<>(TEMP);
        destinations.add(0, "");
        return destinations;
    }

    @Override
    public ArrayList<String> departure_list(String destination){
        TEMP.clear();
        FlightTicketServerSearch.get_tickets();
        for(FlightTicket FT: TicketList){
            if(!FT.getDeparture().equals(destination)){
                TEMP.add(FT.getDeparture());
            }
        }
        return new ArrayList<>(TEMP);
    }

    @Override
    public String find_cheapest(String dep){
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
        return cheapTickets.toString();
    }

    @Override
    public String find_ticket(String dep, String des){
        StringBuilder tickets = new StringBuilder();
        for(FlightTicket FT:TicketList){
            if ((FT.getDeparture().equals(dep))&&(FT.getDestination().equals(des))){
                tickets.append(FT).append("\n");
            }
        }
        return tickets.toString();
    }

    private static void get_tickets(){
        String temp;
        if(TicketList.isEmpty()){
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

}
