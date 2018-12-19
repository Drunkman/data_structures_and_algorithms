package com.leetcode

class LongestValidParentheses2 {
    fun longestValidParentheses(s: String): Int {
        var ans = 0
        val longest = IntArray(s.length)
        for(i in 1 until longest.size) {
            if(s[i] == ')') {
                if (s[i - 1] == '(') {
                    longest[i] = 2 + if (i > 1) longest[i - 2] else 0
                } else {
                    val prev = i - longest[i - 1] - 1
                    if (prev >= 0 && s[prev] == '(') {
                        longest[i] = longest[i - 1] + 2 + if(prev > 0) longest[prev - 1] else 0
                    }
                }
            }
            ans = Math.max(ans, longest[i])
        }
        return ans
    }
}

fun main(args: Array<String>) {
    println(LongestValidParentheses2().longestValidParentheses("(()")) // 2
    println(LongestValidParentheses2().longestValidParentheses(")()())")) // 4
    println(LongestValidParentheses2().longestValidParentheses("(()))(()()()()()(")) // 10
    println(LongestValidParentheses2().longestValidParentheses("()(()")) // 2
    println(LongestValidParentheses2().longestValidParentheses(")()())()()(")) // 4
    println(LongestValidParentheses2().longestValidParentheses("()(())")) // 6
    println(LongestValidParentheses2().longestValidParentheses("(()())")) // 6
}