package com.uttec.diameter.zh;

import com.uttec.diameter.impl.DiameterServerImpl;
import org.junit.Test;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class DiameterServerTest {
    @Test
    public void testBootstrap() {
        new DiameterServerImpl(false, 3868, null, "com.uttec.diameter.zh.ZhServerHandler").run();
    }
}
