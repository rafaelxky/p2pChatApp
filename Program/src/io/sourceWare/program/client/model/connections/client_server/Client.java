package io.sourceWare.program.client.model.connections.client_server;

import java.net.Socket;

public class Client {
    // handles client logic
    public String serverIp = getServerIP();

    private String getServerIP() {
        return "127.0.0.1";
    }
}
