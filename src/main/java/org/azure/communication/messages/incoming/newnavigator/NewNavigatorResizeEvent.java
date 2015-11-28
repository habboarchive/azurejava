package org.azure.communication.messages.incoming.newnavigator;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/28/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class NewNavigatorResizeEvent {
    @MessageEvent(messageId = EClientMessage.NewNavigatorResizeEvent)
    public static void eventHandler(Session session, ClientMessage message) {
        int x = message.readInt();
        int y = message.readInt();
        int width = message.readInt();
        int height = message.readInt();

        // TODO: do something with this data
    }
}