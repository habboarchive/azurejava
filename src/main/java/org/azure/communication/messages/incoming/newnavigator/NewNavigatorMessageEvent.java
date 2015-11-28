package org.azure.communication.messages.incoming.newnavigator;

import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.newnavigator.NavigatorCategorys;
import org.azure.communication.messages.outgoing.newnavigator.NavigatorLiftedRoomsComposer;
import org.azure.communication.messages.outgoing.newnavigator.NavigatorMetaDataComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class NewNavigatorMessageEvent {
    @MessageEvent(messageId = 1121)
    public static void eventHandler(Session session, ClientMessage message) {
        session.sendQueued(NavigatorMetaDataComposer.compose());
        session.sendQueued(NavigatorLiftedRoomsComposer.compose());
        session.sendQueued(NavigatorCategorys.compose());

        session.getChannel().flush();
    }
}