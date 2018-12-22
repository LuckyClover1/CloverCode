package com.clover.concurrent.classLoader;

public class Singleton {
    //@1在此处初始化，由于先初始化变量，再赋给定值，故x=0,y=1
//    private static Singleton instance = new Singleton();
    private static int x=0;
    private static int y;
    private static Singleton instance = new Singleton();
    private Singleton(){
        x++;
        y++;
    }
    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton in = Singleton.getInstance();
        System.out.println(in.x);
        System.out.println(in.y);
    }
}
