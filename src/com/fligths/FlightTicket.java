package com.fligths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FlightTicket {
    private String departure;
    private String destination;
    private Double price;
    private String date;
    private static ArrayList<FlightTicket> TicketList = new ArrayList<>();
    private static SortedSet<String> TEMP = new TreeSet<>();

    FlightTicket(){}

    private FlightTicket(String Departure, String Destination, String Price, String Date){
        departure = Departure;
        destination = Destination;
        price = Double.parseDouble(Price);
        date = Date;
    }

    ArrayList<String> destination_list(String departure){
        TEMP.clear();
        FlightTicket.get_tickets();
        for(FlightTicket FT: TicketList){
            if(FT.departure.equals(departure)){
                TEMP.add(FT.destination);
            }
        }
        ArrayList<String> destinations = new ArrayList<>(TEMP);
        destinations.add(0, "");
        return destinations;
    }

    ArrayList<String> departure_list(String destination){
        TEMP.clear();
        FlightTicket.get_tickets();
        for(FlightTicket FT: TicketList){
            if(!FT.get_departure().equals(destination)){
                TEMP.add(FT.get_departure());
            }
        }
        return new ArrayList<>(TEMP);
    }




    String find_cheapest(String dep){
        Iterator<FlightTicket> FT = TicketList.iterator();
        FlightTicket F;
        StringBuilder cheapTickets = new StringBuilder();
        while(true){
            if ((F = FT.next()).get_departure().equals(dep)) break;
        }
        for(FlightTicket T:TicketList){
            if(T.get_price().equals(F.get_price())&&(T.get_departure().equals(dep))){
                cheapTickets.append(T).append("\n");
            }
        }
        return cheapTickets.toString();
    }


    String find_ticket(String dep, String des){
        StringBuilder tickets = new StringBuilder();
        for(FlightTicket FT:TicketList){
            if ((FT.get_departure().equals(dep))&&(FT.get_destination().equals(des))){
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
                TicketList.sort(Comparator.comparingDouble(FlightTicket::get_price));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                System.exit(1);
            }
        }
    }



    private Double get_price(){
        return price;
    }

    private String get_departure(){
        return departure;
    }

    private String get_destination(){ return destination; }

    private String get_date(){
        return date;
    }


    public String toString() {
        return ("Departure: "+this.get_departure()+
                ", destination: "+ this.get_destination() +
                ", price: "+ this.get_price() +
                ", date : " + this.get_date());
    }
}