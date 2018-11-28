package com.leetcode

class ContainerWithMostWater3 {
    fun maxArea(height: IntArray): Int {
        if(height.size < 2) return 0
        var maxArea = 0
        var i = 0
        var j = height.size - 1
        while(i < j) {
            maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i))
            if(height[i] > height[j]) j--
            else i++
        }
        return maxArea
    }
}

fun main(args: Array<String>) {
    println(ContainerWithMostWater3().maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
}