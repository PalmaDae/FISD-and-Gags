package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket clientSocket = new Socket("127.0.0.1", 8098);
        BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(clientSocket.getOutputStream()));

        System.out.println("say something");

        new Thread(() -> {
            String message;
            while (true) {
                try {
                    if (!((message = reader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(message);
            }
        }).start();

        while (true) {
            String msg = systemReader.readLine();
            System.out.print("Your message: ");
            writer.write(msg + "\n");
            writer.flush();

            if (msg.equals("/exit")) {
                break;
            }
        }

        clientSocket.close();
    }
}
