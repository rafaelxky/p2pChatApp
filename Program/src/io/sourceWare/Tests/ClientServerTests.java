package io.sourceWare.Tests;

import io.sourceWare.program.client.model.connections.client_server.ServerImpl;

public class ClientServerTests {
    public static void main(String[] args) {
        // 127.0.0.1 9005
        ServerImpl server = new ServerImpl();
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
