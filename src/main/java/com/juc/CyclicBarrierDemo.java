package com.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> System.out.println("Finish!"));

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                for (int j = 0; j < 6; j++) {
                    System.out.println(Thread.currentThread().getName() + " " + j);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread " + i).start();
        }
    }
}
