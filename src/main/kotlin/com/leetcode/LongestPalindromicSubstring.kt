package com.leetcode

@Suppress("unused")
class LongestPalindromicSubstring {
    fun longestPalindrome(s: String): String {
        val n = s.length
        val pal = Array(n) { BooleanArray(n) }
        var maxLen = 0
        var x = 0
        var y = 0
        for (i in 0 until n) {
            var j = i
            while (j >= 0) {
                if (s[j] == s[i] && (i - j < 2 || pal[j + 1][i - 1])) {
                    pal[j][i] = true
                    if ((i - j + 1) > maxLen) {
                        x = i
                        y = j
                        maxLen = i - j + 1
                    }
                }
                j--
            }
        }
        return s.substring(y, x + 1)
    }
}

class LongestPalindromicSubstring2 {
    fun longestPalindrome(s: String): String {
        val str = s.fold("", { result, char ->
            result.plus("#$char")
        }).plus("#")
        val arr = Array(str.length) { 1 }
        var maxRight = 0
        var maxMiddle = 0
        var max = 0

        str.forEachIndexed({ i, char ->
            if (maxRight > i) arr[i] = Math.min(arr[maxMiddle * 2 - i], maxRight - i)
            while (i + arr[i] < str.length && i - arr[i] >= 0 && str[i + arr[i]] == str[i - arr[i]]) arr[i]++
            if(maxRight < i +  arr[i] - 1){
                maxRight = i + arr[i] - 1
                maxMiddle = i
                if(arr[i] > arr[max]) {
                    max = i
                }
            }
        })

        return str.substring(max - arr[max] + 1, max + arr[max] - 1).replace("#", "")
    }
}

fun main(args: Array<String>) {
    println(LongestPalindromicSubstring2().longestPalindrome("eabcb"))
}