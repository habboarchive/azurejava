package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.unsorted.CitizenshipStatusMessageComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.communication.protocol.ServerMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class RetrieveCitizenshipStatus {
    @MessageEvent(messageId = 644)
    public static void eventHandler(Session session, ClientMessage message) {
        // CitizenshipStatusMessageComposer - 265
        session.sendMessage(CitizenshipStatusMessageComposer.compose());
    }
}