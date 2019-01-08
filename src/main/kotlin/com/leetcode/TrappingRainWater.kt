package com.leetcode

class TrappingRainWater {
    fun trap(height: IntArray): Int {
        if(height.size < 3) return 0
        var h = height[0]
        var l1 = 0
        val l2: Int
        var r1 = 0
        val r2: Int
        var area = 0
        for(i in 0 until height.size) {
            if(height[i] < h) {
                area += h - height[i]
            } else {
                h = height[i]
                l1 = area
            }
        }
        r2 = area - l1
        area = 0
        h = height.last()
        for(i in height.size - 1 downTo 0) {
            if(height[i] <= h) {
                area += h - height[i]
            } else {
                h = height[i]
                r1 = area
            }
        }
        l2 = area - r1
        return h * height.size - height.fold(0) { sum, it -> sum + it} - ((l2 - l1) + (r2 - r1))
    }
}

fun main(args: Array<String>) {
    val solution = TrappingRainWater()
    println(solution.trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1))) // 6
    println(solution.trap(intArrayOf(2,0,2))) // 2
}