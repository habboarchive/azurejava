package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.Azure;
import org.azure.communication.messages.MessageHandler;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

import java.util.List;

public class GameDecoder extends MessageToMessageDecoder<ByteBuf> {
    private static final Logger logger = LogManager.getLogger(GameDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf data, List<Object> out) throws Exception {
        out.add(data);

        ByteBuf safeData = data.copy();

        if (safeData.readableBytes() < 5) {
            return;
        }

        if (safeData.readByte() == 0x3C) {
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer("<?xml version=\"1.0\"?>\r\n" +
                    "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n" +
                    "<cross-domain-policy>\r\n" +
                    "<allow-access-from domain=\"*\" to-ports=\"*\" />\r\n" +
                    "</cross-domain-policy>\0", CharsetUtil.UTF_8))
                    .addListeners(ChannelFutureListener.CLOSE);

            logger.info("Sent Habbo Policy to: " + ctx.channel().remoteAddress());
        } else {
            int length = data.readInt();
            short header = data.readShort();
            Session session = Azure.getSessionManager().getSessionByChannel(ctx.channel());
            ClientMessage msg = new ClientMessage(header, data);
            MessageHandler.invoke(session, msg);
        }
    }
}