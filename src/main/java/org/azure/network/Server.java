package org.azure.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.azure.network.codec.JsonDecoder;
import org.azure.network.codec.JsonEncoder;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Server {
    private final int port;

    private static final ThreadFactory factory = new ThreadFactory() {
        private final ThreadFactory wrapped = Executors.defaultThreadFactory();

        public Thread newThread(final Runnable r) {
            final Thread t = wrapped.newThread(r);
            t.setDaemon(true);
            return t;
        }
    };

    public Server(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), factory);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 10)
                        //.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                        .childOption((ChannelOption.SO_KEEPALIVE), true)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(
                                        new LoggingHandler(LogLevel.INFO),
                                        new JsonEncoder(),
                                        new JsonDecoder(),
                                        new ServerHandler());
                            }
                        });

                //ChannelFuture f = b.bind(this.port).sync();
                Channel ch = b.bind(port).sync().channel();
                ch.closeFuture().sync();
            } catch(final ChannelException ex) {
                System.out.println("");
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
        }
    }
}
