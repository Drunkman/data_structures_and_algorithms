package com.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NonBlockSocket {

    public static void main(String[] args) {
        client();
    }

    private static void client() {
        try (SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999))) {
            sChannel.configureBlocking(false);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String text = scanner.next();
                sChannel.write(ByteBuffer.wrap(text.getBytes()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
