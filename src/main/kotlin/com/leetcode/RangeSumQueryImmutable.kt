package com.leetcode

class RangeSumQueryImmutable(nums: IntArray) {

    private var sums: IntArray

    init {
        var sum = 0
        sums = nums.map {
            sum += it
            sum
        }.toIntArray()
    }

    fun sumRange(i: Int, j: Int): Int {
        return if(i > 0) sums[j] - sums[i - 1] else sums[j]
    }
}