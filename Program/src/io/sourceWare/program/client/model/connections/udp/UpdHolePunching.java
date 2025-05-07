package io.sourceWare.program.client.model.connections.udp;

import io.sourceWare.program.client.model.connections.ConnectionData;
import io.sourceWare.program.client.view.consoleview.ConsoleInputHandler;
import io.sourceWare.program.interfaces.Input;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdHolePunching {
    public static String message = "Hello";
    public static ExecutorService threadPool = Executors.newFixedThreadPool(4);
    public static Thread send = new Thread(new SendUdpPacket(message));
    public static Thread receive = new Thread(new ReceiveUdpPacket());


    public static void startUdpPunching(){
        // attempts to punch a UDP hole by sending data every X seconds and listening for connections

        // listen for connections(done in main)
        // loop, while doesn't receive response, send UPD request every X seconds
        // if stop typed, stop sending
        // if receive data, stop sending

        receive.start();

        // while true
        while (true){
            // send default message
            send.start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // check if received udp
            checkUdpPacket();
        }
    }

    public static void keepUdpHoleAlive(){
        System.out.println("Connected");
        // send keep alive message every 15 sec
        // sout if disconnected
        // sout when connected
    }

    public static void checkUdpPacket(){
        // if received correct packet
        // check for correct ip
        // call keepUdpHoleAlive
        if (!ConnectionData.getMessage().equals("default")){
            keepUdpHoleAlive();
        }
    }
}
