package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.handshake.InitDiffieHandshakeComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.NetworkBootstrap;
import org.azure.network.sessions.Session;
import org.azure.communication.encryption.DiffieHellman;

public class InitDiffieHandshakeMessageEvent {
    @MessageEvent(messageId = EClientMessage.InitDiffieHandshakeMessageEvent)
    public static void initCrypto(Session session, ClientMessage message) {
        DiffieHellman dh = session.getDiffieHellman();
        String P = dh.Prime.toString();
        String G = dh.Generator.toString();
        String encP = NetworkBootstrap.getRSA().Sign(P);
        String encG = NetworkBootstrap.getRSA().Sign(G);

        session.sendMessage(InitDiffieHandshakeComposer.compose(encP, encG));
    }
}
