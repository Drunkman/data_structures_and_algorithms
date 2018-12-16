package com.leetcode

class LongestValidParentheses {
    fun longestValidParentheses(s: String): Int {
        var ans = 0
        var count = 0
        val list = mutableListOf<Char>()
        var acc = 0
        s.forEach {
            if(it == '(') {
                list.add(it)
                count++
            } else {
                if(list.isNotEmpty()) {
                    list.removeAt(list.size - 1)
                    count++
                } else {
                    count = 0
                    acc = 0
                }
                if(list.isEmpty()) {
                    count += acc
                    acc = count
                    if(count > ans) ans = count
                    count = 0
                }
            }
        }
        if(list.isNotEmpty()) {
            count -= list.size
        }
        if(count > ans) ans = count
        return ans
    }
}

fun main(args: Array<String>) {
    println(LongestValidParentheses().longestValidParentheses("(()")) // 2
    println(LongestValidParentheses().longestValidParentheses(")()())")) // 4
    println(LongestValidParentheses().longestValidParentheses("(()))(()()()()()(")) // 10
    println(LongestValidParentheses().longestValidParentheses("()(()")) // 2
    println(LongestValidParentheses().longestValidParentheses(")()())()()(")) // 4
}