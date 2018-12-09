package com.leetcode

@Suppress("unused")
class RemoveDuplicatesFromSortedArray {
    fun removeDuplicates(nums: IntArray): Int {
        if(nums.size < 2) return nums.size
        var ans = 1
        for(i in 1 until nums.size) {
            if(nums[i] == nums[i - 1]) {
                continue
            } else if(i != ans) {
                nums[ans] = nums[i]
            }
            ans++
        }
        return ans
    }
}