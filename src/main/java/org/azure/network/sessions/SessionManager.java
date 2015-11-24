package org.azure.network.sessions;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SessionManager {
    private ConcurrentMap<Integer, Session> sessions = new ConcurrentHashMap<Integer, Session>();

    public Session create(Channel ch) {
        Session session = new Session(ch);
        if (ch.isOpen()) {
            sessions.put(sessions.size() + 1, session);
            return session;
        }
        return null;
    }
}
