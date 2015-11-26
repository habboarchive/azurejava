package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
public class PolicyDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        msg.markReaderIndex();
        byte delimiter = msg.readByte();
        msg.resetReaderIndex();

        if (delimiter == 0x3C) {
            msg.retain();
            String p = "<?xml version=\"1.0\"?>\r\n"
                    + "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n"
                    + "<cross-domain-policy>\r\n"
                    + "<allow-access-from domain=\"*\" to-ports=\"*\" />\r\n"
                    + "</cross-domain-policy>\0";
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(p.getBytes()));
        } else {
            ctx.channel().pipeline().remove("policyDecoder");
        }
    }
}
