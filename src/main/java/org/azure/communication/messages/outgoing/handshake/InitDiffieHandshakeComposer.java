package org.azure.communication.messages.outgoing.handshake;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

public class InitDiffieHandshakeComposer {
    public static ServerMessage compose(String prime, String generator) {
        ServerMessage msg = new ServerMessage(EServerMessage.InitDiffieHandshakeComposer);
        msg.writeString(prime);
        msg.writeString(generator);
        return msg;
    }
}