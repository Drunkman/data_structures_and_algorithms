package com.leetcode

@Suppress("unused")
class MedianOfTwoSortedArrays {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double =
            if ((nums1.size + nums2.size) % 2 == 1) {
                findNth(nums1, 0, nums2, 0, (nums1.size + nums2.size) / 2 + 1)
            } else {
                findNth1(nums1, 0, nums2, 0, (nums1.size + nums2.size) / 2)
            }

    private fun findNth(nums1: IntArray, start1: Int, nums2: IntArray, start2: Int, rank: Int): Double {
        if (nums1.size - start1 > nums2.size - start2) {
            return findNth(nums2, start2, nums1, start1, rank)
        }
        if (nums1.size - start1 == 0) {
            return nums2[start2 + rank - 1].toDouble()
        }
        if (rank == 1) {
            return Math.min(nums1[start1], nums2[start2]).toDouble()
        }

        val r1 = Math.min(rank / 2, nums1.size - start1)
        val r2 = rank - r1
        return when {
            nums1[start1 + r1 - 1] < nums2[start2 + r2 - 1] -> findNth(nums1, start1 + r1, nums2, start2, rank - r1)
            nums1[start1 + r1 - 1] > nums2[start2 + r2 - 1] -> findNth(nums1, start1, nums2, start2 + r2, rank - r2)
            else -> nums1[start1 + r1 - 1].toDouble()
        }
    }

    private fun findNth1(nums1: IntArray, start1: Int, nums2: IntArray, start2: Int, rank: Int): Double {
        if (nums1.size - start1 > nums2.size - start2) {
            return findNth1(nums2, start2, nums1, start1, rank)
        }
        if (nums1.size - start1 == 0) {
            return (nums2[start2 + rank - 1].toDouble() + nums2[start2 + rank].toDouble()) / 2
        }
        if (rank == 1) {
            return if (nums1[start1] < nums2[start2]) {
                (nums1[start1].toDouble() + findNth(nums1, start1 + 1, nums2, start2, 1)) / 2
            } else {
                (nums2[start2].toDouble() + findNth(nums1, start1, nums2, start2 + 1, 1)) / 2
            }
        }

        val r1 = Math.min(rank / 2, nums1.size - start1)
        val r2 = rank - r1
        return when {
            nums1[start1 + r1 - 1] < nums2[start2 + r2 - 1] -> findNth1(nums1, start1 + r1, nums2, start2, rank - r1)
            nums1[start1 + r1 - 1] > nums2[start2 + r2 - 1] -> findNth1(nums1, start1, nums2, start2 + r2, rank - r2)
            else -> (nums1[start1 + r1 - 1].toDouble() + findNth(nums1, start1 + r1, nums2, start2, rank - r1 + 1)) / 2
        }
    }

}