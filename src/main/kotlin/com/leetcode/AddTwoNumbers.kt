package com.leetcode

class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
}

@Suppress("unused")
class AddTwoNumbers {
    fun addTwoNumbers(ln1: ListNode?, ln2: ListNode?): ListNode? {
        var l1 = ln1
        var l2 = ln2
        var n1: Int
        var n2: Int
        var n3: Int
        var l3: ListNode? = null
        var temp: ListNode? = null
        var flag = 0

        while (l1 != null || l2 != null || flag == 1) {
            n1 = if (l1 != null) l1.`val` else 0
            n2 = if (l2 != null) l2.`val` else 0
            n3 = n1 + n2 + flag

            flag = n3 / 10
            l1 = if (l1 != null) l1.next else null
            l2 = if (l2 != null) l2.next else null
            if (l3 == null) {
                l3 = ListNode(n3 % 10)
                temp = l3
            } else {
                temp!!.next = ListNode(n3 % 10)
                temp = temp.next
            }
        }
        return l3
    }
}