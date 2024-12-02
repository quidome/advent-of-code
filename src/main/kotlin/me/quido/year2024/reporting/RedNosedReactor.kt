package me.quido.year2024.reporting

import me.quido.util.Solver
import me.quido.util.toIntList

enum class Direction {
    NONE,
    DOWN,
    UP,
}

class RedNosedReactor : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val reports = input.map { it.toIntList().orderAscending() }

        val safeReports =
            reports
                .map { report ->
                    isSafe(report)
                }.count { it }

        val tolerableReport =
            reports
                .map { report ->
                    report.isSafeDampened()
                }.count { it }

        return safeReports to tolerableReport
    }

    private fun isSafe(report: List<Int>): Boolean {
        var isSafe = true
        report.zipWithNext { a, b ->
            val gap = (b - a)
            if (gap !in listOf(1, 2, 3)) {
                isSafe = false
            }
            if (a == b) {
                isSafe = false
            }
        }
        return isSafe
    }

    private fun List<Int>.isSafeDampened(): Boolean =
        indices.any { indexToRemove ->
            val dampened = this.filterIndexed { index, _ -> index != indexToRemove }
            isSafe(dampened)
        }

    /*
    This was my (failing) attempt to solve the issue with an algorithm
    I ended up taking the solution from the JetBrains YT video and brute forced it
    See the function `isSafeDampened`

    I understand my mistake now. I was convinced that I could not fix gap problems, because I thought gaps would
    only grow bigger. That is not entirely true:
    [13, 14, 17, 19, 20, 23, 24, 30]
    In this list removing the last element would fix a gap problem.
     */
    private fun stripFirstAnomaly(report: List<Int>): List<Int> {
        var indexToRemove: Int? = null

        for (index in 0..report.size - 2) {
            val left = report[index]
            val right = report[index + 1]

            if (left >= right) {
                indexToRemove = index + 1
                break
            }
        }

        if (indexToRemove == null) {
            return report
        }

        val updatedReport =
            report
                .filterIndexed { index, _ -> index != indexToRemove }

        return updatedReport
    }

    private fun List<Int>.orderAscending(): List<Int> =
        when (reportTrend(this)) {
            Direction.DOWN -> reversed()
            else -> this
        }

    private fun reportTrend(report: List<Int>): Direction =
        if (countAscending(report) == countDescending(report)) {
            Direction.NONE
        } else if (countAscending(report) > countDescending(report)) {
            Direction.UP
        } else {
            Direction.DOWN
        }

    private fun countAscending(report: List<Int>): Int = report.zipWithNext { a, b -> a < b }.count { it }

    private fun countDescending(report: List<Int>): Int = report.zipWithNext { a, b -> a > b }.count { it }
}
