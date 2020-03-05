package com.fligths;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Server Started");
        try {
            ServerSocket serverSocket = new ServerSocket(8081);

            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                FlightCommand flightCommand = (FlightCommand) input.readObject();
                System.out.println("Command received:" + flightCommand.getName());
                System.out.println("sleep");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
