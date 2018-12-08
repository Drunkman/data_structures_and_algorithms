package com.leetcode

class ValidParentheses {
    fun isValid(s: String): Boolean {
        val stack = mutableListOf<Char>()
        s.forEach {
            if (it == ')') {
                if(stack.isNotEmpty() && stack.last() == '(') {
                    stack.removeAt(stack.size - 1)
                } else {
                    return false
                }
            } else if (it == '}') {
                if(stack.isNotEmpty() && stack.last() == '{') {
                    stack.removeAt(stack.size - 1)
                } else {
                    return false
                }
            } else if (it == ']') {
                if(stack.isNotEmpty() && stack.last() == '[') {
                    stack.removeAt(stack.size - 1)
                } else {
                    return false
                }
            } else {
                stack.add(it)
            }
        }
        return stack.isEmpty()
    }
}

fun main(args: Array<String>) {
    println(ValidParentheses().isValid("()")) // true
    println(ValidParentheses().isValid("()[]{}")) // true
    println(ValidParentheses().isValid("(]")) // false
    println(ValidParentheses().isValid("([)]")) // false
    println(ValidParentheses().isValid("{[]}")) // true
}