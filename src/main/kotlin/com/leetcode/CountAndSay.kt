package com.leetcode

class CountAndSay {
    private val list = mutableListOf<String>("", "1", "11", "21", "1211", "111221")

    fun countAndSay(n: Int): String {
        if(list.size > n) return list[n]
        while(list.size <= n) {
            val str = list.last()
            var prev = str[0]
            var nums = 1
            val nextStr = StringBuilder()
            for (i in 1..str.length) {
                if(prev == str[i]) {
                    nums++
                } else {
                    nextStr.append(nums).append(prev)
                    nums = 1
                    prev = str[i]
                }
            }
            nextStr.append(nums).append(prev)
            list.add(nextStr.toString())
        }
        return list.last()
    }
}