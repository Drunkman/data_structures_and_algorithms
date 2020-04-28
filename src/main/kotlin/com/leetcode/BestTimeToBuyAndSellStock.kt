package com.leetcode

@Suppress("unused")
fun maxProfit(prices: IntArray): Int {
    var max = 0
    var temp = 0
    prices.mapIndexed { index, i ->
        if (index == 0) 0 else i - prices[index - 1]
    }.mapIndexed { _, i ->
        if(temp + i > 0) {
            temp += i
        } else {
            temp = 0
        }
        max = Math.max(max, temp)
    }
    return max
}