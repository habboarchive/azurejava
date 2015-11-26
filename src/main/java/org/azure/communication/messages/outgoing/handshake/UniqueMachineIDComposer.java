package org.azure.communication.messages.outgoing.handshake;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class UniqueMachineIDComposer {
    public static ServerMessage compose(String uniqueId) {
        ServerMessage msg = new ServerMessage(EServerMessage.UniqueMachineIDMessageComposer);
        msg.writeString(uniqueId);
        return msg;
    }
}
