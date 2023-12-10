package me.quido.year2023.engineering

import me.quido.util.Solver
import me.quido.util.nonBlank

val numbersRegex = """\d+""".toRegex()
val partsRegex = """[^\d.]""".toRegex()

data class Coordinate(
    val x: Int,
    val y: Int,
)

data class EnginePart(
    val coordinate: Coordinate,
    val type: Char,
    val partNumbers: List<Int>,
) {
    fun isGear() = type == '*' && partNumbers.size == 2
}

data class PartNumber(
    val coordinate: Coordinate,
    val number: String,
) {
    private fun range() = coordinate.y..coordinate.y + number.lastIndex
    fun value() = number.toInt()
    fun touches(gridLimits: Coordinate): List<Coordinate> {
        val coordinates: MutableList<Coordinate> = mutableListOf()
        for (row in
        growBoundedRange(coordinate.x..coordinate.x, gridLimits.x)) {
            for (column in growBoundedRange(this.range(), gridLimits.x)) {
                coordinates += Coordinate(row, column)
            }
        }
        return coordinates
    }
}

fun growBoundedRange(range: IntRange, max: Int) =
    (range.first - 1).coerceAtLeast(0)..(range.last + 1).coerceAtMost(max)

class Gondola : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val engineParts = engineParts(input.nonBlank())

        return engineParts.sumOf { it.partNumbers.reduce { acc, i -> acc + i } } to
                engineParts.filter { it.isGear() }
                    .sumOf { it.partNumbers.reduce { acc, i -> acc * i } }
    }

    private fun engineParts(input: List<String>): List<EnginePart> = input.flatMapIndexed { index, line ->
        partsRegex.findAll(line)
            .map {
                EnginePart(Coordinate(index, it.range.first), it.value[0], addPartNumbers(input, index, it.range.first))

            }
    }

    private fun partNumbers(input: List<String>): List<PartNumber> = input.flatMapIndexed { index, line ->
        numbersRegex.findAll(line)
            .map { PartNumber(Coordinate(index, it.range.first), it.value) }
    }

    private fun addPartNumbers(input: List<String>, row: Int, column: Int): List<Int> =
        partNumbers(input).filter {
            Coordinate(row, column) in it.touches(
                Coordinate(
                    input.lastIndex,
                    input.first().lastIndex
                )
            )
        }.map { it.value() }

}
