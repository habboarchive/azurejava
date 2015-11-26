package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.azure.Azure;
import org.azure.communication.messages.MessageHandler;
import org.azure.communication.protocol.ClientMessage;
import org.azure.network.sessions.Session;

import java.util.List;

public class GameDecoder extends MessageToMessageDecoder<ByteBuf> {
//    private static final Logger logger = LogManager.getLogger(GameDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        out.add(buf);

        // less than 5 bytes == garbage data
        if (buf.readableBytes() < 5) return;

        Session session = Azure.getSessionManager().getSessionByChannel(ctx.channel());

        buf.readInt(); // length
        short header = buf.readShort();
        ClientMessage msg = new ClientMessage(header, buf);
        MessageHandler.invoke(session, msg);
    }
}