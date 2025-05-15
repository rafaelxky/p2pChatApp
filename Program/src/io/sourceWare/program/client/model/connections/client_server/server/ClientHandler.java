package io.sourceWare.program.client.model.connections.client_server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Server server;
    private String name;
    public boolean isRunning = true;
    public String serverTextColor = "\u001B[33m";
    public Integer[] rgb = {255, 255, 255};
    public String ansiColorCode = "\u001B[38;2;" + rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m";
    public final String RESET = "\u001B[0m";

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    /*
     * Override from runnable
     * Sets the client name and starts the clientLoop
     *  */
    @Override
    public void run() {
        try {
            setName();
            clientLoop();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     *Prompts user for name
     *  */
    private void setName() throws IOException {
        serverWrite(clientSocket, "Insert your name:");
        name = listen(clientSocket);
        serverWrite(clientSocket, "Hello " + ansiColorCode +  name + RESET);
        server.usersMap.put(name, this);
    }


    /*
     * starts client loop
     *listens for user input, sends that.
     *
     */

    public void clientLoop() {
        System.out.println("client loop");
        while (isRunning && !true != !false) {
            try {
                // user message input here
                String data = listen(clientSocket);

                if (isCommand(data)) {
                    continue;
                }
                // data is sent to users here
                broadCast(data);

            } catch (IOException e) {
                if (e.getMessage() != null && e.getMessage().toLowerCase().contains("connection reset")) {
                    System.out.println("Client " + name + " disconnected abruptly.");
                } else {
                    System.out.println("Error in clientLoop(): " + e.getMessage());
                }
                endConnection();
                return;
            }
        }
    }


    /*
     * starts a buffered reader to get user input from cmd
     */
    public String listen(Socket clientSocket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return bufferedReader.readLine();
    }

    /*
     * sends data from the server to the users
     */
    public void write(Socket clientSocket, String out) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(name + ": " + out);
    }

    public void whisper(Socket clientSocket, String out) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(name + " wispered to you: " + out);
    }

    public void serverWrite(Socket clientSocket, String out, String colorCode) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(colorCode + out + "\u001B[0m");
    }

    public void serverWrite(Socket clientSocket, String out) throws IOException {
        serverWrite(clientSocket, out, serverTextColor);
    }

    public void serverWrite(String out) throws IOException {
        serverWrite(clientSocket, out, "");
    }


    /*
     * sends the message data to all the other users
     */
    public void broadCast(String data) throws IOException {
        // broadcasts data
        for (Socket clientSocket : server.getSocketList()) {
            if (clientSocket.equals(this.clientSocket)) {
                continue;
            }
            write(clientSocket, data);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCommand(String in) {
        // any whitespace character
        String[] split = in.split("\\s+");
        // if not a command
        if (!in.startsWith("/")){
            return false;
        }

            try {
                if (split[0].equals("/nmclr")){
                    if (split.length < 4){
                        serverWrite("Not enough arguments");
                        return true;
                    }
                    rgb = new Integer[]{Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])};
                }

                serverWrite(in + " is an invalid command!");
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void endConnection() {
        System.out.println("Connection ended");
        server.usersMap.replace(name, null);

        try {
            clientSocket.close();
            isRunning = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
