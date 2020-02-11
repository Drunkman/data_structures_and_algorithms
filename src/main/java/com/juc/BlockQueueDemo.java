package com.juc;

public class BlockQueueDemo {
    public static void main(String[] args) {
//        BlockingQueue queue = new ArrayBlockingQueue(3);
//        System.out.println(queue.peek());

        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
        byte[] arr = new byte[10 * 1024 * 1024];

    }
}
