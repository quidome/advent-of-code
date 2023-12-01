package me.quido.year2022.day3

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {
    private val input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent().trimEnd()

    private val solver = Day3(input)

    @Test
    fun part1() {
        val expected = 157
        assertEquals(expected, solver.part1())
    }

//    @Test
//    fun part2() {
//        val expected = 70
//        assertEquals(expected, solver.part2())
//    }
}
