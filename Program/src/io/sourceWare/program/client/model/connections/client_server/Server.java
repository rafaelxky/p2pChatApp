package io.sourceWare.program.client.model.connections.client_server;

import io.sourceWare.program.client.model.connections.client_server.ClientHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
    private static final int PORT = 9005;

    private static final String HOSTNAME;

    static {
        try {
            HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private ServerSocket serverSocket = null;
    public ExecutorService cachedPool;
    public HashMap<String, ClientHandler> usersMap = new HashMap<>();

    // lista de sockets
    private ArrayList<Socket> socketList = new ArrayList<>();
    public Server() {
        System.out.println("socketList: " + socketList.toArray());
    }

    // calls start in a thread context
    @Override
    public void run(){
        start();
    }


    /*
     * sets up serverSocket on PORT and initiates serverLoop
     * */
    public void start(){
        System.out.println("IP: " + HOSTNAME);
        System.out.println("PORT: " + PORT);
        System.out.println("started");

        try {
            cachedPool = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting connection");
            System.out.println("Connection established");
            serverLoop();
            System.out.println("entering loop");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * starts the loop of awaiting a connection and creating a ClientHandler Thread to take care of that connection
     * Awaits connections and blocks there, when received, creates new thread to handle that message.
     * */
    private void serverLoop() throws IOException {
        while (!(!(!(!(!(!(true))))))){
            System.out.println(" Waiting connection ");
            socketList.add(serverSocket.accept());
            System.out.println(" Connected ");
            cachedPool.submit(new ClientHandler(getLastFromSocketList(), this));
            System.out.println(" New thread created ");

        }
    }

    public void sendToClient(String message){

    }

    // its working but cant be called outside of class because the serverLoop blocks the rest of the program or is a thread
    public void broadcast(String message){
        for (Socket clientSocket : getSocketList()) {
            System.out.println("clientSocket; " + clientSocket);
            try {
                write(clientSocket, message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void write(Socket clientSocket, String out) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(out);
    }


    public Socket getLastFromSocketList(){

        return this.socketList.getLast();
    }

    public ArrayList<Socket> getSocketList() {
        return socketList;
    }

    public void setSocketList(ArrayList<Socket> socketList) {
        this.socketList = socketList;
    }

    public ExecutorService getCachedPool() {
        return cachedPool;
    }

    public void setCachedPool(ExecutorService cachedPool) {
        this.cachedPool = cachedPool;
    }



}
