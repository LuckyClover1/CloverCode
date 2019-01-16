package com.clover.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface Lock {
    /**
     * 获取锁
     * @throws InterruptedException
     */
    public void lock() throws InterruptedException;

    /**
     * 获取超时锁，在milliSeconds时间后未获得锁，则中断
     * @param milliSeconds 单位毫秒
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public void lock(long milliSeconds) throws InterruptedException, TimeoutException;

    /**
     * 释放锁
     */
    public void unlock();
}
