package com.clover.concurrent.observablethread;

/**
 * 线程生命周期接口
 * @param <T>
 */
public interface TaskCycleLife<T> {
    /**
     * 线程开始时调用
     * @param thread
     */
    void onStart(Thread thread);

    /**
     * 线程结束时调用
     * @param thread
     */
    void onEnd(Thread thread);

    /**
     * 线程进入执行状态时调用
     * @param thread
     */
    void onRunning(Thread thread);

    /**
     * 线程结束时调用
     * @param thread
     * @param result
     */
    void onFinish(Thread thread,T result);

    /**
     * 线程报错时调用
     * @param thread
     * @param e
     */
    void onError(Thread thread,Exception e);
}
