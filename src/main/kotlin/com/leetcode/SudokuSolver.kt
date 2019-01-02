package com.leetcode

class SudokuSolver {
    private val rowCheck = Array(10) { mutableListOf<Int>() }
    private val colCheck = Array(10) { mutableListOf<Int>() }
    private val boxCheck = Array(10) { mutableListOf<Int>() }
    private val track = mutableListOf<IntArray>()

    fun solveSudoku(board: Array<CharArray>) {
        // init
        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, char ->
                if (char != '.') {
                    val k = char - '0'
                    rowCheck[k].add(i)
                    colCheck[k].add(j)
                    boxCheck[k].add(i / 3 * 3 + j / 3)
                }
            }
        }

        // dfs
        var i = 0
        var j: Int
        var k: Int
        while (i < 9) {
            j = 0
            k = 0
            while (j < 9) {
                if(board[i][j] == '.') {
                    k = getAvailableValid(i, j, k + 1)
                    if (k != -1 && k < 10) {
                        rowCheck[k].add(i)
                        colCheck[k].add(j)
                        boxCheck[k].add(i / 3 * 3 + j / 3)
                        track.add(intArrayOf(i, j, k))
                        board[i][j] = '0' + k
                        k = 0
                        j++
                    } else {
                        val intArray = track.removeAt(track.size - 1)
                        i = intArray[0]
                        j = intArray[1]
                        k = intArray[2]
                        board[i][j] = '.'
                        rowCheck[k].remove(i)
                        colCheck[k].remove(j)
                        boxCheck[k].remove(i / 3 * 3 + j / 3)
                    }
                } else {
                    j++
                }
            }
            i++
        }
    }

    private fun getAvailableValid(i: Int, j: Int, minValue: Int): Int {
        var k = minValue
        while(k <= 9) {
            if(rowCheck[k].contains(i) || colCheck[k].contains(j) || boxCheck[k].contains(i / 3 * 3 + j / 3)) k++
            else return k
        }
        return -1
    }
}