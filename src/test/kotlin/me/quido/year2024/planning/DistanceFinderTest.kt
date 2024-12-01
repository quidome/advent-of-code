package me.quido.year2024.planning

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DistanceFinderTest {
    val input =
        """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
        """.trimIndent().lines()

    @Test
    fun `with given input, the correct distance should be returned`() {
        val distanceFinder = DistanceFinder()
        val expected = 11L to 31L

        val output = distanceFinder.solve(input)

        assertEquals(expected, output)
    }
}
