package com.leetcode

import java.util.*

class MinimumNumberOfRefuelingStops2 {
    fun minRefuelStops(target: Int, startFuel: Int, stations: Array<IntArray>): Int {
        val q = PriorityQueue<Int>(Collections.reverseOrder())
        var num = 0
        var prev = 0
        var fuel = startFuel
        for(station in  stations) {
            fuel -= station[0] - prev
            while(q.isNotEmpty() && fuel < 0) {
                fuel += q.poll()
                num++
            }
            if(fuel < 0) return -1
            q.offer(station[1])
            prev = station[0]
        }
        fuel -= target - prev
        while(q.isNotEmpty() && fuel < 0) {
            fuel += q.poll()
            num++
        }
        if(fuel < 0) return -1
        return num
    }
}

fun main(args: Array<String>) {
    println(
        MinimumNumberOfRefuelingStops2().minRefuelStops(
            100,
            10,
            arrayOf(intArrayOf(10, 60), intArrayOf(20, 30), intArrayOf(30, 30), intArrayOf(60, 40))
        )
    ) // 2

    println(
        MinimumNumberOfRefuelingStops2().minRefuelStops(
            100,
            50,
            arrayOf(intArrayOf(25, 25), intArrayOf(50, 50))
        )
    ) // 1
}