package com.leetcode

class SubstringWithConcatenationOfAllWords {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        if (words.isEmpty() || s.length < words.size * words[0].length) return emptyList()
        val tl = s.length - words[0].length
        val ws = words.size
        val wl = words[0].length
        val ans = mutableListOf<Int>()
        for (i in 0 until wl) {
            var j = i
            val list = mutableListOf<String>()
            var checkList = words.toMutableList()
            while (j <= tl) {
                val str = s.substring(j, j + wl)
                when {
                    checkList.contains(str) -> {
                        list.add(str)
                        checkList.remove(str)
                    }
                    list.contains(str) -> {
                        while (list[0] != str) {
                            checkList.add(list[0])
                            list.removeAt(0)
                        }
                        list.removeAt(0)
                        list.add(str)
                    }
                    else -> {
                        list.clear()
                        checkList = words.toMutableList()
                    }
                }
                j += wl
                if (list.size == ws) {
                    ans.add(j - ws * wl)
                }
            }
        }
        return ans
    }
}

fun main(args: Array<String>) {
    println(
        SubstringWithConcatenationOfAllWords().findSubstring(
            "wordgoodgoodgoodbestword",
            arrayOf("word", "good", "best", "good")
        ).joinToString()
    ) // 8
    println(
        SubstringWithConcatenationOfAllWords().findSubstring(
            "aaaaaaaa",
            arrayOf("aa", "aa", "aa")
        ).joinToString()
    ) // 8
}