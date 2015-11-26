package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.handshake.UniqueMachineIDComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class UniqueIDMessageEvent {
    @MessageEvent(messageId = EClientMessage.UniqueIDMessageEvent)
    public static void uniqueId(Session session, ClientMessage message) {
        //message.readShort();
        String uniqueId = message.readString();
        session.sendMessage(UniqueMachineIDComposer.compose(uniqueId));
    }
}
