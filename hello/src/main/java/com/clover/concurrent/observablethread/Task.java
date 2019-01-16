package com.clover.concurrent.observablethread;

/**
 * ObservableThread 任务，相当于Runnable接口
 * @param <T>
 */
public interface Task<T> {
    T call();
}
