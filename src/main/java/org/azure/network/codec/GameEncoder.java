package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GameEncoder extends MessageToMessageEncoder<ByteBuf> {
    private static final Logger logger = LogManager.getLogger(GameEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf data, List<Object> out) throws Exception {
        try {
            if (ctx.channel().isOpen()) {
                out.add(data.retain());
            }
        } catch (final Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}