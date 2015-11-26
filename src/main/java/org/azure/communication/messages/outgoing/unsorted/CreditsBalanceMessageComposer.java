package org.azure.communication.messages.outgoing.unsorted;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class CreditsBalanceMessageComposer {
    public static ServerMessage compose(int credits) {
        ServerMessage msg = new ServerMessage(EServerMessage.CreditsBalanceMessageComposer);
        msg.writeString(credits + ".0");

        return msg;
    }
}
