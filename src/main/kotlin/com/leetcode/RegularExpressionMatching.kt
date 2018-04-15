package com.leetcode

class RegularExpressionMatching {
    fun isMatch2(s: String, p: String): Boolean {
        var s1 = s
        if (p.isEmpty()) return s1.isEmpty()
        if (p.length == 1) return s1.length == 1 && (p[0] == '.' || p[0] == s1[0])
        if (p[1] != '*')
            return if (s1.isEmpty()) false
            else (p[0] == s1[0] || p[0] == '.') && isMatch(s1.substring(1), p.substring(1))
        while (s1.isNotEmpty() && (s1[0] == p[0] || p[0] == '.')) {
            if(isMatch(s1, p.substring(2))) return true
            s1 = s1.substring(1)
        }
        return isMatch(s1, p.substring(2))
    }

    fun isMatch(s: String, p: String): Boolean {
        var arr = Array(s.length, {Array(p.length, {false})})
        var i = 0
        var j = 1
        arr[0][0] = true
        while(i <= s.length) {
            while(j <= p.length) {
                if(j > 1 && p[j - 1] == '*') {
                    arr[i][j] = arr[i][j - 2] || (i > 0 && (s[i - 1] == p[j - 2] || p[j - 2] == '.') && arr[i - 1][j])
                } else {
                    arr[i][j] = i > 0 && arr[i - 1][j - 1] && (s[i - 1] == p[j - 1] || p[j - 1] == '.')
                }
                j++
            }
            i++
        }
        return arr[s.length][p.length]
    }
}