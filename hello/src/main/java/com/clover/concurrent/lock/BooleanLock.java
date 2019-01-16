package com.clover.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {
    private Thread currentThread = null;
    private boolean locked = false;
    private final List<Thread> lockList = new ArrayList<>();
    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while(locked){
                final Thread tempThread = currentThread();
                try {
                    if(lockList.contains(tempThread)) {
                        lockList.add(tempThread);
                    }
                    this.wait();
                }catch (InterruptedException e){
                    lockList.remove(tempThread);
                    throw e;
                }
            }
            System.out.println(currentThread() + " get lock");
            lockList.remove(currentThread());
            this.currentThread = currentThread();
            this.locked = true;
        }
    }

    @Override
    public void lock(long milliSeconds) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(milliSeconds <= 0){
                lock();
            }else{
                long remainingMilliSeconds = milliSeconds;
                long endMilliSeconds = System.currentTimeMillis() + remainingMilliSeconds;
                while(locked){
                    final Thread tempThread = currentThread();
                    try {
                        if (remainingMilliSeconds <= 0) {
                            throw new TimeoutException(tempThread + " cannot get the lock during " + milliSeconds);
                        }
                        if (lockList.contains(tempThread)) {
                            lockList.add(tempThread);
                        }
                        this.wait(remainingMilliSeconds);
                        remainingMilliSeconds = endMilliSeconds - System.currentTimeMillis();
                    }catch (InterruptedException e){
                        lockList.remove(tempThread);
                        throw e;
                    }
                }
                System.out.println(currentThread() + " get lock with timeout");
                lockList.remove(currentThread());
                this.currentThread = currentThread();
                this.locked = true;
            }
        }
    }

    @Override
    public void unlock() {
        synchronized(this){
            if(this.currentThread.getId() == currentThread().getId()){
                this.locked = false;
                this.notifyAll();
                System.out.println(currentThread() + " release lock");
            }
        }
    }

    private Thread currentThread(){
        return Thread.currentThread();
    }
}
