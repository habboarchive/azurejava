package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.unsorted.SubscriptionStatusMessageComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class GetSubscriptionDataMessageEvent {
    @MessageEvent(messageId = EClientMessage.GetSubscriptionDataMessageEvent)
    public static void getSubscriptionData(Session session, ClientMessage message) {
        if (message.readString().equals("habbo_club")) {
            // SubscriptionStatusMessageComposer - 2645
            session.sendMessage(SubscriptionStatusMessageComposer.compose());
        }
    }
}
