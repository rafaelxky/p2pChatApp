package io.sourceWare.program.client.model.connections;

import java.net.*;
import java.util.concurrent.BlockingDeque;

public class ConnectionData {
    public static DatagramSocket datagramSocket;

    static {
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static String localAddress;
    public static Integer localPort = datagramSocket.getLocalPort();
    public static String foreignAddress = "127.0.0.1";
    public static Integer foreignPort = 9001;
    public static volatile String message = "default";

    static {
        try {
            localAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }

    public static void setDatagramSocket(DatagramSocket datagramSocket) {
        ConnectionData.datagramSocket = datagramSocket;
    }

    public static void createNewDatagramSocket(){
        try {
            ConnectionData.datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLocalAddress() {
        return localAddress;
    }

    public static void setLocalAddress(String localAddress) {
        ConnectionData.localAddress = localAddress;
    }

    public static Integer getLocalPort() {
        return localPort;
    }

    public static void setLocalPort(Integer localPort) {
        ConnectionData.localPort = localPort;
    }

    public static String getForeignAddress() {
        return foreignAddress;
    }

    public static void setForeignAddress(String foreignAddress) {
        ConnectionData.foreignAddress = foreignAddress;
    }

    public static Integer getForeignPort() {
        return foreignPort;
    }

    public static void setForeignPort(Integer foreignPort) {
        ConnectionData.foreignPort = foreignPort;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        ConnectionData.message = message;
    }

    public static void setForeignCredentials(String address, Integer port){
        foreignAddress = address;
        foreignPort = port;
    }

}
