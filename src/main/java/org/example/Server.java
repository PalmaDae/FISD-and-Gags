package org.example;

import java.io.IOException;
import java.net.ServerSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8098);
        Socket clientSocket = serverSocket.accept();
        System.out.println("socket " + clientSocket.getPort() + " accepted");

        BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = null;
        BufferedWriter writer = null;

        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        String messageFromClient;
        String messageFromServer;

        while (true) {
            messageFromClient = reader.readLine();

            if (messageFromClient.equals("/exit")) {
                System.out.println("Bye bye)");
                break;
            }

            System.out.println("socket" + clientSocket.getPort() + " says: " + messageFromClient);

            System.out.println("Response");
            messageFromServer = systemReader.readLine();

            writer.write(messageFromServer + "\n");
            writer.flush();
        }
        writer.close();
        clientSocket.close();
        reader.close();
        writer.close();
    }
}
