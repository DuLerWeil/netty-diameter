package com.uttec.diameter.msg;

/**
 * Created by DuLerWeil on 2014/12/9.
 */
public class Header {
    private byte version;
    private int length;
    private boolean R;
    private boolean P;
    private boolean E;
    private boolean T;
    private byte flags;
    private int command;
    private long applicationId;
    private long hopByHopId;
    private long endToEndId;

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isR() {
        return R;
    }

    public void setR(boolean r) {
        R = r;
    }

    public boolean isP() {
        return P;
    }

    public void setP(boolean p) {
        P = p;
    }

    public boolean isE() {
        return E;
    }

    public void setE(boolean e) {
        E = e;
    }

    public boolean isT() {
        return T;
    }

    public void setT(boolean t) {
        T = t;
    }

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.R = ((flags & 0x0080) == 0x0080);
        this.P = ((flags & 0x0040) == 0x0040);
        this.E = ((flags & 0x0020) == 0x0020);
        this.T = ((flags & 0x0010) == 0x0010);
        this.flags = flags;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public long getHopByHopId() {
        return hopByHopId;
    }

    public void setHopByHopId(long hopByHopId) {
        this.hopByHopId = hopByHopId;
    }

    public long getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(long endToEndId) {
        this.endToEndId = endToEndId;
    }
}
