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

            String message;

            do {
                message = reader.readLine();
                System.out.println("socket" + clientSocket.getPort() + " says: " + message);
                System.out.println("Response it!");
                message = systemReader.readLine();
                writer.write(message + "\n");
                writer.flush();
            } while (!message.equals("/exit"));
        clientSocket.close();
        reader.close();
        writer.close();
    }
}
