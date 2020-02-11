package com.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NonBlockServerSocket {
    public static void main(String[] args) {
        server();
    }

    private static void server() {
        try (Selector selector = Selector.open();
             ServerSocketChannel ssChannel = ServerSocketChannel.open()) {
            ssChannel.bind(new InetSocketAddress(9999));
            ssChannel.configureBlocking(false);
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Iterator<SelectionKey> keyIterable = selector.selectedKeys().iterator();
                while (keyIterable.hasNext()) {
                    SelectionKey selectionKey = keyIterable.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel sChannel = ssChannel.accept();
                        sChannel.configureBlocking(false);
                        sChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    } else if (selectionKey.isReadable()) {
                        ByteBuffer buf = (ByteBuffer) selectionKey.attachment();
                        SocketChannel sChannel = (SocketChannel) selectionKey.channel();
                        sChannel.read(buf);
                        buf.flip();
                        if (buf.remaining() > 0) {
                            System.out.println(new String(buf.array(), 0, buf.remaining()));
                        }
                        buf.clear();
                    }
                    keyIterable.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
