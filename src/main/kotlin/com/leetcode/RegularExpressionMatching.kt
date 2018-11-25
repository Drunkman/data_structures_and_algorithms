package com.leetcode

@Suppress("unused")
class RegularExpressionMatching {

    fun isMatch(s: String, p: String): Boolean {
        val arr = Array(s.length + 1, { Array(p.length + 1, { false }) })
        var i = 0
        var j = 1
        arr[0][0] = true
        while (i <= s.length) {
            while (j <= p.length) {
                if (j > 1 && p[j - 1] == '*') {
                    arr[i][j] = arr[i][j - 2] || (i > 0 && (s[i - 1] == p[j - 2] || p[j - 2] == '.') && arr[i - 1][j])
                } else {
                    arr[i][j] = i > 0 && arr[i - 1][j - 1] && (s[i - 1] == p[j - 1] || p[j - 1] == '.')
                }
                j++
            }
            i++
            j = 1
        }
        return arr[s.length][p.length]
    }
}

fun main(args:Array<String>) {
    print(RegularExpressionMatching().isMatch("aa", "a*"))
}