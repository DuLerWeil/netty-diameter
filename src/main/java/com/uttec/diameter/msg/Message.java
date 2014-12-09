package com.uttec.diameter.msg;

/**
 * Created by DuLerWeil on 2014/12/9.
 */
public class Message {
    private Header header;
    private AvpSet avpSet;

    public Message(Header header, AvpSet avpSet) {
        this.header = header;
        this.avpSet = avpSet;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public AvpSet getAvpSet() {
        return avpSet;
    }

    public void setAvpSet(AvpSet avpSet) {
        this.avpSet = avpSet;
    }
}
