package com.leetcode

@Suppress("unused")
class PalindromeNumber {
    fun isPalindrome(x: Int): Boolean {
        if(x < 0) return false
        var init = x
        var rev = 0
        while (init != 0) {
            val pop = init % 10
            init /= 10
            rev = rev * 10 + pop
        }
        return rev == x
    }
}