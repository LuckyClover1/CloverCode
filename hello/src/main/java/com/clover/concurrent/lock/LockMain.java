package com.clover.concurrent.lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

public class LockMain {
    private final Lock lock = new BooleanLock();
    public static void main(String[] args) {
        LockMain lm = new LockMain();
        //创建5个线程
        IntStream.range(0,5).mapToObj(i->new Thread(lm::syncMehod)).forEach(Thread::start);
    }

    /**
     * 线程执行方法
     */
    private void syncMehod(){
        try {
            //3秒超时，中断
            lock.lock(3000);
//            int randomInt = (int)(Math.random()*10);
            int randomInt = ThreadLocalRandom.current().nextInt(3);
            System.out.println("randomInt:" + randomInt);
          //睡眠随机时间
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
