package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
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
        // less than 5 bytes == garbage data
        if (data.readableBytes() < 5) return;

        Session session = Azure.getSessionManager().getSessionByChannel(ctx.channel());

        if (session.getRC4() != null)
            data = session.getRC4().decipher(data);

        //int length = data.readInt();
        data.readInt();
        short header = data.readShort();
        ClientMessage msg = new ClientMessage(header, data);
        MessageHandler.invoke(session, msg);
    }
}