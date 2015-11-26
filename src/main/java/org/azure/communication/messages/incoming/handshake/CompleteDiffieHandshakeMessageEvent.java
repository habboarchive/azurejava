package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.handshake.SecretKeyMessageComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.NetworkBootstrap;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/25/2015 (Scott Stamp).
 */
public class CompleteDiffieHandshakeMessageEvent {
    @MessageEvent(messageId = EClientMessage.CompleteDiffieHandshakeMessageEvent)
    public static void completeHandshake(Session session, ClientMessage message) {
        String publicKey = message.readString();

        String plaintextKey = NetworkBootstrap.getRSA().Decrypt(publicKey).replace(Character.toString((char) 0), "");

        String P = session.getDiffieHellman().PublicKey.toString();
        String encP = NetworkBootstrap.getRSA().Sign(P);
        session.sendMessage(SecretKeyMessageComposer.compose(encP, false));

        session.getDiffieHellman().GenerateSharedKey(plaintextKey);
        byte[] sharedKey = session.getDiffieHellman().SharedKey.toByteArray();
        session.enableRC4(sharedKey);
    }
}
