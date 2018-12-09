package com.leetcode

@Suppress("unused")
class SwapNodesInPairs {
    fun swapPairs(head: ListNode?): ListNode? {
        if(head?.next == null) return head
        var pre: ListNode? = ListNode(0)
        val ans = pre
        pre!!.next = head
        while(pre?.next != null) {
            val pair1 = pre.next
            val pair2 = pair1?.next ?: break
            pre.next = pair2
            pair1.next = pair2.next
            pair2.next = pair1
            pre = pair1
        }
        return ans!!.next
    }

    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }
}