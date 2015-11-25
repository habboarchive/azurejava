package org.azure.communication.messages.outgoing.handshake;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

public class InitDiffieHandshakeComposer extends ServerMessage {
    public InitDiffieHandshakeComposer() {
        super(EServerMessage.InitDiffieHandshakeComposer);
        super.writeString("Azure");
        super.writeString("Disabled Crypto");
    }
}
