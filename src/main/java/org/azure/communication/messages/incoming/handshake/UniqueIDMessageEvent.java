package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class UniqueIDMessageEvent {
    @MessageEvent(messageId = EClientMessage.UniqueIDMessageEvent)
    public static void uniqueId(Session session, ClientMessage message) {
        session.setUniqueID(message.readString());
    }
}
