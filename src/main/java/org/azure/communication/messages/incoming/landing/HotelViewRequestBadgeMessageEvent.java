package org.azure.communication.messages.incoming.landing;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class HotelViewRequestBadgeMessageEvent {
    @MessageEvent(messageId = EClientMessage.HotelViewRequestBadgeMessageEvent)
    public static void eventHandler(Session session, ClientMessage message) {
        // TODO: Implementation for org.azure.communication.messages.incoming.landing.HotelViewRequestBadgeMessageEvent
    }
}