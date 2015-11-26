package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class OnlineConfirmationMessageEvent {
    @MessageEvent(messageId = EClientMessage.OnlineConfirmationMessageEvent)
    public static void eventHandler(Session session, ClientMessage message) {
//        session.sendMessage(
//                SuperNotificationMessageComposer.compose(
//                        "staffcloud",
//                        "AzureJAVA",
//                        "We're making progress! ... and servers."
//                )
//        );
    }
}