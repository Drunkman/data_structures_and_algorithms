package com.leetcode

class NextPermutation {
    fun nextPermutation(nums: IntArray) {
        for(i in nums.size - 1 downTo 1) {
            if(nums[i] > nums[i - 1]) {
                var min = i
                for(j in nums.size - 1 downTo i) {
                    if(nums[j] > nums[i - 1]) {
                        min = j
                        break
                    }
                }
                nums[min] = nums[min] xor nums[i - 1]
                nums[i - 1] = nums[min] xor nums[i - 1]
                nums[min] = nums[i - 1] xor nums[min]
                reverse(nums, i)
                return
            }
        }
        reverse(nums)
    }
    fun reverse(nums: IntArray, start: Int = 0) {
        var i = start
        var j = nums.size - 1
        while(i < j) {
            nums[i] = nums[i] xor nums[j]
            nums[j] = nums[i] xor nums[j]
            nums[i] = nums[i] xor nums[j]
            i++
            j--
        }
    }
}

fun main(args: Array<String>) {
    val n = NextPermutation()
    var num = intArrayOf(1,2,3)
    n.nextPermutation(num)
    println(num.joinToString()) // 1,3,2
    num = intArrayOf(3,2,1)
    n.nextPermutation(num)
    println(num.joinToString()) // 1,2,3
    num = intArrayOf(1,3,2)
    n.nextPermutation(num)
    println(num.joinToString()) // 2,1,3
    num = intArrayOf(2,3,1)
    n.nextPermutation(num)
    println(num.joinToString()) // 3,1,2
}