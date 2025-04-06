package io.sourceWare.program.client.model.connections.udp;
import io.sourceWare.program.client.model.connections.ConnectionData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SendUdpPacket implements Runnable{
    String localAddress = ConnectionData.localAddress;
    Integer localPort = ConnectionData.localPort;
    String foreignAddress = ConnectionData.foreignAddress;
    Integer foreignPort = ConnectionData.foreignPort;
    DatagramSocket socket = null;
    String message = "Hello World";

    public SendUdpPacket(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        createDatagramSocket();
        sendUdpPackage();
    }

    public void sendUdpPackage(){
        try {

            byte[] sendData = message.getBytes();
            InetAddress serverIP = InetAddress.getByName(foreignAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, foreignPort);
            socket.send(sendPacket);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDatagramSocket() {
        try {

            socket = new DatagramSocket();


        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }


}
