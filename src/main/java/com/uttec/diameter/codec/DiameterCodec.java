package com.uttec.diameter.codec;

import com.uttec.diameter.msg.AvpSet;
import com.uttec.diameter.msg.Header;
import com.uttec.diameter.msg.Message;
import com.uttec.diameter.util.DiameterHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class DiameterCodec extends ByteToMessageCodec<Message> {
    private boolean headerNext = true;
    private Header header;
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeBytes(DiameterHelper.encode(msg));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (headerNext) {
            if (in.readableBytes() < 20) {
                return;
            }
            header = DiameterHelper.parseHeader(in.readSlice(20));
            headerNext = false;
        } else {
            int avpSetLength = header.getLength() - 20;
            if (in.readableBytes() < avpSetLength) {
                return;
            }
            AvpSet avpSet = DiameterHelper.parseAvpSet(in.readSlice(avpSetLength));
            Message msg = new Message(header, avpSet);
            out.add(msg);
            headerNext = true;
        }
    }
}
