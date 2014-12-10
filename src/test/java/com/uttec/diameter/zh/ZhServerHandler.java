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
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        msg.getHeader().setR(false);
        msg.getHeader().setP(true);
        msg.getHeader().setE(true);
        msg.getHeader().setT(true);
        ctx.write(msg).addListener(ChannelFutureListener.CLOSE);
    }
}
