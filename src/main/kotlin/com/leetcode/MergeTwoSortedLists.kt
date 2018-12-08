package com.leetcode

@Suppress("unused")
class MergeTwoSortedLists {

    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) return null
        else if (l1 != null && l2 == null) return l1
        else if (l1 == null && l2 != null) return l2
        var p = if(l1!!.`val` < l2!!.`val`) l1 else l2
        var p1 = l1
        var p2 = l2
        while (p1 != null && p2 != null) {
            if (p1.`val` < p2.`val`) {
                val temp = p1
                p1 = p1.next
                p.next = temp
                p = temp
            } else {
                val temp = p2
                p2 = p2.next
                p.next = temp
                p = temp
            }
        }
        if(p1 == null && p2 != null) {
            p.next = p2
        } else if(p1 != null && p2 == null) {
            p.next = p1
        }
        return if(l1.`val` < l2.`val`) l1 else l2
    }

    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }
}