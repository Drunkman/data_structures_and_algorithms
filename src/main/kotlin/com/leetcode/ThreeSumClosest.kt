package com.leetcode

class ThreeSumClosest {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()
        var ans = nums[0] + nums[1] + nums[2]
        for ((i, num) in nums.withIndex()) {
            val target1 = target - num
            var begin = 0
            var end = nums.size - 1
            while (begin < end) {
                if (begin == i) {
                    begin++
                    continue
                }
                if (end == i) {
                    end--
                    continue
                }
                val diff = target1 - nums[begin] - nums[end]
                if (Math.abs(diff) < Math.abs(ans - target)) {
                    ans = num + nums[begin] + nums[end]
                }
                when {
                    diff > 0 -> begin++
                    diff < 0 -> end--
                    else -> return target
                }
            }
        }
        return ans
    }
}

fun main(args: Array<String>) {
    println(
        ThreeSumClosest().threeSumClosest(
            intArrayOf(
                -1, 2, 1, -4
            ), 1
        )
    ) // 2

    println(
        ThreeSumClosest().threeSumClosest(
            intArrayOf(
                -3,-2,-5,3,-4
            ), -1
        )
    ) // -2
}