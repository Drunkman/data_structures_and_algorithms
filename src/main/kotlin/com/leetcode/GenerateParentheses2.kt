package com.leetcode

class GenerateParentheses2 {
    fun generateParenthesis(n: Int): List<String> {
        val ans  = mutableListOf<String>()
        generate(2 * n, 0, StringBuilder(""), ans)
        return ans
    }

    fun generate(n: Int, balance: Int, str: StringBuilder, ans: MutableList<String>) {
        if(balance < 0 || balance > n) return
        if(n == 0 && balance == 0) ans.add(str.toString())
        generate(n - 1, balance + 1, str.append('('), ans)
        str.deleteCharAt(str.length - 1)
        generate(n - 1, balance - 1, str.append(')'), ans)
        str.deleteCharAt(str.length - 1)
    }
}

fun main(args: Array<String>) {
    GenerateParentheses2().generateParenthesis(3).forEach { print("$it | ") }
    println()
}