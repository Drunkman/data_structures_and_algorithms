package com.leetcode;

import com.algorithm.Sort;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortTest extends TestCase {

    private List<Integer> list;
    private Sort<Integer> sort;

    public void setUp() throws Exception {
        super.setUp();
        sort = new Sort();
        list = new ArrayList();
        Random random = new Random();
        for(int i = 0; i < 100000000; i++) {
            list.add(random.nextInt(100000000));
        }
    }

    public void testQuickSort() throws Exception {
        Long start = System.currentTimeMillis();
        //System.out.println(list);
        sort.quickSort(list);
        //System.out.println(list);
        System.out.println(sort.check(list) + "    " + (System.currentTimeMillis() - start));
    }

}