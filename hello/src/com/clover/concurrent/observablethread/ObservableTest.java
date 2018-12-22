package com.clover.concurrent.observablethread;

import com.clover.concurrent.observablethread.impl.EmtryTaskLifeCycle;

import java.util.concurrent.TimeUnit;

public class ObservableTest {
    public static void main(String[] args) {
        new ObservableThread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int d = 0;
            System.out.println(34/d);
            return null;
        }).start();

        final TaskCycleLife task = new EmtryTaskLifeCycle<String>(){
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("Finish "+thread.getName()+" Result "+result);
            }
        };

        new ObservableThread(task,()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hahahahah";
        }).start();
    }
}
