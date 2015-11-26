package org.azure.network.codec;

/**
 * AzureJava,
 * Edited: 11/26/2015 (Scott Stamp).
 */
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.azure.utils.RC4;

import java.util.List;

/**
 * Intercepts data before it reaches the GameDecoder,
 * to decrypt the message and ensure enough data is available.
 */
public class EncryptionDecoder extends ByteToMessageDecoder {
    private RC4 rc4;

    public EncryptionDecoder(byte[] key) {
        this.rc4 = new RC4(key);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        byte[] data = buffer.readBytes(buffer.readableBytes()).array();
        System.out.println("Deciphered data!");
        //out.add(Unpooled.copiedBuffer(this.rc4.decipher(data)));
    }
}

