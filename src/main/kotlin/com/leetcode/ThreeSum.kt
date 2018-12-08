package com.leetcode

class ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) return emptyList()
        val ans = mutableListOf<List<Int>>()
        if (nums.count { it == 0 } >= 3) ans.add(listOf(0, 0, 0))
        nums.sort()
        val (positive, negative) = nums.partition { it > 0 }
        for((i, num) in nums.withIndex()) {
            if(i != 0 && num == nums[i - 1]) continue
            val list = if(num > 0) negative else positive
            var begin = 0
            var end = list.size - 1
            while(begin < end) {
                val sum = list[begin] + list[end] + num
                when {
                    sum < 0 -> begin ++
                    sum > 0 -> end --
                    else -> {
                        ans.add(listOf(list[begin], list[end], num))
                        begin++
                        end--
                        while(begin < list.size && list[begin] == list[begin - 1]) begin ++
                        while(end > 0 && list[end] == list[end + 1]) end --
                    }
                }
            }
        }
        return ans
    }
}

fun main(args: Array<String>) {
    ThreeSum().threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)).forEach { println(it.joinToString()) }
    println()
    ThreeSum().threeSum(intArrayOf(0, 0, 0)).forEach { println(it.joinToString()) }
    println()
    ThreeSum().threeSum(intArrayOf(1, 1, -2)).forEach { println(it.joinToString()) }
    println()
    ThreeSum().threeSum(intArrayOf(1, -1, 0)).forEach { println(it.joinToString()) }
    println()
    ThreeSum().threeSum(intArrayOf(-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0)).forEach { println(it.joinToString()) }
    println()
}