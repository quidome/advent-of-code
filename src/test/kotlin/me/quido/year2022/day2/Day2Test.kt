package me.quido.year2022.day2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {
    private val input =
        """
        A Y
        B X
        C Z
        """.trimIndent().trimEnd()

    private val solver = Day2(input)

    @Test
    fun givenTestSetMethodShouldReturn15() {
        val expected = 15

        val output = solver.part1()

        assertEquals(expected, output)
    }

    @Test
    fun givenTestSetMethodShouldReturn12() {
        val expected = 12

        val output = solver.part2()

        assertEquals(expected, output)
    }
}
