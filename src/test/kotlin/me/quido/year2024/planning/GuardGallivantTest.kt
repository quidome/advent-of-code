package me.quido.year2024.planning

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GuardGallivantTest {
    @Test
    fun solver() {
        val guardGallivant = GuardGallivant()
        val input =
            """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """.trimIndent().lines()
        val expected = 41 to 41

        val output = guardGallivant.solve(input)

        assertEquals(expected, output)
    }
}
