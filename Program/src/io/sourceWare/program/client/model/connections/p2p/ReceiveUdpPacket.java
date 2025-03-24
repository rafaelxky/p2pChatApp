package io.sourceWare.program.client.model.connections.p2p;


import io.sourceWare.program.client.model.connections.ConnectionData;
import io.sourceWare.program.client.view.Popus;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ReceiveUdpPacket implements Runnable {
    String localAddress = ConnectionData.localAddress;
    Integer localPort = ConnectionData.localPort;
    String foreignAddress = ConnectionData.foreignAddress;
    Integer foreignPort = ConnectionData.foreignPort;

    @Override
    public void run() {
        while (true) {
            receiveUdpPackage();
        }
    }

    public String receiveUdpPackage(){
        try {

            DatagramSocket datagramSocket = new DatagramSocket(foreignPort);
            byte[] bytes = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            datagramSocket.receive(datagramPacket);

            // if the address of the receiving message is not the correct address, send a warning.
            if (!datagramPacket.getAddress().equals(InetAddress.getByName(foreignAddress))){
                Popus.unknownConnection();
                datagramSocket.close();
                return "Unknown connection!";
            }

            datagramSocket.close();

            // store the data in the ConnectionData class
            ConnectionData.setMessage(new String(datagramPacket.getData(),0 ,datagramPacket.getLength() ,StandardCharsets.UTF_8));
            return ConnectionData.getMessage();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
