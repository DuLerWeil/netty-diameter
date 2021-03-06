package com.uttec.diameter.zh;

import com.uttec.diameter.msg.Avp;
import com.uttec.diameter.msg.AvpSet;
import com.uttec.diameter.msg.Header;
import com.uttec.diameter.msg.Message;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class ZhServerHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        Header header = new Header();
        header.setVersion((byte) 1);
        header.setLength(44);
        header.setR(false);
        header.setCommand(280);
        header.setApplicationId(0);
        header.setHopByHopId(msg.getHeader().getHopByHopId());
        header.setEndToEndId(msg.getHeader().getEndToEndId());
        AvpSet avpSet = new AvpSet();
        Avp avp = new Avp();
        Avp avp2 = new Avp();
        avp.setCode(264);
        avp.setLength(12);
        avp.setData(new byte[4]);
        avp2.setCode(296);
        avp2.setLength(12);
        avp2.setData(new byte[4]);
        avpSet.getAll().add(avp);
        avpSet.getAll().add(avp2);
        Message res = new Message(header, avpSet);
        ctx.writeAndFlush(res);
    }
}
