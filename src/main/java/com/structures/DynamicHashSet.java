package com.structures;

import java.util.LinkedList;

public class DynamicHashSet<E> {

    static int[] FILTER = {0x8000, 0xC000, 0xE000, 0xF000,
                           0xF800, 0xFC00, 0xFE00, 0xFF00,
                           0xFF80, 0xFFC0, 0xFFE0, 0xFFF0,
                           0xFFF8, 0xFFFC, 0xFFFE, 0xFFFF};

    static class Node<E> {
        int profix;
        short pLen;
        LinkedList<E> list;

        Node(int profix, short pLen) {
            this.profix = profix;
            this.pLen = pLen;
            list = new LinkedList<>();
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
