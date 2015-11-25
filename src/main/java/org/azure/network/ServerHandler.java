package org.azure.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.azure.Azure;
import org.azure.communication.messages.MessageDataWrapper;
import org.azure.network.sessions.Session;
import org.azure.communication.protocol.ClientMessage;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Azure.getSessionManager().create(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        /*Session session = Azure.getSessionManager().getSessionByChannel(ctx.channel());
        try {
            ByteBuf data = ((ByteBuf) obj).copy();
            MessageDataWrapper message = new MessageDataWrapper(data);
            Azure.getMessageClassManager().execute(session, message);
        } catch (Exception ex) {
            LogManager.getLogger(ServerHandler.class).error("ERROR", ex);
        }*/
    }

    @Override
    @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
