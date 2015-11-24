package org.azure.communication.messages.incoming.handshake;

import org.azure.communication.messages.IMessageEvent;
import org.azure.communication.messages.MessageDataWrapper;
import org.azure.network.sessions.Session;

public class VersionCheckMessageEvent implements IMessageEvent {
    public void parse(Session session, MessageDataWrapper message) {

    }
}
