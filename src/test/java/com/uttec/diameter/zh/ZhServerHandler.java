package com.uttec.diameter.zh;

import com.uttec.diameter.api.handler.DiameterServerHandler;
import com.uttec.diameter.api.msg.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class ZhServerHandler extends SimpleChannelInboundHandler<Message> implements DiameterServerHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

    }
}
