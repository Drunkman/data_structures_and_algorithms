package com.leetcode

class ValidSudoku {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val hashSet = hashSetOf<String>()
        for((i, row) in board.withIndex()) {
            for((j, char) in row.withIndex()) {
                if(char != '.') {
                    if(!hashSet.add("$char-$i") || !hashSet.add("$char~$j") || !hashSet.add("$char:${i / 3}:${j / 3}")) {
                        return false
                    }
                }
            }
        }
        return true
    }
}

fun main(args: Array<String>) {
    val solution = ValidSudoku()
    println(solution.isValidSudoku(arrayOf(
            charArrayOf('5','3','.','.','7','.','.','.','.'),
            charArrayOf('6','.','.','1','9','5','.','.','.'),
            charArrayOf('.','9','8','.','.','.','.','6','.'),
            charArrayOf('8','.','.','.','6','.','.','.','3'),
            charArrayOf('4','.','.','8','.','3','.','.','1'),
            charArrayOf('7','.','.','.','2','.','.','.','6'),
            charArrayOf('.','6','.','.','.','.','2','8','.'),
            charArrayOf('.','.','.','4','1','9','.','.','5'),
            charArrayOf('.','.','.','.','8','.','.','7','9')
    )))
}