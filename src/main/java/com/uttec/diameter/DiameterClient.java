package com.uttec.diameter;

import com.uttec.diameter.handler.DiameterClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class DiameterClient implements Runnable {
    private boolean sctp;
    private SslContext sslCtx;
    private String handlerClass;

    public DiameterClient(boolean sctp, SslContext sslCtx, String handlerClass) {
        this.sctp = sctp;
        this.sslCtx = sslCtx;
        this.handlerClass = handlerClass;
    }
    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .handler(new DiameterClientInitializer(null, handlerClass));

            Channel ch = b.connect("127.0.0.1", 5658).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
