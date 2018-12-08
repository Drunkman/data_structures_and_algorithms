package com.leetcode

class LetterCombinationsOfAPhoneNumber {
    fun letterCombinations(digits: String): List<String> {
        if(digits.isEmpty()) return emptyList()
        val digitMap = mapOf(
            '2' to charArrayOf('a', 'b', 'c'),
            '3' to charArrayOf('d', 'e', 'f'),
            '4' to charArrayOf('g', 'h', 'i'),
            '5' to charArrayOf('j', 'k', 'l'),
            '6' to charArrayOf('m', 'n', 'o'),
            '7' to charArrayOf('p', 'q', 'r', 's'),
            '8' to charArrayOf('t', 'u', 'v'),
            '9' to charArrayOf('w', 'x', 'y', 'z')
        )
        var ans = mutableListOf("")
        digits.forEach {
            val temp = mutableListOf<String>()
            ans.forEach {pre ->
                (digitMap[it]?: charArrayOf()).forEach { dig ->
                    temp.add("$pre$dig")
                }
            }
            ans = temp
        }
        return ans
    }
}

fun main(args: Array<String>) {
    println(LetterCombinationsOfAPhoneNumber().letterCombinations("23")) // ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
}