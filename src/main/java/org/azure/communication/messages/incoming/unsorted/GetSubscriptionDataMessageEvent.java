package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.communication.protocol.ServerMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class GetSubscriptionDataMessageEvent {
    @MessageEvent(messageId = 3785)
    public static void getSubscriptionData(Session session, ClientMessage message) {
        if (message.readString().equals("habbo_club")) {
            // SubscriptionStatusMessageComposer - 2645
            session.sendMessage(SubscriptionStatusMessageComposer.compose());
        }
    }
}
