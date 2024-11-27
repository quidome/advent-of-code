package me.quido.year2022.day0

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day0Test {
    private val input =
        """
        Example input
        """.trimIndent().trimEnd()

    private val solver = Day0(input)

    @Test
    fun part1() {
        val expected = 1
        assertEquals(expected, solver.part1())
    }

    @Test
    fun part2() {
        val expected = 2
        assertEquals(expected, solver.part2())
    }
}
