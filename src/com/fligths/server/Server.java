package com.fligths.server;

import com.fligths.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        System.out.println("Server Started");
        int port = 8081;
        FlightTicketSearch flightTicket =
                FlightTicketServerSearch.createSearchFromFile(new File("C:/projects/flights.csv"));
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            System.out.println("done");
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream;
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            while(true){
                objectInputStream = new ObjectInputStream(inputStream);
                Object object = objectInputStream.readObject();
                ServerFlightCommandExecutor executor = new ServerFlightCommandExecutor(flightTicket);
                FlightTicketList list = new FlightTicketList(executor.executeCommand((FlightCommand)object));
                objectOutputStream.writeObject(list);
                objectOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}