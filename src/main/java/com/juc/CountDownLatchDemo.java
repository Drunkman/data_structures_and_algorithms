package com.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);

        for(int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            }, "线程" + i).start();
        }
        latch.await();
        System.out.println("显示完毕！");
    }
}
