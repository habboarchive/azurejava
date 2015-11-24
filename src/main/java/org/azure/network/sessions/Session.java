package org.azure.network.sessions;

import io.netty.channel.Channel;

public class Session {
    private final Channel channel;

    public Session(Channel ch) {
        this.channel = ch;
    }

    public Channel getChannel() {
        return this.channel;
    }
}
