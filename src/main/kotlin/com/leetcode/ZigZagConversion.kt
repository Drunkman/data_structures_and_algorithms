package com.leetcode

@Suppress("unused")
class ZigZagConversion {
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s
        val rows = Array(numRows) { StringBuilder() }
        var down = true
        var num = 0
        for (i in 0 until s.length) {
            rows[num].append(s[i])
            if (num == numRows - 1) down = false
            if (num == 0) down = true
            if (down) num++ else num--
        }
        return rows.reduce { init, ele ->
            init.append(ele)
        }.toString()
    }
}

fun main(args: Array<String>) {
    println(ZigZagConversion().convert("PAYPALISHIRING", 4) == "PINALSIGYAHRPI")
    println(ZigZagConversion().convert("PAYPALISHIRING", 3) == "PAHNAPLSIIGYIR")
    println(ZigZagConversion().convert("AB", 1) == "AB")
}