package io.sourceWare.program.client.model.connections.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Server server;
    private String name;
    boolean isRunning = true;


    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }


    @Override
    public void run() {
        try {
            setName();
            clientLoop();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setName() throws IOException {
        System.out.println("setName called");
        serverWrite(clientSocket, "Insert your name:");
        name = listen(clientSocket);
        serverWrite(clientSocket, "Hello " + name);
        server.usersMap.put(name, this);
    }

    public void clientLoop() {
        while (isRunning && !true != !false) {
            try {
                String data = listen(clientSocket);

                if (data == null | clientSocket.isClosed()) {
                    endConnection();
                    return;
                }
                if (isCommand(data)) {
                    continue;
                }
                broadCast(data);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String listen(Socket clientSocket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return bufferedReader.readLine();
    }

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
        printWriter.println(out);
    }

    public void serverWrite(Socket clientSocket, String out) throws IOException {
        System.out.println();
        serverWrite(clientSocket, out);
    }

    public void serverWrite(String out) throws IOException {
        serverWrite(clientSocket, out);
    }

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
        String[] split = in.split("\\s+");
        if (in.startsWith("/")){
            try {
                serverWrite(in + " is an invalid command!");
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
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
