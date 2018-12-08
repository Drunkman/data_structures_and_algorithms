package com.leetcode

class GenerateParentheses {
    fun generateParenthesis(n: Int): List<String> {
        if(n == 0) return emptyList()
        if(n == 1) return listOf("()")
        var set = mutableSetOf("()")
        for(i in 1 until n) {
            val temp = mutableSetOf<String>()
            for (str in set) {
                temp.add("()$str")
                temp.add("$str()")
                for(j in 1 until str.length) {
                    temp.add("${str.substring(0, j)}()${str.substring(j)}")
                }
            }
            set = temp
        }
        return set.toList()
    }
}

fun main(args: Array<String>) {
    GenerateParentheses().generateParenthesis(3).forEach { print("$it ") }
    println()
}