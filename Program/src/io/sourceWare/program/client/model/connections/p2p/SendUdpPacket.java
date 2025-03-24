package io.sourceWare.program.client.model.connections.p2p;
import io.sourceWare.program.client.model.connections.ConnectionData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendUdpPacket implements Runnable{
    String localAddress = ConnectionData.localAddress;
    Integer localPort = ConnectionData.localPort;
    String foreignAddress = ConnectionData.foreignAddress;
    Integer foreignPort = ConnectionData.foreignPort;

    @Override
    public void run() {
            String message = "Hello World";
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();

        byte[] sendData = message.getBytes();
            InetAddress serverIP = InetAddress.getByName(foreignAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, foreignPort);
            socket.send(sendPacket);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
