package com.leetcode

@Suppress("unused")
class MergeKSortedLists {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if(lists.isEmpty()) return null
        if(lists.size == 1) return lists[0]
        return dive(lists, 0, lists.size - 1)
    }

    fun dive(lists: Array<ListNode?>, l: Int, r: Int): ListNode? {
        if (r - l == 1) return mergeTwoLists(lists[l], lists[r])
        if (l == r) return lists[l]
        val mid = (r + l) / 2
        return mergeTwoLists(dive(lists, l, mid), dive(lists, mid + 1, r))
    }

    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) return null
        else if (l2 == null) return l1
        else if (l1 == null) return l2
        var p = if(l1.`val` < l2.`val`) l1 else l2
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