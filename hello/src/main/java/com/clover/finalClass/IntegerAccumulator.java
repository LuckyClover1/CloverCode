package com.clover.finalClass;
import java.util.concurrent.TimeUnit;

import java.util.stream.IntStream;

/**
 *此类不可实现累加
 * @deprecated
 */
public final class IntegerAccumulator{
    private final int init;
    public IntegerAccumulator(int init)
    {
        this.init = init;
    }
    public IntegerAccumulator(IntegerAccumulator accumulator, int init){
        this.init = accumulator.getValue() + init;
    }
    public IntegerAccumulator add(int i){
        return new IntegerAccumulator(this, i);
    }
    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() ->{
            int inc = 0;
            while (true){
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc).getValue();
                System.out.println(oldValue + "+" + inc + "=" + result);
                if (inc + oldValue!= result){
                    System.err.println("ERROR：" + oldValue + "+" + inc + "=" + result);
                }

                inc++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start());
    }
}