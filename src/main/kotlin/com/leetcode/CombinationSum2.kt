package com.leetcode

class CombinationSum2 {
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sort()
        if(candidates.isEmpty() || target < candidates[0]) return emptyList()
        else if(target == candidates[0]) return listOf(listOf(target))
        val ans = mutableListOf<List<Int>>()
        ans.addAll(find(candidates, target - candidates[0], 1).map {
            val list = it.toMutableList()
            list.add(0, candidates[0])
            list
        })
        ans.addAll(find(candidates, target, 1))
        return ans.distinct()
    }

    fun find(candidates: IntArray, target: Int, start: Int): List<List<Int>> {
        if(start >= candidates.size || target < candidates[start]) return emptyList()
        else if(target == candidates[start]) return listOf(listOf(target))
        val ans = mutableListOf<List<Int>>()
        ans.addAll(find(candidates, target - candidates[start], start + 1).map {
            val list = it.toMutableList()
            list.add(0, candidates[start])
            list
        })
        ans.addAll(find(candidates, target, start + 1))
        return ans.distinct()
    }
}