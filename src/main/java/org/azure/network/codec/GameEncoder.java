package org.azure.network.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.communication.protocol.ServerMessage;

import java.util.List;

public class GameEncoder extends MessageToMessageEncoder<ServerMessage> {
    private static final Logger logger = LogManager.getLogger(GameEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, ServerMessage msg, List<Object> out) throws Exception {
        if (ctx.channel().isActive()) {
            msg.getBuffer().retain();
            out.add(msg.getBuffer());
            logger.info("Message sent (id: " + msg.opCode + " length: " + msg.length() +") session: 1");
            logger.info(msg.toString());
        }
    }
}