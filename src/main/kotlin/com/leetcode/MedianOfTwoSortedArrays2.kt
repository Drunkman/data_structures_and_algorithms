package com.leetcode

class MedianOfTwoSortedArrays2 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size) {
            return findMedianSortedArrays(nums2, nums1)
        }
        var min = 0
        var max = nums1.size
        var total = nums1.size + nums2.size
        var medianIndex = (total + 1) / 2

        while (min <= max) {
            var i = (max + min) / 2
            var j = medianIndex - i

            if (i > min && nums1[i - 1] > nums2[j]) {
                max--
            } else if (i < max && nums1[i] < nums2[j - 1]) {
                min++
            } else {
                var maxLeft = 0
                if (i == 0) {
                    maxLeft = nums2[j - 1]
                } else if (j == 0) {
                    maxLeft = nums1[i - 1]
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1])
                }
                if (total % 2 == 1) {
                    return maxLeft.toDouble()
                }

                var minRight = 0
                if (i == nums1.size) {
                    minRight = nums2[j]
                } else if (j == nums2.size) {
                    minRight = nums1[i]
                } else {
                    minRight = Math.min(nums1[i], nums2[j])
                }
                return (maxLeft.toDouble() + minRight.toDouble()) / 2
            }
        }
        return 0.0
    }
}