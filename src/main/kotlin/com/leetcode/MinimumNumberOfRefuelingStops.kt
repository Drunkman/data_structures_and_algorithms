package com.leetcode

class MinimumNumberOfRefuelingStops {
    fun minRefuelStops(target: Int, startFuel: Int, stations: Array<IntArray>): Int {
        if (startFuel >= target) return 0
        if (stations.isEmpty() && startFuel < target) return -1
        val size = stations.size
        val dp = Array(size + 1) { 0 }
        dp[0] = startFuel
        for ((i, station) in stations.withIndex()) {
            for (j in i downTo 0) {
                if (dp[j] >= station[0]) {
                    dp[j + 1] = Math.max(dp[j] + station[1], dp[j + 1])
                }
            }
        }
        for ((i, dis) in dp.withIndex()) {
            if (dis >= target) return i
        }
        return -1
    }
}

fun main(args: Array<String>) {
    println(
        MinimumNumberOfRefuelingStops().minRefuelStops(
            100,
            10,
            arrayOf(intArrayOf(10, 60), intArrayOf(20, 30), intArrayOf(30, 30), intArrayOf(60, 40))
        )
    ) // 2
}