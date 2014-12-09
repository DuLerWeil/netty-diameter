package com.uttec.diameter.msg;

import com.uttec.diameter.codec.DiameterHelper;

import java.net.InetAddress;
import java.net.URI;
import java.util.Date;

/**
 * Created by DuLerWeil on 2014/12/9.
 */
public class Avp {
    private long code;
    private boolean V;
    private boolean M;
    private boolean P;
    private byte flags;
    private int length;
    private long vendorId;
    private byte[] data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public boolean isV() {
        return V;
    }

    public void setV(boolean v) {
        V = v;
    }

    public boolean isM() {
        return M;
    }

    public void setM(boolean m) {
        M = m;
    }

    public boolean isP() {
        return P;
    }

    public void setP(boolean p) {
        P = p;
    }

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.V = ((flags & 0x0080) == 0x0080);
        this.M = ((flags & 0x0040) == 0x0040);
        this.P = ((flags & 0x0020) == 0x0020);
        this.flags = flags;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getOctetString() {
        return DiameterHelper.getOctetString(data);
    }

    public String getUTF8String() {
        return DiameterHelper.getUTF8String(data);
    }

    public int getInteger32() {
        return DiameterHelper.getInt(data, false);
    }

    public long getInteger64() {
        return DiameterHelper.getLong(data, false);
    }

    public long getUnsigned32() {
        return DiameterHelper.getUnsignedInt(data, false);
    }

    public long getUnsigned64() {
        return DiameterHelper.getLong(data, false);//FIXME
    }

    public float getFloat32() {
        return DiameterHelper.getFloat(data, false);
    }

    public double getFloat64() {
        return DiameterHelper.getDouble(data, false);
    }

    public InetAddress getAddress() {
        return DiameterHelper.getAddress(data);
    }

    public Date getTime() {
        return DiameterHelper.getTime(data);
    }

    public String getDiameterIdentity() {
        return DiameterHelper.getDiameterIdentity(data);
    }

    public URI getDiameterURI() {
        return DiameterHelper.getDiameterURI(data);
    }

    public AvpSet getGrouped() {
        return DiameterHelper.getGrouped(data);
    }
}
