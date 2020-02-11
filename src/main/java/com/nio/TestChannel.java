package com.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

public class TestChannel {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        copyFileByNormalBuffer();
        System.out.println("normal time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        copyFileByDirectBuffer();
        System.out.println("direct time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        copyFileByMappedBuffer();
        System.out.println("mapped time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        copyFileByTransfer();
        System.out.println("transfer time: " + (System.currentTimeMillis() - start));
    }

    private static void copyFileByTransfer() {
        try (FileChannel inChannel = FileChannel.open(Paths.get("D:/test.mkv"), READ);
             FileChannel outChannel = FileChannel.open(Paths.get("D:/test2.mkv"), READ, WRITE, CREATE)) {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyFileByMappedBuffer() {
        try (FileChannel inChannel = FileChannel.open(Paths.get("D:/test.mkv"), READ);
             FileChannel outChannel = FileChannel.open(Paths.get("D:/test2.mkv"), READ, WRITE, CREATE)) {
            MappedByteBuffer m1 = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer m2 = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            byte[] bytes = new byte[m1.limit()];
            m1.get(bytes);
            m2.put(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyFileByDirectBuffer() {
        try (FileChannel inChannel = new FileInputStream("D:/test.mkv").getChannel();
             FileChannel outChannel = new FileOutputStream("D:/test1.mkv").getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int)inChannel.size());
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyFileByNormalBuffer() {
        try (FileChannel inChannel = new FileInputStream("D:/test.mkv").getChannel();
             FileChannel outChannel = new FileOutputStream("D:/test1.mkv").getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)inChannel.size());
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
