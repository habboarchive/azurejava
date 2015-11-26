package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class SubscriptionStatusMessageComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.SubscriptionStatusMessageComposer);
        msg.writeString("club_habbo");
        msg.writeInt(0); // Days
        msg.writeInt(1); // ?
        msg.writeInt(3); // Months
        msg.writeInt(1);
        msg.writeBoolean(true);
        msg.writeBoolean(true);
        msg.writeInt(10); // no idea
        msg.writeInt(10); // no idea
        msg.writeInt(10);

        return msg;
    }
}