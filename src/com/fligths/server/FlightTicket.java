package com.fligths.server;

public class FlightTicket {
    private String departure;
    private String destination;
    private Double price;
    private String date;

    FlightTicket(String Departure, String Destination, String Price, String Date) {
        departure = Departure;
        destination = Destination;
        price = Double.parseDouble(Price);
        date = Date;
    }

    String getDeparture() {return departure;}

    String getDestination() {return destination;}

    Double getPrice() {return price;}

    public String getDate() {
        return date;
    }

    public String toString() {
        return ("Departure: " + this.departure +
                ", destination: " + this.destination +
                ", price: " + this.price +
                ", date : " + this.date);
    }
}