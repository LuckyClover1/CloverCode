package com.clover.concurrent.observablethread.impl;

import com.clover.concurrent.observablethread.TaskCycleLife;

/**
 *默认的生命周期调用接口实现
 * @param <T>
 */
public class EmtryTaskLifeCycle<T> implements TaskCycleLife<T> {
    @Override
    public void onStart(Thread thread) {
        System.out.println("start "+thread.getName());
    }

    @Override
    public void onRunning(Thread thread) {
        System.out.println("Running "+thread.getName());
    }

    @Override
    public void onEnd(Thread thread) {
        System.out.println("End "+thread.getName());
    }

    @Override
    public void onFinish(Thread thread, T result) {
        System.out.println("Finish "+thread.getName());

    }

    @Override
    public void onError(Thread thread, Exception e) {
        System.out.println("Error "+thread.getName());
        e.printStackTrace();
    }
}
