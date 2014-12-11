package com.uttec.diameter.handler;

import com.uttec.diameter.codec.DiameterCodec;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ssl.SslContext;

/**
 * Created by DuLerWeil on 2014/12/10.
 */
public class DiameterClientInitializer extends ChannelInitializer<Channel> {
    private SslContext sslCtx;
    private String handlerClass;

    public DiameterClientInitializer(SslContext sslCtx, String handlerClass) {
        this.sslCtx = sslCtx;
        this.handlerClass = handlerClass;
    }
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline p  = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
        p.addLast("codec", new DiameterCodec());
        Class<?> server = Class.forName(handlerClass);
        p.addLast("client", (ChannelHandler) server.newInstance());
    }
}
