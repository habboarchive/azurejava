package org.azure.communication.messages.incoming.newnavigator;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/27/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class SearchNewNavigatorEvent {
    @MessageEvent(messageId = EClientMessage.SearchNewNavigatorEvent)
    public static void eventHandler(Session session, ClientMessage message) {
        String name = message.readString();
        String junk = message.readString();

//        session.sendMessage(SearchResultSetComposer.compose(name, junk));
    }
}