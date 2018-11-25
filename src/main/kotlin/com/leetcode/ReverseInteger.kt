package com.leetcode

// This question has problem
@Suppress("unused")
class ReverseInteger {
    fun reverse(x: Int): Int {
        var init = x
        var rev = 0
        while (init != 0) {
            val pop = init % 10
            init /= 10
            if (rev > Integer.MAX_VALUE / 10 || rev == Integer.MAX_VALUE / 10 && pop > 7) return 0
            if (rev < Integer.MIN_VALUE / 10 || rev == Integer.MIN_VALUE / 10 && pop < -8) return 0
            rev = rev * 10 + pop
        }
        return rev
    }
}

fun main(args:Array<String>) {
    print(ReverseInteger().reverse(123))
}