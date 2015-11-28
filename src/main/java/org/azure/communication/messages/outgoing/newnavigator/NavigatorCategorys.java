package org.azure.communication.messages.outgoing.newnavigator;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/27/2015 (Scott Stamp).
 */
public class NavigatorCategorys {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.NavigatorCategorys);
        msg.writeInt(4); // number of categories (plus four defaults)

        // default categories
        msg.writeString("recommended");
        msg.writeString("new_ads");
        msg.writeString("staffpicks");
        msg.writeString("official");

        return msg;
    }
}