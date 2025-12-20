package org.example;

import java.net.ServerSocket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    private static final List<BufferedWriter> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8098);

        System.out.println("Server is ready");

        while (true) {
            Socket socket = serverSocket.accept();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            clients.add(writer);

            Thread newThread =  new Thread(() -> {
                try {
                    handle(socket, writer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            newThread.start();
        }
    }

    private static void handle(Socket socket, BufferedWriter writer) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String message;

        while ((message = reader.readLine()) != null) {
            if (message.equals("/exit")) {
                break;
            }

            System.out.println("[" + socket.getPort() + "] " + message + "\n"); //1984

            for (BufferedWriter writerClient : clients) {
                writerClient.write("[" + socket.getPort() + "] " + message + "\n");
                writerClient.flush();
            }
        }

        clients.remove(writer);
        writer.close();
        socket.close();
    }
}
