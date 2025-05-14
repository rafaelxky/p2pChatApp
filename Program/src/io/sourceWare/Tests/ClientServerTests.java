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

        // todo: fix connection reset error
        // todo: crete a client (currently there is only server, (Server, ClientHandler))
        // todo: the client can type text and receive from the server
        // todo: login
        // todo: session
        // todo: implement encryption, signing, decription and nodes (later)


    }
}
