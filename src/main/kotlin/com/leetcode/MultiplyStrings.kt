package com.leetcode

class MultiplyStrings {
    fun multiply(num1: String, num2: String): String {
        val list = mutableListOf<String>()
        var carry = 0
        val zore = StringBuilder("")
        var max = 0
        for (i in num1.length - 1 downTo 0) {
            var str = StringBuilder("")
            for (j in num2.length - 1 downTo 0) {
                val num = (num1[i] - '0') * (num2[j] - '0') + carry
                carry = num / 10
                str = str.append(num % 10)
            }
            if (carry != 0) {
                str = str.append(carry)
            }
            val s = "$zore$str"
            if(s.length > max) max = s.length
            list.add(s)
            zore.append('0')
            carry = 0
        }
        var ans = ""
        for(i in 0..max) {
            var num = 0
            for(j in list.size - 1 downTo 0) {
                num += (list[j].elementAtOrNull(i) ?: '0') - '0'
            }
            num += carry
            ans = "${num % 10}$ans"
            carry = num / 10
        }
        ans = "$carry$ans"
        for(i in 0 until ans.length) {
            if(ans[i] == '0') continue
            else return ans.substring(i)
        }
        return "0"
    }
}

fun main(args: Array<String>) {
    val solution = MultiplyStrings()
    println(solution.multiply("2", "3"))
    println(solution.multiply("123", "456"))
}