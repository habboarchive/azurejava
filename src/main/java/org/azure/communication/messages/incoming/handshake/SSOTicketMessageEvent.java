package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.handshake.BuildersClubMembershipMessageComposer;
import org.azure.communication.messages.outgoing.handshake.SendPerkAllowancesMessageComposer;
import org.azure.communication.messages.outgoing.handshake.UserObjectMessageComposer;
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

        session.sendMessage(UserObjectMessageComposer.compose());
        session.sendMessage(BuildersClubMembershipMessageComposer.compose());
        session.sendMessage(SendPerkAllowancesMessageComposer.compose());
        //session.sendMessage();
    }
}
