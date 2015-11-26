package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class EnableNotificationsMessageComposer {

    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.EnableNotificationsMessageComposer);
        msg.writeBoolean(true); // is open
        msg.writeBoolean(false); // ?

        return msg;
    }
}
