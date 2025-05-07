package io.sourceWare.Tests;

import io.sourceWare.program.client.model.connections.ConnectionData;
import io.sourceWare.program.client.model.connections.udp.ReceiveUdpPacket;
import io.sourceWare.program.client.model.connections.udp.SendUdpPacket;

public class UdpConnectionTest {
    public static void main(String[] args) {
        start();
    }

    public static void start(){
        System.out.println("Send and receive package test: " + sendAndReceiveUdpPackagesTest());
        System.out.println("Message received: " + ConnectionData.getMessage() + (ConnectionData.getMessage().equals("Test message") ? " ✅" : " ❌"));
    }

    public static void udpSendTest(){
        System.out.println("Trying to send UDP packet ⏳");
        Thread sendPacket = new Thread(new SendUdpPacket("Test message"));
        sendPacket.start();
        System.out.println("Finished sending UPD data ⏳");
    }

    public static String udpReceiveTest(){
        System.out.println("Trying to receive UDP packet ⏳");
        Thread receivePacket = new Thread(new ReceiveUdpPacket());
        receivePacket.start();
        udpSendTest();

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String out = ConnectionData.getMessage();

        if (out.equals("default")){
            System.out.println("Received no UDP data ❌");
            return "failed ❌";
        }

        System.out.println("UDP receive test passed ✅");
        return "Test message";
    }

    public static String sendAndReceiveUdpPackagesTest(){
        System.out.println("Starting UDP send and receive test ⏳");
        String msg = udpReceiveTest();

            if (ConnectionData.getMessage().equals(msg)){
                return "passed ✅";
            }
        return "failed ❌";
    }

}
