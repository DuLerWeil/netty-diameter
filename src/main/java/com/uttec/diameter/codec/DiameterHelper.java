package com.uttec.diameter.codec;

import com.uttec.diameter.msg.Avp;
import com.uttec.diameter.msg.AvpSet;
import com.uttec.diameter.msg.Header;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Date;
import java.util.List;

/**
 * Created by DuLerWeil on 2014/12/9.
 */
public class DiameterHelper {
    /**
     * This is seconds shift (70 years in seconds) applied to date,
     * since NTP date starts since 1900, not 1970.
     */
    private static final long SECOND_SHIFT = 2208988800L;

    public static Header parseHeader(ByteBuf in) {
        Header header = new Header();
        header.setVersion(in.readByte());
        byte[] lengthBytes = new byte[3];
        in.readBytes(lengthBytes);
        int length = getInt(lengthBytes, false);
        header.setLength(length);
        header.setFlags(in.readByte());
        byte[] commandBytes = new byte[3];
        in.readBytes(commandBytes);
        int command = getInt(commandBytes, false);
        header.setCommand(command);
        header.setApplicationId(in.readUnsignedInt());
        header.setHopByHopId(in.readUnsignedInt());
        header.setEndToEndId(in.readUnsignedInt());
        return header;
    }

    public static Avp parseAvp(ByteBuf in) {
        Avp avp = new Avp();
        avp.setCode(in.readUnsignedInt());
        avp.setFlags(in.readByte());
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

    public static AvpSet parseAvpSet(ByteBuf in, int length) {
        int end = in.readerIndex() + length;
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

    public static String getOctetString(byte[] data) {
        try {
            return new String(data, "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUTF8String(byte[] data) {
        try {
            return new String(data, "utf8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static float getFloat(byte[] data, boolean asc) {
        return Float.intBitsToFloat(getInt(data, asc));
    }

    public static double getDouble(byte[] data, boolean asc) {
        return Double.longBitsToDouble(getLong(data, asc));
    }

    public static InetAddress getAddress(byte[] data) {
        InetAddress inetAddress;
        byte[] address;
        try {
            if (data[1] == 1) {//1:IPv4;2:IPv6
                address = new byte[4];
                System.arraycopy(data, 2, address, 0, address.length);
                inetAddress = Inet4Address.getByAddress(address);
            } else {
                address = new byte[16];
                System.arraycopy(data, 2, address, 0, address.length);
                inetAddress = Inet6Address.getByAddress(address);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return inetAddress;
    }

    public static Date getTime(byte[] data) {
        byte[] tmp = new byte[8];
        System.arraycopy(data, 0 , tmp, 4, 4);
        return new Date(((getLong(tmp, false) - SECOND_SHIFT) * 1000L));
    }

    public static String getDiameterIdentity(byte[] data) {
        return getOctetString(data);
    }

    public static URI getDiameterURI(byte[] data) {
        try {
            return new URI(getOctetString(data));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static AvpSet getGrouped(byte[] data) {
        ByteBuf buf = Unpooled.wrappedBuffer(data);
        return parseAvpSet(buf, data.length);
    }
}
