package com.uttec.diameter.zh;

import com.uttec.diameter.DiameterServer;
import org.junit.Test;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class DiameterServerTest {
    @Test
    public void testBootstrap() {
        new DiameterServer(false, 5658, null, "com.uttec.diameter.zh.ZhServerHandler").run();
    }
}
