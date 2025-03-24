package io.sourceWare.program.client.model.connections.p2p;

import io.sourceWare.program.client.model.connections.Vars;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class sendUdpPacket implements Runnable{
    String localAddress = Vars.localAddress;
    Integer localPort = Vars.localPort;
    String foreignAddress = Vars.foreignAddress;
    Integer foreignPort = Vars.foreignPort;

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
