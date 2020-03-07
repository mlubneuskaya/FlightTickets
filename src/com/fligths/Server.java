package com.fligths;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        FlightTicketServerSearch.getTickets();
        System.out.println("Server Started");
        int port = 8081;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            System.out.println("done");
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream;
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            FlightTicketServerSearch flightTicket = new FlightTicketServerSearch();
            while(true){
                objectInputStream = new ObjectInputStream(inputStream);
                Object object = objectInputStream.readObject();
                FlightCommandExecutor executor = new FlightCommandExecutor(flightTicket);
                FlightTicketList list = new FlightTicketList(executor.executeCommand((FlightCommand)object));
                objectOutputStream.writeObject(list);
                objectOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}