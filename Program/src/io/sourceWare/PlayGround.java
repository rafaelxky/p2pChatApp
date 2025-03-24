package io.sourceWare;

import io.sourceWare.program.client.model.connections.p2p.UpdHolePunching;

import java.io.IOException;

public class PlayGround {
    public static void main(String[] args) throws IOException {
        UpdHolePunching n = new UpdHolePunching();
        n.sendUpdPacket();
    }
}
