package io.sourceWare.Tests;

import io.sourceWare.program.client.model.connections.client_server.client.Client;
import io.sourceWare.program.client.model.connections.client_server.server.Server;

public class ClientServerTests {
    public static void main(String[] args) {
        // ncat <ip> 9005
        // the ip is shown when server starts
        Server server = new Server();
        Thread serverThread = new Thread(server);
        serverThread.start();

        Client client = new Client("127.0.0.1");
        Thread clientThread = new Thread(client);
        clientThread.start();

        // todo: login
        // todo: session
        // todo: implement nodes and node logic (i am screwed with this)


    }
}
