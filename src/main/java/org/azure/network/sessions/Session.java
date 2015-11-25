package org.azure.network.sessions;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.communication.protocol.ServerMessage;

public class Session {
    private static final Logger logger = LogManager.getLogger(Session.class);

    private final int id;
    private final Channel channel;

    public Session(int id, Channel ch) {
        this.id = id;
        this.channel = ch;
    }

    public int getId() {
        return this.id;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public void sendPacket(ServerMessage message) {
        this.sendRaw(message.getBuffer());
    }

    private void sendRaw(ByteBuf data) {
        ChannelFuture cf = channel.writeAndFlush(data);
        if (!cf.isSuccess()) {
            logger.error("Error", cf.cause());
        }
    }
}
