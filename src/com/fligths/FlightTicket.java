package com.fligths;

public class FlightTicket {
    private String departure;
    private String destination;
    private Double price;
    private String date;

    public FlightTicket(String Departure, String Destination, String Price, String Date) {
        departure = Departure;
        destination = Destination;
        price = Double.parseDouble(Price);
        date = Date;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public Double getPrice() {
        return price;
    }

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