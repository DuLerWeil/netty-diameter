package com.uttec.diameter.zh;

import com.uttec.diameter.msg.Message;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class ZhServerHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        ctx.close().addListener(ChannelFutureListener.CLOSE);
    }
}
