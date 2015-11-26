package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class FavouriteRoomsMessageComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.FavouriteRoomsMessageComposer);
        msg.writeInt(1); // # of rooms
        msg.writeInt(0); // Room ID (foreach)

        return msg;
    }
}
