package com.nio;

import java.nio.ByteBuffer;

public class TestBuffer {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        printInfo(byteBuffer, "allocate");

        byteBuffer.put("abcde".getBytes());
        printInfo(byteBuffer, "put");

        byteBuffer.flip();
        printInfo(byteBuffer, "flip");

        byteBuffer.put("f".getBytes());
        printInfo(byteBuffer, "put after flip");

        System.out.println(byteBuffer.get());
        printInfo(byteBuffer, "get");

        System.out.println(byteBuffer.getChar());
        printInfo(byteBuffer, "getChar");

        byteBuffer.flip();
        printInfo(byteBuffer, "flip again");

        byteBuffer.flip();
        printInfo(byteBuffer, "flip again again");

        // throw java.nio.BufferUnderflowException
//        System.out.println(byteBuffer.get());
//        printInfo(byteBuffer, "get after limit is 0");

        byteBuffer.rewind();
        printInfo(byteBuffer, "rewind");

        byteBuffer.clear();
        printInfo(byteBuffer, "clear");
    }

    private static void printInfo(ByteBuffer byteBuffer, String title) {
        System.out.println("============" + title + "============");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }
}
