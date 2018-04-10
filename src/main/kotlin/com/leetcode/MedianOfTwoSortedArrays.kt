package com.leetcode

class MedianOfTwoSortedArrays {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if((nums1.size + nums2.size) % 2 == 1) {
            return findNth(nums1, 0, nums2, 0, (nums1.size + nums2.size) / 2 + 1)
        } else {
            return findNth1(nums1, 0, nums2, 0, (nums1.size + nums2.size) / 2)
        }
    }

    fun findNth(nums1: IntArray, start1: Int, nums2: IntArray, start2: Int, rank: Int): Double {
        if(nums1.size - start1 > nums2.size - start2) {
            return findNth(nums2, start2, nums1, start1, rank)
        }
        if(nums1.size - start1 == 0) {
            return nums2[start2 + rank - 1].toDouble()
        }
        if(rank == 1) {
            return Math.min(nums1[start1], nums2[start2]).toDouble()
        }

        val r1 = Math.min(rank / 2, nums1.size - start1)
        val r2 = rank - r1
        if(nums1[start1 + r1 - 1] < nums2[start2 + r2 - 1]) {
            return findNth(nums1, start1 + r1, nums2, start2, rank - r1)
        } else if(nums1[start1 + r1 - 1] > nums2[start2 + r2 - 1]) {
            return findNth(nums1, start1, nums2, start2 + r2, rank - r2)
        } else {
            return nums1[start1 + r1 - 1].toDouble()
        }
    }

    fun findNth1(nums1: IntArray, start1: Int, nums2: IntArray, start2: Int, rank: Int): Double {
        if(nums1.size - start1 > nums2.size - start2) {
            return findNth1(nums2, start2, nums1, start1, rank)
        }
        if(nums1.size - start1 == 0) {
            return (nums2[start2 + rank - 1].toDouble() + nums2[start2 + rank].toDouble()) / 2
        }
        if(rank == 1) {
            if(nums1[start1] < nums2[start2]) {
                return (nums1[start1].toDouble() + findNth(nums1, start1 + 1, nums2, start2, 1)) / 2
            } else {
                return (nums2[start2].toDouble() + findNth(nums1, start1, nums2, start2 + 1, 1)) / 2
            }
        }

        val r1 = Math.min(rank / 2, nums1.size - start1)
        val r2 = rank - r1
        if(nums1[start1 + r1 - 1] < nums2[start2 + r2 - 1]) {
            return findNth1(nums1, start1 + r1, nums2, start2, rank - r1)
        } else if(nums1[start1 + r1 - 1] > nums2[start2 + r2 - 1]) {
            return findNth1(nums1, start1, nums2, start2 + r2, rank - r2)
        } else {
            return (nums1[start1 + r1 - 1].toDouble() + findNth(nums1, start1 + r1, nums2, start2, rank - r1 + 1).toDouble()) / 2
        }
    }

}