package io.sourceWare.program.client.model.connections.client_server.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable{
    // handles client logic
    public String serverIp = "127.0.0.1";
    public String clientIp = "127.0.0.1";
    public Socket clientSocket = new Socket();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Client(String serverIp) {
       serverIp = serverIp;
    }

    public Client() {
        serverIp = getHardCoddedServerIP();
    }

    @Override
    public void run() {
       start();
    }

    private String getHardCoddedServerIP() {
        return "127.0.0.1";
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
    // open socket
    // listed for data from server and print it
    // listen for user input and s
    // end it to the server

    public void start(){
        // connect to server
        try {
            // remove the thread sleep later in deployment
            Thread.sleep(1000);
            System.out.println("trying to connect to server");
            clientSocket.connect(new java.net.InetSocketAddress(serverIp,9005), 1000);
            System.out.println("Connected to server");
            System.out.println("enter stuff:");

            Thread readThread = new Thread(new ClientRead(clientSocket));
            Thread writeThread = new Thread(new ClientWrite(bufferedReader, clientSocket, clientIp));
            readThread.start();
            writeThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
