package com.uttec.diameter.msg;

/**
 * Created by DuLerWeil on 2014/12/9.
 */
public class Header {
    private byte version;
    private int length;
    private byte flags;
    private int command;
    private int applicationId;
    private long hopByHopId;
    private long endToEndId;

    public int getVersion() {
        return version;
    }

    public int getLength() {
        return length;
    }
}
