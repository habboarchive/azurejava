package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class EnableTradingMessageComposer {

    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.EnableTradingMessageComposer);
        msg.writeBoolean(true);

        return msg;
    }
}
