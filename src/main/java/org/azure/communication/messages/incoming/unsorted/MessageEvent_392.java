package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class MessageEvent_392 {
    // I have no fucking idea what this packet is for. :)

    @MessageEvent(messageId = 392)
    public static void eventHandler(Session session, ClientMessage message) {
        session.sendHotelAlert("AzureJAVA", "We're making progress! ... and servers.");
    }
}