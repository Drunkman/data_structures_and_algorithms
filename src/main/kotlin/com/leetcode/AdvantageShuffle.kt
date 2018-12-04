package com.leetcode

import java.util.*

class AdvantageShuffle {
    fun advantageCount(A: IntArray, B: IntArray): IntArray {
        val size = A.size
        val sortB = B.sorted()
        val sortA = A.sorted()
        val map = mutableMapOf<Int, MutableList<Int>>()
        B.forEach{ map[it] = mutableListOf() }
        val remain = mutableListOf<Int>()
        var i = 0
        for (a in sortA) {
            if(a > sortB[i]) {
                map[sortB[i++]]!!.add(a)
            } else {
                remain.add(a)
            }
        }
        for (j in 0 until size) {
            if(map[B[j]]!!.size > 0) {
                B[j] = map[B[j]]!!.removeAt(0)
            } else {
                B[j] = remain.removeAt(0)
            }
        }
        return B
    }
}

fun main(args: Array<String>) {
    println(
        AdvantageShuffle().advantageCount(
            intArrayOf(2, 7, 11, 15),
            intArrayOf(1, 10, 4, 11)
        ).joinToString()
    ) // [2,11,7,15]
    println(
        AdvantageShuffle().advantageCount(
            intArrayOf(12, 24, 8, 32),
            intArrayOf(13, 25, 32, 11)
        ).joinToString()
    ) // [24,32,8,12]
    println(
        AdvantageShuffle().advantageCount(
            intArrayOf(2,0,4,1,2),
            intArrayOf(1,3,0,0,2)
        ).joinToString()
    ) // [2,0,2,1,4]
    println(
        AdvantageShuffle().advantageCount(
            intArrayOf(15777,7355,6475,15448,18412),
            intArrayOf(986,13574,14234,18412,19893)
        ).joinToString()
    ) // [6475,15448,15777,18412,7355]
}