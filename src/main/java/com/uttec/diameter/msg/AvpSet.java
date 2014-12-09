package com.uttec.diameter.msg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DuLerWeil on 2014/12/9.
 */
public class AvpSet {
    private List<Avp> avpList = new ArrayList<Avp>();

    public List<Avp> getAll() {
        return avpList;
    }
}
