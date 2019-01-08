package com.leetcode

class FirstMissingPositive {
    fun firstMissingPositive(nums: IntArray): Int {
        if(nums.isEmpty()) return 1
        val list = nums.sorted()
        var offset = -1
        for(i in 0 until list.size) {
            if(list[i] <= 0) {
                offset++
                continue
            }
            if(list[i] == i - offset) {
                continue
            } else if(i > 0 && list[i] == list[i -1]){
                offset++
                continue
            }
            return i - offset
        }
        return list.last() + 1
    }
}