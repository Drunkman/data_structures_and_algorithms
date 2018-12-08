package com.leetcode

class RemoveNthNodeFromEndOfList {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var num = n
        var node = head
        while (num > 1) {
            if (node != null) {
                node = node.next
                num--
            } else {
                return head
            }
        }
        var pre: ListNode? = ListNode(0)
        pre!!.next = head
        val ans = pre
        var removeNode = head
        while (node != null) {
            node = node.next
            pre = removeNode
            removeNode = removeNode!!.next
        }
        pre!!.next = removeNode?.next
        return ans.next
    }

    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }
}

