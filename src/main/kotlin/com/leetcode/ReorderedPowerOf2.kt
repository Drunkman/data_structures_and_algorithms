package com.leetcode

class ReorderedPowerOf2 {
    fun reorderedPowerOf2(N: Int): Boolean {
        val list = N.toString().toCharArray().sorted()
        var num = 1
        while (true) {
            val numBits = Math.log10(num.toDouble()).toInt() + 1
            if(numBits > list.size) {
                return false
            }
            if(numBits == list.size && list == num.toString().toCharArray().sorted()) {
                return true
            }
            num = num.shl(1)
        }
    }
}

fun main(args: Array<String>) {
    println(ReorderedPowerOf2().reorderedPowerOf2(1)) //true
    println(ReorderedPowerOf2().reorderedPowerOf2(10)) //false
    println(ReorderedPowerOf2().reorderedPowerOf2(16)) //true
    println(ReorderedPowerOf2().reorderedPowerOf2(24)) //false
    println(ReorderedPowerOf2().reorderedPowerOf2(46)) //true
}