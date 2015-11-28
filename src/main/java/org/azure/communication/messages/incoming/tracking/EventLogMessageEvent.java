package org.azure.communication.messages.incoming.tracking;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/28/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class EventLogMessageEvent {
    @MessageEvent(messageId = EClientMessage.EventLogMessageEvent)
    public static void eventHandler(Session session, ClientMessage message) {
        // TODO: do something with this
        String eventType = message.readString();
        String eventSubtype = message.readString();
        String eventDescriptor = message.readString();
        String eventData = message.readString();

        switch (eventType) {
            case "NewNavigator":
                // New Navigator event
                switch (eventSubtype) {
                    case "Search":
                        // user searched for something.
                        break;
                }

                break;
        }
    }
}