package org.azure.communication.messages.outgoing.handshake;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class AuthenticationOKMessageComposer {
    public static ServerMessage compose() {
        return new ServerMessage(EServerMessage.AuthenticationOKMessageComposer);
    }
}
