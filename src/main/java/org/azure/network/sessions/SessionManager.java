package org.azure.network.sessions;

import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.communication.protocol.ServerMessage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SessionManager {
    private static final Logger logger = LogManager.getLogger(SessionManager.class);

    private ConcurrentMap<Channel, Session> sessions = new ConcurrentHashMap<Channel, Session>();
    private int sessionCount = 0;

    public void addSession(Channel ch) {
        Session session = new Session(++sessionCount, ch);
        if (!sessions.containsKey(ch)) {
            logger.info("<Session " + session.getId() + "> has connected to the server.");
            sessions.put(ch, session);
            return;
        }
        logger.debug("Duplicate connection from user: " + ch.remoteAddress());
        ch.disconnect();
    }

    public void removeSession(Channel ch) {
        Session session = sessions.get(ch);
        logger.info("<Session " + session.getId() + "> has disconnected from the server.");
        sessions.remove(ch);
    }

    public Session getSessionByChannel(Channel ch) {
        if (!sessions.containsKey(ch)) {
            return null;
        }
        return sessions.get(ch);
    }

    public void broadcast(ServerMessage message) {
        sessions.forEach((k,v) -> v.sendMessage(message));
    }

    public int getSessionCount() {
        return this.sessionCount;
    }
}
