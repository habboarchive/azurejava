package org.azure.network.sessions;

import io.netty.channel.Channel;

public class Session {
    private final int id;
    private final Channel channel;

    public Session(int id, Channel ch) {
        this.id = id;
        this.channel = ch;
    }

    public int getId() {
        return this.id;
    }

    public Channel getChannel() {
        return this.channel;
    }
}
