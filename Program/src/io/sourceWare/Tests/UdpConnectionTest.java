package io.sourceWare.Tests;

import io.sourceWare.program.client.model.connections.ConnectionData;
import io.sourceWare.program.client.model.connections.p2p.ReceiveUdpPacket;
import io.sourceWare.program.client.model.connections.p2p.SendUdpPacket;

import java.net.Socket;

public class UdpConnectionTest {
    public static void main(String[] args) {
        System.out.println("Send and receive package test: " + sendAndReceiveUdpPackagesTest());
    }
    public static void udpSendTest(){
        System.out.println("Trying to send UDP packet.");
        Thread sendPacket = new Thread(new SendUdpPacket("Test message"));
        sendPacket.start();
    }

    public static String udpReceiveTest(){
        String msg = "Trying to receive UDP packet.";
        System.out.println(msg);
        Thread receivePacket = new Thread(new ReceiveUdpPacket());
        receivePacket.start();
        if (ConnectionData.getMessage().equals(msg)){
            return "passed :)";
        }
        return "failed :(";
    }

    public static String sendAndReceiveUdpPackagesTest(){
        System.out.println("Starting UDP send and receive test.");
        udpSendTest();
        return udpReceiveTest();
    }

}
