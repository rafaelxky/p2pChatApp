package io.sourceWare.Tests;

import io.sourceWare.program.client.model.connections.client_server.Server;

public class ClientServerTests {
    public static void main(String[] args) {
        // ncat 172.31.48.1 9005
        Server server = new Server();
        Thread thread1 = new Thread(server);
        thread1.start();
        // todo: fix connection reset error
        // todo: crete a client (currently there is only server, (Server, ClientHandler))
        // todo: the client can type text and receive from the server
        // todo: login
        // todo: session
        // todo: implement encryption, signing, decription and nodes (later)


    }
}
