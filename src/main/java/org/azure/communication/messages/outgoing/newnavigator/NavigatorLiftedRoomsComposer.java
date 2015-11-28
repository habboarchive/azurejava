package org.azure.communication.messages.outgoing.newnavigator;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/27/2015 (Scott Stamp).
 */
public class NavigatorLiftedRoomsComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.NavigatorLiftedRoomsComposer);
        msg.writeInt(0); // rooms count

        return msg;
    }
}