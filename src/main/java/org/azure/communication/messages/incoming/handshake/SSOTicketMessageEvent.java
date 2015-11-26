package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.handshake.*;
import org.azure.communication.messages.outgoing.unsorted.*;
import org.azure.communication.protocol.ClientMessage;
import org.azure.communication.protocol.ServerMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class SSOTicketMessageEvent {
    @MessageEvent(messageId = EClientMessage.SSOTicketMessageEvent)
    public static void tryLogin(Session session, ClientMessage message) {
        String ssoTicket = message.readString();
        // We'd do some login logic here but fuck that, I'm not implementing users just yet.

        // 3540
        session.sendQueued(UniqueMachineIDComposer.compose(session.getUniqueID()));
        // 3151
        session.sendQueued(AuthenticationOKMessageComposer.compose());
        // 2159
        session.sendQueued(HomeRoomMessageComposer.compose());
        // 2895
        session.sendQueued(MinimailCountMessageComposer.compose());
        session.getChannel().flush();

//        session.getChannel().flush();

        session.sendMessage(FavouriteRoomsMessageComposer.compose());
        session.sendMessage(UserClubRightsMessageComposer.compose());
        session.sendMessage(EnableNotificationsMessageComposer.compose());
        session.sendMessage(EnableTradingMessageComposer.compose());




        //session.sendMessage();
    }
}
