package com.leetcode

@Suppress("unused")
class RegularExpressionMatching2 {
    fun isMatch(s: String, p: String): Boolean {
        var s1 = s
        if (p.isEmpty()) return s1.isEmpty()
        if (p.length == 1) return s1.length == 1 && (p[0] == '.' || p[0] == s1[0])
        if (p[1] != '*')
            return if (s1.isEmpty()) false
            else (p[0] == s1[0] || p[0] == '.') && isMatch(s1.substring(1), p.substring(1))
        while (s1.isNotEmpty() && (s1[0] == p[0] || p[0] == '.')) {
            if (isMatch(s1, p.substring(2))) return true
            s1 = s1.substring(1)
        }
        return isMatch(s1, p.substring(2))
    }
}

fun main(args:Array<String>) {
    print(RegularExpressionMatching().isMatch("aa", "a*"))
}