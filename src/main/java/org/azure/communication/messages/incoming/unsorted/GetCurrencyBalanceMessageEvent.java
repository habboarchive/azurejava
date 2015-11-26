package org.azure.communication.messages.incoming.unsorted;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.messages.outgoing.unsorted.ActivityPointsMessageComposer;
import org.azure.communication.messages.outgoing.unsorted.CreditsBalanceMessageComposer;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
@SuppressWarnings("unused")
public class GetCurrencyBalanceMessageEvent {
    @MessageEvent(messageId = EClientMessage.GetCurrencyBalanceMessageEvent)
    public static void getCurrencyBalance(Session session, ClientMessage message) {
        // 713
        session.sendQueued(CreditsBalanceMessageComposer.compose(9999)); // 9999 credits

        // 74
        session.sendMessage(ActivityPointsMessageComposer.compose());
    }
}
