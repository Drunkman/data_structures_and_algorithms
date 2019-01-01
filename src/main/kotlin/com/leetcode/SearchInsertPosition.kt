package com.leetcode

class SearchInsertPosition {
    fun searchInsert(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0
        if (target < nums[0]) return 0
        if (target > nums.last()) return nums.size
        var start = 0
        var end = nums.size - 1
        var mid = 0
        while (start <= end) {
            mid = (start + end) / 2
            if (target < nums[mid]) end = mid - 1
            else if (target > nums[mid]) start = mid + 1
            else return mid
        }
        return if (nums[mid] > target) mid else mid + 1
    }
}

fun main(args: Array<String>) {
    val solution = SearchInsertPosition()
    println(solution.searchInsert(intArrayOf(1, 3, 5, 6), 2)) // 1
    println(solution.searchInsert(intArrayOf(1, 6), 2)) // 1
    println(solution.searchInsert(intArrayOf(1, 2), 2)) // 1
    println(solution.searchInsert(intArrayOf(1, 3, 5, 6), 4)) // 2
    println(solution.searchInsert(intArrayOf(1, 3, 5, 6), 5)) // 2
    println(solution.searchInsert(intArrayOf(1, 3, 5, 7), 6)) // 3
    println(solution.searchInsert(intArrayOf(1), 1)) // 0
}