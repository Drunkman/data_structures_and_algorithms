package com.juc;

/**
 * 如果使用notify(), 而不是notifyAll()，则存在所有的线程都处于waiting状态。
 * 比如在只有A处于Runnable状态时，A把number置为1，并把B唤醒，A自己检查条件之后进入waiting状态，
 * 则B在获取锁之后，检查条件也进入到waiting状态，但是没有唤醒任何其他线程，
 * 至此，所有线程进入waiting状态，程序挂起
 *
 * static method -- 类对象 -- synchronized(类对象)
 * instant method -- 类对象实例 -- synchronized(类对象实例)
 */
public class SynchronizedDemo
{
    public static void main( String[] args ) {
        new Thread(() -> {for(int i = 0; i < 10; i++) AirCondition.add();}, "A").start();
        new Thread(() -> {for(int i = 0; i < 10; i++) AirCondition.add();}, "B").start();
        new Thread(() -> {for(int i = 0; i < 10; i++) AirCondition.sub();}, "C").start();
        new Thread(() -> {for(int i = 0; i < 10; i++) AirCondition.sub();}, "D").start();
    }

    static class AirCondition {
        private static int number = 0;

        public static synchronized void add() {
            while(number != 0) {
                try {
                    AirCondition.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            AirCondition.class.notifyAll();
        }

        public static synchronized void sub() {
            while(number == 0) {
                try {
                    AirCondition.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number--;
            AirCondition.class.notifyAll();
        }
    }
}