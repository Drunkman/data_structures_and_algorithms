package com.leetcode

class BinaryGap {
    fun binaryGap(N: Int): Int {
        var maxGap = 0
        var gap = 0
        var tmp = N
        var begin = false
        while (tmp > 0) {
           if(tmp % 2 == 0) {
               if(begin) gap++
           } else {
               if (begin && gap > maxGap) maxGap = gap
               begin = true
               gap = 1
           }
           tmp /= 2
        }
        return maxGap
    }
}

fun main(args: Array<String>) {
    println(BinaryGap().binaryGap(22)) // 2
    println(BinaryGap().binaryGap(5)) // 2
    println(BinaryGap().binaryGap(6)) // 1
    println(BinaryGap().binaryGap(8)) // 0
}