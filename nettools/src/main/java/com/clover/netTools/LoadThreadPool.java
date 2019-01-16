package com.clover.netTools;

import com.clover.config.ConfigUtil;

import java.util.concurrent.*;

public class LoadThreadPool extends Thread{
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                Integer.valueOf(ConfigUtil.getString("threadpool.coreSize","5")),
                Integer.valueOf(ConfigUtil.getString("threadpool.maxSize","5")),
                Integer.valueOf(ConfigUtil.getString("threadpool.keepAliveSecond","60*10")),
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(50));

    public static void submit(Runnable runable){
        threadPool.submit(runable);
    }
}
