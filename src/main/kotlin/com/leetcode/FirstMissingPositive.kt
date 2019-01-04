package com.leetcode

class FirstMissingPositive {
    fun firstMissingPositive(nums: IntArray): Int {
        if(nums.isEmpty()) return 1
        val list = nums.distinct().sorted()
        var offset = -1
        for(i in 0 until list.size) {
            if(list[i] <= 0) {
                offset++
                continue
            }
            if(list[i] == i - offset) {
                continue
            }
            return i - offset
        }
        return list.last() + 1
    }
}