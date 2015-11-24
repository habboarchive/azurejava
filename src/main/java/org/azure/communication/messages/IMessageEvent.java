package org.azure.communication.messages;

import org.azure.network.sessions.Session;

public interface IMessageEvent {
    void parse(Session session, MessageDataWrapper message);
}
