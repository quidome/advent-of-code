package me.quido.year2024.compliancy

import me.quido.util.Solver
import me.quido.util.chunkInput
import me.quido.util.toIntList
import me.quido.util.toPairOfInt

class SafetyManual : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val (orderingRulesChunk, manualsPages) = input.chunkInput().let {
            Pair(
                it[0].map { it.toPairOfInt("|") },
                it[1].map { it.toIntList(",") },
            )
        }

        val orderingRules = orderingRulesChunk.groupBy({ it.first }, { it.second })
        val (validManuals, invalidManuals) = manualsPages.partition { it.allPagesInProperOrder(orderingRules) }

        val validManualsMiddlePages = validManuals.map { it.getMiddleListItem() }
        val invalidManualsMiddlePages = invalidManuals.map {
            val t = it.correct(orderingRules)
            t.getMiddleListItem()
        }

        return Pair(validManualsMiddlePages.sum(), invalidManualsMiddlePages.sum())
    }

    private fun List<Int>.getMiddleListItem(): Int = this.get(this.size / 2)

    private fun List<Int>.allPagesInProperOrder(orderingRules: Map<Int, List<Int>>): Boolean =
        this
            .zipWithNext { left, right ->
                Pair(left, right).pagePairInProperOrder(orderingRules)
            }.all { it }

    private fun List<Int>.correct(orderingRules: Map<Int, List<Int>>): List<Int> {
        this.indices.forEach { i ->
            if (i >= this.size - 1) return this
            val left = this[i]
            val right = this[i + 1]
            if (!Pair(left, right).pagePairInProperOrder(orderingRules)) return this.swapped(i, i + 1).correct(orderingRules)
        }

        return this
    }

    private fun List<Int>.swapped(first: Int, second: Int): List<Int> {
        if (first !in indices || second !in indices) return this

        return toMutableList().apply {
            this[first] = this[second].also { this[second] = this[first] }
        }
    }

    private fun Pair<Int, Int>.pagePairInProperOrder(orderingRules: Map<Int, List<Int>>): Boolean =
        orderingRules[first]?.contains(second) ?: false
}
