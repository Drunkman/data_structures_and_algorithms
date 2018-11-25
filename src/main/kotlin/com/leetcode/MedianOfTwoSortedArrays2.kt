package com.leetcode

@Suppress("unused")
class MedianOfTwoSortedArrays2 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size) {
            return findMedianSortedArrays(nums2, nums1)
        }
        var min = 0
        var max = nums1.size
        val total = nums1.size + nums2.size
        val medianIndex = (total + 1) / 2

        while (min <= max) {
            val i = (max + min) / 2
            val j = medianIndex - i

            if (i > min && nums1[i - 1] > nums2[j]) {
                max--
            } else if (i < max && nums1[i] < nums2[j - 1]) {
                min++
            } else {
                val maxLeft = when {
                    i == 0 -> nums2[j - 1]
                    j == 0 -> nums1[i - 1]
                    else -> Math.max(nums1[i - 1], nums2[j - 1])
                }
                if (total % 2 == 1) {
                    return maxLeft.toDouble()
                }

                val minRight = when {
                    i == nums1.size -> nums2[j]
                    j == nums2.size -> nums1[i]
                    else -> Math.min(nums1[i], nums2[j])
                }
                return (maxLeft.toDouble() + minRight.toDouble()) / 2
            }
        }
        return 0.0
    }
}