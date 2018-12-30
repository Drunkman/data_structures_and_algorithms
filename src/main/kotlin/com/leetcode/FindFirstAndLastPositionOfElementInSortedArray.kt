package com.leetcode

class FindFirstAndLastPositionOfElementInSortedArray {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if(nums.isEmpty()) return intArrayOf(-1, -1)
        val ans1: Int
        var ans2 = -1
        var start = 0
        var end = nums.size - 1
        while (start <= end) {
            val mid = (start + end) / 2
            if(target <= nums[mid]) end = mid
            else start = mid + 1
        }
        if(nums[end] == target) ans1 = end
        else return intArrayOf(-1, -1)
        start = ans1
        end = nums.size - 1
        while (start <= end) {
            val mid = (start + end) / 2 + 1
            if(nums[mid] <= target) start = mid
            else end = mid - 1
        }
        if(nums[start] == target) ans2 = start
        return intArrayOf(ans1, ans2)
    }
}