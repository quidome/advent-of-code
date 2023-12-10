package me.quido.year2023.engineering

import me.quido.util.Solver
import me.quido.util.nonBlank

val numbersRegex = """\d+""".toRegex()
val partsRegex = """[^\d.]""".toRegex()

data class Coordinate(
    val x: Int,
    val y: Int
)
data class EnginePart(
    val coordinate: Coordinate,
    val type: Char,
    val partNumbers: List<Int>
)

data class PartNumbers(
    val coordinate: Coordinate,
    val number: String,
) {
    fun range() = coordinate.y .. coordinate.y + number.length
    fun value() = number.toInt()
    fun touches(gridLimits: Coordinate): List<Coordinate> {
        // should return a list of coordinates that it touches
        // list of coordinates =
        // - own coordinate
        // - own coordinate - 1
        // - own coordinate + size + 1
        // - and also for the lines above and below
        growBoundedRange(range(), gridLimits.x)

        return listOf(Coordinate(0,0), Coordinate(0,0))
    }
}
fun growBoundedRange(range: IntRange, max: Int) =
    (range.first - 1).coerceAtLeast(0)..(range.last + 1).coerceAtMost(max)

class Gondola : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        /*
        Writing down some ideas around this problem. I solved part one
        but part two makes things a bit more clear.

        The numbers in the schematic are part numbers, but only when they
        connect to a part.
        The symbols are the parts.

        The first assignment was amount finding all the parts, and sum all the part numbers.
        The second assignment is about finding all the gear ratios. Each asterisk in the schematic
        with exactly two part numbers is a gear. The product of these part numbers is the gear ratio.
        The answer to problem 2 is the sum of all the gear ratios.

        So what I need (and could have used in part 1): a list of engineParts, with their part numbers
        and the symbol that identifies them.
        */

        val engineParts = engineParts(input.nonBlank())

        return findPartNumbers(input.nonBlank()) to 467835 //engineParts(input.nonBlank())
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

    private fun engineParts(input: List<String>): List<EnginePart>  = input.flatMapIndexed { index, line ->
            partsRegex.findAll(line)
                .map {
                    EnginePart(Coordinate(index,it.range.first), it.value[0], addPartNumbers(input, index, it.range.first))
                }
        }

    private fun partNumbers(input: List<String>): List<PartNumbers>  = input.flatMapIndexed { index, line ->
            numbersRegex.findAll(line)
                .map {PartNumbers(Coordinate(index,it.range.first), it.value)}
        }

    private fun addPartNumbers(input: List<String>, row: Int, column: Int): List<Int> {
        // Find all part numbers surrounding given row and column
        // Part numbers are all numbers that are connected to a part
        // Horizontally, vertically or diagonally.

        val partNumbers = partNumbers(input)

        val rowRange = growBoundedRange( row .. row, input.lastIndex)
        val x = input.slice(rowRange).forEach {
            partsRegex.findAll(it.slice(growBoundedRange(column .. column, 1)))
        }
        println(x)
        return listOf(0,0)
    }

    private fun growBoundedRange(range: IntRange, max: Int) =
        (range.first - 1).coerceAtLeast(0)..(range.last + 1).coerceAtMost(max)

    private fun isEnginePart(lines: List<String>, checkRange: IntRange) =
        lines.any { partsRegex.containsMatchIn(it.substring(growBoundedRange(checkRange, it.lastIndex))) }

}
