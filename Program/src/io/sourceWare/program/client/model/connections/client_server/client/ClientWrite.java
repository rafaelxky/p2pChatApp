package io.sourceWare.program.client.model.connections.client_server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/*
* Reads user input and sends to the server
* */
public class ClientWrite implements Runnable{
    BufferedReader bufferedReader;
    Socket clientSocket;
    String foreignAddress;

    public ClientWrite(BufferedReader bufferedReader, Socket clientSocket, String foreignAddress){
        System.out.println("client write");
        this.bufferedReader = bufferedReader;
        this.clientSocket = clientSocket;
        // this is the id for the other user, maybe dont use the real ip but another identifier
        this.foreignAddress = foreignAddress;

    }

    public void startListening() {
        String line;
        while (true){
            try {
                // read line from user
                line = bufferedReader.readLine();
                System.out.println(line);
                // send data to server
                clientSocket.getOutputStream().write(line.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void run() {
        startListening();
    }
}

