package me.quido.year2023.engineering

import me.quido.util.Solver
import me.quido.util.nonBlank

val numbersRegex = """\d+""".toRegex()
val partsRegex = """[^\d.]""".toRegex()

data class Coordinate(
    val row: Int,
    val column: Int,
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
    val number: Int,
) {
    fun value() = number

    fun coordinatesReach(gridLimits: Coordinate): List<Coordinate> =
        this
            .growBoundedRange(coordinate.row..coordinate.row, gridLimits.row)
            .flatMap { row ->
                this.growBoundedRange(this.range(), gridLimits.row).map { column ->
                    Coordinate(row, column)
                }
            }

    private fun range() = coordinate.column..coordinate.column + number.toString().lastIndex

    private fun growBoundedRange(
        range: IntRange,
        max: Int,
    ) = (range.first - 1).coerceAtLeast(0)..(range.last + 1).coerceAtMost(max)
}

class EngineReader : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val engineParts = engineParts(input.nonBlank())

        return engineParts.sumOf { it.partNumbers.sum() } to
            engineParts
                .filter { it.isGear() }
                .sumOf { it.partNumbers.reduce(Int::times) }
    }

    private fun engineParts(input: List<String>): List<EnginePart> =
        input.flatMapIndexed { index, line ->
            partsRegex
                .findAll(line)
                .map {
                    EnginePart(Coordinate(index, it.range.first), it.value[0], addPartNumbers(input, index, it.range.first))
                }
        }

    private fun partNumbers(input: List<String>): List<PartNumber> =
        input.flatMapIndexed { index, line ->
            numbersRegex
                .findAll(line)
                .map { PartNumber(Coordinate(index, it.range.first), it.value.toInt()) }
        }

    private fun addPartNumbers(
        input: List<String>,
        row: Int,
        column: Int,
    ): List<Int> =
        partNumbers(input)
            .filter {
                Coordinate(row, column) in
                    it.coordinatesReach(
                        Coordinate(
                            input.lastIndex,
                            input.first().lastIndex,
                        ),
                    )
            }.map { it.value() }
}
