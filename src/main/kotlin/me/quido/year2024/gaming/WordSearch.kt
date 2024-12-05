package me.quido.year2024.gaming

import me.quido.util.Solver

enum class Property(
    val char: Char?,
) {
    NULL(null),
    X('X'),
    M('M'),
    A('A'),
    S('S'),
    ;

    companion object {
        fun fromChar(char: Char?): Property = values().find { it.char == char } ?: NULL

        fun next(value: Property): Property = values()[(value.ordinal + 1) % values().size]
    }
}

enum class Direction {
    ANY,
    UP,
    UP_RIGHT,
    UP_LEFT,
    DOWN,
    DOWN_RIGHT,
    DOWN_LEFT,
    LEFT,
    RIGHT,
}

data class Coordinate(
    val x: Int,
    val y: Int,
)

data class Position(
    val coordinate: Coordinate,
    var value: Property? = null,
)

data class Board(
    val width: Int,
    val height: Int,
    val positions: List<Position>,
    var wordCount: Int = 0,
) {
    fun getPosition(coordinate: Coordinate): Position? =
        positions.find { position ->
            position.coordinate.x == coordinate.x && position.coordinate.y == coordinate.y
        }

    fun getNeighboringCoordinates(
        x: Int,
        y: Int,
    ): List<Pair<Int, Int>> =
        makeRange(x, this.width - 1).flatMap { currentX ->
            makeRange(y, this.height - 1).map { currentY ->
                currentX to currentY
            }
        }

    fun getInlineCoordinates(
        coordinate: Coordinate,
        direction: Direction,
    ): Coordinate? {
        var x = coordinate.x
        var y = coordinate.y

        val directionString = direction.toString()
        if ("LEFT" in directionString) x--
        if ("RIGHT" in directionString) x++
        if ("UP" in directionString) y--
        if ("DOWN" in directionString) y++

        if (x < 0 || y < 0 || x >= width || y >= height) return null
        return Coordinate(x, y)
    }

    private fun makeRange(
        center: Int,
        max: Int,
    ): IntRange = (center - 1).coerceAtLeast(0)..(center + 1).coerceAtMost(max)
}

class WordSearch : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val board = createBoardFromInput(input)
        var wordCount = 0
        var crossCount = 0

        board.positions
            .filter { position -> position.value == Property.X }
            .forEach { startingPosition ->
                wordCount +=
                    findXmasInSingleDirection(board, startingPosition.coordinate, Property.X)
            }

        board.positions
            .filter { position -> position.value == Property.A }
            .forEach { startingPosition ->
                crossCount += findXmasCrosses(board, startingPosition.coordinate, Property.X)
            }

        return wordCount to crossCount
    }

    private fun findXmasCrosses(
        board: Board,
        coordinate: Coordinate,
        property: Property,
        direction: Direction = Direction.ANY,
    ): Int {
        // Let's get cracking

        // We don't need to work on any A in the outer line, those won't
        // create proper X-es.

        return 1
    }

    private fun findXmasInSingleDirection(
        board: Board,
        coordinate: Coordinate,
        property: Property,
        direction: Direction = Direction.ANY,
    ): Int {
        val nextProperty = Property.next(property)

        if (board.getPosition(coordinate)?.value != property) { // Property does not match
            return 0
        } else if (nextProperty == Property.NULL) { // End of the line, last property was found
            return 1
        }

        var count = 0
        if (direction == Direction.ANY) {
            for (d in Direction.values().filter { it != Direction.ANY }) {
                val nextCoordinate = board.getInlineCoordinates(coordinate, d)
                if (nextCoordinate != null) {
                    count += findXmasInSingleDirection(board, nextCoordinate, nextProperty, d)
                }
            }
        } else {
            val nextCoordinate = board.getInlineCoordinates(coordinate, direction) ?: return 0
            count += findXmasInSingleDirection(board, nextCoordinate, nextProperty, direction)
        }

        return count
    }

    private fun findXmasSnakeStyle(
        board: Board,
        x: Int,
        y: Int,
        propertyToFind: Property, // property to look for
    ): Int {
        val nextProperty = Property.next(propertyToFind)

        if (board.getPosition(Coordinate(x, y))?.value != propertyToFind) { // Property does not match
            return 0
        } else if (nextProperty == Property.NULL) { // End of the line, last property was found
            return 1
        }

        var count = 0
        val surroundings: List<Pair<Int, Int>> = board.getNeighboringCoordinates(x, y)
        for (surrounding in surroundings) {
            count += findXmasSnakeStyle(board, surrounding.first, surrounding.second, nextProperty)
        }

        return count
    }

    private fun createBoardFromInput(boardInput: List<String>): Board {
        val width = boardInput.maxOf { it.length }
        val height = boardInput.size

        val positions =
            (0 until width).flatMap { x ->
                (0 until height).map { y ->
                    Position(Coordinate(x, y), Property.fromChar(boardInput[x][y]))
                }
            }

        return Board(width = width, height = height, positions = positions)
    }
}
