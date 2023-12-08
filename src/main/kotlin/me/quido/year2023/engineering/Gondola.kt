package me.quido.year2023.engineering

import me.quido.util.Solver
import me.quido.util.nonBlank

class Gondola : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {

        return findPartNumbers(input.nonBlank()) to 0
    }

    private fun findPartNumbers(input: List<String>): Int {
        var returnValue = 0
        val maxLineIndex = input.size - 1
        val numbersRegex = """(\d+)""".toRegex()

        input.forEachIndexed { index, line ->
            val lineRange = (index - 1).coerceAtLeast(0)..(index + 1).coerceAtMost(maxLineIndex)
            var matchResult = numbersRegex.find(line)

            while (matchResult != null) {
                val checkRange =
                    (matchResult.range.first - 1).coerceAtLeast(0)..(matchResult.range.last + 1).coerceAtMost(line.length - 1)

                if (isEnginePart(input.subList(lineRange.first, lineRange.last + 1), checkRange)) {
                    returnValue += matchResult.value.toInt()
                }
                matchResult = matchResult.next()
            }

        }
        return returnValue
    }

    private fun isEnginePart(lines: List<String>, checkRange: IntRange): Boolean {
        lines.forEach { if (""".*[^\d.].*""".toRegex().containsMatchIn(it.substring(checkRange))) return true }
        return false
    }
}
