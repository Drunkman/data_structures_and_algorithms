package com.leetcode

class StringToInteger2 {
    fun myAtoi(str: String): Int {
        var s = str.trim()
        if(s.isEmpty()) return 0
        val firstChar = s[0]
        val maxIntDiv10 = Int.MAX_VALUE / 10
        if(s[0] == '-' || s[0] == '+') {
            s = s.substring(1)
        }
        var result = 0
        for(i in 0 until s.length) {
            val n = s[i] - '0'
            if(n < 0 || n > 9) {
                break
            }
            if(result < maxIntDiv10 || result == maxIntDiv10 && n <= 7) {
                result = result * 10 + n
            } else {
                return if(firstChar == '-') Int.MIN_VALUE else Int.MAX_VALUE
            }
        }
        return if(firstChar == '-') -1 * result else result
    }
}

fun main(args:Array<String>) {
    println(StringToInteger2().myAtoi("12346 sufas"))
    println(StringToInteger2().myAtoi("+1"))
    println(StringToInteger2().myAtoi("  0000000000012345678"))
    println(StringToInteger2().myAtoi("words and 987"))
    println(StringToInteger2().myAtoi("   -42"))
    println(StringToInteger2().myAtoi("-912834723"))
}