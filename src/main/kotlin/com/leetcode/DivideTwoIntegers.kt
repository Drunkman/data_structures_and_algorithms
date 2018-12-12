package com.leetcode

@Suppress("unused")
class DivideTwoIntegers {
    fun divide(dividend: Int, divisor: Int): Int {
        if (divisor == 1) return dividend
        if (divisor == -1) {
            return if (dividend == Int.MIN_VALUE) Int.MAX_VALUE else -dividend
        }
        if (divisor == Int.MIN_VALUE) {
            return if (dividend == Int.MIN_VALUE) 1 else 0
        }

        var a = Math.abs(dividend.toLong())
        var b = Math.abs(divisor.toLong())
        var ans = 0L
        var offset = -1
        while (b shl offset <= a) {
            offset++
        }
        b = b shl offset
        while(offset >= 0) {
            if(a >= b) {
                a -= b
                ans++
            }
            offset--
            b = b shr 1
            ans = ans shl 1
        }
        ans = ans shr 1
        ans = if(dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) -ans else ans
        return if (ans > Int.MAX_VALUE) Int.MAX_VALUE else ans.toInt()
    }
}