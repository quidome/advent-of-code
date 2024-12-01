package me.quido.year2024.planning

import me.quido.util.Solver
import kotlin.math.abs

class DistanceFinder : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val (left, right) =
            input
                .map { line ->
                    line.split("""\D+""".toRegex()).let {
                        Pair(it[0].toLong(), it[1].toLong())
                    }
                }.unzip()

        val distances =
            left
                .sorted()
                .zip(right.sorted())
                .map { (left, right) -> abs(left - right) }

        val frequencyMap: Map<Long, Int> = right.groupingBy { it }.eachCount()
        val similarities =
            left
                .map {
                    it * frequencyMap.getOrDefault(it, 0)
                }

        return distances.sum() to similarities.sum()
    }
}
