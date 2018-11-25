package com.leetcode

@Suppress("unused")
fun rob(nums: IntArray): Int {
    val arr = Array(nums.size, {0})
    nums.mapIndexed { index, i ->
        when (index) {
            0 -> arr[0] = i
            1 -> arr[1] = Math.max(nums[0], nums[1])
            else -> arr[index] = Math.max(arr[index - 1], arr[index - 2] + i)
        }
    }
    return if(arr.isEmpty()) 0 else arr.last()
}