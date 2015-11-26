package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class GameCenterGamesListMessageComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.GameCenterGamesListMessageComposer);
        msg.writeInt(1);
        msg.writeInt(18);
        msg.writeString("elisa_habbo_stories");
        msg.writeString("000000");
        msg.writeString("ffffff");
        msg.writeString("");
        msg.writeString("");

        return msg;
    }
}
