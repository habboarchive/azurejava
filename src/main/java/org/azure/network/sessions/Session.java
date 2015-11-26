package org.azure.network.sessions;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.communication.protocol.ServerMessage;
import org.azure.network.codec.EncryptionDecoder;
import org.azure.utils.DiffieHellman;
import org.azure.utils.RC4;

public class Session {
    private static final Logger logger = LogManager.getLogger(Session.class);

    private final int id;
    private final Channel channel;
    private DiffieHellman diffieHellman;
    private RC4 rc4 = null;

    public Session(int id, Channel ch) {
        this.id = id;
        this.channel = ch;
        this.diffieHellman = new DiffieHellman();
    }

    public int getId() {
        return this.id;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public DiffieHellman getDiffieHellman() { return this.diffieHellman; }

    public void sendMessage(ServerMessage message) {
        this.sendRaw(message.getBuffer());
    }

    private void sendRaw(ByteBuf data) {
        ChannelFuture cf = channel.writeAndFlush(data);
        if (!cf.isSuccess()) {
            logger.error("Error", cf.cause());
        }
    }

    public void enableRC4(byte[] sharedKey) {
        this.rc4 = new RC4(sharedKey);
        //this.channel.pipeline().addBefore("gameDecoder", "gameCrypto", new EncryptionDecoder(sharedKey));
    }

    public RC4 getRC4() { return this.rc4; }
}
