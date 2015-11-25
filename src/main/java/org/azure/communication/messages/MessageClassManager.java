package org.azure.communication.messages;

import gnu.trove.map.hash.THashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.communication.messages.incoming.handshake.VersionCheckMessageEvent;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

public class MessageClassManager {
    private static final Logger logger = LogManager.getLogger(MessageClassManager.class);

    private final THashMap<Short, IMessageEvent> messages = new THashMap<Short, IMessageEvent>();

    public void registerHandshakeMessages() {
        //this.messages.put(OperationCodes.Incoming.VersionCheckMessageEvent, new VersionCheckMessageEvent());
    }

    public void execute(Session session, MessageDataWrapper message) {
        ClientMessage msg = new ClientMessage(message.getHeader(), message.getData());
        MessageHandler.invoke(session, msg);

        /*IMessageEvent event = messages.containsKey(message.getHeader()) ? messages.get(message.getHeader()) : null;
        if (event == null) {
            logger.debug("Unhandled Packet: " + message.getHeader());
            return;
        }
        logger.debug("<Session " + session.getId() + "> Executing Packet: " + message.getHeader());
        System.out.println("Header: " + message.getHeader() + " Length: " + message.getLength() + " Body: " + message.getBody());*/
    }

    public void dispose() {
        this.messages.clear();
    }
}
