package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class ActivityPointsMessageComposer {

    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.ActivityPointsMessageComposer);
        msg.writeInt(2); // ?
        msg.writeInt(0); // ?
        msg.writeInt(9999); // ActivityPoints
        msg.writeInt(5); // ?
        msg.writeInt(9999); // Diamonds

        return msg;
    }
}
