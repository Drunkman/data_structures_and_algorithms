package com.leetcode

class IntegerToRoman {
    fun intToRoman(num: Int): String {
        var init = num
        val nums = intArrayOf(1000, 500, 100, 50, 10, 5, 1)
        val romans = charArrayOf('M', 'D', 'C', 'L', 'X', 'V', 'I')
        val str = StringBuilder()
        var i = 0
        while (i < 7) {
            if (init < nums[i]) {
                i++
            } else if (init in 900..999) {
                str.append("CM")
                init -= 900
            } else if(init in 400..499) {
                str.append("CD")
                init -= 400
            } else if (init in 90..99) {
                str.append("XC")
                init -= 90
            } else if (init in 40..49) {
                str.append("XL")
                init -= 40
            } else if (init == 9) {
                str.append("IX")
                return str.toString()
            } else if (init == 4) {
                str.append("IV")
                return str.toString()
            } else {
                str.append(romans[i])
                init -= nums[i]
            }
        }
        return str.toString()
    }
}

fun main(args: Array<String>) {
    println(IntegerToRoman().intToRoman(3))
    println(IntegerToRoman().intToRoman(4))
    println(IntegerToRoman().intToRoman(9))
    println(IntegerToRoman().intToRoman(58))
    println(IntegerToRoman().intToRoman(1994))
}