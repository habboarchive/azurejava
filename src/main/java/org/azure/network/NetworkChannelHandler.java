package org.azure.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;
import org.azure.Azure;

public class NetworkChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Azure.getSessionManager().addSession(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Azure.getSessionManager().removeSession(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
    }

    @Override
    @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause.getCause() instanceof ReadTimeoutException) {
            // keep alive.
        }
    }
}
