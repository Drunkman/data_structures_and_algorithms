package com.leetcode

@Suppress("unused")
class ReverseNodesInKGroup {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if(k < 2) return head
        if(head?.next == null) return head
        val ans = ListNode(0)
        ans.next = head
        var pre = head
        var post = head
        var lastPost = ans
        while(post?.next != null) {
            var i = 1
            var record = pre
            while(i < k) {
                if(record?.next == null) {
                    return ans.next
                } else {
                    record = record.next
                    i++
                }
            }
            i = 1
            while(i < k) {
                if(post.next == null) break
                val temp = post.next?.next
                post.next!!.next = pre
                pre = post.next
                post.next = temp
                i++
            }
            lastPost.next = pre
            lastPost = post
            post = post.next
            pre = post
        }
        return ans.next
    }

    class ListNode(var `val`: Int = 0) {
        var next: ListNode? = null
    }
}