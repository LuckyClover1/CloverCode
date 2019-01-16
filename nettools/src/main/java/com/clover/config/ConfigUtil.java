package com.clover.config;

import com.clover.enumEntity.DelegateIP;
import com.clover.runableTask.DelegateIpTask;
import com.sun.javaws.exceptions.UnsignedAccessViolationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 获取配置文件内容
 * @author Clover
 */
public class ConfigUtil {
    private static Properties p = null;
    private static Random random = new Random();
    private static List<DelegateIP> delegateIpList = new ArrayList<>();
    private static int delegateIpListIndex = 0;

    static{
        p = new Properties();
        try {
            p.load(ConfigUtil.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        new DelegateIpTask().run();
    }
    public static String getString(String key, String defaultValue){
        return p.get(key) == null ? defaultValue:(String)p.get(key);
    }

    /**
     * 获取随机无符号整数，范围[0,Integer.MAX_VALUE]
     * @return
     */
    public static int getRandomUnsignedInt(){
        return getRandomUnsignedInt(0,Integer.MAX_VALUE);
    }
    /**
     * 获取随机无符号整数，范围[0,max]
     * @param max
     * @return
     */
    public static int getRandomUnsignedInt(int max){
        if(max <= 0){
            max = 0;
        }
        return getRandomUnsignedInt(0,max);
    }

    /**
     * 获取随机无符号整数，范围[min,max]
     * @param min
     * @param max
     * @return
     */
    public static int getRandomUnsignedInt(int min,int max){
        if(min < 0) {
            min = 0;
        }
        if(max <= min){
            max += min;
        }
        return min + random.nextInt(max);
    }

    public static void setDelegateIpList(List<DelegateIP> list){
        delegateIpList.clear();
        delegateIpList = list;
    }

    public static DelegateIP getNextDelegate(){
        while(delegateIpList.size() == 0){
            synchronized (ConfigUtil.class){
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(delegateIpListIndex >= delegateIpList.size()){
            delegateIpListIndex = 0;
        }
        return delegateIpList.get(delegateIpListIndex++);
    }

    /**
     * @deprecated
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(ConfigUtil.getString("text", "wwwww"));
    }
}
