package com.leetcode

class StringToInteger{
    fun myAtoi(str: String): Int {
        if(str.isEmpty()) return 0
        val s = (Regex("^([-+])?[0-9]+").find(str.trim())?.value?:"0").toBigInteger()
        return when {
            s > Int.MAX_VALUE.toBigInteger() -> Int.MAX_VALUE
            s < Int.MIN_VALUE.toBigInteger() -> Int.MIN_VALUE
            else -> s.toInt()
        }
    }
}

fun main(args:Array<String>) {
    println(StringToInteger().myAtoi("12346 sufas"))
    println(StringToInteger().myAtoi("+1"))
    println(StringToInteger().myAtoi("  0000000000012345678"))
    println(StringToInteger().myAtoi("words and 987"))
}