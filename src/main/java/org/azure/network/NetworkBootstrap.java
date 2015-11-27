package org.azure.network;

import com.google.inject.Injector;
import com.netflix.governator.annotations.AutoBindSingleton;
import com.netflix.governator.annotations.Configuration;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.communication.encryption.RSA;
import org.azure.network.codec.GameDecoder;
import org.azure.network.codec.GameEncoder;
import org.azure.network.codec.PolicyDecoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@AutoBindSingleton
public class NetworkBootstrap {
    private static final Logger logger = LogManager.getLogger(NetworkBootstrap.class);
    private static Injector injector;
    private static RSA rsa;
    @Configuration(value = "org.azure.network.Server.port")
    private int port;
    @Configuration(value = "org.azure.network.Server.host")
    private String host;

    public static Injector getInjector() {
        return injector;
    }
    public static RSA getRSA() { return rsa; }

    public static void setInjector(Injector injector) {
        NetworkBootstrap.injector = injector;
    }

    private static final ThreadFactory factory = new ThreadFactory() {
        private final ThreadFactory wrapped = Executors.defaultThreadFactory();

        @SuppressWarnings("NullableProblems")
        public Thread newThread(final Runnable r) {
            final Thread t = wrapped.newThread(r);
            t.setDaemon(true);
            return t;
        }
    };

    public void startServer() {
        rsa = injector.getInstance(RSA.class);
        rsa.init();

        EventLoopGroup bossGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), factory);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1000)
                    //.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
//                    .childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024)
//                    .childOption(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 8 * 1024)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption((ChannelOption.SO_KEEPALIVE), true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("loggingHandler", new LoggingHandler(LogLevel.DEBUG));
                            ch.pipeline().addLast("gameEncoder", new GameEncoder());
                            ch.pipeline().addLast("policyDecoder", new PolicyDecoder());
                            ch.pipeline().addLast("gameDecoder", new GameDecoder());
                            ch.pipeline().addLast("networkChannelHandler", new NetworkChannelHandler());
                        }
                    });

            Channel ch = b.bind(new InetSocketAddress(host, port)).sync().channel();
            logger.info("Successfully established a socket connection on {}:{}", this.host, this.port);
            ch.closeFuture().sync();
        } catch (final ChannelException ex) {
            logger.error("Failed to establish a socket connection on {}:{}", this.host, this.port);
            System.exit(1);
        } catch (InterruptedException e) {
            logger.error("Interrupted while establishing a socket connection on {}:{}", this.host, this.port);
            System.exit(1);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
