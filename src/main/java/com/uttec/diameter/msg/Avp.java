package com.uttec.diameter.msg;

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
}
