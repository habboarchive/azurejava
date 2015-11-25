package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.handshake.InitDiffieHandshakeComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

public class InitDiffieHandshakeMessageEvent {
    @MessageEvent(messageId = EClientMessage.InitDiffieHandshakeMessageEvent)
    public static void initCrypto(Session session, ClientMessage message) {
        session.sendPacket(new InitDiffieHandshakeComposer());
    }
}
