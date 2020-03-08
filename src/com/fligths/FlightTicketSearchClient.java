package com.fligths;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class FlightTicketSearchClient implements FlightTicketSearch {

    private Socket socket;
    private ObjectInputStream objectInputStream;

    public FlightTicketSearchClient() {
        String address = "127.0.0.1";
        try{
            InetAddress ipAddress = InetAddress.getByName(address);
            int port = 8081;
            this.socket = new Socket(ipAddress, port);
            System.out.println("Soooocket");
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> destinationList(String departure) {
        FlightCommand command = new FlightCommand("getDestinationList", new String[]{departure});
        List<String> destinations = null;
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            destinations = ((FlightTicketList)objectInputStream.readObject()).getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destinations;
    }

    @Override
    public List<String> departureList(String destination) {
        FlightCommand command = new FlightCommand("getDepartureList", new String[]{destination});
        List<String> departures = null;
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            departures = ((FlightTicketList)objectInputStream.readObject()).getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departures;
    }

    @Override
    public List<String> findCheapest(String dep) {
        FlightCommand command = new FlightCommand("findCheapestTicket", new String[]{dep});
        return getList(command);
    }

    @Override
    public List<String> findTicket(String dep, String des) {

        FlightCommand command = new FlightCommand("findTicket", new String[]{dep, des});
        return getList(command);
    }

    private List<String> getList(FlightCommand command) {
        String string ="";
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            string = ((FlightTicketList)objectInputStream.readObject()).getList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.singletonList(string);
    }
}
