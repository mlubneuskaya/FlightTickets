package com.fligths;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
        /*try {
            this.socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8081));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
        FlightCommand command = new FlightCommand("find_cheapest_ticket", new String[]{dep});
        try{
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return Collections.singletonList("");
    }

    @Override
    public List<String> findTicket(String dep, String des) {
        return Collections.singletonList("");
    }
}
