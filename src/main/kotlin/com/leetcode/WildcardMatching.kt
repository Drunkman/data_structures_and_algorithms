package com.leetcode

class WildcardMatching {
    fun isMatch(s: String, p: String): Boolean {
        if (p.isEmpty() && s.isNotEmpty()) return false
        val list = mutableListOf<Int>()
        var i = 0
        var j = 0
        while (i < s.length) {
            if(j < p.length) {
                if (p[j] == '*') {
                    if (j < p.length - 1) {
                        if (p[j + 1] == '*') {
                            j++
                        } else if (p[j + 1] == s[i] || s[i] == '?') {
                            list.add(j)
                            j += 2
                            i++
                        } else {
                            i++
                        }
                    } else i++
                } else if (p[j] == '?' || p[j] == s[i]) {
                    i++
                    j++
                } else {
                    if (list.isEmpty())
                        return false
                    else {
                        j = list.last()
                        list.removeAt(list.size - 1)
                    }
                }
            } else if(list.isNotEmpty()) {
                j = list.last()
                list.removeAt(list.size - 1)
            } else {
                return false
            }
        }
        while (j < p.length) {
            if (p[j] == '*') j++
            else return false
        }
        return true
    }
}

fun main(args: Array<String>) {
    val solution = WildcardMatching()
    println(solution.isMatch("aa", "a")) // false
    println(solution.isMatch("aa", "*")) // true
    println(solution.isMatch("cb", "?a")) // false
    println(solution.isMatch("adceb", "*a*b")) // true
    println(solution.isMatch("acdcb", "a*c?b")) // false
    println(solution.isMatch("a", "")) // false
    println(solution.isMatch("abefcdgiescdfimde", "ab*cd?i*de")) // true
    println(solution.isMatch("aaaa", "***a")) // true
    println(solution.isMatch("c", "*?*")) // true
}