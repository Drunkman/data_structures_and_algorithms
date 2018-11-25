package com.leetcode

@Suppress("unused")
class TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val copy = nums.copyOf()
        copy.sort()
        var a = 0
        var b = nums.size - 1
        while(a < b) {
            when {
                copy[a] + copy[b] < target -> a++
                copy[a] + copy[b] > target -> b--
                else -> return intArrayOf(nums.indexOf(copy[a]), nums.indexOfLast { it == copy[b] })
            }
        }
        return intArrayOf()
    }

    fun twoSum1(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for(item in nums.indices) {
            if(map.contains(target - nums[item])) {
                return intArrayOf(map[target - nums[item]]!!, item)
            } else {
                map[nums[item]] = item
            }
        }
        return intArrayOf()
    }
}