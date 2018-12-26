package com.leetcode

class SearchInRotatedSortedArray {
    fun search(nums: IntArray, target: Int): Int {
        if(nums.isEmpty()) return -1
        var start = 0
        var end = nums.size - 1
        if(nums[start] > nums[end]) {
            while (start < end) {
                val mid = (start + end) / 2
                if (nums[start] > nums[mid]) end = mid
                else if (nums[mid] > nums[end]) start = mid
                if (end - start == 1 && nums[end] < nums[start]) {
                    start = end
                    end--
                }
            }
        }
        while (end != start) {
            val mid = if(end > start) (start + end) / 2 else (start + end + nums.size) / 2 % nums.size
            if(target > nums[mid]) start = (mid + 1) % nums.size
            else if(target < nums[mid]) end = mid
            else return mid
        }
        if(nums[start] == target) return start
        return -1
    }
}

fun main(args: Array<String>) {
    val tmp = SearchInRotatedSortedArray()
    println(tmp.search(intArrayOf(4,5,6,7,0,1,2), 3)) // -1
    println(tmp.search(intArrayOf(4,5,6,7,0,1,2), 0)) // 4
    println(tmp.search(intArrayOf(1), 1)) // 0
    println(tmp.search(intArrayOf(4,5,6,7,8,1,2,3), 8)) // 4
    println(tmp.search(intArrayOf(1,3), 0)) // -1
}