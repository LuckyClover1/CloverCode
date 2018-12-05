package com.clover.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author Clover
 */
public class ThreadJoin {
    public static void main(String[] args) {
        Thread  t = new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(5);
                //t.join后，main进入阻塞
//                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end Tread");
        });
        t.start();
        try {
            //t生命周期结束后，不进入阻塞
            TimeUnit.SECONDS.sleep(10);
            //线程阻塞，等待join结束
//            TimeUnit.SECONDS.sleep(5);
            System.out.println(System.currentTimeMillis()+"start join");
            t.join();
            System.out.println(System.currentTimeMillis()+"end join");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}