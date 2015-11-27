package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.azure.Azure;
import org.azure.communication.messages.MessageHandler;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

import java.util.List;

public class GameDecoder extends ByteToMessageDecoder {
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        Session session = Azure.getSessionManager().getSessionByChannel(ctx.channel());

        // exit if session is null
        if (session == null) return;

        // message must be at least six bytes (length [4], header [2])
        if (buf.readableBytes() < 5) {
            session.killSession();
            return;
        }

        // skip packet length
        buf.readerIndex(4);

        // ClientMessage(header, buffer)
        ClientMessage msg = new ClientMessage(buf.readShort(), buf);
        MessageHandler.invoke(session, msg);
    }
}