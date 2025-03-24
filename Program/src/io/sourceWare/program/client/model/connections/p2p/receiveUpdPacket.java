package io.sourceWare.program.client.model.connections.p2p;

import io.sourceWare.program.client.model.connections.Vars;

import java.net.*;

public class receiveUpdPacket implements Runnable {
    String localAddress = Vars.localAddress;
    Integer localPort = Vars.localPort;
    String foreignAddress = Vars.foreignAddress;
    Integer foreignPort = Vars.foreignPort;

    @Override
    public void run() {
        try {
            
            DatagramSocket datagramSocket = new DatagramSocket(foreignPort);
            byte[] bytes = new byte[1024];
            InetAddress inetAddress = InetAddress.getByName(foreignAddress);
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, foreignPort);

        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
