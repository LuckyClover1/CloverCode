package com.clover.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class CloverQueue {
    private static int DEFAULT_LENGTH = 10;
    private final LinkedList<Message> queue = new LinkedList<>();
    public void offer(Message o){
        synchronized(queue) {
            if (queue.size() >= DEFAULT_LENGTH) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("offer Message " + o.getId());
            queue.addLast(o);
            queue.notify();
        }
    }

    public Message take(){
        synchronized(queue) {
            if (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message o = queue.pollFirst();
            queue.notify();
            System.out.println("take message "+ o.getId());
            return o;
        }
    }

    public static void main(String[] args) {
        CloverQueue cloverQueue = new CloverQueue();
        new Thread(()->{
            for(int i=0;i<100;i++){
                cloverQueue.offer(new Message(i));
            }
        }).start();

        new Thread(()->{
            for(;;){
                Message m = (Message) cloverQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
class Message{
    private int id;
    public Message(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}