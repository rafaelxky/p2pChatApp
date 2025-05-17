package io.sourceWare.program.client.model.connections.client_server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Server server;
    public boolean isRunning = true;
    public final String serverTextColor = "\u001B[33m";
    public Integer[] rgb = {null,null, null};
    public String ansiColorCode = "\u001B[38;2;" + rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m";
    public final String RESET = "\u001B[0m";
    private String name;

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
            setNameByInput();
            clientLoop();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     *Prompts user for name
     *  */
    private void setNameByInput() throws IOException {
        serverWrite(clientSocket, "Insert your name:");
        setName(listen(clientSocket));
        serverWrite(clientSocket, "Hello " + getName());
        server.usersMap.put(name, this);
    }

    /*
     * starts client loop
     *listens for user input, sends that.
     *
     */

    public void clientLoop() {
        // receives data from the user and broadcasts it
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
                    System.out.println("Client " + getName() + " disconnected abruptly.");
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
     * clientSocket specifies which user
     */
    public void write(Socket clientSocket, String out) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(getName() + ": " + out);
    }

    // todo: make this work
    public void whisper(Socket clientSocket, String out) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(getName() + " whispered to you: " + out);
    }

    public void serverWrite(Socket clientSocket, String out, String colorCode) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(colorCode + out + RESET);
    }

    public void serverWrite(Socket clientSocket, String out) throws IOException {
        serverWrite(clientSocket, out, serverTextColor);
    }

    public void serverWrite(String out) throws IOException {
        serverWrite(clientSocket, out, serverTextColor);
    }

    // send a message to yourself
    public void selfWrite(String out) throws IOException {
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        printWriter.println(out);
    }


    /*
     * sends the message data to all the other users from the user
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

    // broadcasts message from the server
    public void serverBroadCast(String data){
        for (Socket clientSocket : server.getSocketList()) {
            try {
                serverWrite(clientSocket, data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // broadcasts message from user to every other user and also itself
    public void broadCastPlusSelf(String data) throws IOException {
        broadCast(data);
        selfWrite(data);
    }

    public String getName() {
        if (rgb[0] == null || rgb[1] == null || rgb[2] == null){
            return name;
        }

        return ansiColorCode + name + RESET;
    }

    public void setName(String name) {
        this.name = name;
    }

    // check for all commands and executes their logic
    public boolean isCommand(String in) {
        // any whitespace character
        String[] split = in.split("\\s+");
        // if not a command
        if (!in.startsWith("/")){
            return false;
        }

            try {
                if (split[0].equals("/nmclr")){
                    if (in.equals("/nmclr")){
                        serverWrite(ansiColorCode + "r:" + rgb[0] + " g:" + rgb[1] + " b:" + rgb[2] + RESET);
                        return true;
                    }
                    if (split.length < 4){
                        serverWrite("Not enough arguments");
                        serverWrite("Usage: /nmclr r g b");
                        return true;
                    }
                    // set rgb
                    rgb = new Integer[]{Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])};
                    // set ansiColorCode
                    ansiColorCode = "\u001B[38;2;" + rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m";

                    serverWrite("Color set to " + ansiColorCode + " r:" + rgb[0] + " g:" + rgb[1] + " b:" + rgb[2] + RESET);
                    return true;
                }
                if (split[0].equals("/whoami")){
                    serverWrite(getName());
                    return true;
                }
                if (split[0].equals("/flip")){
                    serverBroadCast("flip: " + ((int)(Math.random() * 2) == 0 ? "Heads" : "Tails"));
                    return true;
                }
                if (split[0].equals("/roll")){
                    serverBroadCast("roll: " + String.valueOf((int)Math.ceil(Math.random() * 6)));
                    return true;
                }
                if (split[0].equals("/help")){
                    serverWrite("help: /help, /exit, /shrug, /lenny, /tableflip, /nmclr, /whoami, /flip, /roll, /whisper");
                    return true;
                }
                // todo: implement
                if (split[0].equals("/whisper")){

                }
                // todo: implement
                if (split[0].equals("/exit")){
                    return true;
                }
                if (split[0].equals("/shrug")){
                    // type chcp 65001 in terminal for it to work
                    broadCastPlusSelf("¯\\_(ツ)_/¯");
                    return true;
                }
                if (split[0].equals("/lenny")){
                    broadCastPlusSelf("( ͡° ͜ʖ ͡°)");
                    return true;
                }

                if (split[0].equals("/tableflip")){
                    String msg = "(╯°□°）╯︵ ┻━┻";
                    broadCastPlusSelf(msg);
                    return true;
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
