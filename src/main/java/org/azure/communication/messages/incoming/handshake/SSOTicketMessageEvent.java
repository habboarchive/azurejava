package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.handshake.*;
import org.azure.communication.messages.outgoing.unsorted.*;
import org.azure.communication.protocol.ClientMessage;
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

        session.sendMessage(UniqueMachineIDComposer.compose(session.getUniqueID()));
        session.sendMessage(AuthenticationOKMessageComposer.compose());
//        session.sendMessage(HomeRoomMessageComposer.compose());
//        session.sendMessage(MinimailCountMessageComposer.compose());
//        session.sendMessage(FavouriteRoomsMessageComposer.compose());
//        session.sendMessage(UserClubRightsMessageComposer.compose());
//        session.sendMessage(EnableNotificationsMessageComposer.compose());
//        session.sendMessage(EnableTradingMessageComposer.compose());
//        session.sendMessage(ActivityPointsMessageComposer.compose());

//        session.sendMessage(UserObjectMessageComposer.compose());
//        session.sendMessage(BuildersClubMembershipMessageComposer.compose());
//        session.sendMessage(SendPerkAllowancesMessageComposer.compose());
        //session.sendMessage();
    }
}
