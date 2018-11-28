package com.leetcode

class ContainerWithMostWater {
    fun maxArea(height: IntArray): Int {
        if(height.size < 2) return 0
        var max = 0
        for(i in 0 until height.size - 1) {
            for (j in i until height.size) {
                val sum = Math.min(height[i], height[j]) * (j - i)
                if(sum > max) max = sum
            }
        }
        return max
    }
}

fun main(args: Array<String>) {
    println(ContainerWithMostWater().maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
}