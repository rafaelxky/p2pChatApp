package io.sourceWare.program.client.model.connections;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionData {
    public static String localAddress;

    static {
        try {
            localAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer localPort = 9001;
    public static String foreignAddress = "127.0.0.1";
    public static Integer foreignPort = 9001;
}
