package com.uttec.diameter.codec;

import com.uttec.diameter.msg.AvpSet;
import com.uttec.diameter.msg.Header;
import com.uttec.diameter.msg.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class DiameterCodec extends ByteToMessageCodec<Message> {
    private boolean toHeader = true;
    private Header header;
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (toHeader) {
            if (in.readableBytes() < 20) {
                return;
            }
            header = DiameterHelper.parseHeader(in);
            toHeader = false;
        } else {
            if (in.readableBytes() < header.getLength() - 20) {
                return;
            }
            AvpSet avpSet = DiameterHelper.parseAvpSet(in, header.getLength() - 20);
            Message msg = new Message(header, avpSet);
            out.add(msg);
            toHeader = true;
        }
    }
}
