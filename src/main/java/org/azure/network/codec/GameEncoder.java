package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.Azure;
import org.azure.network.sessions.Session;

import java.util.List;

public class GameEncoder extends MessageToMessageEncoder<ByteBuf> {
    private static final Logger logger = LogManager.getLogger(GameEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf data, List<Object> out) throws Exception {
        data.retain();
        try {
            if (ctx.channel().isOpen()) {
                Session session = Azure.getSessionManager().getSessionByChannel(ctx.channel());
                if (session.getRC4() != null) {
//                    out.add(session.getRC4().decipher(data));
                    out.add(data);
                } else {
                    out.add(data);
                }
            }
        } catch (final Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}