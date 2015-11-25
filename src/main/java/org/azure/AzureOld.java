/*package org.azure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.communication.messages.MessageClassManager;
import org.azure.communication.messages.MessageHandler;
import org.azure.network.Server;
import org.azure.network.sessions.SessionManager;
import com.google.inject.AbstractModule;

public class Azure extends AbstractModule {
    private static final Logger logger = LogManager.getLogger(Azure.class);

    private static Server server = null;
    private static MessageClassManager messageClassManager = null;
    private static SessionManager sessionManager = null;

    @Override
    protected void configure() {
        bind(MessageHandler.class).asEagerSingleton();
    }

    public static void main(String[] args) throws Exception {
        messageClassManager = new MessageClassManager();
        messageClassManager.registerHandshakeMessages();

        sessionManager = new SessionManager();

        server = new Server(3000);
        server.run();
    }

    public static Server getServer() {
        return server;
    }

    public static MessageClassManager getMessageClassManager() {
        return messageClassManager;
    }

    public static SessionManager getSessionManager() {
        return sessionManager;
    }
}*/