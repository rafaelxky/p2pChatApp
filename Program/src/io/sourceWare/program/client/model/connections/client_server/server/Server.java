package io.sourceWare.program.client.model.connections.client_server.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
    private static final int PORT = 9005;
    private static final boolean testing = true;

    private static InetAddress HOSTNAME;

    static {
        try {
            HOSTNAME = InetAddress.getByName("127.0.0.1");
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
        System.out.println(getAllIps() + " - all ips");

        System.out.println("PORT: " + PORT);
        System.out.println("started");

        try {
            cachedPool = Executors.newCachedThreadPool();
            serverSocket = newServerSocket();
            System.out.println(HOSTNAME);
            
            System.out.println("Waiting connection");
            serverLoop();
            System.out.println("entering loop");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private ServerSocket newServerSocket() {
        try {
            if (testing)
            return new ServerSocket(PORT);
            if (!testing)
            return new ServerSocket(PORT, 0 , HOSTNAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
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
        // send a message to a specific user (message, userId)

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

    public static String getAllIps() {
        StringBuilder ipList = new StringBuilder();

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();

                // Skip down, loopback, or virtual interfaces
                if (!iface.isUp() || iface.isLoopback() || iface.isVirtual()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    // Only show IPv4 addresses
                    if (addr instanceof Inet4Address) {
                        ipList.append(iface.getDisplayName())
                                .append(" - ")
                                .append(addr.getHostAddress())
                                .append("\n");
                    }
                }
            }

        } catch (SocketException e) {
            return "Error retrieving IP addresses: " + e.getMessage();
        }

        return ipList.toString().isEmpty() ? "No active IP addresses found." : ipList.toString();
    }

}
