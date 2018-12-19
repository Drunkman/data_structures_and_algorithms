package com.leetcode

class LongestValidParentheses {
    fun longestValidParentheses(s: String): Int {
        var ans = 0
        val balances = Array(s.length + 1) {0}
        for(i in 0 until s.length) {
            if(s[i] == '(') {
                balances[i] = 0
                for(j in i + 1 until s.length + 1) {
                    balances[j] = balances[j - 1] + if (s[j - 1] == '(') 1 else -1
                    if(balances[j] < 0) break
                    if(balances[j] == 0 && j - i > ans){
                        ans = j - i
                    }
                }
            }
        }
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