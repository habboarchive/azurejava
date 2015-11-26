package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.handshake.UserObjectMessageComposer;
import org.azure.communication.messages.outgoing.unsorted.ActivityPointsMessageComposer;
import org.azure.communication.messages.outgoing.unsorted.BuildersClubMembershipMessageComposer;
import org.azure.communication.messages.outgoing.unsorted.GameCenterGamesListMessageComposer;
import org.azure.communication.messages.outgoing.unsorted.SendPerkAllowancesMessageComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.communication.protocol.ServerMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class InfoRetrieveMessageEvent {
    @MessageEvent(messageId = EClientMessage.InfoRetrieveMessageEvent)
    public static void infoRetrieve(Session session, ClientMessage message) {
        ServerMessage msg;

        // 1096
        session.sendMessage(UserObjectMessageComposer.compose());

        session.sendMessage(BuildersClubMembershipMessageComposer.compose());
        session.sendMessage(SendPerkAllowancesMessageComposer.compose());

        // GameCenterGamesListMessageComposer - 3263
        session.sendMessage(GameCenterGamesListMessageComposer.compose());
    }
}
