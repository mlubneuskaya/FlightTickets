package com.fligths;

import com.fligths.FlightCommand;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class FlightTicketSearchClient implements FlightTicketSearch {

    private Socket socket;

    public FlightTicketSearchClient() {
        try {
            this.socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8081));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<String> destination_list(String departure) {
        return Collections.singletonList("Minsk");
    }

    @Override
    public List<String> departure_list(String destination) {
        return Collections.singletonList("Minsk");
    }

    @Override
    public String find_cheapest(String dep) {
        FlightCommand command = new FlightCommand("find_cheapest_ticket", new String[]{dep});
        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //send command over socket
        //receive response
        //parse response into string
        return "";
    }

    @Override
    public String find_ticket(String dep, String des) {
        return "";
    }
}
