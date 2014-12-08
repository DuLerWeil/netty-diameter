package com.uttec.diameter.impl.codec;

import com.uttec.diameter.api.codec.DiameterCodec;
import com.uttec.diameter.api.msg.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class DiameterCodecImpl extends ByteToMessageCodec<Message> implements DiameterCodec {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    }
}
