package com.uttec.diameter.zh;

import com.uttec.diameter.DiameterClient;
import com.uttec.diameter.DiameterServer;
import org.junit.Test;

/**
 * Created by DuLerWeil on 2014/12/8.
 */
public class DiameterServerClientTest {
    @Test
    public void testBootstrap() {
        new Thread(new DiameterServer(false, 5658, null, "com.uttec.diameter.zh.ZhServerHandler"), "DiameterServer").start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new DiameterClient(false, null, "com.uttec.diameter.zh.ZhClientHandler"), "DiameterClient").start();
        new Thread(new DiameterClient(false, null, "com.uttec.diameter.zh.ZhClientHandler"), "DiameterClient").start();
        new Thread(new DiameterClient(false, null, "com.uttec.diameter.zh.ZhClientHandler"), "DiameterClient").start();
        new Thread(new DiameterClient(false, null, "com.uttec.diameter.zh.ZhClientHandler"), "DiameterClient").start();
        new Thread(new DiameterClient(false, null, "com.uttec.diameter.zh.ZhClientHandler"), "DiameterClient").start();
        try {
            Thread.sleep(1200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
