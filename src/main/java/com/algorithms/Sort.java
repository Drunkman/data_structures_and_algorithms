package com.algorithms;

import java.util.List;

public class Sort<T extends Comparable<T>> {
    private Quicksort<T> quickSort = new Quicksort<T>();

    public void quickSort(List<T> list) {
        quickSort.sort(list);
    }

    public String check(List<T> list) {
        if(list == null || list.size() <= 1) {
            return "success";
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i < list.size() - 1; i++) {
            if(list.get(i).compareTo(list.get(i - 1)) < 0) {
                builder.append(i-1 + ":" + list.get(i-1) + "    " + i + ":" + list.get(i) + "\n");
            }
        }
        if(builder.length() == 0) {
            return "success";
        } else {
            return builder.toString();
        }
    }
}
