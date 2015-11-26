package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class OnlineConfirmationMessageEvent {
    @MessageEvent(messageId = 523)
    public static void eventHandler(Session session, ClientMessage message) {

    }
}