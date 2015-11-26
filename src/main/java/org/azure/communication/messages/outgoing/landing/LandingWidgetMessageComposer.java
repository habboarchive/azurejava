package org.azure.communication.messages.outgoing.landing;

import org.azure.communication.messages.EServerMessage;
import org.azure.communication.protocol.ServerMessage;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class LandingWidgetMessageComposer {
    public static ServerMessage compose(String widget) {
        ServerMessage msg = new ServerMessage(EServerMessage.LandingWidgetMessageComposer);
        if (widget.isEmpty()) {
            msg.writeString("");
            msg.writeString("");
        }

        return msg;
    }
}