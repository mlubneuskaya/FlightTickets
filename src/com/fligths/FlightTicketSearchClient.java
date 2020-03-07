package com.fligths;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

public class FlightTicketSearchClient implements FlightTicketSearch {

    private Socket socket;

    public FlightTicketSearchClient() {
        String address = "127.0.0.1";
        try{
            InetAddress ipAddress = InetAddress.getByName(address);
            int port = 8081;
            this.socket = new Socket(ipAddress, port);
            System.out.println("Soooocket");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<String> destinationList(String departure) {
        return Collections.singletonList("Minsk");
    }

    @Override
    public List<String> departureList(String destination) {
        return Collections.singletonList("Minsk");
    }

    @Override
    public List<String> findCheapest(String dep) {
        FlightCommand command = new FlightCommand("findCheapestTicket", new String[]{dep});
        String string ="";
        try{
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            string = ((FlightTicketList)objectInputStream.readObject()).getList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return Collections.singletonList(string);
    }

    @Override
    public List<String> findTicket(String dep, String des) {
        return Collections.singletonList("");
    }
}
