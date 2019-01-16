package com.clover.concurrent.classLoader;

/**
 * 静态内部类单例
 * 在Singleton类中并没有instance的静态成员，而是将其放到了静态内部类Holder之中，
 * 因此在Singleton类的初始化过程中并不会创建Singleton的实例，Holder类中定义了Singleton的静态变量，
 * 并且直接进行了实例化，当Holder被主动引用的时候则会创建Singleton的实例，
 * Singleton实例的创建过程在Java程序编译时期收集至＜clinit＞（）方法中，该方法又是同步方法，
 * 同步方法可以保证内存的可见性、JVM指令的顺序性和原子性、Holder方式的单例设计是最好的设计之一，
 * 也是目前使用比较广的设计之一。
 */
public final class HolderSingleton {
    private byte[] data = new byte[1024];
    private HolderSingleton(){

    }
    public static HolderSingleton getInstance(){
        return Holder.instance;
    }
    //HolderSingleton加载时不会主动加载静态内部类，调用静态内部类的方法、变量等时才会加载
    private static class Holder{
        private static HolderSingleton instance = new HolderSingleton();
    }
}
