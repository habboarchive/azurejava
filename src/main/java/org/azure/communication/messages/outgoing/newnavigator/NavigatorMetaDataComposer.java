package org.azure.communication.messages.outgoing.newnavigator;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/27/2015 (Scott Stamp).
 */
public class NavigatorMetaDataComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.NavigatorMetaDataComposer);
        msg.writeInt(4);
        msg.writeString("official_view");
        msg.writeInt(0);
        msg.writeString("hotel_view");
        msg.writeInt(0);
        msg.writeString("roomads_view");
        msg.writeInt(0);
        msg.writeString("myworld_view");
        msg.writeInt(0);

        return msg;
    }
}