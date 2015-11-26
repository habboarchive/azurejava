package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class HomeRoomMessageComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.HomeRoomMessageComposer);
        msg.writeInt(1); // Home Room ID
        msg.writeInt(0); // Home Room ID

        return msg;
    }
}
