package com.leetcode

class RemoveElement {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var ans = 0
        var step = 0
        for(i in 0 until nums.size) {
            if(nums[i] == `val`) {
                step++
            } else {
                nums[i - step] = nums[i]
                ans++
            }
        }
        return ans
    }
}