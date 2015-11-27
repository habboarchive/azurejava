package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class PolicyDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        char delimiter = (char)buf.readByte();
        buf.resetReaderIndex();

        if (delimiter == '<') {
            String p = "<?xml version=\"1.0\"?>\r\n"
                    + "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n"
                    + "<cross-domain-policy>\r\n"
                    + "<allow-access-from domain=\"*\" to-ports=\"*\" />\r\n"
                    + "</cross-domain-policy>\0";

            ctx.writeAndFlush(Unpooled.copiedBuffer(p.getBytes())).addListener(ChannelFutureListener.CLOSE);
        } else {
            out.add(buf);
            ctx.pipeline().remove(this);
        }
    }
}
