package org.azure.communication.messages.outgoing.handshake;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class BuildersClubMembershipMessageComposer {
    public static ServerMessage compose() {
        ServerMessage msg = new ServerMessage(EServerMessage.BuildersClubMembershipMessageComposer);
        msg.writeInt(1); // BuildersExpire
        msg.writeInt(1000); // BuildersItemsMax
        msg.writeInt(2); // ?

        return msg;
    }
}
