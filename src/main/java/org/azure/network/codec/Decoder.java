package org.azure.network.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.Azure;
import org.azure.communication.messages.MessageDataWrapper;
import org.azure.network.sessions.Session;

import java.util.List;

public class Decoder extends MessageToMessageDecoder<ByteBuf> {
    private static final Logger logger = LogManager.getLogger(Decoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf data, List<Object> out) throws Exception {
        out.add(data);

        if (data.readableBytes() < 5) {
            return;
        }

        int index = data.readerIndex();

        if (data.readByte() == 0x3C) {
            data.clear();
            ctx.channel().write("<?xml version=\\\"1.0\\\"?>\\r\\n\" +\n" +
                    "<!DOCTYPE cross-domain-policy SYSTEM \\\"/xml/dtds/cross-domain-policy.dtd\\\">\\r\\n\" +\n" +
                    "<cross-domain-policy>\\r\\n\" +\n" +
                    "<allow-access-from domain=\\\"*\\\" to-ports=\\\"1-31111\\\" />\\r\\n\" +\n" +
                    "</cross-domain-policy>\\0")
                    .addListener(ChannelFutureListener.CLOSE);
            logger.info("Sent Habbo Policy to: " + ctx.channel().remoteAddress());
        } else {
            data.readerIndex(index);

            int length = data.readInt() - 2;

            if (data.readableBytes() < length) {
                data.readerIndex(index);
                return;
            }
        }
    }
}