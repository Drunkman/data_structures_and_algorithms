package com.leetcode

class LongestCommonPrefix {
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        if (strs.size == 1) return strs[0]
        for (i in 0 until strs[0].length) {
            for (j in 1 until strs.size) {
                if(i >= strs[j].length) {
                    return strs[j]
                }
                if(strs[0][i] != strs[j][i]) {
                    return strs[0].substring(0, i)
                }
            }
        }
        return strs[0]
    }
}

fun main(args: Array<String>) {
    println(LongestCommonPrefix().longestCommonPrefix(arrayOf("flower","flow","flight"))) // "fl"
    println(LongestCommonPrefix().longestCommonPrefix(arrayOf("dog","racecar","car"))) // ""
}