package org.azure.communication.messages.outgoing.handshake;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/25/2015 (Scott Stamp).
 */
public class SecretKeyMessageComposer {
    public static ServerMessage compose(String publicKey, boolean clientSide) {
        ServerMessage message = new ServerMessage(EServerMessage.SecretKeyMessageComposer);
        message.writeString(publicKey);
        message.writeBoolean(clientSide);
        return message;
    }
}