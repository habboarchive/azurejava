package org.azure.communication.messages.outgoing.handshake;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class UserClubRightsMessageComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.UserClubRightsMessageComposer);
        msg.writeInt(2); // 0 - no club, 2 - club
        msg.writeInt(7); // rank
        msg.writeInt(0); // ?

        return msg;
    }
}
