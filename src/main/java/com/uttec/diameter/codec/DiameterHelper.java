package com.uttec.diameter.codec;

import com.uttec.diameter.msg.Avp;
import com.uttec.diameter.msg.AvpSet;
import com.uttec.diameter.msg.Header;
import io.netty.buffer.ByteBuf;

import java.util.List;

/**
 * Created by DuLerWeil on 2014/12/9.
 */
public class DiameterHelper {
    public static Header parseHeader(ByteBuf in) {
        return new Header();
    }

    public static Avp parseAvp(ByteBuf in) {
        Avp avp = new Avp();
        avp.setCode(in.readUnsignedInt());
        avp.setFlags(in.readUnsignedByte());

        byte[] lengthBytes = new byte[3];
        in.readBytes(lengthBytes);
        int length = getInt(lengthBytes, false);
        avp.setLength(length);

        byte[] data;
        if (avp.isV()) {
            avp.setVendorId(in.readUnsignedInt());
            data = new byte[length - 12];
        } else {
            data = new byte[length - 8];
        }
        in.readBytes(data);
        avp.setData(data);

        int padding = length % 4;
        if (padding > 0) {
            padding = 4 - padding;
            in.skipBytes(padding);
        }
        return avp;
    }

    public static AvpSet parseAvpSet(ByteBuf in, Header header) {
        int end = in.readerIndex() + header.getLength();
        AvpSet avpSet = new AvpSet();
        List<Avp> avpList = avpSet.getAll();
        while (in.readerIndex() < end) {
            Avp avp = parseAvp(in);
            avpList.add(avp);
        }
        return avpSet;
    }

    public static byte[] getBytes(short s, boolean asc) {
        byte[] buf = new byte[2];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        return buf;
    }

    public static byte[] getBytes(int s, boolean asc) {
        byte[] buf = new byte[4];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x000000ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x000000ff);
                s >>= 8;
            }
        return buf;
    }

    public static byte[] getBytes(long s, boolean asc) {
        byte[] buf = new byte[8];
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00000000000000ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00000000000000ff);
                s >>= 8;
            }
        return buf;
    }

    public static short getShort(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 2) {
            throw new IllegalArgumentException("byte array size > 2 !");
        }
        short r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x00ff);
            }
        return r;
    }

    public static int getInt(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
        int r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        return r;
    }

    public static long getUnsignedInt(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
        long r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        return r;
    }

    public static long getLong(byte[] buf, boolean asc) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 8) {
            throw new IllegalArgumentException("byte array size > 8 !");
        }
        long r = 0;
        if (asc)
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        else
            for (int i = 0; i < buf.length; i++) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        return r;
    }
}
