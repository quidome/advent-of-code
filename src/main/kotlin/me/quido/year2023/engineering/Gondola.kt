package me.quido.year2023.engineering

import me.quido.util.Solver
import me.quido.util.nonBlank

val numbersRegex = """(\d+)""".toRegex()
val symbolRegex = """[^\d.]""".toRegex()

class Gondola : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {

        return findPartNumbers(input.nonBlank()) to 0
    }

    private fun findPartNumbers(input: List<String>): Int {
        val numbers = input.flatMapIndexed { index, line ->
            val lineRange = growBoundedRange(index..index, input.lastIndex)
            numbersRegex.findAll(line)
                .filter { isEnginePart(input.slice(lineRange), it.range) }
                .map { it.value.toInt() }
        }

        return numbers.sum()
    }

    private fun growBoundedRange(range: IntRange, max: Int) =
        (range.first - 1).coerceAtLeast(0)..(range.last + 1).coerceAtMost(max)

    private fun isEnginePart(lines: List<String>, checkRange: IntRange) =
        lines.any { symbolRegex.containsMatchIn(it.substring(growBoundedRange(checkRange, it.lastIndex))) }
}
