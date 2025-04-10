package io.sourceWare;

import io.sourceWare.program.client.model.connections.p2p.ReceiveUdpPacket;
import io.sourceWare.program.client.model.connections.p2p.SendUdpPacket;
import io.sourceWare.program.client.view.Popus;

import java.io.IOException;

public class PlayGround {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Thread receiveThread = new Thread(new ReceiveUdpPacket());
        //receiveThread.start();
        //System.out.println("receiving start");

        Thread.sleep(100);

        Thread sendThread = new Thread(new SendUdpPacket("Hello World!"));
        sendThread.start();
        System.out.println("send start");

    }
}
