package com.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Quicksort<T extends Comparable<T>> {

    public void sort(List<T> list) {
        if(list == null || list.size() <= 1) {
            return;
        }
        sort2(0, list.size() - 1, list);
    }

    private void sort1(int low, int high, List<T> list) {
        if(low >= high) {
            return;
        }
        int h = partiable(low, high, list);
        sort1(low, h -1, list);
        sort1(h + 1, high, list);
    }

    public void sort2(int low, int high, List<T> list) {
        if(low >= high) {
            return;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        int h = partiable(low, high, list), h1;
        q.offer(low);
        q.offer(h - 1);
        q.offer(h + 1);
        q.offer(high);
        while(!q.isEmpty()) {
            low = q.poll();
            high = q.poll();
            if(low < high) {
                h1 = partiable(low, high, list);
                h = h1;
                q.offer(low);
                q.offer(h - 1);
                q.offer(h + 1);
                q.offer(high);
            }
        }
    }

    private int partiable(int low, int high, List<T> list) {
        T t = list.get(low);
        int l = low + 1, h = high;
        while(true) {
            while(list.get(h).compareTo(t) > 0) {
                h--;
            }
            while(l <= h && list.get(l).compareTo(t) <= 0) {
                l++;
            }
            if(l < h) {
                Collections.swap(list, l, h);
            } else {
                break;
            }
        }
        Collections.swap(list, low, h);
        return h;
    }
}
