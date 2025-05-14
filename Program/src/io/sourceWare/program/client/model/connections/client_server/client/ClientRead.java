package io.sourceWare.program.client.model.connections.client_server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/*
*
* Receives data from the server
* */
public class ClientRead implements Runnable{

    public Socket clientSocket;

    public ClientRead(Socket clientSocket){
        System.out.println("client read");
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
       startListening();
    }

    public void startListening(){
        // listen to server on port 9005
        listeningLoop();
    }

    public void listeningLoop(){
        while (true){
            try (BufferedReader serverReader = new BufferedReader(new java.io.InputStreamReader(clientSocket.getInputStream()))){
                String message = serverReader.readLine();
                System.out.println(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
