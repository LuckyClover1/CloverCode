package com.clover.concurrent.observablethread;

/**
 * 线程观察者接口
 */
public interface Observable {
    enum Cycle{
        START,RUNNING,DONE,ERROR
    }
    Cycle getCycle();

    void start();

    void interupt();
}
