package io.sourceWare.program.client.model.connections.client_server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/*
* Reads user input and sends to the server
* */
public class ClientWrite implements Runnable{
    public BufferedReader bufferedReader;
    public Socket clientSocket;
    public PrintWriter out;
    public String foreignAddress;

    public ClientWrite(BufferedReader bufferedReader, Socket clientSocket, String foreignAddress){
        System.out.println("client write");
        this.bufferedReader = bufferedReader;
        this.clientSocket = clientSocket;
        // this is the id for the other user, maybe don't use the real ip but another identifier
        this.foreignAddress = foreignAddress;

    }

    public void startListening() {
        try {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        String line;
        while (true){
                // read line from user
                line = getUserInput();
                // send data to server
                out.println(line);
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        startListening();
    }

    public String getUserInput() throws IOException {
        // method to be switched out later to get data from the view layer.
        String userInput = bufferedReader.readLine();
        return userInput;
    }

}