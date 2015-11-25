package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Encoder extends MessageToMessageEncoder<ByteBuf> {
    private static final Logger logger = LogManager.getLogger(Encoder.class);

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