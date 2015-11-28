package org.azure.communication.messages.incoming.newnavigator;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.incoming.unsorted.FlatCategoriesMessageComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class NavigatorGetFlatCategoriesMessageEvent {
    @MessageEvent(messageId = EClientMessage.NavigatorGetFlatCategoriesMessageEvent)
    public static void eventHandler(Session session, ClientMessage message) {
        session.sendMessage(FlatCategoriesMessageComposer.compose());
    }
}