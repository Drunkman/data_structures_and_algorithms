package com.leetcode

class RomanToInteger {
    fun romanToInt(s: String): Int {
        val map = mapOf('M' to 1000, 'D' to 500, 'C' to 100, 'L' to 50, 'X' to 10, 'V' to 5, 'I' to 1)
        var num = 0
        var i = 0
        while (i < s.length) {
            if(i < s.length - 1 && map[s[i]]!! < map[s[i + 1]]!!) {
                num += map[s[i + 1]]!! - map[s[i]]!!
                i += 2
            } else {
                num += map[s[i]]!!
                i++
            }
        }
        return num
    }
}

fun main(args: Array<String>) {
    println(RomanToInteger().romanToInt("III")) // 3
    println(RomanToInteger().romanToInt("IV")) // 4
    println(RomanToInteger().romanToInt("IX")) // 9
    println(RomanToInteger().romanToInt("LVIII")) //58
    println(RomanToInteger().romanToInt("MCMXCIV")) //1994
}