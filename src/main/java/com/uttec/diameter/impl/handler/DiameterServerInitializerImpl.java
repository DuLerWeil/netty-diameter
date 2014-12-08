package com.uttec.diameter.impl.handler;

import com.uttec.diameter.api.handler.DiameterServerInitializer;
import com.uttec.diameter.impl.codec.DiameterCodecImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ssl.SslContext;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class DiameterServerInitializerImpl extends ChannelInitializer<Channel> implements DiameterServerInitializer {
    private SslContext sslCtx;
    private String handlerClass;

    public DiameterServerInitializerImpl(SslContext sslCtx, String handlerClass) {
        this.sslCtx = sslCtx;
        this.handlerClass = handlerClass;
    }
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline p  = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
        p.addLast("codec", new DiameterCodecImpl());
        Class<?> server = Class.forName(handlerClass);
        p.addLast("server", (ChannelHandler) server.newInstance());
    }
}
