package org.azure.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.network.codec.Encoder;
import org.azure.network.codec.Decoder;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);

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
                                    new Encoder(),
                                    new Decoder(),
                                    new ServerHandler());
                        }
                    });

            //ChannelFuture f = b.bind(this.port).sync();
            Channel ch = b.bind(port).sync().channel();
            ch.closeFuture().sync();
            // Don't know if this will show due to the channel executing closeFuture().sync();
            logger.info("Successfully established a socket connection on port " + this.port);
        } catch (final ChannelException ex) {
            logger.info("Failed to establish a socket connection on port " + this.port);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
