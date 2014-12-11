package com.uttec.diameter.zh;

import com.uttec.diameter.msg.Avp;
import com.uttec.diameter.msg.AvpSet;
import com.uttec.diameter.msg.Header;
import com.uttec.diameter.msg.Message;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class ZhClientHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        Header header = new Header();
        header.setVersion((byte) 1);
        header.setLength(44);
        header.setR(true);
        header.setCommand(280);
        header.setApplicationId(0);
        header.setHopByHopId(0);
        header.setEndToEndId(0);
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
        Message msg = new Message(header, avpSet);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        Header header = new Header();
        header.setVersion((byte) 1);
        header.setLength(44);
        header.setR(true);
        header.setCommand(280);
        header.setApplicationId(0);
        header.setHopByHopId(msg.getHeader().getHopByHopId() + 1);
        header.setEndToEndId(msg.getHeader().getEndToEndId() + 1);
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
        Thread.sleep(1000);
        ctx.writeAndFlush(res);
    }
}
