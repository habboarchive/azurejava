package org.azure.communication.messages.incoming.landing;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.landing.LandingWidgetMessageComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class LandingLoadWidgetMessageEvent {
    @MessageEvent(messageId = EClientMessage.LandingLoadWidgetMessageEvent)
    public static void eventHandler(Session session, ClientMessage message) {
        String text = message.readString();
        session.sendMessage(LandingWidgetMessageComposer.compose(text));
    }
}