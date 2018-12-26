package com.leetcode

class SearchInRotatedSortedArray2 {
    fun search(nums: IntArray, target: Int): Int {
        if(nums.isEmpty()) return -1
        var start = 0
        var end = nums.size - 1
        while(start <= end) {
            val mid = (start + end) / 2
            if(target < nums[mid]) {
                if(target <= nums.last() && nums[mid] > nums.last()) start = mid + 1
                else end = mid - 1
            } else if(target > nums[mid]) {
                if(target > nums.last() && nums[mid] < nums.last()) end = mid - 1
                else start = mid + 1
            } else return mid
        }
        return -1
    }
}

fun main(args: Array<String>) {
    val tmp = SearchInRotatedSortedArray2()
    println(tmp.search(intArrayOf(4,5,6,7,0,1,2), 3)) // -1
    println(tmp.search(intArrayOf(4,5,6,7,0,1,2), 0)) // 4
    println(tmp.search(intArrayOf(1), 1)) // 0
    println(tmp.search(intArrayOf(4,5,6,7,8,1,2,3), 8)) // 4
    println(tmp.search(intArrayOf(1,3), 0)) // -1
}