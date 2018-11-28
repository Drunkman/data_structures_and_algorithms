package com.leetcode

class ContainerWithMostWater2 {
    fun maxArea(height: IntArray): Int {
        if(height.size < 2) return 0
        var maxArea = 0
        for(i in 0 until height.size) {
            if(height[i] == 0) continue
            val width = maxArea / height[i]
            if(width >= i) continue
            for(j in 0 until i - width) {
                val area = Math.min(height[i], height[j]) * (i - j)
                if(area > maxArea) maxArea = area
            }
        }
        return maxArea
    }
}

fun main(args: Array<String>) {
    println(ContainerWithMostWater2().maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
}