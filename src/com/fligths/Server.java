package com.fligths;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Server Started");
        int port = 8081;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            System.out.println("done");
            while(true){
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            }

            /*while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                FlightCommand flightCommand = (FlightCommand) input.readObject();
                System.out.println("Command received:" + flightCommand.getName());
                System.out.println("sleep");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
