package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.unsorted.LatencyTestResponseMessageComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class RequestLatencyTestMessageEvent {
    @MessageEvent(messageId = EClientMessage.RequestLatencyTestMessageEvent)
    public static void eventHandler(Session session, ClientMessage message) {
        try {
            int count = message.readInt();
            session.sendMessage(LatencyTestResponseMessageComposer.compose(count));
        } catch (Exception ex) {
            // discard
        }
    }
}