package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.EClientMessage;
import org.azure.communication.messages.MessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

@SuppressWarnings("unused")
public class VersionCheckMessageEvent {
    @MessageEvent(messageId = EClientMessage.VersionCheckMessageEvent)
    public static void versionCheck(Session session, ClientMessage message) {
        System.out.println(message.readString());
    }
}
