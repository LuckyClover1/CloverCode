package com.clover.concurrent.observablethread;

import com.clover.concurrent.observablethread.impl.EmtryTaskLifeCycle;

/**
 * 观察者线程
 * @param <T>
 */
public class ObservableThread<T> extends Thread implements Observable {
    private TaskCycleLife<T> taskCycleLife;
    private Task<T> task;
    private Cycle cycle;
    ObservableThread(Task<T> task){
        this(new EmtryTaskLifeCycle(),task);
    }
    ObservableThread(TaskCycleLife<T> cycleLife, Task task){
        super();
        if(task == null){
            throw new IllegalArgumentException("task is null");
        }
        this.taskCycleLife = cycleLife;
        this.task = task;
    }

    @Override
    public void run() {
        this.update(Cycle.START,null,null);
        try{
            this.update(Cycle.RUNNING,null,null);
            T result = task.call();
            this.update(Cycle.DONE,result,null);
        }catch (Exception e){
            this.update(Cycle.ERROR,null,e);
        }
    }

    /**
     * 更新生命周期
     * @param cycle
     * @param result
     * @param e
     */
    private void update(Cycle cycle,T result,Exception e){
        this.cycle = cycle;
        if(taskCycleLife == null){
            return;
        }
        try{
            switch (cycle) {
                case START:{
                    this.taskCycleLife.onStart(currentThread());
                    break;
                }
                case RUNNING: {
                    this.taskCycleLife.onRunning(currentThread());
                    break;
                }
                case DONE: {
                    this.taskCycleLife.onFinish(currentThread(), result);
                    break;
                }
                case ERROR:{
                    this.taskCycleLife.onError(currentThread(),e);
                    break;
                }
            }
        }catch (Exception ex){
            if(cycle == Cycle.ERROR){
                throw ex;
            }
        }
    }
    @Override
    public Cycle getCycle() {
        return cycle;
    }

    @Override
    public void interupt() {
        currentThread().interrupt();
    }

}
