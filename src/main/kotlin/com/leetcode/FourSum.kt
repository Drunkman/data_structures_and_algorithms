package com.leetcode

class FourSum {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        if (nums.size < 4) return emptyList()
        val ans = mutableListOf<List<Int>>()
        nums.sort()
        for (i in 0 until nums.size - 3) {
            if (i != 0 && nums[i] == nums[i - 1]) continue
            for (j in i + 1 until nums.size - 2) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue
                var begin = j + 1
                var end = nums.size - 1
                while (begin < end) {
                    if(begin != j + 1 && nums[begin] == nums[begin - 1]) {
                        begin++
                        continue
                    }
                    if(end != nums.size - 1 && nums[end] == nums[end + 1]) {
                        end--
                        continue
                    }
                    val num = nums[i] + nums[j] + nums[begin] + nums[end]
                    when {
                        num > target -> end--
                        num < target -> begin++
                        else -> {
                            ans.add(listOf(nums[i], nums[j], nums[begin], nums[end]))
                            begin ++
                            end --
                        }
                    }
                }

            }
        }
        return ans
    }
}

fun main(args: Array<String>) {
    FourSum().fourSum(intArrayOf(1, 0, -1, 0, -2, 2), 0).forEach { println(it.joinToString()) }
//    [
//        [-1,  0, 0, 1],
//        [-2, -1, 1, 2],
//        [-2,  0, 0, 2]
//    ]
}