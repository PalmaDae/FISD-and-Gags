package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket clientSocket = new Socket("127.0.0.1", 8098);
        BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(clientSocket.getOutputStream()));

        System.out.println("say something");

        String message;

        while (true) {
            message = systemReader.readLine();

            writer.write(message + "\n");
            writer.flush();

            if (message.equals("/exit")) {
                break;
            }

            String serverMessage = reader.readLine();
            System.out.println("server says: " + serverMessage);
        }

        clientSocket.close();
    }
}
