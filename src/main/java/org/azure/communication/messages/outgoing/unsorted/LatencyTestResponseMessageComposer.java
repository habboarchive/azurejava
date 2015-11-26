package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class LatencyTestResponseMessageComposer {
    public static ServerMessage compose(int count) {
        ServerMessage msg = new ServerMessage(EServerMessage.LatencyTestResponseMessageComposer);
        msg.writeInt(count);

        return msg;
    }
}