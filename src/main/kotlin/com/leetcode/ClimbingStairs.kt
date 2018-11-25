package com.leetcode

@Suppress("unused")
class ClimbingStairs {
    fun climbStairs(n: Int): Int {
        var answer = 1
        var i = 0
        var pre = 0
        while (i < n) {
            answer += pre
            pre = answer - pre
            i++
        }
        return answer
    }
}