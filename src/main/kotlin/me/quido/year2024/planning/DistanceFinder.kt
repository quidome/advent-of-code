package me.quido.year2024.planning

import me.quido.util.Solver
import kotlin.math.abs

class DistanceFinder : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val (left, right) =
            input
                .map {
                    it.split("""\D+""".toRegex()).let {
                        Pair(it[0].toLong(), it[1].toLong())
                    }
                }.unzip()

        val distanceSum =
            left
                .sorted()
                .zip(right.sorted())
                .map { (left, right) -> abs(left - right) }
                .sum()

        val frequencies: Map<Long, Int> = right.groupingBy { it }.eachCount()
        val similarities =
            left
                .map {
                    it * frequencies.getOrDefault(it, 0)
                }.sum()

        return distanceSum to similarities
    }
}
