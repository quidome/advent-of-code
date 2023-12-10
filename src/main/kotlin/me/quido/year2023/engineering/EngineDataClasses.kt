package me.quido.year2023.engineering

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
        this.growBoundedRange(coordinate.row..coordinate.row, gridLimits.row)
            .flatMap { row ->
                this.growBoundedRange(this.range(), gridLimits.row).map { column ->
                    Coordinate(row, column)
                }
            }

    private fun range() = coordinate.column..coordinate.column + number.toString().lastIndex

    private fun growBoundedRange(range: IntRange, max: Int) =
        (range.first - 1).coerceAtLeast(0)..(range.last + 1).coerceAtMost(max)
}